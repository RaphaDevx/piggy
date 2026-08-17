package io.sentry.util;

import java.util.UUID;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

/* loaded from: classes3.dex */
public final class UUIDGenerator {
    public static long randomHalfLengthUUID() {
        byte[] bArr = new byte[8];
        SentryRandom.current().nextBytes(bArr);
        byte b = (byte) (bArr[6] & 15);
        bArr[6] = b;
        bArr[6] = (byte) (b | 64);
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | (bArr[i] & UByte.MAX_VALUE);
        }
        return j;
    }

    public static UUID randomUUID() {
        byte[] bArr = new byte[16];
        SentryRandom.current().nextBytes(bArr);
        byte b = (byte) (bArr[6] & 15);
        bArr[6] = b;
        bArr[6] = (byte) (b | 64);
        byte b2 = (byte) (bArr[8] & Utf8.REPLACEMENT_BYTE);
        bArr[8] = b2;
        bArr[8] = (byte) (b2 | ByteCompanionObject.MIN_VALUE);
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 << 8) | (bArr[i] & UByte.MAX_VALUE);
        }
        for (int i2 = 8; i2 < 16; i2++) {
            j = (j << 8) | (bArr[i2] & UByte.MAX_VALUE);
        }
        return new UUID(j2, j);
    }
}
