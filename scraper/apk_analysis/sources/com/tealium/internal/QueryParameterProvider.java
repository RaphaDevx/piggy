package com.tealium.internal;

import java.util.Map;

/* loaded from: classes2.dex */
public interface QueryParameterProvider {

    public interface QueryParameterUpdatedNotifier {
        void onNotifyUpdatedParameters(Map<String, String[]> map);
    }

    void provideParameters(QueryParameterUpdatedNotifier queryParameterUpdatedNotifier);
}
