package com.scandit.datacapture.core.internal.module.source;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import java.lang.ref.WeakReference;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class HandlerC0554a extends Handler {
    public final WeakReference a;

    public HandlerC0554a(C0595n delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.a = new WeakReference(delegate);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        C0595n c0595n = (C0595n) this.a.get();
        if (c0595n == null) {
            return;
        }
        int i = msg.what;
        if (i == 1) {
            Object obj = msg.obj;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Pair<android.graphics.SurfaceTexture, kotlin.Function1<kotlin.Boolean, kotlin.Unit>>");
            Pair pair = (Pair) obj;
            ((Function1) pair.getSecond()).invoke(Boolean.valueOf(c0595n.d.a((SurfaceTexture) pair.getFirst())));
            return;
        }
        if (i == 2) {
            c0595n.a(true);
            Object obj2 = msg.obj;
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
            C0595n.a(c0595n, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj2, 1));
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            c0595n.b();
            return;
        }
        c0595n.a(false);
        Object obj3 = msg.obj;
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
        C0595n.a(c0595n, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj3, 1));
        HandlerC0554a handlerC0554a = c0595n.e;
        if (handlerC0554a != null) {
            handlerC0554a.sendMessageDelayed(handlerC0554a.obtainMessage(4), EventForResult.DEFAULT_TIMEOUT_MILLIS);
        }
    }
}
