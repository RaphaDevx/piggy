package ch.datatrans.payment;

import android.os.Bundle;
import com.samsung.android.sdk.samsungpay.v2.StatusListener;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import kotlin.coroutines.SafeContinuation;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.la, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0175la implements StatusListener {
    public final /* synthetic */ C0193oa a;
    public final /* synthetic */ SafeContinuation b;

    public C0175la(C0193oa c0193oa, SafeContinuation safeContinuation) {
        this.a = c0193oa;
        this.b = safeContinuation;
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.StatusListener
    public final void onFail(int i, Bundle bundle) {
        this.a.a(this.b, bundle);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.StatusListener
    public final void onSuccess(int i, Bundle bundle) {
        if (i == 0) {
            this.a.a(this.b, bundle);
            return;
        }
        if (i == 1) {
            this.a.a(this.b, bundle);
            return;
        }
        if (i != 2) {
            this.a.a(this.b, bundle);
            return;
        }
        C0193oa c0193oa = this.a;
        SafeContinuation cont = this.b;
        Intrinsics.checkNotNullParameter(cont, "cont");
        new PaymentManager(c0193oa.a, c0193oa.b.createPartnerInfo$lib_release(c0193oa.c)).requestCardInfo(new Bundle(), new ha(c0193oa, cont));
    }
}
