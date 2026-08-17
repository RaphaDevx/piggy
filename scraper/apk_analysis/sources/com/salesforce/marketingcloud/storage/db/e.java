package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.storage.db.a;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class e extends b implements com.salesforce.marketingcloud.storage.c {
    static final String e = "device_stats";
    private static final String f = com.salesforce.marketingcloud.g.a("DeviceStatsDbStorage");

    public e(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_stats");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE device_stats(id INTEGER PRIMARY KEY, type INTEGER, date INTEGER, event_data TEXT, in_transit INTEGER DEFAULT 0, ready_to_send INTEGER DEFAULT 0);");
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,type,date,event_data,in_transit,ready_to_send FROM device_stats");
            return true;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(f, e2, "%s is invalid", e);
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
            com.salesforce.marketingcloud.g.b(f, e2, "Unable to recover %s", e);
            return c;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public int f() {
        return i(null);
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public List<com.salesforce.marketingcloud.analytics.stats.b> j(Crypto crypto) {
        List<com.salesforce.marketingcloud.analytics.stats.b> emptyList = Collections.emptyList();
        Cursor rawQuery = this.c.rawQuery("SELECT * FROM device_stats WHERE ready_to_send=0", null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                ArrayList arrayList = new ArrayList(rawQuery.getCount());
                do {
                    arrayList.add(a(rawQuery, crypto));
                } while (rawQuery.moveToNext());
                emptyList = arrayList;
            }
            rawQuery.close();
        }
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public List<com.salesforce.marketingcloud.analytics.stats.b> k(Crypto crypto) {
        return a(crypto, "SELECT * FROM device_stats WHERE ready_to_send=1 AND in_transit=0 AND type IN(100, 101, 102, 103, 104, 106, 107, 110, 111, 112)");
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public List<com.salesforce.marketingcloud.analytics.stats.b> p(Crypto crypto) {
        return a(crypto, "SELECT * FROM device_stats WHERE ready_to_send=1 AND in_transit=0 AND type IN(105)");
    }

    private static com.salesforce.marketingcloud.analytics.stats.b a(Cursor cursor, Crypto crypto) {
        try {
            int i = cursor.getInt(cursor.getColumnIndex("id"));
            int i2 = cursor.getInt(cursor.getColumnIndex("type"));
            Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
            com.salesforce.marketingcloud.analytics.stats.d a = com.salesforce.marketingcloud.analytics.stats.d.a(crypto.decString(cursor.getString(cursor.getColumnIndex("event_data"))));
            boolean z = true;
            if (cursor.getInt(cursor.getColumnIndex(a.C0097a.f)) != 1) {
                z = false;
            }
            return com.salesforce.marketingcloud.analytics.stats.b.a(i, i2, date, a, z);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(f, e2, "Unable to read analytic item from cursor.", new Object[0]);
            return null;
        }
    }

    private static ContentValues b(com.salesforce.marketingcloud.analytics.stats.b bVar, Crypto crypto) throws Exception {
        ContentValues contentValues = new ContentValues();
        if (bVar.b() != null) {
            contentValues.put("id", bVar.b());
        }
        contentValues.put("type", Integer.valueOf(bVar.d()));
        contentValues.put("date", Long.valueOf(bVar.a().getTime()));
        contentValues.put("event_data", crypto.encString(bVar.c().a()));
        contentValues.put(a.C0097a.f, Integer.valueOf(bVar.e() ? 1 : 0));
        return contentValues;
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public void c(String[] strArr) {
        if (strArr.length > 0) {
            try {
                com.salesforce.marketingcloud.g.c(f, "Deleted %d items of %d items", Integer.valueOf(strArr.length), Integer.valueOf(c(Arrays.asList(strArr))));
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.e(f, "Unable to clean up %s table.", o());
            }
        }
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public void d(String[] strArr) {
        if (strArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("in_transit", (Integer) 0);
            try {
                a(contentValues, Arrays.asList(strArr));
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.e(f, "Unable to update %s table.", o());
            }
        }
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public void a(com.salesforce.marketingcloud.analytics.stats.b bVar, Crypto crypto) throws Exception {
        ContentValues b = b(bVar, crypto);
        if (bVar.b() == null || a(b, "id = ?", new String[]{bVar.b().toString()}) == 0) {
            a(b);
        }
    }

    private List<com.salesforce.marketingcloud.analytics.stats.b> a(Crypto crypto, String str) {
        List<com.salesforce.marketingcloud.analytics.stats.b> emptyList = Collections.emptyList();
        this.c.beginTransaction();
        Cursor rawQuery = this.c.rawQuery(str, null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                int count = rawQuery.getCount();
                int columnIndex = rawQuery.getColumnIndex("id");
                String[] strArr = new String[count];
                ArrayList arrayList = new ArrayList(count);
                int i = 0;
                while (true) {
                    arrayList.add(a(rawQuery, crypto));
                    strArr[i] = rawQuery.getString(columnIndex);
                    if (!rawQuery.moveToNext()) {
                        break;
                    }
                    int i2 = i + 1;
                    if (i >= count) {
                        break;
                    }
                    i = i2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("in_transit", (Integer) 1);
                try {
                    a(contentValues, Arrays.asList(strArr));
                    this.c.setTransactionSuccessful();
                } catch (Exception unused) {
                    com.salesforce.marketingcloud.g.e(f, "Unable to update %s table.", o());
                }
                emptyList = arrayList;
            }
            rawQuery.close();
            this.c.endTransaction();
        }
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.storage.c
    public int a() {
        try {
            return a("(type = ? OR type = ?) AND in_transit = 0 AND date <= ?", new String[]{String.valueOf(com.salesforce.marketingcloud.analytics.stats.b.l), String.valueOf(com.salesforce.marketingcloud.analytics.stats.b.m), String.valueOf(System.currentTimeMillis() - 1209600000)}) + a("type = ? AND ready_to_send = 0 AND date <= ?", new String[]{String.valueOf(100), String.valueOf(System.currentTimeMillis() - 1209600000)});
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(f, e2, "Unable to purge old debug/telemetry data.", new Object[0]);
            return 0;
        }
    }
}
