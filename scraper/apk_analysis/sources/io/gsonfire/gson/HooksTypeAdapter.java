package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.ClassConfig;
import io.gsonfire.PostProcessor;
import io.gsonfire.PreProcessor;
import io.gsonfire.util.JsonUtils;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class HooksTypeAdapter<T> extends TypeAdapter<T> {
    private final ClassConfig<? super T> classConfig;
    private final Class<T> clazz;
    private final Gson gson;
    private final HooksInvoker hooksInvoker = new HooksInvoker();
    private final TypeAdapter<T> originalTypeAdapter;

    public HooksTypeAdapter(Class<T> cls, ClassConfig<? super T> classConfig, TypeAdapter<T> typeAdapter, Gson gson) {
        this.classConfig = classConfig;
        this.gson = gson;
        this.originalTypeAdapter = typeAdapter;
        this.clazz = cls;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) throws IOException {
        if (this.classConfig.isHooksEnabled()) {
            this.hooksInvoker.preSerialize(t);
        }
        JsonElement jsonTree = JsonUtils.toJsonTree(this.originalTypeAdapter, jsonWriter, t);
        runPostSerialize(jsonTree, t);
        this.gson.toJson(jsonTree, jsonWriter);
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        JsonElement parse = new JsonParser().parse(jsonReader);
        runPreDeserialize(parse);
        T deserialize = deserialize(parse, jsonReader.isLenient());
        if (this.classConfig.isHooksEnabled()) {
            this.hooksInvoker.postDeserialize(deserialize, parse, this.gson);
        }
        runPostDeserialize(deserialize, parse);
        return deserialize;
    }

    private void runPostSerialize(JsonElement jsonElement, T t) {
        Iterator<PostProcessor<? super T>> it = this.classConfig.getPostProcessors().iterator();
        while (it.hasNext()) {
            it.next().postSerialize(jsonElement, t, this.gson);
        }
    }

    private void runPostDeserialize(T t, JsonElement jsonElement) {
        Iterator<PostProcessor<? super T>> it = this.classConfig.getPostProcessors().iterator();
        while (it.hasNext()) {
            it.next().postDeserialize(t, jsonElement, this.gson);
        }
    }

    private void runPreDeserialize(JsonElement jsonElement) {
        Iterator<PreProcessor<? super T>> it = this.classConfig.getPreProcessors().iterator();
        while (it.hasNext()) {
            it.next().preDeserialize(this.clazz, jsonElement, this.gson);
        }
    }

    private T deserialize(JsonElement jsonElement, boolean z) throws IOException {
        JsonTreeReader jsonTreeReader = new JsonTreeReader(jsonElement);
        jsonTreeReader.setLenient(z);
        return this.originalTypeAdapter.read(jsonTreeReader);
    }
}
