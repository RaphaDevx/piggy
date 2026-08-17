package com.scandit.datacapture.barcode.ui.overlay;

import com.scandit.datacapture.barcode.capture.BarcodeCapture;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializer;
import com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.extensions.BrushExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.OverlayPreconditionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.viewfinder.Viewfinder;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.tealium.library.DataSources;
import java.util.HashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 12\u00020\u00012\u00020\u0002:\u00011B\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017R.\u0010\u001f\u001a\u0004\u0018\u00010\u00182\b\u0010\u000f\u001a\u0004\u0018\u00010\u00188\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010&\u001a\u00020 2\u0006\u0010!\u001a\u00020 8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010,\u001a\u00020'2\u0006\u0010!\u001a\u00020'8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0014\u00100\u001a\u00020-8WX\u0097\u0005¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayProxy;", "Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;)V", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_dataCaptureOverlayImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;", "", "name", "", "value", "", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "jsonData", "updateFromJson", "(Ljava/lang/String;)V", "Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;", "c", "Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;", "getViewfinder", "()Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;", "setViewfinder", "(Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;)V", "viewfinder", "Lcom/scandit/datacapture/core/ui/style/Brush;", "<set-?>", "getBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "setBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "brush", "", "getShouldShowScanAreaGuides", "()Z", "setShouldShowScanAreaGuides", "(Z)V", "shouldShowScanAreaGuides", "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;", "getStyle", "()Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;", "style", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCaptureOverlay implements DataCaptureOverlay, BarcodeCaptureOverlayProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeCaptureOverlayProxyAdapter a;
    private final HashMap b;

    /* renamed from: c, reason: from kotlin metadata */
    private Viewfinder viewfinder;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCaptureOverlay(NativeBarcodeCaptureOverlay impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeCaptureOverlayProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        this.b = new HashMap();
    }

    @JvmStatic
    public static final Brush defaultBrush() {
        return INSTANCE.defaultBrush();
    }

    @JvmStatic
    public static final BarcodeCaptureOverlay fromJson(BarcodeCapture barcodeCapture, String str) {
        return INSTANCE.fromJson(barcodeCapture, str);
    }

    @JvmStatic
    public static final BarcodeCaptureOverlay newInstance(BarcodeCapture barcodeCapture, DataCaptureView dataCaptureView) {
        return INSTANCE.newInstance(barcodeCapture, dataCaptureView);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    @NativeImpl
    /* renamed from: _dataCaptureOverlayImpl */
    public NativeDataCaptureOverlay getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCaptureOverlay getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @ProxyFunction(nativeName = "getBrushForRecognizedBarcodes", property = "brush")
    public Brush getBrush() {
        return this.a.getBrush();
    }

    public final Object getProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.b.get(name);
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @ProxyFunction(property = "shouldShowScanAreaGuides")
    public boolean getShouldShowScanAreaGuides() {
        return this.a.getShouldShowScanAreaGuides();
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @ProxyFunction(property = "style")
    public BarcodeCaptureOverlayStyle getStyle() {
        return this.a.getStyle();
    }

    public final Viewfinder getViewfinder() {
        return this.viewfinder;
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @ProxyFunction(nativeName = "setBrushForRecognizedBarcodes", property = "brush")
    public void setBrush(Brush brush) {
        Intrinsics.checkNotNullParameter(brush, "<set-?>");
        this.a.setBrush(brush);
    }

    public final void setProperty(String name, Object value) {
        NativeBrush convert;
        Intrinsics.checkNotNullParameter(name, "name");
        this.b.put(name, value);
        if (Intrinsics.areEqual(name, "localizedOnlyBarcodeBrush")) {
            if (value == null) {
                convert = NativeBarcodeCaptureOverlay.defaultBrushForLocalizedOnlyBarcodes();
                Intrinsics.checkNotNull(convert);
            } else {
                convert = CoreNativeTypeFactory.INSTANCE.convert((Brush) value);
            }
            getA().setBrushForLocalizedOnlyBarcodes(convert);
            getA().setShouldDrawLocalizedOnlyBarcodes(true);
        }
    }

    @Override // com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayProxy
    @ProxyFunction(property = "shouldShowScanAreaGuides")
    public void setShouldShowScanAreaGuides(boolean z) {
        this.a.setShouldShowScanAreaGuides(z);
    }

    public final void setViewfinder(Viewfinder viewfinder) {
        this.viewfinder = viewfinder;
        getA().setViewfinder(viewfinder != null ? viewfinder.getC() : null);
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new BarcodeCaptureDeserializer().updateOverlayFromJson(this, jsonData);
    }

    @Deprecated(message = "Use the version without style parameter instead", replaceWith = @ReplaceWith(expression = "defaultBrush()", imports = {}))
    @JvmStatic
    public static final Brush defaultBrush(BarcodeCaptureOverlayStyle barcodeCaptureOverlayStyle) {
        return INSTANCE.defaultBrush(barcodeCaptureOverlayStyle);
    }

    @Deprecated(message = "Use the version without style parameter instead", replaceWith = @ReplaceWith(expression = "newInstance(mode, view)", imports = {}))
    @JvmStatic
    public static final BarcodeCaptureOverlay newInstance(BarcodeCapture barcodeCapture, DataCaptureView dataCaptureView, BarcodeCaptureOverlayStyle barcodeCaptureOverlayStyle) {
        return INSTANCE.newInstance(barcodeCapture, dataCaptureView, barcodeCaptureOverlayStyle);
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0007\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay$Companion;", "", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "mode", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "newInstance", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Lcom/scandit/datacapture/core/ui/DataCaptureView;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;", "style", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Lcom/scandit/datacapture/core/ui/DataCaptureView;Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "", "jsonData", "fromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "Lcom/scandit/datacapture/core/ui/style/Brush;", "defaultBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "(Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlayStyle;)Lcom/scandit/datacapture/core/ui/style/Brush;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final Brush defaultBrush() {
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForStyle = NativeBarcodeCaptureOverlay.getDefaultBrushForStyle(BarcodeCaptureOverlayStyleKt.toNative(BarcodeCaptureOverlayStyle.FRAME));
            Intrinsics.checkNotNullExpressionValue(defaultBrushForStyle, "getDefaultBrushForStyle(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForStyle);
        }

        @JvmStatic
        public final BarcodeCaptureOverlay fromJson(BarcodeCapture mode, String jsonData) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new BarcodeCaptureDeserializer().overlayFromJson(mode, jsonData);
        }

        @JvmStatic
        public final BarcodeCaptureOverlay newInstance(BarcodeCapture mode, DataCaptureView view) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, a.a);
            BarcodeCaptureOverlay barcodeCaptureOverlay = new BarcodeCaptureOverlay(mode, null);
            if (view != null) {
                view.addOverlay(barcodeCaptureOverlay);
            }
            return barcodeCaptureOverlay;
        }

        @Deprecated(message = "Use the version without style parameter instead", replaceWith = @ReplaceWith(expression = "defaultBrush()", imports = {}))
        @JvmStatic
        public final Brush defaultBrush(BarcodeCaptureOverlayStyle style) {
            Intrinsics.checkNotNullParameter(style, "style");
            Brush.Companion companion = Brush.INSTANCE;
            NativeBrush defaultBrushForStyle = NativeBarcodeCaptureOverlay.getDefaultBrushForStyle(BarcodeCaptureOverlayStyleKt.toNative(style));
            Intrinsics.checkNotNullExpressionValue(defaultBrushForStyle, "getDefaultBrushForStyle(...)");
            return BrushExtensionsKt.of(companion, defaultBrushForStyle);
        }

        @Deprecated(message = "Use the version without style parameter instead", replaceWith = @ReplaceWith(expression = "newInstance(mode, view)", imports = {}))
        @JvmStatic
        public final BarcodeCaptureOverlay newInstance(BarcodeCapture mode, DataCaptureView view, BarcodeCaptureOverlayStyle style) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(style, "style");
            OverlayPreconditionsKt.checkAttachedToSameDataCaptureContext(mode, view, b.a);
            BarcodeCaptureOverlay barcodeCaptureOverlay = new BarcodeCaptureOverlay(mode, style, null);
            if (view != null) {
                view.addOverlay(barcodeCaptureOverlay);
            }
            return barcodeCaptureOverlay;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCaptureOverlay(com.scandit.datacapture.barcode.capture.BarcodeCapture r1, kotlin.jvm.internal.DefaultConstructorMarker r2) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture r1 = r1.getA()
            com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay r1 = com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay.createWithDefaultStyle(r1)
            java.lang.String r2 = "createWithDefaultStyle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay.<init>(com.scandit.datacapture.barcode.capture.BarcodeCapture, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCaptureOverlay(com.scandit.datacapture.barcode.capture.BarcodeCapture r1, com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayStyle r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture r1 = r1.getA()
            com.scandit.datacapture.barcode.ui.overlay.NativeBarcodeCaptureOverlayStyle r2 = com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayStyleKt.toNative(r2)
            com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay r1 = com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay.create(r1, r2)
            java.lang.String r2 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay.<init>(com.scandit.datacapture.barcode.capture.BarcodeCapture, com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayStyle, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
