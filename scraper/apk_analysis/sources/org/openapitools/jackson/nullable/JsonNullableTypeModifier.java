package org.openapitools.jackson.nullable;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class JsonNullableTypeModifier extends TypeModifier {
    @Override // com.fasterxml.jackson.databind.type.TypeModifier
    public JavaType modifyType(JavaType javaType, Type type, TypeBindings typeBindings, TypeFactory typeFactory) {
        return (javaType.isReferenceType() || javaType.isContainerType() || javaType.getRawClass() != JsonNullable.class) ? javaType : ReferenceType.upgradeFrom(javaType, javaType.containedTypeOrUnknown(0));
    }
}
