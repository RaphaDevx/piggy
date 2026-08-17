package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

/* loaded from: classes2.dex */
public final class X25519 {
    public static byte[] generatePrivateKey() {
        byte[] randBytes = Random.randBytes(32);
        randBytes[0] = (byte) (randBytes[0] | 7);
        byte b = (byte) (randBytes[31] & Utf8.REPLACEMENT_BYTE);
        randBytes[31] = b;
        randBytes[31] = (byte) (b | ByteCompanionObject.MIN_VALUE);
        return randBytes;
    }

    public static byte[] computeSharedSecret(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] copyOf = Arrays.copyOf(bArr, 32);
        copyOf[0] = (byte) (copyOf[0] & 248);
        byte b = (byte) (copyOf[31] & ByteCompanionObject.MAX_VALUE);
        copyOf[31] = b;
        copyOf[31] = (byte) (b | 64);
        Curve25519.curveMult(jArr, copyOf, bArr2);
        return Field25519.contract(jArr);
    }

    public static byte[] publicFromPrivate(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = 9;
        return computeSharedSecret(bArr, bArr2);
    }
}
