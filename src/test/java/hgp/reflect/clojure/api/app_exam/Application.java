package hgp.reflect.clojure.api.app_exam;

import java.net.URL;
import java.util.Objects;

public class Application {

    public static String BLUBB = "Hallo";
    private final String host;

    private final Integer port;

    private final URL url;

    public Application(String host, Integer port, URL url) {
        this.host = host;
        this.port = port;
        this.url = url;
    }

    public String host() {
        return host;
    }

    public Integer port() {
        return port;
    }

    public URL url() {
        return url;
    }

    private Integer sendRequest(URL url, String host, Integer port) {
        System.out.println("I am called");
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(host, that.host) && Objects.equals(port, that.port) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, url);
    }

    @Override
    public String toString() {
        return "Application{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", url=" + url +
                '}';
    }
}
