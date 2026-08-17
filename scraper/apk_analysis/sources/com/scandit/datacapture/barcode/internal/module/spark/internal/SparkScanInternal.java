package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.barcode.spark.capture.SparkScan$sparkScanListenerInternal$1;
import com.scandit.datacapture.barcode.spark.capture.SparkScanLicenseInfo;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSession;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanLicenseInfo;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class SparkScanInternal implements DataCaptureMode {
    private final /* synthetic */ t a;
    private DataCaptureContext b;
    private final CopyOnWriteArraySet c;
    private final SparkScanSession d;

    public SparkScanInternal(SparkScanSettings sparkScanSettings) {
        NativeSparkScan impl = NativeSparkScan.create(null, sparkScanSettings.getA());
        Intrinsics.checkNotNullExpressionValue(impl, "create(...)");
        Intrinsics.checkNotNullParameter(impl, "impl");
        t tVar = new t(impl);
        this.a = tVar;
        this.c = new CopyOnWriteArraySet();
        this.d = new SparkScanSession(new i(impl));
        tVar.a.addListenerAsync(new s(new h(this), this));
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final NativeDataCaptureMode _dataCaptureModeImpl() {
        return this.a.b;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public final void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.b = dataCaptureContext;
    }

    public final NativeSparkScan a() {
        return this.a.a;
    }

    public final SparkScanSession b() {
        return this.d;
    }

    public final SparkScanLicenseInfo c() {
        NativeSparkScanLicenseInfo sparkScanLicenseInfo = this.a.a.getSparkScanLicenseInfo();
        if (sparkScanLicenseInfo != null) {
            return new SparkScanLicenseInfo(sparkScanLicenseInfo);
        }
        return null;
    }

    public final void d() {
        this.a.a.selectBarcodeWithSmartScanSelectionAsync();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext */
    public final DataCaptureContext getC() {
        return this.b;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final boolean isEnabled() {
        return this.a.a.isEnabled();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final void setEnabled(boolean z) {
        this.a.a.setEnabled(z);
    }

    public final void a(boolean z) {
        this.a.a.enableSingleScanMode(z);
    }

    public final void a(SparkScan$sparkScanListenerInternal$1 listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.c.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    public static void a(SparkScanInternal sparkScanInternal, SparkScanSettings settings) {
        sparkScanInternal.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeWrappedFuture applySettingsWrapped = sparkScanInternal.a.a.applySettingsWrapped(settings.getA());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, (Runnable) null);
    }
}
