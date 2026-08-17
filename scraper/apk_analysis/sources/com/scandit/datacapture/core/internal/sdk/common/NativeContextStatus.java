package com.scandit.datacapture.core.internal.sdk.common;

/* loaded from: classes2.dex */
public final class NativeContextStatus {
    final int code;
    final String message;

    public NativeContextStatus(String str, int i) {
        this.message = str;
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "NativeContextStatus{message=" + this.message + ",code=" + this.code + "}";
    }
}
