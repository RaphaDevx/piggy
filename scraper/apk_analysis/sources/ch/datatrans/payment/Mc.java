package ch.datatrans.payment;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes3.dex */
public final class Mc {
    public static final void a(byte[] bArr) {
        for (byte b : bArr) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(String.format("%02X ", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1)), "format(...)");
        }
    }
}
