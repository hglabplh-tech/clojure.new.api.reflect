(ns ie.harald.g.p.it-cons.reflect.clojure.api.packages
  (:import (java.util Set)
           (java.lang Package)
           (org.reflections Reflections)
           (org.reflections.scanners Scanners)
           (org.reflections.util ConfigurationBuilder FilterBuilder)
           (ie.harald.g.p.it_cons.reflect.clojure.api.utils GetPackagesAndClasses)))


;; rewrite this using the classpath namespace and
;; build the new logic in class-path
(defn list-packages
  "this function lists the content of a given package by name"
  [& pkg-names]
  (let [pkg-jlist (map GetPackagesAndClasses/findAllClassesUsingClassLoader pkg-names)]
    pkg-jlist
    ))

