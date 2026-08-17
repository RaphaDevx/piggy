package com.scandit.datacapture.core.ui.viewfinder;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00188V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010 \u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00188V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u0006!"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinderProxyAdapter;", "Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinderProxy;", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeLaserlineViewfinder;", "_NativeLaserlineViewfinder", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeLaserlineViewfinder;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeLaserlineViewfinder;", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "_viewfinderImpl", "()Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_capture_core", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "p0", "getWidth", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "setWidth", "(Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;)V", "width", "", "getEnabledColor", "()I", "setEnabledColor", "(I)V", "enabledColor", "getDisabledColor", "setDisabledColor", "disabledColor", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class LaserlineViewfinderProxyAdapter implements LaserlineViewfinderProxy {
    private final NativeLaserlineViewfinder a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeViewfinder c;

    public LaserlineViewfinderProxyAdapter(NativeLaserlineViewfinder _NativeLaserlineViewfinder, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeLaserlineViewfinder, "_NativeLaserlineViewfinder");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeLaserlineViewfinder;
        this.proxyCache = proxyCache;
        NativeViewfinder asViewfinder = _NativeLaserlineViewfinder.asViewfinder();
        Intrinsics.checkNotNullExpressionValue(asViewfinder, "asViewfinder(...)");
        this.c = asViewfinder;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    /* renamed from: _impl, reason: from getter */
    public NativeLaserlineViewfinder getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy, com.scandit.datacapture.core.ui.viewfinder.Viewfinder
    /* renamed from: _viewfinderImpl, reason: from getter */
    public NativeViewfinder getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public int getDisabledColor() {
        NativeColor disabledColor = this.a.getDisabledColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(disabledColor);
        return coreNativeTypeFactory.convert(disabledColor);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public int getEnabledColor() {
        NativeColor enabledColor = this.a.getEnabledColor();
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(enabledColor);
        return coreNativeTypeFactory.convert(enabledColor);
    }

    /* renamed from: getProxyCache$scandit_capture_core, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public FloatWithUnit getWidth() {
        FloatWithUnit width = this.a.getWidth();
        Intrinsics.checkNotNull(width);
        return width;
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public void setDisabledColor(int i) {
        this.a.setDisabledColor(CoreNativeTypeFactory.INSTANCE.convert(i));
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public void setEnabledColor(int i) {
        this.a.setEnabledColor(CoreNativeTypeFactory.INSTANCE.convert(i));
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    public void setWidth(FloatWithUnit p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.a.setWidth(p0);
    }

    public /* synthetic */ LaserlineViewfinderProxyAdapter(NativeLaserlineViewfinder nativeLaserlineViewfinder, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeLaserlineViewfinder, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
