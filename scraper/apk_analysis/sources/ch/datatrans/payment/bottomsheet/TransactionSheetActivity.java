package ch.datatrans.payment.bottomsheet;

import android.R;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.A6;
import ch.datatrans.payment.AbstractActivityC0147g4;
import ch.datatrans.payment.AbstractC0144g0;
import ch.datatrans.payment.Ac;
import ch.datatrans.payment.Ae;
import ch.datatrans.payment.B4;
import ch.datatrans.payment.Ba;
import ch.datatrans.payment.Be;
import ch.datatrans.payment.C0118c7;
import ch.datatrans.payment.C0126cd;
import ch.datatrans.payment.C0159i6;
import ch.datatrans.payment.C0182n;
import ch.datatrans.payment.C0224td;
import ch.datatrans.payment.C0245w2;
import ch.datatrans.payment.Cd;
import ch.datatrans.payment.Dd;
import ch.datatrans.payment.Ed;
import ch.datatrans.payment.ExternalProcessRelayActivity;
import ch.datatrans.payment.F;
import ch.datatrans.payment.Fd;
import ch.datatrans.payment.Gd;
import ch.datatrans.payment.Jc;
import ch.datatrans.payment.L8;
import ch.datatrans.payment.N5;
import ch.datatrans.payment.Na;
import ch.datatrans.payment.Nb;
import ch.datatrans.payment.O5;
import ch.datatrans.payment.Pa;
import ch.datatrans.payment.R9;
import ch.datatrans.payment.Sb;
import ch.datatrans.payment.Td;
import ch.datatrans.payment.Ub;
import ch.datatrans.payment.Vb;
import ch.datatrans.payment.W4;
import ch.datatrans.payment.Wb;
import ch.datatrans.payment.Xa;
import ch.datatrans.payment.Y9;
import ch.datatrans.payment.api.InitialLoader;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.api.TransactionSuccess;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.gg;
import ch.datatrans.payment.mb;
import ch.datatrans.payment.p9;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.pd;
import ch.datatrans.payment.qg;
import ch.datatrans.payment.t4;
import ch.datatrans.payment.z6;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/bottomsheet/TransactionSheetActivity;", "Lch/datatrans/payment/g4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TransactionSheetActivity extends AbstractActivityC0147g4 {
    public static final /* synthetic */ int t = 0;
    public final ViewModelLazy o;
    public final ViewModelLazy p;
    public final ViewModelLazy r;
    public final ViewModelLazy s;
    public A6 u;

    public TransactionSheetActivity() {
        Function0 a = Gd.a(new Fd(this), C0182n.class);
        this.o = new ViewModelLazy(Reflection.getOrCreateKotlinClass(C0182n.class), new Dd(this), a == null ? new Cd(this) : a, new Ed(this));
        Function0 a2 = Gd.a(new Fd(this), C0224td.class);
        this.p = new ViewModelLazy(Reflection.getOrCreateKotlinClass(C0224td.class), new Dd(this), a2 == null ? new Cd(this) : a2, new Ed(this));
        this.r = new ViewModelLazy(Reflection.getOrCreateKotlinClass(N5.class), new Vb(this), new Function0() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TransactionSheetActivity.a(TransactionSheetActivity.this);
            }
        }, new Wb(this));
        Function0 a3 = Gd.a(new Fd(this), Jc.class);
        this.s = new ViewModelLazy(Reflection.getOrCreateKotlinClass(Jc.class), new Dd(this), a3 == null ? new Cd(this) : a3, new Ed(this));
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, Boolean bool) {
        Pa pa = (Pa) transactionSheetActivity.n.getValue();
        Intrinsics.checkNotNull(bool);
        bool.getClass();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) transactionSheetActivity.n.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) transactionSheetActivity.n.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit c(TransactionSheetActivity transactionSheetActivity, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) transactionSheetActivity.n.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) transactionSheetActivity.n.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit d(TransactionSheetActivity transactionSheetActivity, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) transactionSheetActivity.n.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) transactionSheetActivity.n.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit f(TransactionSheetActivity transactionSheetActivity, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transactionSheetActivity.d();
        ((F) transactionSheetActivity.l.getValue()).a.postValue(Boolean.TRUE);
        return Unit.INSTANCE;
    }

    public static final Unit g(TransactionSheetActivity transactionSheetActivity, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transactionSheetActivity.c();
        return Unit.INSTANCE;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        if (transactionRegistry.getTransaction() == null) {
            transactionRegistry.initTransactionFailure$lib_release();
        }
        R9 r9 = a(base).a;
        r9.getClass();
        Intrinsics.checkNotNullParameter("language", "key");
        super.attachBaseContext(AbstractActivityC0147g4.a(base, r9.a.getString("language", null)));
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final boolean e() {
        ((Jc) this.s.getValue()).getClass();
        return TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.s;
    }

    public final void i() {
        ((Jc) this.s.getValue()).getClass();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        Nb transactionModel$lib_release = transactionRegistry.getTransactionModel$lib_release();
        String str = transactionModel$lib_release.n;
        TransactionSuccess transactionSuccess = null;
        String str2 = null;
        transactionSuccess = null;
        if (str != null && transactionModel$lib_release.i != null) {
            SavedPaymentMethod savedPaymentMethod = transactionModel$lib_release.h.l ? transactionModel$lib_release.l : null;
            Intrinsics.checkNotNull(str);
            PaymentMethodType paymentMethodType = transactionModel$lib_release.i;
            Intrinsics.checkNotNull(paymentMethodType);
            String str3 = transactionModel$lib_release.a;
            if (str3 != null) {
                str2 = str3;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mobileToken");
            }
            transactionSuccess = new TransactionSuccess(str, paymentMethodType, str2, savedPaymentMethod);
        }
        Transaction transaction = transactionRegistry.getTransaction();
        if (transaction != null) {
            Intrinsics.checkNotNull(transactionSuccess);
            transaction.finish$lib_release(transactionSuccess);
        }
        Sb.a();
        Sb.a.postValue(transactionSuccess);
        finish();
    }

    public final void j() {
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((C0182n) this.o.getValue()).c, this);
        ((C0182n) this.o.getValue()).d.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (Boolean) obj);
            }
        }));
        ((C0182n) this.o.getValue()).a.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (t4) obj);
            }
        }));
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (TransactionException) obj);
            }
        }, ((C0182n) this.o.getValue()).b, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((C0182n) this.o.getValue()).e, this);
    }

    public final void k() {
        final long integer = getResources().getInteger(R.integer.config_mediumAnimTime);
        ((Jc) this.s.getValue()).q = (long) (integer * 0.25d);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.c(TransactionSheetActivity.this, (TransactionException) obj);
            }
        }, ((Jc) this.s.getValue()).c, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (Ac) obj);
            }
        }, ((Jc) this.s.getValue()).f, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.c(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((Jc) this.s.getValue()).d, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.d(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((Jc) this.s.getValue()).e, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (Ae) obj);
            }
        }, ((Jc) this.s.getValue()).g, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (Ae) obj);
            }
        }, ((Jc) this.s.getValue()).h, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.e(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((Jc) this.s.getValue()).i, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (String) obj);
            }
        }, ((Jc) this.s.getValue()).j, this);
        ((Jc) this.s.getValue()).n.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, integer, (Boolean) obj);
            }
        }));
        ((Jc) this.s.getValue()).l.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (t4) obj);
            }
        }));
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (TransactionException) obj);
            }
        }, ((Jc) this.s.getValue()).m, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (Boolean) obj);
            }
        }, ((Jc) this.s.getValue()).o, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.f(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((Jc) this.s.getValue()).k, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, (Nb) obj);
            }
        }, ((Jc) this.s.getValue()).p, this);
    }

    public final void l() {
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.g(TransactionSheetActivity.this, (Unit) obj);
            }
        }, ((N5) this.r.getValue()).d, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (Nb) obj);
            }
        }, ((N5) this.r.getValue()).e, this);
        ((N5) this.r.getValue()).b.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.c(TransactionSheetActivity.this, (t4) obj);
            }
        }));
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.d(TransactionSheetActivity.this, (TransactionException) obj);
            }
        }, ((N5) this.r.getValue()).c, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.a(TransactionSheetActivity.this, ((Boolean) obj).booleanValue());
            }
        }, ((N5) this.r.getValue()).f, this);
    }

    public final void m() {
        ((C0224td) this.p.getValue()).e.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.c(TransactionSheetActivity.this, (Boolean) obj);
            }
        }));
        ((C0224td) this.p.getValue()).a.observe(this, new Ub(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.d(TransactionSheetActivity.this, (t4) obj);
            }
        }));
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.e(TransactionSheetActivity.this, (TransactionException) obj);
            }
        }, ((C0224td) this.p.getValue()).b, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.b(TransactionSheetActivity.this, (String) obj);
            }
        }, ((C0224td) this.p.getValue()).d, this);
        qg.a(new Function1() { // from class: ch.datatrans.payment.bottomsheet.TransactionSheetActivity$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransactionSheetActivity.c(TransactionSheetActivity.this, (String) obj);
            }
        }, ((C0224td) this.p.getValue()).c, this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        Fragment findFragmentByTag;
        super.onActivityResult(i, i2, intent);
        if (i != 1111) {
            if (i == 2222 || i != 3333 || (findFragmentByTag = getSupportFragmentManager().findFragmentByTag("GOOGLE_PAY")) == null) {
                return;
            }
            findFragmentByTag.onActivityResult(i, i2, intent);
            return;
        }
        d(false);
        Object obj = null;
        String url = intent != null ? intent.getStringExtra(ExternalProcessRelayActivity.EXTRA_CALLBACK_URL) : null;
        if (i2 == 0 || url == null) {
            ((Jc) this.s.getValue()).c();
            return;
        }
        if (i2 == -1) {
            Jc jc = (Jc) this.s.getValue();
            jc.getClass();
            Intrinsics.checkNotNullParameter(url, "url");
            Ae ae = (Ae) jc.h.getValue();
            if (ae == null) {
                PaymentMethodType paymentMethodType = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().i;
                Intrinsics.checkNotNull(paymentMethodType);
                ae = jc.a(paymentMethodType);
            }
            Iterator it = ae.f().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((Y9) next).a(url)) {
                    obj = next;
                    break;
                }
            }
            Y9 y9 = (Y9) obj;
            if (y9 != null) {
                y9.c(url);
            }
        }
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4, ch.datatrans.payment.C, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isFinishing()) {
            return;
        }
        if (bundle == null) {
            ((N5) this.r.getValue()).a();
        }
        l();
        k();
        j();
        m();
    }

    public static final Unit e(TransactionSheetActivity transactionSheetActivity, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transactionSheetActivity.d(true);
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) transactionSheetActivity.n.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) transactionSheetActivity.n.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit e(TransactionSheetActivity transactionSheetActivity, TransactionException transactionException) {
        Intrinsics.checkNotNull(transactionException);
        transactionSheetActivity.a(transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, Boolean bool) {
        ((F) transactionSheetActivity.l.getValue()).a.postValue(bool);
        return Unit.INSTANCE;
    }

    public static final Unit c(TransactionSheetActivity transactionSheetActivity, Boolean bool) {
        Pa pa = (Pa) transactionSheetActivity.n.getValue();
        Intrinsics.checkNotNull(bool);
        bool.getClass();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final void d() {
        ((Jc) this.s.getValue()).getClass();
        InitialLoader initialLoader = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.y;
        if (initialLoader != null) {
            initialLoader.dismiss();
        }
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, Nb value) {
        Intrinsics.checkNotNullParameter(value, "model");
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        String str = value.h.j;
        R9 r9 = transactionSheetActivity.a((Context) transactionSheetActivity).a;
        r9.getClass();
        Intrinsics.checkNotNullParameter("language", "key");
        if (!Intrinsics.areEqual(r9.a.getString("language", null), str)) {
            A6 a = transactionSheetActivity.a((Context) transactionSheetActivity);
            BuildersKt__Builders_commonKt.launch$default(a.b, a.c, null, new z6(str, a, null), 2, null);
            transactionSheetActivity.recreate();
        }
        ((Jc) transactionSheetActivity.s.getValue()).e();
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, Ae webProcess) {
        Intrinsics.checkNotNullParameter(webProcess, "webProcess");
        Pa pa = (Pa) transactionSheetActivity.n.getValue();
        pa.getClass();
        Intrinsics.checkNotNullParameter(webProcess, "webProcess");
        pa.h = webProcess;
        Xa xa = pa.g;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit c(TransactionSheetActivity transactionSheetActivity, Unit it) {
        PaymentMethodType paymentMethodType;
        Intrinsics.checkNotNullParameter(it, "it");
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        Nb transactionModel$lib_release = transactionRegistry.getTransactionModel$lib_release();
        PaymentMethodType paymentMethodType2 = transactionModel$lib_release.i;
        Intrinsics.checkNotNull(paymentMethodType2);
        if (!paymentMethodType2.isCreditCard$lib_release() && (paymentMethodType = transactionModel$lib_release.i) != PaymentMethodType.GOOGLE_PAY && paymentMethodType != PaymentMethodType.POST_FINANCE_PAY && transactionModel$lib_release.d != null) {
            transactionSheetActivity.i();
        } else {
            ((Pa) transactionSheetActivity.n.getValue()).b.postValue(Boolean.TRUE);
            C0182n c0182n = (C0182n) transactionSheetActivity.o.getValue();
            ((Jc) transactionSheetActivity.s.getValue()).getClass();
            c0182n.a(transactionRegistry.getTransactionModel$lib_release());
        }
        return Unit.INSTANCE;
    }

    public static final Unit d(TransactionSheetActivity transactionSheetActivity, TransactionException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        transactionSheetActivity.a(exception);
        return Unit.INSTANCE;
    }

    public static final Unit d(TransactionSheetActivity transactionSheetActivity, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transactionSheetActivity.c();
        return Unit.INSTANCE;
    }

    public final void d(boolean z) {
        getApplicationContext().getPackageManager().setComponentEnabledSetting(new ComponentName(getPackageName(), ExternalProcessRelayActivity.class.getName()), z ? 1 : 2, 1);
    }

    public static final Unit c(TransactionSheetActivity transactionSheetActivity, String str) {
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        TransactionRegistry.INSTANCE.getTransactionModel$lib_release().n = str;
        ((Jc) transactionSheetActivity.s.getValue()).a((Be) null);
        return Unit.INSTANCE;
    }

    public static final Unit c(TransactionSheetActivity transactionSheetActivity, TransactionException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        transactionSheetActivity.a(exception);
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final void c() {
        String str;
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        Transaction transaction = transactionRegistry.getTransaction();
        if (transaction == null || (str = transaction.getMobileToken()) == null) {
            str = "";
        }
        Transaction transaction2 = transactionRegistry.getTransaction();
        if (transaction2 != null) {
            transaction2.cancel$lib_release();
        }
        Sb.a();
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        Sb.e = str;
        Sb.c.postValue(Boolean.TRUE);
        finish();
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, Nb model) {
        C0224td c0224td = (C0224td) transactionSheetActivity.p.getValue();
        Intrinsics.checkNotNull(model);
        c0224td.getClass();
        Intrinsics.checkNotNullParameter(model, "model");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0224td), Dispatchers.getIO(), null, new pd(c0224td, model, null), 2, null);
        return Unit.INSTANCE;
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, String str) {
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        transactionRegistry.getTransactionModel$lib_release().n = str;
        C0182n c0182n = (C0182n) transactionSheetActivity.o.getValue();
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        c0182n.a(transactionRegistry.getTransactionModel$lib_release());
        return Unit.INSTANCE;
    }

    public static final ViewModelProvider.Factory a(TransactionSheetActivity transactionSheetActivity) {
        Application application = transactionSheetActivity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
        ((Jc) transactionSheetActivity.s.getValue()).getClass();
        return new O5(application, TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a());
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, Ae webProcess) {
        Intrinsics.checkNotNullParameter(webProcess, "webProcess");
        transactionSheetActivity.d(true);
        Intent intent = new Intent(transactionSheetActivity, (Class<?>) ExternalProcessRelayActivity.class);
        intent.putExtra(ExternalProcessRelayActivity.EXTRA_CUSTOM_TABS_URL, webProcess.e());
        intent.addFlags(536870912);
        transactionSheetActivity.startActivityForResult(intent, 1111);
        return Unit.INSTANCE;
    }

    public final A6 a(Context context) {
        A6 a6 = this.u;
        if (a6 != null) {
            return a6;
        }
        A6 a62 = new A6(new R9(context), LifecycleOwnerKt.getLifecycleScope(this));
        this.u = a62;
        return a62;
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, TransactionException transactionException) {
        Intrinsics.checkNotNull(transactionException);
        transactionSheetActivity.a(transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, boolean z) {
        transactionSheetActivity.c(z);
        return Unit.INSTANCE;
    }

    public static final Unit b(TransactionSheetActivity transactionSheetActivity, Unit unit) {
        transactionSheetActivity.c();
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, Ac screen) {
        Fragment fragment;
        Intrinsics.checkNotNullParameter(screen, "screen");
        transactionSheetActivity.getClass();
        B4.a = null;
        B4.b = null;
        AbstractC0144g0.a();
        switch (screen.ordinal()) {
            case 0:
                transactionSheetActivity.d();
                ((F) transactionSheetActivity.l.getValue()).a.postValue(Boolean.TRUE);
                fragment = new Na();
                break;
            case 1:
                transactionSheetActivity.d();
                ((F) transactionSheetActivity.l.getValue()).a.postValue(Boolean.TRUE);
                fragment = new p9();
                break;
            case 2:
                fragment = new C0245w2();
                break;
            case 3:
                fragment = new L8();
                break;
            case 4:
                fragment = new W4();
                break;
            case 5:
                fragment = new Ba();
                break;
            case 6:
                fragment = new C0126cd();
                break;
            case 7:
                fragment = new C0159i6();
                break;
            case 8:
                fragment = new mb();
                break;
            case 9:
                fragment = new Td();
                break;
            case 10:
                fragment = new C0118c7();
                break;
            case 11:
                fragment = new gg();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        FragmentManager supportFragmentManager = transactionSheetActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        String name = screen.name();
        Intrinsics.checkNotNullParameter(supportFragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        boolean isEmpty = supportFragmentManager.getFragments().isEmpty();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (!isEmpty) {
            beginTransaction.setCustomAnimations(ch.datatrans.payment.R.anim.dtpl_slide_left_in, ch.datatrans.payment.R.anim.dtpl_slide_left_out, ch.datatrans.payment.R.anim.dtpl_slide_right_in, ch.datatrans.payment.R.anim.dtpl_slide_right_out);
            beginTransaction.addToBackStack("bottom_sheet");
        }
        beginTransaction.replace(ch.datatrans.payment.R.id.container, fragment, name);
        beginTransaction.commit();
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        transactionSheetActivity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(url)), 2222);
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, long j, Boolean bool) {
        View findViewById = transactionSheetActivity.findViewById(ch.datatrans.payment.R.id.list);
        if (bool.booleanValue() && findViewById != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById, "translationX", -findViewById.getWidth());
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.start();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        transactionSheetActivity.i();
        return Unit.INSTANCE;
    }

    public static final Unit a(TransactionSheetActivity transactionSheetActivity, TransactionException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        transactionSheetActivity.a(exception);
        return Unit.INSTANCE;
    }

    public final void a(TransactionException transactionException) {
        Transaction transaction = TransactionRegistry.INSTANCE.getTransaction();
        if (transaction != null) {
            transaction.fail$lib_release(transactionException);
        }
        Sb.a();
        Sb.b.postValue(transactionException);
        finish();
    }
}
