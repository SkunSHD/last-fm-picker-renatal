(ns last-fm-picker._app.models.search-by-username
	(:require
		[reagent.core :as r :refer [atom]]

		[cljs.core.async :refer [<!]]
		[cljs-http.client :as http]
	)
	(:require-macros [cljs.core.async.macros :refer [go]]))


(def log (.-log js/console))
(defn- -cljs [string] (js->clj (.parse js/JSON string) :keywordize-keys true))


; ========
; Atoms
(def users_artists (atom { }))
(def current_search (atom ""))
(def current_search_artists (atom []))
(def timeout (atom 0))


; ========
; Public
(defn set_current_search [search_text] (reset! current_search search_text))
(defn set_current_search_artists [artists] (reset! current_search_artists artists))


(defn set_user_artists [albums]
	(let [albums_map (-cljs albums)]
		(swap! users_artists assoc @current_search(:artist (:artists albums_map)))
		(log "SET set_current_search_artists!! " (:artist (:artists albums_map)))
		(set_current_search_artists (:artist (:artists albums_map)))))


(defn get_user_artists []
	(log "SEARCH: " (str "https://last-fm-server.herokuapp.com/user/artists/" @current_search))
	(set_current_search_artists nil)
	(go (let [response (<! (http/get (str "https://last-fm-server.herokuapp.com/user/artists/" @current_search)))]
		    (if (:error (-cljs (:body response)))
			    (set_current_search_artists [])
			    (set_user_artists (:body response) ))
		    )))


(defn search_user_artists []
	(if (get @users_artists @current_search)
		(log "CACHE!" (set_current_search_artists (get @users_artists @current_search)))
		(if-not (empty? @current_search) (get_user_artists)))
	)



; ========
; Watchers
(defn- -on_current_search_change [key atom old new]
	(js/clearTimeout @timeout)
	(reset! timeout(js/setTimeout search_user_artists 500)))

(add-watch current_search "current_search" -on_current_search_change)
