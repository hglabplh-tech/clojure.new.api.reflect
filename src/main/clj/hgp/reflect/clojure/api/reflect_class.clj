(ns hgp.reflect.clojure.api.reflect-class
  (:require [hgp.reflect.clojure.api.packages :as pkg]            )
  (:import (java.lang.annotation Annotation)
    (hgp.reflect.clojure.api.utils ClassUtil)))


(defn get-class [clazz-name & pkg-names]
  (let [the-clazz (ClassUtil. (first (first (map (fn [pkg-list]
                         (let [result (filter
                                        (fn [member]
                                          (.equals (.getCanonicalName member)
                                                   clazz-name))
                                        pkg-list)]
                           result))  (apply pkg/list-packages pkg-names)))))]
    the-clazz))

(defn get-all-ctors [^ClassUtil util]
  (.getAllConstructors util))

(defn get-public-methods [^ClassUtil util]
(.getPublicMethods util))

(defn get-all-fields [^ClassUtil class-util]
  (.getAllFields class-util))

(defn get-all-methods [^ClassUtil util]
  (.getAllMethods util))

(defn get-all-sub-classes [^ClassUtil util]
  (.getAllSubClasses util))

(defn get-class-name [^ClassUtil util]
  (.getClassName util))

(defn get-class-name [^ClassUtil util]
  (.getClassName util))

(defn get-constructors [^ClassUtil util]
  (.getConstructors util))

(defn get-annots-by-type [^ClassUtil util ^Class annotClass]
  (.getDeclAnnotsByType util annotClass))

(defn get-generic-interfaces [^ClassUtil util]
  (.getGenericInterfaces util))

(defn get-generic-super-class [^ClassUtil util]
  (.getGenericSuperClass util))

(defn get-public-annotations [^ClassUtil util]
  (.getPublicAnnotations util))

(defn get-public-fields [^ClassUtil util]
  (.getPublicFields util))

(defn get-public-methods [^ClassUtil util]
  (.getPublicMethods util))

(defn get-public-sub-classes [^ClassUtil util]
  (.getPublicSubClasses util))

(defn get-the-class [^ClassUtil util]
  (.getTheClass util))