package ie.harald.g.p.it_cons.reflect.clojure.api.example.annot_rec;

public enum MyEnum {
    TOOLS("tools", "develop"),
    APIS("apis", "design"),
    TESTS("tests", "support"),
    PROJECT("project", "architecture board"),
    NONE("none","none"),;

    private final String name;

    private final String department;

    MyEnum(String name, String department) {
        this.name = name;
        this.department = department;
    }


    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public static MyEnum findByName(String theName) {
        for (MyEnum actVal : MyEnum.values()){
            if (actVal.getName().equals(theName)) {
                return actVal;
            }
        }
        return MyEnum.NONE;
    }

}
