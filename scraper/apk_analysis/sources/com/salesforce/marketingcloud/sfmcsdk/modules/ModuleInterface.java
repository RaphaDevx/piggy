package com.salesforce.marketingcloud.sfmcsdk.modules;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: ModuleInterface.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "", "moduleIdentity", "Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/ModuleIdentity;", "getModuleIdentity", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/ModuleIdentity;", SentryThread.JsonKeys.STATE, "Lorg/json/JSONObject;", "getState", "()Lorg/json/JSONObject;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface ModuleInterface {
    ModuleIdentity getModuleIdentity();

    JSONObject getState();
}
