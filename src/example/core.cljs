(ns example.core
  (:require [chia.view :as v]
            [chia.view.util :as vu]
            [chia.util.js-interop :as j]
            [clojure.pprint :refer [pprint]])
  (:require-macros [example.macros :as m]))
 
(v/defview example 
  {:key :source}
  [{:keys [source value]}]
  [:pre.pa2.bb.b--near-white.flex.monospace
   [:.pa3.br.b--near-white source]
   [:.pa3 value]])

(v/defview examples []
  (->> (m/expose-sources 
        
        (def o #js {})
        
        (j/assoc! o :a 0 :b 0)
        
        (j/update! o :a inc)
        
        ;; NOTE - should fix assoc-in to create empty intermediate objects if they don't exist
        (j/assoc! o :c #js {})
        
        (j/assoc-in! o [:c :x] 100)
        
        (j/update-in! o [:c :x] inc))
       (map example)))

(defn ^:export ^:dev/after-load init []
  (binding [v/*reload* true]
    (v/render-to-dom (examples) (vu/find-or-append-element "example"))))