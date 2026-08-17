package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.barcode.internal.module.spark.data.C0431j;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningModeHandlerListener;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class v extends NativeSparkScanScanningModeHandlerListener {
    public final u a;
    public final WeakReference b;

    public v(C0431j _SparkScanInternalScanningModeListener, SparkScanStateManager _SparkScanStateManager) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_SparkScanInternalScanningModeListener, "_SparkScanInternalScanningModeListener");
        Intrinsics.checkNotNullParameter(_SparkScanStateManager, "_SparkScanStateManager");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _SparkScanInternalScanningModeListener;
        this.b = new WeakReference(_SparkScanStateManager);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0039, code lost:
    
        r1 = r1.t;
     */
    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningModeHandlerListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onScanningModeChanged(com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode r2, com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode r3) {
        /*
            r1 = this;
            java.lang.String r0 = "previousScanningMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "newScanningMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r0 = r1.b
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager r0 = (com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager) r0
            if (r0 == 0) goto L53
            com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory r0 = com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory.INSTANCE
            com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode r2 = r0.convert(r2)
            com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode r3 = r0.convert(r3)
            com.scandit.datacapture.barcode.internal.module.spark.internal.u r1 = r1.a
            com.scandit.datacapture.barcode.internal.module.spark.data.j r1 = (com.scandit.datacapture.barcode.internal.module.spark.data.C0431j) r1
            r1.getClass()
            java.lang.String r0 = "previousMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "newMode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager r1 = (com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager) r1
            if (r1 == 0) goto L53
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager.a(r1)
            if (r1 == 0) goto L53
            java.util.Iterator r1 = r1.iterator()
        L43:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L53
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.spark.data.k r0 = (com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0432k) r0
            r0.a(r2, r3)
            goto L43
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.internal.v.onScanningModeChanged(com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode, com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode):void");
    }
}
