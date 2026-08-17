package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.util.SimpleIterable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class SimpleIterableTypeAdapter extends TypeAdapter<SimpleIterable<?>> {
    private final Gson gson;
    private final Type type;

    public SimpleIterableTypeAdapter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, SimpleIterable<?> simpleIterable) throws IOException {
        if (simpleIterable != null) {
            jsonWriter.beginArray();
            Iterator<?> it = simpleIterable.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                this.gson.toJson(next, next.getClass(), jsonWriter);
            }
            jsonWriter.endArray();
            return;
        }
        jsonWriter.nullValue();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public SimpleIterable<?> read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(this.gson.fromJson(jsonReader, this.type));
        }
        jsonReader.endArray();
        return SimpleIterable.of(arrayList);
    }
}
