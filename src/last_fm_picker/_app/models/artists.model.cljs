(ns last-fm-picker._app.models.artists
	(:require
		[reagent.core :as r :refer [atom]]
		[cljs.core.async :refer [<!]]
		[cljs-http.client :as http]

		[clojure.string :as s :refer [blank?]]
		)
	(:require-macros [cljs.core.async.macros :refer [go]]))

(def log (.-log js/console))
(defn- -cljs [string] (js->clj (.parse js/JSON string) :keywordize-keys true))


(def artists (atom {}))


(defn set_artist [artist-name artist]
	(swap! artists assoc artist-name artist)
	)


(defn fetch_artist [artist-name]
	(log "GET-artists-info: " (str "https://last-fm-server.herokuapp.com/artist/" artist-name))
	(if-not (blank? artist-name)
		(go (let [response (<! (http/get (str "https://last-fm-server.herokuapp.com/artist/" artist-name)))]
				(if-not (:error (-cljs (:body response)))
					(set_artist artist-name (:artist (-cljs (:body response))))))
			)))