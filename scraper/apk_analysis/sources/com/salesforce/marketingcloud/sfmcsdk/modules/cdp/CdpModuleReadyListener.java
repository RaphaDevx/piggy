package com.salesforce.marketingcloud.sfmcsdk.modules.cdp;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CdpModuleInterface.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModuleReadyListener;", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyListener;", "ready", "", "module", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModuleInterface;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface CdpModuleReadyListener extends ModuleReadyListener {
    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
    void ready(ModuleInterface module);

    void ready(CdpModuleInterface module);

    /* compiled from: CdpModuleInterface.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static void ready(CdpModuleReadyListener cdpModuleReadyListener, ModuleInterface module) {
            Intrinsics.checkNotNullParameter(module, "module");
            cdpModuleReadyListener.ready((CdpModuleInterface) module);
        }
    }
}
