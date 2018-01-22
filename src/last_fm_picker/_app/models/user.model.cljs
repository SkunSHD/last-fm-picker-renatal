(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]) )

(def log (.-log js/console))


(def user (atom nil))
(def isAuthorized (atom false))

(defn set_username [text]
	(reset! user text))

(defn loginIn []
	(reset! isAuthorized true))

(defn loginOut []
	(reset! user nil)
	(reset! isAuthorized false))

