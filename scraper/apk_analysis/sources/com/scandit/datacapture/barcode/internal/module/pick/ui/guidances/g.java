package com.scandit.datacapture.barcode.internal.module.pick.ui.guidances;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g extends FrameLayout implements BarcodePickGuidanceHandler {
    public a a;
    public final Lazy b;
    public boolean c;
    public boolean d;
    public final Lazy e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, com.scandit.datacapture.barcode.internal.module.pick.ui.f loadingViewFactory, k guidanceViewFactory) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(loadingViewFactory, "loadingViewFactory");
        Intrinsics.checkNotNullParameter(guidanceViewFactory, "guidanceViewFactory");
        this.a = a.a;
        this.b = LazyKt.lazy(new d(loadingViewFactory));
        this.e = LazyKt.lazy(new b(guidanceViewFactory));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final void a(boolean z) {
        ViewExtensionsKt.runOnMainThread(this, new f(this, z));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final void b(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        a aVar = a.b;
        if (z) {
            this.a = aVar;
        } else if (this.a != aVar) {
            return;
        } else {
            this.a = a.a;
        }
        ViewExtensionsKt.runOnMainThread(this, new e(z, this, text, false));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final void c(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        a aVar = a.d;
        if (z) {
            this.a = aVar;
        } else if (this.a != aVar) {
            return;
        } else {
            this.a = a.a;
        }
        ViewExtensionsKt.runOnMainThread(this, new e(z, this, text, false));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final g getView() {
        return this;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final void a() {
        ViewExtensionsKt.runOnMainThread(this, new c(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler
    public final void a(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        a aVar = a.c;
        if (z) {
            this.a = aVar;
        } else if (this.a != aVar) {
            return;
        } else {
            this.a = a.a;
        }
        ViewExtensionsKt.runOnMainThread(this, new e(z, this, text, true));
    }
}
