package com.beust.jcommander;

/* loaded from: classes3.dex */
public interface IUsageFormatter {
    String getCommandDescription(String str);

    void usage(String str);

    void usage(String str, StringBuilder sb);

    void usage(String str, StringBuilder sb, String str2);

    void usage(StringBuilder sb);

    void usage(StringBuilder sb, String str);
}
