package ch.datatrans.payment;

import ch.datatrans.payment.exception.ModuleMissingException;
import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.json.JSONException;

/* loaded from: classes3.dex */
public final class L5 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ N5 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L5(N5 n5, Continuation continuation) {
        super(2, continuation);
        this.b = n5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new L5(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new L5(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v16 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                N5 n5 = this.b;
                this.a = 1;
                Object a = N5.a(n5, this);
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
            N5 n52 = this.b;
            int i2 = N5.g;
            n52.b();
        } catch (ModuleMissingException e) {
            N5 n53 = this.b;
            int i3 = N5.g;
            n53.a(e);
        } catch (SSLException unused2) {
            N5 n54 = this.b;
            int i4 = N5.g;
            n54.c();
        } catch (JSONException unused3) {
            N5 n55 = this.b;
            int i5 = N5.g;
            n55.b();
        } catch (Exception e2) {
            N5 n56 = this.b;
            int i6 = N5.g;
            n56.a(e2);
        }
        return Unit.INSTANCE;
    }
}
