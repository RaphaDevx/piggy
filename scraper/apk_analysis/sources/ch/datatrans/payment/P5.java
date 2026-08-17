package ch.datatrans.payment;

import java.util.Locale;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class P5 {
    public final R9 a;

    public P5(R9 preferencesRepository) {
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        this.a = preferencesRepository;
    }

    public final Unit a(S3 s3) {
        R9 r9 = this.a;
        Intrinsics.checkNotNullParameter("DTPL_INSTALLATION_ID", "key");
        if (r9.a.contains("DTPL_INSTALLATION_ID")) {
            return Unit.INSTANCE;
        }
        String lowerCase = String.valueOf(UUID.randomUUID()).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        StringBuilder sb = new StringBuilder();
        int length = lowerCase.length();
        for (int i = 0; i < length; i++) {
            char charAt = lowerCase.charAt(i);
            if (Character.isLetter(charAt) || Character.isDigit(charAt)) {
                sb.append(charAt);
            }
        }
        Unit a = this.a.a("DTPL_INSTALLATION_ID", sb.toString(), true);
        return a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? a : Unit.INSTANCE;
    }
}
