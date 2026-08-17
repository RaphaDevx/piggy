package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;

/* loaded from: classes3.dex */
public class CharArrayConverter implements IStringConverter<char[]> {
    @Override // com.beust.jcommander.IStringConverter
    public char[] convert(String str) {
        return str.toCharArray();
    }
}
