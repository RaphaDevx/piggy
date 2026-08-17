package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public abstract class S {
    public static void a(boolean z, String str, t tVar) {
        String str2;
        if (z) {
            return;
        }
        int i = 0;
        Object[] objArr = {tVar};
        try {
            str2 = String.valueOf(tVar);
        } catch (Exception e) {
            String str3 = tVar.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(tVar));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str3, (Throwable) e);
            str2 = "<" + str3 + " threw " + e.getClass().getName() + ">";
        }
        objArr[0] = str2;
        StringBuilder sb = new StringBuilder(str.length() + 16);
        int i2 = 0;
        while (i < 1) {
            int indexOf = str.indexOf("%s", i2);
            if (indexOf == -1) {
                break;
            }
            sb.append((CharSequence) str, i2, indexOf);
            sb.append(objArr[i]);
            i2 = indexOf + 2;
            i++;
        }
        sb.append((CharSequence) str, i2, str.length());
        if (i < 1) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i3 = i + 1; i3 < 1; i3++) {
                sb.append(", ");
                sb.append(objArr[i3]);
            }
            sb.append(']');
        }
        throw new IllegalStateException(sb.toString());
    }
}
