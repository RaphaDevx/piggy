package com.scandit.datacapture.core.internal.sdk.common;

/* loaded from: classes2.dex */
public final class NativeError {
    final int code;
    final String message;

    public NativeError(String str, int i) {
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
        return "NativeError{message=" + this.message + ",code=" + this.code + "}";
    }
}
