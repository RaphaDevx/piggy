package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Enums;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class Validators {
    private static final int MIN_RSA_MODULUS_SIZE = 2048;
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/";
    private static final String URI_UNRESERVED_CHARS = "([0-9a-zA-Z\\-\\.\\_~])+";
    private static final Pattern GCP_KMS_CRYPTO_KEY_PATTERN = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s$", URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS), 2);
    private static final Pattern GCP_KMS_CRYPTO_KEY_VERSION_PATTERN = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s/cryptoKeyVersions/%s$", URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS), 2);

    public static void validateTypeUrl(String str) throws GeneralSecurityException {
        if (!str.startsWith(TYPE_URL_PREFIX)) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it must start with %s.\n", str, TYPE_URL_PREFIX));
        }
        if (str.length() == 20) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it has no message name.\n", str));
        }
    }

    public static void validateAesKeySize(int i) throws InvalidAlgorithmParameterException {
        if (i != 16 && i != 32) {
            throw new InvalidAlgorithmParameterException(String.format("invalid key size %d; only 128-bit and 256-bit AES keys are supported", Integer.valueOf(i * 8)));
        }
    }

    public static void validateVersion(int i, int i2) throws GeneralSecurityException {
        if (i < 0 || i > i2) {
            throw new GeneralSecurityException(String.format("key has version %d; only keys with version in range [0..%d] are supported", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    /* renamed from: com.google.crypto.tink.subtle.Validators$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void validateSignatureHash(Enums.HashType hashType) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[hashType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        throw new GeneralSecurityException("Unsupported hash: " + hashType.name());
    }

    public static void validateRsaModulusSize(int i) throws GeneralSecurityException {
        if (i < 2048) {
            throw new GeneralSecurityException(String.format("Modulus size is %d; only modulus size >= 2048-bit is supported", Integer.valueOf(i)));
        }
    }

    public static void validateNotExists(File file) throws IOException {
        if (file.exists()) {
            throw new IOException(String.format("%s exists, please choose another file\n", file.toString()));
        }
    }

    public static void validateExists(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException(String.format("Error: %s doesn't exist, please choose another file\n", file.toString()));
        }
    }

    public static String validateKmsKeyUriAndRemovePrefix(String str, String str2) throws IllegalArgumentException {
        if (!str2.toLowerCase().startsWith(str)) {
            throw new IllegalArgumentException(String.format("key URI must start with %s", str));
        }
        return str2.substring(str.length());
    }

    public static void validateCryptoKeyUri(String str) throws GeneralSecurityException {
        if (GCP_KMS_CRYPTO_KEY_PATTERN.matcher(str).matches()) {
            return;
        }
        if (GCP_KMS_CRYPTO_KEY_VERSION_PATTERN.matcher(str).matches()) {
            throw new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey, not a CryptoKeyVersion");
        }
        throw new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey in the format projects/*/locations/*/keyRings/*/cryptoKeys/*. See https://cloud.google.com/kms/docs/reference/rest/v1/projects.locations.keyRings.cryptoKeys#CryptoKey");
    }
}
