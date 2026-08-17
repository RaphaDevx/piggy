package okio.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: -Utf8.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00fb, code lost:
    
        if ((r16[r5] & 192) == 128) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0090, code lost:
    
        if ((r16[r5] & 192) == 128) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String commonToUtf8String(byte[] r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[], int, int):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        char charAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] bArr = new byte[str.length() * 4];
        int length = str.length();
        if (length > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (Intrinsics.compare((int) charAt2, 128) >= 0) {
                    int length2 = str.length();
                    int i4 = i2;
                    while (i2 < length2) {
                        char charAt3 = str.charAt(i2);
                        if (Intrinsics.compare((int) charAt3, 128) < 0) {
                            int i5 = i4 + 1;
                            bArr[i4] = (byte) charAt3;
                            i2++;
                            while (true) {
                                i4 = i5;
                                if (i2 < length2 && Intrinsics.compare((int) str.charAt(i2), 128) < 0) {
                                    i5 = i4 + 1;
                                    bArr[i4] = (byte) str.charAt(i2);
                                    i2++;
                                }
                            }
                        } else {
                            if (Intrinsics.compare((int) charAt3, 2048) < 0) {
                                bArr[i4] = (byte) ((charAt3 >> 6) | 192);
                                i4 += 2;
                                bArr[i4 + 1] = (byte) ((charAt3 & '?') | 128);
                            } else if (55296 > charAt3 || charAt3 > 57343) {
                                bArr[i4] = (byte) ((charAt3 >> '\f') | 224);
                                bArr[i4 + 1] = (byte) (((charAt3 >> 6) & 63) | 128);
                                i4 += 3;
                                bArr[i4 + 2] = (byte) ((charAt3 & '?') | 128);
                            } else if (Intrinsics.compare((int) charAt3, GeneratorBase.SURR1_LAST) > 0 || length2 <= (i = i2 + 1) || 56320 > (charAt = str.charAt(i)) || charAt > 57343) {
                                bArr[i4] = Utf8.REPLACEMENT_BYTE;
                                i2++;
                                i4++;
                            } else {
                                int charAt4 = ((charAt3 << '\n') + str.charAt(i)) - 56613888;
                                bArr[i4] = (byte) ((charAt4 >> 18) | 240);
                                bArr[i4 + 1] = (byte) (((charAt4 >> 12) & 63) | 128);
                                bArr[i4 + 2] = (byte) (((charAt4 >> 6) & 63) | 128);
                                i4 += 4;
                                bArr[i4 + 3] = (byte) ((charAt4 & 63) | 128);
                                i2 += 2;
                            }
                            i2++;
                        }
                    }
                    byte[] copyOf = Arrays.copyOf(bArr, i4);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                    return copyOf;
                }
                bArr[i2] = (byte) charAt2;
                if (i3 >= length) {
                    break;
                }
                i2 = i3;
            }
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, str.length());
        Intrinsics.checkNotNullExpressionValue(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}
