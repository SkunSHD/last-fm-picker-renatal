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


(defn render []
	[view {:style {:width 150}}
		 (if @user-model/isAuthorized
			 [view
				[text "KU!ee  " @user-model/username]
                [button {:title "Go profile scene" :on-press #(router-model/set_current_scene "ProfileScene")}]
				[button {
									:title "Logout"
									:on-press user-model/log_out}]]
			 [view
				[input {
						   :onSubmitEditing #(user-model/log_in (get_input_text %))
						   :onChangeText #(user-model/set_username %)
						   :value @user-model/username
						   :autoCapitalize "none"
						   :style { :borderColor "orange" :padding 2 :borderWidth 1}}]
				[button {
									:title "Login"
									:on-press user-model/log_in}]])])