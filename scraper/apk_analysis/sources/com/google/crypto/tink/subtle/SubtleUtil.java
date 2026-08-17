package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class SubtleUtil {
    public static String toEcdsaAlgo(Enums.HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        return hashType + "withECDSA";
    }

    public static String toRsaSsaPkcs1Algo(Enums.HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        return hashType + "withRSA";
    }

    /* renamed from: com.google.crypto.tink.subtle.SubtleUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String toDigestAlgo(Enums.HashType hashType) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[hashType.ordinal()];
        if (i == 1) {
            return "SHA-1";
        }
        if (i == 2) {
            return "SHA-256";
        }
        if (i == 3) {
            return "SHA-384";
        }
        if (i == 4) {
            return "SHA-512";
        }
        throw new GeneralSecurityException("Unsupported hash " + hashType);
    }

    public static boolean isAndroid() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static BigInteger bytes2Integer(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static byte[] integer2Bytes(BigInteger bigInteger, int i) throws GeneralSecurityException {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i) {
            return byteArray;
        }
        int i2 = i + 1;
        if (byteArray.length > i2) {
            throw new GeneralSecurityException("integer too large");
        }
        if (byteArray.length == i2) {
            if (byteArray[0] == 0) {
                return Arrays.copyOfRange(byteArray, 1, byteArray.length);
            }
            throw new GeneralSecurityException("integer too large");
        }
        byte[] bArr = new byte[i];
        System.arraycopy(byteArray, 0, bArr, i - byteArray.length, byteArray.length);
        return bArr;
    }

    public static byte[] mgf1(byte[] bArr, int i, Enums.HashType hashType) throws GeneralSecurityException {
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(toDigestAlgo(hashType));
        int digestLength = engineFactory.getDigestLength();
        byte[] bArr2 = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 <= (i - 1) / digestLength; i3++) {
            engineFactory.reset();
            engineFactory.update(bArr);
            engineFactory.update(integer2Bytes(BigInteger.valueOf(i3), 4));
            byte[] digest = engineFactory.digest();
            System.arraycopy(digest, 0, bArr2, i2, Math.min(digest.length, i - i2));
            i2 += digest.length;
        }
        return bArr2;
    }

    public static void putAsUnsigedInt(ByteBuffer byteBuffer, long j) throws GeneralSecurityException {
        if (0 > j || j >= 4294967296L) {
            throw new GeneralSecurityException("Index out of range");
        }
        byteBuffer.putInt((int) j);
    }
}
