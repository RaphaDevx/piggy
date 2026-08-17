package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentViewCallback;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentsSDKError;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class S5 implements KlarnaPaymentViewCallback {
    public final /* synthetic */ C0159i6 a;

    public S5(C0159i6 c0159i6) {
        this.a = c0159i6;
    }

    public final void onAuthorized(KlarnaPaymentView view, boolean z, String authToken, Boolean bool) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (!z) {
            ((C0221t6) this.a.c.getValue()).j.postValue(Boolean.TRUE);
            return;
        }
        C0221t6 c0221t6 = (C0221t6) this.a.c.getValue();
        Intrinsics.checkNotNull(authToken);
        c0221t6.getClass();
        Intrinsics.checkNotNullParameter(authToken, "authToken");
        c0221t6.e().p = authToken;
        c0221t6.b.postValue(Unit.INSTANCE);
    }

    public final void onErrorOccurred(KlarnaPaymentView view, KlarnaPaymentsSDKError error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(error, "error");
        C0221t6 c0221t6 = (C0221t6) this.a.c.getValue();
        c0221t6.getClass();
        Intrinsics.checkNotNullParameter(error, "error");
        c0221t6.j.postValue(Boolean.TRUE);
        c0221t6.a(error);
    }

    public final void onFinalized(KlarnaPaymentView view, boolean z, String str) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void onInitialized(KlarnaPaymentView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.load((String) null);
    }

    public final void onLoadPaymentReview(KlarnaPaymentView view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void onLoaded(KlarnaPaymentView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        C0159i6 c0159i6 = this.a;
        int i = C0159i6.g;
        if (!c0159i6.f) {
            c0159i6.f = true;
            ((Pa) c0159i6.a.getValue()).c.postValue(Boolean.TRUE);
        }
        ((Pa) this.a.a.getValue()).b.postValue(Boolean.FALSE);
        C0221t6 c0221t6 = (C0221t6) this.a.c.getValue();
        MutableLiveData mutableLiveData = c0221t6.i;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.postValue(bool);
        c0221t6.j.postValue(bool);
    }

    public final void onReauthorized(KlarnaPaymentView view, boolean z, String str) {
        Intrinsics.checkNotNullParameter(view, "view");
    }
}
