(ns ie.harald.g.p.it-cons.reflect.clojure.compile.compiler
  (:require [ie.harald.g.p.it-cons.reflect.clojure.api.packages :as pkg]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-class :as rcl]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-meths-ctors :as meths]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-field :as fields]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-annotation :as annot]
            [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-types :as types])
  (:import (java.lang Class)
    (ie.harald.g.p.it_cons.reflect.clojure.api.utils ClassUtil)))


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
  (let [ctor-general (meths/get-all-ctor-and-type-modifiers ctor)
        ctor-attributes (types/get-ctor-attributes ctor)
        ctor-parm-types (meths/get-ctor-param-types ctor)
        ctor-annots (annot/get-ctor-annots ctor)
        ctor-p-annots (annot/get-ctor-param-annots ctor)]
  [:ctor [:general ctor-general] [:attr ctor-attributes]
    [:parm-types ctor-parm-types]  [:parm-annot ctor-p-annots]
   [:annots ctor-annots]]
  ))

(defn retrieve-method-info [method]
  (let [meth-general (meths/get-all-meth-and-type-modifiers method)
        meth-attributes (types/get-meth-attributes method)
        meth-parm-types (meths/get-meth-param-types method)
        meth-p-annots (annot/get-meth-param-annots method)
        meth-annots (annot/get-method-annots method)
        meth-ret-type (annot/get-annot-return-type method)]
    [:method [:general meth-general] [:attr meth-attributes]
     [:parm-types meth-parm-types]
     [:parm-annot meth-p-annots] [:annots meth-annots]
     [:return-type meth-ret-type]]
  ) )

(defn retrieve-field-info [field]
  (let [fld-general (fields/get-all-fields-and-type-modifiers field)
        fld-gen-type (fields/get-generic-type field)
        fld-annots (fields/get-all-annots field)]
    [:field [:general fld-general]  [:gen-type fld-gen-type]
     [:annots fld-annots]]
    ))

(defn define-direct-class-parameters [clazz-util]
  (let [class-name (rcl/get-class-name clazz-util)
        attributes (rcl/get-class-attributes (rcl/get-the-class clazz-util))

        interfaces (rcl/get-interfaces clazz-util)
        super-class (rcl/get-super-class clazz-util)
        gen-interfaces (rcl/get-generic-interfaces clazz-util)
        gen-super-class (rcl/get-generic-super-class clazz-util)]
    [:class class-name [:attributes attributes]
     [:super super-class]
     [:interface interfaces]
     [:gen-interface gen-interfaces]
     [:gen-super gen-super-class]]

    ))

(defn really-compile [^ClassUtil clazz-util]
  (let [class-def (define-direct-class-parameters clazz-util)
        the-class (rcl/get-the-class clazz-util)
        class-body (retrieve-class-body (rcl/get-all-ctors clazz-util)
                                        (rcl/get-all-fields clazz-util)
                                        (rcl/get-all-methods clazz-util)
                                        (rcl/get-all-sub-classes clazz-util))]
    [:compilation class-def class-body]))


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