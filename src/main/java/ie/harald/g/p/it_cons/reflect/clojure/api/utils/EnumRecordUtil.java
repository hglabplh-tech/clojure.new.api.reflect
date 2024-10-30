package ie.harald.g.p.it_cons.reflect.clojure.api.utils;

import clojure.lang.*;

import javax.annotation.Nonnull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import static ie.harald.g.p.it_cons.reflect.clojure.api.utils.ClojFunctionalUtils.*;

public class EnumRecordUtil {

    public static @Nonnull  IPersistentMap
        getEnumSpec(@Nonnull  Enum theEnum) {
        Enum[]  enumConsts = theEnum.getClass().getEnumConstants();
        IPersistentMap enumMap = PersistentArrayMap.EMPTY;
        for (Enum enumC :  enumConsts) {
            String name = enumC.name();
            Integer ordinal = enumC.ordinal();
            makeKeyWordFromEnumAndAdd(enumMap, name, ordinal);
        }
        return enumMap;

    }

    public static @Nonnull  Integer
        getRecordSpec(@Nonnull Record theEnum) {
        return 0;
    }

    public @Nonnull IPersistentVector
        analyzeGeneralLambdaExpr (@Nonnull Class<?> theFunction, String... name) {
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

    /*
    Private methods
     */


}
