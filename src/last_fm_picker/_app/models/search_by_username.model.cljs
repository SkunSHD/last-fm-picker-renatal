(ns last-fm-picker.search-by-username-model
	(:require
		[reagent.core :as r :refer [atom]]
))

(def log (.-log js/console))

; users { :username { :artists {}}}
(def users (atom {}))


(defn set_user_artists [username albums]
	(swap! users assoc username albums)
	(log (str @users) "334")
	)