package ch.coop.capacitor.persistentstorage;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import ch.coop.capacitor.persistentstorage.daos.EntryDao;
import ch.coop.capacitor.persistentstorage.daos.MigrationResultDao;

/* loaded from: classes3.dex */
public abstract class CoopchDatabase extends RoomDatabase {
    private static volatile CoopchDatabase INSTANCE;

    public abstract EntryDao entryDao();

    public abstract MigrationResultDao migrationResultDao();

    public static CoopchDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CoopchDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = (CoopchDatabase) Room.databaseBuilder(context, CoopchDatabase.class, "CoopchDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}
