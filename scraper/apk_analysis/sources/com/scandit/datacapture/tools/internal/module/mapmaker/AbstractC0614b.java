package com.scandit.datacapture.tools.internal.module.mapmaker;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0614b {
    public static String a(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt >= 'A' && charAt <= 'Z') {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (c >= 'A' && c <= 'Z') {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }
}
