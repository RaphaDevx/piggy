package com.scandit.datacapture.core.internal.module.https;

/* loaded from: classes2.dex */
public abstract class NativeHttpsSession {
    public abstract NativeHttpsSessionConfiguration getConfiguration();

    public abstract NativeHttpsSessionDelegate getDelegate();

    public abstract void setDelegate(NativeHttpsSessionDelegate nativeHttpsSessionDelegate);

    public abstract void shouldAllowExpiredCertificates(boolean z);

    public abstract NativeHttpsTask startRequest(NativeHttpsRequest nativeHttpsRequest);
}
