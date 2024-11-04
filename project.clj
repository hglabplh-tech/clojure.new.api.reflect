(defproject ie.harald.g.p.it-cons.reflect.clojure.api/cloj-new-reflection-api "0.9-SNAPSHOT"
  :description "Clojure Layer for Java reflection with add-ons"
  :url "https://github.com/hglabplh-tech/clojure.new.api.reflect.git"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["jitpack" "https://jitpack.io"]]
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [lein-javadoc "0.3.0"]
                                  [org.clojure/core.async "1.6.681"]
                                  [de.active-group/active-clojure "0.42.2"]
                                  [de.active-group/active-data "0.2.1"]
                                  [com.github.technomancy/leiningen "2.11.2"]
                                  [org.reflections/reflections "0.10.2"]
                 ]
  :deploy-repositories [["releases" :clojars :sign-releases false]
                         ["snapshots" :clojars :sign-releases false]]

 :user {:signing {:gpg-key true
                    :ssh-key "~/.ssh/id_rsa"}}
  :plugins [[lein-codox "0.10.8"]  [lein-javadoc "0.3.0"]]

  :javac-options ["-target" "17" "-source" "17" "-Xlint:-options"]
  :scm {:name "git"
        :url  "https://github.com/hglabplh-tech/clojure.new.api.reflect.git"}

  :source-paths ["src/main/clj"]
  :java-source-paths ["src/main/java"]                     ; Java source is stored separately.
  :test-paths ["src/test/clj" "src/test/java"]
  :resource-paths ["src/test/resources" ]

  :aot :all

  :profiles {:java-tests-compile
             {:java-source-paths ["src/test/java"]}}
  :aliases {
            "java-tests" ["do" "compile," "with-profile"
                          "java-tests-compile" "javac," "codox.main/generate-docs"]
            "all-tests" ["test," "java-tests"]}

  :codox {:extra-deps {codox/codox {:mvn/version "0.10.8"}}
          :metadata   {:doc/format :markdown}
          :exec-fn codox.main/generate-docs
          :exec-args {:source-paths ["src/main/clj"]}}
  :javadoc-opts {
                 :package-names ["ie.harald.g.p.it-cons.reflect.clojure"]}
  )