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

    private final Class<?> theClass;

    public ClassUtil (@Nonnull Class<?> theClass) {
        this.theClass = theClass;
    }

    public String getClassName() {
        return this.theClass.getCanonicalName();
    }

    public Class<?> getTheClass() {
        return this.theClass;
    }


    // here have to think about the name ;; annot are a special case
    public @Nonnull IPersistentVector getPublicAnnotations(Class<Annotation> annotationClass) {
        Annotation[] annots = theClass.getAnnotations();
        return getArrayAsLazyVector(annots);
    }

    public @Nonnull IPersistentVector getDeclAnnotsByType(Class<Annotation> annotationClass) {
        Annotation[] annots = theClass.getDeclaredAnnotationsByType(annotationClass);
        return getArrayAsLazyVector(annots);
    }

    public @Nonnull IPersistentVector getPublicMethods() {
        Method[] methods = theClass.getMethods();
        return getArrayAsLazyVector(methods);
    }

    public @Nonnull IPersistentVector getConstructors() {
        Constructor<?>[] constrArray =
                this.theClass.getConstructors();
        return getArrayAsLazyVector(constrArray);
    }

    public @Nonnull IPersistentVector getAllConstructors() {
        Constructor<?>[] constrArray =
                this.theClass.getDeclaredConstructors();
        return getArrayAsLazyVector(constrArray);
    }

    /**
     * Get all methods inside the class
     * @return a Vector with all declared methods either private
     * or public
     *
     */
    public @Nonnull IPersistentVector getAllMethods() {
        Method[] methods = this.theClass.getDeclaredMethods();
        return getArrayAsLazyVector(methods);
    }

    public @Nonnull IPersistentVector getVisibleMethods () {
        return getArrayAsLazyVector(this.theClass.getMethods());
    }

    /**
     * Get all public fields inside the class
     * @return a vector with all public fields
     */
    public @Nonnull IPersistentVector getPublicFields() {
        Field[] fields = theClass.getFields();
        return getArrayAsLazyVector(fields);
    }

    public @Nonnull IPersistentVector getAllFields() {
        Field[] fields = theClass.getDeclaredFields();
        return getArrayAsLazyVector(fields);
    }

    public @Nonnull IPersistentVector getGenericInterfaces() {
        return getArrayAsLazyVector(this.theClass.getGenericInterfaces());
    }

    public @Nonnull Type getGenericSuperClass() {
        return this.theClass.getGenericSuperclass();
    }

    public @Nonnull IPersistentVector getGetPublicSubClasses () {
        return getArrayAsLazyVector(this.theClass.getClasses());
    }
    public @Nonnull IPersistentVector getAllSubClasses() {
        return getArrayAsLazyVector(this.theClass.getDeclaredClasses());
    }

    public static boolean isClassAAnnotation (Class<?> classToCheck) {
        return classToCheck.isAnnotation();
    }

    public boolean isClassSynthetic (Class<?> classToCheck) {
        return classToCheck.isSynthetic();
    }

    public boolean isClassSealed (Class<?> classToCheck) {
        return classToCheck.isSealed();
    }

    public boolean isClassEnum (Class<?> classToCheck) {
        return classToCheck.isEnum();
    }

    public boolean isClassAnonymous (Class<?> classToCheck) {
        return classToCheck.isAnonymousClass();
    }

    public boolean isClassArray (Class<?> classToCheck) {
        return classToCheck.isArray();
    }

    public boolean isClassMember (Class<?> classToCheck) {
        return classToCheck.isMemberClass();
    }

    public boolean isClassInterface (Class<?> classToCheck) {
        return classToCheck.isInterface();
    }

    public boolean isClassLocal (Class<?> classToCheck) {
        return classToCheck.isLocalClass();
    }

    public boolean isClassPrimitive (Class<?> classToCheck) {
        return classToCheck.isPrimitive();
    }




}
