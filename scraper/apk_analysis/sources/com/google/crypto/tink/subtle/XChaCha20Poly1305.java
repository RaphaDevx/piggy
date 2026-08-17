package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* loaded from: classes2.dex */
public final class XChaCha20Poly1305 extends ChaCha20Poly1305Base {
    @Override // com.google.crypto.tink.subtle.ChaCha20Poly1305Base, com.google.crypto.tink.Aead
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.decrypt(bArr, bArr2);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Poly1305Base, com.google.crypto.tink.Aead
    public /* bridge */ /* synthetic */ byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.encrypt(bArr, bArr2);
    }

    public XChaCha20Poly1305(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Poly1305Base
    ChaCha20Base newChaCha20Instance(byte[] bArr, int i) throws InvalidKeyException {
        return new XChaCha20(bArr, i);
    }
}
