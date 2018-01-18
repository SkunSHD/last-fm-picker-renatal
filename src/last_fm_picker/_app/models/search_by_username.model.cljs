(ns last-fm-picker.search-by-username-model
	(:require
		[reagent.core :as r :refer [atom]]
))

(def log (.-log js/console))

; users { :username { :artists {}}}
(def users (atom {}))


(defn set_user_artists [username albums]
	(swap! users assoc :x (js->clj (.parse js/JSON albums)))
	(log (type @users) "344" (.parse js/JSON albums))
	)