package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes3.dex */
public class BooleanConverter extends BaseConverter<Boolean> {
    public BooleanConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Boolean convert(String str) {
        if (BooleanUtils.FALSE.equalsIgnoreCase(str) || "true".equalsIgnoreCase(str)) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        throw new ParameterException(getErrorString(str, "a boolean"));
    }
}
