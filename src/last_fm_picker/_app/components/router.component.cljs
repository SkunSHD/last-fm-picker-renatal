(ns last-fm-picker._app.components.router
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;		Models
		[last-fm-picker._app.models.router :as router-model]
;		Scenes
		[last-fm-picker._app.scenes.home :as home_scene]
		[last-fm-picker._app.scenes.search_artists :as search_artists_scene]
))


(def log (.-log js/console))


(defn render_scene []
	(case @router-model/current_scene
		"HomeScene" [home_scene/render]
		"HomeScene1" [search_artists_scene/render]))


(defn render []
	[render_scene])

