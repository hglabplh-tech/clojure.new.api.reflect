(ns hgp.reflect.clojure.api.reflect-field-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [hgp.reflect.clojure.api.reflect-class :as rcl]
            [hgp.reflect.clojure.api.reflect-field :as rfield]))



(deftest reflect.fields.test
  (testing "Here the reflection of fields in a class is tested"
    (let [spec-clazz-refl (rcl/get-class
                            "hgp.reflect.clojure.api.app_exam.Application"
                            "hgp.reflect.clojure.api.app_exam"
                            "hgp.reflect.clojure.api.example")
          all-fields (rcl/get-all-fields spec-clazz-refl)]
       (pprint (map rfield/get-all-fields-and-type-modifiers all-fields))
       )))
(run-tests)