package org.apache.commons.lang3;

/* loaded from: classes4.dex */
public class CharSetUtils {
    public static boolean containsAny(String str, String... strArr) {
        if (!StringUtils.isEmpty(str) && !deepEmpty(strArr)) {
            CharSet charSet = CharSet.getInstance(strArr);
            for (char c : str.toCharArray()) {
                if (charSet.contains(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int count(String str, String... strArr) {
        if (StringUtils.isEmpty(str) || deepEmpty(strArr)) {
            return 0;
        }
        CharSet charSet = CharSet.getInstance(strArr);
        int i = 0;
        for (char c : str.toCharArray()) {
            if (charSet.contains(c)) {
                i++;
            }
        }
        return i;
    }

    private static boolean deepEmpty(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (StringUtils.isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static String delete(String str, String... strArr) {
        return (StringUtils.isEmpty(str) || deepEmpty(strArr)) ? str : modify(str, strArr, false);
    }

    public static String keep(String str, String... strArr) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty() || deepEmpty(strArr)) {
            return "";
        }
        return modify(str, strArr, true);
    }

    private static String modify(String str, String[] strArr, boolean z) {
        CharSet charSet = CharSet.getInstance(strArr);
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            if (charSet.contains(c) == z) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String squeeze(String str, String... strArr) {
        if (StringUtils.isEmpty(str) || deepEmpty(strArr)) {
            return str;
        }
        CharSet charSet = CharSet.getInstance(strArr);
        StringBuilder sb = new StringBuilder(str.length());
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        char c = charArray[0];
        sb.append(c);
        Character ch2 = null;
        Character ch3 = null;
        for (int i = 1; i < length; i++) {
            char c2 = charArray[i];
            if (c2 == c) {
                if (ch2 == null || c2 != ch2.charValue()) {
                    if (ch3 == null || c2 != ch3.charValue()) {
                        if (charSet.contains(c2)) {
                            ch2 = Character.valueOf(c2);
                        } else {
                            ch3 = Character.valueOf(c2);
                        }
                    }
                }
            }
            sb.append(c2);
            c = c2;
        }
        return sb.toString();
    }
}
