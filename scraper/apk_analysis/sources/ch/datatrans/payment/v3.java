package ch.datatrans.payment;

import android.content.Context;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionOptions;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationOptions;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class v3 extends ViewModel {
    public final MutableLiveData a = new MutableLiveData();
    public final MutableLiveData b = new MutableLiveData();
    public final MutableLiveData c = new MutableLiveData();
    public final MutableLiveData d = new MutableLiveData();
    public final MutableLiveData e = new MutableLiveData(null);
    public final MutableLiveData f = new MutableLiveData(null);
    public final MutableLiveData g;
    public final MutableLiveData h;
    public final MediatorLiveData i;
    public F3 j;

    public v3() {
        Boolean bool = Boolean.FALSE;
        this.g = new MutableLiveData(bool);
        this.h = new MutableLiveData(bool);
        this.i = new MediatorLiveData();
    }

    public static final Unit b(v3 v3Var, Context context, Boolean bool) {
        boolean z;
        MediatorLiveData mediatorLiveData = v3Var.i;
        if (!bool.booleanValue() && context.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            F3 f3 = v3Var.j;
            if (f3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("creditCardScannerHelper");
                f3 = null;
            }
            if (Intrinsics.areEqual(f3.g.getValue(), Boolean.TRUE)) {
                z = true;
                mediatorLiveData.setValue(Boolean.valueOf(z));
                return Unit.INSTANCE;
            }
        }
        z = false;
        mediatorLiveData.setValue(Boolean.valueOf(z));
        return Unit.INSTANCE;
    }

    public final void a(r3 listener, final Context context, C3 cardNumberModel) {
        PCIPTokenization tokenization;
        PCIPTokenizationOptions pCIPTokenizationOptions;
        boolean isTesting;
        TransactionOptions transactionOptions;
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cardNumberModel, "cardNumberModel");
        MutableLiveData mutableLiveData = this.h;
        Boolean bool = (Boolean) cardNumberModel.e.getValue();
        bool.getClass();
        mutableLiveData.postValue(bool);
        this.g.postValue(Boolean.valueOf(cardNumberModel.d));
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        if (transactionRegistry.getTransaction() != null) {
            Transaction transaction = transactionRegistry.getTransaction();
            if (transaction != null && (transactionOptions = transaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String()) != null) {
                isTesting = transactionOptions.getIsTesting();
            }
            isTesting = false;
        } else {
            PCIPTokenizationRegistry pCIPTokenizationRegistry = PCIPTokenizationRegistry.INSTANCE;
            if (pCIPTokenizationRegistry.getTokenization() != null && (tokenization = pCIPTokenizationRegistry.getTokenization()) != null && (pCIPTokenizationOptions = tokenization.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String()) != null) {
                isTesting = pCIPTokenizationOptions.getIsTesting();
            }
            isTesting = false;
        }
        F3 f3 = new F3(ViewModelKt.getViewModelScope(this), listener, isTesting);
        this.j = f3;
        this.i.addSource(f3.g, new C0213s3(new Function1() { // from class: ch.datatrans.payment.v3$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return v3.a(v3.this, context, (Boolean) obj);
            }
        }));
        this.i.addSource(this.g, new C0213s3(new Function1() { // from class: ch.datatrans.payment.v3$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return v3.b(v3.this, context, (Boolean) obj);
            }
        }));
    }

    public static final Unit a(v3 v3Var, Context context, Boolean bool) {
        v3Var.i.setValue(Boolean.valueOf(bool.booleanValue() && context.getPackageManager().hasSystemFeature("android.hardware.camera") && Intrinsics.areEqual(v3Var.g.getValue(), Boolean.FALSE)));
        return Unit.INSTANCE;
    }
}
