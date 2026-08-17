package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes2.dex */
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final String signatureAlgorithm;

    public RsaSsaPkcs1SignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
        this.privateKey = rSAPrivateCrtKey;
        this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(hashType);
        this.publicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        engineFactory.initSign(this.privateKey);
        engineFactory.update(bArr);
        byte[] sign = engineFactory.sign();
        Signature engineFactory2 = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        engineFactory2.initVerify(this.publicKey);
        engineFactory2.update(bArr);
        if (engineFactory2.verify(sign)) {
            return sign;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }
}
