(ns last-fm-picker._app.scenes.home
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;   Models
		[last-fm-picker._app.models.user :as user-model]
		))

(def log (.-log js/console))

(defn get_input_text [event]
	(.-text (.-nativeEvent event)))

(defn render []
	[view {:style {:width 150}}
		[text "HOME PAGE!!!!!!!"]
		 (if @user-model/isAuthorized
			 [view
				[text "KU! " @user-model/user]
				[button {
									:title "Logout"
									:on-press user-model/loginOut}]]
			 [view
				[input {
						   :onSubmitEditing #(user-model/loginIn (get_input_text %))
						   :onChangeText #(user-model/set_username %)
						   :value @user-model/user
						   :autoCapitalize "none"
						   :style { :borderColor "orange" :padding 2 :borderWidth 1}}]
				[button {
									:title "Login"
									:on-press user-model/loginIn}]])])