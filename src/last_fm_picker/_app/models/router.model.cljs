(ns last-fm-picker._app.models.router
	(:require
		[reagent.core :as r :refer [atom]]) )

(def log (.-log js/console))
(def group (.-log js/console))
(def groupEnd (.-log js/console))


; Atoms
(def current_scene (atom { :name "HomeScene" :params {}}))


(defn set_current_scene [name params]
	(reset! current_scene { :name name :params (if (empty? params) {} params)}))


; ========
; Watchers
(defn- -on_current_scene_change [key atom old new]
	(groupEnd)
	(group (:name old) " -> " (:name new)))
(add-watch current_scene "current_scene_change" -on_current_scene_change)
