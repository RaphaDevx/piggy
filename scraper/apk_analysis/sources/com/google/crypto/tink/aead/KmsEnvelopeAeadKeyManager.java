package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKey;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public class KmsEnvelopeAeadKeyManager extends KeyTypeManager<KmsEnvelopeAeadKey> {
    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    KmsEnvelopeAeadKeyManager() {
        super(KmsEnvelopeAeadKey.class, new KeyTypeManager.PrimitiveFactory<Aead, KmsEnvelopeAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Aead getPrimitive(KmsEnvelopeAeadKey kmsEnvelopeAeadKey) throws GeneralSecurityException {
                String kekUri = kmsEnvelopeAeadKey.getParams().getKekUri();
                return new KmsEnvelopeAead(kmsEnvelopeAeadKey.getParams().getDekTemplate(), KmsClients.get(kekUri).getAead(kekUri));
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(KmsEnvelopeAeadKey kmsEnvelopeAeadKey) throws GeneralSecurityException {
        Validators.validateVersion(kmsEnvelopeAeadKey.getVersion(), getVersion());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public KmsEnvelopeAeadKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsEnvelopeAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, KmsEnvelopeAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey>(KmsEnvelopeAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) throws GeneralSecurityException {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsEnvelopeAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKey createKey(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) throws GeneralSecurityException {
                return KmsEnvelopeAeadKey.newBuilder().setParams(kmsEnvelopeAeadKeyFormat).setVersion(KmsEnvelopeAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsEnvelopeAeadKeyManager(), z);
    }
}
