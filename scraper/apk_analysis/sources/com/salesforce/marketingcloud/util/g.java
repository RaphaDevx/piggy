package com.salesforce.marketingcloud.util;

import android.text.TextUtils;
import java.util.Collection;

/* loaded from: classes2.dex */
public final class g {
    private g() {
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T extends CharSequence> T a(T t, String str) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException(str);
        }
        return t;
    }

    public static <T extends Collection> T a(T t, String str) {
        if (t.isEmpty()) {
            throw new IllegalArgumentException(str);
        }
        return t;
    }

    public static boolean a(boolean z, String str) {
        if (z) {
            return true;
        }
        throw new IllegalArgumentException(str);
    }
}
