package com.beust.jcommander.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.internal.Lists;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class DefaultListConverter<T> implements IStringConverter<List<T>> {
    private final IStringConverter<T> converter;
    private final IParameterSplitter splitter;

    public DefaultListConverter(IParameterSplitter iParameterSplitter, IStringConverter<T> iStringConverter) {
        this.splitter = iParameterSplitter;
        this.converter = iStringConverter;
    }

    @Override // com.beust.jcommander.IStringConverter
    public List<T> convert(String str) {
        List<T> newArrayList = Lists.newArrayList();
        Iterator<String> it = this.splitter.split(str).iterator();
        while (it.hasNext()) {
            newArrayList.add(this.converter.convert(it.next()));
        }
        return newArrayList;
    }
}
