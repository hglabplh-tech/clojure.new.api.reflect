(ns ie.harald.g.p.it-cons.reflect.clojure.compile.compiler-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [ie.harald.g.p.it-cons.reflect.clojure.compile.ast-table-defs :as tdefs]
            [ie.harald.g.p.it-cons.reflect.clojure.compile.compiler :as comp]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-field :as rfield]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-class :as rcl]))

(deftest simple-class-compile
  (testing "This simple test is to smoke test / check if compile works"
    (let  [comp-result (comp/search-compile-class
      "ie.harald.g.p.it_cons.reflect.clojure.api.example.InterfaceImplBaseAbstr"
      "ie.harald.g.p.it_cons.reflect.clojure.api.example")]
      (pprint comp-result)
    )))

(run-tests)
