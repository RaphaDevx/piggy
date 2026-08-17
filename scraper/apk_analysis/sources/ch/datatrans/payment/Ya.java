package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Ya {
    public final boolean a;
    public final String b;

    public Ya(String transactionId, boolean z) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.a = z;
        this.b = transactionId;
    }
}
