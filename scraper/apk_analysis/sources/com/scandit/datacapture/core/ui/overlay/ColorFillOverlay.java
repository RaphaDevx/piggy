package com.scandit.datacapture.core.ui.overlay;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.ui.NativeColorFillOverlay;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u00012\u00020\u0002:\u0001\u0011B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u000fH\u0097\u0001J\t\u0010\u0010\u001a\u00020\bH\u0097\u0001R$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00058W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/core/ui/overlay/ColorFillOverlay;", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "Lcom/scandit/datacapture/core/ui/overlay/ColorFillOverlayProxy;", "()V", TypedValues.Custom.S_COLOR, "", "(I)V", "impl", "Lcom/scandit/datacapture/core/internal/module/ui/NativeColorFillOverlay;", "(Lcom/scandit/datacapture/core/internal/module/ui/NativeColorFillOverlay;)V", "<set-?>", "getColor", "()I", "setColor", "_dataCaptureOverlayImpl", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/NativeDataCaptureOverlay;", "_impl", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ColorFillOverlay implements DataCaptureOverlay, ColorFillOverlayProxy {
    private static final NativeColor b = NativeColorExtensionsKt.toNativeColor(0);
    private final /* synthetic */ ColorFillOverlayProxyAdapter a;

    public ColorFillOverlay(NativeColorFillOverlay impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new ColorFillOverlayProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    @NativeImpl
    /* renamed from: _dataCaptureOverlayImpl */
    public NativeDataCaptureOverlay getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.core.ui.overlay.ColorFillOverlayProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeColorFillOverlay getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.ui.overlay.ColorFillOverlayProxy
    @ProxyFunction(property = TypedValues.Custom.S_COLOR)
    public int getColor() {
        return this.a.getColor();
    }

    @Override // com.scandit.datacapture.core.ui.overlay.ColorFillOverlayProxy
    @ProxyFunction(property = TypedValues.Custom.S_COLOR)
    public void setColor(int i) {
        this.a.setColor(i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ColorFillOverlay() {
        /*
            r2 = this;
            com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor r0 = com.scandit.datacapture.core.ui.overlay.ColorFillOverlay.b
            com.scandit.datacapture.core.internal.module.ui.NativeColorFillOverlay r0 = com.scandit.datacapture.core.internal.module.ui.NativeColorFillOverlay.create(r0)
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.overlay.ColorFillOverlay.<init>():void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ColorFillOverlay(int r2) {
        /*
            r1 = this;
            com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor r2 = com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt.toNativeColor(r2)
            com.scandit.datacapture.core.internal.module.ui.NativeColorFillOverlay r2 = com.scandit.datacapture.core.internal.module.ui.NativeColorFillOverlay.create(r2)
            java.lang.String r0 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.overlay.ColorFillOverlay.<init>(int):void");
    }
}
