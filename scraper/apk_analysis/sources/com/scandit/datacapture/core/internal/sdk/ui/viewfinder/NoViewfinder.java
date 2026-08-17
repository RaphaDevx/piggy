package com.scandit.datacapture.core.internal.sdk.ui.viewfinder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0004H\u0097\u0001J\t\u0010\u0007\u001a\u00020\bH\u0097\u0001¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NoViewfinder;", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NoViewfinderProxy;", "()V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeNoViewfinder;", "(Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeNoViewfinder;)V", "_impl", "_viewfinderImpl", "Lcom/scandit/datacapture/core/internal/sdk/ui/viewfinder/NativeViewfinder;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NoViewfinder implements NoViewfinderProxy {
    private final /* synthetic */ NoViewfinderProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public NoViewfinder(NativeNoViewfinder impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new NoViewfinderProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NoViewfinderProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeNoViewfinder getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NoViewfinderProxy, com.scandit.datacapture.core.ui.viewfinder.Viewfinder
    @NativeImpl
    /* renamed from: _viewfinderImpl */
    public NativeViewfinder getC() {
        return this.a.getC();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NoViewfinder() {
        /*
            r2 = this;
            com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeNoViewfinder r0 = com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeNoViewfinder.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NoViewfinder.<init>():void");
    }
}
