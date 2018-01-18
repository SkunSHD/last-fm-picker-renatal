(ns last-fm-picker.search-by-username-component
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]

		[cljs.core.async :refer [<!]]
		[cljs-http.client :as http]

		[last-fm-picker.search-by-username-model :as search-by-username-model]
	)
	(:require-macros [cljs.core.async.macros :refer [go]]))


(def log (.-log js/console))


(defn- on_change_text [search_text]
	(search-by-username-model/set_current_search search_text))


(defn render_search_input []
	[input {
		 :value @search-by-username-model/current_search
		 :onChangeText on_change_text
		 :autoCapitalize "none"
		 :style { :borderColor "gray" :padding 5 :borderWidth 1}}])


(defn render_search_results []
	[view {:style {:paddingTop 20}}
	 (if (nil? @search-by-username-model/current_search_artists)
		 [text "Searching..."]
		 (if (empty? @search-by-username-model/current_search_artists)
			 [text "No artists"]
			 [view
			  (for [artist @search-by-username-model/current_search_artists]
				  ^{:key (:name artist)} [view {:style {:borderColor "black" :borderWidth 1 :margin 5 :padding 5}}
				                          [text (:name artist)]
				                          (if (empty? (:#text (nth (:image artist) 0)))
					                          nil
					                          [image {:source {:uri (:#text (nth (:image artist) 0))}
					                                  :style  {:width 80 :height 80 :margin 5}}])
				                          ])]
			 )
		 )])


(defn render []
	[view {:style {:borderColor "gray" :borderWidth 1 :padding 10 :paddingBottom 20 :margin 20 :width 350}}
	 [text {:style {:font-size 15 :margin-bottom 10 :text-align "center"}} "[search albums by username: " @search-by-username-model/current_search "]"]
	 [render_search_input]
	 [render_search_results]])