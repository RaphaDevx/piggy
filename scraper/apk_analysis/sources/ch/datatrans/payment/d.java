package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class d extends AbstractC0138f {
    public final X9 b;
    public final K3 d;
    public final String e;
    public final String f;
    public final C0195p0 g;
    public final String h;
    public final x6 i;
    public final a9 j;
    public final Map k;
    public final W8 l;
    public final SavedPaymentMethod m;
    public final L3 n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String str, W8 payment, SavedPaymentMethod savedPaymentMethod, X9 reqType, LinkedHashMap merchantProperties, K3 k3, String str2, String str3, C0195p0 c0195p0, String str4, x6 x6Var, a9 a9Var, L3 l3) {
        super(str);
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(savedPaymentMethod, "savedPaymentMethod");
        Intrinsics.checkNotNullParameter(reqType, "reqType");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        this.l = payment;
        this.m = savedPaymentMethod;
        this.b = reqType;
        this.k = merchantProperties;
        this.d = k3;
        this.e = str2;
        this.f = str3;
        this.g = c0195p0;
        this.h = str4;
        this.i = x6Var;
        this.j = a9Var;
        this.n = l3;
    }
}
