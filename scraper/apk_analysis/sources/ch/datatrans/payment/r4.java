package ch.datatrans.payment;

import android.app.Activity;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.KlarnaConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedBoncard;
import ch.datatrans.payment.paymentmethods.SavedCard;
import ch.datatrans.payment.paymentmethods.SavedGooglePay;
import ch.datatrans.payment.paymentmethods.SavedPayPal;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.paymentmethods.SavedPostFinanceCard;
import ch.datatrans.payment.paymentmethods.SavedPostFinancePay;
import ch.datatrans.payment.paymentmethods.SavedReka;
import ch.datatrans.payment.paymentmethods.SavedSEPA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.sentry.SentryReplayEvent;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class r4 {
    public static C7 a;
    public static E7 b;
    public static wd c;
    public static C0156he d;
    public static De e;
    public static Map f;
    public static final Lazy g = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.r4$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return r4.b();
        }
    });

    public static void a(String mobileToken, s4 options, Activity context) {
        E7 c0153h4;
        E7 e7;
        wd wdVar;
        String customName$lib_release;
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(context, "context");
        String str = options.e;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            c0153h4 = new C0172l4(str);
        } else {
            c0153h4 = new C0153h4(options.b);
        }
        Intrinsics.checkNotNullParameter(c0153h4, "<set-?>");
        b = c0153h4;
        wd wdVar2 = new wd(context);
        Intrinsics.checkNotNullParameter(wdVar2, "<set-?>");
        c = wdVar2;
        E7 e72 = b;
        C0156he c0156he = null;
        if (e72 != null) {
            e7 = e72;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException(SentryReplayEvent.JsonKeys.URLS);
            e7 = null;
        }
        wd wdVar3 = c;
        if (wdVar3 != null) {
            wdVar = wdVar3;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("version");
            wdVar = null;
        }
        C7 c7 = new C7(mobileToken, e7, wdVar, options.c, options.b);
        Intrinsics.checkNotNullParameter(c7, "<set-?>");
        a = c7;
        C0156he c0156he2 = new C0156he(options.d, options.b);
        Intrinsics.checkNotNullParameter(c0156he2, "<set-?>");
        d = c0156he2;
        E7 e73 = b;
        if (e73 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SentryReplayEvent.JsonKeys.URLS);
            e73 = null;
        }
        C0156he c0156he3 = d;
        if (c0156he3 != null) {
            c0156he = c0156he3;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("webCallbackUrls");
        }
        De de2 = new De(e73, c0156he);
        Intrinsics.checkNotNullParameter(de2, "<set-?>");
        e = de2;
        Map createMapBuilder = MapsKt.createMapBuilder();
        KlarnaConfig klarnaConfig = options.a;
        if (klarnaConfig != null && (customName$lib_release = klarnaConfig.getCustomName$lib_release()) != null) {
            PaymentMethodType paymentMethodType = PaymentMethodType.KLARNA;
            Intrinsics.checkNotNullParameter(customName$lib_release, "<this>");
            createMapBuilder.put(paymentMethodType, new Za(customName$lib_release));
        }
        Map build = MapsKt.build(createMapBuilder);
        Intrinsics.checkNotNullParameter(build, "<set-?>");
        f = build;
    }

    public static final Gson b() {
        return new GsonBuilder().registerTypeHierarchyAdapter(PaymentMethodType.class, new PaymentMethodType.PaymentMethodTypeSerializer()).registerTypeHierarchyAdapter(SavedPaymentMethod.class, new SavedPaymentMethod.SavedPaymentMethodSerializer()).registerTypeHierarchyAdapter(SavedCard.class, new SavedCard.SavedCardSerializer()).registerTypeHierarchyAdapter(CardExpiryDate.class, new CardExpiryDate.CardExpiryDateSerializer()).registerTypeHierarchyAdapter(SavedReka.class, new SavedReka.SavedRekaSerializer()).registerTypeHierarchyAdapter(SavedPostFinanceCard.class, new SavedPostFinanceCard.SavedPostFinanceCardSerializer()).registerTypeHierarchyAdapter(SavedSEPA.class, new SavedSEPA.SavedSEPASerializer()).registerTypeHierarchyAdapter(SavedPayPal.class, new SavedPayPal.SavedPayPalSerializer()).registerTypeHierarchyAdapter(SavedBoncard.class, new SavedBoncard.SavedBoncardSerializer()).registerTypeHierarchyAdapter(SavedGooglePay.class, new SavedGooglePay.SavedGooglePaySerializer()).registerTypeHierarchyAdapter(SavedPostFinancePay.class, new SavedPostFinancePay.SavedPostFinancePaySerializer()).create();
    }

    public static C7 a() {
        C7 c7 = a;
        if (c7 != null) {
            return c7;
        }
        Intrinsics.throwUninitializedPropertyAccessException("networking");
        return null;
    }
}
