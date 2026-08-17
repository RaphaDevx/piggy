package com.scandit.datacapture.core.internal.sdk.analytics;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class NativePropertySubscriber {

    public static final class CppProxy extends NativePropertySubscriber {
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

        private native void native_onPropertyChanged(long j, JSONObject jSONObject);

        @Override // com.scandit.datacapture.core.internal.sdk.analytics.NativePropertySubscriber
        public void onPropertyChanged(JSONObject jSONObject) {
            native_onPropertyChanged(this.nativeRef, jSONObject);
        }
    }

    public abstract void onPropertyChanged(JSONObject jSONObject);
}
