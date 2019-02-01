(ns example.macros
  (:require [clojure.pprint :refer [pprint]]))

(defmacro expose-sources [& forms]
  (mapv (fn [form]
          `{:source (with-out-str (pprint (quote ~form)))
            :value (with-out-str (pprint ~form))}) forms))