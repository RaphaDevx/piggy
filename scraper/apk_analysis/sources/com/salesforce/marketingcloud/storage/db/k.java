package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.registration.Registration;
import com.salesforce.marketingcloud.util.Crypto;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class k extends b implements com.salesforce.marketingcloud.storage.k {
    public static final String e = "registration";
    private static final String[] f = {"id", "platform", a.c, a.d, "timezone", a.f, "tags", "attributes", a.i, a.j, a.k, a.l, a.m, a.o, a.p, "app_version", a.r, a.s, "locale", "uuid"};
    private static final String g = "CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, proximity_enabled SMALLINT, hwid VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, signed_string VARCHAR, locale VARCHAR, uuid VARCHAR );";

    public static class a {
        public static final String a = "id";
        public static final String b = "platform";
        public static final String c = "subscriber_key";
        public static final String d = "et_app_id";
        public static final String e = "timezone";
        public static final String f = "dst";
        public static final String g = "tags";
        public static final String h = "attributes";
        public static final String i = "platform_version";
        public static final String j = "push_enabled";
        public static final String k = "location_enabled";
        public static final String l = "proximity_enabled";
        public static final String m = "hwid";
        public static final String n = "locale";
        public static final String o = "system_token";
        public static final String p = "device_id";
        public static final String q = "app_version";
        public static final String r = "sdk_version";
        public static final String s = "signed_string";
        public static final String t = "uuid";
    }

    public k(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS registration");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(g);
    }

    static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(a("SELECT %s FROM %s", TextUtils.join(FirebaseConstants.SEPARATOR, f), e));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.k
    public Registration l(Crypto crypto) throws Exception {
        Cursor a2 = a(f, null, null, null, null, a("%s DESC", "id"), "1");
        if (a2 != null) {
            r0 = a2.moveToFirst() ? d.d(a2, crypto) : null;
            a2.close();
        }
        return r0;
    }

    @Override // com.salesforce.marketingcloud.storage.k
    public int n() {
        return i(null);
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues c(Registration registration, Crypto crypto) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put(a.c, crypto.encString(registration.contactKey()));
        contentValues.put(a.s, crypto.encString(registration.signedString()));
        contentValues.put(a.d, crypto.encString(registration.appId()));
        contentValues.put(a.o, crypto.encString(registration.systemToken()));
        contentValues.put("tags", crypto.encString(com.salesforce.marketingcloud.util.j.a(registration.tags())));
        contentValues.put("attributes", crypto.encString(com.salesforce.marketingcloud.util.j.a(registration.attributes())));
        contentValues.put(a.p, registration.deviceId());
        contentValues.put("platform", registration.platform());
        contentValues.put("timezone", Integer.valueOf(registration.timeZone()));
        contentValues.put(a.f, Integer.valueOf(registration.dst() ? 1 : 0));
        contentValues.put(a.i, registration.platformVersion());
        contentValues.put(a.j, Integer.valueOf(registration.pushEnabled() ? 1 : 0));
        contentValues.put(a.k, Integer.valueOf(registration.locationEnabled() ? 1 : 0));
        contentValues.put(a.l, Integer.valueOf(registration.proximityEnabled() ? 1 : 0));
        contentValues.put(a.m, registration.hwid());
        contentValues.put("locale", registration.locale());
        contentValues.put("app_version", registration.appVersion());
        contentValues.put(a.r, registration.sdkVersion());
        contentValues.put("uuid", com.salesforce.marketingcloud.internal.m.d(registration));
        return contentValues;
    }

    @Override // com.salesforce.marketingcloud.storage.k
    public int b(Registration registration, Crypto crypto) throws Exception {
        return a(c(registration, crypto), a("%s = ?", "id"), new String[]{String.valueOf(com.salesforce.marketingcloud.internal.m.b(registration))});
    }

    @Override // com.salesforce.marketingcloud.storage.k
    public void a(Registration registration, Crypto crypto) throws Exception {
        com.salesforce.marketingcloud.internal.m.a(registration, (int) a(c(registration, crypto)));
        c();
    }

    @Override // com.salesforce.marketingcloud.storage.k
    public int c() {
        return i(a("%1$s NOT IN ( SELECT %1$s FROM ( SELECT %1$s FROM %2$s ORDER BY %1$s DESC LIMIT 1))", "id", o()));
    }
}
