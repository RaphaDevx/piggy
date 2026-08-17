package ch.coop.supercardapp.containers.app;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.core.helpers.GsonHelper;
import ch.coop.supercardapp.core.helpers.MigrationHelper;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import com.google.gson.Gson;
import com.salesforce.marketingcloud.push.g;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lib.android.paypal.com.magnessdk.c;

/* loaded from: classes3.dex */
public class AppState {
    static final String STORAGE_KEY = "native_app-state";
    private String appInstallationId;
    private final Context context;
    private final PersistentStorage storage;
    private String appLanguage = null;
    private String deviceId = null;
    private final Gson gson = GsonHelper.create();

    static class StoredState {
        String appInstallationId;
        String appLanguage;

        StoredState(String str, String str2) {
            this.appInstallationId = str;
            this.appLanguage = str2;
        }
    }

    public AppState(Context context) {
        this.appInstallationId = null;
        this.context = context;
        this.storage = AppContainer.getInstance(context).getStorage();
        loadState();
        if (this.appInstallationId == null) {
            String widgetUUIDForMigration = MigrationHelper.getWidgetUUIDForMigration(context);
            this.appInstallationId = widgetUUIDForMigration;
            if (widgetUUIDForMigration == null) {
                this.appInstallationId = UUID.randomUUID().toString();
            }
            storeState();
        }
    }

    public String getAppInstallationId() {
        return this.appInstallationId;
    }

    public boolean setAppLanguage(String str) {
        if (Objects.equals(this.appLanguage, str)) {
            return false;
        }
        this.appLanguage = str;
        storeState();
        return true;
    }

    public String getAppLanguage() {
        List m;
        String str = this.appLanguage;
        if (str == null || Build.VERSION.SDK_INT >= 33) {
            str = this.context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{"de", "fr", g.h});
        return !m.contains(str) ? "de" : str;
    }

    public String getDeviceId() {
        if (this.deviceId == null) {
            this.deviceId = Settings.Secure.getString(this.context.getContentResolver(), c.f);
        }
        return this.deviceId;
    }

    private void loadState() {
        StoredState storedState;
        String str = this.storage.get(STORAGE_KEY);
        if (str == null || (storedState = (StoredState) this.gson.fromJson(str, StoredState.class)) == null) {
            return;
        }
        this.appInstallationId = storedState.appInstallationId;
        this.appLanguage = storedState.appLanguage;
    }

    private void storeState() {
        this.storage.set(STORAGE_KEY, this.gson.toJson(new StoredState(this.appInstallationId, this.appLanguage)), false, false);
    }
}
