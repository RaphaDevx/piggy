package com.scandit.datacapture.core.internal.module.https;

import java.util.HashMap;

/* loaded from: classes2.dex */
public final class NativeHttpsResponse {
    final byte[] body;
    final HashMap<String, String> headers;
    final int statusCode;

    public NativeHttpsResponse(int i, HashMap<String, String> hashMap, byte[] bArr) {
        this.statusCode = i;
        this.headers = hashMap;
        this.body = bArr;
    }

    public byte[] getBody() {
        return this.body;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return "NativeHttpsResponse{statusCode=" + this.statusCode + ",headers=" + this.headers + ",body=" + this.body + "}";
    }
}
