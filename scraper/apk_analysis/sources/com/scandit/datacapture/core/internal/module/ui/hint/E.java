package com.scandit.datacapture.core.internal.module.ui.hint;

import android.os.Handler;
import android.os.Looper;
import com.scandit.datacapture.core.internal.module.ui.NativeGuidanceHint;
import com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2;
import com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHint;
import com.scandit.datacapture.core.internal.sdk.ui.hint.GuidanceHint;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class E extends NativePlatformHintPresenter {
    public final Handler a;
    public WeakReference b;
    public final AtomicBoolean c;
    public final AtomicLong d;
    public final WeakReference e;
    public final D f;

    public E(HintHolderV2 hintHolder) {
        Handler handler = new Handler(Looper.getMainLooper());
        Intrinsics.checkNotNullParameter(hintHolder, "hintHolder");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = handler;
        this.b = new WeakReference(null);
        this.c = new AtomicBoolean(false);
        this.d = new AtomicLong(Long.MAX_VALUE);
        this.e = new WeakReference(hintHolder);
        this.f = new D(this);
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void hideGuidance(NativeGuidanceHint guidance) {
        Intrinsics.checkNotNullParameter(guidance, "guidance");
        HintHolderV2 hintHolderV2 = (HintHolderV2) this.e.get();
        if (hintHolderV2 != null) {
            hintHolderV2.hideGuidance(new GuidanceHint(guidance));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void hideToast(NativeToastHint toast) {
        Intrinsics.checkNotNullParameter(toast, "toast");
        HintHolderV2 hintHolderV2 = (HintHolderV2) this.e.get();
        if (hintHolderV2 != null) {
            hintHolderV2.hideToast(new ToastHint(toast));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void setHintPresenter(NativeHintPresenterV2 presenter) {
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        this.b = new WeakReference(presenter);
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void showGuidance(NativeGuidanceHint guidance) {
        Intrinsics.checkNotNullParameter(guidance, "guidance");
        HintHolderV2 hintHolderV2 = (HintHolderV2) this.e.get();
        if (hintHolderV2 != null) {
            hintHolderV2.showGuidance(new GuidanceHint(guidance));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void showToast(NativeToastHint toast) {
        Intrinsics.checkNotNullParameter(toast, "toast");
        HintHolderV2 hintHolderV2 = (HintHolderV2) this.e.get();
        if (hintHolderV2 != null) {
            hintHolderV2.showToast(new ToastHint(toast));
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void startUpdateTimer(long j) {
        this.c.set(true);
        this.d.set(j);
        this.a.removeCallbacks(this.f);
        this.a.post(this.f);
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativePlatformHintPresenter
    public final void stopUpdateTimer() {
        this.c.set(false);
        this.a.removeCallbacks(this.f);
    }
}
