package ie.harald.g.p.it_cons.reflect.clojure.api.example;

public abstract class InterfaceImplBaseAbstr
        implements InterfaceEx {

    public enum MyPlan {
        TEST_OK(1, "test-ok"),
        TEST_NOK(6, "test-nok"),
        TEST_OK_CLONE(7, "the-clone"),
        TEST_MAYBE_OK(8, "test-maybe-ok"),
        ;

        private final Integer codeNum;

        private final String displayName;

        MyPlan(Integer codeNum, String displayName) {
            this.codeNum = codeNum;
            this.displayName = displayName;
        }

        public Integer codeNum() {
            return codeNum;
        }

        public String displayName() {
            return displayName;
        }
    }
}
