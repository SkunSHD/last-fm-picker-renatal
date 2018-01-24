(ns last-fm-picker._app.scenes.artist
	(:require
		[reagent.core :as r :refer [atom]]

		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
		;   Models
		[last-fm-picker._app.models.artists :as artists-model]
		[last-fm-picker._app.models.router :as router-model])
	)


(def log (.-log js/console))
(defn- -cljs [string] (js->clj (.parse js/JSON string) :keywordize-keys true))


(defn- page_artist_name []
	(:name (:params @router-model/current_scene)))


(defn- -on_did_mount []
	(artists-model/fetch_artist (page_artist_name))
	)


(defn- -on_render []
	[view
	 [text "ARTIST DETAILS"]
	 [text "Band name: " (page_artist_name)]
	 [text (str (get @artists-model/artists (page_artist_name)))]]
	)


(defn render []
	[(r/create-class {
						:component-did-mount -on_did_mount
					 	:reagent-render -on_render})])
