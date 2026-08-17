package ch.datatrans.payment;

import java.util.HashSet;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ub, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0234ub extends AbstractC0167k4 {
    public static final List b = CollectionsKt.listOf((Object[]) new String[]{"SSL", "SSLv3", "TLS", "TLSv1", "TLSv1.1"});

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0234ub(SSLSocket delegate) {
        super(delegate);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        String[] enabledProtocols = delegate.getEnabledProtocols();
        Intrinsics.checkNotNull(enabledProtocols);
        setEnabledProtocols(enabledProtocols);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledProtocols(String[] protocols) {
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        HashSet hashSet = new HashSet();
        for (String str : protocols) {
            if (!b.contains(str)) {
                hashSet.add(str);
            }
        }
        if (hashSet.isEmpty()) {
            hashSet.add("TLSv1.2");
        }
        String[] protocols2 = (String[]) hashSet.toArray(new String[0]);
        Intrinsics.checkNotNullParameter(protocols2, "protocols");
        this.a.setEnabledProtocols(protocols2);
    }
}
