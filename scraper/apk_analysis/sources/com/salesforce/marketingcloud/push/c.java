package com.salesforce.marketingcloud.push;

import com.salesforce.marketingcloud.push.f;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(String message) {
        super(f.a.d, "Invalid compression: " + message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
