package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "ch.datatrans.payment.paymentmethods.GooglePayConfig$Companion$hasGooglePayJava$1", f = "GooglePayConfig.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class GooglePayConfig$Companion$hasGooglePayJava$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public MutableLiveData a;
    public int b;
    public final /* synthetic */ MutableLiveData c;
    public final /* synthetic */ Context d;
    public final /* synthetic */ List e;
    public final /* synthetic */ List f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ boolean h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ boolean j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GooglePayConfig$Companion$hasGooglePayJava$1(MutableLiveData mutableLiveData, Context context, List list, List list2, boolean z, boolean z2, boolean z3, boolean z4, Continuation continuation) {
        super(2, continuation);
        this.c = mutableLiveData;
        this.d = context;
        this.e = list;
        this.f = list2;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = z4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GooglePayConfig$Companion$hasGooglePayJava$1(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.b;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableLiveData mutableLiveData2 = this.c;
            GooglePayConfig.Companion companion = GooglePayConfig.INSTANCE;
            Context context = this.d;
            List<? extends PaymentMethodType> list = this.e;
            List<? extends GooglePayConfig.AuthenticationMethodType> list2 = this.f;
            boolean z = this.g;
            boolean z2 = this.h;
            boolean z3 = this.i;
            boolean z4 = this.j;
            this.a = mutableLiveData2;
            this.b = 1;
            Object hasGooglePay = companion.hasGooglePay(context, list, list2, z, z2, z3, z4, this);
            if (hasGooglePay == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = hasGooglePay;
            mutableLiveData = mutableLiveData2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mutableLiveData = this.a;
            ResultKt.throwOnFailure(obj);
        }
        mutableLiveData.postValue(obj);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GooglePayConfig$Companion$hasGooglePayJava$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
