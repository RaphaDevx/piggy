package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager<AesGcmHkdfStreamingKey> {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int TAG_SIZE_IN_BYTES = 16;

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    AesGcmHkdfStreamingKeyManager() {
        super(AesGcmHkdfStreamingKey.class, new KeyTypeManager.PrimitiveFactory<StreamingAead, AesGcmHkdfStreamingKey>(StreamingAead.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public StreamingAead getPrimitive(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
                return new AesGcmHkdfStreaming(aesGcmHkdfStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesGcmHkdfStreamingKey.getParams().getHkdfHashType()), aesGcmHkdfStreamingKey.getParams().getDerivedKeySize(), aesGcmHkdfStreamingKey.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmHkdfStreamingKey.getVersion(), getVersion());
        validateParams(aesGcmHkdfStreamingKey.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesGcmHkdfStreamingKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmHkdfStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesGcmHkdfStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey>(AesGcmHkdfStreamingKeyFormat.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
                if (aesGcmHkdfStreamingKeyFormat.getKeySize() >= 16) {
                    AesGcmHkdfStreamingKeyManager.validateParams(aesGcmHkdfStreamingKeyFormat.getParams());
                    return;
                }
                throw new GeneralSecurityException("key_size must be at least 16 bytes");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
                return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmHkdfStreamingKeyFormat.getKeySize()))).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmHkdfStreamingKeyFormat.getVersion(), AesGcmHkdfStreamingKeyManager.this.getVersion());
                byte[] bArr = new byte[aesGcmHkdfStreamingKeyFormat.getKeySize()];
                try {
                    if (inputStream.read(bArr) != aesGcmHkdfStreamingKeyFormat.getKeySize()) {
                        throw new GeneralSecurityException("Not enough pseudorandomness given");
                    }
                    return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesGcmHkdfStreamingParams.getDerivedKeySize());
        if (aesGcmHkdfStreamingParams.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        }
        if (aesGcmHkdfStreamingParams.getCiphertextSegmentSize() < aesGcmHkdfStreamingParams.getDerivedKeySize() + 25) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
        }
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), z);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 4096);
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 1048576);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 4096);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 1048576);
    }

    private static KeyTemplate createKeyTemplate(int i, HashType hashType, int i2, int i3) {
        return KeyTemplate.create(new AesGcmHkdfStreamingKeyManager().getKeyType(), AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(i).setParams(AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(i3).setDerivedKeySize(i2).setHkdfHashType(hashType).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
