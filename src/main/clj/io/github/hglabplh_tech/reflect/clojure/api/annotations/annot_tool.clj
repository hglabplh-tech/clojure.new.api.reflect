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

