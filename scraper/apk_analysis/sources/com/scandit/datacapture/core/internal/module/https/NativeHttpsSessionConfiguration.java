package com.scandit.datacapture.core.internal.module.https;

/* loaded from: classes2.dex */
public final class NativeHttpsSessionConfiguration {
    final boolean allowsCellularAccess;
    final int timeoutInterval;

    public NativeHttpsSessionConfiguration(int i, boolean z) {
        this.timeoutInterval = i;
        this.allowsCellularAccess = z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeHttpsSessionConfiguration)) {
            return false;
        }
        NativeHttpsSessionConfiguration nativeHttpsSessionConfiguration = (NativeHttpsSessionConfiguration) obj;
        return this.timeoutInterval == nativeHttpsSessionConfiguration.timeoutInterval && this.allowsCellularAccess == nativeHttpsSessionConfiguration.allowsCellularAccess;
    }

    public boolean getAllowsCellularAccess() {
        return this.allowsCellularAccess;
    }

    public int getTimeoutInterval() {
        return this.timeoutInterval;
    }

    public int hashCode() {
        return ((this.timeoutInterval + 527) * 31) + (this.allowsCellularAccess ? 1 : 0);
    }

    public String toString() {
        return "NativeHttpsSessionConfiguration{timeoutInterval=" + this.timeoutInterval + ",allowsCellularAccess=" + this.allowsCellularAccess + "}";
    }
}
