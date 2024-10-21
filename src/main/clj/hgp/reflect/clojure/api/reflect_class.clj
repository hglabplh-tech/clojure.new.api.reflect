(ns hgp.reflect.clojure.api.reflect-class
  (:require [hgp.reflect.clojure.api.packages :as pkg]            )
  (:import (hgp.reflect.clojure.api.utils ClassUtil)))


(defn get-class [clazz-name & pkg-names]
  (let [the-clazz (ClassUtil. (first (first (map (fn [pkg-list]
                         (let [result (filter
                                        (fn [member]
                                          (.equals (.getCanonicalName member)
                                                   clazz-name))
                                        pkg-list)]
                           result))  (apply pkg/list-packages pkg-names)))))]
    the-clazz))

(defn get-public-annots [^ClassUtil util]
  (.getPublicAnnotations util))

(defn get-public-methods [^ClassUtil util]
(.getPublicMethods util))

(defn get-all-ctors [^ClassUtil util]
  (.getConstructors util))

(defn get-all-methods [^ClassUtil util]
  (.getAllMethods util))

(defn get-all-fields [^ClassUtil class-util]
  (.getAllFields class-util))


