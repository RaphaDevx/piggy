package com.salesforce.marketingcloud.storage.db.upgrades;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.util.Crypto;

/* loaded from: classes2.dex */
public class l {
    private static final String a = com.salesforce.marketingcloud.g.a("Version9ToVersion10");

    private l() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:37|38|40|41|(5:97|98|99|100|101)(1:43)|44|(1:46)|47|(3:72|73|(12:75|(8:78|79|80|81|82|83|85|76)|88|89|50|(4:66|(1:68)|(1:70)|71)|53|54|55|56|57|(1:60)(1:59)))|49|50|(0)|66|(0)|(0)|71|53|54|55|56|57|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01f9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0261 A[Catch: all -> 0x028b, Exception -> 0x028d, TryCatch #2 {Exception -> 0x028d, blocks: (B:12:0x0258, B:14:0x0261, B:16:0x0267, B:21:0x0284, B:22:0x0287), top: B:11:0x0258, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0227 A[LOOP:1: B:37:0x0036->B:59:0x0227, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0226 A[EDGE_INSN: B:60:0x0226->B:61:0x0226 BREAK  A[LOOP:1: B:37:0x0036->B:59:0x0227], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01b3 A[Catch: Exception -> 0x01fb, all -> 0x0206, TryCatch #4 {all -> 0x0206, blocks: (B:41:0x0040, B:98:0x0058, B:101:0x0062, B:44:0x0076, B:46:0x0080, B:47:0x0094, B:73:0x0102, B:75:0x010c, B:76:0x0119, B:78:0x011f, B:83:0x0132, B:89:0x014e, B:50:0x0163, B:53:0x01c4, B:66:0x01ac, B:68:0x01b3, B:70:0x01ba, B:71:0x01bf), top: B:40:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ba A[Catch: Exception -> 0x01fb, all -> 0x0206, TryCatch #4 {all -> 0x0206, blocks: (B:41:0x0040, B:98:0x0058, B:101:0x0062, B:44:0x0076, B:46:0x0080, B:47:0x0094, B:73:0x0102, B:75:0x010c, B:76:0x0119, B:78:0x011f, B:83:0x0132, B:89:0x014e, B:50:0x0163, B:53:0x01c4, B:66:0x01ac, B:68:0x01b3, B:70:0x01ba, B:71:0x01bf), top: B:40:0x0040 }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.database.sqlite.SQLiteDatabase r21, com.salesforce.marketingcloud.util.Crypto r22) {
        /*
            Method dump skipped, instructions count: 681
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.l.a(android.database.sqlite.SQLiteDatabase, com.salesforce.marketingcloud.util.Crypto):void");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, Crypto crypto) {
        a(sQLiteDatabase, crypto);
        b(sQLiteDatabase);
        a(sQLiteDatabase);
        c(sQLiteDatabase);
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("CREATE TABLE region_messages ( region_id TEXT, message_id TEXT, FOREIGN KEY (region_id) REFERENCES regions(id) ON DELETE CASCADE, PRIMARY KEY (region_id, message_id));");
                sQLiteDatabase.execSQL("INSERT INTO region_messages SELECT region_id,message_id FROM region_message;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e) {
                com.salesforce.marketingcloud.g.b(a, e, "Unable to create region_messages table and migrate data from region_message.", new Object[0]);
                try {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_messages");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
                    sQLiteDatabase.execSQL("CREATE TABLE region_messages ( region_id TEXT, message_id TEXT, FOREIGN KEY (region_id) REFERENCES regions(id) ON DELETE CASCADE, PRIMARY KEY (region_id, message_id));");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException e2) {
                    com.salesforce.marketingcloud.g.b(a, e2, "Unable to create region_messages table", new Object[0]);
                    sQLiteDatabase.endTransaction();
                }
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("CREATE TABLE triggers (id TEXT PRIMARY KEY, _key TEXT, start_date INTEGER DEFAULT NULL, _trigger TEXT, app_open_count INTEGER DEFAULT 0);");
                sQLiteDatabase.execSQL("CREATE TABLE in_app_messages(id TEXT PRIMARY KEY, priority INTEGER DEFAULT 999, start_date DATETIME, end_date DATETIME, modified_date DATETIME, display_limit INTEGER DEFAULT 1, media_url TEXT DEFAULT NULL, message_json TEXT);");
                sQLiteDatabase.execSQL("CREATE TABLE iam_state(id TEXT PRIMARY KEY, display_count integer DEFAULT 0, FOREIGN KEY (id) REFERENCES in_app_messages(id) ON DELETE CASCADE);");
                sQLiteDatabase.execSQL("CREATE TRIGGER iam_state_init AFTER INSERT ON in_app_messages BEGIN INSERT INTO iam_state (id) VALUES (NEW.id); END;");
                sQLiteDatabase.execSQL("CREATE VIEW iam_view AS SELECT in_app_messages.id,in_app_messages.priority,in_app_messages.start_date,in_app_messages.end_date,in_app_messages.modified_date,in_app_messages.display_limit,in_app_messages.message_json,iam_state.display_count FROM in_app_messages INNER JOIN iam_state ON iam_state.id = in_app_messages.id;");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(a, e, "Unable to create in app message table", new Object[0]);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("CREATE TABLE device_stats(id INTEGER PRIMARY KEY, type INTEGER, date INTEGER, event_data TEXT, in_transit INTEGER DEFAULT 0, ready_to_send INTEGER DEFAULT 0);");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(a, e, "Unable to create device stats table.", new Object[0]);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }
}
