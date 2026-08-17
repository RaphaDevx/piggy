package ch.datatrans.payment;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.p5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0196p5 extends AbstractC0200q5 {
    public final byte[] b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0196p5(String url, String encoding, Map parameters) {
        super(url);
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        byte[] a = AbstractC0192o5.a(encoding, parameters);
        this.b = a;
    }
}
