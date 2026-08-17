package javax.ws.rs.core;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class GenericEntity<T> {
    private final T entity;
    private final Class<?> rawType;
    private final Type type;

    protected GenericEntity(T t) {
        if (t == null) {
            throw new IllegalArgumentException("The entity must not be null");
        }
        this.entity = t;
        this.type = GenericType.getTypeArgument(getClass(), GenericEntity.class);
        this.rawType = t.getClass();
    }

    public GenericEntity(T t, Type type) {
        if (t == null || type == null) {
            throw new IllegalArgumentException("Arguments must not be null.");
        }
        this.entity = t;
        Class<?> cls = t.getClass();
        this.rawType = cls;
        checkTypeCompatibility(cls, type);
        this.type = type;
    }

    private void checkTypeCompatibility(Class<?> cls, Type type) {
        if (type instanceof Class) {
            if (((Class) type).isAssignableFrom(cls)) {
                return;
            }
        } else if (type instanceof ParameterizedType) {
            checkTypeCompatibility(cls, ((ParameterizedType) type).getRawType());
            return;
        } else if (cls.isArray() && (type instanceof GenericArrayType)) {
            checkTypeCompatibility(cls.getComponentType(), ((GenericArrayType) type).getGenericComponentType());
            return;
        }
        throw new IllegalArgumentException("The type is incompatible with the class of the entity.");
    }

    public final Class<?> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final T getEntity() {
        return this.entity;
    }

    public boolean equals(Object obj) {
        boolean z = this == obj;
        if (z || !(obj instanceof GenericEntity)) {
            return z;
        }
        GenericEntity genericEntity = (GenericEntity) obj;
        return this.type.equals(genericEntity.type) && this.entity.equals(genericEntity.entity);
    }

    public int hashCode() {
        return this.entity.hashCode() + (this.type.hashCode() * 37) + 5;
    }

    public String toString() {
        return "GenericEntity{" + this.entity.toString() + ", " + this.type.toString() + "}";
    }
}
