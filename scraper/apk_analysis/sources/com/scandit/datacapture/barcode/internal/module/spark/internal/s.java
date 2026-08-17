package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.barcode.spark.capture.SparkScanSession;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanListener;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class s extends NativeSparkScanListener {
    public final j a;
    public final ProxyCache b;
    public final WeakReference c;

    public s(h _SparkScanInternalListener, SparkScanInternal _SparkScanInternal) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_SparkScanInternalListener, "_SparkScanInternalListener");
        Intrinsics.checkNotNullParameter(_SparkScanInternal, "_SparkScanInternal");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _SparkScanInternalListener;
        this.b = proxyCache;
        this.c = new WeakReference(_SparkScanInternal);
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanListener
    public final void onObservationStarted(NativeSparkScan sparkScan) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        SparkScanInternal sparkScanInternal = (SparkScanInternal) this.c.get();
        if (sparkScanInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScan.class), null, sparkScan, new k(sparkScanInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStarted((SparkScanInternal) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanListener
    public final void onObservationStopped(NativeSparkScan sparkScan) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        SparkScanInternal sparkScanInternal = (SparkScanInternal) this.c.get();
        if (sparkScanInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScan.class), null, sparkScan, new l(sparkScanInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            this.a.onObservationStopped((SparkScanInternal) orPut);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanListener
    public final void onScan(NativeSparkScan sparkScan, NativeSparkScanSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        SparkScanInternal sparkScanInternal = (SparkScanInternal) this.c.get();
        if (sparkScanInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScan.class), null, sparkScan, new m(sparkScanInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            SparkScanSession sparkScanSession = (SparkScanSession) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScanSession.class), null, session, new n(sparkScanInternal));
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new o(data));
            this.a.onBarcodeScanned((SparkScanInternal) orPut, sparkScanSession, frameData);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanListener
    public final void onSessionUpdated(NativeSparkScan sparkScan, NativeSparkScanSession session, NativeFrameData data) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        SparkScanInternal sparkScanInternal = (SparkScanInternal) this.c.get();
        if (sparkScanInternal != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScan.class), null, sparkScan, new p(sparkScanInternal));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            SparkScanSession sparkScanSession = (SparkScanSession) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeSparkScanSession.class), null, session, new q(sparkScanInternal));
            FrameData frameData = (FrameData) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeFrameData.class), null, data, new r(data));
            this.a.onSessionUpdated((SparkScanInternal) orPut, sparkScanSession, frameData);
        }
    }
}
