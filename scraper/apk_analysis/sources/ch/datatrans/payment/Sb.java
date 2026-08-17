package ch.datatrans.payment;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import ch.datatrans.payment.api.TransactionListener;
import ch.datatrans.payment.api.TransactionSuccess;
import ch.datatrans.payment.exception.TransactionException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class Sb {
    public static boolean d;
    public static final MutableLiveData a = new MutableLiveData();
    public static final MutableLiveData b = new MutableLiveData();
    public static final MutableLiveData c = new MutableLiveData(Boolean.FALSE);
    public static String e = "";

    public static void a(LifecycleOwner owner, final TransactionListener listener) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(listener, "listener");
        d = true;
        Transformations.distinctUntilChanged(a).observe(owner, new Ob(new Function1() { // from class: ch.datatrans.payment.Sb$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sb.a(TransactionListener.this, (TransactionSuccess) obj);
            }
        }));
        Transformations.distinctUntilChanged(b).observe(owner, new Ob(new Function1() { // from class: ch.datatrans.payment.Sb$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sb.a(TransactionListener.this, (TransactionException) obj);
            }
        }));
        Transformations.distinctUntilChanged(c).observe(owner, new Ob(new Function1() { // from class: ch.datatrans.payment.Sb$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sb.a(TransactionListener.this, ((Boolean) obj).booleanValue());
            }
        }));
    }

    public static final Unit a(TransactionListener transactionListener, TransactionSuccess transactionSuccess) {
        if (transactionSuccess != null) {
            transactionListener.onTransactionSuccess(transactionSuccess);
            a();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionListener transactionListener, TransactionException transactionException) {
        if (transactionException != null) {
            transactionListener.onTransactionError(transactionException);
            a();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionListener transactionListener, boolean z) {
        if (z) {
            transactionListener.onTransactionCancel(e);
            a();
        }
        return Unit.INSTANCE;
    }

    public static void a() {
        a.postValue(null);
        b.postValue(null);
        c.postValue(Boolean.FALSE);
        e = "";
    }
}
