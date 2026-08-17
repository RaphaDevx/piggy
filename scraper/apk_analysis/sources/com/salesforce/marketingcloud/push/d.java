package com.salesforce.marketingcloud.push;

import com.salesforce.marketingcloud.push.f;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String message) {
        super(f.a.c, "Invalid json: " + message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
