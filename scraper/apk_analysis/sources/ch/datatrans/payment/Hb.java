package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTransaction;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedCard;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLException;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes3.dex */
public final class Hb implements Be {
    public final Nb a;
    public final ViewModel b;

    /* JADX WARN: Multi-variable type inference failed */
    public Hb(Nb transactionModel, xb threeDSFlowCallback) {
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(threeDSFlowCallback, "threeDSFlowCallback");
        this.a = transactionModel;
        this.b = (ViewModel) threeDSFlowCallback;
    }

    public final Object a(final String str, final CardExpiryDate cardExpiryDate, C0154h5 c0154h5) {
        Object a = a(new Ab(this, str, new Function1() { // from class: ch.datatrans.payment.Hb$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Hb.a(Hb.this, str, cardExpiryDate, ((Boolean) obj).booleanValue());
            }
        }, null), c0154h5);
        if (a != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            a = Unit.INSTANCE;
        }
        return a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? a : Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    @Override // ch.datatrans.payment.Be
    public final void c() {
        this.b.b();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    public static final Unit a(Hb hb, String str, CardExpiryDate cardExpiryDate, boolean z) {
        if (z) {
            hb.a(str, cardExpiryDate);
        } else {
            hb.b.a(hb.a);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    public final void a(String str, CardExpiryDate cardExpiryDate) {
        Y8 y8;
        String str2;
        CardExpiryDate cardExpiryDate2;
        CardExpiryDate cardExpiryDate3;
        String str3;
        De de2 = r4.e;
        if (de2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webRequestFactory");
            de2 = null;
        }
        Nb model = this.a;
        de2.getClass();
        Intrinsics.checkNotNullParameter(model, "model");
        String k = de2.a.k();
        HashMap hashMap = new HashMap(model.h.k);
        W8 w8 = model.k;
        if (w8 != null) {
            y8 = Z8.a(w8, null);
        } else {
            C0131e c0131e = model.d;
            Intrinsics.checkNotNull(c0131e);
            Intrinsics.checkNotNullParameter(c0131e, "<this>");
            y8 = new Y8(0, c0131e.a, c0131e.b, c0131e.c, c0131e.d, c0131e.e, null);
        }
        if (str == null) {
            str2 = model.n;
            Intrinsics.checkNotNull(str2);
        } else {
            str2 = str;
        }
        hashMap.put("uppTransactionId", str2);
        hashMap.put("merchantId", y8.a);
        hashMap.put(CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT, String.valueOf(y8.b));
        hashMap.put("currency", y8.c);
        if (cardExpiryDate == null) {
            Card card = model.f;
            if (card != null) {
                Intrinsics.checkNotNull(card);
                cardExpiryDate2 = card.getExpiryDate();
            } else {
                SavedPaymentMethod savedPaymentMethod = model.l;
                if (savedPaymentMethod instanceof SavedCard) {
                    Intrinsics.checkNotNull(savedPaymentMethod, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedCard");
                    cardExpiryDate2 = ((SavedCard) savedPaymentMethod).getCardExpiryDate();
                    Intrinsics.checkNotNull(cardExpiryDate2);
                } else {
                    cardExpiryDate2 = null;
                }
            }
        } else {
            cardExpiryDate2 = cardExpiryDate;
        }
        hashMap.put("expm", cardExpiryDate2 != null ? cardExpiryDate2.getFormattedMonth() : null);
        if (cardExpiryDate == null) {
            Card card2 = model.f;
            if (card2 != null) {
                Intrinsics.checkNotNull(card2);
                cardExpiryDate3 = card2.getExpiryDate();
            } else {
                SavedPaymentMethod savedPaymentMethod2 = model.l;
                if (savedPaymentMethod2 instanceof SavedCard) {
                    Intrinsics.checkNotNull(savedPaymentMethod2, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedCard");
                    cardExpiryDate3 = ((SavedCard) savedPaymentMethod2).getCardExpiryDate();
                    Intrinsics.checkNotNull(cardExpiryDate3);
                } else {
                    cardExpiryDate3 = null;
                }
            }
        } else {
            cardExpiryDate3 = cardExpiryDate;
        }
        hashMap.put("expy", cardExpiryDate3 != null ? cardExpiryDate3.getFormattedYear() : null);
        hashMap.put("errorUrl", de2.b.b);
        hashMap.put("returnUrl", de2.b.c);
        hashMap.put("testOnly", model.h.v ? BooleanUtils.YES : BooleanUtils.NO);
        hashMap.put("uppWebResponseMethod", "GET");
        hashMap.put("addResponseUrlParam", "true");
        wd wdVar = r4.c;
        if (wdVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("version");
            wdVar = null;
        }
        hashMap.putAll(wdVar.b());
        L3 l3 = model.g;
        if (l3 != null && (str3 = l3.a) != null) {
            hashMap.put("dccId", str3);
        }
        SavedPaymentMethod savedPaymentMethod3 = model.l;
        Card card3 = model.f;
        if (savedPaymentMethod3 != null && (savedPaymentMethod3 instanceof SavedCard)) {
            String cardholder = ((SavedCard) savedPaymentMethod3).getCardholder();
            if (cardholder != null) {
                hashMap.put("cardholderName", cardholder);
            }
        } else if ((card3 != null ? card3.getCardholder() : null) != null) {
            hashMap.put("cardholderName", card3.getCardholder());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            String str4 = (String) entry.getKey();
            String str5 = (String) entry.getValue();
            Object obj = linkedHashMap.get(str4);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(str4, obj);
            }
            ((List) obj).add(str5);
        }
        C0196p5 c0196p5 = new C0196p5(k, "UTF-8", linkedHashMap);
        PaymentMethodType paymentMethodType = this.a.i;
        Intrinsics.checkNotNull(paymentMethodType);
        this.b.a(new I9(c0196p5, paymentMethodType, this, true));
    }

    public final Object a(F2 f2) {
        Object a = a(new zb(this, new Function1() { // from class: ch.datatrans.payment.Hb$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Hb.a(Hb.this, ((Boolean) obj).booleanValue());
            }
        }, null), f2);
        if (a != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            a = Unit.INSTANCE;
        }
        return a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? a : Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    public static final Unit a(Hb hb, boolean z) {
        if (z) {
            hb.a(hb.a.n, (CardExpiryDate) null);
        } else {
            hb.b.a(hb.a);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|7|(1:(2:10|11)(2:17|18))(3:19|20|(1:22))|12|13|14))|24|6|7|(0)(0)|12|13|14) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0041, code lost:
    
        r4.b.a(new ch.datatrans.payment.Eb(r4, r5, null));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r6v2, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.jvm.functions.Function1 r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof ch.datatrans.payment.Db
            if (r0 == 0) goto L13
            r0 = r6
            ch.datatrans.payment.Db r0 = (ch.datatrans.payment.Db) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            ch.datatrans.payment.Db r0 = new ch.datatrans.payment.Db
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlin.jvm.functions.Function1 r5 = r0.a
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: ch.datatrans.payment.D7 -> L41
            goto L4c
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.a = r5     // Catch: ch.datatrans.payment.D7 -> L41
            r0.e = r3     // Catch: ch.datatrans.payment.D7 -> L41
            java.lang.Object r4 = r5.invoke(r0)     // Catch: ch.datatrans.payment.D7 -> L41
            if (r4 != r1) goto L4c
            return r1
        L41:
            androidx.lifecycle.ViewModel r6 = r4.b
            ch.datatrans.payment.Eb r0 = new ch.datatrans.payment.Eb
            r1 = 0
            r0.<init>(r4, r5, r1)
            r6.a(r0)
        L4c:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.Hb.a(kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    @Override // ch.datatrans.payment.Be
    public final void a(final BackendException exception) {
        C0178m4 c0178m4;
        String str;
        String replace$default;
        Intrinsics.checkNotNullParameter(exception, "e");
        boolean z = exception instanceof D7;
        if (z) {
            this.b.a();
            return;
        }
        final String transactionId = exception.getTransactionId();
        if (transactionId == null) {
            transactionId = this.a.n;
        }
        if (transactionId != null) {
            this.a.n = transactionId;
        }
        Map<String, String> info = exception.getInfo();
        final String message = (info == null || (str = info.get("threeDScardholderInfo")) == null || (replace$default = StringsKt.replace$default(str, "/'", "'", false, 4, (Object) null)) == null) ? null : StringsKt.replace$default(replace$default, "/\"", "\"", false, 4, (Object) null);
        boolean z2 = this.a.d == null;
        if (message == null) {
            C7 c7 = r4.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.Hb$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Hb.a(Hb.this, exception, transactionId);
                }
            };
            Intrinsics.checkNotNullParameter(exception, "exception");
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            if (exception instanceof SSLException) {
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, dismissAction);
            } else if (z) {
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, dismissAction);
            } else if (z2) {
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed, dismissAction);
            } else {
                Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
                c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction);
            }
        } else {
            C7 c72 = r4.a;
            Function0 dismissAction2 = new Function0() { // from class: ch.datatrans.payment.Hb$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Hb.a(Hb.this, exception, transactionId, message);
                }
            };
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(dismissAction2, "dismissAction");
            c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_authentication_failed, new Za(message), dismissAction2);
        }
        this.b.a(c0178m4);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    /* JADX WARN: Type inference failed for: r9v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    public static final Unit a(Hb hb, Exception exc, String str) {
        hb.b.a(null);
        PaymentMethodType paymentMethodType = hb.a.i;
        Intrinsics.checkNotNull(paymentMethodType);
        hb.b.a(new AuthenticationException(exc, paymentMethodType, str, null, 8, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    public static final Unit a(Hb hb, Exception exc, String str, String str2) {
        hb.b.a(null);
        PaymentMethodType paymentMethodType = hb.a.i;
        Intrinsics.checkNotNull(paymentMethodType);
        hb.b.a(new AuthenticationException(exc, paymentMethodType, str, str2));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.xb] */
    @Override // ch.datatrans.payment.Be
    public final void a(String str, Map parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        if (str != null) {
            this.a.n = str;
        }
        this.b.a(this.a);
    }
}
