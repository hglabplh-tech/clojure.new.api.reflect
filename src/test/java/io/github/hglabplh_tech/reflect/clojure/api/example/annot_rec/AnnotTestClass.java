package io.github.hglabplh_tech.reflect.clojure.api.example.annot_rec;

import javax.annotation.Nonnull;

public class AnnotTestClass extends AnnotTestBase implements  HumanResource {

    private final Category theCategory;

    private final EmployeeType emplType;

    private final String name;

    public AnnotTestClass(@Nonnull Category category,
                          @Nonnull EmployeeType emplType, @Nonnull String name) {
        this.theCategory = category;
        this.emplType = emplType;
        this.name = name;
    }

    @SuppressWarnings({"test", "is", "well"})
    public Category theCategory() {
        return theCategory;
    }

    @TransportSpec(transportType = TransportSpec.TransportType.PIPE,
    sourceType = Category.class, targetType = StringBuilder.class)
    public EmployeeType emplType() {
        return emplType;
    }

    @TransportSpec(transportType = TransportSpec.TransportType.HTTP)
    public String name() {
        return name;
    }
}
