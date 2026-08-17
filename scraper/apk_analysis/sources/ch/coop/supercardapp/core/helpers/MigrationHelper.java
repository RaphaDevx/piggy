package ch.coop.supercardapp.core.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import io.sentry.Sentry;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* loaded from: classes3.dex */
public class MigrationHelper {
    static final String MIGRATION_DONE_NAME = "migrationToPersistentStorageDone";
    static final String TAG = "MigrationHelper";
    static final String UUID_NAME = "SupercardWidgetUUID";

    public static String getWidgetUUIDForMigration(Context context) {
        try {
            return createEncryptedSharedPreferences(context).getString(UUID_NAME, null);
        } catch (Exception unused) {
            return null;
        }
    }

    protected static SharedPreferences createEncryptedSharedPreferences(Context context) {
        try {
            return EncryptedSharedPreferences.create(context, "widget", new MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (IOException | GeneralSecurityException e) {
            Sentry.captureException(e);
            return null;
        }
    }
}
