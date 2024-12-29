(defproject org.clojars.hglabplh/cloj-code-data-gen "1.1.0-SNAPSHOT"
  :description "Clojure Layer for Java reflection with add-ons"
  :url "https://github.com/hglabplh-tech/clojure.new.api.reflect.git"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :distribution :repo
  :scm {:name "git"
        :url  "https://github.com/hglabplh-tech/clojure.new.api.reflect"}
  :pom-addition ([:developers
                  [:developer
                   [:id "hglabplh-tech"]
                   [:name "Harald Glab-Plhak"]
                   [:url "https://hglabplh-tech.github.io"]
                   [:roles
                    [:role "developer"]
                    [:role "maintainer"]]]])
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


  :user {:signing {:gpg-key true
                   :ssh-key "~/.ssh/id_rsa"}}
  :plugins [[lein-codox "0.10.8"]
            [lein-cljsbuild "1.0.1"]
            [lein-ubersource "0.1.1"]
            [lein-javadoc "0.3.0"]]

  :javac-options ["-target" "17" "-source" "17" "-Xlint:-options"]


  :source-paths ["src/main/clj"]
  :java-source-paths ["src/main/java"]                      ; Java source is stored separately.
  :test-paths ["src/test/clj" "src/test/java"]
  :resource-paths ["src/test/resources"]

  :aot :all

  :profiles {:java-tests-compile
             {:java-source-paths ["src/test/java"]}
             }
  :aliases {"java-tests" ["do" "compile," "with-profile"
                          "java-tests-compile" "javac," "codox.main/generate-docs"]
            "all-tests"  ["test," "java-tests"]}

  :uberjar {:prep-tasks ["clean" "javac" "compile"]
            :aot        :all}
  :classifiers [["sources" {:source-paths      ^:replace ["src/main/clj"]
                            :java-source-paths ^:replace ["src/main/java"]
                            :resource-paths    ^:replace ["javadoc"]}]
                ["javadoc" {:source-paths      ^:replace []
                            :java-source-paths ^:replace []
                            :resource-paths    ^:replace ["javadoc"]}]]

  :codox {:extra-deps {codox/codox                         {:mvn/version "0.10.7" :exclusions [org.ow2.asm/asm-all]}
                       codox-theme-rdash/codox-theme-rdash {:mvn/version "0.1.2"}
                       hiccup/hiccup                       {:mvn/version "1.0.5"}
                       enlive/enlive                       {:mvn/version "1.1.6"}
                       org.pegdown/pegdown                 {:mvn/version "1.6.0"}
                       org.ow2.asm/asm-all                 {:mvn/version "5.2"}}
          :jvm-opts   ["--add-opens" "java.base/java.lang=ALL-UNNAMED"]
          :exec-fn    codox.main/generate-docs
          :exec-args  {:language    :clojure
                       :metadata    {:doc/format :markdown}
                       :themes      [:rdash]
                       :output-path "docs"}}
  :javadoc-opts {
                 :package-names ["io.github.hglabplh-tech.reflect"]}


  :deploy-repositories [["clojars" {:url "https://repo.clojars.org/"
                                    :username :env/clojars_username
                                    :password :env/clojars_password}]])