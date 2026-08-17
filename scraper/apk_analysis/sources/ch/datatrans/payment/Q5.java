package ch.datatrans.payment;

import ch.datatrans.payment.api.DCCShowMode;
import ch.datatrans.payment.api.InitialLoader;
import ch.datatrans.payment.paymentmethods.CardLabelType;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.KlarnaConfig;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import ch.datatrans.payment.paymentmethods.boncard.BoncardConfig;
import java.util.LinkedHashMap;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Q5 {
    public DCCShowMode A;
    public String B;
    public jg C;
    public boolean D;
    public String a;
    public BoncardConfig b;
    public C0195p0 c;
    public K3 d;
    public String e;
    public String f;
    public String g;
    public CardLabelType h = CardLabelType.CREDIT_OR_DEBIT_CARD;
    public GooglePayConfig i;
    public String j;
    public final LinkedHashMap k;
    public boolean l;
    public String m;
    public X9 n;
    public SamsungPayConfig o;
    public KlarnaConfig p;
    public a9 q;
    public boolean r;
    public boolean s;
    public tb t;
    public x6 u;
    public boolean v;
    public boolean w;
    public V8 x;
    public InitialLoader y;
    public C0113ae z;

    public Q5() {
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        this.j = language;
        this.k = new LinkedHashMap();
        this.n = X9.c;
        this.A = DCCShowMode.ALWAYS;
    }
}
