package ch.datatrans.payment.api.tokenization;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.api.InitialLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u0013\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0015\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\"\u0010\u001a\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0012R$\u0010\"\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010*\u001a\u00020#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPTokenizationOptions;", "", "<init>", "()V", "", "a", "Ljava/lang/String;", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "language", "", "b", "Z", "getSuppressCriticalErrorDialog", "()Z", "setSuppressCriticalErrorDialog", "(Z)V", "suppressCriticalErrorDialog", "c", "isTesting", "setTesting", "d", "getUseCertificatePinning", "setUseCertificatePinning", "useCertificatePinning", "Lch/datatrans/payment/api/InitialLoader;", "e", "Lch/datatrans/payment/api/InitialLoader;", "getCustomInitialLoader", "()Lch/datatrans/payment/api/InitialLoader;", "setCustomInitialLoader", "(Lch/datatrans/payment/api/InitialLoader;)V", "customInitialLoader", "Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "f", "Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "getCardholder", "()Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "setCardholder", "(Lch/datatrans/payment/api/tokenization/PCIPCardholder;)V", "cardholder", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PCIPTokenizationOptions {

    /* renamed from: a, reason: from kotlin metadata */
    public String language;

    /* renamed from: b, reason: from kotlin metadata */
    public boolean suppressCriticalErrorDialog;

    /* renamed from: c, reason: from kotlin metadata */
    public boolean isTesting;

    /* renamed from: d, reason: from kotlin metadata */
    public boolean useCertificatePinning;

    /* renamed from: e, reason: from kotlin metadata */
    public InitialLoader customInitialLoader;

    /* renamed from: f, reason: from kotlin metadata */
    public PCIPCardholder cardholder = new PCIPCardholder(null, 1, null);

    public final PCIPCardholder getCardholder() {
        return this.cardholder;
    }

    public final InitialLoader getCustomInitialLoader() {
        return this.customInitialLoader;
    }

    public final String getLanguage() {
        return this.language;
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

    public final void setCardholder(PCIPCardholder pCIPCardholder) {
        Intrinsics.checkNotNullParameter(pCIPCardholder, "<set-?>");
        this.cardholder = pCIPCardholder;
    }

    public final void setCustomInitialLoader(InitialLoader initialLoader) {
        this.customInitialLoader = initialLoader;
    }

    public final void setLanguage(String str) {
        this.language = str;
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
