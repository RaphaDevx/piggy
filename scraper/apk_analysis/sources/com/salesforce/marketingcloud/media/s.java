package com.salesforce.marketingcloud.media;

import com.salesforce.marketingcloud.util.d;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class s {
    private static final int d = 20971520;
    private final File a;
    private final Object b = new Object();
    private com.salesforce.marketingcloud.util.d c;

    s(File file) {
        this.a = file;
    }

    private static String c(String str) {
        return com.salesforce.marketingcloud.util.j.e(str);
    }

    void a(String str, InputStream inputStream) throws IOException {
        b();
        String c = c(str);
        synchronized (this.b) {
            d.c a = this.c.a(c);
            try {
                com.salesforce.marketingcloud.util.e.a(inputStream, a.c(0));
                a.c();
            } finally {
                com.salesforce.marketingcloud.util.e.a((Closeable) inputStream);
            }
        }
    }

    void b(String str) throws IOException {
        b();
        this.c.d(c(str));
    }

    private void b() throws IOException {
        synchronized (this.b) {
            if (this.c == null) {
                this.c = com.salesforce.marketingcloud.util.d.a(this.a, 0, 1, 20971520L);
                this.b.notifyAll();
            }
        }
    }

    InputStream a(String str) throws IOException {
        InputStream a;
        b();
        String c = c(str);
        synchronized (this.b) {
            d.e b = this.c.b(c);
            a = b != null ? b.a(0) : null;
        }
        return a;
    }

    void a() throws IOException {
        b();
        this.c.c();
    }
}
