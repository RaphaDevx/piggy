package org.openapitools.jackson.nullable;

/* loaded from: classes4.dex */
abstract class JsonNullableValueExtractorHelper {

    @FunctionalInterface
    interface ValueSetter {
        void apply(String str, Object obj);
    }

    JsonNullableValueExtractorHelper() {
    }

    public static void extractValues(JsonNullable<?> jsonNullable, ValueSetter valueSetter) {
        if (jsonNullable.isPresent()) {
            valueSetter.apply(null, jsonNullable.get());
        }
    }
}
