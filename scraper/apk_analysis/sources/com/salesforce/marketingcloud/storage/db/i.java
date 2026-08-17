package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.util.Crypto;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class i extends b implements com.salesforce.marketingcloud.storage.i {
    public static final String e = "messages";
    private static final String g = "CREATE TABLE messages (id VARCHAR PRIMARY KEY, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER );";
    private static final String[] f = {"id", "title", "alert", "sound", a.e, a.f, a.g, "start_date", "end_date", "message_type", "content_type", "url", "custom", "keys", a.o, a.q, a.r, a.p, a.s, a.t, a.u, a.v, a.w, a.x, a.y};
    private static final String h = com.salesforce.marketingcloud.g.a("MessageDbStorage");

    public static class a {
        public static final String a = "id";
        public static final String b = "title";
        public static final String c = "alert";
        public static final String d = "sound";
        public static final String e = "mediaUrl";
        public static final String f = "mediaAlt";
        public static final String g = "open_direct";
        public static final String h = "start_date";
        public static final String i = "end_date";
        public static final String j = "message_type";
        public static final String k = "content_type";
        public static final String l = "url";
        public static final String m = "custom";
        public static final String n = "keys";
        public static final String o = "period_show_count";
        public static final String p = "show_count";
        public static final String q = "last_shown_date";
        public static final String r = "next_allowed_show";
        public static final String s = "message_limit";
        public static final String t = "rolling_period";
        public static final String u = "period_type";
        public static final String v = "number_of_periods";
        public static final String w = "messages_per_period";
        public static final String x = "proximity";
        public static final String y = "notify_id";
    }

    public i(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(g);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(FirebaseConstants.SEPARATOR, f), e));
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

    @Override // com.salesforce.marketingcloud.storage.i
    public int e(int i) {
        return a(a("%s = ?", "message_type"), new String[]{String.valueOf(i)});
    }

    @Override // com.salesforce.marketingcloud.storage.db.b
    String o() {
        return e;
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues b(Message message, Crypto crypto) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", message.id());
        contentValues.put("title", crypto.encString(message.title()));
        contentValues.put("alert", crypto.encString(message.alert()));
        contentValues.put("sound", message.sound());
        if (message.media() != null) {
            contentValues.put(a.e, crypto.encString(message.media().url()));
            contentValues.put(a.f, crypto.encString(message.media().altText()));
        }
        contentValues.put("start_date", com.salesforce.marketingcloud.util.j.a(message.startDateUtc()));
        contentValues.put("end_date", com.salesforce.marketingcloud.util.j.a(message.endDateUtc()));
        contentValues.put("message_type", Integer.valueOf(message.messageType()));
        contentValues.put("content_type", Integer.valueOf(message.contentType()));
        contentValues.put("url", crypto.encString(message.url()));
        contentValues.put("custom", crypto.encString(message.custom()));
        contentValues.put(a.w, Integer.valueOf(message.messagesPerPeriod()));
        contentValues.put(a.v, Integer.valueOf(message.numberOfPeriods()));
        contentValues.put(a.u, Integer.valueOf(message.periodType()));
        contentValues.put(a.t, Integer.valueOf(message.isRollingPeriod() ? 1 : 0));
        contentValues.put(a.s, Integer.valueOf(message.messageLimit()));
        contentValues.put(a.x, Integer.valueOf(message.proximity()));
        contentValues.put(a.g, crypto.encString(message.openDirect()));
        contentValues.put("keys", crypto.encString(com.salesforce.marketingcloud.util.j.a(message.customKeys())));
        contentValues.put(a.r, com.salesforce.marketingcloud.util.j.a(com.salesforce.marketingcloud.internal.h.b(message)));
        contentValues.put(a.o, Integer.valueOf(com.salesforce.marketingcloud.internal.h.d(message)));
        contentValues.put(a.y, Integer.valueOf(com.salesforce.marketingcloud.internal.h.c(message)));
        contentValues.put(a.p, Integer.valueOf(com.salesforce.marketingcloud.internal.h.e(message)));
        contentValues.put(a.q, com.salesforce.marketingcloud.util.j.a(com.salesforce.marketingcloud.internal.h.a(message)));
        return contentValues;
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public void a(Message message, Crypto crypto) throws Exception {
        ContentValues b = b(message, crypto);
        if (a(b, a("%s = ?", "id"), new String[]{message.id()}) == 0) {
            a(b);
        }
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public Message a(String str, Crypto crypto) {
        Cursor a2 = a(f, a("%s = ?", "id"), new String[]{str}, null, null, null, "1");
        if (a2 != null) {
            r9 = a2.moveToFirst() ? d.b(a2, crypto) : null;
            a2.close();
        }
        return r9;
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public List<Message> a(Crypto crypto) {
        List<Message> emptyList = Collections.emptyList();
        Cursor a2 = a(f, a(3, 4));
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList = new ArrayList(a2.getCount());
                do {
                    Message b = d.b(a2, crypto);
                    if (b != null) {
                        arrayList.add(b);
                    }
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public List<Message> b(Crypto crypto) {
        List<Message> emptyList = Collections.emptyList();
        Cursor a2 = a(f, a(5));
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList = new ArrayList(a2.getCount());
                do {
                    Message b = d.b(a2, crypto);
                    if (b != null) {
                        arrayList.add(b);
                    }
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    private String a(int... iArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (int i : iArr) {
            if (z) {
                sb.append("message_type IN(");
                z = false;
            } else {
                sb.append(',');
            }
            sb.append(i);
        }
        sb.append(");");
        return sb.toString();
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public int a(String str) {
        return a(a("%s = ?", "id"), new String[]{str});
    }

    @Override // com.salesforce.marketingcloud.storage.i
    public int a(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(a.y, Integer.valueOf(i));
        return a(contentValues, a("%s = ?", "id"), new String[]{str});
    }
}
