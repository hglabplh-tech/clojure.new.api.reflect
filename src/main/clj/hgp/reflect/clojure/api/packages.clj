(ns hgp.reflect.clojure.api.packages
  (:import (java. Set)
           (org.reflections Reflections)
           (org.reflections.scanners SubTypesScanner)))

(defn make-cloj-seq
  ^{:doc "the java list of packages is converted to a clojure sequence "}
  [pkg-jlist]
  (let [the-seq (sequence ^java.util.Set pkg-jlist)]
    the-seq))

(defn list-package
  ^{:doc "this function lists the content of a given package by namr"}
  [pkg-name]
  (let [sub-type-scan (SubTypesScanner. (false))
        reflections (Reflections. pkg-name sub-type-scan)
        pkg-jlist (.getSubTypesOf reflections (.getClass java.lang.Object))]
    (make-cloj-seq pkg-jlist)
    ))

