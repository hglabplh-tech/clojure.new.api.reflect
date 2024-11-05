package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.ObjType;
import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.retrieveKeywordForJavaID;

public class DataTypeTransformer {

    public static @Nonnull IPersistentMap
    transformTypeValuesFromFields(@Nonnull Field[] fields, Object instance) {
        IPersistentMap nameValueMap = PersistentArrayMap.create
                (PersistentArrayMap.EMPTY);
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(Boolean.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getBoolean(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Double.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getDouble(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Float.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getFloat(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getInt(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Short.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getShort(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Long.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getLong(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (field.getType().isAssignableFrom(Byte.TYPE)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getByte(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (field.getType().isAssignableFrom(char.class)) {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getChar(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    nameValueMap.assocEx(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.get(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nameValueMap;
    }
}
