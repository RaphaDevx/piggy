package com.scandit.datacapture.barcode.internal.module.find.ui.overlay;

import com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g extends NativeBarcodeFindGuidanceHandler {
    public final BarcodeFindGuidanceHandler a;
    public final WeakReference b;

    public g(BarcodeFindGuidanceHandler _BarcodeFindGuidanceHandler, BarcodeFindBasicOverlay _BarcodeFindBasicOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodeFindGuidanceHandler, "_BarcodeFindGuidanceHandler");
        Intrinsics.checkNotNullParameter(_BarcodeFindBasicOverlay, "_BarcodeFindBasicOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeFindGuidanceHandler;
        this.b = new WeakReference(_BarcodeFindBasicOverlay);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setAllItemsFound(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.f(z, text);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setInitialGuidance(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.b(z, text);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setItemListUpdated(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.c(z, text);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setMoveCloserGuidance(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.a(z, text);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setTapShutterToPause(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.e(z, text);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindGuidanceHandler
    public final void setTapShutterToResume(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (((BarcodeFindBasicOverlay) this.b.get()) != null) {
            this.a.d(z, text);
        }
    }
}
