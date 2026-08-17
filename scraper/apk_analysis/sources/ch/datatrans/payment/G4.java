package ch.datatrans.payment;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class G4 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ H4 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G4(H4 h4, Continuation continuation) {
        super(2, continuation);
        this.b = h4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new G4(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new G4(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        H4 h4 = this.b;
        this.a = 1;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(this));
        try {
            h4.d.getClass();
            JSONObject put = new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
            Intrinsics.checkNotNullExpressionValue(put, "put(...)");
            put.put("allowedPaymentMethods", new JSONArray().put(h4.d.a()));
            put.put("existingPaymentMethodRequired", h4.d.a.getExistingPaymentMethodRequired());
            IsReadyToPayRequest fromJson = IsReadyToPayRequest.fromJson(put.toString());
            Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
            Context context = h4.a;
            boolean z = h4.b;
            Intrinsics.checkNotNullParameter(context, "context");
            PaymentsClient paymentsClient = Wallet.getPaymentsClient(context, new Wallet.WalletOptions.Builder().setEnvironment(z ? 3 : 1).build());
            Intrinsics.checkNotNullExpressionValue(paymentsClient, "getPaymentsClient(...)");
            Task<Boolean> isReadyToPay = paymentsClient.isReadyToPay(fromJson);
            Intrinsics.checkNotNullExpressionValue(isReadyToPay, "isReadyToPay(...)");
            try {
                Boolean bool = (Boolean) Tasks.await(isReadyToPay, 5000L, TimeUnit.MILLISECONDS);
                Result.Companion companion = Result.INSTANCE;
                safeContinuation.resumeWith(Result.m3945constructorimpl(bool));
            } catch (InterruptedException unused) {
                Result.Companion companion2 = Result.INSTANCE;
                safeContinuation.resumeWith(Result.m3945constructorimpl(Boxing.boxBoolean(false)));
            } catch (ExecutionException unused2) {
                Result.Companion companion3 = Result.INSTANCE;
                safeContinuation.resumeWith(Result.m3945constructorimpl(Boxing.boxBoolean(false)));
            } catch (TimeoutException unused3) {
                Result.Companion companion4 = Result.INSTANCE;
                safeContinuation.resumeWith(Result.m3945constructorimpl(Boxing.boxBoolean(false)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Result.Companion companion5 = Result.INSTANCE;
            safeContinuation.resumeWith(Result.m3945constructorimpl(Boxing.boxBoolean(false)));
        }
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(this);
        }
        return orThrow == coroutine_suspended ? coroutine_suspended : orThrow;
    }
}
