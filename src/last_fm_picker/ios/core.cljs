(ns last-fm-picker.ios.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [last-fm-picker.events]
            [last-fm-picker.subs]
            [last-fm-picker.rn :refer [ReactNative app-registry text view image touchable-highlight]]
            [last-fm-picker.search-by-username-component :as search-by-username-component]
 ))


(def logo-img (js/require "./images/cljs.png"))

(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [image {:source logo-img
               :style  {:width 80 :height 80 :margin-bottom 30}}]
       [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(alert "HELLO!")}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]

       [search-by-username-component/render]
       ])))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "LastFmPicker" #(r/reactify-component app-root)))
