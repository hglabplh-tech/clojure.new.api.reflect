(ns io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms
  (:require [io.github.hglabplh-tech.reflect.clojure.api.reflect-types :as types])
  (:import (io.github.hglabplh_tech.reflect.clojure.api.utils
             SpecialFormsUtil)
           (java.lang.reflect  AnnotatedType)           ))

(declare handle-record
         handle-enum)

(defn analyze-enum
  "Analyze enum type"
  {:added "1.1.0"
   :static true}
  [clazz]
  (let [enum-spec
        (SpecialFormsUtil/getEnumSpec clazz)]
    {:enum-spec enum-spec}
    ))

(defn analyze-record
  "Analyze record type"
  {:added "1.1.0"
   :static true}
  [clazz]
  (let [rec-spec
        (SpecialFormsUtil/getRecordSpec clazz)]
    rec-spec)
  )

(defn analyze-lambda
  "Analyze lambda expressions"
  {:added "1.1.0"
   :static true}
  [clazz]
  (let [lambda-spec
        (SpecialFormsUtil/getGeneralLambdaExprSpec clazz)]
    lambda-spec)
  )