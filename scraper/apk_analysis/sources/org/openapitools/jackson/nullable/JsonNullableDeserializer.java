package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.io.IOException;

/* loaded from: classes4.dex */
public class JsonNullableDeserializer extends ReferenceTypeDeserializer<JsonNullable<Object>> {
    private static final long serialVersionUID = 1;
    private boolean isStringDeserializer;

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public /* bridge */ /* synthetic */ ReferenceTypeDeserializer<JsonNullable<Object>> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        return withResolved2(typeDeserializer, (JsonDeserializer<?>) jsonDeserializer);
    }

    public JsonNullableDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(javaType, valueInstantiator, typeDeserializer, jsonDeserializer);
        this.isStringDeserializer = false;
        if (javaType instanceof ReferenceType) {
            ReferenceType referenceType = (ReferenceType) javaType;
            if (referenceType.getReferencedType() != null) {
                this.isStringDeserializer = referenceType.getReferencedType().isTypeOrSubTypeOf(String.class);
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonNullable<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && !this.isStringDeserializer && jsonParser.getText().trim().isEmpty()) {
            return JsonNullable.undefined();
        }
        return (JsonNullable) super.deserialize(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    /* renamed from: withResolved, reason: avoid collision after fix types in other method */
    public ReferenceTypeDeserializer<JsonNullable<Object>> withResolved2(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new JsonNullableDeserializer(this._fullType, this._valueInstantiator, typeDeserializer, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
    public Object getAbsentValue(DeserializationContext deserializationContext) {
        return JsonNullable.undefined();
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
    public JsonNullable<Object> getNullValue(DeserializationContext deserializationContext) {
        return JsonNullable.of(null);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) {
        return JsonNullable.undefined();
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public JsonNullable<Object> referenceValue(Object obj) {
        return JsonNullable.of(obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public Object getReferenced(JsonNullable<Object> jsonNullable) {
        return jsonNullable.get();
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
    public JsonNullable<Object> updateReference(JsonNullable<Object> jsonNullable, Object obj) {
        return JsonNullable.of(obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }
}
