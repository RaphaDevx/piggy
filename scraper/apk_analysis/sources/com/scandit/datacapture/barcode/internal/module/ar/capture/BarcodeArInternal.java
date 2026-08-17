package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListener;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListenerReversedAdapter;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSettings;
import com.scandit.datacapture.barcode.ar.feedback.BarcodeArFeedback;
import com.scandit.datacapture.barcode.internal.module.ar.feedback.BarcodeArFeedbackEmitter;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class BarcodeArInternal implements DataCaptureMode {
    private final BarcodeArSession a;
    private final /* synthetic */ c b;
    private BarcodeArFeedbackEmitter c;
    private BarcodeArFeedback d;
    private BarcodeAr e;
    private DataCaptureContext f;
    private final CopyOnWriteArraySet g;

    public BarcodeArInternal(NativeBarcodeAr impl) {
        BarcodeArSession session = new BarcodeArSession(new a(impl));
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(session, "session");
        this.a = session;
        this.b = new c(impl);
        this.d = BarcodeArFeedback.INSTANCE.defaultFeedback();
        this.g = new CopyOnWriteArraySet();
        ProxyCacheKt.getGlobalProxyCache().put(Reflection.getOrCreateKotlinClass(NativeBarcodeAr.class), null, impl, this);
        impl.addListenerAsync(new BarcodeArListenerReversedAdapter(new b(this), this, null, 4, null));
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final NativeDataCaptureMode _dataCaptureModeImpl() {
        return this.b.b;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public final void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.f = dataCaptureContext;
    }

    public final void a(BarcodeArSettings settings, Runnable runnable) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeWrappedFuture applySettingsWrapped = this.b.a.applySettingsWrapped(settings.getA());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, runnable);
    }

    public final boolean b(BarcodeArListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.g.remove(listener);
    }

    public final BarcodeAr c() {
        BarcodeAr barcodeAr = this.e;
        if (barcodeAr != null) {
            return barcodeAr;
        }
        BarcodeAr barcodeAr2 = new BarcodeAr(this);
        this.e = barcodeAr2;
        return barcodeAr2;
    }

    public final BarcodeArFeedback d() {
        return this.d;
    }

    public final BarcodeArFeedbackEmitter e() {
        return this.c;
    }

    public final void f() {
        this.b.a.pauseAsync();
    }

    public final void g() {
        this.b.a.startAsync();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext */
    public final DataCaptureContext getC() {
        return this.f;
    }

    public final void h() {
        this.b.a.stopAsync();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final boolean isEnabled() {
        return this.b.a.isEnabled();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final void setEnabled(boolean z) {
        this.b.a.setEnabled(z);
    }

    public final BarcodeArSession b() {
        return this.a;
    }

    public final NativeBarcodeAr a() {
        return this.b.a;
    }

    public final void a(BarcodeArFeedbackEmitter barcodeArFeedbackEmitter) {
        this.c = barcodeArFeedbackEmitter;
        if (barcodeArFeedbackEmitter != null) {
            barcodeArFeedbackEmitter.a(this.d);
        }
    }

    public final void a(BarcodeArFeedback value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.d = value;
        BarcodeArFeedbackEmitter barcodeArFeedbackEmitter = this.c;
        if (barcodeArFeedbackEmitter != null) {
            barcodeArFeedbackEmitter.a(value);
        }
    }

    public final void a(BarcodeAr value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.e = value;
    }

    public final boolean a(BarcodeArListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.g.add(listener);
    }
}
