package kotlin.collections;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001e\u0010\u000b\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u001f\u0010\u0010\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b \u0010\u0015\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001¢\u0006\u0004\b!\u0010\u001a¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", RRWebVideoEvent.JsonKeys.LEFT, "right", "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m4405partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m4021getw2LRezQ = UByteArray.m4021getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m4021getw2LRezQ2 = UByteArray.m4021getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = m4021getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(m4021getw2LRezQ2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m4021getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m4021getw2LRezQ3 = UByteArray.m4021getw2LRezQ(bArr, i);
                UByteArray.m4026setVurrAj0(bArr, i, UByteArray.m4021getw2LRezQ(bArr, i2));
                UByteArray.m4026setVurrAj0(bArr, i2, m4021getw2LRezQ3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m4409quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m4405partition4UcCI2c = m4405partition4UcCI2c(bArr, i, i2);
        int i3 = m4405partition4UcCI2c - 1;
        if (i < i3) {
            m4409quickSort4UcCI2c(bArr, i, i3);
        }
        if (m4405partition4UcCI2c < i2) {
            m4409quickSort4UcCI2c(bArr, m4405partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m4406partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m4284getMh2AYeg = UShortArray.m4284getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m4284getMh2AYeg2 = UShortArray.m4284getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = m4284getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m4284getMh2AYeg2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m4284getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m4284getMh2AYeg3 = UShortArray.m4284getMh2AYeg(sArr, i);
                UShortArray.m4289set01HTLdE(sArr, i, UShortArray.m4284getMh2AYeg(sArr, i2));
                UShortArray.m4289set01HTLdE(sArr, i2, m4284getMh2AYeg3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m4410quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m4406partitionAa5vz7o = m4406partitionAa5vz7o(sArr, i, i2);
        int i3 = m4406partitionAa5vz7o - 1;
        if (i < i3) {
            m4410quickSortAa5vz7o(sArr, i, i3);
        }
        if (m4406partitionAa5vz7o < i2) {
            m4410quickSortAa5vz7o(sArr, m4406partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m4407partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m4100getpVg5ArA = UIntArray.m4100getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compareUnsigned(UIntArray.m4100getpVg5ArA(iArr, i), m4100getpVg5ArA) < 0) {
                i++;
            }
            while (Integer.compareUnsigned(UIntArray.m4100getpVg5ArA(iArr, i2), m4100getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m4100getpVg5ArA2 = UIntArray.m4100getpVg5ArA(iArr, i);
                UIntArray.m4105setVXSXFK8(iArr, i, UIntArray.m4100getpVg5ArA(iArr, i2));
                UIntArray.m4105setVXSXFK8(iArr, i2, m4100getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m4411quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m4407partitionoBK06Vg = m4407partitionoBK06Vg(iArr, i, i2);
        int i3 = m4407partitionoBK06Vg - 1;
        if (i < i3) {
            m4411quickSortoBK06Vg(iArr, i, i3);
        }
        if (m4407partitionoBK06Vg < i2) {
            m4411quickSortoBK06Vg(iArr, m4407partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m4404partitionnroSd4(long[] jArr, int i, int i2) {
        long m4179getsVKNKU = ULongArray.m4179getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compareUnsigned(ULongArray.m4179getsVKNKU(jArr, i), m4179getsVKNKU) < 0) {
                i++;
            }
            while (Long.compareUnsigned(ULongArray.m4179getsVKNKU(jArr, i2), m4179getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m4179getsVKNKU2 = ULongArray.m4179getsVKNKU(jArr, i);
                ULongArray.m4184setk8EXiF4(jArr, i, ULongArray.m4179getsVKNKU(jArr, i2));
                ULongArray.m4184setk8EXiF4(jArr, i2, m4179getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m4408quickSortnroSd4(long[] jArr, int i, int i2) {
        int m4404partitionnroSd4 = m4404partitionnroSd4(jArr, i, i2);
        int i3 = m4404partitionnroSd4 - 1;
        if (i < i3) {
            m4408quickSortnroSd4(jArr, i, i3);
        }
        if (m4404partitionnroSd4 < i2) {
            m4408quickSortnroSd4(jArr, m4404partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m4413sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4409quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m4414sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4410quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m4415sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4411quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m4412sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m4408quickSortnroSd4(array, i, i2 - 1);
    }
}
