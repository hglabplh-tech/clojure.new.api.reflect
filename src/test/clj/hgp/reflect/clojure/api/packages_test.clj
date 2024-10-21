(ns hgp.reflect.clojure.api.packages-test
  (:require [clojure.pprint :refer :all]
            [clojure.test :refer :all]
            [hgp.reflect.clojure.api.packages :as pkgs]))



(deftest hgp.package.list.test
  (testing "This test checks if the list-pkgs works correctly"
    (let [result (pkgs/list-packages
                   "hgp.reflect.clojure.api.example"
                   "hgp.reflect.clojure.api.app_exam")]
      (pprint (map type (first result) ))
      (pprint result)
    )))

(run-tests)