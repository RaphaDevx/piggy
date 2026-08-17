package io.gsonfire.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.gsonfire.util.FieldNameResolver;
import io.gsonfire.util.reflection.Factory;
import io.gsonfire.util.reflection.FieldInspector;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class ExcludeByValueTypeAdapterFactory implements TypeAdapterFactory {
    private final Factory factory;
    private final FieldInspector fieldInspector;
    private FieldNameResolver fieldNameResolver = null;

    public ExcludeByValueTypeAdapterFactory(FieldInspector fieldInspector, Factory factory) {
        this.fieldInspector = fieldInspector;
        this.factory = factory;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (this.fieldNameResolver == null) {
            this.fieldNameResolver = new FieldNameResolver(gson);
        }
        return new ExcludeByValueTypeAdapter(gson, gson.getDelegateAdapter(this, typeToken));
    }

    private class ExcludeByValueTypeAdapter extends TypeAdapter {
        private final Gson gson;
        private final TypeAdapter originalTypeAdapter;

        public ExcludeByValueTypeAdapter(Gson gson, TypeAdapter typeAdapter) {
            this.gson = gson;
            this.originalTypeAdapter = typeAdapter;
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:
        
            r5.gson.toJson(r1, r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
        
            return;
         */
        @Override // com.google.gson.TypeAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void write(com.google.gson.stream.JsonWriter r6, java.lang.Object r7) throws java.io.IOException {
            /*
                r5 = this;
                if (r7 != 0) goto L9
                com.google.gson.TypeAdapter r5 = r5.originalTypeAdapter
                r5.write(r6, r7)
                goto L8b
            L9:
                io.gsonfire.gson.ExcludeByValueTypeAdapterFactory r0 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.this
                io.gsonfire.util.reflection.FieldInspector r0 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.access$000(r0)
                java.lang.Class r1 = r7.getClass()
                java.lang.Class<io.gsonfire.annotations.ExcludeByValue> r2 = io.gsonfire.annotations.ExcludeByValue.class
                java.util.Collection r0 = r0.getAnnotatedMembers(r1, r2)
                java.util.Iterator r0 = r0.iterator()
                r1 = 0
            L1e:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L7e
                java.lang.Object r2 = r0.next()
                java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
                java.lang.Class<io.gsonfire.annotations.ExcludeByValue> r3 = io.gsonfire.annotations.ExcludeByValue.class
                java.lang.annotation.Annotation r3 = r2.getAnnotation(r3)     // Catch: java.lang.IllegalAccessException -> L77
                io.gsonfire.annotations.ExcludeByValue r3 = (io.gsonfire.annotations.ExcludeByValue) r3     // Catch: java.lang.IllegalAccessException -> L77
                java.lang.Class r3 = r3.value()     // Catch: java.lang.IllegalAccessException -> L77
                io.gsonfire.gson.ExcludeByValueTypeAdapterFactory r4 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.this     // Catch: java.lang.IllegalAccessException -> L77
                io.gsonfire.util.reflection.Factory r4 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.access$100(r4)     // Catch: java.lang.IllegalAccessException -> L77
                java.lang.Object r3 = r4.get(r3)     // Catch: java.lang.IllegalAccessException -> L77
                io.gsonfire.gson.ExclusionByValueStrategy r3 = (io.gsonfire.gson.ExclusionByValueStrategy) r3     // Catch: java.lang.IllegalAccessException -> L77
                java.lang.Object r4 = r2.get(r7)     // Catch: java.lang.IllegalAccessException -> L77
                boolean r3 = r3.shouldSkipField(r4)     // Catch: java.lang.IllegalAccessException -> L77
                if (r3 == 0) goto L1e
                io.gsonfire.gson.ExcludeByValueTypeAdapterFactory r3 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.this     // Catch: java.lang.IllegalAccessException -> L77
                io.gsonfire.util.FieldNameResolver r3 = io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.access$200(r3)     // Catch: java.lang.IllegalAccessException -> L77
                java.lang.String r2 = r3.getFieldName(r2)     // Catch: java.lang.IllegalAccessException -> L77
                if (r2 == 0) goto L1e
                if (r1 != 0) goto L73
                com.google.gson.TypeAdapter r3 = r5.originalTypeAdapter     // Catch: java.lang.IllegalAccessException -> L77
                com.google.gson.JsonElement r3 = io.gsonfire.util.JsonUtils.toJsonTree(r3, r6, r7)     // Catch: java.lang.IllegalAccessException -> L77
                if (r3 == 0) goto L7e
                boolean r4 = r3.isJsonNull()     // Catch: java.lang.IllegalAccessException -> L77
                if (r4 != 0) goto L7e
                boolean r4 = r3.isJsonObject()     // Catch: java.lang.IllegalAccessException -> L77
                if (r4 != 0) goto L6f
                goto L7e
            L6f:
                com.google.gson.JsonObject r1 = r3.getAsJsonObject()     // Catch: java.lang.IllegalAccessException -> L77
            L73:
                r1.remove(r2)     // Catch: java.lang.IllegalAccessException -> L77
                goto L1e
            L77:
                r5 = move-exception
                java.lang.RuntimeException r6 = new java.lang.RuntimeException
                r6.<init>(r5)
                throw r6
            L7e:
                if (r1 == 0) goto L86
                com.google.gson.Gson r5 = r5.gson
                r5.toJson(r1, r6)
                goto L8b
            L86:
                com.google.gson.TypeAdapter r5 = r5.originalTypeAdapter
                r5.write(r6, r7)
            L8b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.gsonfire.gson.ExcludeByValueTypeAdapterFactory.ExcludeByValueTypeAdapter.write(com.google.gson.stream.JsonWriter, java.lang.Object):void");
        }

        @Override // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader) throws IOException {
            return this.originalTypeAdapter.read(jsonReader);
        }
    }
}
