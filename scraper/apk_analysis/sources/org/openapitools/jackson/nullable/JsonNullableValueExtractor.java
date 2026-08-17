package org.openapitools.jackson.nullable;

import java.util.Objects;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;
import org.openapitools.jackson.nullable.JsonNullableValueExtractorHelper;

@UnwrapByDefault
/* loaded from: classes4.dex */
public class JsonNullableValueExtractor implements ValueExtractor<JsonNullable<?>> {
    public void extractValues(JsonNullable<?> jsonNullable, final ValueExtractor.ValueReceiver valueReceiver) {
        Objects.requireNonNull(valueReceiver);
        JsonNullableValueExtractorHelper.extractValues(jsonNullable, new JsonNullableValueExtractorHelper.ValueSetter() { // from class: org.openapitools.jackson.nullable.JsonNullableValueExtractor$$ExternalSyntheticLambda0
            @Override // org.openapitools.jackson.nullable.JsonNullableValueExtractorHelper.ValueSetter
            public final void apply(String str, Object obj) {
                valueReceiver.value(str, obj);
            }
        });
    }
}
