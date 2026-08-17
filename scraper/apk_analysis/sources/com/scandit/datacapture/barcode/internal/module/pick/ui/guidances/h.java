package com.scandit.datacapture.barcode.internal.module.pick.ui.guidances;

import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h extends NativeBarcodePickGuidanceHandler {
    public final BarcodePickGuidanceHandler a;

    public h(BarcodePickGuidanceHandler _BarcodePickGuidanceHandler) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodePickGuidanceHandler, "_BarcodePickGuidanceHandler");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodePickGuidanceHandler;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler
    public final void hideLoadingPopup() {
        this.a.a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler
    public final void setInitialGuidance(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.b(z, text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler
    public final void setMoveCloserGuidance(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.a(z, text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler
    public final void setTapShutterToPauseGuidance(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.c(z, text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickGuidanceHandler
    public final void showLoadingPopup(boolean z) {
        this.a.a(z);
    }
}
