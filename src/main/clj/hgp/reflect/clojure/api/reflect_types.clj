(ns hgp.reflect.clojure.api.reflect-types
  (:import (java.lang.reflect Modifier)))

(defn get-modifiers-readable [mod-int]
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

(defn filter-out-mods [mod-map]
  (loop [modifier-keys (keys  mod-map)
         result []]
    (if (empty?  modifier-keys)
      result
      (let [act-key (first modifier-keys)
            value (get mod-map act-key)]
        (recur (rest modifier-keys) (if value
                                      (conj result act-key)
                                      result))
        ))
    )
  )

(defn get-item-modifiers [mod-int]
  (filter-out-mods (get-modifiers-readable mod-int)))
