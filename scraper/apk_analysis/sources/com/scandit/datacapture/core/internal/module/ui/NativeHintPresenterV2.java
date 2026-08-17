package com.scandit.datacapture.core.internal.module.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeHintPresenterV2 {

    public static final class CppProxy extends NativeHintPresenterV2 {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native void native_hideAllToasts(long j);

        private native void native_hideCurrentGuidance(long j);

        private native void native_hideToast(long j, String str);

        private native void native_showGuidance(long j, NativeGuidanceHint nativeGuidanceHint);

        private native void native_showToast(long j, NativeToastHint nativeToastHint);

        private native void native_update(long j);

        private native void native_userWantsToHideGuidance(long j, NativeGuidanceHint nativeGuidanceHint);

        private native void native_userWantsToHideToast(long j, NativeToastHint nativeToastHint);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void hideAllToasts() {
            native_hideAllToasts(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void hideCurrentGuidance() {
            native_hideCurrentGuidance(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void hideToast(String str) {
            native_hideToast(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void showGuidance(NativeGuidanceHint nativeGuidanceHint) {
            native_showGuidance(this.nativeRef, nativeGuidanceHint);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void showToast(NativeToastHint nativeToastHint) {
            native_showToast(this.nativeRef, nativeToastHint);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void update() {
            native_update(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void userWantsToHideGuidance(NativeGuidanceHint nativeGuidanceHint) {
            native_userWantsToHideGuidance(this.nativeRef, nativeGuidanceHint);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenterV2
        public void userWantsToHideToast(NativeToastHint nativeToastHint) {
            native_userWantsToHideToast(this.nativeRef, nativeToastHint);
        }
    }

    public static native NativeHintPresenterV2 create(NativePlatformHintPresenter nativePlatformHintPresenter);

    public abstract void hideAllToasts();

    public abstract void hideCurrentGuidance();

    public abstract void hideToast(String str);

    public abstract void showGuidance(NativeGuidanceHint nativeGuidanceHint);

    public abstract void showToast(NativeToastHint nativeToastHint);

    public abstract void update();

    public abstract void userWantsToHideGuidance(NativeGuidanceHint nativeGuidanceHint);

    public abstract void userWantsToHideToast(NativeToastHint nativeToastHint);
}
