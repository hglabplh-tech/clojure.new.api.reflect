package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;

import javax.annotation.Nonnull;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class FieldsUtil {

    public static @Nonnull String getFieldName (
            @Nonnull Field field) {
        return field.getName();
    }
    public static @Nonnull Class<?> getFieldType (@Nonnull Field field) {
        return field.getType();
    }

    public static @Nonnull Integer getFieldModifier (@Nonnull Field field) {
        return field.getModifiers();
    }

    public static @Nonnull IPersistentVector getAllAnnotations(@Nonnull Field field) {
        return getArrayAsLazyVector(field.getDeclaredAnnotations());
    }

    public static @Nonnull Type getGenericType(@Nonnull Field field) {
        return field.getGenericType();
    }

    public static @Nonnull AnnotatedType getAnnotType(@Nonnull Field field) {
        return field.getAnnotatedType();
    }

    public static boolean isSynthetic(@Nonnull Field field) {
        return field.isSynthetic();
    }

    public static boolean isEnumConst(@Nonnull Field field) {
        return field.isEnumConstant();
    }




}