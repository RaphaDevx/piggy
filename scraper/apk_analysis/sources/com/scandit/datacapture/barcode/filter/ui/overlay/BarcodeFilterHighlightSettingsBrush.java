package com.scandit.datacapture.barcode.filter.ui.overlay;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003H\u0097\u0001¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettingsBrush;", "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterOverlaySettingsBrushProxy;", "impl", "Lcom/scandit/datacapture/barcode/filter/ui/overlay/NativeBarcodeFilterOverlaySettingsBrush;", "(Lcom/scandit/datacapture/barcode/filter/ui/overlay/NativeBarcodeFilterOverlaySettingsBrush;)V", "_impl", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeFilterHighlightSettingsBrush implements BarcodeFilterOverlaySettingsBrushProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ BarcodeFilterOverlaySettingsBrushProxyAdapter a;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettingsBrush$Companion;", "", "Lcom/scandit/datacapture/core/ui/style/Brush;", "brush", "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "create", "(Lcom/scandit/datacapture/core/ui/style/Brush;)Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final BarcodeFilterHighlightSettings create(Brush brush) {
            Intrinsics.checkNotNullParameter(brush, "brush");
            NativeBarcodeFilterOverlaySettings create = NativeBarcodeFilterOverlaySettingsBrush.create(new NativeBrush(NativeColorExtensionsKt.toNativeColor(brush.getFillColor()), NativeColorExtensionsKt.toNativeColor(brush.getStrokeColor()), brush.getStrokeWidth()));
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return new BarcodeFilterHighlightSettings(create);
        }
    }

    public BarcodeFilterHighlightSettingsBrush(NativeBarcodeFilterOverlaySettingsBrush impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeFilterOverlaySettingsBrushProxyAdapter(impl, null, 2, null);
    }

    @JvmStatic
    public static final BarcodeFilterHighlightSettings create(Brush brush) {
        return INSTANCE.create(brush);
    }

    @Override // com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterOverlaySettingsBrushProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeFilterOverlaySettingsBrush getA() {
        return this.a.getA();
    }
}
