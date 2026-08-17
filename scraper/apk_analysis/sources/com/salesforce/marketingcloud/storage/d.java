package com.salesforce.marketingcloud.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class d {
    final File b;
    private final Context d;
    private final SharedPreferences e;
    private final String f;
    private final com.salesforce.marketingcloud.internal.n g;
    private String h;
    final Object a = new Object();
    private final Object c = new Object();
    private boolean i = false;

    class a extends Thread {
        a(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            d.this.b();
        }
    }

    class b extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            FileOutputStream fileOutputStream;
            synchronized (d.this.a) {
                FileOutputStream fileOutputStream2 = null;
                FileOutputStream fileOutputStream3 = null;
                try {
                    try {
                        fileOutputStream = new FileOutputStream(d.this.b);
                    } catch (Exception unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                }
                try {
                    String str = this.c;
                    fileOutputStream.write(str != null ? str.getBytes(com.salesforce.marketingcloud.util.j.b) : new byte[0]);
                    String str2 = l.f;
                    com.salesforce.marketingcloud.g.d(str2, "Gdpr mode [%s] written to file.", this.c);
                    fileOutputStream2 = str2;
                } catch (Exception unused2) {
                    fileOutputStream3 = fileOutputStream;
                    com.salesforce.marketingcloud.g.b(l.f, "Failed to write gdpr mode to file: ", d.this.b.getAbsolutePath());
                    fileOutputStream = fileOutputStream3;
                    fileOutputStream2 = fileOutputStream3;
                    com.salesforce.marketingcloud.util.e.a(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    com.salesforce.marketingcloud.util.e.a(fileOutputStream);
                    throw th;
                }
                com.salesforce.marketingcloud.util.e.a(fileOutputStream);
            }
        }
    }

    d(Context context, SharedPreferences sharedPreferences, String str, com.salesforce.marketingcloud.internal.n nVar) {
        this.d = context;
        this.e = sharedPreferences;
        this.g = nVar;
        String str2 = str + "_SFMC_PrivacyMode";
        this.f = str2;
        this.b = new File(context.getNoBackupFilesDir(), str2);
        c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileInputStream, java.io.InputStream] */
    private static String a(File file) {
        ?? r1;
        Closeable closeable;
        String str = null;
        try {
            r1 = new FileInputStream(file);
            try {
                str = new BufferedReader(new InputStreamReader((InputStream) r1, com.salesforce.marketingcloud.util.j.b)).readLine();
                closeable = r1;
            } catch (Exception unused) {
                try {
                    com.salesforce.marketingcloud.g.b(l.f, "Failed to read gdpr mode from file: ", file.getAbsolutePath());
                    closeable = r1;
                    com.salesforce.marketingcloud.util.e.a(closeable);
                    return str;
                } catch (Throwable th) {
                    th = th;
                    str = r1;
                    r1 = str;
                    com.salesforce.marketingcloud.util.e.a((Closeable) r1);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                com.salesforce.marketingcloud.util.e.a((Closeable) r1);
                throw th;
            }
        } catch (Exception unused2) {
            r1 = 0;
        } catch (Throwable th3) {
            th = th3;
            r1 = str;
            com.salesforce.marketingcloud.util.e.a((Closeable) r1);
            throw th;
        }
        com.salesforce.marketingcloud.util.e.a(closeable);
        return str;
    }

    private void c() {
        synchronized (this.c) {
            this.i = false;
        }
        new a("gdpr_file_load").start();
    }

    void b() {
        synchronized (this.c) {
            if (this.i) {
                return;
            }
            String str = null;
            if (this.b.exists()) {
                String a2 = a(this.b);
                if (!TextUtils.isEmpty(a2)) {
                    str = a2;
                }
            } else {
                String str2 = l.f;
                com.salesforce.marketingcloud.g.a(str2, "Checking SharedPreferences for gdpr mode", new Object[0]);
                str = this.e.getString("cc_state", null);
                if (str != null) {
                    this.e.edit().remove("cc_state").apply();
                } else {
                    com.salesforce.marketingcloud.g.a(str2, "Checking pre-lollipop location for gdpr mode", new Object[0]);
                    File file = new File(this.d.getFilesDir(), this.f);
                    if (file.exists()) {
                        str = a(file);
                        com.salesforce.marketingcloud.util.e.b(file);
                    }
                }
                c(str);
            }
            synchronized (this.c) {
                this.h = str;
                this.i = true;
                this.c.notifyAll();
            }
        }
    }

    void c(String str) {
        this.g.b().execute(new b("storing_gdpr", new Object[0], str));
    }

    public String a(String str) {
        synchronized (this.c) {
            a();
            String str2 = this.h;
            if (str2 != null) {
                str = str2;
            }
        }
        return str;
    }

    private void a() {
        while (!this.i) {
            try {
                this.c.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void b(String str) {
        synchronized (this.c) {
            com.salesforce.marketingcloud.g.d(l.f, "Updating gdpr mode: %s", str);
            this.h = str;
            c(str);
        }
    }
}
