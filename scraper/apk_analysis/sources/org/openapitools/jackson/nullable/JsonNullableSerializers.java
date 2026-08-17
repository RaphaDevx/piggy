package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;

/* loaded from: classes4.dex */
public class JsonNullableSerializers extends Serializers.Base {
    @Override // com.fasterxml.jackson.databind.ser.Serializers.Base, com.fasterxml.jackson.databind.ser.Serializers
    public JsonSerializer<?> findReferenceSerializer(SerializationConfig serializationConfig, ReferenceType referenceType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        if (JsonNullable.class.isAssignableFrom(referenceType.getRawClass())) {
            return new JsonNullableSerializer(referenceType, typeSerializer == null && serializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING), typeSerializer, jsonSerializer);
        }
        return null;
    }
}
