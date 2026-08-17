package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.Crypto;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a extends b implements com.salesforce.marketingcloud.storage.a {
    public static final String e = "analytic_item";
    static final int f = 999;
    private static final String g = "999";
    private static final String i = "CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, enc_json_pi_payload VARCHAR, enc_json_et_payload VARCHAR, predictive_intelligence_identifier VARCHAR DEFAULT NULL);";
    private static final String[] h = {"id", C0097a.c, C0097a.i, C0097a.d, "value", C0097a.f, C0097a.e, C0097a.h, C0097a.g, "predictive_intelligence_identifier"};
    private static final String j = com.salesforce.marketingcloud.g.a("AnalyticItemDbStorage");

    /* renamed from: com.salesforce.marketingcloud.storage.db.a$a, reason: collision with other inner class name */
    public static class C0097a {
        public static final String a = "id";
        public static final String b = "value";
        public static final String c = "event_date";
        public static final String d = "analytic_type";
        public static final String e = "object_ids";
        public static final String f = "ready_to_send";
        public static final String g = "enc_json_et_payload";
        public static final String h = "enc_json_pi_payload";
        public static final String i = "analytic_product_type";
        public static final String j = "predictive_intelligence_identifier";
    }

    public a(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS analytic_item");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(i);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(FirebaseConstants.SEPARATOR, h), e));
            return true;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(j, e2, "%s is invalid", e);
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
            com.salesforce.marketingcloud.g.b(j, e2, "Unable to recover %s", e);
            return c;
        }
    }

    private int h(int i2) {
        return (int) DatabaseUtils.queryNumEntries(this.c, e, a("%s=%s", C0097a.i, Integer.valueOf(i2)));
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int e() {
        return h(1);
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int g(int i2) {
        return a(a("%s = ?", C0097a.i), new String[]{String.valueOf(i2)});
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> i(Crypto crypto) {
        return b(1, crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> o(Crypto crypto) {
        return b(a(h, a("%s=? AND %s=?", C0097a.i, C0097a.f), new String[]{String.valueOf(1), String.valueOf(1)}, null, null, a("%s ASC", C0097a.c), "999"), crypto);
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    List<com.salesforce.marketingcloud.analytics.b> b(Cursor cursor, Crypto crypto) {
        List<com.salesforce.marketingcloud.analytics.b> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                do {
                    com.salesforce.marketingcloud.analytics.b a = a(cursor, crypto);
                    if (a != null) {
                        arrayList.add(a);
                    } else {
                        int i2 = cursor.getInt(cursor.getColumnIndex("id"));
                        if (i2 >= 0) {
                            a(a("%s = ?", "id"), new String[]{String.valueOf(i2)});
                        }
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    private static com.salesforce.marketingcloud.analytics.b a(Cursor cursor, Crypto crypto) {
        String str;
        String str2;
        com.salesforce.marketingcloud.analytics.b a;
        try {
            int i2 = cursor.getInt(cursor.getColumnIndex(C0097a.d));
            int i3 = cursor.getInt(cursor.getColumnIndex(C0097a.i)) == 0 ? 0 : 1;
            Date d = com.salesforce.marketingcloud.util.j.d(cursor.getString(cursor.getColumnIndex(C0097a.c)));
            boolean z = cursor.getInt(cursor.getColumnIndex(C0097a.f)) == 1;
            List emptyList = Collections.emptyList();
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex(C0097a.e)));
            if (jSONArray.length() > 0) {
                emptyList = new ArrayList();
                for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                    emptyList.add(jSONArray.getString(i4));
                }
            }
            List list = emptyList;
            if (crypto != null) {
                String decString = crypto.decString(cursor.getString(cursor.getColumnIndex(C0097a.g)));
                str = !TextUtils.isEmpty(decString) ? new JSONObject(decString).optString("requestId") : null;
                str2 = decString;
            } else {
                str = null;
                str2 = null;
            }
            if (!TextUtils.isEmpty(str)) {
                a = com.salesforce.marketingcloud.analytics.b.a(d, i3, i2, list, str, z);
            } else if (list.size() > 0) {
                a = com.salesforce.marketingcloud.analytics.b.a(d, i3, i2, (List<String>) list, z);
            } else {
                a = com.salesforce.marketingcloud.analytics.b.a(d, i3, i2);
                a.a(z);
            }
            a.a(cursor.getInt(cursor.getColumnIndex("id")));
            a.b(cursor.getInt(cursor.getColumnIndex("value")));
            a.b(str2);
            if (i3 == 1 && crypto != null) {
                a.d(crypto.decString(cursor.getString(cursor.getColumnIndex("predictive_intelligence_identifier"))));
                String string = cursor.getString(cursor.getColumnIndex(C0097a.h));
                if (!TextUtils.isEmpty(string)) {
                    a.c(crypto.decString(string));
                }
            }
            return a;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(j, e2, "Failed to create our analytic item from storage.", new Object[0]);
            return null;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> g(Crypto crypto) {
        return b(0, crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> h(Crypto crypto) {
        return b(a(h, a("(%1$s=? OR %1$s=?) AND %2$s=?", C0097a.d, C0097a.f), new String[]{String.valueOf(13), String.valueOf(11), String.valueOf(0)}), crypto);
    }

    private static ContentValues c(com.salesforce.marketingcloud.analytics.b bVar, Crypto crypto) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C0097a.c, com.salesforce.marketingcloud.util.j.a(bVar.b()));
        contentValues.put(C0097a.i, Integer.valueOf(bVar.j()));
        contentValues.put(C0097a.d, Integer.valueOf(bVar.a()));
        contentValues.put("value", Integer.valueOf(bVar.g()));
        contentValues.put(C0097a.f, Integer.valueOf(bVar.h() ? 1 : 0));
        contentValues.put(C0097a.e, new JSONArray((Collection) bVar.i()).toString());
        if (bVar.j() == 0) {
            if (bVar.c() != null) {
                contentValues.put(C0097a.g, crypto.encString(bVar.c()));
            }
            contentValues.put("predictive_intelligence_identifier", (String) null);
            contentValues.put(C0097a.h, (String) null);
        } else if (bVar.j() == 1) {
            contentValues.put("predictive_intelligence_identifier", crypto.encString(bVar.f()));
            contentValues.put(C0097a.h, crypto.encString(bVar.e()));
            contentValues.put(C0097a.g, (String) null);
        }
        return contentValues;
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int d() {
        return h(0);
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int b(com.salesforce.marketingcloud.analytics.b bVar, Crypto crypto) throws Exception {
        return a(c(bVar, crypto), a("%s = ?", "id"), new String[]{String.valueOf(bVar.d())});
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> c(Crypto crypto) {
        return b(a(h, a("%s=? AND %s=?", C0097a.i, C0097a.f), new String[]{String.valueOf(0), "1"}, null, null, a("%s ASC", "id"), "999"), crypto);
    }

    private List<com.salesforce.marketingcloud.analytics.b> b(int i2, Crypto crypto) {
        return b(a(h, a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", C0097a.d, C0097a.i, "value", C0097a.f), new String[]{String.valueOf(4), String.valueOf(5), String.valueOf(i2), String.valueOf(0), String.valueOf(0)}, null, null, a("%s ASC", "id")), crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public boolean c(int i2) {
        return DatabaseUtils.queryNumEntries(this.c, o(), a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", C0097a.d, C0097a.i, "value", C0097a.f), new String[]{String.valueOf(4), String.valueOf(5), String.valueOf(i2), String.valueOf(0), String.valueOf(0)}) > 0;
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public List<com.salesforce.marketingcloud.analytics.b> b(Region region, Crypto crypto) {
        return b(a(h, a("(%1$s=? OR %1$s=?) AND %2$s LIKE ? AND %3$s=?", C0097a.d, C0097a.e, C0097a.f), new String[]{String.valueOf(13), String.valueOf(11), a("%%%s%%", region.id()), String.valueOf(0)}), crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public void a(com.salesforce.marketingcloud.analytics.b bVar, Crypto crypto) throws Exception {
        int i2 = bVar.j() == 0 ? 0 : 1;
        int h2 = h(i2);
        if (h2 + 1 > 999) {
            a(h2, 999, i2);
        }
        bVar.a((int) a(c(bVar, crypto)));
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int b(int i2) {
        return a(a("%s = ? AND %s IN (%s)", C0097a.i, C0097a.d, TextUtils.join(FirebaseConstants.SEPARATOR, com.salesforce.marketingcloud.analytics.b.C)), new String[]{String.valueOf(i2)});
    }

    private void a(int i2, int i3, int i4) throws Exception {
        i(a("%s IN ( SELECT %s FROM %s WHERE %s=%d ORDER BY %s ASC LIMIT %d )", "id", "id", e, C0097a.i, Integer.valueOf(i4), "id", Integer.valueOf((i2 + 1) - i3)));
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int a(String[] strArr) {
        return i(a("%s IN (%s)", "id", TextUtils.join(FirebaseConstants.SEPARATOR, strArr)));
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int a() {
        try {
            return a("analytic_product_type =? AND event_date <= ?", new String[]{String.valueOf(1), com.salesforce.marketingcloud.util.j.a(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(14L)))});
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(j, e2, "Unable to purge old analytic data.", new Object[0]);
            return 0;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.a
    public int a(int i2) {
        return a(a("%s = ? AND %s NOT IN (%s)", C0097a.i, C0097a.d, TextUtils.join(FirebaseConstants.SEPARATOR, com.salesforce.marketingcloud.analytics.b.C)), new String[]{String.valueOf(i2)});
    }
}
