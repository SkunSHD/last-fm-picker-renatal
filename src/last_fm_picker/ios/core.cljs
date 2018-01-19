(ns last-fm-picker.ios.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [last-fm-picker.events]
            [last-fm-picker.subs]
            [last-fm-picker.rn :refer [ReactNative app-registry text scroll input view image touchable-highlight]]
            [cljs.core.async :refer [<!]]
            [cljs-http.client :as http]
;            Components
            [last-fm-picker._app.router :as router]
 ))


(def logo-img (js/require "./images/cljs.png"))
(def log (.-log js/console))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [scroll [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
               [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
               [image {:source logo-img
                       :style  {:width 80 :height 80 :margin-bottom 30}}]
               [router/render]]])))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "LastFmPicker" #(r/reactify-component app-root)))
