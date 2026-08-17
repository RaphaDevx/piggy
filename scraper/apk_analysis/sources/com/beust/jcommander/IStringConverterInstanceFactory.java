package com.beust.jcommander;

/* loaded from: classes3.dex */
public interface IStringConverterInstanceFactory {
    IStringConverter<?> getConverterInstance(Parameter parameter, Class<?> cls, String str);
}
