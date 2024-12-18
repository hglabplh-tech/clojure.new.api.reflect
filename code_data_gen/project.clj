(def refl_comp_gen_version "0.1.0-SNAPSHOT")
(defproject  refl_comp_gen refl_comp_gen_version
  :description ""
  :parent [org.clojars.hglabplh/cloj-new-reflection-api _ :relative-path "../pom.xml"]
  :thrift-source-path "build-support/thrift"
  :thrift-java-path "src/java"
  :thrift-opts "beans,hashcode")