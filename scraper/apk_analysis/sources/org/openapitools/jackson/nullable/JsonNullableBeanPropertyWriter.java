package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;

/* loaded from: classes4.dex */
public class JsonNullableBeanPropertyWriter extends BeanPropertyWriter {
    private static final long serialVersionUID = 1;

    protected JsonNullableBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    protected JsonNullableBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, PropertyName propertyName) {
        super(beanPropertyWriter, propertyName);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter
    protected BeanPropertyWriter _new(PropertyName propertyName) {
        return new JsonNullableBeanPropertyWriter(this, propertyName);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter
    public BeanPropertyWriter unwrappingWriter(NameTransformer nameTransformer) {
        return new UnwrappingJsonNullableBeanPropertyWriter(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter, com.fasterxml.jackson.databind.ser.PropertyWriter
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
