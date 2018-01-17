(ns last-fm-picker.search-by-username-component
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text view image touchable-highlight]]

		[cljs.core.async :refer [<!]]
		[cljs-http.client :as http]
	)
	(:require-macros [cljs.core.async.macros :refer [go]]))

(def log (.-log js/console))


(defn test_ajax_call []
	(go (let [response (<! (http/get "https://last-fm-server.herokuapp.com/user/artists/andiwillfly"))]
		    (log  (:body response))
		    )))


(defn render []
	(test_ajax_call)
	[view [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} "[search by username]"]])