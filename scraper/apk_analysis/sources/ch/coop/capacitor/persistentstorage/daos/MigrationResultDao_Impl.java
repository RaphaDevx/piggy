package ch.coop.capacitor.persistentstorage.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import ch.coop.capacitor.persistentstorage.models.MigrationResult;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class MigrationResultDao_Impl implements MigrationResultDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<MigrationResult> __insertionAdapterOfMigrationResult;
    private final EntityDeletionOrUpdateAdapter<MigrationResult> __updateAdapterOfMigrationResult;

    public MigrationResultDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMigrationResult = new EntityInsertionAdapter<MigrationResult>(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.MigrationResultDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `MigrationResult` (`id`,`completed`) VALUES (nullif(?, 0),?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MigrationResult migrationResult) {
                supportSQLiteStatement.bindLong(1, migrationResult.id);
                if ((migrationResult.completed == null ? null : Integer.valueOf(migrationResult.completed.booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, r2.intValue());
                }
            }
        };
        this.__updateAdapterOfMigrationResult = new EntityDeletionOrUpdateAdapter<MigrationResult>(this, __db) { // from class: ch.coop.capacitor.persistentstorage.daos.MigrationResultDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR REPLACE `MigrationResult` SET `id` = ?,`completed` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MigrationResult migrationResult) {
                supportSQLiteStatement.bindLong(1, migrationResult.id);
                if ((migrationResult.completed == null ? null : Integer.valueOf(migrationResult.completed.booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, r3.intValue());
                }
                supportSQLiteStatement.bindLong(3, migrationResult.id);
            }
        };
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.MigrationResultDao
    public void insert(final MigrationResult result) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMigrationResult.insert((EntityInsertionAdapter<MigrationResult>) result);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.MigrationResultDao
    public void update(final MigrationResult result) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMigrationResult.handle(result);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // ch.coop.capacitor.persistentstorage.daos.MigrationResultDao
    public MigrationResult get() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM migrationresult LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        MigrationResult migrationResult = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "completed");
            if (query.moveToFirst()) {
                MigrationResult migrationResult2 = new MigrationResult();
                migrationResult2.id = query.getInt(columnIndexOrThrow);
                Integer valueOf2 = query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                migrationResult2.completed = valueOf;
                migrationResult = migrationResult2;
            }
            return migrationResult;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
