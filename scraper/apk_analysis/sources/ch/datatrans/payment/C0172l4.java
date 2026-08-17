package ch.datatrans.payment;

import java.net.URL;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.l4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0172l4 implements E7 {
    public final String a;

    public C0172l4(String devHost) {
        Intrinsics.checkNotNullParameter(devHost, "devHost");
        this.a = devHost;
    }

    @Override // ch.datatrans.payment.E7
    public final URL a() {
        return a("XML_AliasGateway.jsp");
    }

    @Override // ch.datatrans.payment.E7
    public final String b(boolean z) {
        return this.a + (z ? "/upp/payment/resume" : "/upp/jsp/upStart.jsp") + '?';
    }

    @Override // ch.datatrans.payment.E7
    public final URL c() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a + "/upp/api/");
        sb.append("v1/transactionGroups/mobile/binRange");
        return new URL(sb.toString());
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.a + "/upp/api/");
        sb.append("v1/transactions/mobile/");
        return new URL(sb.toString());
    }

    @Override // ch.datatrans.payment.E7
    public final URL g() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a + "/upp/api/");
        sb.append("v1/tokenizations/");
        return new URL(sb.toString());
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
        return this.a + "/upp/jsp/3D_submit_ACS.jsp";
    }

    @Override // ch.datatrans.payment.E7
    public final URL l() {
        return new URL(this.a + "/upp/services/v1/twintRegistration/monitorRegistration");
    }

    @Override // ch.datatrans.payment.E7
    public final URL a(boolean z) {
        return a(z ? "XML_3DdirectoryRegistrations" : "XML_3DdirectoryAcs.jsp");
    }

    public final URL a(String str) {
        return new URL(this.a + "/upp/jsp/" + str);
    }

    @Override // ch.datatrans.payment.E7
    public final URL b() {
        return new URL(this.a + "/upp/services/v1/twintRegistration/startRegistration");
    }
}
