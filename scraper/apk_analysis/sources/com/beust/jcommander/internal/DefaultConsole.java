package com.beust.jcommander.internal;

import com.beust.jcommander.ParameterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/* loaded from: classes3.dex */
public class DefaultConsole implements Console {
    private final PrintStream target;

    public DefaultConsole(PrintStream printStream) {
        this.target = printStream;
    }

    public DefaultConsole() {
        this.target = System.out;
    }

    @Override // com.beust.jcommander.internal.Console
    public void print(String str) {
        this.target.print(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public void println(String str) {
        this.target.println(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public char[] readPassword(boolean z) {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
        } catch (IOException e) {
            throw new ParameterException(e);
        }
    }
}
