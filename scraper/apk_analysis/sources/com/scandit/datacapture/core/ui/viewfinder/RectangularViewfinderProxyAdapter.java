package com.scandit.datacapture.core.ui.viewfinder;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinder;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderLineStyle;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderStyle;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0016\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR$\u0010%\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010(\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$R\u0014\u0010,\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R(\u00102\u001a\u0004\u0018\u00010-2\b\u0010 \u001a\u0004\u0018\u00010-8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0014\u00106\u001a\u0002038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R$\u0010;\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010>\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b<\u00108\"\u0004\b=\u0010:¨\u0006?"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderProxyAdapter;", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderProxy;", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinder;", "_NativeRectangularViewfinder", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinder;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinder;", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "_viewfinderImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "width", "", "heightToWidthAspect", "", "setWidthAndAspectRatio", "(Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;F)V", "height", "widthToHeightAspect", "setHeightAndAspectRatio", "shorterDimension", "aspect", "setShorterDimensionAndAspectRatio", "(FF)V", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "", "p0", "getColor", "()I", "setColor", "(I)V", TypedValues.Custom.S_COLOR, "getDisabledColor", "setDisabledColor", "disabledColor", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "getStyle", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "style", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;", "getAnimation", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;", "setAnimation", "(Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;)V", "animation", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "getLineStyle", "()Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "lineStyle", "getDimming", "()F", "setDimming", "(F)V", "dimming", "getDisabledDimming", "setDisabledDimming", "disabledDimming", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class RectangularViewfinderProxyAdapter implements RectangularViewfinderProxy {
    private final NativeRectangularViewfinder a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeViewfinder c;

    public RectangularViewfinderProxyAdapter(NativeRectangularViewfinder _NativeRectangularViewfinder, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeRectangularViewfinder, "_NativeRectangularViewfinder");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeRectangularViewfinder;
        this.proxyCache = proxyCache;
        NativeViewfinder asViewfinder = _NativeRectangularViewfinder.asViewfinder();
        Intrinsics.checkNotNullExpressionValue(asViewfinder, "asViewfinder(...)");
        this.c = asViewfinder;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    /* renamed from: _impl, reason: from getter */
    public NativeRectangularViewfinder getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy, com.scandit.datacapture.core.ui.viewfinder.Viewfinder
    /* renamed from: _viewfinderImpl, reason: from getter */
    public NativeViewfinder getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public RectangularViewfinderAnimation getAnimation() {
        NativeRectangularViewfinderAnimation animation = this.a.getAnimation();
        if (animation != null) {
            return (RectangularViewfinderAnimation) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeRectangularViewfinderAnimation.class), null, animation, new a(animation));
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public int getColor() {
        NativeColor color = this.a.getColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(color);
        return coreNativeTypeFactory.convert(color);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public float getDimming() {
        return this.a.getDimming();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public int getDisabledColor() {
        NativeColor disabledColor = this.a.getDisabledColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(disabledColor);
        return coreNativeTypeFactory.convert(disabledColor);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public float getDisabledDimming() {
        return this.a.getDisabledDimming();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public RectangularViewfinderLineStyle getLineStyle() {
        NativeRectangularViewfinderLineStyle lineStyle = this.a.getLineStyle();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(lineStyle);
        return coreNativeTypeFactory.convert(lineStyle);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public RectangularViewfinderStyle getStyle() {
        NativeRectangularViewfinderStyle style = this.a.getStyle();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(style);
        return coreNativeTypeFactory.convert(style);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setAnimation(RectangularViewfinderAnimation rectangularViewfinderAnimation) {
        NativeRectangularViewfinderAnimation nativeRectangularViewfinderAnimation = null;
        if (rectangularViewfinderAnimation != null) {
            NativeRectangularViewfinderAnimation a = rectangularViewfinderAnimation.getA();
            this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeRectangularViewfinderAnimation.class), null, a, rectangularViewfinderAnimation);
            nativeRectangularViewfinderAnimation = a;
        }
        this.a.setAnimation(nativeRectangularViewfinderAnimation);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setColor(int i) {
        this.a.setColor(CoreNativeTypeFactory.INSTANCE.convert(i));
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setDimming(float f) {
        this.a.setDimming(f);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setDisabledColor(int i) {
        this.a.setDisabledColor(CoreNativeTypeFactory.INSTANCE.convert(i));
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setDisabledDimming(float f) {
        this.a.setDisabledDimming(f);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setHeightAndAspectRatio(FloatWithUnit height, float widthToHeightAspect) {
        Intrinsics.checkNotNullParameter(height, "height");
        this.a.setHeightAndAspectRatio(height, widthToHeightAspect);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setShorterDimensionAndAspectRatio(float shorterDimension, float aspect) {
        this.a.setShorterDimensionAndAspectRatio(shorterDimension, aspect);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderProxy
    public void setWidthAndAspectRatio(FloatWithUnit width, float heightToWidthAspect) {
        Intrinsics.checkNotNullParameter(width, "width");
        this.a.setWidthAndAspectRatio(width, heightToWidthAspect);
    }

    public /* synthetic */ RectangularViewfinderProxyAdapter(NativeRectangularViewfinder nativeRectangularViewfinder, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeRectangularViewfinder, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
