package io.github.hglabplh_tech.reflect.clojure.api.annotations;

import clojure.lang.IPersistentMap;
import clojure.lang.Keyword;
import clojure.lang.PersistentArrayMap;
import io.github.hglabplh_tech.reflect.clojure.api.utils.ClassUtil;
import io.github.hglabplh_tech.reflect.clojure.api.utils.DataTypeTransformer;

import javax.annotation.Nonnull;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.ObjType;
import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.retrieveKeywordForJavaID;

/**
 * This class is a utility class for analyzing the content and the attributes of
 * a given annotation.
 *
 * @param <T> T is the type of the class and this type extends the annotation class
 * @author Harald Glab-Plhak (Harald G.P. IT-Consulting / Projéct Support)
 */
public class AnnotationTool<T extends Annotation> {

    /**
     * the annotation to be analyzed
     */
    private final Class<T> theAnnotation;

    /**
     * Here the Utility is constructed with an annotation to be analyzed
     * if the input class is not an annotation an Illegal Argument Exception
     * is thrown
     *
     * @param annot a annotation to analyze
     */
    public AnnotationTool(Class<T> annot) {
        if (ClassUtil.isClassAnnotation(annot)) {
            this.theAnnotation = annot;
        } else {
            throw new IllegalArgumentException("class is not an annotation");
        }
    }


    /**
     * With this the attributes of a annotation are retrieved (Target, Retention)
     *
     * @return the map of the annotation attributes
     */
    public IPersistentMap getAnnotationAttributes() {
        Annotation[] annots = this.theAnnotation.getAnnotations();
        IPersistentMap theMap = PersistentArrayMap.create(PersistentArrayMap.EMPTY);
        for (Annotation annot : annots) {
            if (annot.getClass()
                    .isAssignableFrom(java.lang.annotation.Target.class)) {
                IPersistentMap typeMap = PersistentArrayMap.create(PersistentArrayMap.EMPTY);
                theMap.assocEx(
                        Keyword.intern("Target"),
                        typeMap);
                Target theTarget = (Target) annot;
                fillTargetAnnotAttribs(theTarget, typeMap);
            } else if (annot.getClass()
                    .isAssignableFrom(java.lang.annotation.Retention.class)) {
                IPersistentMap typeMap = PersistentArrayMap.create(PersistentArrayMap.EMPTY);
                theMap.assocEx(
                        Keyword.intern("Retention"),
                        typeMap);
                Retention theRetention = (Retention) annot;
                RetentionPolicy theRetPolicy = theRetention.value();
                typeMap.assocEx(
                        retrieveKeywordForJavaID(theRetPolicy.name(), ObjType.FIELD),
                        theRetPolicy);
            }
        }
        return theMap;
    }

    public static @Nonnull IPersistentMap retrieveAnnotationValues(Annotation annot) {
        IPersistentMap theMap = PersistentArrayMap.create(PersistentArrayMap.EMPTY);
        Class<? extends Annotation> type = annot.annotationType();
        Field[] fields = type.getFields();
        Set<String> fldNames =
                Arrays.stream(fields).map(Field::getName)
                        .collect(Collectors.toSet());

        Method[] methods = type.getMethods();
        Set<String> methNames =
                Arrays.stream(methods)
                        .filter(e -> e.getParameterCount() == 0)
                        .map(Method::getName)
                        .collect(Collectors.toSet());
        IPersistentMap fldMap = DataTypeTransformer.transformTypeValuesFromFields(fields, annot);
        theMap.assocEx(retrieveKeywordForJavaID("fields-values", ObjType.NONE),
        fldMap);
        return theMap;
    }


    /**
     * Put the element types of the annotation '@Target' to a map
     *
     * @param theAnnotation the annotation
     * @param typeMap       the map containing the element types of the @Target
     *                      annotation
     */
    private static void
    fillTargetAnnotAttribs(Target theAnnotation, IPersistentMap typeMap) {
        ElementType[] elementTypes = theAnnotation.value();
        for (ElementType eType : elementTypes) {
            Keyword elementKey = retrieveKeywordForJavaID(eType.name(), ObjType.FIELD);
            typeMap.assoc(elementKey, eType);
        }
    }
}