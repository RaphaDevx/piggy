package com.scandit.datacapture.core.ui.viewfinder;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.SizeWithUnit;
import com.scandit.datacapture.core.common.geometry.SizeWithUnitAndAspect;
import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinder;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0000\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010,\u001a\u00020\tH\u0097\u0001J\t\u0010-\u001a\u00020.H\u0097\u0001J\u0019\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0018H\u0097\u0001J\u0019\u00104\u001a\u0002002\u0006\u00105\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u0018H\u0097\u0001J\u000e\u00107\u001a\u0002002\u0006\u00108\u001a\u000209J\u0019\u0010:\u001a\u0002002\u0006\u0010;\u001a\u0002022\u0006\u0010<\u001a\u00020\u0018H\u0097\u0001R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00128W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u00188W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00128W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R$\u0010!\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u00188W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010\u0003\u001a\u00020\u00048WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006="}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinder;", "Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderProxy;", "style", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "lineStyle", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "(Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;)V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinder;", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinder;)V", "<set-?>", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;", "animation", "getAnimation", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;", "setAnimation", "(Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;)V", "", TypedValues.Custom.S_COLOR, "getColor", "()I", "setColor", "(I)V", "", "dimming", "getDimming", "()F", "setDimming", "(F)V", "disabledColor", "getDisabledColor", "setDisabledColor", "disabledDimming", "getDisabledDimming", "setDisabledDimming", "getLineStyle", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "sizeWithUnitAndAspect", "Lcom/scandit/datacapture/core/common/geometry/SizeWithUnitAndAspect;", "getSizeWithUnitAndAspect", "()Lcom/scandit/datacapture/core/common/geometry/SizeWithUnitAndAspect;", "getStyle", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "_impl", "_viewfinderImpl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "setHeightAndAspectRatio", "", "height", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "widthToHeightAspect", "setShorterDimensionAndAspectRatio", "shorterDimension", "aspect", "setSize", RRWebVideoEvent.JsonKeys.SIZE, "Lcom/scandit/datacapture/core/common/geometry/SizeWithUnit;", "setWidthAndAspectRatio", "width", "heightToWidthAspect", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectangularViewfinder implements Viewfinder, RectangularViewfinderProxy {
    private final /* synthetic */ RectangularViewfinderProxyAdapter a;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RectangularViewfinder(RectangularViewfinderStyle style) {
        this(style, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(style, "style");
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeRectangularViewfinder getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.Viewfinder
    @NativeImpl
    /* renamed from: _viewfinderImpl */
    public NativeViewfinder getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "animation")
    public RectangularViewfinderAnimation getAnimation() {
        return this.a.getAnimation();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = TypedValues.Custom.S_COLOR)
    public int getColor() {
        return this.a.getColor();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "dimming")
    public float getDimming() {
        return this.a.getDimming();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "disabledColor")
    public int getDisabledColor() {
        return this.a.getDisabledColor();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "disabledDimming")
    public float getDisabledDimming() {
        return this.a.getDisabledDimming();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "lineStyle")
    public RectangularViewfinderLineStyle getLineStyle() {
        return this.a.getLineStyle();
    }

    public final SizeWithUnitAndAspect getSizeWithUnitAndAspect() {
        NativeSizeWithUnitAndAspect sizeWithUnitAndAspect = getA().getSizeWithUnitAndAspect();
        Intrinsics.checkNotNullExpressionValue(sizeWithUnitAndAspect, "getSizeWithUnitAndAspect(...)");
        return new SizeWithUnitAndAspect(sizeWithUnitAndAspect);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "style")
    public RectangularViewfinderStyle getStyle() {
        return this.a.getStyle();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "animation")
    public void setAnimation(RectangularViewfinderAnimation rectangularViewfinderAnimation) {
        this.a.setAnimation(rectangularViewfinderAnimation);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = TypedValues.Custom.S_COLOR)
    public void setColor(int i) {
        this.a.setColor(i);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "dimming")
    public void setDimming(float f) {
        this.a.setDimming(f);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "disabledColor")
    public void setDisabledColor(int i) {
        this.a.setDisabledColor(i);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction(property = "disabledDimming")
    public void setDisabledDimming(float f) {
        this.a.setDisabledDimming(f);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction
    public void setHeightAndAspectRatio(FloatWithUnit height, float widthToHeightAspect) {
        Intrinsics.checkNotNullParameter(height, "height");
        this.a.setHeightAndAspectRatio(height, widthToHeightAspect);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction
    public void setShorterDimensionAndAspectRatio(float shorterDimension, float aspect) {
        this.a.setShorterDimensionAndAspectRatio(shorterDimension, aspect);
    }

    public final void setSize(SizeWithUnit size) {
        Intrinsics.checkNotNullParameter(size, "size");
        getA().setWidthAndHeight(size.getWidth(), size.getHeight());
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    @ProxyFunction
    public void setWidthAndAspectRatio(FloatWithUnit width, float heightToWidthAspect) {
        Intrinsics.checkNotNullParameter(width, "width");
        this.a.setWidthAndAspectRatio(width, heightToWidthAspect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RectangularViewfinder(NativeRectangularViewfinder impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new RectangularViewfinderProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    public /* synthetic */ RectangularViewfinder(RectangularViewfinderStyle rectangularViewfinderStyle, RectangularViewfinderLineStyle rectangularViewfinderLineStyle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(rectangularViewfinderStyle, (i & 2) != 0 ? RectangularViewfinderLineStyle.LIGHT : rectangularViewfinderLineStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RectangularViewfinder(com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyle r2, com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderLineStyle r3) {
        /*
            r1 = this;
            java.lang.String r0 = "style"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "lineStyle"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderStyle r2 = com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyleKt.toNative(r2)
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderLineStyle r3 = com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderLineStyleKt.toNative(r3)
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinder r2 = com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinder.createForStyles(r2, r3)
            java.lang.String r3 = "createForStyles(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinder.<init>(com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyle, com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderLineStyle):void");
    }
}
