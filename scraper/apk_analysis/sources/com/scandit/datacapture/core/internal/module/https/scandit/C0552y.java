package com.scandit.datacapture.core.internal.module.https.scandit;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.y, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0552y extends D {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0552y(String from, String to) {
        super(new IllegalStateException("Request was routed from \"" + from + "\" to \"" + to + '\"'));
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
    }
}
