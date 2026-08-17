package com.scandit.datacapture.core.internal.module.source;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class HandlerC0598q extends Handler {
    public final WeakReference a;

    public HandlerC0598q(C0599s pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.a = new WeakReference(pool);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        C0599s c0599s = (C0599s) this.a.get();
        if (c0599s == null) {
            return;
        }
        Object obj = msg.obj;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.ByteArray");
        c0599s.a((byte[]) obj);
    }
}
