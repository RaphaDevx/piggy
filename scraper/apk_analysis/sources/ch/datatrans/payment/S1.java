package ch.datatrans.payment;

import android.text.Editable;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes3.dex */
public final class S1 extends ViewModel {
    public final U1 a;
    public final C0205r0 b;
    public final W0 c;
    public final C3 d;
    public final C0219t0 e;
    public final MutableLiveData f;
    public final MutableLiveData g;
    public final MutableLiveData h;
    public final MutableLiveData i;
    public final MutableLiveData j;
    public final MutableLiveData k;
    public final Xa l;
    public final Xa m;
    public final Xa n;
    public final Xa o;
    public final Xa p;

    public S1(U1 dateModel, C0205r0 cvvModel, W0 dccModel, C3 numberModel, C0219t0 c0219t0) {
        Intrinsics.checkNotNullParameter(dateModel, "dateModel");
        Intrinsics.checkNotNullParameter(cvvModel, "cvvModel");
        Intrinsics.checkNotNullParameter(dccModel, "dccModel");
        Intrinsics.checkNotNullParameter(numberModel, "numberModel");
        this.a = dateModel;
        this.b = cvvModel;
        this.c = dccModel;
        this.d = numberModel;
        this.e = c0219t0;
        this.f = new MutableLiveData();
        this.g = new MutableLiveData();
        Boolean bool = Boolean.FALSE;
        this.h = new MutableLiveData(bool);
        this.i = new MutableLiveData(bool);
        this.j = new MutableLiveData(bool);
        this.k = new MutableLiveData(bool);
        this.l = new Xa();
        this.m = new Xa();
        this.n = new Xa();
        this.o = new Xa();
        this.p = new Xa();
    }

    public static final Unit a(S1 s1, Exception exception, Q3 q3) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(q3, "<unused var>");
        s1.i.postValue(Boolean.FALSE);
        W0 w0 = s1.c;
        a1 a1Var = a1.d;
        w0.getClass();
        Intrinsics.checkNotNullParameter(a1Var, "<set-?>");
        w0.f = a1Var;
        s1.b();
        String message = exception.getMessage();
        if (message == null) {
            message = "DCC Error";
        }
        Log.e("DTPL", message, exception);
        return Unit.INSTANCE;
    }

    public final void b() {
        boolean a = this.a.a();
        boolean a2 = this.b.a();
        boolean z = this.b.c;
        a1 a1Var = this.c.f;
        boolean z2 = false;
        boolean z3 = a1Var == a1.c || a1Var == a1.d;
        this.k.postValue(Boolean.valueOf(a && !a2 && z && z3));
        this.h.postValue(Boolean.valueOf(a && a2 && z3));
        MutableLiveData mutableLiveData = this.j;
        if (a && ((z || a2) && !z3)) {
            z2 = true;
        }
        mutableLiveData.postValue(Boolean.valueOf(z2));
    }

    public final void c() {
        C0219t0 c0219t0 = this.e;
        this.f.postValue(this.b.a);
        this.g.postValue(this.a.a);
        b();
        PaymentMethodType paymentMethodType = this.d.b.c;
        if (paymentMethodType != null) {
            if ((c0219t0 != null ? c0219t0.a : null) == null || !c0219t0.a(paymentMethodType)) {
                return;
            }
            W0 w0 = this.c;
            if (w0.f != a1.b) {
                a1 a1Var = a1.a;
                Intrinsics.checkNotNullParameter(a1Var, "<set-?>");
                w0.f = a1Var;
                Xa xa = this.p;
                C3 c3 = this.d;
                PaymentMethodType paymentMethodType2 = c3.b.c;
                String str = c3.a;
                Q3 q3 = Q3.a;
                xa.postValue(new Y0(c0219t0, new Function2() { // from class: ch.datatrans.payment.S1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return S1.a(S1.this, (Exception) obj, (Q3) obj2);
                    }
                }, new Function2() { // from class: ch.datatrans.payment.S1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return S1.a(S1.this, (X0) obj, (Q3) obj2);
                    }
                }, paymentMethodType2, Q3.a, null, str));
            }
        }
    }

    public static final Unit a(S1 s1, X0 result, Q3 q3) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(q3, "<unused var>");
        s1.i.postValue(Boolean.FALSE);
        s1.c.a(result);
        s1.b();
        s1.a(false);
        return Unit.INSTANCE;
    }

    public final void a() {
        if (!this.a.a()) {
            this.l.postValue(Unit.INSTANCE);
        } else if (!this.b.a()) {
            this.m.postValue(Unit.INSTANCE);
        } else if (this.c.f != a1.b) {
            this.n.postValue(Unit.INSTANCE);
        }
    }

    public static boolean a(Editable editable) {
        if (editable.length() == 3) {
            Iterable intRange = new IntRange(0, 9);
            if ((intRange instanceof Collection) && ((Collection) intRange).isEmpty()) {
                return false;
            }
            Iterator it = intRange.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                StringBuilder sb = new StringBuilder();
                sb.append((Object) editable);
                sb.append(nextInt);
                if (a(sb.toString())) {
                    return true;
                }
            }
            return false;
        }
        return a((CharSequence) editable);
    }

    public static boolean a(CharSequence charSequence) {
        int intValue;
        int intValue2;
        String date = charSequence.toString();
        Intrinsics.checkNotNullParameter(date, "date");
        if (date.length() != 4) {
            return false;
        }
        Integer intOrNull = date.length() >= 2 ? StringsKt.toIntOrNull(StringsKt.take(date, 2)) : null;
        if (intOrNull == null || 1 > (intValue = intOrNull.intValue()) || intValue >= 13) {
            return false;
        }
        Integer intOrNull2 = date.length() == 4 ? StringsKt.toIntOrNull(StringsKt.takeLast(date, 2)) : null;
        return intOrNull2 != null && 25 <= (intValue2 = intOrNull2.intValue()) && intValue2 < 100;
    }

    public final void a(boolean z) {
        boolean a = this.a.a();
        boolean a2 = this.b.a();
        boolean z2 = this.b.c;
        a1 a1Var = this.c.f;
        if ((a2 || z2) && a) {
            if (a1Var == a1.b) {
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new R1(z, this, null), 2, null);
            } else if (a1Var == a1.a) {
                this.i.postValue(Boolean.TRUE);
            }
        }
    }
}
