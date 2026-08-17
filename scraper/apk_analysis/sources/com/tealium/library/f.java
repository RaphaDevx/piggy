package com.tealium.library;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: PublishSettingsExtractor.java */
/* loaded from: classes2.dex */
final class f {
    static String a(String str) {
        Pattern compile = Pattern.compile("<script(.*?)>(.*?)</script>");
        Pattern compile2 = Pattern.compile(";? *var +[\\w]+ *= *");
        Matcher matcher = compile.matcher(str);
        String str2 = null;
        while (matcher.find() && str2 == null) {
            if (!matcher.group(1).toLowerCase(Locale.ROOT).contains("src")) {
                str2 = a(compile2, matcher.group(2));
            }
        }
        return str2;
    }

    private static String a(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        int i = -1;
        int i2 = -1;
        while (matcher.find()) {
            String group = matcher.group(0);
            if (group.toLowerCase(Locale.ROOT).contains("mps")) {
                i = str.indexOf(group) + group.length();
            } else if (i != -1 && i2 == -1) {
                i2 = str.indexOf(group);
            }
        }
        if (i == -1) {
            return null;
        }
        if (i2 == -1) {
            i2 = str.length();
        }
        return str.substring(i, i2);
    }
}
