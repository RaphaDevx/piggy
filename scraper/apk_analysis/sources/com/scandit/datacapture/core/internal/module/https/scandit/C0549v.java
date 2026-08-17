package com.scandit.datacapture.core.internal.module.https.scandit;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.v, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0549v extends D {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0549v(String message) {
        super(new IOException(message));
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
