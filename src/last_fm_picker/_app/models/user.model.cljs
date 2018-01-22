(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]
;		Models
		[last-fm-picker._app.models.search-by-username :as search-by-username-model]
		) )

(def log (.-log js/console))

; Atoms
(def username (atom nil))
(def isAuthorized (atom false))


; Public
(defn set_username [text]
	(reset! username text))


(defn log_in []
	(reset! isAuthorized true))


(defn log_out []
	(reset! username nil)
	(reset! isAuthorized false))



; Private
(defn- -set_search []
	(log "SERT!!")
	(search-by-username-model/set_current_search @username))


(defn- -clear_search []
	(log "CLEAR!!")
	(search-by-username-model/set_current_search "")
	(search-by-username-model/set_current_search_artists []))


(defn- -set_search_by_username_model []
	(if (= @isAuthorized true) (-set_search) (-clear_search)))

; Watchers
(defn- -on_isAuthorized_change [key atom old new]
	(if-not(= old new) (-set_search_by_username_model)))

(add-watch isAuthorized "isAuthorized_chjange" -on_isAuthorized_change)