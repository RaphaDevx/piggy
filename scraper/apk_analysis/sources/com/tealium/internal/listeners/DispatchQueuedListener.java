package com.tealium.internal.listeners;

import com.tealium.internal.data.Dispatch;

/* loaded from: classes2.dex */
public interface DispatchQueuedListener extends BackgroundListener {
    void onDispatchQueued(Dispatch dispatch);
}
