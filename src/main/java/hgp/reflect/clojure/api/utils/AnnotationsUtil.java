package hgp.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;
import clojure.lang.LazilyPersistentVector;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static hgp.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class AnnotationsUtil {

    public static @Nonnull IPersistentVector getFieldAnnots(Field field) {
        return getArrayAsLazyVector(field.getAnnotations());
    }

    public static @Nonnull IPersistentVector getFieldAnnots(Constructor ctor) {
        return getArrayAsLazyVector(ctor.getAnnotations());
    }

    public static @Nonnull IPersistentVector getMethodAnnots(Method meth) {
        return getArrayAsLazyVector(meth.getAnnotations());
    }

    public static IPersistentVector getMethParamAnnots(Method meth) {
        Object[][] array =
                meth.getParameterAnnotations();
        IPersistentVector vect = LazilyPersistentVector.createOwning();

        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            IPersistentVector innerVect = null;
            Object[] objects = (Object[]) obj;
            for (Object objInner : objects) {
                innerVect = getArrayAsLazyVector((Object[]) objInner);
            }
            vect.assocN(i, innerVect);
        }

        return  vect;
    }


}
