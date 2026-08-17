package com.salesforce.marketingcloud.sfmcsdk.components.storage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptedSharedPreferences;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptionManager;
import com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StorageManager.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/storage/StorageManager;", "", "context", "Landroid/content/Context;", "encryptionManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptionManager;", "moduleAppId", "", "registrationId", "(Landroid/content/Context;Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptionManager;Ljava/lang/String;Ljava/lang/String;)V", "getSecurePrefs", "Landroid/content/SharedPreferences;", "name", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class StorageManager {
    private final Context context;
    private final EncryptionManager encryptionManager;
    private final String moduleAppId;
    private final String registrationId;

    public StorageManager(Context context, EncryptionManager encryptionManager, String moduleAppId, String registrationId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(encryptionManager, "encryptionManager");
        Intrinsics.checkNotNullParameter(moduleAppId, "moduleAppId");
        Intrinsics.checkNotNullParameter(registrationId, "registrationId");
        this.context = context;
        this.encryptionManager = encryptionManager;
        this.moduleAppId = moduleAppId;
        this.registrationId = registrationId;
    }

    public final SharedPreferences getSecurePrefs(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return EncryptedSharedPreferences.INSTANCE.create(this.context, FileUtilsKt.getFilenameForModuleInstallation(name, this.moduleAppId, this.registrationId), this.encryptionManager.getEncryptionKey());
    }
}
