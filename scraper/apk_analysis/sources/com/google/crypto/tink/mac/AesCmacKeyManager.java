package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesCmacKey;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesCmac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public final class AesCmacKeyManager extends KeyTypeManager<AesCmacKey> {
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int MAX_TAG_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int VERSION = 0;

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    AesCmacKeyManager() {
        super(AesCmacKey.class, new KeyTypeManager.PrimitiveFactory<Mac, AesCmacKey>(Mac.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Mac getPrimitive(AesCmacKey aesCmacKey) throws GeneralSecurityException {
                return new AesCmac(aesCmacKey.getKeyValue().toByteArray(), aesCmacKey.getParams().getTagSize());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesCmacKey aesCmacKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacKey.getVersion(), getVersion());
        validateSize(aesCmacKey.getKeyValue().size());
        validateParams(aesCmacKey.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesCmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesCmacParams aesCmacParams) throws GeneralSecurityException {
        if (aesCmacParams.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (aesCmacParams.getTagSize() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateSize(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 16 bytes");
        }
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesCmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacKeyFormat, AesCmacKey>(AesCmacKeyFormat.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCmacKeyFormat aesCmacKeyFormat) throws GeneralSecurityException {
                AesCmacKeyManager.validateParams(aesCmacKeyFormat.getParams());
                AesCmacKeyManager.validateSize(aesCmacKeyFormat.getKeySize());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCmacKey createKey(AesCmacKeyFormat aesCmacKeyFormat) throws GeneralSecurityException {
                return AesCmacKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacKeyFormat.getKeySize()))).setParams(aesCmacKeyFormat.getParams()).build();
            }
        };
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacKeyManager(), z);
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
