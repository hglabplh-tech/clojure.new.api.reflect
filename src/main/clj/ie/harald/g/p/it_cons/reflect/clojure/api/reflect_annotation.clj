(ns ie.harald.g.p.it-cons.reflect.clojure.api.reflect-annotation
  (:import (java.lang Class)
           (java.lang.reflect Constructor Field Method)
           (ie.harald.g.p.it_cons.reflect.clojure.api.utils AnnotationsUtil)))

(defn get-class-annots [^Class clazz]
  (AnnotationsUtil/getClassAnnots clazz))

(defn get-ctor-annots [^Constructor ctor]
  (AnnotationsUtil/getCtorAnnots ctor))


(defn get-ctor-param-annots [^Constructor ctor]
  (AnnotationsUtil/getCtorParamAnnots ctor))

(defn get-field-annots [^Field field]
  (AnnotationsUtil/getFieldAnnots field))

(defn get-method-annots [^Method meth]
  (AnnotationsUtil/getMethodAnnots meth))

(defn get-meth-param-annots [^Method meth]
  (AnnotationsUtil/getMethParamAnnots meth))

(defn get-annot-return-type [^Method meth]
  (AnnotationsUtil/getAnnotReturnType meth))

