package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ch.datatrans.payment.H4;
import ch.datatrans.payment.paymentmethods.CustomerInfoParameters;
import com.getcapacitor.PluginMethod;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 42\u00020\u0001:\u0003456R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\u0012\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u00138\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001e\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u0011R\u001a\u0010$\u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010'\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b%\u0010\u000f\u001a\u0004\b&\u0010\u0011R\u001a\u0010*\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010\u000f\u001a\u0004\b)\u0010\u0011R\u001c\u00100\u001a\u0004\u0018\u00010+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001c\u00103\u001a\u0004\u0018\u00010\u00138\u0000X\u0080\u0004¢\u0006\f\n\u0004\b1\u0010\u0015\u001a\u0004\b2\u0010\u0017¨\u00067"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "a", "Ljava/util/List;", "getSupportedNetworks$lib_release", "()Ljava/util/List;", "supportedNetworks", "Lch/datatrans/payment/paymentmethods/GooglePayConfig$AuthenticationMethodType;", "b", "getAuthenticationMethods$lib_release", "authenticationMethods", "", "c", "Z", "getExistingPaymentMethodRequired$lib_release", "()Z", "existingPaymentMethodRequired", "", "d", "Ljava/lang/String;", "getMerchantName$lib_release", "()Ljava/lang/String;", "merchantName", "e", "getShowLargeButton$lib_release", "showLargeButton", "f", "getShowGooglePayAsSavedPaymentMethod$lib_release", "showGooglePayAsSavedPaymentMethod", "Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "g", "Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "getCustomerInfoParameters$lib_release", "()Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "customerInfoParameters", "h", "getAreCreditCardsAllowed$lib_release", "areCreditCardsAllowed", "i", "getArePrepaidCardsAllowed$lib_release", "arePrepaidCardsAllowed", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;", "j", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;", "getCustomerInfoCallback$lib_release", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;", "customerInfoCallback", "k", "getCustomLabel$lib_release", "customLabel", "Companion", "Builder", "AuthenticationMethodType", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class GooglePayConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final List l = CollectionsKt.listOf((Object[]) new AuthenticationMethodType[]{AuthenticationMethodType.CRYPTOGRAM_3DS, AuthenticationMethodType.PAN_ONLY});

    /* renamed from: a, reason: from kotlin metadata */
    public final List supportedNetworks;

    /* renamed from: b, reason: from kotlin metadata */
    public final List authenticationMethods;

    /* renamed from: c, reason: from kotlin metadata */
    public final boolean existingPaymentMethodRequired;

    /* renamed from: d, reason: from kotlin metadata */
    public final String merchantName;

    /* renamed from: e, reason: from kotlin metadata */
    public final boolean showLargeButton;

    /* renamed from: f, reason: from kotlin metadata */
    public final boolean showGooglePayAsSavedPaymentMethod;

    /* renamed from: g, reason: from kotlin metadata */
    public final CustomerInfoParameters customerInfoParameters;

    /* renamed from: h, reason: from kotlin metadata */
    public final boolean areCreditCardsAllowed;

    /* renamed from: i, reason: from kotlin metadata */
    public final boolean arePrepaidCardsAllowed;

    /* renamed from: j, reason: from kotlin metadata */
    public final GooglePayCustomerInfoCallback customerInfoCallback;

    /* renamed from: k, reason: from kotlin metadata */
    public final String customLabel;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayConfig$AuthenticationMethodType;", "", "PAN_ONLY", "CRYPTOGRAM_3DS", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AuthenticationMethodType {
        public static final AuthenticationMethodType CRYPTOGRAM_3DS;
        public static final AuthenticationMethodType PAN_ONLY;
        public static final /* synthetic */ AuthenticationMethodType[] a;
        public static final /* synthetic */ EnumEntries b;

        static {
            AuthenticationMethodType authenticationMethodType = new AuthenticationMethodType("PAN_ONLY", 0);
            PAN_ONLY = authenticationMethodType;
            AuthenticationMethodType authenticationMethodType2 = new AuthenticationMethodType("CRYPTOGRAM_3DS", 1);
            CRYPTOGRAM_3DS = authenticationMethodType2;
            AuthenticationMethodType[] authenticationMethodTypeArr = {authenticationMethodType, authenticationMethodType2};
            a = authenticationMethodTypeArr;
            b = EnumEntriesKt.enumEntries(authenticationMethodTypeArr);
        }

        public AuthenticationMethodType(String str, int i) {
        }

        public static EnumEntries<AuthenticationMethodType> getEntries() {
            return b;
        }

        public static AuthenticationMethodType valueOf(String str) {
            return (AuthenticationMethodType) Enum.valueOf(AuthenticationMethodType.class, str);
        }

        public static AuthenticationMethodType[] values() {
            return (AuthenticationMethodType[]) a.clone();
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0000¢\u0006\u0004\b\u000e\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0000¢\u0006\u0004\b\u0019\u0010\rJ\r\u0010\u001a\u001a\u00020\u0000¢\u0006\u0004\b\u001a\u0010\rJ\r\u0010\u001b\u001a\u00020\u0000¢\u0006\u0004\b\u001b\u0010\rJ\r\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayConfig$Builder;", "", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "supportedNetworks", "", "merchantName", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "label", "showCustomLabel", "(Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/GooglePayConfig$Builder;", "showLargeButton", "()Lch/datatrans/payment/paymentmethods/GooglePayConfig$Builder;", "showInSavedPaymentMethods", "Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "parameters", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;", PluginMethod.RETURN_CALLBACK, "returnCustomerInfo", "(Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;)Lch/datatrans/payment/paymentmethods/GooglePayConfig$Builder;", "Lch/datatrans/payment/paymentmethods/GooglePayConfig$AuthenticationMethodType;", "authenticationMethods", "allowedAuthenticationMethods", "(Ljava/util/List;)Lch/datatrans/payment/paymentmethods/GooglePayConfig$Builder;", "requireExistingPaymentMethod", "disableCreditCards", "disablePrepaidCards", "Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "build", "()Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        public final List a;
        public final String b;
        public String c;
        public boolean d;
        public boolean e;
        public CustomerInfoParameters f;
        public GooglePayCustomerInfoCallback g;
        public List h;
        public boolean i;
        public boolean j;
        public boolean k;

        public Builder(List<? extends PaymentMethodType> supportedNetworks, String merchantName) {
            Intrinsics.checkNotNullParameter(supportedNetworks, "supportedNetworks");
            Intrinsics.checkNotNullParameter(merchantName, "merchantName");
            this.a = supportedNetworks;
            this.b = merchantName;
            this.f = new CustomerInfoParameters.Builder().build();
            this.h = GooglePayConfig.l;
            this.j = true;
            this.k = true;
        }

        public final Builder allowedAuthenticationMethods(List<? extends AuthenticationMethodType> authenticationMethods) {
            Intrinsics.checkNotNullParameter(authenticationMethods, "authenticationMethods");
            if (authenticationMethods.isEmpty()) {
                authenticationMethods = GooglePayConfig.l;
            }
            this.h = authenticationMethods;
            return this;
        }

        public final GooglePayConfig build() {
            return new GooglePayConfig(this.a, this.h, this.i, this.b, this.d, this.e, this.f, this.j, this.k, this.g, this.c, null);
        }

        public final Builder disableCreditCards() {
            this.j = false;
            return this;
        }

        public final Builder disablePrepaidCards() {
            this.k = false;
            return this;
        }

        public final Builder requireExistingPaymentMethod() {
            this.i = true;
            return this;
        }

        public final Builder returnCustomerInfo(CustomerInfoParameters parameters, GooglePayCustomerInfoCallback callback) {
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.f = parameters;
            this.g = callback;
            return this;
        }

        public final Builder showCustomLabel(String label) {
            Intrinsics.checkNotNullParameter(label, "label");
            this.c = label;
            return this;
        }

        public final Builder showInSavedPaymentMethods() {
            this.e = true;
            return this;
        }

        public final Builder showLargeButton() {
            this.d = true;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001J^\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fJY\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0014R\u0014\u0010\u001a\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayConfig$Companion;", "", "Landroid/content/Context;", "context", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "supportedNetworks", "Lch/datatrans/payment/paymentmethods/GooglePayConfig$AuthenticationMethodType;", "authenticationMethods", "", "existingPaymentMethodRequired", "areCreditCardsAllowed", "arePrepaidCardsAllowed", "isTesting", "hasGooglePay", "(Landroid/content/Context;Ljava/util/List;Ljava/util/List;ZZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LiveData;", "hasGooglePayJava", "(Landroid/content/Context;Ljava/util/List;Ljava/util/List;ZZZZ)Landroidx/lifecycle/LiveData;", "DEFAULT_EXISTING_PAYMENT_METHOD_REQUIRED", "Z", "DEFAULT_SHOW_LARGE_BUTTON", "DEFAULT_SAVED_PAYMENT_METHOD", "DEFAULT_AUTHENTICATION_METHODS", "Ljava/util/List;", "DEFAULT_CREDIT_CARDS_ALLOWED", "DEFAULT_PREPAID_CARDS_ALLOWED", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final Object hasGooglePay(Context context, List<? extends PaymentMethodType> list, List<? extends AuthenticationMethodType> list2, boolean z, boolean z2, boolean z3, boolean z4, Continuation<? super Boolean> continuation) {
            return new H4(context, new GooglePayConfig(list, list2, z, "", false, false, new CustomerInfoParameters.Builder().build(), z2, z3, null, null), z4).a(continuation);
        }

        @JvmStatic
        public final LiveData<Boolean> hasGooglePayJava(Context context, List<? extends PaymentMethodType> supportedNetworks, List<? extends AuthenticationMethodType> authenticationMethods, boolean existingPaymentMethodRequired, boolean areCreditCardsAllowed, boolean arePrepaidCardsAllowed, boolean isTesting) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(supportedNetworks, "supportedNetworks");
            Intrinsics.checkNotNullParameter(authenticationMethods, "authenticationMethods");
            MutableLiveData mutableLiveData = new MutableLiveData(Boolean.FALSE);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new GooglePayConfig$Companion$hasGooglePayJava$1(mutableLiveData, context, supportedNetworks, authenticationMethods, existingPaymentMethodRequired, areCreditCardsAllowed, arePrepaidCardsAllowed, isTesting, null), 3, null);
            return mutableLiveData;
        }
    }

    public /* synthetic */ GooglePayConfig(List list, List list2, boolean z, String str, boolean z2, boolean z3, CustomerInfoParameters customerInfoParameters, boolean z4, boolean z5, GooglePayCustomerInfoCallback googlePayCustomerInfoCallback, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, z, str, z2, z3, customerInfoParameters, z4, z5, googlePayCustomerInfoCallback, str2);
    }

    @JvmStatic
    public static final LiveData<Boolean> hasGooglePayJava(Context context, List<? extends PaymentMethodType> list, List<? extends AuthenticationMethodType> list2, boolean z, boolean z2, boolean z3, boolean z4) {
        return INSTANCE.hasGooglePayJava(context, list, list2, z, z2, z3, z4);
    }

    /* renamed from: getAreCreditCardsAllowed$lib_release, reason: from getter */
    public final boolean getAreCreditCardsAllowed() {
        return this.areCreditCardsAllowed;
    }

    /* renamed from: getArePrepaidCardsAllowed$lib_release, reason: from getter */
    public final boolean getArePrepaidCardsAllowed() {
        return this.arePrepaidCardsAllowed;
    }

    public final List<AuthenticationMethodType> getAuthenticationMethods$lib_release() {
        return this.authenticationMethods;
    }

    /* renamed from: getCustomLabel$lib_release, reason: from getter */
    public final String getCustomLabel() {
        return this.customLabel;
    }

    /* renamed from: getCustomerInfoCallback$lib_release, reason: from getter */
    public final GooglePayCustomerInfoCallback getCustomerInfoCallback() {
        return this.customerInfoCallback;
    }

    /* renamed from: getCustomerInfoParameters$lib_release, reason: from getter */
    public final CustomerInfoParameters getCustomerInfoParameters() {
        return this.customerInfoParameters;
    }

    /* renamed from: getExistingPaymentMethodRequired$lib_release, reason: from getter */
    public final boolean getExistingPaymentMethodRequired() {
        return this.existingPaymentMethodRequired;
    }

    /* renamed from: getMerchantName$lib_release, reason: from getter */
    public final String getMerchantName() {
        return this.merchantName;
    }

    /* renamed from: getShowGooglePayAsSavedPaymentMethod$lib_release, reason: from getter */
    public final boolean getShowGooglePayAsSavedPaymentMethod() {
        return this.showGooglePayAsSavedPaymentMethod;
    }

    /* renamed from: getShowLargeButton$lib_release, reason: from getter */
    public final boolean getShowLargeButton() {
        return this.showLargeButton;
    }

    public final List<PaymentMethodType> getSupportedNetworks$lib_release() {
        return this.supportedNetworks;
    }

    public GooglePayConfig(List list, List list2, boolean z, String str, boolean z2, boolean z3, CustomerInfoParameters customerInfoParameters, boolean z4, boolean z5, GooglePayCustomerInfoCallback googlePayCustomerInfoCallback, String str2) {
        this.supportedNetworks = list;
        this.authenticationMethods = list2;
        this.existingPaymentMethodRequired = z;
        this.merchantName = str;
        this.showLargeButton = z2;
        this.showGooglePayAsSavedPaymentMethod = z3;
        this.customerInfoParameters = customerInfoParameters;
        this.areCreditCardsAllowed = z4;
        this.arePrepaidCardsAllowed = z5;
        this.customerInfoCallback = googlePayCustomerInfoCallback;
        this.customLabel = str2;
    }
}
