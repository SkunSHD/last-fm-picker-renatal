(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]
;		Models
		[last-fm-picker._app.models.search-by-username :as search-by-username-model]
		) )

(def log (.-log js/console))


(def username (atom nil))
(def isAuthorized (atom false))


(defn set_username [text]
	(reset! username text))


(defn log_in []
	(reset! isAuthorized true)
	(search-by-username-model/set_current_search @username)
	(search-by-username-model/get_user_artists)
	)


(defn log_out []
	(reset! username nil)
	(reset! isAuthorized false)
	(search-by-username-model/set_current_search "")
	(search-by-username-model/set_current_search_artists [])
	)
