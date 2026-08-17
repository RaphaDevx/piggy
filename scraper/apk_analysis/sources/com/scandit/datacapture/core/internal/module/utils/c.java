package com.scandit.datacapture.core.internal.module.utils;

import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public final class c {
    public final double a;
    public final LinkedHashMap b = new LinkedHashMap();
    public double c;
    public boolean d;

    public c(double d) {
        this.a = d;
    }

    public final void a(long j, long j2) {
        Long l = (Long) this.b.remove(new e(j));
        if (l != null) {
            long longValue = j2 - l.longValue();
            if (!this.d) {
                this.c = longValue;
                this.d = true;
            } else {
                double d = this.c;
                double d2 = this.a;
                this.c = ((1.0d - d2) * longValue) + (d * d2);
            }
        }
    }
}
