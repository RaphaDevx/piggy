package ch.coop.capacitor.persistentstorage.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import ch.coop.capacitor.persistentstorage.models.Entry;
import ch.coop.capacitor.persistentstorage.tuples.KeyTuple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class EntryDao_Impl implements EntryDao {
    private final RoomDatabase __db;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByKey;
    private final EntityUpsertionAdapter<Entry> __upsertionAdapterOfEntry;

    public EntryDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__preparedStmtOfDeleteByKey = new SharedSQLiteStatement(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.EntryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM entry WHERE lookup_key == ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.EntryDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM entry";
            }
        };
        this.__upsertionAdapterOfEntry = new EntityUpsertionAdapter<>(new EntityInsertionAdapter<Entry>(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.EntryDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT INTO `Entry` (`lookup_key`,`value`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Entry value) {
                if (value.key == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.key);
                }
                if (value.value == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.value);
                }
            }
        }, new EntityDeletionOrUpdateAdapter<Entry>(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.EntryDao_Impl.4
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE `Entry` SET `lookup_key` = ?,`value` = ? WHERE `lookup_key` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Entry value) {
                if (value.key == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.key);
                }
                if (value.value == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.value);
                }
                if (value.key == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.key);
                }
            }
        });
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.EntryDao
    public Integer deleteByKey(final String input) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByKey.acquire();
        if (input == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, input);
        }
        this.__db.beginTransaction();
        try {
            Integer valueOf = Integer.valueOf(acquire.executeUpdateDelete());
            this.__db.setTransactionSuccessful();
            return valueOf;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByKey.release(acquire);
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.EntryDao
    public Integer deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            Integer valueOf = Integer.valueOf(acquire.executeUpdateDelete());
            this.__db.setTransactionSuccessful();
            return valueOf;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.EntryDao
    public void upsert(final Entry entry) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__upsertionAdapterOfEntry.upsert((EntityUpsertionAdapter<Entry>) entry);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.EntryDao
    public List<KeyTuple> getKeys() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT lookup_key FROM entry", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KeyTuple keyTuple = new KeyTuple();
                if (query.isNull(0)) {
                    keyTuple.key = null;
                } else {
                    keyTuple.key = query.getString(0);
                }
                arrayList.add(keyTuple);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.EntryDao
    public Entry findByKey(final String input) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM entry WHERE lookup_key == ? LIMIT 1", 1);
        if (input == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, input);
        }
        this.__db.assertNotSuspendingTransaction();
        Entry entry = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "lookup_key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "value");
            if (query.moveToFirst()) {
                Entry entry2 = new Entry();
                if (query.isNull(columnIndexOrThrow)) {
                    entry2.key = null;
                } else {
                    entry2.key = query.getString(columnIndexOrThrow);
                }
                if (query.isNull(columnIndexOrThrow2)) {
                    entry2.value = null;
                } else {
                    entry2.value = query.getString(columnIndexOrThrow2);
                }
                entry = entry2;
            }
            return entry;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
