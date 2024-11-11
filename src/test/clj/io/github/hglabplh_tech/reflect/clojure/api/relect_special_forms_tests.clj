(ns io.github.hglabplh-tech.reflect.clojure.api.relect-special-forms-tests
  (:require [clojure.pprint :as pp]
            [clojure.test :refer :all]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as rcl]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms :as sform]))
(def enum-check-vect [:enum-spec
                      {"TOOLS"
                       {:ordinal-val    0,
                        :class-descr-val
                        "Lio/github/hglabplh_tech/reflect/clojure/api/example/annot_rec/Category;",
                        :enem-descr-val "EnumDesc[Category.TOOLS]"},
                       "APIS"
                       {:ordinal-val    1,
                        :class-descr-val
                        "Lio/github/hglabplh_tech/reflect/clojure/api/example/annot_rec/Category;",
                        :enem-descr-val "EnumDesc[Category.APIS]"},
                       "TESTS"
                       {:ordinal-val    2,
                        :class-descr-val
                        "Lio/github/hglabplh_tech/reflect/clojure/api/example/annot_rec/Category;",
                        :enem-descr-val "EnumDesc[Category.TESTS]"},
                       "PROJECT"
                       {:ordinal-val    3,
                        :class-descr-val
                        "Lio/github/hglabplh_tech/reflect/clojure/api/example/annot_rec/Category;",
                        :enem-descr-val "EnumDesc[Category.PROJECT]"},
                       "NONE"
                       {:ordinal-val    4,
                        :class-descr-val
                        "Lio/github/hglabplh_tech/reflect/clojure/api/example/annot_rec/Category;",
                        :enem-descr-val "EnumDesc[Category.NONE]"}}])
(deftest enum.handle.test
  (testing "Here the enum transform is tested"
    (let [class-util (rcl/get-class-util
                       "io.github.hglabplh_tech.reflect.clojure.api.app_exam.AppConfigFields")
          result (sform/handle-enum (rcl/get-the-class class-util))]
      (pp/pprint result)
      (is (= enum-check-vect result)))))

(run-tests)


