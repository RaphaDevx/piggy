package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class PublicKeyVerifyWrapper implements PrimitiveWrapper<PublicKeyVerify> {
    private static final Logger logger = Logger.getLogger(PublicKeyVerifyWrapper.class.getName());

    PublicKeyVerifyWrapper() {
    }

    private static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final PrimitiveSet<PublicKeyVerify> primitives;

        public WrappedPublicKeyVerify(PrimitiveSet<PublicKeyVerify> primitiveSet) {
            this.primitives = primitiveSet;
        }

        @Override // com.google.crypto.tink.PublicKeyVerify
        public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length <= 5) {
                throw new GeneralSecurityException("signature too short");
            }
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (PrimitiveSet.Entry<PublicKeyVerify> entry : this.primitives.getPrimitive(copyOfRange)) {
                try {
                    if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                        entry.getPrimitive().verify(copyOfRange2, Bytes.concat(bArr2, new byte[]{0}));
                        return;
                    } else {
                        entry.getPrimitive().verify(copyOfRange2, bArr2);
                        return;
                    }
                } catch (GeneralSecurityException e) {
                    PublicKeyVerifyWrapper.logger.info("signature prefix matches a key, but cannot verify: " + e.toString());
                }
            }
            Iterator<PrimitiveSet.Entry<PublicKeyVerify>> it = this.primitives.getRawPrimitives().iterator();
            while (it.hasNext()) {
                try {
                    it.next().getPrimitive().verify(bArr, bArr2);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("invalid signature");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeyVerify wrap(PrimitiveSet<PublicKeyVerify> primitiveSet) {
        return new WrappedPublicKeyVerify(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PublicKeyVerifyWrapper());
    }
}
