(ns last-fm-picker._app.scenes.profile
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
		;   Models
		[last-fm-picker._app.models.router :as router-model]
		[last-fm-picker._app.models.user :as user-model]
))


(def log (.-log js/console))

(defn- -on_profile_enter [key atom old new]
	(if (= (:name new) "ProfileScene") (log "Welcome!!!")))


(add-watch router-model/current_scene "on_profile_scene_enter" -on_profile_enter)

(defn render []
	[view {:style {:width 150}}
	 [text "profile!"]])