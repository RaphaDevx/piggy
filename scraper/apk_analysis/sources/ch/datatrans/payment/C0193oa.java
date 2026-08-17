package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Result;
import kotlin.coroutines.SafeContinuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.oa, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0193oa implements b9 {
    public final Context a;
    public final SamsungPayConfig b;
    public final boolean c;
    public final PaymentMethodType d;
    public final Lazy e;

    public C0193oa(Context context, SamsungPayConfig config, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.a = context;
        this.b = config;
        this.c = z;
        this.d = PaymentMethodType.SAMSUNG_PAY;
        this.e = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.oa$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0193oa.a(C0193oa.this);
            }
        });
    }

    @Override // ch.datatrans.payment.b9
    public final PaymentMethodType a() {
        return this.d;
    }

    public static final Map a(C0193oa c0193oa) {
        c0193oa.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(linkedHashMap, SpaySdk.ERROR_DEVICE_NOT_SAMSUNG, "ERROR_DEVICE_NOT_SAMSUNG");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_PKG_NOT_FOUND, "ERROR_SPAY_PKG_NOT_FOUND");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_SDK_SERVICE_NOT_AVAILABLE, "ERROR_SPAY_SDK_SERVICE_NOT_AVAILABLE");
        a(linkedHashMap, SpaySdk.ERROR_DEVICE_INTEGRITY_CHECK_FAIL, "ERROR_DEVICE_INTEGRITY_CHECK_FAIL");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_APP_INTEGRITY_CHECK_FAIL, "ERROR_SPAY_APP_INTEGRITY_CHECK_FAIL");
        a(linkedHashMap, SpaySdk.ERROR_ANDROID_PLATFORM_CHECK_FAIL, "ERROR_ANDROID_PLATFORM_CHECK_FAIL");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_SETUP_NOT_COMPLETED, "ERROR_SPAY_SETUP_NOT_COMPLETED");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_APP_NEED_TO_UPDATE, "ERROR_SPAY_APP_NEED_TO_UPDATE");
        a(linkedHashMap, SpaySdk.ERROR_SERVER_INTERNAL, "ERROR_SERVER_INTERNAL");
        a(linkedHashMap, -12, "ERROR_INVALID_PARAMETER");
        a(linkedHashMap, SpaySdk.ERROR_SDK_NOT_SUPPORTED_FOR_THIS_REGION, "ERROR_SDK_NOT_SUPPORTED_FOR_THIS_REGION");
        a(linkedHashMap, -301, "ERROR_SERVICE_ID_INVALID");
        a(linkedHashMap, -302, "ERROR_SERVICE_UNAVAILABLE_FOR_THIS_REGION");
        a(linkedHashMap, -303, "ERROR_SERVICE_NOT_VERIFIED_WITH_PARTNER");
        a(linkedHashMap, -304, "ERROR_SERVICE_VERSION_NOT_SUPPORTED");
        a(linkedHashMap, -305, "ERROR_SERVICE_BLOCKED");
        a(linkedHashMap, SpaySdk.ERROR_USER_NOT_REGISTERED_FOR_DEBUG, "ERROR_USER_NOT_REGISTERED_FOR_DEBUG");
        a(linkedHashMap, -307, "ERROR_SERVICE_NOT_APPROVED_FOR_RELEASE");
        a(linkedHashMap, SpaySdk.ERROR_PARTNER_NOT_APPROVED, "ERROR_PARTNER_NOT_APPROVED");
        a(linkedHashMap, SpaySdk.ERROR_UNAUTHORIZED_REQUEST_TYPE, "ERROR_UNAUTHORIZED_REQUEST_TYPE");
        a(linkedHashMap, SpaySdk.ERROR_EXPIRED_OR_INVALID_DEBUG_KEY, "ERROR_EXPIRED_OR_INVALID_DEBUG_KEY");
        a(linkedHashMap, -354, "ERROR_MISSING_INFORMATION");
        a(linkedHashMap, SpaySdk.ERROR_UNABLE_TO_VERIFY_CALLER, "ERROR_UNABLE_TO_VERIFY_CALLER");
        a(linkedHashMap, -10, "ERROR_PARTNER_SDK_API_LEVEL");
        a(linkedHashMap, -11, "ERROR_PARTNER_SERVICE_TYPE");
        a(linkedHashMap, -358, "ERROR_PARTNER_SDK_VERSION_NOT_ALLOWED");
        a(linkedHashMap, SpaySdk.ERROR_SPAY_CONNECTED_WITH_EXTERNAL_DISPLAY, "ERROR_SPAY_CONNECTED_WITH_EXTERNAL_DISPLAY");
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // ch.datatrans.payment.b9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.Continuation r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof ch.datatrans.payment.C0165ja
            if (r0 == 0) goto L13
            r0 = r5
            ch.datatrans.payment.ja r0 = (ch.datatrans.payment.C0165ja) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            ch.datatrans.payment.ja r0 = new ch.datatrans.payment.ja
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L51
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            ch.datatrans.payment.paymentmethods.PaymentMethodType r5 = r4.d
            kotlin.jvm.functions.Function0 r5 = r5.isModuleAvailable$lib_release()
            if (r5 == 0) goto L58
            java.lang.Object r5 = r5.invoke()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != r3) goto L58
            r0.c = r3
            java.lang.Object r5 = r4.a(r0)
            if (r5 != r1) goto L51
            return r1
        L51:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r4 = r5.booleanValue()
            goto L59
        L58:
            r4 = 0
        L59:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0193oa.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|(1:(1:10)(2:16|17))(3:18|19|(1:21))|11|12|13))|25|6|7|(0)(0)|11|12|13) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
    
        android.util.Log.e("DTPL", "Samsung Pay unexpected exception", r6);
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof ch.datatrans.payment.C0169ka
            if (r0 == 0) goto L13
            r0 = r7
            ch.datatrans.payment.ka r0 = (ch.datatrans.payment.C0169ka) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            ch.datatrans.payment.ka r0 = new ch.datatrans.payment.ka
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            if (r2 != r4) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            goto L46
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            ch.datatrans.payment.na r7 = new ch.datatrans.payment.na     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            r2 = 0
            r7.<init>(r6, r2)     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            r0.c = r4     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            r4 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r7 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r7, r0)     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            if (r7 != r1) goto L46
            return r1
        L46:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            boolean r3 = r7.booleanValue()     // Catch: java.lang.Exception -> L4d kotlinx.coroutines.TimeoutCancellationException -> L55
            goto L55
        L4d:
            r6 = move-exception
            java.lang.String r7 = "DTPL"
            java.lang.String r0 = "Samsung Pay unexpected exception"
            android.util.Log.e(r7, r0, r6)
        L55:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0193oa.a(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void a(SafeContinuation cont, Bundle bundle) {
        Intrinsics.checkNotNullParameter(cont, "cont");
        Result.Companion companion = Result.INSTANCE;
        cont.resumeWith(Result.m3945constructorimpl(Boolean.FALSE));
        Integer valueOf = bundle != null ? Integer.valueOf(bundle.getInt(SpaySdk.EXTRA_ERROR_REASON)) : null;
        if (valueOf != null) {
        }
    }

    public static void a(LinkedHashMap linkedHashMap, int i, String str) {
        linkedHashMap.put(Integer.valueOf(i), str + " (" + i + ')');
    }
}
