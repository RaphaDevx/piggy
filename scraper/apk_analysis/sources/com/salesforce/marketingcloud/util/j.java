package com.salesforce.marketingcloud.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.registration.Registration;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import kotlin.UByte;

/* loaded from: classes2.dex */
public final class j {
    private static final String d = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String e = "^|^";
    private static final String f = "\\^\\|\\^";
    private static Boolean i;
    public static final Locale a = Locale.US;
    public static final Charset b = Charset.forName("UTF-8");
    static final String c = com.salesforce.marketingcloud.g.a("Utils");
    private static final char[] g = "0123456789ABCDEF".toCharArray();
    private static final TimeZone h = TimeZone.getTimeZone("UTC");

    private j() {
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d, a);
        simpleDateFormat.setTimeZone(h);
        return simpleDateFormat.format(date);
    }

    private static boolean b(Context context) {
        try {
        } catch (PackageManager.NameNotFoundException unused) {
            com.salesforce.marketingcloud.g.e(c, "Failed to determine if app was in debug mode.", new Object[0]);
        }
        return (context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 2) != 0;
    }

    public static boolean c() {
        return true;
    }

    public static Date d(String str) throws ParseException {
        if (str == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d, a);
        simpleDateFormat.setTimeZone(h);
        return simpleDateFormat.parse(str);
    }

    public static boolean f(String str) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d, Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            simpleDateFormat.parse(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean c(Context context) {
        try {
            Class<?> cls = Class.forName(context.getApplicationContext().getClass().getName().replace("." + context.getApplicationContext().getClass().getSimpleName(), "") + ".BuildConfig");
            Field field = cls.getField("DEBUG");
            field.setAccessible(true);
            boolean z = field.getBoolean(null);
            com.salesforce.marketingcloud.g.c(c, "isDebugBuild set to %s for clazz %s", Boolean.valueOf(z), cls.getCanonicalName());
            return z;
        } catch (Exception e2) {
            boolean b2 = b(context);
            com.salesforce.marketingcloud.g.a(c, "isDebugBuild determination failed with Exception [%s].  isDebugBuild set to: %s", e2.getMessage(), Boolean.valueOf(b2));
            return b2;
        }
    }

    public static String e(String str) {
        try {
            StringBuilder sb = new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(str.getBytes(b))).toString(16));
            while (sb.length() < 64) {
                sb.insert(0, '0');
            }
            return sb.toString();
        } catch (Throwable th) {
            com.salesforce.marketingcloud.g.b(c, th, "sha-256 failed", new Object[0]);
            return "";
        }
    }

    public static boolean a(Context context) {
        if (i == null) {
            i = Boolean.valueOf(c(context));
        }
        return i.booleanValue();
    }

    public static Map<String, String> b(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(f);
            for (int i2 = 0; i2 < split.length; i2 += 2) {
                int i3 = i2 + 1;
                hashMap.put(split[i2], i3 < split.length ? split[i3] : "");
            }
        }
        return hashMap;
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(e);
        sb.append(str2);
        sb.append(e);
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return "";
        }
        HashMap hashMap = new HashMap(map);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : hashMap.entrySet()) {
            a(sb, (String) entry.getKey(), (String) entry.getValue());
        }
        return sb.toString();
    }

    public static int b() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 1000;
    }

    public static int b(int i2) {
        return Build.VERSION.SDK_INT >= 31 ? i2 | 33554432 : i2;
    }

    public static Set<String> c(String str) {
        TreeSet treeSet = new TreeSet();
        if (str != null && !TextUtils.isEmpty(str)) {
            for (String str2 : str.split(f)) {
                if (str2 != null && !str2.isEmpty()) {
                    treeSet.add(str2);
                }
            }
        }
        return treeSet;
    }

    public static synchronized String a(Set<String> set) {
        synchronized (j.class) {
            if (set == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(e);
            }
            return sb.toString();
        }
    }

    static String a(String str) {
        return a(str, "SHA-256", "UTF-8");
    }

    public static String a(Registration registration) {
        return a(m.a(registration), "SHA-256", "UTF-8");
    }

    public static boolean a(Registration registration, Registration registration2) {
        return !a(registration).equals(a(registration2));
    }

    private static String a(String str, String str2, String str3) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            byte[] bytes = str.getBytes(str3);
            messageDigest.update(bytes, 0, bytes.length);
            return a(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            int i3 = b2 & UByte.MAX_VALUE;
            int i4 = i2 * 2;
            char[] cArr2 = g;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    private static boolean a() {
        return MarketingCloudSdk.isReady() || MarketingCloudSdk.isInitializing();
    }

    public static boolean a(long j, long j2) {
        if (j > 0) {
            long min = Math.min(j2, j);
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = currentTimeMillis;
            boolean z = false;
            while (true) {
                long j4 = currentTimeMillis + j;
                if (j4 <= j3 || z || a()) {
                    break;
                }
                long j5 = j3 + min > j4 ? j4 - j3 : min;
                try {
                    com.salesforce.marketingcloud.g.d(c, "Marketing Cloud SDK is not yet initializing.  Trying again in %sms. %s", Long.valueOf(j5), Long.valueOf(j3 - currentTimeMillis));
                    Thread.sleep(j5);
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(c, e2, "Encountered exception while waiting for SDK initialization to be triggered by the application.", new Object[0]);
                    z = true;
                }
                j3 = System.currentTimeMillis();
            }
        }
        return a();
    }

    public static int a(int i2) {
        return Build.VERSION.SDK_INT >= 31 ? i2 | AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : i2;
    }
}
