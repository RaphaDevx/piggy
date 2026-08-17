package ch.coop.capacitor.persistentstorage.daos;

import ch.coop.capacitor.persistentstorage.models.Entry;
import ch.coop.capacitor.persistentstorage.tuples.KeyTuple;
import java.util.List;

/* loaded from: classes3.dex */
public interface EntryDao {
    Integer deleteAll();

    Integer deleteByKey(String input);

    Entry findByKey(String input);

    List<KeyTuple> getKeys();

    void upsert(Entry entry);
}
