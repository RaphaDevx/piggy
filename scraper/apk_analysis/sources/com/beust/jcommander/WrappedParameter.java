package com.beust.jcommander;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class WrappedParameter {
    private DynamicParameter dynamicParameter;
    private Parameter parameter;

    public WrappedParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public WrappedParameter(DynamicParameter dynamicParameter) {
        this.dynamicParameter = dynamicParameter;
    }

    public Parameter getParameter() {
        return this.parameter;
    }

    public DynamicParameter getDynamicParameter() {
        return this.dynamicParameter;
    }

    public int arity() {
        Parameter parameter = this.parameter;
        if (parameter != null) {
            return parameter.arity();
        }
        return 1;
    }

    public boolean hidden() {
        Parameter parameter = this.parameter;
        return parameter != null ? parameter.hidden() : this.dynamicParameter.hidden();
    }

    public boolean required() {
        Parameter parameter = this.parameter;
        return parameter != null ? parameter.required() : this.dynamicParameter.required();
    }

    public boolean password() {
        Parameter parameter = this.parameter;
        if (parameter != null) {
            return parameter.password();
        }
        return false;
    }

    public String[] names() {
        Parameter parameter = this.parameter;
        return parameter != null ? parameter.names() : this.dynamicParameter.names();
    }

    public boolean variableArity() {
        Parameter parameter = this.parameter;
        if (parameter != null) {
            return parameter.variableArity();
        }
        return false;
    }

    public int order() {
        Parameter parameter = this.parameter;
        return parameter != null ? parameter.order() : this.dynamicParameter.order();
    }

    public Class<? extends IParameterValidator>[] validateWith() {
        Parameter parameter = this.parameter;
        return parameter != null ? parameter.validateWith() : this.dynamicParameter.validateWith();
    }

    public Class<? extends IValueValidator>[] validateValueWith() {
        Parameter parameter = this.parameter;
        if (parameter != null) {
            return parameter.validateValueWith();
        }
        return this.dynamicParameter.validateValueWith();
    }

    public boolean echoInput() {
        Parameter parameter = this.parameter;
        if (parameter != null) {
            return parameter.echoInput();
        }
        return false;
    }

    public void addValue(Parameterized parameterized, Object obj, Object obj2) {
        try {
            addValue(parameterized, obj, obj2, null);
        } catch (IllegalAccessException e) {
            throw new ParameterException("Couldn't set " + obj + " to " + obj2, e);
        }
    }

    public void addValue(Parameterized parameterized, Object obj, Object obj2, Field field) throws IllegalAccessException {
        if (this.parameter != null) {
            if (field != null) {
                field.set(obj, obj2);
                return;
            } else {
                parameterized.set(obj, obj2);
                return;
            }
        }
        String assignment = this.dynamicParameter.assignment();
        String obj3 = obj2.toString();
        int indexOf = obj3.indexOf(assignment);
        if (indexOf == -1) {
            throw new ParameterException("Dynamic parameter expected a value of the form a" + assignment + "b but got:" + obj3);
        }
        callPut(obj, parameterized, obj3.substring(0, indexOf), obj3.substring(indexOf + 1));
    }

    private void callPut(Object obj, Parameterized parameterized, String str, String str2) {
        try {
            findPut(parameterized.getType()).invoke(parameterized.get(obj), str, str2);
        } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Method findPut(Class<?> cls) throws SecurityException, NoSuchMethodException {
        return cls.getMethod("put", Object.class, Object.class);
    }

    public String getAssignment() {
        DynamicParameter dynamicParameter = this.dynamicParameter;
        return dynamicParameter != null ? dynamicParameter.assignment() : "";
    }

    public boolean isHelp() {
        Parameter parameter = this.parameter;
        return parameter != null && parameter.help();
    }

    public boolean isNonOverwritableForced() {
        Parameter parameter = this.parameter;
        return parameter != null && parameter.forceNonOverwritable();
    }
}
