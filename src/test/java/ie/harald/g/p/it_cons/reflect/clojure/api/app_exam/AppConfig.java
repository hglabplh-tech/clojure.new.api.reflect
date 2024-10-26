package ie.harald.g.p.it_cons.reflect.clojure.api.app_exam;

public record AppConfig() {
    static AppConfigFields configEnum = AppConfigFields.HOST;
    static String user = "default";
}
