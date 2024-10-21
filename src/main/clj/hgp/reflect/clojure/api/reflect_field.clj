(ns hgp.reflect.clojure.api.reflect-field
  (:require [hgp.reflect.clojure.api.reflect-types :as rtypes])
  (:import (hgp.reflect.clojure.api.utils FieldsUtil)))


(defn get-field-type [field]
  (FieldsUtil/getFieldType field))

(defn get-field-modifier [field]
  (FieldsUtil/getFieldModifier field))

(defn get-all-fields-and-type-modifiers [& fields]
  (map (fn [field]
         {field [(get-field-type field) (rtypes/get-item-modifiers
                                          (get-field-modifier field))]}
         )
       fields))
