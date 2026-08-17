package com.salesforce.marketingcloud.util;

import android.os.Build;

/* loaded from: classes2.dex */
public final class a {
    private static final String a = "Amazon";

    private a() {
    }

    public static boolean a() {
        return Build.MANUFACTURER.equalsIgnoreCase(a);
    }
}
