(ns hgp.reflect.clojure.api.packages
  (:import (java.util Set)
           (org.reflections Reflections)
           (org.reflections.scanners Scanner Scanners SubTypesScanner)
           (org.reflections.util ConfigurationBuilder FilterBuilder)))

(defn reflect-config-builder [pkg-names]
  (let [config-builder (ConfigurationBuilder.)
        filter-builder (FilterBuilder.)]
    (.includePackage filter-builder pkg-names) ;; here to do write  loop
    (.forPackages config-builder pkg-names)
    (.setScanners config-builder Scanners/values)
    (.filterInputsBy config-builder filter-builder)
    ))

(defn make-cloj-seq
  ^{:doc "the java list of packages is converted to a clojure sequence "}
  [pkg-jlist]
  (let [the-seq (sequence ^Set pkg-jlist)]
    the-seq))

(defn list-packages
   "this function lists the content of a given packages (more than one) by namr"
  [pkg-name]
  (let [config-builder (reflect-config-builder pkg-name)
        reflections (Reflections. ^ConfigurationBuilder config-builder)
        ]
    ;;(make-cloj-seq pkg-jlist)
    ))

(defn list-package
  "This function lists a specific package"
  [pkg-name]
  (let [reflections (Reflections. ^java.lang.String pkg-name)
        pkgs (.get ^Reflections reflections
                   ^java.lang.Class (Class/forName "java.lang.Package"))]


    ))

.setScanners(Scanners.values())     // all standard scanners
.filterInputsBy(new FilterBuilder()
                    .includePackage("com.my.project")
                    .excludePackage("com.my.project.exclude")));