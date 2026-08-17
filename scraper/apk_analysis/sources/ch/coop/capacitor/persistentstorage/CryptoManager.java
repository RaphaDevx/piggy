package ch.coop.capacitor.persistentstorage;

import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import io.sentry.Sentry;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* loaded from: classes3.dex */
public class CryptoManager {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String CIPHER_COMPONENT_SEPARATOR = "]";
    public static final Boolean DEBUG = false;
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_BLOCK_MODE = "GCM";
    private static final String ENCRYPTION_PADDING = "NoPadding";
    private static final int KEY_SIZE = 256;
    private final String keyAlias;
    public SecretKey symmetricKey = getOrCreateEncryptionKey();

    public CryptoManager(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getCipherTransformation() {
        return String.format("%s/%s/%s", ENCRYPTION_ALGORITHM, "GCM", ENCRYPTION_PADDING);
    }

    private SecretKey getOrCreateEncryptionKey() {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
        } catch (Exception e) {
            if (DEBUG.booleanValue()) {
                e.printStackTrace();
            }
            Sentry.captureException(e);
        }
        if (!keyStore.containsAlias(this.keyAlias)) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, ANDROID_KEY_STORE);
            keyGenerator.init(new KeyGenParameterSpec.Builder(this.keyAlias, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings(ENCRYPTION_PADDING).build());
            return keyGenerator.generateKey();
        }
        KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(this.keyAlias, null);
        if (secretKeyEntry != null) {
            return secretKeyEntry.getSecretKey();
        }
        return null;
    }

    public String encrypt(String plaintext) {
        if (TextUtils.isEmpty(plaintext) || this.symmetricKey == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(getCipherTransformation());
            cipher.init(1, this.symmetricKey);
            GCMParameterSpec gCMParameterSpec = (GCMParameterSpec) cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
            String str = gCMParameterSpec.getTLen() + CIPHER_COMPONENT_SEPARATOR + toBase64(gCMParameterSpec.getIV()) + CIPHER_COMPONENT_SEPARATOR + toBase64(cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8)));
            if (DEBUG.booleanValue()) {
                Log.d("Encryption", "encrypted value: " + plaintext + "\nto: " + str);
            }
            return str;
        } catch (Exception e) {
            if (DEBUG.booleanValue()) {
                e.printStackTrace();
            }
            Sentry.captureException(e);
            return null;
        }
    }

    public String decrypt(String tLenIvCiphertext) {
        if (TextUtils.isEmpty(tLenIvCiphertext) || this.symmetricKey == null) {
            return null;
        }
        try {
            String[] split = tLenIvCiphertext.split(CIPHER_COMPONENT_SEPARATOR);
            if (split.length != 3) {
                throw new IllegalArgumentException("Passed data is incorrect. There was no authentication tag length or initialization vector specified with it.");
            }
            int parseInt = Integer.parseInt(split[0]);
            byte[] fromBase64 = fromBase64(split[1]);
            byte[] fromBase642 = fromBase64(split[2]);
            Cipher cipher = Cipher.getInstance(getCipherTransformation());
            cipher.init(2, this.symmetricKey, new GCMParameterSpec(parseInt, fromBase64));
            String str = new String(cipher.doFinal(fromBase642), StandardCharsets.UTF_8);
            if (DEBUG.booleanValue()) {
                Log.d("Encryption", "decrypted ciphertext: " + tLenIvCiphertext + "\nto: " + str);
            }
            return str;
        } catch (Exception e) {
            if (DEBUG.booleanValue()) {
                e.printStackTrace();
            }
            Sentry.captureException(e);
            return null;
        }
    }

    private String toBase64(byte[] input) {
        return new String(Base64.encode(input, 0), StandardCharsets.UTF_8);
    }

    private byte[] fromBase64(String input) {
        return Base64.decode(input.getBytes(StandardCharsets.UTF_8), 0);
    }
}
