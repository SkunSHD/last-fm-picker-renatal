(ns last-fm-picker._app.scenes.home
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;       Models
		[last-fm-picker._app.models.user :as user-model]
		[last-fm-picker._app.models.router :as router-model]
		))

(def log (.-log js/console))


(defn get_input_text [event]
	(.-text (.-nativeEvent event)))


(defn isNotEmptyString [inputText]
	(not))


(defn login []
	(if-not (clojure.string/blank? @user-model/username) (user-model/log_in)))


(defn render []
	[view {:style {:width 150}}
		 (if @user-model/isAuthorized
			 [view
                [button {:title "Go profile scene" :on-press #(router-model/set_current_scene "ProfileScene")}]
				[button {
							:title "Logout"
							:on-press user-model/log_out}]]
			 [view
				[input {
						   :onSubmitEditing login
						   :onChangeText #(user-model/set_username %)
						   :value @user-model/username
						   :autoCapitalize "none"
						   :style { :borderColor "orange" :padding 2 :borderWidth 1}}]
				[button {
							:title "Login"
							:on-press login}]])])