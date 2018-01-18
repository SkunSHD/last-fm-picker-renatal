(ns last-fm-picker.search-by-username-model
	(:require
		[reagent.core :as r :refer [atom]]
))


(def log (.-log js/console))


; ========
; Atoms
(def users_artists (atom {}))
(def current_search (atom ""))
(def timeout (atom 0))



; ========
; Private
(defn- str_to_cljs [string]
	(js->clj (.parse js/JSON string) :keywordize-keys true))


(defn- search_user_artists [username]
	(log "REASULT: " @current_search @users_artists))


(defn- on_current_search_change [key atom old new]
	(js/clearTimeout @timeout)
	(reset! timeout(js/setTimeout search_user_artists 1000)))



; ========
; Watchers
(add-watch current_search "current_search" on_current_search_change)



; ========
; Public
(defn set_current_search [search_text]
	(reset! current_search search_text))


(defn set_user_artists [username albums]
	(let [albums_map (str_to_cljs albums)]
		(swap! users_artists assoc username (:artist (:artists albums_map))))

	(log "AJAX!")
	)