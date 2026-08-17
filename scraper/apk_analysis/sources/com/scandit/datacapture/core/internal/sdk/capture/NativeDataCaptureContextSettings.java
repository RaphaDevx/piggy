package com.scandit.datacapture.core.internal.sdk.capture;

import com.scandit.datacapture.core.internal.sdk.analytics.NativeAnalyticsSettings;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContextSettings {

    public static final class CppProxy extends NativeDataCaptureContextSettings {
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

        private native NativeAnalyticsSettings native_getAnalyticsSettings(long j);

        private native String native_getEnabledCpusBitset(long j);

        private native int native_getNumberOfEngineThreads(long j);

        private native void native_setAnalyticsSettings(long j, NativeAnalyticsSettings nativeAnalyticsSettings);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setEnabledCpusBitset(long j, String str);

        private native void native_setFloatProperty(long j, String str, float f);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setNumberOfEngineThreads(long j, int i);

        private native void native_setStringProperty(long j, String str, String str2);

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public NativeAnalyticsSettings getAnalyticsSettings() {
            return native_getAnalyticsSettings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public String getEnabledCpusBitset() {
            return native_getEnabledCpusBitset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public int getNumberOfEngineThreads() {
            return native_getNumberOfEngineThreads(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setAnalyticsSettings(NativeAnalyticsSettings nativeAnalyticsSettings) {
            native_setAnalyticsSettings(this.nativeRef, nativeAnalyticsSettings);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setEnabledCpusBitset(String str) {
            native_setEnabledCpusBitset(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setFloatProperty(String str, float f) {
            native_setFloatProperty(this.nativeRef, str, f);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setNumberOfEngineThreads(int i) {
            native_setNumberOfEngineThreads(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings
        public void setStringProperty(String str, String str2) {
            native_setStringProperty(this.nativeRef, str, str2);
        }
    }

    public static native NativeDataCaptureContextSettings create();

    public abstract NativeAnalyticsSettings getAnalyticsSettings();

    public abstract String getEnabledCpusBitset();

    public abstract int getNumberOfEngineThreads();

    public abstract void setAnalyticsSettings(NativeAnalyticsSettings nativeAnalyticsSettings);

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setEnabledCpusBitset(String str);

    public abstract void setFloatProperty(String str, float f);

    public abstract void setIntProperty(String str, int i);

    public abstract void setNumberOfEngineThreads(int i);

    public abstract void setStringProperty(String str, String str2);
}
