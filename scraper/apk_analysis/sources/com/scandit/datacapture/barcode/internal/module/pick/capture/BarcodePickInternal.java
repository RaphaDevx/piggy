package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListenerKt;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickListenerReversedAdapter;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListenerReversedAdapter;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSelectItemActionCallback;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSelectItemActionCallbackKt;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class BarcodePickInternal implements DataCaptureMode {
    private final v a;
    private final BarcodePickScanningSession b;
    private final /* synthetic */ t c;
    private BarcodePick d;
    private final CopyOnWriteArraySet e;
    private final CopyOnWriteArraySet f;
    private final CopyOnWriteArraySet g;
    private final CopyOnWriteArraySet h;
    private DataCaptureContext i;

    public BarcodePickInternal(NativeBarcodePick impl) {
        v session = new v(new C0421a(impl));
        BarcodePickScanningSession scanningSession = new BarcodePickScanningSession(new b(impl));
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(scanningSession, "scanningSession");
        this.a = session;
        this.b = scanningSession;
        t tVar = new t(impl);
        this.c = tVar;
        this.e = new CopyOnWriteArraySet();
        this.f = new CopyOnWriteArraySet();
        this.g = new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet();
        NativeDataCaptureContext context = tVar.a.getContext();
        this.i = context != null ? (DataCaptureContext) tVar.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new s(context)) : null;
        impl.addListenerAsync(new r(new f(this), this));
        impl.addActionListenerAsync(BarcodePickActionListenerKt.asNative(new c(this)));
        impl.addScanningListenerAsync(new BarcodePickScanningListenerReversedAdapter(new e(this), this, null, 4, null));
        impl.addPublicListenerAsync(new BarcodePickListenerReversedAdapter(new d(this), this, null, 4, null));
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    /* renamed from: _dataCaptureModeImpl */
    public final NativeDataCaptureMode getC() {
        return this.c.c;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public final void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.i = dataCaptureContext;
    }

    public final void a(BarcodePickSettings settings, Runnable runnable) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeWrappedFuture applySettingsWrapped = this.c.a.applySettingsWrapped(settings.getA());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, runnable);
    }

    public final BarcodePick d() {
        BarcodePick barcodePick = this.d;
        if (barcodePick != null) {
            return barcodePick;
        }
        BarcodePick barcodePick2 = new BarcodePick(this);
        this.d = barcodePick2;
        return barcodePick2;
    }

    public final /* synthetic */ CopyOnWriteArraySet e() {
        return this.f;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext */
    public final DataCaptureContext getC() {
        return this.i;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final boolean isEnabled() {
        return this.c.a.isEnabled();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    public final void setEnabled(boolean z) {
        this.c.a.setEnabled(z);
    }

    public final BarcodePickScanningSession b() {
        return this.b;
    }

    public final v c() {
        return this.a;
    }

    public final void b(BarcodePickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.remove(listener)) {
            listener.b(this);
        }
    }

    public final void a(String itemData, boolean z) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        t tVar = this.c;
        tVar.getClass();
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        tVar.a.completeActionForItem(itemData, z);
    }

    public final void b(BarcodePickActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.remove(listener);
    }

    public final boolean b(BarcodePickScanningListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.g.remove(listener);
    }

    public final boolean b(com.scandit.datacapture.barcode.pick.capture.BarcodePickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.h.remove(listener);
    }

    public final NativeBarcodePick a() {
        return this.c.a;
    }

    public final void a(BarcodePick value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.d = value;
    }

    public final void a(BarcodePickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.add(listener)) {
            listener.a(this);
        }
    }

    public final void a(BarcodePickActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.add(listener);
    }

    public final boolean a(BarcodePickScanningListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.g.add(listener);
    }

    public final boolean a(com.scandit.datacapture.barcode.pick.capture.BarcodePickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.h.add(listener);
    }

    public final void a(TrackedObject track, BarcodePickSelectItemActionCallback barcodePickSelectItemActionCallback) {
        Intrinsics.checkNotNullParameter(track, "track");
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((BarcodePickListener) it.next()).a(track);
        }
        this.c.a.handleTapAsync(track.getA(), barcodePickSelectItemActionCallback != null ? BarcodePickSelectItemActionCallbackKt.asNative(barcodePickSelectItemActionCallback) : null);
    }
}
