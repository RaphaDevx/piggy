package kotlin.comparisons;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _UComparisons.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a(\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a(\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0087\b¢\u0006\u0004\b\u0014\u0010\u0015\u001a(\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0087\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0001H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a#\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\n\u0010\u0018\u001a\u00020\u001c\"\u00020\u0006H\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a#\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u0018\u001a\u00020\u001f\"\u00020\tH\u0007¢\u0006\u0004\b \u0010!\u001a#\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u0018\u001a\u00020\"\"\u00020\fH\u0007¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b&\u0010\u0005\u001a\u001f\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0007¢\u0006\u0004\b'\u0010\b\u001a\u001f\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0007¢\u0006\u0004\b(\u0010\u000b\u001a\u001f\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0007¢\u0006\u0004\b)\u0010\u000e\u001a(\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b*\u0010\u0011\u001a(\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b+\u0010\u0013\u001a(\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0087\b¢\u0006\u0004\b,\u0010\u0015\u001a(\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0087\b¢\u0006\u0004\b-\u0010\u0017\u001a#\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0001H\u0007¢\u0006\u0004\b.\u0010\u001b\u001a#\u0010%\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\n\u0010\u0018\u001a\u00020\u001c\"\u00020\u0006H\u0007¢\u0006\u0004\b/\u0010\u001e\u001a#\u0010%\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u0018\u001a\u00020\u001f\"\u00020\tH\u0007¢\u0006\u0004\b0\u0010!\u001a#\u0010%\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u0018\u001a\u00020\"\"\u00020\fH\u0007¢\u0006\u0004\b1\u0010$¨\u00062"}, d2 = {"maxOf", "Lkotlin/UInt;", "a", "b", "maxOf-J1ME1BU", "(II)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "Lkotlin/UByte;", "maxOf-Kr8caGY", "(BB)B", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "c", "maxOf-WZ9TVnA", "(III)I", "maxOf-sambcqE", "(JJJ)J", "maxOf-b33U2AM", "(BBB)B", "maxOf-VKSA0NQ", "(SSS)S", Request.JsonKeys.OTHER, "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-J1ME1BU", "minOf-eb3DHEI", "minOf-Kr8caGY", "minOf-5PvTz6A", "minOf-WZ9TVnA", "minOf-sambcqE", "minOf-b33U2AM", "minOf-VKSA0NQ", "minOf-Md2H83M", "minOf-R03FKyM", "minOf-Wr6uiD8", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, xs = "kotlin/comparisons/UComparisonsKt")
/* loaded from: classes4.dex */
public class UComparisonsKt___UComparisonsKt {
    /* renamed from: maxOf-J1ME1BU, reason: not valid java name */
    public static final int m5132maxOfJ1ME1BU(int i, int i2) {
        return Integer.compareUnsigned(i, i2) >= 0 ? i : i2;
    }

    /* renamed from: maxOf-eb3DHEI, reason: not valid java name */
    public static final long m5140maxOfeb3DHEI(long j, long j2) {
        return Long.compareUnsigned(j, j2) >= 0 ? j : j2;
    }

    /* renamed from: maxOf-Kr8caGY, reason: not valid java name */
    public static final byte m5133maxOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & UByte.MAX_VALUE, b2 & UByte.MAX_VALUE) >= 0 ? b : b2;
    }

    /* renamed from: maxOf-5PvTz6A, reason: not valid java name */
    public static final short m5131maxOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) >= 0 ? s : s2;
    }

    /* renamed from: maxOf-WZ9TVnA, reason: not valid java name */
    private static final int m5137maxOfWZ9TVnA(int i, int i2, int i3) {
        return UComparisonsKt.m5132maxOfJ1ME1BU(i, UComparisonsKt.m5132maxOfJ1ME1BU(i2, i3));
    }

    /* renamed from: maxOf-sambcqE, reason: not valid java name */
    private static final long m5141maxOfsambcqE(long j, long j2, long j3) {
        return UComparisonsKt.m5140maxOfeb3DHEI(j, UComparisonsKt.m5140maxOfeb3DHEI(j2, j3));
    }

    /* renamed from: maxOf-b33U2AM, reason: not valid java name */
    private static final byte m5139maxOfb33U2AM(byte b, byte b2, byte b3) {
        return UComparisonsKt.m5133maxOfKr8caGY(b, UComparisonsKt.m5133maxOfKr8caGY(b2, b3));
    }

    /* renamed from: maxOf-VKSA0NQ, reason: not valid java name */
    private static final short m5136maxOfVKSA0NQ(short s, short s2, short s3) {
        return UComparisonsKt.m5131maxOf5PvTz6A(s, UComparisonsKt.m5131maxOf5PvTz6A(s2, s3));
    }

    /* renamed from: maxOf-Md2H83M, reason: not valid java name */
    public static final int m5134maxOfMd2H83M(int i, int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4101getSizeimpl = UIntArray.m4101getSizeimpl(other);
        for (int i2 = 0; i2 < m4101getSizeimpl; i2++) {
            i = UComparisonsKt.m5132maxOfJ1ME1BU(i, UIntArray.m4100getpVg5ArA(other, i2));
        }
        return i;
    }

    /* renamed from: maxOf-R03FKyM, reason: not valid java name */
    public static final long m5135maxOfR03FKyM(long j, long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4180getSizeimpl = ULongArray.m4180getSizeimpl(other);
        for (int i = 0; i < m4180getSizeimpl; i++) {
            j = UComparisonsKt.m5140maxOfeb3DHEI(j, ULongArray.m4179getsVKNKU(other, i));
        }
        return j;
    }

    /* renamed from: maxOf-Wr6uiD8, reason: not valid java name */
    public static final byte m5138maxOfWr6uiD8(byte b, byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4022getSizeimpl = UByteArray.m4022getSizeimpl(other);
        for (int i = 0; i < m4022getSizeimpl; i++) {
            b = UComparisonsKt.m5133maxOfKr8caGY(b, UByteArray.m4021getw2LRezQ(other, i));
        }
        return b;
    }

    /* renamed from: maxOf-t1qELG4, reason: not valid java name */
    public static final short m5142maxOft1qELG4(short s, short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4285getSizeimpl = UShortArray.m4285getSizeimpl(other);
        for (int i = 0; i < m4285getSizeimpl; i++) {
            s = UComparisonsKt.m5131maxOf5PvTz6A(s, UShortArray.m4284getMh2AYeg(other, i));
        }
        return s;
    }

    /* renamed from: minOf-J1ME1BU, reason: not valid java name */
    public static final int m5144minOfJ1ME1BU(int i, int i2) {
        return Integer.compareUnsigned(i, i2) <= 0 ? i : i2;
    }

    /* renamed from: minOf-eb3DHEI, reason: not valid java name */
    public static final long m5152minOfeb3DHEI(long j, long j2) {
        return Long.compareUnsigned(j, j2) <= 0 ? j : j2;
    }

    /* renamed from: minOf-Kr8caGY, reason: not valid java name */
    public static final byte m5145minOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & UByte.MAX_VALUE, b2 & UByte.MAX_VALUE) <= 0 ? b : b2;
    }

    /* renamed from: minOf-5PvTz6A, reason: not valid java name */
    public static final short m5143minOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) <= 0 ? s : s2;
    }

    /* renamed from: minOf-WZ9TVnA, reason: not valid java name */
    private static final int m5149minOfWZ9TVnA(int i, int i2, int i3) {
        return UComparisonsKt.m5144minOfJ1ME1BU(i, UComparisonsKt.m5144minOfJ1ME1BU(i2, i3));
    }

    /* renamed from: minOf-sambcqE, reason: not valid java name */
    private static final long m5153minOfsambcqE(long j, long j2, long j3) {
        return UComparisonsKt.m5152minOfeb3DHEI(j, UComparisonsKt.m5152minOfeb3DHEI(j2, j3));
    }

    /* renamed from: minOf-b33U2AM, reason: not valid java name */
    private static final byte m5151minOfb33U2AM(byte b, byte b2, byte b3) {
        return UComparisonsKt.m5145minOfKr8caGY(b, UComparisonsKt.m5145minOfKr8caGY(b2, b3));
    }

    /* renamed from: minOf-VKSA0NQ, reason: not valid java name */
    private static final short m5148minOfVKSA0NQ(short s, short s2, short s3) {
        return UComparisonsKt.m5143minOf5PvTz6A(s, UComparisonsKt.m5143minOf5PvTz6A(s2, s3));
    }

    /* renamed from: minOf-Md2H83M, reason: not valid java name */
    public static final int m5146minOfMd2H83M(int i, int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4101getSizeimpl = UIntArray.m4101getSizeimpl(other);
        for (int i2 = 0; i2 < m4101getSizeimpl; i2++) {
            i = UComparisonsKt.m5144minOfJ1ME1BU(i, UIntArray.m4100getpVg5ArA(other, i2));
        }
        return i;
    }

    /* renamed from: minOf-R03FKyM, reason: not valid java name */
    public static final long m5147minOfR03FKyM(long j, long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4180getSizeimpl = ULongArray.m4180getSizeimpl(other);
        for (int i = 0; i < m4180getSizeimpl; i++) {
            j = UComparisonsKt.m5152minOfeb3DHEI(j, ULongArray.m4179getsVKNKU(other, i));
        }
        return j;
    }

    /* renamed from: minOf-Wr6uiD8, reason: not valid java name */
    public static final byte m5150minOfWr6uiD8(byte b, byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4022getSizeimpl = UByteArray.m4022getSizeimpl(other);
        for (int i = 0; i < m4022getSizeimpl; i++) {
            b = UComparisonsKt.m5145minOfKr8caGY(b, UByteArray.m4021getw2LRezQ(other, i));
        }
        return b;
    }

    /* renamed from: minOf-t1qELG4, reason: not valid java name */
    public static final short m5154minOft1qELG4(short s, short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m4285getSizeimpl = UShortArray.m4285getSizeimpl(other);
        for (int i = 0; i < m4285getSizeimpl; i++) {
            s = UComparisonsKt.m5143minOf5PvTz6A(s, UShortArray.m4284getMh2AYeg(other, i));
        }
        return s;
    }
}
