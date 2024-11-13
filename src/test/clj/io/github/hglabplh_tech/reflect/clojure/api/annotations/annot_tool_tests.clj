(ns io.github.hglabplh-tech.reflect.clojure.api.annotations.annot-tool-tests
  (:require [clojure.test :refer :all]
            [clojure.pprint :as pp]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as cls]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-annotation :as refla]
            [io.github.hglabplh-tech.reflect.clojure.api.annotations.annot-tool :as tool])
  (:import (java.lang Deprecated)))

(def smoke-result {:annotation-type "java.lang.Deprecated",
                   :fields-values {},
                   :methods-values
                   {:meth-since "v1.78", :meth-for-removal true}})



(deftest smoke.test.get-vals
  (testing "the retrieve functionality for annotation values"
    (let [cls-util (cls/get-class-util
                  "io.github.hglabplh_tech.reflect.clojure.api.app_exam.Application")
          ctors (cls/get-public-ctors cls-util)
          annotations (refla/get-ctor-annots (first ctors))
          annot (get annotations 0)
          annot-as-cloj  (tool/get-annotation-data-from-methods annot)]
    (pp/pprint annot-as-cloj)

    (is (= annot-as-cloj smoke-result))
    )
    ))

(deftest expanded.test.get-vals
  (testing "the expanded retrieve functionality for annotation values"
    (let [cls-util (cls/get-class-util
                     "io.github.hglabplh_tech.reflect.clojure.api.example.annot_rec.AnnotTestClass")
          methods (cls/get-all-methods cls-util)
          annotations (map refla/get-method-annots methods)
          annot-as-cloj  (map (fn [annots]

                                  (map (fn [annot]
                                         (if (nil? annot)
                                           'nil
                                           (tool/get-annotation-data-from-methods annot)
                                           ))
                                       annots)) annotations)
          ]
      (pp/pprint annot-as-cloj)
      ;;(is (= annot-as-cloj expanded-res))
      )
    ))

(run-tests)