package ch.datatrans.payment;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.InitializationException;
import ch.datatrans.payment.exception.ModuleMissingException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final class N5 extends AndroidViewModel {
    public static final /* synthetic */ int g = 0;
    public final Nb a;
    public final MutableLiveData b;
    public final Xa c;
    public final Xa d;
    public final Xa e;
    public final Xa f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N5(Application application, Nb transactionModel) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.a = transactionModel;
        this.b = new MutableLiveData();
        this.c = new Xa();
        this.d = new Xa();
        this.e = new Xa();
        this.f = new Xa();
    }

    /* JADX WARN: Code restructure failed: missing block: B:164:0x0082, code lost:
    
        if (r1 == r3) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0170 -> B:11:0x0171). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.N5 r33, kotlin.coroutines.jvm.internal.ContinuationImpl r34) {
        /*
            Method dump skipped, instructions count: 1618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.N5.a(ch.datatrans.payment.N5, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static final Unit b(N5 n5) {
        n5.b.postValue(null);
        Xa xa = n5.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void c() {
        MutableLiveData mutableLiveData = this.b;
        C7 c7 = r4.a;
        J5 retryAction = new J5(this);
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.N5$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return N5.b(N5.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, retryAction, cancelAction));
    }

    public final void b() {
        MutableLiveData mutableLiveData = this.b;
        C7 c7 = r4.a;
        H5 retryAction = new H5(this);
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.N5$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return N5.a(N5.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public final void a() {
        this.f.postValue(Boolean.valueOf(this.a.h.y == null));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new L5(this, null), 3, null);
    }

    public static final Unit a(N5 n5) {
        n5.b.postValue(null);
        Xa xa = n5.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void a(final ModuleMissingException moduleMissingException) {
        MutableLiveData mutableLiveData = this.b;
        C7 c7 = r4.a;
        PaymentMethodType paymentMethodType = moduleMissingException.getPaymentMethodType();
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.N5$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return N5.a(N5.this, moduleMissingException);
            }
        };
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, new u4(paymentMethodType), dismissAction));
    }

    public static final Unit a(N5 n5, ModuleMissingException moduleMissingException) {
        n5.b.postValue(null);
        n5.c.postValue(moduleMissingException);
        return Unit.INSTANCE;
    }

    public final void a(final Exception exc) {
        MutableLiveData mutableLiveData = this.b;
        C7 c7 = r4.a;
        mutableLiveData.postValue(v4.a(new Function0() { // from class: ch.datatrans.payment.N5$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return N5.a(N5.this, exc);
            }
        }));
    }

    public static final Unit a(N5 n5, Exception exc) {
        n5.b.postValue(null);
        n5.c.postValue(exc instanceof InitializationException ? (TransactionException) exc : new TechnicalException(exc.getMessage(), exc, null, null, 12, null));
        return Unit.INSTANCE;
    }
}
