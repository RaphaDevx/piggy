package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class x extends NativeBarcodePickStatusListener {
    public final BarcodePickStatusListenerInternal a;
    public final WeakReference b;

    public x(com.scandit.datacapture.barcode.pick.capture.a _BarcodePickStatusListenerInternal, BarcodePick _BarcodePick) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodePickStatusListenerInternal, "_BarcodePickStatusListenerInternal");
        Intrinsics.checkNotNullParameter(_BarcodePick, "_BarcodePick");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodePickStatusListenerInternal;
        this.b = new WeakReference(_BarcodePick);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onFreezed() {
        if (((BarcodePick) this.b.get()) != null) {
            this.a.c();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onObservationStarted(NativeBarcodePick barcodePick) {
        Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        if (((BarcodePick) this.b.get()) != null) {
            this.a.onObservationStarted(BarcodeNativeTypeFactory.INSTANCE.convert(barcodePick));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onObservationStopped(NativeBarcodePick barcodePick) {
        Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        if (((BarcodePick) this.b.get()) != null) {
            this.a.onObservationStopped(BarcodeNativeTypeFactory.INSTANCE.convert(barcodePick));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onPaused() {
        if (((BarcodePick) this.b.get()) != null) {
            this.a.d();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onStarted() {
        if (((BarcodePick) this.b.get()) != null) {
            this.a.a();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickStatusListener
    public final void onStopped() {
        if (((BarcodePick) this.b.get()) != null) {
            this.a.b();
        }
    }
}
