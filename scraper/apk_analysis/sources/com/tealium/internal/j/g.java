package com.tealium.internal.j;

import com.tealium.internal.listeners.CollectHttpResponseListener;
import java.util.List;
import java.util.Map;

/* compiled from: CollectHttpResponseMessenger.java */
/* loaded from: classes2.dex */
public class g extends n<CollectHttpResponseListener> {
    private final String b;
    private final String c;
    private final int d;
    private final Map<String, List<String>> e;
    private final byte[] f;

    public g(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr) {
        super(CollectHttpResponseListener.class);
        this.b = str;
        if (str != null) {
            this.c = str2;
            if (str2 != null) {
                this.d = i;
                if (i != 0) {
                    this.e = map;
                    if (map != null) {
                        this.f = bArr;
                        if (bArr != null) {
                            return;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // com.tealium.internal.j.n
    public void a(CollectHttpResponseListener collectHttpResponseListener) {
        collectHttpResponseListener.onCollectHttpResponse(this.b, this.c, this.d, this.e, this.f);
    }
}
