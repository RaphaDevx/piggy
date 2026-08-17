package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;

/* loaded from: classes3.dex */
public abstract class BaseConverter<T> implements IStringConverter<T> {
    private String optionName;

    public BaseConverter(String str) {
        this.optionName = str;
    }

    public String getOptionName() {
        return this.optionName;
    }

    protected String getErrorString(String str, String str2) {
        return "\"" + getOptionName() + "\": couldn't convert \"" + str + "\" to " + str2;
    }
}
