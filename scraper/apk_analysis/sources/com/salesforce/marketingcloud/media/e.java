package com.salesforce.marketingcloud.media;

import java.util.Iterator;

/* loaded from: classes2.dex */
class e implements Runnable {
    final o b;
    final d c;
    final h d;
    private Exception e;

    e(h hVar, d dVar) {
        this.b = dVar.c();
        this.d = hVar;
        this.c = dVar;
    }

    Exception a() {
        return this.e;
    }

    boolean b() {
        return this.e != null;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                Thread.currentThread().setName("CacheCleaner - Cleaning");
                s a = this.c.a();
                Iterator<String> it = this.c.d().iterator();
                while (it.hasNext()) {
                    a.b(it.next());
                }
                a.a();
            } catch (Exception e) {
                this.e = e;
            }
            Thread.currentThread().setName("CacheCleaner - Idle");
            this.d.a(this);
        } catch (Throwable th) {
            Thread.currentThread().setName("CacheCleaner - Idle");
            throw th;
        }
    }
}
