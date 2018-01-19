(ns last-fm-picker._app.scenes.home
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;   Models
		[last-fm-picker._app.models.user :as user-model]
		))

;(defn inputValue []
;  (if (nil? @user-model/user)
;    "username"
;    @user-model/user))

(defn handleInputChange [text]
	user-model/set_username text)

(defn render []
	[view {:style {:width 300}}
		[text "HOME PAGE!!!!!!!"]
		 (if (nil? @user-model/user)
			 [input {
								:onChangeText handleInputChange
								:value @user-model/user
								:autoCapitalize "none"
								:style { :borderColor "orange" :padding 2 :borderWidth 1}}]
			 [text "KU!" @user-model/user])])