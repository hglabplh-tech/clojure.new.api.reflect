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
                                          (get-field-modifier field))
                 (rtypes/get-field-attributes field)]}
         )
       fields))

(defn get-all-annots [field]
  (FieldsUtil/getAllAnnotations field))

(defn get-annot-type [field]
  (FieldsUtil/getAnnotType field))

(defn get-generic-type [field]
  (FieldsUtil/getGenericType field))


