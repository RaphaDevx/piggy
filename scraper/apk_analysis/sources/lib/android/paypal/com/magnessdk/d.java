package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes4.dex */
public final class d {
    private d() {
    }

    public static String a(Context context, String str) {
        return context.getSharedPreferences(str, 0).getString(str, "");
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static boolean a(File file) {
        try {
            if (file.exists()) {
                e.a((Class<?>) d.class, 0, "deleting CachedConfigDataFromDisk");
                return file.delete();
            }
        } catch (Exception e) {
            e.a((Class<?>) d.class, 3, e);
        }
        return false;
    }

    public static boolean a(File file, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            fileOutputStream.write(str.getBytes("UTF-8"));
            o.a((Class<?>) d.class, fileOutputStream);
            return true;
        } catch (Exception e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            e.a((Class<?>) d.class, 3, e);
            o.a((Class<?>) d.class, fileOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            o.a((Class<?>) d.class, fileOutputStream);
            throw th;
        }
    }

    public static boolean a(String[] strArr, String str) {
        if (strArr != null && str != null) {
            boolean isEmpty = str.isEmpty();
            for (String str2 : strArr) {
                if ((!isEmpty ? new File(str2, str) : new File(str2)).exists()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String b(File file) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bArr);
            String str = new String(bArr, "UTF-8");
            o.a((Class<?>) d.class, randomAccessFile);
            return str;
        } catch (Exception e2) {
            e = e2;
            randomAccessFile2 = randomAccessFile;
            e.a((Class<?>) d.class, 3, e);
            o.a((Class<?>) d.class, randomAccessFile2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            o.a((Class<?>) d.class, randomAccessFile);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.io.File r5) {
        /*
            java.lang.Class<lib.android.paypal.com.magnessdk.d> r0 = lib.android.paypal.com.magnessdk.d.class
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
        L12:
            java.lang.String r5 = r3.readLine()     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            if (r5 == 0) goto L2d
            r1.append(r5)     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            goto L12
        L1c:
            r5 = move-exception
            goto L43
        L1e:
            r5 = move-exception
            goto L24
        L20:
            r5 = move-exception
            goto L42
        L22:
            r5 = move-exception
            r3 = r2
        L24:
            r4 = -403(0xfffffffffffffe6d, float:NaN)
            r1.append(r4)     // Catch: java.lang.Throwable -> L40
            r4 = 3
            lib.android.paypal.com.magnessdk.e.a(r0, r4, r5)     // Catch: java.lang.Throwable -> L40
        L2d:
            lib.android.paypal.com.magnessdk.o.a(r0, r3)
            java.lang.String r5 = r1.toString()
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3b
            goto L3f
        L3b:
            java.lang.String r2 = r1.toString()
        L3f:
            return r2
        L40:
            r5 = move-exception
            r2 = r3
        L42:
            r3 = r2
        L43:
            lib.android.paypal.com.magnessdk.o.a(r0, r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.d.c(java.io.File):java.lang.String");
    }
}
