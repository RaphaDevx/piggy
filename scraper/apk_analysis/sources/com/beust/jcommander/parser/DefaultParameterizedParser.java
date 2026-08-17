package com.beust.jcommander.parser;

import com.beust.jcommander.IParameterizedParser;
import com.beust.jcommander.Parameterized;
import java.util.List;

/* loaded from: classes3.dex */
public class DefaultParameterizedParser implements IParameterizedParser {
    @Override // com.beust.jcommander.IParameterizedParser
    public List<Parameterized> parseArg(Object obj) {
        return Parameterized.parseArg(obj);
    }
}
