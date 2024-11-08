package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getBoolean(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Double.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getDouble(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Float.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getFloat(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getInt(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Short.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getShort(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else if (field.getType().isAssignableFrom(Long.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getLong(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (field.getType().isAssignableFrom(Byte.TYPE)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getByte(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (field.getType().isAssignableFrom(char.class)) {
                try {
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.getChar(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else {
                try {
                    Object value = field.get(instance);
                    if(value.getClass().isEnum()) {
                        value = SpecialFormsUtil.getEnumSpec((Enum)value);
                    }
                    nameValueMap.assoc(retrieveKeywordForJavaID(field.getName(),
                                    ObjType.FIELD),
                            field.get(instance));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nameValueMap;
    }

    public static @Nonnull IPersistentMap
    transformTypeValuesFromMethods(@Nonnull Method[] methods, Object instance) {
        IPersistentMap nameValueMap = PersistentArrayMap.create
                (PersistentArrayMap.EMPTY);
        for (Method method : methods) {
            Object result = null;
            if (method.getParameterCount() == 0) {
                try {
                    result = method.invoke(instance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                if (result == null) {
                    throw new IllegalStateException("value not there");
                }

                if(result.getClass().isEnum()) {
                    result = SpecialFormsUtil.getEnumSpec((Enum)result);
                }
                nameValueMap = nameValueMap.assoc(
                        retrieveKeywordForJavaID(method.getName(), ObjType.METHOD),
                        result);

            }

        }
        return nameValueMap;
    }
}

