package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;

/* loaded from: classes4.dex */
public class JsonNullableSerializer extends ReferenceTypeSerializer<JsonNullable<?>> {
    private static final long serialVersionUID = 1;

    protected JsonNullableSerializer(ReferenceType referenceType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(referenceType, z, typeSerializer, jsonSerializer);
    }

    protected JsonNullableSerializer(JsonNullableSerializer jsonNullableSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, Object obj) {
        super(jsonNullableSerializer, beanProperty, typeSerializer, jsonSerializer, nameTransformer, obj, false);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    protected ReferenceTypeSerializer<JsonNullable<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer) {
        return new JsonNullableSerializer(this, beanProperty, typeSerializer, jsonSerializer, nameTransformer, this._suppressableValue);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public ReferenceTypeSerializer<JsonNullable<?>> withContentInclusion(Object obj, boolean z) {
        return new JsonNullableSerializer(this, this._property, this._valueTypeSerializer, this._valueSerializer, this._unwrapper, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public boolean _isValuePresent(JsonNullable<?> jsonNullable) {
        return jsonNullable.isPresent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public Object _getReferenced(JsonNullable<?> jsonNullable) {
        return jsonNullable.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
    public Object _getReferencedIfPresent(JsonNullable<?> jsonNullable) {
        if (jsonNullable.isPresent()) {
            return jsonNullable.get();
        }
        return null;
    }
}
