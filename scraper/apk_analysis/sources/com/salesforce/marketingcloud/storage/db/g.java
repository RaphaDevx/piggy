package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.storage.f;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public final class g extends b implements com.salesforce.marketingcloud.storage.f {
    public static final String e = "inbox_messages";
    private static final String g = "(start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?)";
    static final String f = com.salesforce.marketingcloud.g.a("InboxMessageDbStorage");
    private static final String[] h = {"id", "start_date", "is_deleted", "is_read", "message_hash", "is_dirty"};

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[f.a.values().length];
            a = iArr;
            try {
                iArr[f.a.READ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[f.a.UNREAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[f.a.DELETED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[f.a.NOT_DELETED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public g(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS inbox_messages");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE inbox_messages(id TEXT PRIMARY KEY, start_date INTEGER DEFAULT NULL, end_date INTEGER DEFAULT NULL, is_deleted INTEGER DEFAULT 0, is_read INTEGER DEFAULT 0, is_dirty INTEGER DEFAULT 0, message_type INTEGER DEFAULT " + InboxMessage.InboxMessageType.LEGACY.getIndex() + ", message_hash TEXT DEFAULT NULL, notification_message_json TEXT DEFAULT NULL, message_json TEXT);");
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,start_date,end_date,is_deleted,is_read,is_dirty,message_hash,message_json,message_type,notification_message_json FROM inbox_messages");
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

    @Override // com.salesforce.marketingcloud.storage.f
    public boolean e(String str) {
        return DatabaseUtils.queryNumEntries(this.c, e, "id=?", new String[]{str}) > 0;
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public f.b f(String str) {
        Cursor a2 = a(h, "id=?", new String[]{str});
        if (a2 != null) {
            r3 = a2.moveToFirst() ? a(a2) : null;
            a2.close();
        }
        return r3;
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public int h() {
        return i(null);
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public List<f.b> i() {
        ArrayList arrayList = null;
        Cursor a2 = a(h, "is_dirty=1", (String[]) null);
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList2 = new ArrayList(a2.getCount());
                do {
                    arrayList2.add(a(a2));
                } while (a2.moveToNext());
                arrayList = arrayList2;
            }
            a2.close();
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void j() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.c.execSQL("UPDATE inbox_messages SET   is_read = 1,  is_dirty = CASE WHEN is_dirty=1 OR is_deleted=0 THEN 1 ELSE 0 END WHERE (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_read=0", new String[]{valueOf, valueOf});
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public List<InboxMessage> n(Crypto crypto) {
        return a(a((String[]) null, (String) null, (String[]) null), crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    private static List<InboxMessage> a(Cursor cursor, Crypto crypto) {
        List<InboxMessage> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                do {
                    InboxMessage a2 = d.a(cursor, crypto);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    private static String c(f.a aVar) {
        StringBuilder sb = new StringBuilder(101);
        sb.append(g);
        int i = a.a[aVar.ordinal()];
        if (i == 1 || i == 2) {
            sb.append(" AND is_read=? AND is_deleted=?");
        } else {
            if (i != 3 && i != 4) {
                throw new IllegalArgumentException("Unknown MessageStatus while getting message counts.");
            }
            sb.append(" AND is_deleted=?");
        }
        return sb.toString();
    }

    private static String[] b(f.a aVar) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        int i = a.a[aVar.ordinal()];
        if (i == 1 || i == 2) {
            return new String[]{valueOf, valueOf, aVar != f.a.READ ? "0" : "1", "0"};
        }
        if (i == 3 || i == 4) {
            return new String[]{valueOf, valueOf, aVar != f.a.DELETED ? "0" : "1"};
        }
        throw new IllegalArgumentException("Unknown MessageStatus while getting message counts.");
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void d(String str) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.c.execSQL("UPDATE inbox_messages SET   is_read = 1,  is_dirty = CASE WHEN is_dirty=1 OR is_deleted=0 THEN 1 ELSE 0 END WHERE   id=? AND (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_read=0", new String[]{str, valueOf, valueOf});
    }

    private static f.b a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("start_date");
        return new f.b(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("message_hash")), cursor.isNull(columnIndex) ? null : new Date(cursor.getLong(columnIndex)), cursor.getInt(cursor.getColumnIndex("is_read")) == 1, cursor.getInt(cursor.getColumnIndex("is_deleted")) == 1, cursor.getInt(cursor.getColumnIndex("is_dirty")) == 1);
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void a(InboxMessage inboxMessage, Crypto crypto) {
        ContentValues a2 = d.a(inboxMessage, crypto);
        if (a(a2, "id = ?", new String[]{inboxMessage.id()}) == 0) {
            a(a2);
        }
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public int a(List<String> list) {
        if (list.isEmpty()) {
            return 0;
        }
        try {
            return c(list);
        } catch (Exception unused) {
            com.salesforce.marketingcloud.g.e(f, "Unable to clean up %s table.", this.o());
            return 0;
        }
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public InboxMessage a(String str, Crypto crypto) {
        Cursor a2 = a(null, "id=?", new String[]{str}, null, null, null, "1");
        if (a2 != null) {
            r9 = a2.moveToFirst() ? d.a(a2, crypto) : null;
            a2.close();
        }
        return r9;
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void b() {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("is_dirty", (Integer) 1);
        contentValues.put("is_deleted", (Integer) 1);
        String valueOf = String.valueOf(System.currentTimeMillis());
        a(contentValues, "(start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_deleted=0", new String[]{valueOf, valueOf});
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void c(String str) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("is_dirty", (Integer) 1);
        contentValues.put("is_deleted", (Integer) 1);
        String valueOf = String.valueOf(System.currentTimeMillis());
        a(contentValues, "id=? AND (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_deleted=0", new String[]{str, valueOf, valueOf});
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public List<InboxMessage> a(Crypto crypto, f.a aVar) {
        return a(a(null, c(aVar), b(aVar), null, null, "IFNULL(start_date, " + System.currentTimeMillis() + ") DESC"), crypto);
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public void b(String[] strArr) {
        if (strArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_dirty", (Integer) 0);
            try {
                a(contentValues, Arrays.asList(strArr));
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.e(f, "Unable to update %s table.", o());
            }
        }
    }

    @Override // com.salesforce.marketingcloud.storage.f
    public int a(f.a aVar) {
        return (int) DatabaseUtils.queryNumEntries(this.c, e, c(aVar), b(aVar));
    }
}
