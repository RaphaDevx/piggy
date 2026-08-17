package com.salesforce.marketingcloud.storage.db.upgrades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.storage.db.a;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class d {
    static final String a = "geofence_request";
    static final String b = "beacon_request";
    private static final String c = com.salesforce.marketingcloud.g.a("Version1ToVersion2");

    private static class a {
        final String a;
        final String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            String str = this.a;
            if (str == null && aVar.a == null) {
                return true;
            }
            return str.equalsIgnoreCase(aVar.a);
        }

        public int hashCode() {
            return this.a.toLowerCase().hashCode();
        }
    }

    private d() {
    }

    private static void a(SQLiteDatabase sQLiteDatabase, Context context, Crypto crypto) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("ALTER TABLE registration RENAME TO old_registration");
                sQLiteDatabase.execSQL("CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, badge INTEGER, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, gcm_sender_id VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR )");
                sQLiteDatabase.execSQL("INSERT INTO registration (id,platform,subscriber_key,et_app_id,badge,timezone,dst,tags,attributes,platform_version,push_enabled,location_enabled,hwid,gcm_sender_id,locale) SELECT id,platform,subscriber_key,et_app_id,badge,timezone,dst,tags,attributes,platform_version,push_enabled,location_enabled,hwid,gcm_sender_id,locale FROM old_registration");
                if (DatabaseUtils.queryNumEntries(sQLiteDatabase, com.salesforce.marketingcloud.storage.db.k.e) > 0) {
                    ContentValues contentValues = new ContentValues(3);
                    contentValues.put(k.a.p, com.salesforce.marketingcloud.util.c.a(context, (String) null));
                    contentValues.put("app_version", com.salesforce.marketingcloud.util.f.a(context));
                    contentValues.put(k.a.r, MarketingCloudSdk.getSdkVersionName());
                    sQLiteDatabase.update(com.salesforce.marketingcloud.storage.db.k.e, contentValues, null, null);
                }
                sQLiteDatabase.execSQL("DROP TABLE old_registration");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e) {
                com.salesforce.marketingcloud.g.b(c, e, "Unable to update registration table", new Object[0]);
                try {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS registration");
                    sQLiteDatabase.execSQL("CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, badge INTEGER, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, gcm_sender_id VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR )");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(c, e2, "Unable to create registration table", new Object[0]);
                }
                sQLiteDatabase.endTransaction();
            }
            try {
                sQLiteDatabase.beginTransaction();
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT id, attributes FROM registration", null);
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        do {
                            try {
                                ArrayList<a> a2 = a(crypto.decString(rawQuery.getString(rawQuery.getColumnIndex("attributes"))));
                                ArraySet arraySet = new ArraySet();
                                if (!a2.isEmpty()) {
                                    for (int size = a2.size() - 1; size >= 0; size--) {
                                        arraySet.add(a2.get(size));
                                    }
                                    ContentValues contentValues2 = new ContentValues();
                                    contentValues2.put("attributes", crypto.encString(a(arraySet)));
                                    sQLiteDatabase.update(com.salesforce.marketingcloud.storage.db.k.e, contentValues2, "id=?", new String[]{rawQuery.getString(rawQuery.getColumnIndex("id"))});
                                }
                            } catch (Exception e3) {
                                com.salesforce.marketingcloud.g.b(c, "Unable to remove duplicate attributes from row", e3);
                            }
                        } while (rawQuery.moveToNext());
                    }
                    rawQuery.close();
                }
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void b(SQLiteDatabase sQLiteDatabase, Context context, Crypto crypto) {
        e(sQLiteDatabase);
        b(sQLiteDatabase);
        d(sQLiteDatabase);
        c(sQLiteDatabase);
        a(sQLiteDatabase, context, crypto);
        a(sQLiteDatabase);
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
                sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER );");
                sQLiteDatabase.execSQL("DELETE FROM region_message");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException unused) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
                sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER );");
                sQLiteDatabase.execSQL("CREATE TABLE region_message (id INTEGER PRIMARY KEY AUTOINCREMENT, region_id VARCHAR, message_id VARCHAR );");
                sQLiteDatabase.setTransactionSuccessful();
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c, e, "Unable to create location table", new Object[0]);
            }
            try {
                sQLiteDatabase.execSQL("DROP TABLE beacon_request");
                sQLiteDatabase.execSQL("DROP TABLE geofence_request");
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(c, e2, "Unable to drop unused request tables", new Object[0]);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT)");
                sQLiteDatabase.execSQL("INSERT INTO cloud_page_messages SELECT id,start_date,end_date,message_type,content_type,url,subject,read,message_deleted FROM messages WHERE message_type=1 AND content_type=2");
                sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type=1 AND content_type=2");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e) {
                com.salesforce.marketingcloud.g.b(c, e, "Failed to move Messages to CloudPage Messages table.", new Object[0]);
                try {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cloud_page_messages");
                    sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT)");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(c, e2, "Could not create cloud_page_messages table.", new Object[0]);
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
            sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type NOT IN ( 5, 3, 4 )");
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.b(c, e, "Unable to clean unused messages from db.", new Object[0]);
        }
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("ALTER TABLE messages RENAME TO old_messages");
                sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, has_entered SMALLINT, notify_id INTEGER );");
                sQLiteDatabase.execSQL("INSERT INTO messages SELECT id, alert, sound, open_direct, start_date, end_date, message_type, content_type, url, custom, keys, period_show_count, last_shown_date, next_allowed_show, show_count, message_limit, rolling_period, period_type, number_of_periods, messages_per_period, proximity, has_entered, notify_id FROM old_messages");
                sQLiteDatabase.execSQL("DROP TABLE old_messages");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                sQLiteDatabase.endTransaction();
                try {
                    sQLiteDatabase.beginTransaction();
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
                    sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, has_entered SMALLINT, notify_id INTEGER );");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (Exception e3) {
                    com.salesforce.marketingcloud.g.b(c, e3, "Unable a create message table.", new Object[0]);
                }
                com.salesforce.marketingcloud.g.b(c, e2, "Unable to update message table", new Object[0]);
            }
            sQLiteDatabase.endTransaction();
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("ALTER TABLE analytic_item RENAME TO old_analytic_item");
                sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_types VARCHAR, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR)");
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM old_analytic_item", null);
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        do {
                            ContentValues contentValues = new ContentValues();
                            try {
                                contentValues.put("id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
                                contentValues.put(a.C0097a.c, rawQuery.getString(rawQuery.getColumnIndex(a.C0097a.c)));
                                contentValues.put(a.C0097a.i, Integer.valueOf(!TextUtils.isEmpty(rawQuery.getString(rawQuery.getColumnIndex("pi_app_key"))) ? 1 : 0));
                                contentValues.put("analytic_types", rawQuery.getString(rawQuery.getColumnIndex("analytic_types")));
                                contentValues.put("value", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("value"))));
                                contentValues.put(a.C0097a.f, Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex(a.C0097a.f))));
                                contentValues.put(a.C0097a.e, rawQuery.getString(rawQuery.getColumnIndex(a.C0097a.e)));
                                contentValues.put("json_payload", rawQuery.getString(rawQuery.getColumnIndex("json_payload")));
                                sQLiteDatabase.insert(com.salesforce.marketingcloud.storage.db.a.e, null, contentValues);
                            } catch (Exception e) {
                                com.salesforce.marketingcloud.g.b(c, e, "Failed to update item in Analytics local storage during upgrade.", new Object[0]);
                            }
                        } while (rawQuery.moveToNext());
                    }
                    rawQuery.close();
                }
                sQLiteDatabase.execSQL("DROP TABLE old_analytic_item");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e2) {
                com.salesforce.marketingcloud.g.b(c, e2, "Failed to upgrade Analytics local storage.  Starting fresh.  Some analytics items may have been lost.", new Object[0]);
                try {
                    sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR);");
                    sQLiteDatabase.setTransactionSuccessful();
                } catch (SQLException e3) {
                    com.salesforce.marketingcloud.g.b(c, e3, "Failed to create local storage for Analytics.", new Object[0]);
                }
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
    }

    private static ArrayList<a> a(String str) {
        ArrayList<a> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\^\\|\\^");
            int i = 0;
            while (i < split.length) {
                while (true) {
                    String str2 = split[i];
                    if (str2 != null && !str2.isEmpty()) {
                        break;
                    }
                    i++;
                }
                int i2 = i + 1;
                if (i2 >= split.length) {
                    arrayList.add(new a(split[i], ""));
                } else {
                    arrayList.add(new a(split[i], split[i2]));
                }
                i += 2;
            }
        }
        return arrayList;
    }

    private static synchronized String a(Collection<a> collection) {
        synchronized (d.class) {
            if (collection == null) {
                return null;
            }
            ArrayList<a> arrayList = new ArrayList(collection.size());
            Iterator<a> it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            StringBuilder sb = new StringBuilder();
            for (a aVar : arrayList) {
                if (aVar != null) {
                    sb.append(aVar.a);
                    sb.append("^|^");
                    sb.append(aVar.b);
                    sb.append("^|^");
                } else {
                    com.salesforce.marketingcloud.g.e(c, "A null attribute was encountered.", new Object[0]);
                }
            }
            return sb.toString();
        }
    }
}
