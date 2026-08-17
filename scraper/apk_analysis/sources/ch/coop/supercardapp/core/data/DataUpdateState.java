package ch.coop.supercardapp.core.data;

import android.content.Context;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.containers.cards.CardIdentifier;
import ch.coop.supercardapp.core.data.DataUpdateState;
import ch.coop.supercardapp.core.helpers.GsonHelper;
import com.google.gson.Gson;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class DataUpdateState {
    public static final String STORAGE_KEY = "native_data-update-state";
    private OffsetDateTime lastDataUpdate;
    private String loginIdentifier;
    private final PersistentStorage storage;
    private HashMap<Integer, CardIdentifier> widgetCardMap;
    private boolean dataUpdateInProgress = false;
    private final List<DataUpdateStateListener> dataUpdateStateListeners = new ArrayList();
    private Boolean needsReauthorization = false;
    private final Gson gson = GsonHelper.create();

    public interface DataUpdateStateListener {
        void onDataUpdateCompleted();

        void onDataUpdateStarted();
    }

    public static class StoredState {
        OffsetDateTime lastDataUpdate;
        String loginIdentifier;
        Boolean needsReauthorization;
        HashMap<Integer, CardIdentifier> widgetCardMap;

        StoredState(OffsetDateTime offsetDateTime, String str, Boolean bool, HashMap<Integer, CardIdentifier> hashMap) {
            this.lastDataUpdate = offsetDateTime;
            this.loginIdentifier = str;
            this.needsReauthorization = bool;
            this.widgetCardMap = hashMap;
        }
    }

    public DataUpdateState(Context context) {
        this.storage = AppContainer.getInstance(context).getStorage();
        loadState();
    }

    public void dataUpdateStarted() {
        this.dataUpdateInProgress = true;
        this.dataUpdateStateListeners.forEach(new Consumer() { // from class: ch.coop.supercardapp.core.data.DataUpdateState$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DataUpdateState.DataUpdateStateListener) obj).onDataUpdateStarted();
            }
        });
    }

    public void dataUpdateCompleted() {
        this.lastDataUpdate = OffsetDateTime.now();
        storeState();
        this.dataUpdateInProgress = false;
        this.dataUpdateStateListeners.forEach(new Consumer() { // from class: ch.coop.supercardapp.core.data.DataUpdateState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((DataUpdateState.DataUpdateStateListener) obj).onDataUpdateCompleted();
            }
        });
    }

    public void addDataUpdateStateListener(DataUpdateStateListener dataUpdateStateListener) {
        if (this.dataUpdateStateListeners.contains(dataUpdateStateListener)) {
            return;
        }
        this.dataUpdateStateListeners.add(dataUpdateStateListener);
    }

    public void removeDataUpdateStateListener(DataUpdateStateListener dataUpdateStateListener) {
        if (this.dataUpdateStateListeners.contains(dataUpdateStateListener)) {
            this.dataUpdateStateListeners.remove(dataUpdateStateListener);
        }
    }

    public boolean isDataUpdateInProgress() {
        return this.dataUpdateInProgress;
    }

    public Optional<OffsetDateTime> getLastDataUpdate() {
        return Optional.ofNullable(this.lastDataUpdate);
    }

    public Optional<String> getLoginIdentifier() {
        return Optional.ofNullable(this.loginIdentifier);
    }

    public void setLoginIdentifier(@Nullable String str) {
        if (Objects.equals(str, this.loginIdentifier)) {
            return;
        }
        this.loginIdentifier = str;
        storeState();
    }

    public Boolean getNeedsReauthorization() {
        return this.needsReauthorization;
    }

    public void setNeedsReauthorization(Boolean bool) {
        if (bool != this.needsReauthorization) {
            this.needsReauthorization = bool;
            storeState();
        }
    }

    public CardIdentifier getCardIdentifierForWidgetId(int i) {
        HashMap<Integer, CardIdentifier> hashMap = this.widgetCardMap;
        if (hashMap != null) {
            return hashMap.get(Integer.valueOf(i));
        }
        return null;
    }

    public void setCardIdentifierForWidgetId(int i, CardIdentifier cardIdentifier) {
        if (this.widgetCardMap == null) {
            this.widgetCardMap = new HashMap<>();
        }
        this.widgetCardMap.put(Integer.valueOf(i), cardIdentifier);
        storeState();
    }

    public void removeCardIdentifierForWidgetIds(int[] iArr) {
        if (this.widgetCardMap != null) {
            for (int i : iArr) {
                this.widgetCardMap.remove(Integer.valueOf(i));
            }
            storeState();
        }
    }

    private void loadState() {
        StoredState storedState;
        String str = this.storage.get(STORAGE_KEY);
        if (str == null || (storedState = (StoredState) this.gson.fromJson(str, StoredState.class)) == null) {
            return;
        }
        this.lastDataUpdate = storedState.lastDataUpdate;
        this.loginIdentifier = storedState.loginIdentifier;
        this.needsReauthorization = storedState.needsReauthorization;
        this.widgetCardMap = storedState.widgetCardMap;
    }

    private void storeState() {
        this.storage.set(STORAGE_KEY, this.gson.toJson(new StoredState(this.lastDataUpdate, this.loginIdentifier, this.needsReauthorization, this.widgetCardMap)), false, false);
    }
}
