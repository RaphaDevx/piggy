package com.beust.jcommander;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Sets;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class Parameterized {
    private Field field;
    private Method getter;
    private Method method;
    private ParametersDelegate parametersDelegate;
    private WrappedParameter wrappedParameter;

    public Parameterized(WrappedParameter wrappedParameter, ParametersDelegate parametersDelegate, Field field, Method method) {
        this.wrappedParameter = wrappedParameter;
        this.method = method;
        this.field = field;
        if (field != null) {
            if (parametersDelegate == null) {
                setFieldAccessible(field);
            } else {
                setFieldAccessibleWithoutFinalCheck(field);
            }
        }
        this.parametersDelegate = parametersDelegate;
    }

    private static void describeClassTree(Class<?> cls, Set<Class<?>> set) {
        if (cls == null || Object.class.equals(cls) || set.contains(cls)) {
            return;
        }
        set.add(cls);
        describeClassTree(cls.getSuperclass(), set);
        for (Class<?> cls2 : cls.getInterfaces()) {
            describeClassTree(cls2, set);
        }
    }

    private static Set<Class<?>> describeClassTree(Class<?> cls) {
        if (cls == null) {
            return Collections.emptySet();
        }
        Set<Class<?>> newLinkedHashSet = Sets.newLinkedHashSet();
        describeClassTree(cls, newLinkedHashSet);
        return newLinkedHashSet;
    }

    public static List<Parameterized> parseArg(Object obj) {
        List<Parameterized> newArrayList = Lists.newArrayList();
        for (Class<?> cls : describeClassTree(obj.getClass())) {
            for (Field field : cls.getDeclaredFields()) {
                Annotation annotation = field.getAnnotation(Parameter.class);
                Annotation annotation2 = field.getAnnotation(ParametersDelegate.class);
                Annotation annotation3 = field.getAnnotation(DynamicParameter.class);
                if (annotation != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((Parameter) annotation), null, field, null));
                } else if (annotation3 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((DynamicParameter) annotation3), null, field, null));
                } else if (annotation2 != null) {
                    newArrayList.add(new Parameterized(null, (ParametersDelegate) annotation2, field, null));
                }
            }
            for (Method method : cls.getDeclaredMethods()) {
                method.setAccessible(true);
                Annotation annotation4 = method.getAnnotation(Parameter.class);
                Annotation annotation5 = method.getAnnotation(ParametersDelegate.class);
                Annotation annotation6 = method.getAnnotation(DynamicParameter.class);
                if (annotation4 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((Parameter) annotation4), null, null, method));
                } else if (annotation6 != null) {
                    newArrayList.add(new Parameterized(new WrappedParameter((DynamicParameter) annotation6), null, null, method));
                } else if (annotation5 != null) {
                    newArrayList.add(new Parameterized(null, (ParametersDelegate) annotation5, null, method));
                }
            }
        }
        return newArrayList;
    }

    public WrappedParameter getWrappedParameter() {
        return this.wrappedParameter;
    }

    public Class<?> getType() {
        Method method = this.method;
        if (method != null) {
            return method.getParameterTypes()[0];
        }
        return this.field.getType();
    }

    public String getName() {
        Method method = this.method;
        if (method != null) {
            return method.getName();
        }
        return this.field.getName();
    }

    public Object get(Object obj) {
        try {
            if (this.method != null) {
                if (this.getter == null) {
                    setGetter(obj);
                }
                return this.getter.invoke(obj, null);
            }
            return this.field.get(obj);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            throw new ParameterException(e);
        } catch (NoSuchMethodException unused) {
            String name = this.method.getName();
            try {
                Field declaredField = this.method.getDeclaringClass().getDeclaredField(Character.toLowerCase(name.charAt(3)) + name.substring(4));
                if (declaredField == null) {
                    return null;
                }
                setFieldAccessible(declaredField);
                return declaredField.get(obj);
            } catch (IllegalAccessException | NoSuchFieldException unused2) {
                return null;
            }
        }
    }

    private void setGetter(Object obj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if ("Boolean".toLowerCase().equals(getType().getName())) {
            try {
                this.getter = obj.getClass().getMethod("is" + this.method.getName().substring(3), null);
                return;
            } catch (NoSuchMethodException unused) {
            }
        }
        this.getter = obj.getClass().getMethod("g" + this.method.getName().substring(1), null);
    }

    public int hashCode() {
        Field field = this.field;
        int hashCode = ((field == null ? 0 : field.hashCode()) + 31) * 31;
        Method method = this.method;
        return hashCode + (method != null ? method.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Parameterized parameterized = (Parameterized) obj;
        Field field = this.field;
        if (field == null) {
            if (parameterized.field != null) {
                return false;
            }
        } else if (!field.equals(parameterized.field)) {
            return false;
        }
        Method method = this.method;
        if (method == null) {
            if (parameterized.method != null) {
                return false;
            }
        } else if (!method.equals(parameterized.method)) {
            return false;
        }
        return true;
    }

    public boolean isDynamicParameter(Field field) {
        Method method = this.method;
        return method != null ? method.getAnnotation(DynamicParameter.class) != null : this.field.getAnnotation(DynamicParameter.class) != null;
    }

    private static void setFieldAccessible(Field field) {
        if (Modifier.isFinal(field.getModifiers())) {
            throw new ParameterException("Cannot use final field " + field.getDeclaringClass().getName() + "#" + field.getName() + " as a parameter; compile-time constant inlining may hide new values written to it.");
        }
        field.setAccessible(true);
    }

    private static void setFieldAccessibleWithoutFinalCheck(Field field) {
        field.setAccessible(true);
    }

    private static String errorMessage(Method method, Exception exc) {
        return "Could not invoke " + method + "\n    Reason: " + exc.getMessage();
    }

    public void set(Object obj, Object obj2) {
        try {
            Method method = this.method;
            if (method != null) {
                method.invoke(obj, obj2);
            } else {
                this.field.set(obj, obj2);
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new ParameterException(errorMessage(this.method, e));
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof ParameterException) {
                throw ((ParameterException) e2.getTargetException());
            }
            throw new ParameterException(errorMessage(this.method, e2));
        }
    }

    public ParametersDelegate getDelegateAnnotation() {
        return this.parametersDelegate;
    }

    public Type getGenericType() {
        Method method = this.method;
        if (method != null) {
            return method.getGenericParameterTypes()[0];
        }
        return this.field.getGenericType();
    }

    public Parameter getParameter() {
        return this.wrappedParameter.getParameter();
    }

    public Type findFieldGenericType() {
        if (this.method == null && (this.field.getGenericType() instanceof ParameterizedType)) {
            Type type = ((ParameterizedType) this.field.getGenericType()).getActualTypeArguments()[0];
            if (type instanceof Class) {
                return type;
            }
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length > 0) {
                    return wildcardType.getLowerBounds()[0];
                }
                if (wildcardType.getUpperBounds().length > 0) {
                    return wildcardType.getUpperBounds()[0];
                }
            }
        }
        return null;
    }

    public boolean isDynamicParameter() {
        return this.wrappedParameter.getDynamicParameter() != null;
    }
}
