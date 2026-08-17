package ch.datatrans.payment;

/* renamed from: ch.datatrans.payment.he, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0156he {
    public final String a;
    public final String b;
    public final String c;

    public C0156he(String str, boolean z) {
        this.a = a(str, "cancelPage.jsp", z);
        this.b = a(str, "errorPage.jsp", z);
        this.c = a(str, "successPage.jsp", z);
    }

    public static String a(String str, String str2, boolean z) {
        if (str == null) {
            str = "https";
        }
        return str + "://" + (z ? "pay.sandbox" : "pay") + ".datatrans.com/upp/merchant/" + str2;
    }
}
