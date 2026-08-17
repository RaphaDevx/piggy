package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.de, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0130de {
    public final String a;
    public final String b;
    public final String c;

    public C0130de(String walletToken, String str, String str2) {
        Intrinsics.checkNotNullParameter(walletToken, "walletToken");
        this.a = walletToken;
        this.b = str;
        this.c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0130de)) {
            return false;
        }
        C0130de c0130de = (C0130de) obj;
        return Intrinsics.areEqual(this.a, c0130de.a) && Intrinsics.areEqual(this.b, c0130de.b) && Intrinsics.areEqual(this.c, c0130de.c);
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WalletAPITransactionInfo(walletToken=");
        sb.append(this.a);
        sb.append(", cardDetails=");
        sb.append(this.b);
        sb.append(", description=");
        return N7.a(sb, this.c, ')');
    }
}
