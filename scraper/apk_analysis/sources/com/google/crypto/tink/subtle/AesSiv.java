package com.google.crypto.tink.subtle;

import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public final class AesSiv implements DeterministicAead {
    private final byte[] aesCtrKey;
    private final AesCmac cmacForS2V;
    private static final Collection<Integer> KEY_SIZES = Arrays.asList(64);
    private static final byte[] BLOCK_ZERO = new byte[16];
    private static final byte[] BLOCK_ONE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    public AesSiv(byte[] bArr) throws GeneralSecurityException {
        if (!KEY_SIZES.contains(Integer.valueOf(bArr.length))) {
            throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 64 bytes");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
        this.aesCtrKey = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
        this.cmacForS2V = new AesCmac(copyOfRange, 16);
    }

    private byte[] s2v(byte[]... bArr) throws GeneralSecurityException {
        byte[] xor;
        if (bArr.length == 0) {
            return this.cmacForS2V.computeMac(BLOCK_ONE);
        }
        byte[] computeMac = this.cmacForS2V.computeMac(BLOCK_ZERO);
        for (int i = 0; i < bArr.length - 1; i++) {
            byte[] bArr2 = bArr[i];
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            computeMac = Bytes.xor(AesUtil.dbl(computeMac), this.cmacForS2V.computeMac(bArr2));
        }
        byte[] bArr3 = bArr[bArr.length - 1];
        if (bArr3.length >= 16) {
            xor = Bytes.xorEnd(bArr3, computeMac);
        } else {
            xor = Bytes.xor(AesUtil.cmacPad(bArr3), AesUtil.dbl(computeMac));
        }
        return this.cmacForS2V.computeMac(xor);
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483631) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] s2v = s2v(bArr2, bArr);
        byte[] bArr3 = (byte[]) s2v.clone();
        bArr3[8] = (byte) (bArr3[8] & ByteCompanionObject.MAX_VALUE);
        bArr3[12] = (byte) (bArr3[12] & ByteCompanionObject.MAX_VALUE);
        engineFactory.init(1, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(bArr3));
        return Bytes.concat(s2v, engineFactory.doFinal(bArr));
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length < 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
        byte[] bArr3 = (byte[]) copyOfRange.clone();
        bArr3[8] = (byte) (bArr3[8] & ByteCompanionObject.MAX_VALUE);
        bArr3[12] = (byte) (bArr3[12] & ByteCompanionObject.MAX_VALUE);
        engineFactory.init(2, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(bArr3));
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, bArr.length);
        byte[] doFinal = engineFactory.doFinal(copyOfRange2);
        if (copyOfRange2.length == 0 && doFinal == null && SubtleUtil.isAndroid()) {
            doFinal = new byte[0];
        }
        if (Bytes.equal(copyOfRange, s2v(bArr2, doFinal))) {
            return doFinal;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }
}
