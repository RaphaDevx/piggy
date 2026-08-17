package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

/* loaded from: classes4.dex */
public class UnwrappingJsonNullableBeanPropertyWriter extends UnwrappingBeanPropertyWriter {
    private static final long serialVersionUID = 1;

    public UnwrappingJsonNullableBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, NameTransformer nameTransformer) {
        super(beanPropertyWriter, nameTransformer);
    }

    protected UnwrappingJsonNullableBeanPropertyWriter(UnwrappingBeanPropertyWriter unwrappingBeanPropertyWriter, NameTransformer nameTransformer, SerializedString serializedString) {
        super(unwrappingBeanPropertyWriter, nameTransformer, serializedString);
    }

    @Override // com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter
    protected UnwrappingBeanPropertyWriter _new(NameTransformer nameTransformer, SerializedString serializedString) {
        return new UnwrappingJsonNullableBeanPropertyWriter(this, nameTransformer, serializedString);
    }

    @Override // com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter, com.fasterxml.jackson.databind.ser.BeanPropertyWriter, com.fasterxml.jackson.databind.ser.PropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = get(obj);
        if (JsonNullable.undefined().equals(obj2)) {
            return;
        }
        if (this._nullSerializer == null && obj2 == null) {
            return;
        }
        super.serializeAsField(obj, jsonGenerator, serializerProvider);
    }
}
