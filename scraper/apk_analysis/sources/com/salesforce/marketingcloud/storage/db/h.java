package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.util.Crypto;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class h extends b implements com.salesforce.marketingcloud.storage.g {
    public static final String e = "location_table";
    private static final String f = "CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );";
    private static final String[] g = {"id", a.b, a.c};
    private static final String h = com.salesforce.marketingcloud.g.a("LocationDbStorage");

    public static class a {
        public static final String a = "id";
        public static final String b = "latitude";
        public static final String c = "longitude";
    }

    public h(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS location_table");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(f);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(FirebaseConstants.SEPARATOR, g), e));
            return true;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(h, e2, "%s is invalid", e);
            return false;
        }
    }

    static boolean d(SQLiteDatabase sQLiteDatabase) {
        boolean c = c(sQLiteDatabase);
        if (c) {
            return c;
        }
        try {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            return c(sQLiteDatabase);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(h, e2, "Unable to recover %s", e);
            return c;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.g
    public LatLon f(Crypto crypto) {
        Cursor a2 = a(g, String.format(Locale.ENGLISH, "%s = ?", "id"), new String[]{"0"});
        LatLon latLon = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                try {
                    latLon = new LatLon(Double.valueOf(crypto.decString(a2.getString(a2.getColumnIndex(a.b)))).doubleValue(), Double.valueOf(crypto.decString(a2.getString(a2.getColumnIndex(a.c)))).doubleValue());
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(h, e2, "Unable to read location from database.", new Object[0]);
                }
            }
            a2.close();
        }
        return latLon;
    }

    @Override // com.salesforce.marketingcloud.storage.g
    public int g() {
        return i(null);
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    @Override // com.salesforce.marketingcloud.storage.g
    public void a(LatLon latLon, Crypto crypto) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", (Integer) 0);
        contentValues.put(a.b, crypto.encString(Double.toString(latLon.latitude())));
        contentValues.put(a.c, crypto.encString(Double.toString(latLon.longitude())));
        if (a(contentValues, String.format(Locale.ENGLISH, "%s = ?", "id"), new String[]{"0"}) == 0) {
            a(contentValues);
        }
    }
}
