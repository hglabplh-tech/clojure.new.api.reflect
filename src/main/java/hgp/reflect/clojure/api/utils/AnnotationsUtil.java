package hgp.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;
import clojure.lang.LazilyPersistentVector;
import clojure.lang.RT;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static hgp.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class AnnotationsUtil {

    public static @Nonnull IPersistentVector getClassAnnots(Class<?> clazz) {
        return getArrayAsLazyVector(clazz.getAnnotations());
    }
    public static @Nonnull IPersistentVector getFieldAnnots(Field field) {
        return getArrayAsLazyVector(field.getAnnotations());
    }

    public static @Nonnull IPersistentVector getMethodAnnots(Method meth) {
        return getArrayAsLazyVector(meth.getAnnotations());
    }

    public static @Nonnull  AnnotatedType getAnnotReturnType (Method meth) {
        return meth.getAnnotatedReturnType();

    }

    public static IPersistentVector getMethParamAnnots(Method meth) {
        Object[][] array =
                meth.getParameterAnnotations();
        return getParamAnnotsInternal(array);
    }

    public static @Nonnull IPersistentVector getCtorAnnots(Constructor<?> ctor){
        return getArrayAsLazyVector(ctor.getAnnotations());
    }



    public static IPersistentVector getCtorParamAnnots(Constructor<?> ctor) {
        Object[][] array =
                ctor.getParameterAnnotations();
        return getParamAnnotsInternal(array);
    }

    private static @Nonnull IPersistentVector getParamAnnotsInternal ( Object[][] array ) {
        IPersistentVector vect = LazilyPersistentVector.createOwning();

        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            IPersistentVector innerVect = LazilyPersistentVector.createOwning();
            Annotation[] objects = (Annotation[]) obj;
            for (Annotation objInner : objects) {
                RT.conj(innerVect, objInner);
            }
            RT.conj(vect, innerVect);
        }

        return  vect;
    }


}
