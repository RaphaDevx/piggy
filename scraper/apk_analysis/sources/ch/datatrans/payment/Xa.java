package ch.datatrans.payment;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Xa extends MutableLiveData {
    public final AtomicBoolean a = new AtomicBoolean(false);

    public static final Unit a(Xa xa, Observer observer, Object obj) {
        if (xa.a.compareAndSet(true, false)) {
            observer.onChanged(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.lifecycle.LiveData
    public final void observe(LifecycleOwner owner, final Observer observer) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(observer, "observer");
        super.observe(owner, new Wa(new Function1() { // from class: ch.datatrans.payment.Xa$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Xa.a(Xa.this, observer, obj);
            }
        }));
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public final void setValue(Object obj) {
        this.a.set(true);
        super.setValue(obj);
    }
}
