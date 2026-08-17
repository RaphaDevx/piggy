package ch.datatrans.payment;

import java.net.URL;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* renamed from: ch.datatrans.payment.h4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0153h4 implements E7 {
    public final String a;

    public C0153h4(boolean z) {
        this.a = z ? "https://%s.sandbox.datatrans.com/" : "https://%s.datatrans.com/";
    }

    @Override // ch.datatrans.payment.E7
    public final URL a() {
        return a("XML_AliasGateway.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final String b(boolean z) {
        String str = z ? "upp/payment/resume" : "upp/jsp/upStart.jsp";
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"pay"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        sb.append(format);
        return N7.a(sb, str, '?');
    }

    @Override // ch.datatrans.payment.E7
    public final URL c() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new URL(format.concat("v1/transactionGroups/mobile/binRange"));
    }

    @Override // ch.datatrans.payment.E7
    public final URL d() {
        return a("XML_processor.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final URL e() {
        return a("XML_authorize.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final URL f() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new URL(format.concat("v1/transactions/mobile/"));
    }

    @Override // ch.datatrans.payment.E7
    public final URL g() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new URL(format.concat("v1/tokenizations/"));
    }

    @Override // ch.datatrans.payment.E7
    public final URL h() {
        return a("XML_authorizeSplit.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final URL i() {
        return a("XML_DccService");
    }

    @Override // ch.datatrans.payment.E7
    public final URL j() {
        return a("XML_WalletTransactionService.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final String k() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"pay"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format.concat("upp/jsp/3D_submit_ACS.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final URL l() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new URL(format.concat("upp/services/v1/twintRegistration/monitorRegistration"));
    }

    @Override // ch.datatrans.payment.E7
    public final URL a(boolean z) {
        return a(z ? "XML_3DdirectoryRegistrations" : "XML_3DdirectoryAcs.jsp");
    }

    public final URL a(String str) {
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        sb.append(format);
        sb.append("upp/jsp/");
        sb.append(str);
        return new URL(sb.toString());
    }

    @Override // ch.datatrans.payment.E7
    public final URL b() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.a, Arrays.copyOf(new Object[]{"api"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new URL(format.concat("upp/services/v1/twintRegistration/startRegistration"));
    }
}
