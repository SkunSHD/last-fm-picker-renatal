(ns last-fm-picker.rn
	(:require
		[reagent.core :as r :refer [atom]]
))


(def ReactNative (js/require "react-native"))


(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def input (r/adapt-react-class (.-TextInput ReactNative)))
(def button (r/adapt-react-class (.-Button ReactNative)))
(def link (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(def scroll (r/adapt-react-class (.-ScrollView ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))