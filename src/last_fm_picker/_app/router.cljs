(ns last-fm-picker._app.router
	(:require
		[reagent.core :as r :refer [atom]]
		[last-fm-picker.rn :refer [ReactNative app-registry text button link input view image touchable-highlight]]
;		Models
		[last-fm-picker._app.models.router :as router_model]
		[last-fm-picker._app.models.user :as user_model]
;		Scenes
		[last-fm-picker._app.scenes.home :as home_scene]
		[last-fm-picker._app.scenes.profile :as profile_scene]
		[last-fm-picker._app.scenes.search_artists :as search_artists_scene]
		[last-fm-picker._app.scenes.artist :as artist_scene]
))


; ==================
; Private
(defn render_scene []
	(case (:name @router_model/current_scene)
		"HomeScene" [home_scene/render]
		"ArtistScene" [artist_scene/render]
		"ProfileScene" [profile_scene/render]
		"SearchArtistsScene" [search_artists_scene/render]
))


(defn render []
	[view
	 (if (= @user_model/isAuthorized true) [text "Logged in as: " @user_model/username] nil)
	 [button {:title "Go home scene" :on-press #(router_model/set_current_scene "HomeScene" {})}]
	 [button {:title "Go search artists scene" :on-press #(router_model/set_current_scene "SearchArtistsScene" {})}]
	 [render_scene]]
	)

