(ns ie.harald.g.p.it-cons.reflect.clojure.api.reflect-meths-ctors
  (:require [ie.harald.g.p.it-cons.reflect.clojure.api.reflect-types :as rtypes])
  (:import (ie.harald.g.p.it_cons.reflect.clojure.api.utils MethsCtorsUtil)))

(defn get-ctor-param-count
  "Get the constructors parameter count
  @param : the ctor we look for"
  {:added "0.90"
   :static true}
  [ctor]
  (MethsCtorsUtil/getCtorParamCount ctor))

(defn get-ctor-param-types
  "Get the constructors parameter types
  @param : the ctor we look for"
  {:added "0.90"
   :static true}
  [ctor]
  (MethsCtorsUtil/getCtorParamTypes ctor))

"Get the constructors modifiers
  @param : the ctor we look for"
{:added "0.90"
 :static true}
(defn get-ctor-modifier [ctor]
  (MethsCtorsUtil/getCtorModifiers ctor))
4
(defn get-all-ctor-and-type-modifiers
  "Get all the constructors modifiers
  types attributes these values are translated partly in keywods
  especially for modifiers and attributes
  @param : the ctors list"
  {:added "0.90"
   :static true}
  [& ctors]
  (map (fn [ctor]
         {ctor [(get-ctor-param-types ctor) (rtypes/get-item-modifiers
                                          (get-ctor-modifier ctor))
                (rtypes/get-ctor-attributes ctor)]}
         )
       ctors))

(defn get-meth-param-count
  "Get the methods parameter count
   @param : the method we look for"
  {:added "0.90"
   :static true}
  [meth]
  (MethsCtorsUtil/getMethodParamCount meth))

(defn get-meth-param-types
  "Get the methods parameter types
 @param : the method we look for"
  {:added "0.90"
   :static true}
  [meth]
  (MethsCtorsUtil/getMethodParamTypes meth))

(defn get-method-return-type
  "Get the methods return type
@param : the method we look for"
  {:added "0.90"
   :static true}
  [meth]
  (MethsCtorsUtil/getMethodReturnType meth))

(defn get-method-modifier
  "Get the methods modifier
@param : the method we look for"
  {:added "0.90"
   :static true}
  [meth]
  (MethsCtorsUtil/getMethodModifiers meth))

(defn get-all-meth-and-type-modifiers
  "Get all the methods modifiers
 types attributes these values are translated partly in keywods
 especially for modifiers and attributes
 @param : the list of methods"
  [& meths]
  (map (fn [meth]
         {meth [(get-meth-param-types meth) (rtypes/get-item-modifiers
                                              (get-method-modifier meth))
                (rtypes/get-meth-attributes meth)]}
         )
       meths))