(ns io.github.hglabplh-tech.reflect.clojure.compile.ast-table-defs
  (:import (java.lang.reflect Type
                              TypeVariable
                              GenericArrayType
                              ParameterizedType
                              )))

(defn type-represent
  "The representation of the type class as function"
  [^Type the-type]
  (let [type-name (.getTypeName the-type)]
    [:name type-name]))

(def assoc-list
  [:class
   :super
   :interface
   :gen-super
   :gen-interface
   :attributes
   :sub-class
   :field
   :method])


