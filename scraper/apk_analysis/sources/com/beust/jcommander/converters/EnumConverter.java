package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import java.lang.Enum;
import java.util.EnumSet;

/* loaded from: classes3.dex */
public class EnumConverter<T extends Enum<T>> implements IStringConverter<T> {
    private final Class<T> clazz;
    private final String optionName;

    public EnumConverter(String str, Class<T> cls) {
        this.optionName = str;
        this.clazz = cls;
    }

    @Override // com.beust.jcommander.IStringConverter
    public T convert(String str) {
        try {
            try {
                return (T) Enum.valueOf(this.clazz, str);
            } catch (IllegalArgumentException unused) {
                return (T) Enum.valueOf(this.clazz, str.toUpperCase());
            }
        } catch (Exception unused2) {
            throw new ParameterException("Invalid value for " + this.optionName + " parameter. Allowed values:" + EnumSet.allOf(this.clazz));
        }
    }
}
