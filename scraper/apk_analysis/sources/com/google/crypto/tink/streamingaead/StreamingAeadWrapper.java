package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public class StreamingAeadWrapper implements PrimitiveWrapper<StreamingAead> {
    StreamingAeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public StreamingAead wrap(PrimitiveSet<StreamingAead> primitiveSet) throws GeneralSecurityException {
        return new StreamingAeadHelper(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<StreamingAead> getPrimitiveClass() {
        return StreamingAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new StreamingAeadWrapper());
    }
}
