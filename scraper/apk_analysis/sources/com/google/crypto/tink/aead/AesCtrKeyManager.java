package com.google.crypto.tink.aead;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesCtrJceCipher;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public class AesCtrKeyManager extends KeyTypeManager<AesCtrKey> {
    private static final int MIN_IV_SIZE_IN_BYTES = 12;

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    AesCtrKeyManager() {
        super(AesCtrKey.class, new KeyTypeManager.PrimitiveFactory<IndCpaCipher, AesCtrKey>(IndCpaCipher.class) { // from class: com.google.crypto.tink.aead.AesCtrKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public IndCpaCipher getPrimitive(AesCtrKey aesCtrKey) throws GeneralSecurityException {
                return new AesCtrJceCipher(aesCtrKey.getKeyValue().toByteArray(), aesCtrKey.getParams().getIvSize());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesCtrKey aesCtrKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrKey.getVersion(), getVersion());
        Validators.validateAesKeySize(aesCtrKey.getKeyValue().size());
        validateParams(aesCtrKey.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesCtrKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCtrKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesCtrKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrKeyFormat, AesCtrKey>(AesCtrKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesCtrKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesCtrKeyFormat.getKeySize());
                AesCtrKeyManager.this.validateParams(aesCtrKeyFormat.getParams());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCtrKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCtrKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCtrKey createKey(AesCtrKeyFormat aesCtrKeyFormat) throws GeneralSecurityException {
                return AesCtrKey.newBuilder().setParams(aesCtrKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrKeyFormat.getKeySize()))).setVersion(AesCtrKeyManager.this.getVersion()).build();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateParams(AesCtrParams aesCtrParams) throws GeneralSecurityException {
        if (aesCtrParams.getIvSize() < 12 || aesCtrParams.getIvSize() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrKeyManager(), z);
    }
}
