package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class MacWrapper implements PrimitiveWrapper<Mac> {
    private static final Logger logger = Logger.getLogger(MacWrapper.class.getName());

    private static class WrappedMac implements Mac {
        private final byte[] formatVersion;
        private final PrimitiveSet<Mac> primitives;

        private WrappedMac(PrimitiveSet<Mac> primitiveSet) {
            this.formatVersion = new byte[]{0};
            this.primitives = primitiveSet;
        }

        @Override // com.google.crypto.tink.Mac
        public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                return Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(Bytes.concat(bArr, this.formatVersion)));
            }
            return Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(bArr));
        }

        @Override // com.google.crypto.tink.Mac
        public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length <= 5) {
                throw new GeneralSecurityException("tag too short");
            }
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (PrimitiveSet.Entry<Mac> entry : this.primitives.getPrimitive(copyOfRange)) {
                try {
                    if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                        entry.getPrimitive().verifyMac(copyOfRange2, Bytes.concat(bArr2, this.formatVersion));
                        return;
                    } else {
                        entry.getPrimitive().verifyMac(copyOfRange2, bArr2);
                        return;
                    }
                } catch (GeneralSecurityException e) {
                    MacWrapper.logger.info("tag prefix matches a key, but cannot verify: " + e.toString());
                }
            }
            Iterator<PrimitiveSet.Entry<Mac>> it = this.primitives.getRawPrimitives().iterator();
            while (it.hasNext()) {
                try {
                    it.next().getPrimitive().verifyMac(bArr, bArr2);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    MacWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Mac wrap(PrimitiveSet<Mac> primitiveSet) throws GeneralSecurityException {
        return new WrappedMac(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new MacWrapper());
    }
}
