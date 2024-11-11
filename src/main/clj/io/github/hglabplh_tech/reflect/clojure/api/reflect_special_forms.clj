(ns io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms
  (:require [io.github.hglabplh-tech.reflect.clojure.api.reflect-types :as types])
  (:import (io.github.hglabplh_tech.reflect.clojure.api.utils SpecialFormsUtil)
           (java.lang.reflect  AnnotatedType)           ))

(declare handle-record
         handle-enum)

(defn handle-enum
  "Handle / analyze enum type"
  {:added "1.1.0"
   :static true}
  [clazz]
  (let [enum-spec (SpecialFormsUtil/getEnumSpec clazz)]
    {:enum-spec enum-spec}
    ))

(defn handle-record
  "Handle / analyze enum type"
  {:added "1.1.0"
   :static true}
  [clazz]
  )


