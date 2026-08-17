package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class AeadWrapper implements PrimitiveWrapper<Aead> {
    private static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());

    private static class WrappedAead implements Aead {
        private final PrimitiveSet<Aead> pSet;

        private WrappedAead(PrimitiveSet<Aead> primitiveSet) {
            this.pSet = primitiveSet;
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return Bytes.concat(this.pSet.getPrimary().getIdentifier(), this.pSet.getPrimary().getPrimitive().encrypt(bArr, bArr2));
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                Iterator<PrimitiveSet.Entry<Aead>> it = this.pSet.getPrimitive(copyOfRange).iterator();
                while (it.hasNext()) {
                    try {
                        return it.next().getPrimitive().decrypt(copyOfRange2, bArr2);
                    } catch (GeneralSecurityException e) {
                        AeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            Iterator<PrimitiveSet.Entry<Aead>> it2 = this.pSet.getRawPrimitives().iterator();
            while (it2.hasNext()) {
                try {
                    return it2.next().getPrimitive().decrypt(bArr, bArr2);
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }
    }

    AeadWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Aead wrap(PrimitiveSet<Aead> primitiveSet) throws GeneralSecurityException {
        return new WrappedAead(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new AeadWrapper());
    }
}
