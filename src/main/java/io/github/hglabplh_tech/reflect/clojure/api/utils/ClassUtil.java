package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.IPersistentMap;
import clojure.lang.IPersistentVector;
import clojure.lang.PersistentArrayMap;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.getArrayAsLazyVector;

/**
 * This utility class is for getting the reflection on a specific class
 * which is given by the parameter of the constructor it is for getting the
 * class members via reflective calls
 *
 * @author Harald Glab-Plhak (Harald G.P. IT-Consulting / Projéct Support)
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
     * Here the Interfaces are returned
     * @return the generic interfaces
     */
    public @Nonnull IPersistentVector getInterfaces() {
        return getArrayAsLazyVector(this.theClass.getInterfaces());
    }

    /**
     * this method return a possible generic super-class-type
     * @return return the class-type
     */
    public @Nonnull Type getGenericSuperClass() {
        return this.theClass.getGenericSuperclass();
    }

    /**
     * this method return a possible super-class-type (Class )
     * @return return the class-type
     */
    public @Nonnull Class<?> getSuperClass() {
        return this.theClass.getSuperclass();
    }

    /**
     * If this Class object represents a local or anonymous class within a constructor, returns a Constructor object representing the immediately enclosing constructor of the underlying class. Returns null otherwise. In particular, this method returns null if the underlying class is a local or anonymous class
     * immediately enclosed by a type declaration, instance initializer or static initializer.
     * @return the enclosing constructor
     */
    public @Nonnull Constructor<?>  getEnclosingConstructor() {
        return this.theClass.getEnclosingConstructor();
    }

    /**
     *If this Class object represents a local or anonymous class within a method, returns a Method object representing the immediately enclosing method of the underlying class.
     * Returns null otherwise. In particular, this method
     * returns null if the underlying class is a local or anonymous class immediately
     * enclosed by a type declaration, instance initializer or static initializer.
     *
     * @return the enclosing method
     */
    public @Nonnull Method  getEnclosingMethod() {
        return this.theClass.getEnclosingMethod();
    }

    /**
     * If the class or interface represented by this Class object is a member of another
     * class, returns the Class object representing the class in which it was declared.
     * This method returns null if this class or interface is not a member of any other class. If this Class object
     * represents an array class, a primitive type, or void,then this method returns null.
     * @return the enclosing class
     */
    public @Nonnull Class<?>  getEnclosingClass() {
        return this.theClass.getEnclosingClass();
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

    public static @Nonnull  Boolean isAssignable(Class<?> interfaceToCheck,
                                                       Object typeInst) {
        if (interfaceToCheck.isInterface()) {
            return interfaceToCheck.isAssignableFrom(typeInst.getClass());
        }
        return false;
    }

    public static @Nonnull  IPersistentMap
    getValuesOfInterface(Class<?> interfaceClass, Object typeInst) {
        IPersistentMap resultMap = PersistentArrayMap.EMPTY;
        if (isAssignable(interfaceClass, typeInst)) {

            for (Method meth : interfaceClass.getDeclaredMethods()) {
                Boolean test = (MethsCtorsUtil
                        .getMethodModifiers(meth) & Modifier.PUBLIC) > 0;
                if (test && (meth.getParameterCount() == 0)) {
                    try {
                        String methName = meth.getName();
                        Object result = meth.invoke(typeInst);
                        resultMap.assocEx(methName, result);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return resultMap;
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
    /**
     * Returns true if the class is a record otherwise false
     * @param classToCheck the class to check
     * @return true / false
     */
    public static boolean isClassRecord (Class<?> classToCheck) {
        return classToCheck.isRecord();
    }
}
