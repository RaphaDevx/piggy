package ch.coop.apidia.appGateway.cards;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class StringUtil {
    public static boolean containsIgnoreCase(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str == null && str2 == null) {
                return true;
            }
            if (str != null && str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String join(String[] strArr, String str) {
        int length = strArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strArr[0]);
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static String join(Collection<String> collection, String str) {
        Iterator<String> it = collection.iterator();
        StringBuilder sb = new StringBuilder();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
        }
        return sb.toString();
    }
}
