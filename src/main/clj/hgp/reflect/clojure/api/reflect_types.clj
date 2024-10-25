(ns hgp.reflect.clojure.api.reflect-types
  (:import (java.lang.reflect Modifier Constructor Method)
           (hgp.reflect.clojure.api.utils ClassUtil MethsCtorsUtil FieldsUtil)))


(defn is-annotation [^Class class-to-check]
  (ClassUtil/isClassAnnotation class-to-check))

(defn is-anonymous [^Class class-to-check]
  (ClassUtil/isClassAnonymous class-to-check))

(defn is-array [^Class class-to-check]
  (ClassUtil/isClassArray class-to-check))

(defn is-enum [^Class class-to-check]
  (ClassUtil/isClassEnum class-to-check))

(defn is-interface [^Class class-to-check]
  (ClassUtil/isClassInterface class-to-check))

(defn is-local [^Class class-to-check]
  (ClassUtil/isClassLocal class-to-check))

(defn is-member [^Class class-to-check]
  (ClassUtil/isClassMember class-to-check))

(defn is-primitive [^Class class-to-check]
  (ClassUtil/isClassPrimitive class-to-check))

(defn is-sealed [^Class class-to-check]
  (ClassUtil/isClassSealed class-to-check))

(defn is-synthetic [^Class class-to-check]
  (ClassUtil/isClassSynthetic class-to-check))

(defn is-synthetic-ctor [^Constructor ctor-to-check]
  (MethsCtorsUtil/isSyntheticCtor ctor-to-check))

(defn is-varargs-ctor [^Constructor ctor-to-check]
  (MethsCtorsUtil/isVarArgsCtor ctor-to-check))

(defn is-synthetic-meth [^Method meth-to-check]
(MethsCtorsUtil/isSyntheticMethod meth-to-check))

(defn is-varargs-meth [^Method meth-to-check]
  (MethsCtorsUtil/isVarArgsMethod meth-to-check))

(defn is-default-meth [^Method meth-to-check]
  (MethsCtorsUtil/isDefaultMethod meth-to-check))

(defn is-bridge-meth [^Method meth-to-check]
  (MethsCtorsUtil/isBridgeMethod meth-to-check))

(defn is-enum-constant [field]
  (FieldsUtil/isEnumConst field))

(defn is-synthetic-field [field]
  (FieldsUtil/isSynthetic field))


(defn- get-modifiers-readable [mod-int]
  (let [mod-map {:abstract (Modifier/isAbstract mod-int)
                  :final (Modifier/isFinal mod-int)
                  :interface (Modifier/isInterface mod-int)
                  :native (Modifier/isNative mod-int)
                  :transient (Modifier/isTransient mod-int)
                  :strict (Modifier/isStrict mod-int)
                  :synchronized (Modifier/isSynchronized mod-int)
                  :static (Modifier/isStatic mod-int)
                  :volatile (Modifier/isVolatile mod-int)
                  :public (Modifier/isPublic mod-int)
                  :private (Modifier/isPrivate mod-int)
                  :protected (Modifier/isProtected mod-int)}]
    mod-map))

(defn- get-class-attributes-readable [class-to-check]
  (let [class-attr-map {:attr-annotation (is-annotation class-to-check)
                 :attr-anonymous (is-anonymous class-to-check)
                 :attr-array (is-array class-to-check)
                 :attr-enum (is-enum class-to-check)
                 :attr-interface (is-interface class-to-check)
                 :attr-local (is-local class-to-check)
                 :attr-member (is-member class-to-check)
                 :attr-primitive (is-primitive class-to-check)
                 :attr-sealed (is-sealed class-to-check)
                 :attr-synthetic (is-synthetic class-to-check)}]
    class-attr-map))

(defn- get-ctor-attributes-readable [ctor-to-check]
  (let [ctor-attr-map {:attr-varargs (is-varargs-ctor ctor-to-check)
                        :attr-synthetic (is-synthetic-ctor ctor-to-check)
                        }]
    ctor-attr-map))

(defn- get-meth-attributes-readable [meth-to-check]
  (let [meth-attr-map {:attr-varargs (is-varargs-meth meth-to-check)
                       :attr-synthetic (is-synthetic-meth meth-to-check)
                       :attr-default (is-default-meth meth-to-check)
                       :attr-bridge (is-bridge-meth meth-to-check)
                       }]
    meth-attr-map))

(defn- get-field-attributes-readable [field]
  (let [ctor-attr-map {:attr-enum-const (is-enum-constant field)
                       :attr-synthetic (is-synthetic-field field)
                       }]
    ctor-attr-map))

(defn attributes-bool-filter [the-map-to-filter]
  (loop [modifier-keys (keys  the-map-to-filter)
         result []]
    (if (empty?  modifier-keys)
      result
      (let [act-key (first modifier-keys)
            value (get the-map-to-filter act-key)]
        (recur (rest modifier-keys) (if value
                                      (conj result act-key)
                                      result))
        ))
    )
  )

(defn attributes-bool-filter [the-map-to-filter]
  (loop [modifier-keys (keys  the-map-to-filter)
         result []]
    (if (empty?  modifier-keys)
      result
      (let [act-key (first modifier-keys)
            value (get the-map-to-filter act-key)]
        (recur (rest modifier-keys) (if value
                                      (conj result act-key)
                                      result))
        ))
    )
  )



(defn get-item-modifiers [mod-int]
  (get-modifiers-readable mod-int))

(defn get-class-attributes [class-to-check]
  (get-class-attributes-readable class-to-check))

(defn get-ctor-attributes [ctor-to-check]
  (get-ctor-attributes-readable ctor-to-check))

(defn get-meth-attributes [meth-to-check]
  (get-meth-attributes-readable meth-to-check))

(defn get-field-attributes [field]
    (get-field-attributes-readable field))

