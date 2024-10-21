(ns hgp.reflect.clojure.api.packages
  (:import (java.util Set)
           (java.lang Package)
           (org.reflections Reflections)
           (org.reflections.scanners Scanners)
           (org.reflections.util ConfigurationBuilder FilterBuilder)
           (hgp.reflect.clojure.api.utils GetPackagesAndClasses)))



(defn list-packages
  ^{:doc "this function lists the content of a given package by namr"}
  [& pkg-names]
  (let [pkg-jlist (map GetPackagesAndClasses/findAllClassesUsingClassLoader pkg-names)]
    pkg-jlist
    ))

