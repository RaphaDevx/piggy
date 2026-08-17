package com.tealium.internal.listeners;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface CollectHttpResponseListener extends BackgroundListener {
    void onCollectHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr);
}
