package com.salesforce.marketingcloud.sfmcsdk;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManager;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptedSharedPreferences;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptionManager;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import com.salesforce.marketingcloud.sfmcsdk.components.http.Authenticator;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.storage.StorageManager;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SFMCSdkComponents.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 /2\u00020\u0001:\u0001/B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010+\u001a\u00020,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0007R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u00060"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkComponents;", "", "context", "Landroid/content/Context;", "moduleName", "", "moduleApplicationId", "behaviorManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManager;", "eventManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/EventManager;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManager;Lcom/salesforce/marketingcloud/sfmcsdk/components/events/EventManager;)V", "getBehaviorManager", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManager;", "getContext$sfmcsdk_release", "()Landroid/content/Context;", "encryptionChanged", "", "getEncryptionChanged", "()Z", "encryptionManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptionManager;", "getEncryptionManager", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptionManager;", "getEventManager", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/events/EventManager;", "executors", "Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;", "getExecutors", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;", "identity", "Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Identity;", "getIdentity", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Identity;", "getModuleApplicationId", "()Ljava/lang/String;", "getModuleName$sfmcsdk_release", SFMCSdkComponents.KEY_PREFS_REGISTRATION_ID, "getRegistrationId", "storageManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/storage/StorageManager;", "getStorageManager", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/storage/StorageManager;", "createNetworkManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/NetworkManager;", "authenticator", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Authenticator;", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SFMCSdkComponents {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_PREFS_REGISTRATION_ID = "registrationId";
    private static final String REGISTRATION_ID_STORAGE = "unified_sdk_registration";

    @Deprecated
    public static final String TAG = "SFMCSdkComponents";
    private final BehaviorManager behaviorManager;
    private final Context context;
    private final boolean encryptionChanged;
    private final EncryptionManager encryptionManager;
    private final EventManager eventManager;
    private final SdkExecutors executors;
    private final Identity identity;
    private final String moduleApplicationId;
    private final String moduleName;
    private final String registrationId;
    private final StorageManager storageManager;

    public final NetworkManager createNetworkManager() {
        return createNetworkManager$default(this, null, 1, null);
    }

    public SFMCSdkComponents(Context context, String moduleName, String moduleApplicationId, BehaviorManager behaviorManager, EventManager eventManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(moduleApplicationId, "moduleApplicationId");
        Intrinsics.checkNotNullParameter(behaviorManager, "behaviorManager");
        Intrinsics.checkNotNullParameter(eventManager, "eventManager");
        this.context = context;
        this.moduleName = moduleName;
        this.moduleApplicationId = moduleApplicationId;
        this.behaviorManager = behaviorManager;
        this.eventManager = eventManager;
        EncryptionManager encryptionManager = new EncryptionManager(context, moduleApplicationId);
        this.encryptionManager = encryptionManager;
        this.executors = new SdkExecutors(null, null, 3, null);
        EncryptedSharedPreferences.Companion companion = EncryptedSharedPreferences.INSTANCE;
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        SharedPreferences create = companion.create(context, REGISTRATION_ID_STORAGE, new EncryptionManager(context, packageName).getEncryptionKey());
        Intrinsics.checkNotNull(create, "null cannot be cast to non-null type com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptedSharedPreferences");
        EncryptedSharedPreferences encryptedSharedPreferences = (EncryptedSharedPreferences) create;
        boolean verifyEncryption = encryptedSharedPreferences.verifyEncryption();
        this.encryptionChanged = !verifyEncryption;
        if (!verifyEncryption) {
            encryptedSharedPreferences.clearInstallationPrefs();
        }
        final String string = create.getString(KEY_PREFS_REGISTRATION_ID, null);
        if (string != null) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "StorageManager was initialized with existing install id: " + string;
                }
            });
        } else {
            string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            encryptedSharedPreferences.edit().putString(KEY_PREFS_REGISTRATION_ID, string).apply();
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "StorageManager was initialized with new install id: " + string;
                }
            });
        }
        this.registrationId = string;
        this.storageManager = new StorageManager(context, encryptionManager, moduleApplicationId, string);
        this.identity = Identity.INSTANCE.create$sfmcsdk_release(string);
    }

    /* renamed from: getContext$sfmcsdk_release, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    /* renamed from: getModuleName$sfmcsdk_release, reason: from getter */
    public final String getModuleName() {
        return this.moduleName;
    }

    public final String getModuleApplicationId() {
        return this.moduleApplicationId;
    }

    public final BehaviorManager getBehaviorManager() {
        return this.behaviorManager;
    }

    public final EventManager getEventManager() {
        return this.eventManager;
    }

    public final EncryptionManager getEncryptionManager() {
        return this.encryptionManager;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public final SdkExecutors getExecutors() {
        return this.executors;
    }

    public final String getRegistrationId() {
        return this.registrationId;
    }

    public final Identity getIdentity() {
        return this.identity;
    }

    public final boolean getEncryptionChanged() {
        return this.encryptionChanged;
    }

    public static /* synthetic */ NetworkManager createNetworkManager$default(SFMCSdkComponents sFMCSdkComponents, Authenticator authenticator, int i, Object obj) {
        if ((i & 1) != 0) {
            authenticator = null;
        }
        return sFMCSdkComponents.createNetworkManager(authenticator);
    }

    public final NetworkManager createNetworkManager(Authenticator authenticator) {
        return new NetworkManager(this.context, this.executors, this.storageManager.getSecurePrefs("network_manager"), authenticator);
    }

    /* compiled from: SFMCSdkComponents.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkComponents$Companion;", "", "()V", "KEY_PREFS_REGISTRATION_ID", "", "REGISTRATION_ID_STORAGE", "TAG", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
