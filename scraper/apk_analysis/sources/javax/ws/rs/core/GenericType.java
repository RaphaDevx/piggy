package javax.ws.rs.core;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Stack;

/* loaded from: classes4.dex */
public class GenericType<T> {
    private final Class<?> rawType;
    private final Type type;

    public static GenericType forInstance(Object obj) {
        if (obj instanceof GenericEntity) {
            return new GenericType(((GenericEntity) obj).getType());
        }
        if (obj == null) {
            return null;
        }
        return new GenericType(obj.getClass());
    }

    protected GenericType() {
        Type typeArgument = getTypeArgument(getClass(), GenericType.class);
        this.type = typeArgument;
        this.rawType = getClass(typeArgument);
    }

    public GenericType(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
        this.type = type;
        this.rawType = getClass(type);
    }

    public final Type getType() {
        return this.type;
    }

    public final Class<?> getRawType() {
        return this.rawType;
    }

    private static Class getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (parameterizedType.getRawType() instanceof Class) {
                return (Class) parameterizedType.getRawType();
            }
        } else if (type instanceof GenericArrayType) {
            return getArrayClass(getClass(((GenericArrayType) type).getGenericComponentType()));
        }
        throw new IllegalArgumentException("Type parameter " + type.toString() + " not a class or parameterized type whose raw type is a class");
    }

    private static Class getArrayClass(Class cls) {
        try {
            return Array.newInstance((Class<?>) cls, 0).getClass();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    static Type getTypeArgument(Class<?> cls, Class<?> cls2) {
        Type genericSuperclass;
        Stack stack = new Stack();
        do {
            genericSuperclass = cls.getGenericSuperclass();
            stack.push(genericSuperclass);
            if (genericSuperclass instanceof Class) {
                cls = (Class) genericSuperclass;
            } else if (genericSuperclass instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) genericSuperclass).getRawType();
            }
        } while (!cls.equals(cls2));
        TypeVariable<Class<?>> typeVariable = cls2.getTypeParameters()[0];
        while (!stack.isEmpty()) {
            genericSuperclass = (Type) stack.pop();
            if (!(genericSuperclass instanceof ParameterizedType)) {
                break;
            }
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            int indexOf = Arrays.asList(((Class) parameterizedType.getRawType()).getTypeParameters()).indexOf(typeVariable);
            if (indexOf <= -1) {
                break;
            }
            Type type = parameterizedType.getActualTypeArguments()[indexOf];
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            typeVariable = (TypeVariable) type;
        }
        throw new IllegalArgumentException(genericSuperclass + " does not specify the type parameter T of GenericType<T>");
    }

    public boolean equals(Object obj) {
        boolean z = this == obj;
        return (z || !(obj instanceof GenericType)) ? z : this.type.equals(((GenericType) obj).type);
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "GenericType{" + this.type.toString() + "}";
    }
}
