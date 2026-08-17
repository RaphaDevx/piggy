package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public class I9 implements Ae {
    public final C0196p5 a;
    public final Be b;
    public final boolean c;
    public final Lazy d;
    public final int e;
    public final String f;

    public I9(C0196p5 httpRequest, PaymentMethodType type, Be listener, boolean z) {
        Intrinsics.checkNotNullParameter(httpRequest, "httpRequest");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = httpRequest;
        this.b = listener;
        this.c = z;
        this.d = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.I9$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return I9.a(I9.this);
            }
        });
        this.e = type.getViewPortWidth();
        this.f = type.getViewPortAdjustment();
    }

    public static final List a(I9 i9) {
        C0156he c0156he = r4.d;
        C0156he c0156he2 = null;
        if (c0156he == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webCallbackUrls");
            c0156he = null;
        }
        G g = new G(c0156he.a, i9.b);
        C0156he c0156he3 = r4.d;
        if (c0156he3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webCallbackUrls");
            c0156he3 = null;
        }
        C0246w4 c0246w4 = new C0246w4(c0156he3.b, i9.b);
        C0156he c0156he4 = r4.d;
        if (c0156he4 != null) {
            c0156he2 = c0156he4;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("webCallbackUrls");
        }
        return CollectionsKt.listOf((Object[]) new Y9[]{g, c0246w4, new C0112ab(c0156he2.c, i9.b)});
    }

    @Override // ch.datatrans.payment.Ae
    public final int b() {
        return this.e;
    }

    @Override // ch.datatrans.payment.Ae
    public final AbstractC0200q5 c() {
        return this.a;
    }

    @Override // ch.datatrans.payment.Ae
    public boolean d() {
        return this.c;
    }

    @Override // ch.datatrans.payment.Ae
    public final String e() {
        return c().a;
    }

    @Override // ch.datatrans.payment.Ae
    public final Be g() {
        return this.b;
    }

    @Override // ch.datatrans.payment.Ae
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public List f() {
        return (List) this.d.getValue();
    }

    @Override // ch.datatrans.payment.Ae
    public final String a() {
        return this.f;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public I9(String url, PaymentMethodType type, Be listener) {
        this(new C0196p5(url, "", MapsKt.emptyMap()), type, listener, false);
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(listener, "listener");
    }
}
