package ch.datatrans.payment;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class X8 extends AbstractC0138f {
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final X9 f;
    public final String h;
    public final Map k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X8(String transactionId, String merchantId, String refno, String str, String str2, X9 reqType, LinkedHashMap merchantProperties, String str3, boolean z) {
        super(transactionId);
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(refno, "refno");
        Intrinsics.checkNotNullParameter(reqType, "reqType");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        this.b = merchantId;
        this.c = refno;
        this.d = str;
        this.e = str2;
        this.f = reqType;
        this.k = merchantProperties;
        this.h = str3;
    }
}
