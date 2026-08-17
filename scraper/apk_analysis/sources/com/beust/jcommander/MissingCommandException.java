package com.beust.jcommander;

/* loaded from: classes3.dex */
public class MissingCommandException extends ParameterException {
    private final String unknownCommand;

    public MissingCommandException(String str) {
        this(str, null);
    }

    public MissingCommandException(String str, String str2) {
        super(str);
        this.unknownCommand = str2;
    }

    public String getUnknownCommand() {
        return this.unknownCommand;
    }
}
