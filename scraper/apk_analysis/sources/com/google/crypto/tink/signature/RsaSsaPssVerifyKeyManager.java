package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes2.dex */
class RsaSsaPssVerifyKeyManager extends KeyTypeManager<RsaSsaPssPublicKey> {
    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    public RsaSsaPssVerifyKeyManager() {
        super(RsaSsaPssPublicKey.class, new KeyTypeManager.PrimitiveFactory<PublicKeyVerify, RsaSsaPssPublicKey>(PublicKeyVerify.class) { // from class: com.google.crypto.tink.signature.RsaSsaPssVerifyKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public PublicKeyVerify getPrimitive(RsaSsaPssPublicKey rsaSsaPssPublicKey) throws GeneralSecurityException {
                RSAPublicKey rSAPublicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPublicKey.getN().toByteArray()), new BigInteger(1, rsaSsaPssPublicKey.getE().toByteArray())));
                RsaSsaPssParams params = rsaSsaPssPublicKey.getParams();
                return new RsaSsaPssVerifyJce(rSAPublicKey, SigUtil.toHashType(params.getSigHash()), SigUtil.toHashType(params.getMgf1Hash()), params.getSaltLength());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.KeyTypeManager
    public RsaSsaPssPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return RsaSsaPssPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(RsaSsaPssPublicKey rsaSsaPssPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPublicKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPublicKey.getN().toByteArray()).bitLength());
        SigUtil.validateRsaSsaPssParams(rsaSsaPssPublicKey.getParams());
    }
}
