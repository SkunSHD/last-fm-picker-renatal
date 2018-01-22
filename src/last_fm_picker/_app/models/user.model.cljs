(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]
;		Models
		[last-fm-picker._app.models.search-by-username :as search-by-username-model]
		) )

(def log (.-log js/console))


(def user (atom nil))
(def isAuthorized (atom false))


(defn set_username [text]
	(reset! user text))


(defn loginIn []
	(reset! isAuthorized true)
	(search-by-username-model/set_current_search @user)
	(search-by-username-model/get_user_artists)
	)


(defn loginOut []
	(reset! user nil)
	(reset! isAuthorized false)
	(search-by-username-model/set_current_search "")
	(search-by-username-model/set_current_search_artists [])
	)
