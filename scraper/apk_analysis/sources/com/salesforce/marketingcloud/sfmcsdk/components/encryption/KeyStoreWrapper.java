package com.salesforce.marketingcloud.sfmcsdk.components.encryption;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

/* loaded from: classes2.dex */
class KeyStoreWrapper {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String EC = "EC";
    private static final int EC_KEY_LENGTH = 256;
    private static final String RSA = "RSA";
    private static final int RSA_KEY_LENGTH = 2048;
    private static final String TAG = "KeyStoreWrapper";
    private final Context context;
    private final KeyStore keyStore = loadKeyStore();

    public KeyStoreWrapper(Context context) throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        this.context = context.getApplicationContext();
    }

    public PublicKey getRSAPublicKey(String str) {
        return getRSAPublicKey(str, 2048);
    }

    public String getRSAPublicString(String str) {
        return getRSAPublicString(str, 2048);
    }

    public PrivateKey getRSAPrivateKey(String str) {
        return getRSAPrivateKey(str, 2048);
    }

    public PublicKey getRSAPublicKey(String str, int i) {
        return getPublicKey(RSA, str, i);
    }

    public String getRSAPublicString(String str, int i) {
        return getPublicKeyString(RSA, str, i);
    }

    public PrivateKey getRSAPrivateKey(String str, int i) {
        return getPrivateKey(RSA, str, i);
    }

    public PublicKey getECPublicKey(String str) {
        return getPublicKey(EC, str, 256);
    }

    public String getECPublicString(String str) {
        return getPublicKeyString(EC, str, 256);
    }

    public PrivateKey getECPrivateKey(String str) {
        return getPrivateKey(EC, str, 256);
    }

    private KeyStore loadKeyStore() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore;
    }

    private PublicKey getPublicKey(String str, String str2, int i) {
        createKeysIfNecessary(str, str2, i);
        try {
            return this.keyStore.getCertificate(str2).getPublicKey();
        } catch (Exception e) {
            Log.e(TAG, "Could not retrieve public key", e);
            return null;
        }
    }

    private String getPublicKeyString(String str, String str2, int i) {
        PublicKey publicKey = getPublicKey(str, str2, i);
        if (publicKey != null) {
            return Base64.encodeToString(publicKey.getEncoded(), 3);
        }
        return null;
    }

    private PrivateKey getPrivateKey(String str, String str2, int i) {
        createKeysIfNecessary(str, str2, i);
        try {
            return (PrivateKey) this.keyStore.getKey(str2, null);
        } catch (Exception e) {
            Log.e(TAG, "Could not retrieve private key", e);
            return null;
        }
    }

    private synchronized void createKeysIfNecessary(String str, String str2, int i) {
        try {
            if (!this.keyStore.containsAlias(str2)) {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str, ANDROID_KEYSTORE);
                KeyGenParameterSpec.Builder encryptionPaddings = new KeyGenParameterSpec.Builder(str2, 3).setKeySize(i).setEncryptionPaddings("PKCS1Padding");
                encryptionPaddings.setIsStrongBoxBacked(false);
                keyPairGenerator.initialize(encryptionPaddings.build());
                keyPairGenerator.generateKeyPair();
            }
        } catch (Exception e) {
            Log.e(TAG, "Could not generate key pair", e);
        }
    }
}
