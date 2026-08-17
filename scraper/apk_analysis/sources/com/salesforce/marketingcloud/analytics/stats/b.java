package com.salesforce.marketingcloud.analytics.stats;

import com.salesforce.marketingcloud.g;
import java.util.Date;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class b {
    public static final int f = 100;
    public static final int g = 101;
    public static final int h = 102;
    public static final int i = 103;
    public static final int j = 104;
    public static final int k = 105;
    public static final int l = 106;
    public static final int m = 107;
    public static final int n = 110;
    public static final int o = 111;
    public static final int p = 112;
    private final Integer a;
    private final int b;
    private final Date c;
    d d;
    private boolean e;

    private b(Integer num, int i2, Date date, d dVar, boolean z) {
        this.a = num;
        this.b = i2;
        this.c = date;
        this.d = dVar;
        this.e = z;
    }

    public static b a(int i2, Date date, d dVar, boolean z) {
        return new b(null, i2, date, dVar, z);
    }

    public Integer b() {
        return this.a;
    }

    public d c() {
        return this.d;
    }

    public int d() {
        return this.b;
    }

    public boolean e() {
        return this.e;
    }

    public static b a(int i2, int i3, Date date, d dVar, boolean z) {
        return new b(Integer.valueOf(i2), i3, date, dVar, z);
    }

    public Date a() {
        return this.c;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(Date date) {
        int time = (int) ((date.getTime() - this.c.getTime()) / 1000.0d);
        if (time <= 0) {
            time = 1;
        }
        try {
            d dVar = this.d;
            if (dVar != null) {
                dVar.b(time);
                this.e = true;
            }
        } catch (JSONException e) {
            g.b(c.k, e, "Unable to finalize event [%d]", Integer.valueOf(this.b));
        }
    }
}
