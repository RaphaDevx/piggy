package com.scandit.datacapture.core.ui.viewfinder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0017\u001a\u00020\u0005H\u0097\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u0097\u0001R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00118W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinder;", "Lcom/scandit/datacapture/core/ui/viewfinder/Viewfinder;", "Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinderProxy;", "()V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeLaserlineViewfinder;", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeLaserlineViewfinder;)V", "<set-?>", "", "disabledColor", "getDisabledColor", "()I", "setDisabledColor", "(I)V", "enabledColor", "getEnabledColor", "setEnabledColor", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "width", "getWidth", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "setWidth", "(Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;)V", "_impl", "_viewfinderImpl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LaserlineViewfinder implements Viewfinder, LaserlineViewfinderProxy {
    private final /* synthetic */ LaserlineViewfinderProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public LaserlineViewfinder(NativeLaserlineViewfinder impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new LaserlineViewfinderProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeLaserlineViewfinder getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.Viewfinder
    @NativeImpl
    /* renamed from: _viewfinderImpl */
    public NativeViewfinder getC() {
        return this.a.getC();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "disabledColor")
    public int getDisabledColor() {
        return this.a.getDisabledColor();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "enabledColor")
    public int getEnabledColor() {
        return this.a.getEnabledColor();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "width")
    public FloatWithUnit getWidth() {
        return this.a.getWidth();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "disabledColor")
    public void setDisabledColor(int i) {
        this.a.setDisabledColor(i);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "enabledColor")
    public void setEnabledColor(int i) {
        this.a.setEnabledColor(i);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinderProxy
    @ProxyFunction(property = "width")
    public void setWidth(FloatWithUnit floatWithUnit) {
        Intrinsics.checkNotNullParameter(floatWithUnit, "<set-?>");
        this.a.setWidth(floatWithUnit);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LaserlineViewfinder() {
        /*
            r2 = this;
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder r0 = com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinder.<init>():void");
    }
}
