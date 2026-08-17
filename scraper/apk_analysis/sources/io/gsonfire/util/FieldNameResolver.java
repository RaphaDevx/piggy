package io.gsonfire.util;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes2.dex */
public final class FieldNameResolver {
    private final ConcurrentMap<Field, String> fieldNameCache = new ConcurrentHashMap();
    private final FieldNamingStrategy fieldNamingStrategy;

    public FieldNameResolver(Gson gson) {
        this.fieldNamingStrategy = getFieldNamingStrategy(gson);
    }

    public String getFieldName(Field field) {
        String str = this.fieldNameCache.get(field);
        if (str == null) {
            SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
            if (serializedName == null) {
                str = this.fieldNamingStrategy.translateName(field);
            } else {
                str = serializedName.value();
            }
            if (!this.fieldNameCache.containsKey(field)) {
                this.fieldNameCache.put(field, str);
            }
        }
        return str;
    }

    private FieldNamingStrategy getFieldNamingStrategy(Gson gson) {
        return gson.fieldNamingStrategy();
    }
}
