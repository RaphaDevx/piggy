package com.scandit.datacapture.core.ui.viewfinder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001a\u00020\u0006H\u0097\u0001J\t\u0010\n\u001a\u00020\u000bH\u0097\u0001R\u0014\u0010\u0002\u001a\u00020\u00038WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0002\u0010\b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimation;", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderAnimationProxy;", "isLooping", "", "(Z)V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinderAnimation;", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeRectangularViewfinderAnimation;)V", "()Z", "_impl", "toJson", "", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectangularViewfinderAnimation implements RectangularViewfinderAnimationProxy {
    private final /* synthetic */ RectangularViewfinderAnimationProxyAdapter a;

    public RectangularViewfinderAnimation(NativeRectangularViewfinderAnimation impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new RectangularViewfinderAnimationProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderAnimationProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeRectangularViewfinderAnimation getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderAnimationProxy
    @ProxyFunction(property = "isLooping")
    public boolean isLooping() {
        return this.a.isLooping();
    }

    @Override // com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderAnimationProxy
    @ProxyFunction
    public String toJson() {
        return this.a.toJson();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RectangularViewfinderAnimation(boolean r2) {
        /*
            r1 = this;
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation r2 = com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation.create(r2)
            java.lang.String r0 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderAnimation.<init>(boolean):void");
    }
}
