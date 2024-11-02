(ns ie.harald.g.p.it-cons.reflect.clojure.api.reflect-field
  (:require [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-types :as rtypes])
  (:import (ie.harald.g.p.it_cons.reflect.clojure.api.utils FieldsUtil)))

(defn get-field-name
  "Get the field name of a field definition
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getFieldName field))

(defn get-field-type
  "Get the field type of a field definition
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getFieldType field))

(defn get-field-modifier
  "Get the field modifier of a field definition
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getFieldModifier field))

(defn get-all-fields-and-type-modifiers
  "Get the fields modifiers and types and
  attributes of one or more  field definition(s)
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [& fields]
  (map (fn [field]
         {field [(get-field-type field) (rtypes/get-item-modifiers
                                          (get-field-modifier field))
                 (rtypes/get-field-attributes field)]}
         )
       fields))

(defn get-all-annots
  "Get all annotations of a field definition
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getAllAnnotations field))

(defn get-annot-type
  "Get annotation type of a field definitions annotation
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getAnnotType field))

(defn get-generic-type
  "Get the generic type of a field definition
  @param : the field we look for"
  {:added "0.90"
   :static true}
  [field]
  (FieldsUtil/getGenericType field))


