package hgp.reflect.clojure.api.app_exam;

public record AppConfig() {
    static AppConfigFields configEnum = AppConfigFields.HOST;
    static String user = "default";
}
