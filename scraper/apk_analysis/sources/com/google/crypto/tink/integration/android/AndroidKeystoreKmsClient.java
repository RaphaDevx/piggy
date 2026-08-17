package com.google.crypto.tink.integration.android;

import android.security.keystore.KeyGenParameterSpec;
import com.google.android.gms.stats.CodePackage;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import javax.crypto.KeyGenerator;

/* loaded from: classes2.dex */
public final class AndroidKeystoreKmsClient implements KmsClient {
    public static final String PREFIX = "android-keystore://";
    private String keyUri;

    private boolean isAtLeastM() {
        return true;
    }

    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        if (!isAtLeastM()) {
            throw new GeneralSecurityException("needs Android Keystore on Android M or newer");
        }
    }

    public AndroidKeystoreKmsClient(String str) {
        if (!str.toLowerCase().startsWith(PREFIX)) {
            throw new IllegalArgumentException("key URI must starts with android-keystore://");
        }
        this.keyUri = str;
    }

    @Override // com.google.crypto.tink.KmsClient
    public boolean doesSupport(String str) {
        String str2 = this.keyUri;
        if (str2 == null || !str2.equals(str)) {
            return this.keyUri == null && str.toLowerCase().startsWith(PREFIX);
        }
        return true;
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String str) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public Aead getAead(String str) throws GeneralSecurityException {
        String str2 = this.keyUri;
        if (str2 != null && !str2.equals(str)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, str));
        }
        try {
            return new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str));
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static Aead getOrGenerateNewAeadKey(String str) throws GeneralSecurityException, IOException {
        String validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (!keyStore.containsAlias(validateKmsKeyUriAndRemovePrefix)) {
            generateNewAeadKey(str);
        }
        return new AndroidKeystoreKmsClient().getAead(str);
    }

    public static void generateNewAeadKey(String str) throws GeneralSecurityException {
        String validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(validateKmsKeyUriAndRemovePrefix, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    static void delete(String str) throws GeneralSecurityException, IOException {
        String validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.deleteEntry(validateKmsKeyUriAndRemovePrefix);
    }
}
