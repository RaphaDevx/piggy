package ch.datatrans.payment;

import android.content.pm.PackageManager;
import android.os.Build;
import androidx.fragment.app.FragmentActivity;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final class Zf extends SuspendLambda implements Function2 {
    public ig a;
    public int b;
    public final /* synthetic */ gg c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Zf(gg ggVar, Continuation continuation) {
        super(2, continuation);
        this.c = ggVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new Zf(this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new Zf(this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        ig igVar;
        jg jgVar;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.b;
        boolean z2 = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ig igVar2 = (ig) this.c.h.getValue();
            FragmentActivity context = this.c.requireActivity();
            Intrinsics.checkNotNullExpressionValue(context, "requireActivity(...)");
            ((Jc) this.c.b.getValue()).getClass();
            boolean z3 = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.v;
            Intrinsics.checkNotNullParameter(context, "context");
            this.a = igVar2;
            this.b = 1;
            try {
                context.getPackageManager().getPackageInfo(z3 ? "ch.postfinance.android.t2" : "ch.postfinance.android", 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
                z = false;
            }
            Boolean boxBoolean = Boxing.boxBoolean(z);
            if (boxBoolean == coroutine_suspended) {
                return coroutine_suspended;
            }
            igVar = igVar2;
            obj = boxBoolean;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            igVar = this.a;
            ResultKt.throwOnFailure(obj);
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (Build.VERSION.SDK_INT < 30) {
            z2 = this.c.getResources().getBoolean(R.bool.isTablet);
        } else if (!this.c.getResources().getBoolean(R.bool.isTablet) || !this.c.requireActivity().getPackageManager().hasSystemFeature("android.hardware.sensor.hinge_angle")) {
            z2 = false;
        }
        if (!igVar.e().b || (jgVar = igVar.e().h.C) == null || jgVar.a) {
            if (booleanValue && !z2) {
                jg jgVar2 = igVar.e().h.C;
                if (!(jgVar2 != null ? Intrinsics.areEqual(jgVar2.b, Boolean.TRUE) : false)) {
                    U9 u9 = igVar.o;
                    StringBuilder sb = new StringBuilder();
                    String str = igVar.e().h.a;
                    Intrinsics.checkNotNull(str);
                    sb.append(str);
                    sb.append("://datatrans-sdk-callback");
                    kg kgVar = new kg(sb.toString());
                    C0149ga c0149ga = u9.a;
                    PaymentMethodType paymentMethodType = u9.e;
                    T9 request = new T9(u9, kgVar, null);
                    c0149ga.getClass();
                    Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                    Intrinsics.checkNotNullParameter(request, "request");
                    BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
                }
            }
            igVar.n.postValue(Unit.INSTANCE);
        } else {
            igVar.b.postValue(Unit.INSTANCE);
        }
        return Unit.INSTANCE;
    }
}
