(ns ie.harald.g.p.it-cons.reflect.clojure.api.reflect-enum-rec
  (:require [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-types :as types]))

(declare handle-record
         handle-enum)

(defn handle-enum-rec
  "Lookup if class is enum or record and call specific function
  for handling of that type"
  {:added "0.90"
   :static true}
  [clazz class-defs class-body]
  (let [attribs (types/get-class-attributes clazz)
        ]
    (if (contains? attribs :attr-record)
      (handle-record  clazz class-defs class-body)
      (if (contains? attribs :attr-enum)
        (handle-enum clazz class-defs class-body)
        [])
      )))

(defn- handle-enum
  "Handle / analyze enum type"
  {:added "0.90"
   :static true}
  [clazz class-defs class-body])

(defn- handle-record
  "Handle / analyze enum type"
  {:added "0.90"
   :static true}
  [clazz class-defs class-body])


