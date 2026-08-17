package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class b {
    protected static final String d = "%s = ?";
    protected final SQLiteDatabase c;

    public b(SQLiteDatabase sQLiteDatabase) {
        this.c = sQLiteDatabase;
    }

    protected final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return this.c.query(false, o(), strArr, str, strArr2, str2, str3, str4, null);
    }

    void b(String str, Collection<String> collection) {
        this.c.execSQL(c.a("DROP TABLE IF EXISTS tmp_%s;", str));
        this.c.execSQL(c.a("CREATE TEMPORARY TABLE tmp_%s(id VARCHAR);", str));
        ContentValues contentValues = new ContentValues();
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            contentValues.put("id", it.next());
            this.c.insert(c.a("tmp_%s", str), null, contentValues);
        }
    }

    final int c(Collection<String> collection) throws SQLException {
        return a(o(), collection, false);
    }

    void h(String str) {
        this.c.execSQL(c.a("DROP TABLE IF EXISTS tmp_%s;", str));
    }

    protected final int i(String str) {
        return a(str, (String[]) null);
    }

    abstract String o();

    protected final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.c.query(false, o(), strArr, str, strArr2, str2, str3, str4, str5);
    }

    protected final Cursor a(String str, String[] strArr, String str2, String[] strArr2) {
        return this.c.query(str, strArr, str2, strArr2, null, null, null);
    }

    protected final Cursor a(String[] strArr, String str, String[] strArr2) {
        return a(strArr, str, strArr2, null, null, null, null);
    }

    protected final Cursor a(String[] strArr, String str) {
        return a(strArr, str, null, null, null, null, null);
    }

    protected final long a(ContentValues contentValues) {
        return this.c.insert(o(), null, contentValues);
    }

    protected final int a(String str, String[] strArr) {
        return this.c.delete(o(), str, strArr);
    }

    protected final int a(ContentValues contentValues, String str, String[] strArr) {
        return this.c.update(o(), contentValues, str, strArr);
    }

    final int a(String str, Collection<String> collection) throws SQLException {
        return a(str, collection, true);
    }

    private int a(String str, Collection<String> collection, boolean z) {
        String str2;
        try {
            this.c.beginTransaction();
            b(str, collection);
            SQLiteDatabase sQLiteDatabase = this.c;
            if (z) {
                str2 = "IS NULL";
            } else {
                str2 = "IS NOT NULL";
            }
            int delete = sQLiteDatabase.delete(str, c.a("id IN(SELECT %1$s.id FROM %1$s LEFT JOIN tmp_%1$s ON %1$s.id = tmp_%1$s.id WHERE tmp_%1$s.id %2$s)", str, str2), null);
            h(str);
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
            return delete;
        } catch (SQLException e) {
            this.c.endTransaction();
            throw e;
        }
    }

    final void a(ContentValues contentValues, Collection<String> collection) {
        try {
            this.c.beginTransactionNonExclusive();
            b(o(), collection);
            a(contentValues, c.a("id IN(SELECT %1$s.id FROM %1$s LEFT JOIN tmp_%1$s ON %1$s.id = tmp_%1$s.id WHERE tmp_%1$s.id IS NOT NULL)", o()), (String[]) null);
            h(o());
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
        } catch (SQLException e) {
            this.c.endTransaction();
            throw e;
        }
    }
}
