(ns io.github.hglabplh-tech.reflect.clojure.api.relect-special-forms-tests
  (:require [clojure.test :refer :all]
            [clojure.pprint :as pp]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as rcl]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms :as sform]))

(deftest enum.handle.test
  (testing "Here the enum transform is tested"
    (let [class-util (rcl/get-class-util
                       "io.github.hglabplh_tech.reflect.clojure.api.app_exam.AppConfigFields")
          result (sform/handle-enum (rcl/get-the-class class-util))]
            (pp/pprint result)
          )))

(run-tests)


