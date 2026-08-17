package ch.datatrans.payment;

import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class C3 {
    public static final C3 i = new C3("", new G3(CollectionsKt.emptyList()));
    public final String a;
    public final G3 b;
    public final Lazy c;
    public final boolean d;
    public final Lazy e;
    public final Lazy f;
    public final Lazy g;
    public final Lazy h;

    public C3(String number, G3 cardType) {
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(cardType, "cardType");
        this.a = number;
        this.b = cardType;
        this.c = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(C3.b(C3.this));
            }
        });
        this.d = cardType.f.getLengthRange().contains(Integer.valueOf(number.length()));
        LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(C3.a(C3.this));
            }
        });
        this.e = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(C3.e(C3.this));
            }
        });
        this.f = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(C3.c(C3.this));
            }
        });
        this.g = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C3.f(C3.this);
            }
        });
        this.h = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C3$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(C3.d(C3.this));
            }
        });
    }

    public static final boolean a(C3 c3) {
        return c3.a.length() == 0;
    }

    public static final boolean b(C3 c3) {
        int length = c3.a.length();
        Integer num = (Integer) CollectionsKt.maxOrNull((Iterable) c3.b.f.getLengthRange());
        return num != null && length == num.intValue();
    }

    public static final boolean c(C3 c3) {
        return c3.b.a && ((Boolean) c3.c.getValue()).booleanValue() && ((Boolean) c3.h.getValue()).booleanValue();
    }

    public static final boolean d(C3 c3) {
        if (StringsKt.toLongOrNull(c3.a) != null) {
            String str = c3.a;
            ArrayList arrayList = new ArrayList(str.length());
            for (int i2 = 0; i2 < str.length(); i2++) {
                arrayList.add(Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i2)))));
            }
            int i3 = 0;
            int i4 = 0;
            for (Object obj : CollectionsKt.reversed(arrayList)) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int intValue = ((Number) obj).intValue();
                if (i4 % 2 != 0) {
                    intValue = intValue != 9 ? (intValue * 2) % 9 : 9;
                }
                i3 += intValue;
                i4 = i5;
            }
            if (i3 % 10 == 0) {
                return true;
            }
        }
        return false;
    }

    public static final boolean e(C3 c3) {
        return c3.b.a && c3.d && ((Boolean) c3.h.getValue()).booleanValue();
    }

    public static final Integer f(C3 c3) {
        return (c3.b.a && ((Boolean) c3.c.getValue()).booleanValue() && !((Boolean) c3.e.getValue()).booleanValue()) ? Integer.valueOf(R.string.datatrans_sdk_incorrect_number_hint) : c3.b.e;
    }
}
