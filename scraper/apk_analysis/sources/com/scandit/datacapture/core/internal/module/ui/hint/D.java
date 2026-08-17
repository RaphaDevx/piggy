package com.scandit.datacapture.core.internal.module.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2;

/* loaded from: classes2.dex */
public final class D implements Runnable {
    public final /* synthetic */ E a;

    public D(E e) {
        this.a = e;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NativeHintPresenterV2 nativeHintPresenterV2 = (NativeHintPresenterV2) this.a.b.get();
        if (nativeHintPresenterV2 != null) {
            E e = this.a;
            if (e.c.get()) {
                nativeHintPresenterV2.update();
                e.a.postDelayed(this, e.d.get());
            }
        }
    }
}
