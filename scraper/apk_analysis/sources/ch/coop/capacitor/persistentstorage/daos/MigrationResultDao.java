package ch.coop.capacitor.persistentstorage.daos;

import ch.coop.capacitor.persistentstorage.models.MigrationResult;

/* loaded from: classes3.dex */
public interface MigrationResultDao {
    MigrationResult get();

    void insert(MigrationResult result);

    void update(MigrationResult result);
}
