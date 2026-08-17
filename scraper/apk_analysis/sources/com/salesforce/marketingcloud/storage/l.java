package com.salesforce.marketingcloud.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class l {
    protected static final String f = com.salesforce.marketingcloud.g.a("Storage");
    protected static final int g = -1;
    protected static final String h = "create_date";
    private static final String i = "mcsdk_%s";
    protected final String a;
    protected final Context b;
    protected final Crypto c;
    private final String d;
    private final String e;

    l(Context context, Crypto crypto, String str, String str2) {
        this.d = (String) com.salesforce.marketingcloud.util.g.a((String) com.salesforce.marketingcloud.util.g.a(str, "Application ID is null."), "Application ID is empty.");
        this.e = (String) com.salesforce.marketingcloud.util.g.a((String) com.salesforce.marketingcloud.util.g.a(str2, "Access Token is null."), "Access Token is empty.");
        this.b = context;
        this.c = crypto;
        this.a = str;
    }

    static String a(String str) {
        return String.format(Locale.ENGLISH, i, str);
    }

    protected abstract Context a();

    protected abstract boolean a(SharedPreferences sharedPreferences);

    public abstract Crypto b();

    public abstract b c();

    protected abstract SQLiteOpenHelper d();

    public abstract SharedPreferences e();
}
