package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class MethsCtorsUtil {

    public static @Nonnull String getCtorName (
            @Nonnull Constructor<?> ctor) {
        return ctor.getName();
    }

    public static @Nonnull String getMethodName (
            @Nonnull Method meth) {
        return meth.getName();
    }

    public static @Nonnull Integer getCtorParamCount (
            @Nonnull Constructor<?> ctor) {
        return ctor.getParameterCount();
    }

    public static @Nonnull IPersistentVector getCtorParamTypes(Constructor<?> ctor) {
        return getArrayAsLazyVector(ctor.getParameterTypes());
    }

    public static @Nonnull Integer getCtorModifiers(Constructor<?> ctor) {
        return ctor.getModifiers();
    }

    public static @Nonnull Class<?> getCtorDeclaringClass(Constructor<?> ctor){
        return ctor.getDeclaringClass();
    }

    public static @Nonnull  Integer getMethodParamCount (
            @Nonnull Method method) {
        return method.getParameterCount();
    }

    public static @Nonnull  IPersistentVector getMethodParamTypes (
            @Nonnull Method method) {
        return getArrayAsLazyVector(method.getParameterTypes());
    }

    public static @Nonnull Class<?> getMethodReturnType (@Nonnull
                                                         Method method) {
        return method.getReturnType();
    }

    public static  @Nonnull Integer getMethodModifiers (@Nonnull
                                                        Method method) {
        return method.getModifiers();
    }

    public static @Nonnull IPersistentVector getMethExceptionTypes(Method method) {
        return getArrayAsLazyVector(method.getExceptionTypes());
    }

    public static @Nonnull IPersistentVector getMethGenericExTypes(Method method) {
        return getArrayAsLazyVector(method.getGenericExceptionTypes());
    }

    public static @Nonnull IPersistentVector getMethGenericParmTypes(Method method){
        return getArrayAsLazyVector(method.getGenericParameterTypes());
    }

    public static @Nonnull Type getMethGenericReturnType(Method method){
        return method.getGenericReturnType();
    }

    public static @Nonnull Class<?> getMethDeclaringClass(Method method){
        return method.getDeclaringClass();
    }

    public static  Boolean isBridgeMethod(Method method){
        return method.isBridge();
    }

    public static  Boolean isDefaultMethod(Method method){
        return method.isDefault();
    }

    public static  Boolean isSyntheticMethod(Method method){
        return method.isSynthetic();
    }

    public static  Boolean isVarArgsMethod(Method method){
        return method.isVarArgs();
    }

    public static  Boolean isSyntheticCtor(Constructor<?> ctor){
        return ctor.isSynthetic();
    }

    public static  Boolean isVarArgsCtor(Constructor<?> ctor){
        return ctor.isVarArgs();
    }

    public static  Boolean isAnnotPresentCtor(Constructor<?> ctor,
                                              Class<? extends Annotation>
                                                      annotation){
        return ctor.isAnnotationPresent(annotation);
    }

    public static  boolean isAnnotPresentMethod(Method meth,
                                              Class<? extends Annotation>
                                                      annotation){
        return meth.isAnnotationPresent(annotation);
    }

    public static @Nonnull IPersistentVector
    getCtorExceptionTypes(Constructor<?> ctor) {
        return getArrayAsLazyVector(ctor.getExceptionTypes());
    }

    public static @Nonnull IPersistentVector
    getCtorGenericExTypes(Constructor<?> ctor) {
        return getArrayAsLazyVector(ctor.getGenericExceptionTypes());
    }

    public static @Nonnull IPersistentVector
    getDeclAnnotsMethod(Method meth) {
        return getArrayAsLazyVector(meth.getDeclaredAnnotations());
    }

    public static @Nonnull IPersistentVector
    getDeclAnnotsCtor(Constructor<?> ctor) {
        return getArrayAsLazyVector(ctor.getDeclaredAnnotations());
    }

    public static @Nonnull IPersistentVector
    getTypeParamsMethod(Method meth) {
        return getArrayAsLazyVector(meth.getTypeParameters());
    }

    //TypeVariable<? extends Constructor<?>>
    public static @Nonnull IPersistentVector
    getTypeParamsCtor(Constructor<?> ctor) {
        return getArrayAsLazyVector(ctor.getTypeParameters());
    }
}
