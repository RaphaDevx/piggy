package com.tealium.library;

import com.tealium.internal.data.Dispatch;

/* loaded from: classes2.dex */
public abstract class DispatchValidator {
    protected boolean shouldDrop(Dispatch dispatch) {
        return false;
    }

    protected boolean shouldQueue(Dispatch dispatch, boolean z) {
        return z;
    }
}
