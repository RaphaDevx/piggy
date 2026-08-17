package io.gsonfire.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class JsonUtils {
    private JsonUtils() {
    }

    public static JsonElement deepCopy(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                jsonObject.add(entry.getKey(), deepCopy(entry.getValue()));
            }
            return jsonObject;
        }
        if (!jsonElement.isJsonArray()) {
            return (jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) ? jsonElement : JsonNull.INSTANCE;
        }
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
        while (it.hasNext()) {
            jsonArray.add(it.next());
        }
        return jsonArray;
    }

    public static JsonElement toJsonTree(TypeAdapter typeAdapter, JsonWriter jsonWriter, Object obj) throws IOException {
        JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
        jsonTreeWriter.setLenient(jsonWriter.isLenient());
        jsonTreeWriter.setHtmlSafe(jsonWriter.isHtmlSafe());
        jsonTreeWriter.setSerializeNulls(jsonWriter.getSerializeNulls());
        typeAdapter.write(jsonTreeWriter, obj);
        return jsonTreeWriter.get();
    }

    public static <T> T fromJsonTree(TypeAdapter<T> typeAdapter, JsonReader jsonReader, JsonElement jsonElement) throws IOException {
        JsonTreeReader jsonTreeReader = new JsonTreeReader(jsonElement);
        jsonTreeReader.setLenient(jsonReader.isLenient());
        return typeAdapter.read(jsonTreeReader);
    }
}
