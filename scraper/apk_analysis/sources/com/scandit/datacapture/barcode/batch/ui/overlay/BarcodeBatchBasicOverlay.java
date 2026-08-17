package com.scandit.datacapture.barcode.batch.ui.overlay;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializer;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.extensions.BrushExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.OverlayPreconditionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ProfilingOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.tealium.library.DataSources;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 ;2\u00020\u00012\u00020\u0002:\u0002;<B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\"\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0097\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010 \u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001d\u001a\u00020\u0019¢\u0006\u0004\b\"\u0010#R$\u0010+\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R(\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010,\u001a\u0004\u0018\u00010\u00158W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00106\u001a\u0002012\u0006\u0010,\u001a\u0002018W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0014\u0010:\u001a\u0002078WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006="}, d2 = {"Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayProxy;", "Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_dataCaptureOverlayImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_impl", "()Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;", "overlay", "", "_setProfilingOverlay", "(Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/ProfilingOverlay;)V", "clearTrackedBarcodeBrushes", "()V", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "trackedBarcode", "Lcom/scandit/datacapture/core/ui/style/Brush;", "brush", "setBrushForTrackedBarcode", "(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;Lcom/scandit/datacapture/core/ui/style/Brush;)V", "", "jsonData", "updateFromJson", "(Ljava/lang/String;)V", "name", "", "value", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayListener;", "c", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayListener;", "getListener", "()Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayListener;", "setListener", "(Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "<set-?>", "getBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "setBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "", "getShouldShowScanAreaGuides", "()Z", "setShouldShowScanAreaGuides", "(Z)V", "shouldShowScanAreaGuides", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;", "getStyle", "()Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;", "style", "Companion", "com/scandit/datacapture/barcode/batch/ui/overlay/c", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeBatchBasicOverlay implements DataCaptureOverlay, BarcodeBatchBasicOverlayProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeBatchBasicOverlayProxyAdapter a;
    private final HashMap b;

    /* renamed from: c, reason: from kotlin metadata */
    private BarcodeBatchBasicOverlayListener listener;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeBatchBasicOverlay(NativeBarcodeTrackingBasicOverlay impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeBatchBasicOverlayProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.b = new HashMap();
        impl.setListener(new BarcodeBatchBasicOverlayListenerReversedAdapter(new c(this), this, null, 4, null));
    }

    @JvmStatic
    public static final Brush defaultBrush(BarcodeBatchBasicOverlayStyle barcodeBatchBasicOverlayStyle) {
        return INSTANCE.defaultBrush(barcodeBatchBasicOverlayStyle);
    }

    @JvmStatic
    public static final BarcodeBatchBasicOverlay fromJson(BarcodeBatch barcodeBatch, String str) {
        return INSTANCE.fromJson(barcodeBatch, str);
    }

    @JvmStatic
    public static final BarcodeBatchBasicOverlay newInstance(BarcodeBatch barcodeBatch, DataCaptureView dataCaptureView) {
        return INSTANCE.newInstance(barcodeBatch, dataCaptureView);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    @NativeImpl
    /* renamed from: _dataCaptureOverlayImpl */
    public NativeDataCaptureOverlay getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeTrackingBasicOverlay getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(nativeName = "setProfilingOverlay")
    public void _setProfilingOverlay(ProfilingOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.a._setProfilingOverlay(overlay);
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction
    public void clearTrackedBarcodeBrushes() {
        this.a.clearTrackedBarcodeBrushes();
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(nativeName = "getDefaultBrush", property = "brush")
    public Brush getBrush() {
        return this.a.getBrush();
    }

    public final BarcodeBatchBasicOverlayListener getListener() {
        return this.listener;
    }

    public final Object getProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.b.get(name);
        if (obj != null) {
            return obj;
        }
        try {
            return Boolean.valueOf(getA().getBoolProperty(name));
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(property = "shouldShowScanAreaGuides")
    public boolean getShouldShowScanAreaGuides() {
        return this.a.getShouldShowScanAreaGuides();
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(property = "style")
    public BarcodeBatchBasicOverlayStyle getStyle() {
        return this.a.getStyle();
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(nativeName = "setDefaultBrush", property = "brush")
    public void setBrush(Brush brush) {
        this.a.setBrush(brush);
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(nativeName = "setTrackedBarcodeBrush")
    public void setBrushForTrackedBarcode(TrackedBarcode trackedBarcode, Brush brush) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        this.a.setBrushForTrackedBarcode(trackedBarcode, brush);
    }

    public final void setListener(BarcodeBatchBasicOverlayListener barcodeBatchBasicOverlayListener) {
        this.listener = barcodeBatchBasicOverlayListener;
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.b.put(name, value);
        if (value instanceof Boolean) {
            getA().setBoolProperty(name, ((Boolean) value).booleanValue());
        }
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayProxy
    @ProxyFunction(property = "shouldShowScanAreaGuides")
    public void setShouldShowScanAreaGuides(boolean z) {
        this.a.setShouldShowScanAreaGuides(z);
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new BarcodeBatchDeserializer().updateBasicOverlayFromJson(this, jsonData);
    }

    @JvmStatic
    public static final BarcodeBatchBasicOverlay newInstance(BarcodeBatch barcodeBatch, DataCaptureView dataCaptureView, BarcodeBatchBasicOverlayStyle barcodeBatchBasicOverlayStyle) {
        return INSTANCE.newInstance(barcodeBatch, dataCaptureView, barcodeBatchBasicOverlayStyle);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\f\u0010\rJ)\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\f\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay$Companion;", "", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;", "style", "Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultBrush", "(Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;)Lcom/scandit/datacapture/core/ui/style/Brush;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;", "mode", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", "newInstance", "(Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;Lcom/scandit/datacapture/core/ui/DataCaptureView;)Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", "(Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;)Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", "", "jsonData", "fromJson", "(Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final Brush defaultBrush(BarcodeBatchBasicOverlayStyle style) {
            Intrinsics.checkNotNullParameter(style, "style");
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForStyle = NativeBarcodeTrackingBasicOverlay.getDefaultBrushForStyle(BarcodeBatchBasicOverlayStyleKt.toNative(style));
            Intrinsics.checkNotNullExpressionValue(defaultBrushForStyle, "getDefaultBrushForStyle(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForStyle);
        }

        @JvmStatic
        public final BarcodeBatchBasicOverlay fromJson(BarcodeBatch mode, String jsonData) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new BarcodeBatchDeserializer().basicOverlayFromJson(mode, jsonData);
        }

        @JvmStatic
        public final BarcodeBatchBasicOverlay newInstance(BarcodeBatch mode, DataCaptureView view) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, a.a);
            BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = new BarcodeBatchBasicOverlay(mode, null);
            if (view != null) {
                view.addOverlay(barcodeBatchBasicOverlay);
            }
            return barcodeBatchBasicOverlay;
        }

        @JvmStatic
        public final BarcodeBatchBasicOverlay newInstance(BarcodeBatch mode, DataCaptureView view, BarcodeBatchBasicOverlayStyle style) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, b.a);
            BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = new BarcodeBatchBasicOverlay(mode, style, null);
            if (view != null) {
                view.addOverlay(barcodeBatchBasicOverlay);
            }
            return barcodeBatchBasicOverlay;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeBatchBasicOverlay(com.scandit.datacapture.barcode.batch.capture.BarcodeBatch r1, kotlin.jvm.internal.DefaultConstructorMarker r2) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking r1 = r1._impl()
            com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay r1 = com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay.createWithDefaultStyle(r1)
            java.lang.String r2 = "createWithDefaultStyle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay.<init>(com.scandit.datacapture.barcode.batch.capture.BarcodeBatch, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeBatchBasicOverlay(com.scandit.datacapture.barcode.batch.capture.BarcodeBatch r1, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayStyle r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking r1 = r1._impl()
            com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlayStyle r2 = com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayStyleKt.toNative(r2)
            com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay r1 = com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay.create(r1, r2)
            java.lang.String r2 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay.<init>(com.scandit.datacapture.barcode.batch.capture.BarcodeBatch, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayStyle, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
