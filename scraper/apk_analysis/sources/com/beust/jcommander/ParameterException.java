package com.beust.jcommander;

/* loaded from: classes3.dex */
public class ParameterException extends RuntimeException {
    private JCommander jc;

    public ParameterException(Throwable th) {
        super(th);
    }

    public ParameterException(String str) {
        super(str);
    }

    public ParameterException(String str, Throwable th) {
        super(str, th);
    }

    public void setJCommander(JCommander jCommander) {
        this.jc = jCommander;
    }

    public JCommander getJCommander() {
        return this.jc;
    }

    public void usage() {
        JCommander jCommander = this.jc;
        if (jCommander != null) {
            jCommander.usage();
        }
    }
}
