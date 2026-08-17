package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class h extends NativeBarcodeFindListener {
    public final InternalBarcodeFindListener a;
    public final ProxyCache b;
    public final WeakReference c;

    public h(InternalBarcodeFindListener _InternalBarcodeFindListener, BarcodeFind _BarcodeFind) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_InternalBarcodeFindListener, "_InternalBarcodeFindListener");
        Intrinsics.checkNotNullParameter(_BarcodeFind, "_BarcodeFind");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _InternalBarcodeFindListener;
        this.b = proxyCache;
        this.c = new WeakReference(_BarcodeFind);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onItemListUpdatedInternal(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        if (((BarcodeFind) this.c.get()) != null) {
            this.a.c(barcodes);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onObservationStarted(NativeBarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeFind barcodeFind = (BarcodeFind) this.c.get();
        if (barcodeFind != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode, new d(barcodeFind));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.a((BarcodeFind) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onObservationStopped(NativeBarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeFind barcodeFind = (BarcodeFind) this.c.get();
        if (barcodeFind != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode, new e(barcodeFind));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.b((BarcodeFind) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onSearchPaused(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        if (((BarcodeFind) this.c.get()) != null) {
            this.a.a(barcodes);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onSearchStarted() {
        if (((BarcodeFind) this.c.get()) != null) {
            this.a.onSearchStarted();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onSearchStopped(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        if (((BarcodeFind) this.c.get()) != null) {
            this.a.b(barcodes);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindListener
    public final void onSessionUpdated(NativeBarcodeFind mode, NativeBarcodeFindSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeFind barcodeFind = (BarcodeFind) this.c.get();
        if (barcodeFind != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode, new f(barcodeFind));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new g(data));
            this.a.a((BarcodeFind) orPut, session, frameData);
        }
    }
}
