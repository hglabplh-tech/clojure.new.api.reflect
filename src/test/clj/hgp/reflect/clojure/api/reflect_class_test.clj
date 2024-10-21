(ns hgp.reflect.clojure.api.reflect-class-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [hgp.reflect.clojure.api.reflect-field :as rfield]
            [hgp.reflect.clojure.api.reflect-types :as rtypes]
            [hgp.reflect.clojure.api.reflect-class :as rcl]))

(deftest get-class.test
  (testing "getting a class of given packages"
    (let [spec-clazz-refl (rcl/get-class
              "hgp.reflect.clojure.api.app_exam.Application"
              "hgp.reflect.clojure.api.app_exam"
              "hgp.reflect.clojure.api.example")]
      (println (type spec-clazz-refl))
      (println (.getClassName spec-clazz-refl))
      (pprint spec-clazz-refl)
      (let [fields-vect
            (rcl/get-all-fields spec-clazz-refl)
            fld-vect-type
            (apply rfield/get-all-fields-and-type-modifiers fields-vect)]
        (pprint fields-vect)
        (pprint fld-vect-type)
        )

      )
    ))

(run-tests)
