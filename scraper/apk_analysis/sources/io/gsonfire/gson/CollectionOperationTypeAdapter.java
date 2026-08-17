package io.gsonfire.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/* loaded from: classes2.dex */
final class CollectionOperationTypeAdapter extends TypeAdapter<Collection> {
    private static final JsonElement EMPTY_ARRAY = new JsonArray();
    private final TypeAdapter<Collection> collectionTypeAdapter;

    private enum Operator {
        $add { // from class: io.gsonfire.gson.CollectionOperationTypeAdapter.Operator.1
            @Override // io.gsonfire.gson.CollectionOperationTypeAdapter.Operator
            public void apply(Collection collection, Collection collection2) {
                collection.addAll(collection2);
            }
        },
        $remove { // from class: io.gsonfire.gson.CollectionOperationTypeAdapter.Operator.2
            @Override // io.gsonfire.gson.CollectionOperationTypeAdapter.Operator
            public void apply(Collection collection, Collection collection2) {
                collection.removeAll(collection2);
            }
        },
        $clear { // from class: io.gsonfire.gson.CollectionOperationTypeAdapter.Operator.3
            @Override // io.gsonfire.gson.CollectionOperationTypeAdapter.Operator
            public void apply(Collection collection, Collection collection2) {
                collection.clear();
            }
        };

        public abstract void apply(Collection collection, Collection collection2);
    }

    public CollectionOperationTypeAdapter(TypeAdapter<Collection> typeAdapter) {
        this.collectionTypeAdapter = typeAdapter;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Collection collection) throws IOException {
        this.collectionTypeAdapter.write(jsonWriter, collection);
    }

    @Override // com.google.gson.TypeAdapter
    public Collection read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            Collection fromJsonTree = this.collectionTypeAdapter.fromJsonTree(EMPTY_ARRAY);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                Operator valueOf = Operator.valueOf(jsonReader.nextName());
                valueOf.apply(fromJsonTree, valueOf == Operator.$clear ? null : this.collectionTypeAdapter.read(jsonReader));
            }
            jsonReader.endObject();
            return fromJsonTree;
        }
        return this.collectionTypeAdapter.read(jsonReader);
    }
}
