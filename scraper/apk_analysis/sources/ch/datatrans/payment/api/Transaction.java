package ch.datatrans.payment.api;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import ch.datatrans.payment.AbstractC0144g0;
import ch.datatrans.payment.AbstractC0236v;
import ch.datatrans.payment.B4;
import ch.datatrans.payment.C;
import ch.datatrans.payment.C7;
import ch.datatrans.payment.Q5;
import ch.datatrans.payment.Sb;
import ch.datatrans.payment.bottomsheet.TransactionSheetActivity;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.exception.TransactionListenerOnSuccessException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardLabelType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.r4;
import ch.datatrans.payment.s4;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.tealium.library.DataSources;
import io.sentry.SentryEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 K2\u00020\u0001:\u0001KB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u0004\u0010\fB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R*\u0010.\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b'\u0010(\u0012\u0004\b-\u0010 \u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u00106\u001a\u00020/8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u00107\u001a\u0004\u0018\u00010\u00068\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R0\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R(\u0010D\u001a\u0004\u0018\u00010\n2\b\u00107\u001a\u0004\u0018\u00010\n8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001a\u0010J\u001a\u00020E8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I¨\u0006L"}, d2 = {"Lch/datatrans/payment/api/Transaction;", "", "", "mobileToken", "<init>", "(Ljava/lang/String;)V", "Lch/datatrans/payment/paymentmethods/Card;", "card", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/Card;)V", "", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "savedPaymentMethods", "(Ljava/lang/String;Ljava/util/List;)V", "savedPaymentMethod", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;)V", "Landroid/app/Activity;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "", "start$lib_release", "(Landroid/app/Activity;)V", "start", "Lch/datatrans/payment/api/TransactionSuccess;", "result", "finish$lib_release", "(Lch/datatrans/payment/api/TransactionSuccess;)V", "finish", "Lch/datatrans/payment/exception/TransactionException;", SentryEvent.JsonKeys.EXCEPTION, "fail$lib_release", "(Lch/datatrans/payment/exception/TransactionException;)V", "fail", "cancel$lib_release", "()V", "cancel", "a", "Ljava/lang/String;", "getMobileToken$lib_release", "()Ljava/lang/String;", "Lch/datatrans/payment/api/TransactionListener;", "b", "Lch/datatrans/payment/api/TransactionListener;", "getListener", "()Lch/datatrans/payment/api/TransactionListener;", "setListener", "(Lch/datatrans/payment/api/TransactionListener;)V", "getListener$annotations", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lch/datatrans/payment/api/TransactionOptions;", "c", "Lch/datatrans/payment/api/TransactionOptions;", "getOptions", "()Lch/datatrans/payment/api/TransactionOptions;", "setOptions", "(Lch/datatrans/payment/api/TransactionOptions;)V", RRWebOptionsEvent.EVENT_TAG, "value", "d", "Lch/datatrans/payment/paymentmethods/Card;", "getCard$lib_release", "()Lch/datatrans/payment/paymentmethods/Card;", "e", "Ljava/util/List;", "getSavedPaymentMethods$lib_release", "()Ljava/util/List;", "f", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "getAppInitSavedPaymentMethod$lib_release", "()Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "appInitSavedPaymentMethod", "Lch/datatrans/payment/Q5;", "g", "Lch/datatrans/payment/Q5;", "getInternalOptions$lib_release", "()Lch/datatrans/payment/Q5;", "internalOptions", "Companion", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class Transaction {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: a, reason: from kotlin metadata */
    public final String mobileToken;

    /* renamed from: b, reason: from kotlin metadata */
    public TransactionListener listener;

    /* renamed from: c, reason: from kotlin metadata */
    public TransactionOptions options;

    /* renamed from: d, reason: from kotlin metadata */
    public final Card card;

    /* renamed from: e, reason: from kotlin metadata */
    public final List savedPaymentMethods;

    /* renamed from: f, reason: from kotlin metadata */
    public final SavedPaymentMethod appInitSavedPaymentMethod;

    /* renamed from: g, reason: from kotlin metadata */
    public final Q5 internalOptions;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001J\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/api/Transaction$Companion;", "", "Landroidx/lifecycle/LifecycleOwner;", "owner", "Lch/datatrans/payment/api/TransactionListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "subscribe", "(Landroidx/lifecycle/LifecycleOwner;Lch/datatrans/payment/api/TransactionListener;)V", "unsubscribe", "(Landroidx/lifecycle/LifecycleOwner;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final void subscribe(LifecycleOwner owner, TransactionListener listener) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            Intrinsics.checkNotNullParameter(listener, "listener");
            Sb.a(owner, listener);
        }

        public final void unsubscribe(LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            MutableLiveData mutableLiveData = Sb.a;
            Intrinsics.checkNotNullParameter(owner, "owner");
            Sb.a();
            Sb.d = false;
            Sb.a.removeObservers(owner);
            Sb.b.removeObservers(owner);
            Sb.c.removeObservers(owner);
        }
    }

    public Transaction(String mobileToken) {
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        this.mobileToken = mobileToken;
        this.options = new TransactionOptions();
        this.savedPaymentMethods = CollectionsKt.emptyList();
        this.internalOptions = new Q5();
    }

    @Deprecated(message = "This property is now deprecated you should use static method Transaction.subscribe instead.")
    public static /* synthetic */ void getListener$annotations() {
    }

    public final void cancel$lib_release() {
        TransactionRegistry.INSTANCE.clear$lib_release();
        B4.a = null;
        B4.b = null;
        AbstractC0144g0.a();
        TransactionListener transactionListener = this.listener;
        if (transactionListener != null) {
            transactionListener.onTransactionCancel(this.mobileToken);
        }
    }

    public final void fail$lib_release(TransactionException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        TransactionRegistry.INSTANCE.clear$lib_release();
        B4.a = null;
        B4.b = null;
        AbstractC0144g0.a();
        TransactionListener transactionListener = this.listener;
        if (transactionListener != null) {
            transactionListener.onTransactionError(exception);
        }
    }

    public final void finish$lib_release(TransactionSuccess result) {
        Intrinsics.checkNotNullParameter(result, "result");
        TransactionRegistry.INSTANCE.clear$lib_release();
        B4.a = null;
        B4.b = null;
        AbstractC0144g0.a();
        try {
            TransactionListener transactionListener = this.listener;
            if (transactionListener != null) {
                transactionListener.onTransactionSuccess(result);
            }
        } catch (Exception e) {
            throw new TransactionListenerOnSuccessException(e);
        }
    }

    /* renamed from: getAppInitSavedPaymentMethod$lib_release, reason: from getter */
    public final SavedPaymentMethod getAppInitSavedPaymentMethod() {
        return this.appInitSavedPaymentMethod;
    }

    /* renamed from: getCard$lib_release, reason: from getter */
    public final Card getCard() {
        return this.card;
    }

    /* renamed from: getInternalOptions$lib_release, reason: from getter */
    public final Q5 getInternalOptions() {
        return this.internalOptions;
    }

    public final TransactionListener getListener() {
        return this.listener;
    }

    /* renamed from: getMobileToken$lib_release, reason: from getter */
    public final String getMobileToken() {
        return this.mobileToken;
    }

    public final TransactionOptions getOptions() {
        return this.options;
    }

    public final List<SavedPaymentMethod> getSavedPaymentMethods$lib_release() {
        return this.savedPaymentMethods;
    }

    public final void setListener(TransactionListener transactionListener) {
        this.listener = transactionListener;
    }

    public final void setOptions(TransactionOptions transactionOptions) {
        Intrinsics.checkNotNullParameter(transactionOptions, "<set-?>");
        this.options = transactionOptions;
    }

    public final void start$lib_release(Activity activity) {
        String str;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.listener != null) {
            boolean z = Sb.d;
        }
        StringBuilder sb = new StringBuilder("Datatrans Mobile SDK 3.9.2 (");
        StringBuilder sb2 = new StringBuilder("Android ");
        sb2.append(Build.VERSION.RELEASE);
        sb2.append("/target=");
        try {
            ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            str = String.valueOf(applicationInfo.targetSdkVersion);
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        sb2.append(str);
        sb.append(sb2.toString());
        sb.append(')');
        Log.d("DTPL", sb.toString());
        Q5 q5 = this.internalOptions;
        q5.a = this.options.getAppCallbackScheme();
        q5.w = this.options.getUseCertificatePinning();
        q5.B = this.options.getMerchantProperties().get("__devHost");
        LinkedHashMap linkedHashMap = q5.k;
        Map<String, String> merchantProperties = this.options.getMerchantProperties();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<String, String> entry : merchantProperties.entrySet()) {
            if (!Intrinsics.areEqual(entry.getKey(), "__devHost")) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        linkedHashMap.putAll(linkedHashMap2);
        q5.s = this.options.getSuppressCriticalErrorDialog();
        q5.v = this.options.getIsTesting();
        CardLabelType cardLabelType = this.options.getCardLabelType();
        Intrinsics.checkNotNullParameter(cardLabelType, "<set-?>");
        q5.h = cardLabelType;
        q5.i = this.options.getGooglePayConfig();
        q5.o = this.options.getSamsungPayConfig();
        q5.b = this.options.getBoncardConfig();
        q5.p = this.options.getKlarnaConfig();
        q5.y = this.options.getCustomInitialLoader();
        DCCShowMode savedCardDCCShowMode = this.options.getSavedCardDCCShowMode();
        Intrinsics.checkNotNullParameter(savedCardDCCShowMode, "<set-?>");
        q5.A = savedCardDCCShowMode;
        C7 c7 = r4.a;
        String str2 = this.mobileToken;
        Q5 options = this.internalOptions;
        Intrinsics.checkNotNullParameter(options, "options");
        s4 s4Var = new s4();
        s4Var.a = options.p;
        s4Var.b = options.v;
        s4Var.c = options.w;
        s4Var.d = options.a;
        s4Var.e = options.B;
        r4.a(str2, s4Var, activity);
        MutableLiveData mutableLiveData = Sb.a;
        String str3 = this.mobileToken;
        Intrinsics.checkNotNullParameter(str3, "<set-?>");
        Sb.e = str3;
        int i = TransactionSheetActivity.t;
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivity(new Intent(activity, (Class<?>) TransactionSheetActivity.class));
        int i2 = C.m;
        AbstractC0236v.a(activity);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Transaction(String mobileToken, Card card) {
        this(mobileToken);
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        Intrinsics.checkNotNullParameter(card, "card");
        this.card = card;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Transaction(String mobileToken, List<? extends SavedPaymentMethod> savedPaymentMethods) {
        this(mobileToken);
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        Intrinsics.checkNotNullParameter(savedPaymentMethods, "savedPaymentMethods");
        this.savedPaymentMethods = savedPaymentMethods;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Transaction(String mobileToken, SavedPaymentMethod savedPaymentMethod) {
        this(mobileToken);
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        Intrinsics.checkNotNullParameter(savedPaymentMethod, "savedPaymentMethod");
        this.appInitSavedPaymentMethod = savedPaymentMethod;
    }
}
