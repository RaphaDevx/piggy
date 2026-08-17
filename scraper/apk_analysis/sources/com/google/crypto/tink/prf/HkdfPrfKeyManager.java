package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKey;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public class HkdfPrfKeyManager extends KeyTypeManager<HkdfPrfKey> {
    private static final int MIN_KEY_SIZE = 32;

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* renamed from: com.google.crypto.tink.prf.HkdfPrfKeyManager$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Enums.HashType convertHash(HashType hashType) throws GeneralSecurityException {
        int i = AnonymousClass4.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i == 1) {
            return Enums.HashType.SHA1;
        }
        if (i == 2) {
            return Enums.HashType.SHA256;
        }
        if (i == 3) {
            return Enums.HashType.SHA384;
        }
        if (i == 4) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("HashType " + hashType.name() + " not known in");
    }

    HkdfPrfKeyManager() {
        super(HkdfPrfKey.class, new KeyTypeManager.PrimitiveFactory<StreamingPrf, HkdfPrfKey>(StreamingPrf.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public StreamingPrf getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
                return new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray());
            }
        }, new KeyTypeManager.PrimitiveFactory<PrfSet, HkdfPrfKey>(PrfSet.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public PrfSet getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
                final PrfImpl wrap = PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray()));
                return new PrfSet() { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.2.1
                    @Override // com.google.crypto.tink.prf.PrfSet
                    public int getPrimaryId() {
                        return 0;
                    }

                    @Override // com.google.crypto.tink.prf.PrfSet
                    public Map<Integer, Prf> getPrfs() {
                        return Collections.singletonMap(0, wrap);
                    }
                };
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(hkdfPrfKey.getVersion(), getVersion());
        validateKeySize(hkdfPrfKey.getKeyValue().size());
        validateParams(hkdfPrfKey.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public HkdfPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HkdfPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, HkdfPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, HkdfPrfKey>(HkdfPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.3
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
                HkdfPrfKeyManager.validateKeySize(hkdfPrfKeyFormat.getKeySize());
                HkdfPrfKeyManager.validateParams(hkdfPrfKeyFormat.getParams());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HkdfPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public HkdfPrfKey createKey(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
                return HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(hkdfPrfKeyFormat.getKeySize()))).setVersion(HkdfPrfKeyManager.this.getVersion()).setParams(hkdfPrfKeyFormat.getParams()).build();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateKeySize(int i) throws GeneralSecurityException {
        if (i < 32) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HkdfPrfParams hkdfPrfParams) throws GeneralSecurityException {
        if (hkdfPrfParams.getHash() != HashType.SHA256 && hkdfPrfParams.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), z);
    }

    public static String staticKeyType() {
        return new HkdfPrfKeyManager().getKeyType();
    }

    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create(staticKeyType(), HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
