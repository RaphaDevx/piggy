package com.scandit.datacapture.core.internal.sdk.analytics;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAnalyticsSettings {

    public static final class CppProxy extends NativeAnalyticsSettings {
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

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setStringProperty(long j, String str, String str2);

        @Override // com.scandit.datacapture.core.internal.sdk.analytics.NativeAnalyticsSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.analytics.NativeAnalyticsSettings
        public void setStringProperty(String str, String str2) {
            native_setStringProperty(this.nativeRef, str, str2);
        }
    }

    public static native NativeAnalyticsSettings create();

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setStringProperty(String str, String str2);
}
