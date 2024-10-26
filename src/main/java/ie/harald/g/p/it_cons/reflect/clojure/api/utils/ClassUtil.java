package ie.harald.g.p.it_cons.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static ie.harald.g.p.it_cons.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

/**
 * This utility class is for getting the reflection on a specific class
 * which is given by the parameter of the constructor it is for getting the
 * class members via reflective calls
 *
 * @author Harald Glab-Plhak
 */
public class ClassUtil {

    /**
     * The class on which we work
     */
    private final Class<?> theClass;

    /**
     * Ctor for this utility class
     * @param theClass the class we work on
     */
    public ClassUtil (@Nonnull Class<?> theClass) {
        this.theClass = theClass;
    }

    /**
     * Get the class name of the class we defined for work with
     * @return the canonical class name
     */
    public String getClassName() {
        return this.theClass.getCanonicalName();
    }

    /**
     * getter for the defined class itself
     * @return the Class<?> instance
     *
     */
    public Class<?> getTheClass() {
        return this.theClass;
    }


    /**
     * get the annotations of the class which are visible
     * @return the annotations visible in a Clojure persistance vector
     */
    public @Nonnull IPersistentVector getPublicAnnotations() {
        Annotation[] annots = theClass.getAnnotations();
        return getArrayAsLazyVector(annots);
    }

    /**
     *
     * @param annotationClass the annotation class which defines
     *                        the type of the annotation we search for
     *                        e.g. @Test
     * @return the declared annotations with that type
     */
    public @Nonnull IPersistentVector getDeclAnnotsByType(Class<Annotation> annotationClass) {
        Annotation[] annots = theClass.getDeclaredAnnotationsByType(annotationClass);
        return getArrayAsLazyVector(annots);
    }

    /**
     * get all methods visible as public from outside the class
     * @return the methods as Clojure persistance vector
     */
    public @Nonnull IPersistentVector getPublicMethods() {
        Method[] methods = theClass.getMethods();
        return getArrayAsLazyVector(methods);
    }

    /**
     * get the constructors which are visible from outside the class
     * @return the constructor definitions in a Clojure persistance vector
     */
    public @Nonnull IPersistentVector getConstructors() {
        Constructor<?>[] constrArray =
                this.theClass.getConstructors();
        return getArrayAsLazyVector(constrArray);
    }

    /**
     * get all constructors declared in the class even also the constructors
     * being private
     * @return the cobstructor definitions as Clojure persistance vector
     */
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

    /**
     * Get all public fields inside the class
     * @return a vector with all public fields
     */
    public @Nonnull IPersistentVector getPublicFields() {
        Field[] fields = theClass.getFields();
        return getArrayAsLazyVector(fields);
    }

    /**
     * get all the fields in the class if they are visible and accessible
     * from outside the class or not
     * @return all fields in a Clojure persistance vector
     */
    public @Nonnull IPersistentVector getAllFields() {
        Field[] fields = theClass.getDeclaredFields();
        return getArrayAsLazyVector(fields);
    }

    /**
     * Here the generic Interfaces (something like
     * TypeClass&lt;T&gt; are returned
     * @return the generic interfaces
     */
    public @Nonnull IPersistentVector getGenericInterfaces() {
        return getArrayAsLazyVector(this.theClass.getGenericInterfaces());
    }

    /**
     * this method return a possible generic super-class-type
     * @return return the class-type
     */
    public @Nonnull Type getGenericSuperClass() {
        return this.theClass.getGenericSuperclass();
    }

    /**
     * get all subclasses(inner classes) which are visible and accessible from outside
     * the class
     * @return public sub-classes as Clojure persistent vector
     */
    public @Nonnull IPersistentVector getPublicSubClasses() {
        return getArrayAsLazyVector(this.theClass.getClasses());
    }

    /**
     * get all subclasses(inner classes) even if they
     * are visible and accessible from outside or not
     * the class
     * @return
     */
    public @Nonnull IPersistentVector getAllSubClasses() {
        return getArrayAsLazyVector(this.theClass.getDeclaredClasses());
    }

    /**
     * returns true if the class given is a annotation otherwise false
     * @param classToCheck the class to be checked
     * @return true / false - see description
     */
    public static boolean isClassAnnotation (Class<?> classToCheck) {
        return classToCheck.isAnnotation();
    }

    /**
     * returns true if the class given is a synthetic class
     * otherwise false
     * @param classToCheck the class to be checked
     * @return true / false - see description
     */
    public static boolean isClassSynthetic (Class<?> classToCheck) {
        return classToCheck.isSynthetic();
    }

    /**
     * Returns true if the class is sealed
     * @param classToCheck the class which is checked
     * @return true/ false
     */
    public static boolean isClassSealed (Class<?> classToCheck) {
        return classToCheck.isSealed();
    }

    /**
     * returns true if the class given is a enum otherwise false
     * @param classToCheck the class to be checked
     * @return true / false - see description
     */
    public static boolean isClassEnum (Class<?> classToCheck) {
        return classToCheck.isEnum();
    }

    /**
     * Returns true if the class is an anonymous class otherwise false
     * @param classToCheck the class to be checked
     * @return true / false
     */
    public static  boolean isClassAnonymous (Class<?> classToCheck) {
        return classToCheck.isAnonymousClass();
    }

    /**
     * Returns true if the class is a Array otherwise false
     * @param classToCheck the class to be checked
     * @return true / false
     */
    public static boolean isClassArray (Class<?> classToCheck) {
        return classToCheck.isArray();
    }

    /**
     * Returns true if the class is a member otherwise false
     * @param classToCheck class to be checked
     * @return true / false
     */
    public static boolean isClassMember (Class<?> classToCheck) {
        return classToCheck.isMemberClass();
    }

    /**
     * Returns true if the class is a interface otherwise false
     * @param classToCheck the class to be checked
     * @return true / false
     */
    public static boolean isClassInterface (Class<?> classToCheck) {
        return classToCheck.isInterface();
    }

    /**
     * Returns true if the class is locel otherwise false
     * @param classToCheck the class to be checked
     * @return true / false
     */
    public static boolean isClassLocal (Class<?> classToCheck) {
        return classToCheck.isLocalClass();
    }

    /**
     * Returns true if the class is a primitive otherwise false
     * @param classToCheck the class to check
     * @return true / false
     */
    public static boolean isClassPrimitive (Class<?> classToCheck) {
        return classToCheck.isPrimitive();
    }
}
