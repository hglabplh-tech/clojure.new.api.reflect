package ie.harald.g.p.it_cons.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;
import clojure.lang.LazilyPersistentVector;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ClojFunctionalUtils {

    public static IPersistentVector getArrayAsLazyVector(Object [] arrayContent) {
        IPersistentVector newVector = LazilyPersistentVector.createOwning(arrayContent);
        int index = 0;
       /* for (Object element : arrayContent) {
            newVector.assocN(index, element);
            index++;
        }*/
        return newVector;
    }



    public static IPersistentVector getListAsLazyVector(List listContent) {
        IPersistentVector newVector = LazilyPersistentVector.create(listContent.toArray());
        int index = 0;
        /*for (Object element : listContent) {
            newVector.assocN(index, element);
            index++;
        }*/
        return newVector;
    }


}
