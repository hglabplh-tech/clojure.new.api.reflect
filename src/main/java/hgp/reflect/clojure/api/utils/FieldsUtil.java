package hgp.reflect.clojure.api.utils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

public class FieldsUtil {
    public static @Nonnull Class<?> getFieldType (@Nonnull Field field) {
        return field.getType();
    }

    public static @Nonnull Integer getFieldModifier (@Nonnull Field field) {
        return field.getModifiers();
    }
}
