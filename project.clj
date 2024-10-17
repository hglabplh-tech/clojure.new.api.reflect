(defproject hgp.reflect.clojure.api/cloj-new-reflection-api "0.02-SNAPSHOT"
  :description "Test Framework extend for Clojure"
  :url "https://github.com/hglabplh-tech/clojure.new.api.reflect.git"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                                  [org.clojure/core.async "1.6.681"]
                                  [de.active-group/active-clojure "0.42.2"]
                                  [de.active-group/active-data "0.2.1"]
                                  [org.reflections/reflections "0.10.2"]
                 ]



  :javac-options ["-target" "22" "-source" "22" "-Xlint:-options"]
  :scm {:name "git"
        :url  "https://github.com/hglabplh-tech/clojure.new.api.reflect.git"}

  :source-paths ["src/main/clj"]
  :java-source-paths ["src/main/java"]                     ; Java source is stored separately.
  :test-paths ["src/test/clj" "src/test/java"]
  :resource-paths ["src/test/resources" "src/main/resources"]

  :aot :all

  :profiles {:java-tests-compile
             {:java-source-paths ["src/test/java"]}}
  :aliases {
            "java-tests" ["do" "compile," "with-profile" "java-tests-compile" "javac," "run" "-m" "myorg.myProject.java-test-code"]
            "all-tests" ["test," "java-tests"]}
  )