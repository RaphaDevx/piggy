package ch.datatrans.payment.api.tokenization;

import android.app.Activity;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.AbstractC0144g0;
import ch.datatrans.payment.AbstractC0236v;
import ch.datatrans.payment.C;
import ch.datatrans.payment.C7;
import ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity;
import ch.datatrans.payment.exception.PCIPTokenizationException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CvvOnlyCard;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.r4;
import ch.datatrans.payment.s4;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.tealium.library.DataSources;
import io.sentry.SentryEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0007\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R$\u0010-\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u00105\u001a\u00020.8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R4\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R(\u0010\n\u001a\u0004\u0018\u00010\t2\b\u00106\u001a\u0004\u0018\u00010\t8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u00106\u001a\u0004\u0018\u00010\f8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B¨\u0006C"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPTokenization;", "", "", "merchantId", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodTypes", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "Lch/datatrans/payment/paymentmethods/CvvOnlyCard;", "cvvOnlyCard", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CvvOnlyCard;)V", "Lch/datatrans/payment/paymentmethods/Card;", "card", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/Card;)V", "Landroid/app/Activity;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "", "start$lib_release", "(Landroid/app/Activity;)V", "start", "Lch/datatrans/payment/api/tokenization/PCIPTokenizationSuccess;", "success", "finish$lib_release", "(Lch/datatrans/payment/api/tokenization/PCIPTokenizationSuccess;)V", "finish", "Lch/datatrans/payment/exception/PCIPTokenizationException;", SentryEvent.JsonKeys.EXCEPTION, "fail$lib_release", "(Lch/datatrans/payment/exception/PCIPTokenizationException;)V", "fail", "cancel$lib_release", "()V", "cancel", "a", "Ljava/lang/String;", "getMerchantId$lib_release", "()Ljava/lang/String;", "Lch/datatrans/payment/api/tokenization/PCIPTokenizationListener;", "b", "Lch/datatrans/payment/api/tokenization/PCIPTokenizationListener;", "getListener", "()Lch/datatrans/payment/api/tokenization/PCIPTokenizationListener;", "setListener", "(Lch/datatrans/payment/api/tokenization/PCIPTokenizationListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lch/datatrans/payment/api/tokenization/PCIPTokenizationOptions;", "c", "Lch/datatrans/payment/api/tokenization/PCIPTokenizationOptions;", "getOptions", "()Lch/datatrans/payment/api/tokenization/PCIPTokenizationOptions;", "setOptions", "(Lch/datatrans/payment/api/tokenization/PCIPTokenizationOptions;)V", RRWebOptionsEvent.EVENT_TAG, "value", "d", "Ljava/util/List;", "getPaymentMethodTypes$lib_release", "()Ljava/util/List;", "e", "Lch/datatrans/payment/paymentmethods/CvvOnlyCard;", "getCvvOnlyCard$lib_release", "()Lch/datatrans/payment/paymentmethods/CvvOnlyCard;", "f", "Lch/datatrans/payment/paymentmethods/Card;", "getCard$lib_release", "()Lch/datatrans/payment/paymentmethods/Card;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PCIPTokenization {

    /* renamed from: a, reason: from kotlin metadata */
    public final String merchantId;

    /* renamed from: b, reason: from kotlin metadata */
    public PCIPTokenizationListener listener;

    /* renamed from: c, reason: from kotlin metadata */
    public PCIPTokenizationOptions options;

    /* renamed from: d, reason: from kotlin metadata */
    public final List paymentMethodTypes;

    /* renamed from: e, reason: from kotlin metadata */
    public final CvvOnlyCard cvvOnlyCard;

    /* renamed from: f, reason: from kotlin metadata */
    public final Card card;

    public PCIPTokenization(String str) {
        this.merchantId = str;
        this.options = new PCIPTokenizationOptions();
    }

    public final void cancel$lib_release() {
        PCIPTokenizationListener pCIPTokenizationListener = this.listener;
        if (pCIPTokenizationListener != null) {
            pCIPTokenizationListener.onTokenizationCancel();
        }
        PCIPTokenizationRegistry.INSTANCE.clear$lib_release();
        AbstractC0144g0.a();
    }

    public final void fail$lib_release(PCIPTokenizationException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        PCIPTokenizationListener pCIPTokenizationListener = this.listener;
        if (pCIPTokenizationListener != null) {
            pCIPTokenizationListener.onTokenizationError(exception);
        }
        PCIPTokenizationRegistry.INSTANCE.clear$lib_release();
        AbstractC0144g0.a();
    }

    public final void finish$lib_release(PCIPTokenizationSuccess success) {
        Intrinsics.checkNotNullParameter(success, "success");
        PCIPTokenizationListener pCIPTokenizationListener = this.listener;
        if (pCIPTokenizationListener != null) {
            pCIPTokenizationListener.onTokenizationSuccess(success);
        }
        PCIPTokenizationRegistry.INSTANCE.clear$lib_release();
        AbstractC0144g0.a();
    }

    /* renamed from: getCard$lib_release, reason: from getter */
    public final Card getCard() {
        return this.card;
    }

    /* renamed from: getCvvOnlyCard$lib_release, reason: from getter */
    public final CvvOnlyCard getCvvOnlyCard() {
        return this.cvvOnlyCard;
    }

    public final PCIPTokenizationListener getListener() {
        return this.listener;
    }

    /* renamed from: getMerchantId$lib_release, reason: from getter */
    public final String getMerchantId() {
        return this.merchantId;
    }

    public final PCIPTokenizationOptions getOptions() {
        return this.options;
    }

    public final List<PaymentMethodType> getPaymentMethodTypes$lib_release() {
        return this.paymentMethodTypes;
    }

    public final void setListener(PCIPTokenizationListener pCIPTokenizationListener) {
        this.listener = pCIPTokenizationListener;
    }

    public final void setOptions(PCIPTokenizationOptions pCIPTokenizationOptions) {
        Intrinsics.checkNotNullParameter(pCIPTokenizationOptions, "<set-?>");
        this.options = pCIPTokenizationOptions;
    }

    public final void start$lib_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        C7 c7 = r4.a;
        PCIPTokenizationOptions options = this.options;
        Intrinsics.checkNotNullParameter(options, "options");
        s4 s4Var = new s4();
        s4Var.b = options.getIsTesting();
        s4Var.c = options.getUseCertificatePinning();
        r4.a("", s4Var, activity);
        int i = PCIPTokenizationRequestSheetActivity.q;
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivity(new Intent(activity, (Class<?>) PCIPTokenizationRequestSheetActivity.class));
        int i2 = C.m;
        AbstractC0236v.a(activity);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PCIPTokenization(String merchantId, List<? extends PaymentMethodType> paymentMethodTypes) {
        this(merchantId);
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(paymentMethodTypes, "paymentMethodTypes");
        this.paymentMethodTypes = paymentMethodTypes;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PCIPTokenization(String merchantId, CvvOnlyCard cvvOnlyCard) {
        this(merchantId);
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(cvvOnlyCard, "cvvOnlyCard");
        this.cvvOnlyCard = cvvOnlyCard;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PCIPTokenization(String merchantId, Card card) {
        this(merchantId, (List<? extends PaymentMethodType>) CollectionsKt.listOf(card.getType()));
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(card, "card");
        this.card = card;
    }
}
