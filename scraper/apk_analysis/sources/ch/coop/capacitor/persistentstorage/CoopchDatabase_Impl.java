package ch.coop.capacitor.persistentstorage;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import ch.coop.capacitor.persistentstorage.daos.EntryDao;
import ch.coop.capacitor.persistentstorage.daos.EntryDao_Impl;
import ch.coop.capacitor.persistentstorage.daos.MigrationResultDao;
import ch.coop.capacitor.persistentstorage.daos.MigrationResultDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class CoopchDatabase_Impl extends CoopchDatabase {
    private volatile EntryDao _entryDao;
    private volatile MigrationResultDao _migrationResultDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: ch.coop.capacitor.persistentstorage.CoopchDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Entry` (`lookup_key` TEXT NOT NULL, `value` TEXT, PRIMARY KEY(`lookup_key`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `MigrationResult` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `completed` INTEGER)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8ca1d78099712b538168ea2b329076b0')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Entry`");
                _db.execSQL("DROP TABLE IF EXISTS `MigrationResult`");
                if (CoopchDatabase_Impl.this.mCallbacks != null) {
                    int size = CoopchDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CoopchDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (CoopchDatabase_Impl.this.mCallbacks != null) {
                    int size = CoopchDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CoopchDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                CoopchDatabase_Impl.this.mDatabase = _db;
                CoopchDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (CoopchDatabase_Impl.this.mCallbacks != null) {
                    int size = CoopchDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CoopchDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(2);
                hashMap.put("lookup_key", new TableInfo.Column("lookup_key", "TEXT", true, 1, null, 1));
                hashMap.put("value", new TableInfo.Column("value", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("Entry", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(_db, "Entry");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "Entry(ch.coop.capacitor.persistentstorage.models.Entry).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("completed", new TableInfo.Column("completed", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("MigrationResult", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(_db, "MigrationResult");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "MigrationResult(ch.coop.capacitor.persistentstorage.models.MigrationResult).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "8ca1d78099712b538168ea2b329076b0", "b2cc36ac7ead5e5337ac62bcbdbcc81e")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Entry", "MigrationResult");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Entry`");
            writableDatabase.execSQL("DELETE FROM `MigrationResult`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(EntryDao.class, EntryDao_Impl.getRequiredConverters());
        hashMap.put(MigrationResultDao.class, MigrationResultDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // ch.coop.capacitor.persistentstorage.CoopchDatabase
    public EntryDao entryDao() {
        EntryDao entryDao;
        if (this._entryDao != null) {
            return this._entryDao;
        }
        synchronized (this) {
            if (this._entryDao == null) {
                this._entryDao = new EntryDao_Impl(this);
            }
            entryDao = this._entryDao;
        }
        return entryDao;
    }

    @Override // ch.coop.capacitor.persistentstorage.CoopchDatabase
    public MigrationResultDao migrationResultDao() {
        MigrationResultDao migrationResultDao;
        if (this._migrationResultDao != null) {
            return this._migrationResultDao;
        }
        synchronized (this) {
            if (this._migrationResultDao == null) {
                this._migrationResultDao = new MigrationResultDao_Impl(this);
            }
            migrationResultDao = this._migrationResultDao;
        }
        return migrationResultDao;
    }
}
