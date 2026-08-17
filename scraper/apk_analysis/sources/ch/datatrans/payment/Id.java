package ch.datatrans.payment;

import android.content.pm.PackageManager;
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
public final class Id extends SuspendLambda implements Function2 {
    public Zd a;
    public int b;
    public final /* synthetic */ Td c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Id(Td td, Continuation continuation) {
        super(2, continuation);
        this.c = td;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new Id(this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new Id(this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Zd zd;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.b;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Zd zd2 = (Zd) this.c.c.getValue();
            FragmentActivity context = this.c.requireActivity();
            Intrinsics.checkNotNullExpressionValue(context, "requireActivity(...)");
            ((Jc) this.c.b.getValue()).getClass();
            boolean z2 = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.v;
            Intrinsics.checkNotNullParameter(context, "context");
            this.a = zd2;
            this.b = 1;
            try {
                context.getPackageManager().getPackageInfo(z2 ? "no.dnb.vipps.mt" : "no.dnb.vipps", 0);
            } catch (PackageManager.NameNotFoundException unused) {
                z = false;
            }
            Boolean boxBoolean = Boxing.boxBoolean(z);
            if (boxBoolean == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = boxBoolean;
            zd = zd2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zd = this.a;
            ResultKt.throwOnFailure(obj);
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        U9 u9 = zd.h;
        StringBuilder sb = new StringBuilder();
        String str = zd.e().h.a;
        Intrinsics.checkNotNull(str);
        sb.append(str);
        sb.append("://");
        C0127ce c0127ce = new C0127ce(sb.toString(), booleanValue);
        C0149ga c0149ga = u9.a;
        PaymentMethodType paymentMethodType = u9.e;
        T9 request = new T9(u9, c0127ce, null);
        c0149ga.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(request, "request");
        BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
        return Unit.INSTANCE;
    }
}
