package com.tealium.internal.listeners;

/* loaded from: classes2.dex */
public interface RequestFlushListener extends BackgroundListener {

    public static class FlushReason {
        public static final String THRESHOLD_REACHED = "threshold reached";
        public static final String TIMEOUT = "timeout";
        public static final String UNKNOWN = "unknown";
        public static final String USER_REQUESTED = "user requested";
    }

    void onRequestFlush(String str);
}
