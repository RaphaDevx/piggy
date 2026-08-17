package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeProductProviderCallback {

    public static final class CppProxy extends NativeProductProviderCallback {
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

        private native void native_onData(long j, ArrayList<NativeProductProviderCallbackItem> arrayList);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallback
        public void onData(ArrayList<NativeProductProviderCallbackItem> arrayList) {
            native_onData(this.nativeRef, arrayList);
        }
    }

    public abstract void onData(ArrayList<NativeProductProviderCallbackItem> arrayList);
}
