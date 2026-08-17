package ch.coop.supercardapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import ch.coop.auth.Auth;
import ch.coop.capacitor.persistentstorage.PersistentStorage;
import ch.coop.supercardapp.containers.app.AppState;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import ch.coop.supercardapp.containers.employeeDiscountCard.EmployeeDiscountCardState;
import ch.coop.supercardapp.containers.supercard.SupercardState;
import ch.coop.supercardapp.core.data.DataUpdateManager;
import ch.coop.supercardapp.core.data.DataUpdateState;
import ch.coop.supercardapp.core.helpers.APIHelper;

/* loaded from: classes3.dex */
public class AppContainer {
    private static volatile AppContainer instance;
    private APIHelper apiHelper;
    private AppState appState;
    private Auth auth;
    private final Context context;
    private DataUpdateManager dataUpdateManager;
    private DataUpdateState dataUpdateState;
    private EmployeeDiscountCardState employeeDiscountCardState;
    private GiftcardState giftcardState;
    private PersistentStorage storage;
    private SupercardState supercardState;

    public static AppContainer getInstance(Context context) {
        if (instance == null) {
            synchronized (AppContainer.class) {
                if (instance == null) {
                    instance = new AppContainer(context);
                }
            }
        }
        return instance;
    }

    public AppContainer(Context context) {
        this.context = context;
    }

    public PersistentStorage getStorage() {
        if (this.storage == null) {
            this.storage = new PersistentStorage(this.context);
        }
        return this.storage;
    }

    public Auth getAuth() {
        if (this.auth == null) {
            this.auth = Auth.getInstance(this.context);
        }
        return this.auth;
    }

    public DataUpdateManager getDataUpdateManager() {
        if (this.dataUpdateManager == null) {
            Context context = this.context;
            this.dataUpdateManager = new DataUpdateManager(context, AppWidgetManager.getInstance(context));
        }
        return this.dataUpdateManager;
    }

    public DataUpdateState getDataUpdateState() {
        if (this.dataUpdateState == null) {
            this.dataUpdateState = new DataUpdateState(this.context);
        }
        return this.dataUpdateState;
    }

    public AppState getAppState() {
        if (this.appState == null) {
            this.appState = new AppState(this.context);
        }
        return this.appState;
    }

    public SupercardState getSupercardState() {
        if (this.supercardState == null) {
            this.supercardState = new SupercardState(this.context);
        }
        return this.supercardState;
    }

    public GiftcardState getGiftcardState() {
        if (this.giftcardState == null) {
            this.giftcardState = new GiftcardState(this.context);
        }
        return this.giftcardState;
    }

    public EmployeeDiscountCardState getEmployeeDiscountCardState() {
        if (this.employeeDiscountCardState == null) {
            this.employeeDiscountCardState = new EmployeeDiscountCardState(this.context);
        }
        return this.employeeDiscountCardState;
    }

    public APIHelper getApiHelper() {
        if (this.apiHelper == null) {
            this.apiHelper = new APIHelper();
        }
        return this.apiHelper;
    }
}
