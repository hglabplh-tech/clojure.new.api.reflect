(ns hgp.reflect.clojure.api.packages-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [hgp.reflect.clojure.api.packages :as pkgs]))



(deftest hgp.package.list.test
  (testing "This test checks if the list-pkgs works correctly"
    (pprint (pkgs/list-package "hgp.reflect.clojure.api.examples"))
    ))

(run-tests)