(ns last-fm-picker.search-by-username-model
	(:require
		[reagent.core :as r :refer [atom]]
))

(def log (.-log js/console))

; users { :username { :artists {}}}
(def users (atom {}))


(defn- str_to_cljs [string]
	(js->clj (.parse js/JSON string) :keywordize-keys true))

(defn set_user_artists [username albums]
	(let [albums_map (str_to_cljs albums)]
		(swap! users assoc username (:artist (:artists albums_map))))
	(log (:name (nth (:leonasrdo @users) 1)))
	)