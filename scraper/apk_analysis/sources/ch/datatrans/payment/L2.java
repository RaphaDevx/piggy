package ch.datatrans.payment;

import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes3.dex */
public final class L2 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ M2 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L2(M2 m2, Continuation continuation) {
        super(2, continuation);
        this.b = m2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new L2(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new L2(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.b.i.postValue(Boxing.boxBoolean(true));
                M2 m2 = this.b;
                this.a = 1;
                Object a = M2.a(m2, this);
                this = a;
                if (a == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this = this;
            }
        } catch (D7 unused) {
            this.b.a();
        } catch (SSLException unused2) {
            M2 m22 = this.b;
            int i2 = M2.o;
            m22.e();
        } catch (Exception e) {
            M2 m23 = this.b;
            int i3 = M2.o;
            m23.a(e);
        }
        return Unit.INSTANCE;
    }
}
