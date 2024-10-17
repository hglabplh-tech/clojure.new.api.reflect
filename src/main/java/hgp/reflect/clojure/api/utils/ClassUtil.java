package hgp.reflect.clojure.api.utils;

import clojure.lang.IPersistentVector;
import clojure.lang.ISeq;
import clojure.lang.LazilyPersistentVector;
import clojure.lang.PersistentVector;

import java.lang.annotation.Annotation;

public class ClassUtil {

    private final Class theClass;

    public ClassUtil(Class theClass) {
        this.theClass = theClass;
    }

    public IPersistentVector getAnnotations() {
        IPersistentVector vector = LazilyPersistentVector.create(null);
        Annotation[] annots = theClass. getAnnotations();
        int index = 0;
        for (Annotation annotation : annots) {
            vector.assocN(index, annotation);
            index++;
        }
        return vector;
    }
}
