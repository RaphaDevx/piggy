package com.salesforce.marketingcloud.util;

import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptionManager;

/* loaded from: classes2.dex */
public final class h implements Crypto {
    private final EncryptionManager a;

    public h(EncryptionManager encryptionManager) {
        this.a = encryptionManager;
    }

    @Override // com.salesforce.marketingcloud.util.Crypto
    public String decString(String str) {
        EncryptionManager encryptionManager;
        if (str == null || (encryptionManager = this.a) == null) {
            return null;
        }
        return encryptionManager.decrypt(str);
    }

    @Override // com.salesforce.marketingcloud.util.Crypto
    public String encString(String str) {
        EncryptionManager encryptionManager;
        if (str == null || (encryptionManager = this.a) == null) {
            return null;
        }
        return encryptionManager.encrypt(str);
    }
}
