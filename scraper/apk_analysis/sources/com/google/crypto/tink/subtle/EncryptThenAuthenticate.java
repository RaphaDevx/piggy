package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Mac;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public final class EncryptThenAuthenticate implements Aead {
    private final IndCpaCipher cipher;
    private final Mac mac;
    private final int macLength;

    public EncryptThenAuthenticate(IndCpaCipher indCpaCipher, Mac mac, int i) {
        this.cipher = indCpaCipher;
        this.mac = mac;
        this.macLength = i;
    }

    public static Aead newAesCtrHmac(byte[] bArr, int i, String str, byte[] bArr2, int i2) throws GeneralSecurityException {
        return new EncryptThenAuthenticate(new AesCtrJceCipher(bArr, i), new MacJce(str, new SecretKeySpec(bArr2, "HMAC"), i2), i2);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] encrypt = this.cipher.encrypt(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return Bytes.concat(encrypt, this.mac.computeMac(Bytes.concat(bArr2, encrypt, Arrays.copyOf(ByteBuffer.allocate(8).putLong(bArr2.length * 8).array(), 8))));
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.macLength;
        if (length < i) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length - i);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, bArr.length - this.macLength, bArr.length);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this.mac.verifyMac(copyOfRange2, Bytes.concat(bArr2, copyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(bArr2.length * 8).array(), 8)));
        return this.cipher.decrypt(copyOfRange);
    }
}
