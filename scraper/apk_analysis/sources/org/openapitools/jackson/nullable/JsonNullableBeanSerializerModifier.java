package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.util.List;

/* loaded from: classes4.dex */
public class JsonNullableBeanSerializerModifier extends BeanSerializerModifier {
    @Override // com.fasterxml.jackson.databind.ser.BeanSerializerModifier
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        for (int i = 0; i < list.size(); i++) {
            BeanPropertyWriter beanPropertyWriter = list.get(i);
            if (beanPropertyWriter.getType().isTypeOrSubTypeOf(JsonNullable.class)) {
                list.set(i, new JsonNullableBeanPropertyWriter(beanPropertyWriter));
            }
        }
        return list;
    }
}
