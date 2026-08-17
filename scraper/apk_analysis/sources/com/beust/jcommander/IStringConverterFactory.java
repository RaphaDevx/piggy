package com.beust.jcommander;

/* loaded from: classes3.dex */
public interface IStringConverterFactory {
    Class<? extends IStringConverter<?>> getConverter(Class<?> cls);
}
