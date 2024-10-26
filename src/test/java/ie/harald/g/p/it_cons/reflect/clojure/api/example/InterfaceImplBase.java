package ie.harald.g.p.it_cons.reflect.clojure.api.example;

import ie.harald.g.p.it_cons.reflect.clojure.api.example.annot_rec.AppAnnot;

import java.util.Objects;

public class InterfaceImplBase {

    private InterfaceImplBase() {

    }

    public static InterfaceImplBase instance() {
        return new InterfaceImplBase();
    }

    public static class TestInner {

        @AppAnnot
        private final String firstName;

        @AppAnnot
        private final String lastName;

        public TestInner(String firtName, String lastName) {
            this.firstName = firtName;
            this.lastName = lastName;
        }

        public String firstName() {
            return firstName;
        }

        public String lastName() {
            return lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestInner testInner = (TestInner) o;
            return Objects.equals(firstName, testInner.firstName) && Objects.equals(lastName, testInner.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }



        public byte[] getBytes() {
            return firstName.getBytes();
        }

        @Override
        public String toString() {
            return "TestInner{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
}
