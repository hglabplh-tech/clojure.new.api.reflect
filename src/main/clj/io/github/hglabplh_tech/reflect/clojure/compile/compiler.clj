(ns io.github.hglabplh-tech.reflect.clojure.compile.compiler
  (:require [io.github.hglabplh-tech.reflect.clojure.api.packages :as pkg]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :refer :all]
            [io.github.hglabplh-tech.reflect.clojure.api.convert-java-cloj :as conv])
  (:import (java.lang Class)
           (io.github.hglabplh_tech.reflect.clojure.api.utils ClassUtil)))

(defn compile-class
  "The class to compile is given by it's canonical class name"
  [^String canonical-nane]
  (let [clazz (Class/forName canonical-nane)
        clazz-util (ClassUtil. clazz)]
    (conv/retrive-class-info clazz-util)))


(defn search-compile-class
  "Here the class for compiling the reflection result is searched in the packages path"
  [class-name & pkg-list]
  (let [compile-pkg-list (apply pkg/list-packages pkg-list)
        clazz-util (apply get-class class-name pkg-list)]
    (if (not (nil? clazz-util))
      (conv/retrive-class-info clazz-util)
      (throw (IllegalArgumentException. "class does not exist")))
    ))