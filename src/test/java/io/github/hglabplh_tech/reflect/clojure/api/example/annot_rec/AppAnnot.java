package io.github.hglabplh_tech.reflect.clojure.api.example.annot_rec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AppAnnot {
    String module = "None";
    String message = "Unknown";
}
