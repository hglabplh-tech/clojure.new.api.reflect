(ns io.github.hglabplh-tech.reflect.clojure.compile.compiler
  (:require [io.github.hglabplh-tech.reflect.clojure.api.packages :as pkg]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as rcl]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-meths-ctors :as meths]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-field :as fields]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-annotation :as annot]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-types :as types]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms :as er-refl])
  (:import (java.lang Class)
           (io.github.hglabplh_tech.reflect.clojure.api.utils ClassUtil)))


(declare retrieve-class-body
         define-direct-class-parameters
         really-compile
         search-compile-class
         compile-class
         retrieve-method-info
         retrieve-field-info
         retrieve-ctor-info
         )


(defn retrieve-class-body [constructors class-fields class-methods inner-classes]
  (let [ctor-infos (map  retrieve-ctor-info constructors)
        field-infos (map  retrieve-field-info class-fields)
        method-infos (map retrieve-method-info class-methods)
        class-infos (map (fn [clazz]
                           (really-compile (ClassUtil. clazz)))
                         inner-classes)]
    [:body [:ctor-infos ctor-infos]
     [:field-infos field-infos]
     [:method-infos method-infos]
     [:class-infos class-infos]]
    ))

(defn retrieve-ctor-info [ctor]
  (let [ctor-name (meths/get-ctor-name ctor)
        ctor-general (meths/get-all-ctor-and-type-modifiers ctor)
        ctor-attributes (types/get-ctor-attributes ctor)
        ctor-parm-types (meths/get-ctor-param-types ctor)
        ctor-annots (annot/get-ctor-annots ctor)
        ctor-p-annots (annot/get-ctor-param-annots ctor)
        ctor-ex-types (meths/get-ctor-exception-types ctor)
        ctor-generic-ex-types (meths/get-ctor-gen-exception-types ctor)
        ctor-declaring-class (meths/get-ctor-declaring-class ctor)
        ]
  [:ctor ctor-name
   [:general ctor-general]
   [:attr ctor-attributes]
    [:parm-types ctor-parm-types]
   [:parm-annot ctor-p-annots]
   [:annots ctor-annots]
   [:excption-types ctor-ex-types]
   [:gen-exception-types ctor-generic-ex-types]
   [:declaring-class ctor-declaring-class]]
  ))

(defn retrieve-method-info [method]
  (let [meth-name (meths/get-meth-name method)
        meth-general (meths/get-all-meth-and-type-modifiers method)
        meth-attributes (types/get-meth-attributes method)
        meth-parm-types (meths/get-meth-param-types method)
        meth-generic-parm-types (meths/get-generic-meth-param-types method)
        meth-p-annots (annot/get-meth-param-annots method)
        meth-annots (annot/get-method-annots method)
        meth-ret-type (meths/get-method-return-type method)
        meth-generic-ret-type (meths/get-generic-method-return-type method)
        meth-ex-types (meths/get-meth-exception-types method)
        meth-generic-ex-types (meths/get-meth-gen-exception-types method)
        meth-declaring-class (meths/get-meth-declaring-class method)
        ]
    [:method meth-name [:general meth-general] [:attr meth-attributes]
     [:parm-types meth-parm-types]
     [:gen-parm-types meth-generic-parm-types]
     [:parm-annot meth-p-annots] [:annots meth-annots]
     [:return-type meth-ret-type]
     [:gen-return-type meth-generic-ret-type]
    [:excption-types meth-ex-types]
    [:gen-exception-types meth-generic-ex-types]
     [:declaring-class meth-declaring-class]]
  ) )

(defn retrieve-field-info [field]
  (let [fld-name (fields/get-field-name field)
        fld-general (fields/get-all-fields-and-type-modifiers field)
        fld-gen-type (fields/get-generic-type field)
        fld-annots (fields/get-all-annots field)]
    [:field fld-name [:general fld-general]  [:gen-type fld-gen-type]
     [:annots fld-annots]]
    ))

(defn retrieve-direct-class-parameters [clazz-util]
  (let [class-name (rcl/get-class-name clazz-util)
        attributes (rcl/get-class-attributes (rcl/get-the-class clazz-util))

        interfaces (rcl/get-interfaces clazz-util)
        super-class (rcl/get-super-class clazz-util)
        gen-interfaces (rcl/get-generic-interfaces clazz-util)
        gen-super-class (rcl/get-generic-super-class clazz-util)
        enclosing-class (rcl/get-enclosing-class clazz-util)
        enclosing-ctor (rcl/get-enclosing-constructor clazz-util)
        enclosing-meth (rcl/get-enclosing-method clazz-util)]
    [:class class-name [:attributes attributes]
     [:super super-class]
     [:interface interfaces]
     [:gen-interface gen-interfaces]
     [:gen-super gen-super-class]
     [:enclosing-class enclosing-class]
     [:enclosing-constructor enclosing-ctor]
     [:enclosing-method enclosing-meth]]

    ))

(defn rec-or-enum? [clazz]
  (or (types/is-enum clazz) (types/is-record clazz)))

(defn really-compile [^ClassUtil clazz-util]
  (let [class-def (retrieve-direct-class-parameters clazz-util)
        the-class (rcl/get-the-class clazz-util)
        class-body (retrieve-class-body (rcl/get-all-ctors clazz-util)
                                        (rcl/get-all-fields clazz-util)
                                        (rcl/get-all-methods clazz-util)
                                        (rcl/get-all-sub-classes clazz-util))]
    ;;    (if (rec-or-enum? the-class)
    ;;[:compilation (er-refl/handle-enum-rec the-class class-def class-body)]
      [:compilation class-def class-body])
    )


(defn compile-class [^String canonical-nane]
  (let [clazz (Class/forName canonical-nane)
        clazz-util (ClassUtil. clazz)]
    (really-compile clazz-util)))


(defn search-compile-class [class-name & pkg-list]
  (let [compile-pkg-list (apply pkg/list-packages pkg-list)
        clazz-util (apply rcl/get-class class-name pkg-list)]
    (if (not (nil? clazz-util))
      (really-compile clazz-util)
      (throw (IllegalArgumentException. "class does not exist")))
    ))