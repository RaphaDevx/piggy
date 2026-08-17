package com.tealium.lifecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import com.tealium.library.Tealium;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: Model.java */
/* loaded from: classes2.dex */
final class a {
    private final SimpleDateFormat a;
    private final String b;
    private final SharedPreferences c;
    private final Calendar d;
    private final String e;
    private final Date f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private long m;
    private long n;
    private long o;
    private long p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;

    private a(Tealium.Config config, Calendar calendar, String str) {
        SharedPreferences sharedPreferences = config.getApplication().getSharedPreferences(b(config), 0);
        this.c = sharedPreferences;
        this.b = str;
        this.d = calendar;
        this.f = new Date(Long.MIN_VALUE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        this.a = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.o = Long.MIN_VALUE;
        this.m = sharedPreferences.getLong("timestamp_update", Long.MIN_VALUE);
        this.p = sharedPreferences.getLong("timestamp_last_wake", Long.MIN_VALUE);
        this.n = sharedPreferences.getLong("timestamp_first_launch", Long.MIN_VALUE);
        this.s = sharedPreferences.getInt("count_launch", 0);
        this.t = sharedPreferences.getInt("count_sleep", 0);
        this.u = sharedPreferences.getInt("count_wake", 0);
        this.v = sharedPreferences.getInt("count_total_crash", 0);
        this.w = sharedPreferences.getInt("count_total_launch", 0);
        this.x = sharedPreferences.getInt("count_total_sleep", 0);
        this.y = sharedPreferences.getInt("count_total_wake", 0);
        this.q = sharedPreferences.getInt("total_seconds_awake", 0);
        this.e = Integer.toString(a(sharedPreferences));
    }

    static boolean b(int i) {
        return (i & 1) == 1;
    }

    static boolean c(int i) {
        return (i & 2) == 2;
    }

    boolean a(long j) {
        if (this.n != Long.MIN_VALUE) {
            return false;
        }
        this.n = j;
        this.c.edit().putLong("timestamp_first_launch", j).putLong("timestamp_last_launch", j).putLong("timestamp_last_wake", j).apply();
        return true;
    }

    boolean b(long j) {
        if (this.b != null) {
            String string = this.c.getString("app_version", null);
            if (string == null) {
                this.c.edit().putString("app_version", this.b).apply();
            } else if (!this.b.equals(string)) {
                this.c.edit().remove("count_launch").remove("count_sleep").remove("count_wake").putLong("timestamp_update", j).putString("app_version", this.b).apply();
                this.s = 0;
                this.t = 0;
                this.u = 0;
                this.m = j;
                return true;
            }
        }
        return false;
    }

    int c() {
        return this.v;
    }

    public int d() {
        return this.w;
    }

    int e() {
        return this.x;
    }

    int f() {
        return this.y;
    }

    int g() {
        return this.u;
    }

    String h() {
        String str = this.h;
        if (str != null) {
            return str;
        }
        this.f.setTime(this.n);
        String format = this.a.format(this.f);
        this.h = format;
        return format;
    }

    String i() {
        String str = this.i;
        if (str != null) {
            return str;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ROOT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.f.setTime(this.n);
        String format = simpleDateFormat.format(this.f);
        this.i = format;
        return format;
    }

    String j() {
        String str = this.j;
        if (str != null) {
            return str;
        }
        String a = a("timestamp_last_launch", this.o);
        this.j = a;
        return a;
    }

    String k() {
        String str = this.k;
        if (str != null) {
            return str;
        }
        String a = a("timestamp_last_sleep", Long.MIN_VALUE);
        this.k = a;
        return a;
    }

    String l() {
        String str = this.l;
        if (str != null) {
            return str;
        }
        String a = a("timestamp_last_wake", this.o);
        this.l = a;
        return a;
    }

    String m() {
        return this.e;
    }

    String n() {
        String str = this.g;
        if (str != null) {
            return str;
        }
        long j = this.m;
        if (j == Long.MIN_VALUE) {
            return null;
        }
        this.f.setTime(j);
        String format = this.a.format(this.f);
        this.g = format;
        return format;
    }

    long o() {
        return this.n;
    }

    long p() {
        return this.p;
    }

    long q() {
        return this.o;
    }

    long r() {
        return this.m;
    }

    int s() {
        return this.q;
    }

    void t() {
        SharedPreferences.Editor edit = this.c.edit();
        int i = this.v + 1;
        this.v = i;
        edit.putInt("count_total_crash", i).apply();
    }

    void u() {
        SharedPreferences.Editor edit = this.c.edit();
        int i = this.s + 1;
        this.s = i;
        SharedPreferences.Editor putInt = edit.putInt("count_launch", i);
        int i2 = this.w + 1;
        this.w = i2;
        putInt.putInt("count_total_launch", i2).apply();
    }

    void v() {
        SharedPreferences.Editor edit = this.c.edit();
        int i = this.t + 1;
        this.t = i;
        SharedPreferences.Editor putInt = edit.putInt("count_sleep", i);
        int i2 = this.x + 1;
        this.x = i2;
        putInt.putInt("count_total_sleep", i2).apply();
    }

    void w() {
        SharedPreferences.Editor edit = this.c.edit();
        int i = this.u + 1;
        this.u = i;
        SharedPreferences.Editor putInt = edit.putInt("count_wake", i);
        int i2 = this.y + 1;
        this.y = i2;
        putInt.putInt("count_total_wake", i2).apply();
    }

    int c(long j) {
        if (this.d.getTimeInMillis() != j) {
            this.d.setTimeInMillis(j);
        }
        return this.d.get(7);
    }

    int d(long j) {
        if (this.d.getTimeInMillis() != j) {
            this.d.setTimeInMillis(j);
        }
        return this.d.get(11);
    }

    void e(long j) {
        this.f.setTime(j);
        this.j = this.a.format(this.f);
        this.c.edit().putLong("timestamp_last_launch", j).apply();
    }

    void f(long j) {
        this.f.setTime(j);
        this.k = this.a.format(this.f);
        this.c.edit().putLong("timestamp_last_sleep", j).apply();
    }

    void g(long j) {
        this.p = j;
        this.f.setTime(j);
        this.l = this.a.format(this.f);
        this.c.edit().putLong("timestamp_last_wake", j).apply();
    }

    int a(long j, long j2) {
        this.d.setTimeInMillis(j);
        long j3 = this.d.get(2);
        int i = 1;
        long j4 = this.d.get(1);
        long j5 = this.d.get(5);
        this.d.setTimeInMillis(j2);
        long j6 = this.d.get(2);
        long j7 = this.d.get(1);
        long j8 = this.d.get(5);
        if (j4 == j7 && j3 == j6) {
            i = 0;
        }
        return (i == 0 && j5 == j8) ? i : i | 2;
    }

    boolean a(String str) {
        String string = this.c.getString("last_event", null);
        boolean z = false;
        if (string == null) {
            return false;
        }
        boolean z2 = "launch".equals(string) || "wake".equals(string);
        boolean z3 = "launch".equals(str) || "wake".equals(str);
        if (z2 && z3) {
            z = true;
        }
        if (z) {
            t();
        }
        return z;
    }

    int b() {
        return this.t;
    }

    void b(String str) {
        this.c.edit().putString("last_event", str).apply();
    }

    private static String b(Tealium.Config config) {
        return "tealium.lifecycle." + Integer.toHexString((config.getAccountName() + config.getProfileName() + config.getEnvironmentName()).hashCode());
    }

    public int a() {
        return this.s;
    }

    void a(int i) {
        this.r += i;
        this.q += i;
        this.c.edit().putInt("total_seconds_awake", this.q).putInt("prior_seconds_awake", this.r).apply();
    }

    static a a(Tealium.Config config) {
        if (config != null) {
            return new a(config, Calendar.getInstance(), a(config.getApplication()));
        }
        throw new IllegalArgumentException();
    }

    private String a(String str, long j) {
        long j2 = this.c.getLong(str, j);
        if (j2 == Long.MIN_VALUE) {
            return null;
        }
        this.f.setTime(j2);
        return this.a.format(this.f);
    }

    private static int a(SharedPreferences sharedPreferences) {
        int i = sharedPreferences.getInt("prior_seconds_awake", 0);
        if (i != 0) {
            sharedPreferences.edit().remove("prior_seconds_awake").apply();
        }
        return i;
    }

    private static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
