package io.gsonfire.util.reflection;

import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class FieldInspector extends AnnotationInspector<Field, Field> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.gsonfire.util.reflection.AnnotationInspector
    public Field map(Field field) {
        return field;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.gsonfire.util.reflection.AnnotationInspector
    public Field[] getDeclaredMembers(Class cls) {
        return cls.getDeclaredFields();
    }
}
