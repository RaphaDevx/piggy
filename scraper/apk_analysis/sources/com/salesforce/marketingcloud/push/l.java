package com.salesforce.marketingcloud.push;

import com.salesforce.marketingcloud.push.f;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class l extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Throwable cause) {
        super(f.a.b, "Unknown error occurred: " + cause.getMessage());
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
