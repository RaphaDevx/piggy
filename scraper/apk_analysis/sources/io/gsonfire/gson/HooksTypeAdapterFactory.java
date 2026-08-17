package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import io.gsonfire.ClassConfig;

/* loaded from: classes2.dex */
public final class HooksTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final ClassConfig<T> classConfig;

    public HooksTypeAdapterFactory(ClassConfig<T> classConfig) {
        this.classConfig = classConfig;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (!this.classConfig.getConfiguredClass().isAssignableFrom(typeToken.getRawType())) {
            return null;
        }
        return new HooksTypeAdapter(typeToken.getRawType(), this.classConfig, gson.getDelegateAdapter(this, typeToken), gson);
    }
}
