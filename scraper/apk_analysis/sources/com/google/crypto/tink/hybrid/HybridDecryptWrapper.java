package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class HybridDecryptWrapper implements PrimitiveWrapper<HybridDecrypt> {
    private static final Logger logger = Logger.getLogger(HybridDecryptWrapper.class.getName());

    private static class WrappedHybridDecrypt implements HybridDecrypt {
        private final PrimitiveSet<HybridDecrypt> primitives;

        public WrappedHybridDecrypt(PrimitiveSet<HybridDecrypt> primitiveSet) {
            this.primitives = primitiveSet;
        }

        @Override // com.google.crypto.tink.HybridDecrypt
        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                Iterator<PrimitiveSet.Entry<HybridDecrypt>> it = this.primitives.getPrimitive(copyOfRange).iterator();
                while (it.hasNext()) {
                    try {
                        return it.next().getPrimitive().decrypt(copyOfRange2, bArr2);
                    } catch (GeneralSecurityException e) {
                        HybridDecryptWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            Iterator<PrimitiveSet.Entry<HybridDecrypt>> it2 = this.primitives.getRawPrimitives().iterator();
            while (it2.hasNext()) {
                try {
                    return it2.next().getPrimitive().decrypt(bArr, bArr2);
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }
    }

    HybridDecryptWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridDecrypt wrap(PrimitiveSet<HybridDecrypt> primitiveSet) {
        return new WrappedHybridDecrypt(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridDecrypt> getPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new HybridDecryptWrapper());
    }
}
