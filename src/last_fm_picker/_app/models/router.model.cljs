(ns last-fm-picker._app.models.router
	(:require
		[reagent.core :as r :refer [atom]]) )

(def log (.-log js/console))
(def group (.-log js/console))
(def groupEnd (.-log js/console))


(def current_scene (atom "HomeScene"))


(defn set_current_scene [scene]
	(reset! current_scene scene))


; ========
; Watchers
(defn- -on_current_scene_change [key atom old new]
	(groupEnd)
	(group old " -> " new))
(add-watch current_scene "current_scene_change" -on_current_scene_change)
