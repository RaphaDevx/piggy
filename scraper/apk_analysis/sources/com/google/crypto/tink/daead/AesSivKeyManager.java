package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesSiv;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/* loaded from: classes2.dex */
public final class AesSivKeyManager extends KeyTypeManager<AesSivKey> {
    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    AesSivKeyManager() {
        super(AesSivKey.class, new KeyTypeManager.PrimitiveFactory<DeterministicAead, AesSivKey>(DeterministicAead.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public DeterministicAead getPrimitive(AesSivKey aesSivKey) throws GeneralSecurityException {
                return new AesSiv(aesSivKey.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesSivKey aesSivKey) throws GeneralSecurityException {
        Validators.validateVersion(aesSivKey.getVersion(), getVersion());
        if (aesSivKey.getKeyValue().size() == 64) {
            return;
        }
        throw new InvalidKeyException("invalid key size: " + aesSivKey.getKeyValue().size() + ". Valid keys must have 64 bytes.");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public AesSivKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesSivKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesSivKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey>(AesSivKeyFormat.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesSivKeyFormat aesSivKeyFormat) throws GeneralSecurityException {
                if (aesSivKeyFormat.getKeySize() == 64) {
                    return;
                }
                throw new InvalidAlgorithmParameterException("invalid key size: " + aesSivKeyFormat.getKeySize() + ". Valid keys must have 64 bytes.");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesSivKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesSivKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesSivKey createKey(AesSivKeyFormat aesSivKeyFormat) throws GeneralSecurityException {
                return AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesSivKeyFormat.getKeySize()))).setVersion(AesSivKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesSivKeyManager(), z);
    }

    public static final KeyTemplate aes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.RAW);
    }

    private static KeyTemplate createKeyTemplate(int i, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesSivKeyManager().getKeyType(), AesSivKeyFormat.newBuilder().setKeySize(i).build().toByteArray(), outputPrefixType);
    }
}
