package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType, Enums.HashType hashType2, int i) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
        this.publicKey = rSAPublicKey;
        this.sigHash = hashType;
        this.mgf1Hash = hashType2;
        this.saltLength = i;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int bitLength = (modulus.bitLength() + 7) / 8;
        int bitLength2 = (modulus.bitLength() + 6) / 8;
        if (bitLength != bArr.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger bytes2Integer = SubtleUtil.bytes2Integer(bArr);
        if (bytes2Integer.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        emsaPssVerify(bArr2, SubtleUtil.integer2Bytes(bytes2Integer.modPow(publicExponent, modulus), bitLength2), modulus.bitLength() - 1);
    }

    private void emsaPssVerify(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] digest = engineFactory.digest(bArr);
        int digestLength = engineFactory.getDigestLength();
        int length = bArr2.length;
        if (length < this.saltLength + digestLength + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        if (bArr2[bArr2.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        int i2 = (length - digestLength) - 1;
        byte[] copyOf = Arrays.copyOf(bArr2, i2);
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, copyOf.length, copyOf.length + digestLength);
        int i3 = 0;
        while (true) {
            int i4 = i2;
            MessageDigest messageDigest = engineFactory;
            byte[] bArr3 = digest;
            long j = (length * 8) - i;
            if (i3 < j) {
                if (((copyOf[i3 / 8] >> (7 - (i3 % 8))) & 1) != 0) {
                    throw new GeneralSecurityException("inconsistent");
                }
                i3++;
                i2 = i4;
                engineFactory = messageDigest;
                digest = bArr3;
            } else {
                byte[] mgf1 = SubtleUtil.mgf1(copyOfRange, i4, this.mgf1Hash);
                int length2 = mgf1.length;
                byte[] bArr4 = new byte[length2];
                for (int i5 = 0; i5 < length2; i5++) {
                    bArr4[i5] = (byte) (mgf1[i5] ^ copyOf[i5]);
                }
                for (int i6 = 0; i6 <= j; i6++) {
                    int i7 = i6 / 8;
                    bArr4[i7] = (byte) ((~(1 << (7 - (i6 % 8)))) & bArr4[i7]);
                }
                int i8 = 0;
                while (true) {
                    int i9 = this.saltLength;
                    if (i8 < (r6 - i9) - 2) {
                        if (bArr4[i8] != 0) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        i8++;
                    } else {
                        if (bArr4[(r6 - i9) - 2] != 1) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, length2 - i9, length2);
                        int i10 = digestLength + 8;
                        byte[] bArr5 = new byte[this.saltLength + i10];
                        System.arraycopy(bArr3, 0, bArr5, 8, bArr3.length);
                        System.arraycopy(copyOfRange2, 0, bArr5, i10, copyOfRange2.length);
                        if (!Bytes.equal(messageDigest.digest(bArr5), copyOfRange)) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        return;
                    }
                }
            }
        }
    }
}
