(ns hgp.reflect.clojure.api.reflect-meths-ctors
  (:require [hgp.reflect.clojure.api.reflect-types :as rtypes])
  (:import (hgp.reflect.clojure.api.utils MethsCtorsUtil)))

(defn get-ctor-param-count [ctor]
  (MethsCtorsUtil/getCtorParamCount ctor))

(defn get-ctor-param-types [ctor]
  (MethsCtorsUtil/getCtorParamTypes ctor))

(defn get-ctor-modifier [ctor]
  (MethsCtorsUtil/getCtorModifiers ctor))

(defn get-all-ctor-and-type-modifiers [& ctors]
  (map (fn [ctor]
         {ctor [(get-ctor-param-types ctor) (rtypes/get-item-modifiers
                                          (get-ctor-modifier ctor))]}
         )
       ctors))


(defn get-meth-param-count [meth]
  (MethsCtorsUtil/getMethodParamCount meth))

(defn get-meth-param-types [meth]
  (MethsCtorsUtil/getMethodParamTypes meth))

(defn get-meth-param-types [meth]
  (MethsCtorsUtil/getMethodParamTypes meth))


(defn get-method-return-type [meth]
  (MethsCtorsUtil/getMethodReturnType meth))

(defn get-method-modifier [meth]
  (MethsCtorsUtil/getMethodModifiers meth))

(defn get-all-meth-and-type-modifiers [& meths]
  (map (fn [meth]
         {meth [(get-meth-param-types meth) (rtypes/get-item-modifiers
                                              (get-method-modifier meth))]}
         )
       meths))