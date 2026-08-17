package com.tealium.internal.listeners;

import com.tealium.internal.data.Dispatch;
import java.util.List;

/* loaded from: classes2.dex */
public interface BulkDispatchSendListener extends BackgroundListener {
    void onBulkDispatchSend(List<Dispatch> list);
}
