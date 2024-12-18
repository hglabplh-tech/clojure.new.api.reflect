(ns io.github.hglabplh-tech.reflect.clojure.general.cloj-java-represent
  (:import (io.github.hglabplh_tech.reflect.clojure.api.utils ClojFunctionalUtils)
           (java.lang.reflect AnnotatedType
                              GenericArrayType
                              ParameterizedType
                              Type
                              TypeVariable))
  )


(defn get-base-type-representation
  "The representation of the type class as a string"
  [the-type]
  (let [type-name (if (instance? (class Type) the-type)
                    (.getTypeName the-type)
                    "N/A")]
    type-name))


(defn get-annot-type-representation
  "The representation of the type AnnotationType(Java class) as Clojure Map"
  [the-type & annotation]
  (if (instance? AnnotatedType the-type)
    (let [the-annot-type (.getAnnotatedOwnerType the-type)
          the-annot-type-name (get-base-type-representation the-annot-type)
          the-type-annotations (ClojFunctionalUtils/getArrayAsLazyVector
                                 (.getAnnotations the-type))
          the-annot (if (not (empty? annotation))
                      (.getAnnotation the-type
                                      (class (first annotation)))
                      nil)]
      {:annot-type {:annot-type-name  the-annot-type-name
                    :type-annotations the-type-annotations
                    :annot            the-annot}}
      )
    {}))

(defn get-parametrized-trespresentation
  "The representation of the type class as a function"
  [the-type]
  (let [type-class (class the-type)]
    (if
      (instance? (class ParameterizedType) the-type)
      (let [type-name (get-base-type-representation the-type)
            type-arguments (ClojFunctionalUtils/getArrayAsLazyVector
                             (.getActualTypeArguments the-type))
            type-argument-names (map #(get-base-type-representation %)
                                     type-arguments)
            raw-type-name (get-base-type-representation
                            (.getRawType the-type))
            owner-type-name (get-base-type-representation
                              (.getOwnerType the-type))]
        {:parametrizied-type
         {:type-name       type-name
          :type-args       type-argument-names
          :raw-type-name   raw-type-name
          :owner-type-name owner-type-name}
         })
      {})))


(defn type-def-representation
  "The representation of the type class as function"
  [the-type]
  (cond (instance? TypeVariable the-type)
        {:type-var-type
         {:bounds       (map get-base-type-representation
                             (ClojFunctionalUtils/getArrayAsLazyVector
                               (.getBounds the-type)))
          :type-name    (.getName the-type)
          :annot-bounds (map get-annot-type-representation
                          (ClojFunctionalUtils/getArrayAsLazyVector
                          (.getAnnotatedBounds the-type)))}}
        (instance? GenericArrayType the-type)
        (let [type-name (get-base-type-representation the-type)
              comp-type-name (get-base-type-representation
                               (.getGenericComponentType the-type))]
          {:generic-type {:type-name      type-name
                          :comp-type-name comp-type-name}})
        (instance? ParameterizedType the-type)
        (get-parametrized-trespresentation the-type)
        (instance? AnnotatedType the-type)
        (get-annot-type-representation the-type)))



