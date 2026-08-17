package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: classes2.dex */
public final class AeadFactory {
    @Deprecated
    public static Aead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static Aead getPrimitive(KeysetHandle keysetHandle, KeyManager<Aead> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new AeadWrapper());
        return (Aead) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, Aead.class));
    }
}
