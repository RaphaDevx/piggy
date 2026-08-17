package com.salesforce.marketingcloud.tozny;

import android.util.Base64;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class AesCbcWithIntegrity {
    private static final int AES_KEY_LENGTH_BITS = 128;
    private static final boolean ALLOW_BROKEN_PRNG = false;
    public static final int BASE64_FLAGS = 2;
    private static final String CIPHER = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final int HMAC_KEY_LENGTH_BITS = 256;
    private static final int IV_LENGTH_BYTES = 16;
    private static final String PBE_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int PBE_SALT_LENGTH_BITS = 128;
    static final AtomicBoolean prngFixed = new AtomicBoolean(false);

    public static class CipherTextIvMac {
        private final byte[] cipherText;
        private final byte[] iv;
        private final byte[] mac;

        public CipherTextIvMac(String str) {
            String[] split = str.split(":");
            if (split.length != 3) {
                throw new IllegalArgumentException("Cannot parse iv:ciphertext:mac");
            }
            this.iv = Base64.decode(split[0], 2);
            this.mac = Base64.decode(split[1], 2);
            this.cipherText = Base64.decode(split[2], 2);
        }

        public static byte[] ivCipherConcat(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[bArr.length + bArr2.length];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
            return bArr3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CipherTextIvMac cipherTextIvMac = (CipherTextIvMac) obj;
            return Arrays.equals(this.cipherText, cipherTextIvMac.cipherText) && Arrays.equals(this.iv, cipherTextIvMac.iv) && Arrays.equals(this.mac, cipherTextIvMac.mac);
        }

        public byte[] getCipherText() {
            return this.cipherText;
        }

        public byte[] getIv() {
            return this.iv;
        }

        public byte[] getMac() {
            return this.mac;
        }

        public int hashCode() {
            return Arrays.hashCode(this.mac) + ((Arrays.hashCode(this.iv) + ((Arrays.hashCode(this.cipherText) + 31) * 31)) * 31);
        }

        public String toString() {
            String encodeToString = Base64.encodeToString(this.iv, 2);
            String encodeToString2 = Base64.encodeToString(this.cipherText, 2);
            return String.format(encodeToString + ":" + Base64.encodeToString(this.mac, 2) + ":" + encodeToString2, new Object[0]);
        }

        public CipherTextIvMac(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[bArr.length];
            this.cipherText = bArr4;
            System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
            byte[] bArr5 = new byte[bArr2.length];
            this.iv = bArr5;
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
            byte[] bArr6 = new byte[bArr3.length];
            this.mac = bArr6;
            System.arraycopy(bArr3, 0, bArr6, 0, bArr3.length);
        }
    }

    public static class SecretKeys {
        private SecretKey confidentialityKey;
        private SecretKey integrityKey;

        public SecretKeys(SecretKey secretKey, SecretKey secretKey2) {
            setConfidentialityKey(secretKey);
            setIntegrityKey(secretKey2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SecretKeys secretKeys = (SecretKeys) obj;
            return this.integrityKey.equals(secretKeys.integrityKey) && this.confidentialityKey.equals(secretKeys.confidentialityKey);
        }

        public SecretKey getConfidentialityKey() {
            return this.confidentialityKey;
        }

        public SecretKey getIntegrityKey() {
            return this.integrityKey;
        }

        public int hashCode() {
            return this.integrityKey.hashCode() + ((this.confidentialityKey.hashCode() + 31) * 31);
        }

        public void setConfidentialityKey(SecretKey secretKey) {
            this.confidentialityKey = secretKey;
        }

        public void setIntegrityKey(SecretKey secretKey) {
            this.integrityKey = secretKey;
        }

        public String toString() {
            return Base64.encodeToString(getConfidentialityKey().getEncoded(), 2) + ":" + Base64.encodeToString(getIntegrityKey().getEncoded(), 2);
        }
    }

    public static boolean constantTimeEq(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    public static byte[] decrypt(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys) {
        if (!constantTimeEq(generateMac(CipherTextIvMac.ivCipherConcat(cipherTextIvMac.getIv(), cipherTextIvMac.getCipherText()), secretKeys.getIntegrityKey()), cipherTextIvMac.getMac())) {
            throw new GeneralSecurityException("MAC stored in civ does not match computed MAC.");
        }
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(2, secretKeys.getConfidentialityKey(), new IvParameterSpec(cipherTextIvMac.getIv()));
        return cipher.doFinal(cipherTextIvMac.getCipherText());
    }

    public static String decryptString(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys) {
        return decryptString(cipherTextIvMac, secretKeys, "UTF-8");
    }

    public static CipherTextIvMac encrypt(String str, SecretKeys secretKeys) {
        return encrypt(str, secretKeys, "UTF-8");
    }

    private static void fixPrng() {
        AtomicBoolean atomicBoolean = prngFixed;
        if (atomicBoolean.get()) {
            return;
        }
        synchronized (a.class) {
            if (!atomicBoolean.get()) {
                a.a();
                atomicBoolean.set(true);
            }
        }
    }

    public static byte[] generateIv() {
        return randomBytes(16);
    }

    public static SecretKeys generateKey() {
        fixPrng();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER);
        keyGenerator.init(128);
        return new SecretKeys(keyGenerator.generateKey(), new SecretKeySpec(randomBytes(32), HMAC_ALGORITHM));
    }

    public static SecretKeys generateKeyFromPassword(String str, String str2, int i) {
        return generateKeyFromPassword(str, Base64.decode(str2, 2), i);
    }

    public static byte[] generateMac(byte[] bArr, SecretKey secretKey) {
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(secretKey);
        return mac.doFinal(bArr);
    }

    public static byte[] generateSalt() {
        return randomBytes(128);
    }

    public static String keyString(SecretKeys secretKeys) {
        return secretKeys.toString();
    }

    public static SecretKeys keys(String str) {
        String[] split = str.split(":");
        if (split.length != 2) {
            throw new IllegalArgumentException("Cannot parse aesKey:hmacKey");
        }
        byte[] decode = Base64.decode(split[0], 2);
        if (decode.length != 16) {
            throw new InvalidKeyException("Base64 decoded key is not 128 bytes");
        }
        byte[] decode2 = Base64.decode(split[1], 2);
        if (decode2.length == 32) {
            return new SecretKeys(new SecretKeySpec(decode, 0, decode.length, CIPHER), new SecretKeySpec(decode2, HMAC_ALGORITHM));
        }
        throw new InvalidKeyException("Base64 decoded key is not 256 bytes");
    }

    private static byte[] randomBytes(int i) {
        fixPrng();
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static String saltString(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static String decryptString(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys, String str) {
        return new String(decrypt(cipherTextIvMac, secretKeys), str);
    }

    public static CipherTextIvMac encrypt(String str, SecretKeys secretKeys, String str2) {
        return encrypt(str.getBytes(str2), secretKeys);
    }

    public static SecretKeys generateKeyFromPassword(String str, byte[] bArr, int i) {
        fixPrng();
        byte[] encoded = SecretKeyFactory.getInstance(PBE_ALGORITHM).generateSecret(new PBEKeySpec(str.toCharArray(), bArr, i, 384)).getEncoded();
        return new SecretKeys(new SecretKeySpec(copyOfRange(encoded, 0, 16), CIPHER), new SecretKeySpec(copyOfRange(encoded, 16, 48), HMAC_ALGORITHM));
    }

    public static CipherTextIvMac encrypt(byte[] bArr, SecretKeys secretKeys) {
        byte[] generateIv = generateIv();
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(1, secretKeys.getConfidentialityKey(), new IvParameterSpec(generateIv));
        byte[] iv = cipher.getIV();
        byte[] doFinal = cipher.doFinal(bArr);
        return new CipherTextIvMac(doFinal, iv, generateMac(CipherTextIvMac.ivCipherConcat(iv, doFinal), secretKeys.getIntegrityKey()));
    }
}
