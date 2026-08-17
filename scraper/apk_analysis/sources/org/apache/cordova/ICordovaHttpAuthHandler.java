package org.apache.cordova;

/* loaded from: classes4.dex */
public interface ICordovaHttpAuthHandler {
    void cancel();

    void proceed(String username, String password);
}
