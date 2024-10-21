package hgp.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentVector;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static hgp.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

public class ClassUtil {

    private final Class theClass;

    public ClassUtil (@Nonnull Class theClass) {
        this.theClass = theClass;
    }

    public String getClassName() {
        return this.theClass.getCanonicalName();
    }

    public Class<?> getTheClass() {
        return this.theClass;
    }


    // here have to think about the name ;; annot are a special case
    public @Nonnull IPersistentVector getPublicAnnotations() {
        Annotation[] annots = theClass.getAnnotations();
        return getArrayAsLazyVector(annots);
    }

    public @Nonnull IPersistentVector getPublicMethods() {
        Method[] methods = theClass.getMethods();
        return getArrayAsLazyVector(methods);
    }

    public @Nonnull IPersistentVector getConstructors() {
        Constructor[] constrArray =
                this.theClass.getConstructors();
        return getArrayAsLazyVector(constrArray);
    }

    public @Nonnull IPersistentVector getAllMethods() {
        Method[] methods = theClass.getDeclaredMethods();
        return getArrayAsLazyVector(methods);
    }

    public @Nonnull IPersistentVector getPublicFields() {
        Field[] fields = theClass.getFields();
        return getArrayAsLazyVector(fields);
    }

    public @Nonnull IPersistentVector getAllFields() {
        Field[] fields = theClass.getDeclaredFields();
        return getArrayAsLazyVector(fields);
    }



    public static @Nonnull Class<?> getFieldType (@Nonnull Field field) {
        return field.getType();
    }

    public static @Nonnull Integer getFieldModifier (@Nonnull Field field) {
        return field.getModifiers();
    }


}
