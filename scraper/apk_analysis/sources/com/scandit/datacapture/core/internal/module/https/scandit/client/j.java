package com.scandit.datacapture.core.internal.module.https.scandit.client;

import com.scandit.datacapture.core.internal.module.https.scandit.C0549v;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements k {
    public final C0549v a;

    public j(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        C0549v error = new C0549v(message);
        Intrinsics.checkNotNullParameter(error, "error");
        this.a = error;
    }

    public final String toString() {
        return "Block{error=" + this.a.getMessage() + '}';
    }
}
