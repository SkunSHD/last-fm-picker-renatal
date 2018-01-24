(ns last-fm-picker._app.scenes.home
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;       Models
		[last-fm-picker._app.models.user :as user_model]
		[last-fm-picker._app.models.router :as router_model]
		))

(def log (.-log js/console))


(defn login []
	(if-not (clojure.string/blank? @user_model/username) (user_model/log_in)))


(defn render_username_input []
	[input {
		       :onSubmitEditing login
		       :onChangeText #(user_model/set_username %)
		       :value @user_model/username
		       :autoCapitalize "none"
		       :style { :borderColor "orange" :padding 2 :borderWidth 1}}])


(defn render_auth_button []
	(if (= @user_model/is_user_fetching true)
		[button { :title "Loggin in..." :on-press (fn [])}]
		[button { :title "Login" :on-press login}] ))


(defn render []
	[view {:style {:width 150}}
		 (if (empty? @user_model/user)
			 [view
				[render_username_input]
				[render_auth_button]]
			 [view
			  [button {:title "Go profile scene" :on-press #(router_model/set_current_scene "ProfileScene" {})}]
			  [button {
				          :title "Logout"
				          :on-press user_model/log_out}]] )
	])