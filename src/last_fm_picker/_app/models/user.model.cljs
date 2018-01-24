(ns last-fm-picker._app.models.user
	(:require
		[reagent.core :as r :refer [atom]]
		[cljs.core.async :refer [<!]]
		[cljs-http.client :as http]
;		Models
		[last-fm-picker._app.models.search-by-username :as search-by-username-model]
		)
	(:require-macros [cljs.core.async.macros :refer [go]]))


(def log (.-log js/console))
(defn- -cljs [string] (js->clj (.parse js/JSON string) :keywordize-keys true))


; Atoms
(def username (atom nil))
(def user (atom nil))
(def is_user_fetching (atom false))
(def isAuthorized (atom false))


; Public
(defn set_user [new_user]
    (reset! user new_user))

(defn set_username [new_username]
	(reset! username new_username))


(defn get_user []
	(go (let [response (<! (http/get (str "https://last-fm-server.herokuapp.com/user/" @username)))]
		    (search-by-username-model/set_current_search @username)
		    (set_user (:user (-cljs (:body response))))
		    (reset! is_user_fetching false)
		    )
	    ))


(defn log_in []
;	TODO: Add ajax request to http://ws.audioscrobbler.com/2.0/?method=user.getinfo&user=rj&api_key=fb9d42de15720bcb20e6ed6fc5016a4c&format=json
;	TODO: And save as user-model/user and get rid of is_authorized
	(reset! is_user_fetching true)
	(log "loggin in...")
	(get_user))


(defn log_out []
	(set_username nil)
	(set_user nil)
	)


; Private
(defn- -set_search []

	)


(defn- -clear_search []
	(search-by-username-model/set_current_search "")
	(search-by-username-model/set_current_search_artists []))


(defn- -set_search_by_username_model []
	(if (= @isAuthorized true) (-set_search) (-clear_search)))

; Watchers
(defn- -on_isAuthorized_change [key atom old new]
	(if-not(= old new) (-set_search_by_username_model)))

(add-watch isAuthorized "isAuthorized_chjange" -on_isAuthorized_change)