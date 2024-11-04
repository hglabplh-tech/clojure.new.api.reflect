package ie.harald.g.p.it_cons.reflect.clojure.api.utils;

import clojure.lang.*;

import javax.annotation.Nonnull;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class is a utility class for conversion t clojure types
 *
 * @author Harald Glab-Plhak (Harald G.P. IT-Consulting / Projéct Support)
 */
public class ClojFunctionalUtils {

    public static IPersistentVector getArrayAsLazyVector(Object [] arrayContent) {
        IPersistentVector newVector = LazilyPersistentVector.createOwning(arrayContent);
        return newVector;
    }



    public static IPersistentVector getListAsLazyVector(List listContent) {
        IPersistentVector newVector = LazilyPersistentVector.create(listContent.toArray());
        return newVector;
    }


    /**
     *
     * @param enumMap
     * @param name
     * @param ordinal
     * @return
     */
    public static @Nonnull IPersistentMap
    makeKeyWordFromEnumAndAdd(@Nonnull  IPersistentMap enumMap,
                                             @Nonnull  String name, Integer ordinal) {

        Keyword word = Keyword.intern(name);
        enumMap.assocEx(word, ordinal);
        return enumMap;
    }

    public static  @Nonnull  IPersistentMap
        retrieveGenericParamTypesAsMeta(IPersistentVector paramVect) {
        IPersistentMap resultMap = PersistentArrayMap.EMPTY;
        for (int index = 0;
             index < paramVect.length();
             index++) {
            TypeVariable<?> typeVar = (TypeVariable<?>)paramVect.nth(index);
            String varName = typeVar.getName();
            String typeName = typeVar.getTypeName();
            resultMap.assocEx(index, Tuple.create(varName, typeName));

        }
        return resultMap;
    }
}
