package com.salesforce.marketingcloud.internal;

import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class i implements Runnable {
    public final String b;

    public i(String str, Object... objArr) {
        this.b = "mcsdk_" + String.format(Locale.US, str, objArr);
    }

    protected abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            a();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
