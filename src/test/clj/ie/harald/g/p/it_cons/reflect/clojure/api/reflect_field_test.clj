(ns ie.harald.g.p.it-cons.reflect.clojure.api.reflect-field-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-class :as rcl]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-field :as rfield]))



(deftest reflect.fields.test
  (testing "Here the reflection of fields in a class is tested"
    (let [spec-clazz-refl (rcl/get-class-simple
                            "ie.harald.g.p.it_cons.reflect.clojure.api.app_exam.Application")
          all-fields (rcl/get-all-fields spec-clazz-refl)]
       (pprint (map rfield/get-all-fields-and-type-modifiers all-fields))
       )))
(run-tests)