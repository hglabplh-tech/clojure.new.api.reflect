package io.github.hglabplh_tech.reflect.clojure.api.utils;

import clojure.lang.*;

import javax.annotation.Nonnull;

import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDesc;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.github.hglabplh_tech.reflect.clojure.api.utils.ClojFunctionalUtils.*;
import static java.lang.invoke.MethodHandles.*;
import static java.lang.invoke.MethodHandles.Lookup.*;

/**
 * This s a utility class for the types enum, records and 'lambda'
 *
 *
 * @author Harald Glab-Plhak (Harald G.P. IT-Consulting / Projéct Support)
 */
public class SpecialFormsUtil {

    /**
     * private contructor for all methods are static
     */
    private SpecialFormsUtil () {

    }

    /**
     * This method analyzes the structure and attributes and constants of a enum and
     * returns it as a map
     * @param theClass the class (it is checked to be an enum class)
     * @return the enum specification
     */
    public static @Nonnull  IPersistentMap
        getEnumSpec(@Nonnull  Class<?> theClass) {
        Class<? extends Enum>  theEnum = null;
        if (theClass.isEnum()) {
            theEnum = (Class<? extends Enum>)theClass;
        } else {
            return PersistentArrayMap.create(PersistentArrayMap.EMPTY)
                    .assocEx(
                            retrieveKeywordForJavaID("e-info-empty", ObjType.CLASS),
                            "empty");
        }
        Enum[]  enumConsts = theEnum.getEnumConstants();
        IPersistentMap enumMap = PersistentArrayMap.EMPTY;
        for (Enum enumC :  enumConsts) {
            String name = enumC.name();
            Integer ordinal = enumC.ordinal();
            Optional<Enum.EnumDesc> enumDescrOpt = enumC.describeConstable();
            IPersistentMap enumAttrMap = PersistentArrayMap
                    .create(PersistentArrayMap.EMPTY)
                    .assoc(retrieveKeywordForJavaID("ordinal", ObjType.NONE)
                            ,ordinal);
            if (enumDescrOpt.isPresent()) { // TODO: complete this
                Enum.EnumDesc description = enumDescrOpt.get();
                ClassDesc classDescription = description.constantType();
                String classDescrString = classDescription.descriptorString();
                enumAttrMap = enumAttrMap
                        .assoc(retrieveKeywordForJavaID("class-descr", ObjType.NONE)
                        ,classDescrString)
                        .assoc(retrieveKeywordForJavaID("enum-descr", ObjType.NONE),
                                description.toString());
            }
            enumMap = enumMap.assoc(name, enumAttrMap);
        }
        return enumMap;

    }

    /**
     * This method analyzes the structure and attributes and constants of a record and
     * returns it as a map
     * @param theObject the object (it is checked to be a record)
     * @return the record specification
     */
    public static @Nonnull  IPersistentMap
        getRecordSpec(@Nonnull Object theObject) {
        IPersistentMap recordMap = PersistentArrayMap.create(PersistentArrayMap.EMPTY);
        Class<? extends  Record> theRecordClass = null;
        if (theObject.getClass().isRecord()) {
            theRecordClass = (Class<? extends Record>)theObject.getClass();
        } else {
            return PersistentArrayMap.create(PersistentArrayMap.EMPTY);
        }
        Field[] fields = theRecordClass.getDeclaredFields();
        DataTypeTransformer.transformTypeValuesFromFields(fields, theObject);
        Method[] methods = theRecordClass.getDeclaredMethods();
        IPersistentMap fieldValues = DataTypeTransformer
                .transformTypeValuesFromFields(fields, theObject);
        IPersistentMap methValues = DataTypeTransformer
                .transformTypeValuesFromMethods(methods, theObject);
        recordMap = recordMap.assoc(retrieveKeywordForJavaID("field-rec-vals", ObjType.NONE),
                fieldValues);
        recordMap = recordMap.assoc(retrieveKeywordForJavaID("meth-rec-vals", ObjType.NONE),
                methValues);
        return recordMap;
    }

    /**
     * This method analyzes a 'lambda' and returns the attributes and the specification
     * !!!EXPERIMENTAL UP TO NOW!!!
     * @param theFunction the object (it is checked to be a 'lambda')
     * @param name the names
     * @return the lambda expression specification
     */
    public @Nonnull IPersistentVector
    getGeneralLambdaExprSpec(@Nonnull Class<? > theFunction, String... name) {
        Constructor<?>[] ctors = theFunction.getDeclaredConstructors();
        Method[] methods = theFunction.getDeclaredMethods();
        Method foundMethod = null;
        for (Method method: methods) {
            boolean isStatic =
                    ((method.getModifiers()
                            & Modifier.STATIC) != 0);
            if (method.isSynthetic() && isStatic) {
                if (name.length != 0) {
                    if(name[0].equals(method.getName())) {
                        foundMethod = method;
                        break;

                }
                } else {
                foundMethod = method;
                break;
                }
            }
        }
        if (foundMethod != null) {
            Type[] paramTypes = foundMethod.getGenericParameterTypes();
            Type returnType = foundMethod.getGenericReturnType();
            Type[] exceptionTypes = foundMethod.getGenericExceptionTypes();
            IPersistentMap paramTypesMap = retrieveGenericParamTypesAsMeta(
                            getArrayAsLazyVector(paramTypes));
            IPersistentMap exceptTypesMap = retrieveGenericParamTypesAsMeta(
                    getArrayAsLazyVector(exceptionTypes));
           IPersistentVector theResult = Tuple.create(
                   returnType,
                   paramTypesMap,
                   exceptTypesMap);
           return theResult;

        }

        return PersistentVector.EMPTY;
    }



}
