package com.beust.jcommander;

/* loaded from: classes3.dex */
public interface IValueValidator<T> {
    void validate(String str, T t) throws ParameterException;
}
