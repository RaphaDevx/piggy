package com.salesforce.marketingcloud.analytics;

import java.util.List;

/* loaded from: classes2.dex */
public final class c {
    private c() {
    }

    public static String[] a(String str) {
        return str.split("\\s*,\\s*");
    }

    public static String a(List<b> list) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (b bVar : list) {
            if (bVar != null) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(bVar.d());
            }
        }
        return sb.toString();
    }
}
