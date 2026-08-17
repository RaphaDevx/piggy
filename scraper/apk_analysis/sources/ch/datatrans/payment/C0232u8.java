package ch.datatrans.payment;

import ch.datatrans.payment.exception.BackendException;
import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* renamed from: ch.datatrans.payment.u8, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0232u8 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0247w8 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0232u8(C0247w8 c0247w8, Continuation continuation) {
        super(2, continuation);
        this.b = c0247w8;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0232u8(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0232u8(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v15 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C0247w8 c0247w8 = this.b;
                this.a = 1;
                Object a = C0247w8.a(c0247w8, this);
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
            C0247w8 c0247w82 = this.b;
            int i2 = C0247w8.r;
            c0247w82.a();
        } catch (SSLException unused2) {
            C0247w8 c0247w83 = this.b;
            int i3 = C0247w8.r;
            c0247w83.b();
        } catch (Exception e) {
            C0247w8 c0247w84 = this.b;
            int i4 = C0247w8.r;
            c0247w84.i.postValue(e instanceof BackendException ? c0247w84.a((BackendException) e) : c0247w84.a(e));
        }
        return Unit.INSTANCE;
    }
}
