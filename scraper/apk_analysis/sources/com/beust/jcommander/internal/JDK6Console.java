package com.beust.jcommander.internal;

import com.beust.jcommander.ParameterException;
import java.io.PrintWriter;

/* loaded from: classes3.dex */
public class JDK6Console implements Console {
    private Object console;
    private PrintWriter writer;

    public JDK6Console(Object obj) throws Exception {
        this.console = obj;
        this.writer = (PrintWriter) obj.getClass().getDeclaredMethod("writer", null).invoke(obj, null);
    }

    @Override // com.beust.jcommander.internal.Console
    public void print(String str) {
        this.writer.print(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public void println(String str) {
        this.writer.println(str);
    }

    @Override // com.beust.jcommander.internal.Console
    public char[] readPassword(boolean z) {
        try {
            this.writer.flush();
            if (z) {
                return ((String) this.console.getClass().getDeclaredMethod("readLine", null).invoke(this.console, null)).toCharArray();
            }
            return (char[]) this.console.getClass().getDeclaredMethod("readPassword", null).invoke(this.console, null);
        } catch (Exception e) {
            throw new ParameterException(e);
        }
    }
}
