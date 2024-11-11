(ns io.github.hglabplh-tech.reflect.clojure.compile.compiler-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [io.github.hglabplh-tech.reflect.clojure.compile.ast-table-defs :as tdefs]
            [io.github.hglabplh-tech.reflect.clojure.compile.compiler :as comp]
            [io.github.hglabplh-tech.reflect.clojure.compile.comp-test-data :as data]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-field :as rfield]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as rcl] ))

(deftest simple-class-compile
  (testing "This simple test is to smoke test / check if compile works"
    (let  [comp-result (comp/compile-class
      "io.github.hglabplh_tech.reflect.clojure.api.example.InterfaceImplBaseAbstr")]
      (pprint comp-result)
    )))

(deftest more-cmplx-class-compile
  (testing "This more complex test is to smoke test / check if compile works"
    (let  [comp-result (comp/compile-class
                         "io.github.hglabplh_tech.reflect.clojure.api.example.InterfaceImplBase"
                         )]
      (pprint comp-result)
      )))

(deftest enum-class-compile
  (testing "This is a test for enum classes / check if compile works"
    (let  [comp-result
           (comp/compile-class
             "io.github.hglabplh_tech.reflect.clojure.api.app_exam.AppConfigFields")

           compile-frag (second comp-result)
           class-frag (second compile-frag)
           enum-specs (get class-frag :enum-specs)]
      (pprint class-frag)
      (is (= class-frag (get (get (get data/enum-class-header-data :compilation)
                                  :class) :enum-specs)))
      )))

(deftest record-class-compile
  (testing "This is a test for record classes / check if compile works"
    (let  [comp-result
           (comp/compile-class
             "io.github.hglabplh_tech.reflect.clojure.api.example.annot_rec.MyTestRecord"
             )]
      (pprint comp-result)
      )))


(deftest lambda-class-compile
  (testing "This is a test for lambda classes / check if compile works"
    (let  [comp-result
           (comp/compile-class
             "io.github.hglabplh_tech.reflect.clojure.api.example.lambda.MyLambdas"
             )]
      (pprint comp-result)
      )))

(run-tests)
