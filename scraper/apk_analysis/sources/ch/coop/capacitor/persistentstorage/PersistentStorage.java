package ch.coop.capacitor.persistentstorage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import ch.coop.capacitor.persistentstorage.daos.EntryDao;
import ch.coop.capacitor.persistentstorage.daos.MigrationResultDao;
import ch.coop.capacitor.persistentstorage.models.Entry;
import ch.coop.capacitor.persistentstorage.tuples.KeyTuple;
import io.sentry.Sentry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class PersistentStorage {
    public static final String KEY_ALIAS = "coopch_secure_storage_key";
    public static final String LARGE_FILE_STORAGE_PREFIX = "coopch_large_file_storage:";
    public static final String SECURE_STORAGE_PREFIX = "coopch_secure_storage_";
    public EntryDao entryDao;
    public LargeFileStorage largeFileStorage;
    public MigrationResultDao migrationResultDao;
    public CryptoManager cryptoManager = new CryptoManager(KEY_ALIAS);
    public Thread mainThread = Looper.getMainLooper().getThread();
    public ExecutorService executorService = Executors.newSingleThreadExecutor();

    public PersistentStorage(Context appContext) {
        this.entryDao = CoopchDatabase.getInstance(appContext).entryDao();
        this.migrationResultDao = CoopchDatabase.getInstance(appContext).migrationResultDao();
        this.largeFileStorage = new LargeFileStorage(appContext);
    }

    public String get(final String key) {
        return (String) ensureBackgroundThread(new Callable() { // from class: ch.coop.capacitor.persistentstorage.PersistentStorage$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String lambda$get$0;
                lambda$get$0 = PersistentStorage.this.lambda$get$0(key);
                return lambda$get$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$get$0(String str) throws Exception {
        Entry findByKey = this.entryDao.findByKey(str);
        if (findByKey == null) {
            return null;
        }
        String str2 = findByKey.value;
        if (!TextUtils.isEmpty(str2) && str2.startsWith(LARGE_FILE_STORAGE_PREFIX)) {
            str2 = this.largeFileStorage.readFile(str);
        }
        return (TextUtils.isEmpty(str2) || !str2.startsWith(SECURE_STORAGE_PREFIX)) ? str2 : this.cryptoManager.decrypt(str2.substring(22));
    }

    public void set(final String key, final String value, final Boolean encrypted, final Boolean largeFileStorageAndroid) {
        ensureBackgroundThread(new Runnable() { // from class: ch.coop.capacitor.persistentstorage.PersistentStorage$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PersistentStorage.this.lambda$set$1(value, encrypted, largeFileStorageAndroid, key);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$set$1(String str, Boolean bool, Boolean bool2, String str2) {
        String writeFile;
        String encrypt;
        if (bool.booleanValue() && (encrypt = this.cryptoManager.encrypt(str)) != null) {
            str = SECURE_STORAGE_PREFIX + encrypt;
        }
        if (bool2.booleanValue() && (writeFile = this.largeFileStorage.writeFile(str2, str)) != null) {
            str = LARGE_FILE_STORAGE_PREFIX + writeFile;
        }
        Entry entry = new Entry();
        entry.key = str2;
        entry.value = str;
        this.entryDao.upsert(entry);
    }

    public void remove(final String key) {
        ensureBackgroundThread(new Runnable() { // from class: ch.coop.capacitor.persistentstorage.PersistentStorage$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PersistentStorage.this.lambda$remove$2(key);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$remove$2(String str) {
        this.entryDao.deleteByKey(str);
    }

    public ArrayList<String> keys() {
        return (ArrayList) ensureBackgroundThread(new Callable() { // from class: ch.coop.capacitor.persistentstorage.PersistentStorage$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ArrayList lambda$keys$3;
                lambda$keys$3 = PersistentStorage.this.lambda$keys$3();
                return lambda$keys$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ArrayList lambda$keys$3() throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator<KeyTuple> it = this.entryDao.getKeys().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().key);
        }
        return arrayList;
    }

    public void clear() {
        ensureBackgroundThread(new Runnable() { // from class: ch.coop.capacitor.persistentstorage.PersistentStorage$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PersistentStorage.this.lambda$clear$4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clear$4() {
        this.entryDao.deleteAll();
    }

    private void ensureBackgroundThread(Runnable codeBlock) {
        if (isOnBackgroundThread()) {
            codeBlock.run();
        } else {
            this.executorService.execute(codeBlock);
        }
    }

    public <T> T ensureBackgroundThread(Callable<T> codeBlock) {
        if (isOnBackgroundThread()) {
            try {
                return codeBlock.call();
            } catch (Exception e) {
                Sentry.captureException(e);
            }
        } else {
            try {
                return this.executorService.submit(codeBlock).get();
            } catch (Exception e2) {
                Sentry.captureException(e2);
            }
        }
        return null;
    }

    private boolean isOnBackgroundThread() {
        return Thread.currentThread() != this.mainThread;
    }
}
