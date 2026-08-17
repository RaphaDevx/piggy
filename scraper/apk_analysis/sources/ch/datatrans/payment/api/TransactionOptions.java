package ch.datatrans.payment.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.CardLabelType;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.KlarnaConfig;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import ch.datatrans.payment.paymentmethods.boncard.BoncardConfig;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 [2\u00020\u0001:\u0001[B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR.\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u001b\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001d\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\"\u0010\"\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR\"\u0010*\u001a\u00020#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u00102\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u0010:\u001a\u0004\u0018\u0001038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010B\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010J\u001a\u0004\u0018\u00010C8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR$\u0010R\u001a\u0004\u0018\u00010K8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\"\u0010Z\u001a\u00020S8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y¨\u0006\\"}, d2 = {"Lch/datatrans/payment/api/TransactionOptions;", "", "<init>", "()V", "", "a", "Ljava/lang/String;", "getAppCallbackScheme", "()Ljava/lang/String;", "setAppCallbackScheme", "(Ljava/lang/String;)V", "appCallbackScheme", "", "b", "Ljava/util/Map;", "getMerchantProperties", "()Ljava/util/Map;", "setMerchantProperties", "(Ljava/util/Map;)V", "merchantProperties", "", "c", "Z", "getSuppressCriticalErrorDialog", "()Z", "setSuppressCriticalErrorDialog", "(Z)V", "suppressCriticalErrorDialog", "d", "isTesting", "setTesting", "e", "getUseCertificatePinning", "setUseCertificatePinning", "useCertificatePinning", "Lch/datatrans/payment/paymentmethods/CardLabelType;", "f", "Lch/datatrans/payment/paymentmethods/CardLabelType;", "getCardLabelType", "()Lch/datatrans/payment/paymentmethods/CardLabelType;", "setCardLabelType", "(Lch/datatrans/payment/paymentmethods/CardLabelType;)V", "cardLabelType", "Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;", "g", "Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;", "getBoncardConfig", "()Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;", "setBoncardConfig", "(Lch/datatrans/payment/paymentmethods/boncard/BoncardConfig;)V", "boncardConfig", "Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "h", "Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "getGooglePayConfig", "()Lch/datatrans/payment/paymentmethods/GooglePayConfig;", "setGooglePayConfig", "(Lch/datatrans/payment/paymentmethods/GooglePayConfig;)V", "googlePayConfig", "Lch/datatrans/payment/paymentmethods/SamsungPayConfig;", "i", "Lch/datatrans/payment/paymentmethods/SamsungPayConfig;", "getSamsungPayConfig", "()Lch/datatrans/payment/paymentmethods/SamsungPayConfig;", "setSamsungPayConfig", "(Lch/datatrans/payment/paymentmethods/SamsungPayConfig;)V", "samsungPayConfig", "Lch/datatrans/payment/paymentmethods/KlarnaConfig;", "j", "Lch/datatrans/payment/paymentmethods/KlarnaConfig;", "getKlarnaConfig", "()Lch/datatrans/payment/paymentmethods/KlarnaConfig;", "setKlarnaConfig", "(Lch/datatrans/payment/paymentmethods/KlarnaConfig;)V", "klarnaConfig", "Lch/datatrans/payment/api/InitialLoader;", "k", "Lch/datatrans/payment/api/InitialLoader;", "getCustomInitialLoader", "()Lch/datatrans/payment/api/InitialLoader;", "setCustomInitialLoader", "(Lch/datatrans/payment/api/InitialLoader;)V", "customInitialLoader", "Lch/datatrans/payment/api/DCCShowMode;", "l", "Lch/datatrans/payment/api/DCCShowMode;", "getSavedCardDCCShowMode", "()Lch/datatrans/payment/api/DCCShowMode;", "setSavedCardDCCShowMode", "(Lch/datatrans/payment/api/DCCShowMode;)V", "savedCardDCCShowMode", "Companion", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TransactionOptions {
    public static final String ERROR_MISSING_OPTION = "%s payments have to be configured via the TransactionOptions.";

    /* renamed from: a, reason: from kotlin metadata */
    public String appCallbackScheme;

    /* renamed from: c, reason: from kotlin metadata */
    public boolean suppressCriticalErrorDialog;

    /* renamed from: d, reason: from kotlin metadata */
    public boolean isTesting;

    /* renamed from: e, reason: from kotlin metadata */
    public boolean useCertificatePinning;

    /* renamed from: g, reason: from kotlin metadata */
    public BoncardConfig boncardConfig;

    /* renamed from: h, reason: from kotlin metadata */
    public GooglePayConfig googlePayConfig;

    /* renamed from: i, reason: from kotlin metadata */
    public SamsungPayConfig samsungPayConfig;

    /* renamed from: j, reason: from kotlin metadata */
    public KlarnaConfig klarnaConfig;

    /* renamed from: k, reason: from kotlin metadata */
    public InitialLoader customInitialLoader;

    /* renamed from: b, reason: from kotlin metadata */
    public Map merchantProperties = MapsKt.emptyMap();

    /* renamed from: f, reason: from kotlin metadata */
    public CardLabelType cardLabelType = CardLabelType.CREDIT_OR_DEBIT_CARD;

    /* renamed from: l, reason: from kotlin metadata */
    public DCCShowMode savedCardDCCShowMode = DCCShowMode.ALWAYS;

    public final String getAppCallbackScheme() {
        return this.appCallbackScheme;
    }

    public final BoncardConfig getBoncardConfig() {
        return this.boncardConfig;
    }

    public final CardLabelType getCardLabelType() {
        return this.cardLabelType;
    }

    public final InitialLoader getCustomInitialLoader() {
        return this.customInitialLoader;
    }

    public final GooglePayConfig getGooglePayConfig() {
        return this.googlePayConfig;
    }

    public final KlarnaConfig getKlarnaConfig() {
        return this.klarnaConfig;
    }

    public final Map<String, String> getMerchantProperties() {
        return this.merchantProperties;
    }

    public final SamsungPayConfig getSamsungPayConfig() {
        return this.samsungPayConfig;
    }

    public final DCCShowMode getSavedCardDCCShowMode() {
        return this.savedCardDCCShowMode;
    }

    public final boolean getSuppressCriticalErrorDialog() {
        return this.suppressCriticalErrorDialog;
    }

    public final boolean getUseCertificatePinning() {
        return this.useCertificatePinning;
    }

    /* renamed from: isTesting, reason: from getter */
    public final boolean getIsTesting() {
        return this.isTesting;
    }

    public final void setAppCallbackScheme(String str) {
        this.appCallbackScheme = str;
    }

    public final void setBoncardConfig(BoncardConfig boncardConfig) {
        this.boncardConfig = boncardConfig;
    }

    public final void setCardLabelType(CardLabelType cardLabelType) {
        Intrinsics.checkNotNullParameter(cardLabelType, "<set-?>");
        this.cardLabelType = cardLabelType;
    }

    public final void setCustomInitialLoader(InitialLoader initialLoader) {
        this.customInitialLoader = initialLoader;
    }

    public final void setGooglePayConfig(GooglePayConfig googlePayConfig) {
        this.googlePayConfig = googlePayConfig;
    }

    public final void setKlarnaConfig(KlarnaConfig klarnaConfig) {
        this.klarnaConfig = klarnaConfig;
    }

    public final void setMerchantProperties(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.merchantProperties = map;
    }

    public final void setSamsungPayConfig(SamsungPayConfig samsungPayConfig) {
        this.samsungPayConfig = samsungPayConfig;
    }

    public final void setSavedCardDCCShowMode(DCCShowMode dCCShowMode) {
        Intrinsics.checkNotNullParameter(dCCShowMode, "<set-?>");
        this.savedCardDCCShowMode = dCCShowMode;
    }

    public final void setSuppressCriticalErrorDialog(boolean z) {
        this.suppressCriticalErrorDialog = z;
    }

    public final void setTesting(boolean z) {
        this.isTesting = z;
    }

    public final void setUseCertificatePinning(boolean z) {
        this.useCertificatePinning = z;
    }
}
