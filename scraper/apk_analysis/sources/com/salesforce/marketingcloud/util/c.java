package com.salesforce.marketingcloud.util;

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
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class c {
    private static final String a = com.salesforce.marketingcloud.g.a("DeviceData");
    private static final String b = "SFMCDeviceUUID";
    static volatile String c;

    private c() {
    }

    public static String a(Context context, String str) {
        if (c == null) {
            synchronized (c.class) {
                c = b(context, str);
            }
        }
        return c;
    }

    private static String b(Context context, String str) {
        File file = new File(context.getNoBackupFilesDir(), b);
        String a2 = file.exists() ? a(file) : null;
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String str2 = a;
        com.salesforce.marketingcloud.g.a(str2, "Checking SharedPreferences for deviceId", new Object[0]);
        String a3 = a(context);
        if (TextUtils.isEmpty(a3)) {
            com.salesforce.marketingcloud.g.a(str2, "Checking pre-lollipop location for deviceId", new Object[0]);
            File file2 = new File(context.getFilesDir(), b);
            if (file2.exists()) {
                a3 = a(file2);
                e.b(file2);
            }
        }
        if (!TextUtils.isEmpty(a3)) {
            str = a3;
        } else if (str != null) {
            com.salesforce.marketingcloud.g.a(str2, "Using registrationId as deviceId", new Object[0]);
        } else {
            com.salesforce.marketingcloud.g.a(str2, "Generating/Storing new deviceId", new Object[0]);
            str = a();
        }
        a(file, str);
        return str;
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
                str = new BufferedReader(new InputStreamReader((InputStream) r1, j.b)).readLine();
                closeable = r1;
            } catch (Exception unused) {
                try {
                    com.salesforce.marketingcloud.g.b(a, "Failed to read device id from file: ", file.getAbsolutePath());
                    closeable = r1;
                    e.a(closeable);
                    return str;
                } catch (Throwable th) {
                    th = th;
                    str = r1;
                    r1 = str;
                    e.a((Closeable) r1);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                e.a((Closeable) r1);
                throw th;
            }
        } catch (Exception unused2) {
            r1 = 0;
        } catch (Throwable th3) {
            th = th3;
            r1 = str;
            e.a((Closeable) r1);
            throw th;
        }
        e.a(closeable);
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    private static void a(File file, String str) {
        ?? r1;
        ?? r12;
        Charset charset = null;
        try {
            try {
                r1 = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                r1 = charset;
            }
        } catch (Exception unused) {
        }
        try {
            charset = j.b;
            r1.write(str.getBytes(charset));
            r12 = r1;
        } catch (Exception unused2) {
            charset = r1;
            com.salesforce.marketingcloud.g.b(a, "Failed to write device id to file: ", file.getAbsolutePath());
            r12 = charset;
            e.a((Closeable) r12);
        } catch (Throwable th2) {
            th = th2;
            e.a((Closeable) r1);
            throw th;
        }
        e.a((Closeable) r12);
    }

    private static String a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("_et_default_shared_preferences", 0);
        String string = sharedPreferences.getString("id", null);
        if (string != null) {
            sharedPreferences.edit().remove("id").apply();
        }
        return string;
    }

    private static String a() {
        return j.a(String.format(Locale.ENGLISH, "%s%d", UUID.randomUUID().toString(), Long.valueOf(System.currentTimeMillis())));
    }
}
