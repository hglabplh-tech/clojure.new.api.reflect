package io.github.hglabplh_tech.reflect.clojure.api.example;

public interface InterfaceEx {

    void newDocument(DocumentExample doc) throws DocumentException;

    void retrieveDocument(DocumentExample doc) throws DocumentException;

    void updateDocument(DocumentExample doc) throws DocumentException;

    void deleteDocument(DocumentExample doc) throws DocumentException;

    default boolean testDocument () throws IllegalArgumentException {
        return true;
    }
}
