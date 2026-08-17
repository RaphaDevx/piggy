package lib.android.paypal.com.magnessdk.network.base;

import lib.android.paypal.com.magnessdk.g;

/* loaded from: classes4.dex */
public final class MagnesNetworkingFactoryImpl implements MagnesNetworkingFactory {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.h.b.values().length];
            a = iArr;
            try {
                iArr[g.h.b.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.h.b.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworkingFactory
    public MagnesNetworking createHttpClient(g.h.b bVar) throws Exception {
        int i = a.a[bVar.ordinal()];
        return i != 1 ? i != 2 ? new b() : new b() : new lib.android.paypal.com.magnessdk.network.base.a();
    }
}
