(ns io.github.hglabplh-tech.reflect.clojure.api.annotations.annot-tool
  (:import (io.github.hglabplh_tech.reflect.clojure.api.annotations  AnnotationTool)))

(defn get-annotation-info
  "gets the meta info of a given annotation "
  {
   :added "1.1.0"
   :static true
   }
  [annot]
  (let [ tool  (AnnotationTool. annot)
        annot-attrs (.getAnnotationAttributes ^AnnotationTool tool)]
   )
  )

(defn get-annotation-data-vals
  "gets the values defined by an annotation "
  {
   :added "1.1.0"
   :static true
   }
  [annot]
  (let [annot-data  (AnnotationTool/retrieveAnnotationValues annot)]
    annot-data
    ))

(defn get-annotation-data-from-methods
  "gets the values defined by an annotation "
  {
   :added "1.1.0"
   :static true
   }
  [^java.lang.annotation.Annotation annot]
  (let [annot-data  (AnnotationTool/retrieveAnnotationValues annot)]
    ;;(get annot-data :methods-values)
    annot-data
    ))

