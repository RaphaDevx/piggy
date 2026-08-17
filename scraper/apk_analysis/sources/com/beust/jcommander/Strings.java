package com.beust.jcommander;

import java.util.List;

/* loaded from: classes3.dex */
public class Strings {
    public static boolean isStringEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean startsWith(String str, String str2, boolean z) {
        if (z) {
            return str.startsWith(str2);
        }
        return str.toLowerCase().startsWith(str2.toLowerCase());
    }

    public static String join(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < list.size()) {
            sb.append(list.get(i));
            i++;
            if (i < list.size()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String join(String str, Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < objArr.length) {
            sb.append(objArr[i]);
            i++;
            if (i < objArr.length) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
