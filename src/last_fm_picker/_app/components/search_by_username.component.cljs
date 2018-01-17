(ns last-fm-picker.search-by-username-component
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text view image touchable-highlight]]
))


(defn render []
	[view [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} "[search by username]"]])