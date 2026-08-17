package org.openapitools.jackson.nullable;

import jakarta.validation.valueextraction.UnwrapByDefault;
import jakarta.validation.valueextraction.ValueExtractor;
import java.util.Objects;
import org.openapitools.jackson.nullable.JsonNullableValueExtractorHelper;

@UnwrapByDefault
/* loaded from: classes4.dex */
public class JsonNullableJakartaValueExtractor implements ValueExtractor<JsonNullable<?>> {
    public void extractValues(JsonNullable<?> jsonNullable, final ValueExtractor.ValueReceiver valueReceiver) {
        Objects.requireNonNull(valueReceiver);
        JsonNullableValueExtractorHelper.extractValues(jsonNullable, new JsonNullableValueExtractorHelper.ValueSetter() { // from class: org.openapitools.jackson.nullable.JsonNullableJakartaValueExtractor$$ExternalSyntheticLambda0
            @Override // org.openapitools.jackson.nullable.JsonNullableValueExtractorHelper.ValueSetter
            public final void apply(String str, Object obj) {
                valueReceiver.value(str, obj);
            }
        });
    }
}
