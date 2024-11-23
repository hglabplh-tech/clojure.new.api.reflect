(ns io.github.hglabplh-tech.reflect.clojure.api.convert-java-cloj
  (:require [io.github.hglabplh-tech.reflect.clojure.api.reflect-annotation :as annot]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-class :as rcl]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-field :as fields]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-meths-ctors :as meths]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-special-forms :as er-refl]
            [io.github.hglabplh-tech.reflect.clojure.api.reflect-types :as types]))

(declare retrive-class-info)

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
    {:ctor
     {:obj-name            ctor-name
      :general             ctor-general
      :attr                ctor-attributes
      :parm-types          ctor-parm-types
      :parm-annot          ctor-p-annots
      :annots              ctor-annots
      :excption-types      ctor-ex-types
      :gen-exception-types ctor-generic-ex-types
      :declaring-class     ctor-declaring-class}}
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
    {:method
     {:obj-name            meth-name
      :general             meth-general
      :attr                meth-attributes
      :parm-types          meth-parm-types
      :gen-parm-types      meth-generic-parm-types
      :parm-annot          meth-p-annots :annots meth-annots
      :return-type         meth-ret-type
      :gen-return-type     meth-generic-ret-type
      :excption-types      meth-ex-types
      :gen-exception-types meth-generic-ex-types
      :declaring-class     meth-declaring-class}}
    ))

(defn retrieve-field-info [field]
  (let [fld-name (fields/get-field-name field)
        fld-general (fields/get-all-fields-and-type-modifiers field)
        fld-gen-type (fields/get-generic-type field)
        fld-annots (fields/get-all-annots field)
        ]
    {:field
     { :obj-name fld-name
      :general  fld-general
      :gen-type fld-gen-type
     :annots fld-annots}}
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
        enclosing-meth (rcl/get-enclosing-method clazz-util)
        enum-specs (er-refl/analyze-enum (rcl/get-the-class clazz-util))
        record-specs (er-refl/analyze-record (rcl/get-the-class clazz-util))]
    {:class
     {:obj-name class-name
      :attributes attributes
     :super super-class
     :interface interfaces
     :gen-interface gen-interfaces
     :gen-super gen-super-class
     :enclosing-class enclosing-class
     :enclosing-constructor enclosing-ctor
     :enclosing-method enclosing-meth
     :enum-specs enum-specs
      :record-specs record-specs}
      }
    ))
(defn retrieve-class-body [constructors class-fields class-methods inner-classes]
  (let [ctor-infos (map retrieve-ctor-info constructors)
        field-infos (map retrieve-field-info class-fields)
        method-infos (map retrieve-method-info class-methods)
        class-infos (map (fn [clazz]
                           (retrive-class-info (rcl/get-class-util-by-class clazz)))
                         inner-classes)]

    {:body {:ctor-infos ctor-infos
     :field-infos field-infos
     :method-infos method-infos
     :class-infos class-infos}}
    ))


(defn retrive-class-info [clazz-util]
  (let [class-def (retrieve-direct-class-parameters clazz-util)
        class-body (retrieve-class-body (rcl/get-all-ctors clazz-util)
                                        (rcl/get-all-fields clazz-util)
                                        (rcl/get-all-methods clazz-util)
                                        (rcl/get-all-sub-classes clazz-util))]
    {:class-info
     {:definition class-def
      :cl-body
       class-body}})
  )
