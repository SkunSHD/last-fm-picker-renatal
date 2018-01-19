(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]) )

(def log (.-log js/console))


(def user (atom nil))

(defn set_username [text]
	reset! user text)

