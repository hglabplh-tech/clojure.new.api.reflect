package hgp.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static hgp.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class MethsCtorsUtil {

    public static @Nonnull Integer getCtorParamCount (
            @Nonnull Constructor ctor) {
        return ctor.getParameterCount();
    }

    public static @Nonnull IPersistentVector getCtorParamTypes(Constructor ctor) {
        return getArrayAsLazyVector(ctor.getParameterTypes());
    }

    public static @Nonnull Integer getCtorModifiers(Constructor ctor) {
        return ctor.getModifiers();
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
}
