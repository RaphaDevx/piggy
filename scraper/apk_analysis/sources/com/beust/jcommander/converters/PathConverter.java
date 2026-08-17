package com.beust.jcommander.converters;

import com.beust.jcommander.ParameterException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/* loaded from: classes3.dex */
public class PathConverter extends BaseConverter<Path> {
    public PathConverter(String str) {
        super(str);
    }

    @Override // com.beust.jcommander.IStringConverter
    public Path convert(String str) {
        try {
            return Paths.get(str, new String[0]);
        } catch (InvalidPathException unused) {
            throw new ParameterException(this.getErrorString(escapeUnprintable(str), "a path"));
        }
    }

    private static String escapeUnprintable(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c < ' ') {
                sb.append("\\u");
                sb.append(String.format("%04X", Integer.valueOf(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
