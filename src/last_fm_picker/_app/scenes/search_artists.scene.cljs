(ns last-fm-picker._app.scenes.search_artists
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;		Components
		[last-fm-picker._app.components.search-by-username :as search-by-username-component]
		))

(defn render []
	[view
	 [search-by-username-component/render]])