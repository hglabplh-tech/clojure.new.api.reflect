(ns io.github.hglabplh-tech.reflect.clojure.api.annotations.annot-tool-tests
  (:require [clojure.test :refer :all]
            [clojure.pprint :as pp]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as cls]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-annotation :as refla]
            [io.github.hglabplh-tech.reflect.clojure.api.annotations.annot-tool :as tool])
  (:import (java.lang Deprecated)))



(deftest smoke.test.get-vals
  (testing "the retrieve functionality for annotation values"
    (let [cls-util (cls/get-class-util
                  "io.github.hglabplh_tech.reflect.clojure.api.app_exam.Application")
          ctors (cls/get-public-ctors cls-util)
          annotations (refla/get-ctor-annots (first ctors))
          annot (get annotations 0)]
      ;; FIXME !!!!!!!!!!
      (println annotations)
      (println annot)
      (println "Class type :" (.getClass annot))
    (pp/pprint (tool/get-annotation-data-vals annot))
    )
    ))

(run-tests)