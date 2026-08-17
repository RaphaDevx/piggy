package ch.datatrans.payment;

import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.paymentmethods.AuthorizationInitRequestConfig;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedCard;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import ch.datatrans.payment.paymentmethods.SavedSEPA;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryLogEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes3.dex */
public abstract class Lf {
    public static final byte[] a = {-118, -64, 103, -45, -87, -51, -127, 126, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 10, 77, -19, 97, -59, -117, -82, 86, 51, -44, -95, 59, -43, -50, -81, -101, 99, 120, 35, 17, 110, 26, 119, 125, 12, 125, 113, -90, -59, -86, -10, 112, -112, 104, -92, 47, -16, 58, 72, 55, -126, -14, 98, 106, 106, -115, 34, -82, 106, 125, 44, -6, 90, -29};

    public static final Unit a(G0 g0, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "uppTransactionId", g0.a, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final Y8 y8, final G0 g0, final SavedPaymentMethod savedPaymentMethod, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", y8.a)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(Y8.this, g0, savedPaymentMethod, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit c(final H h, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", h.c)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.d(H.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit d(final X8 x8, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.e(X8.this, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit e(H h, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        String str = h.d;
        if (str != null) {
            Ge.a(element, "refno2", str, 12);
        }
        String str2 = h.e;
        if (str2 != null) {
            Ge.a(element, "refno3", str2, 12);
        }
        element.a(h.f);
        Ge.a(element, "currency", h.g, 12);
        Ge.a(element, "uppTransactionId", h.a, 12);
        Ge.a(element, "reqtype", "DOA", 12);
        a(map, element);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final G0 authorizationRequest) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(authorizationRequest, "authorizationRequest");
        final Y8 y8 = authorizationRequest.l;
        final SavedPaymentMethod savedPaymentMethod = authorizationRequest.m;
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(Y8.this, authorizationRequest, savedPaymentMethod, metaInformation, (Ge) obj);
            }
        });
    }

    public static final Unit a(Q5 q5, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        K3 k3 = q5.d;
        if (k3 != null) {
            a(k3.a(), element);
        }
        a9 a9Var = q5.q;
        if (a9Var != null) {
            a(a9Var.a(), element);
        }
        x6 x6Var = q5.u;
        if (x6Var != null) {
            a(x6Var.a, element);
        }
        return Unit.INSTANCE;
    }

    public static final Unit d(final W8 w8, final SavedPaymentMethod savedPaymentMethod, final Ref.ObjectRef objectRef, final Ref.ObjectRef objectRef2, final Map map, final d dVar, final String str, final L3 l3, final Ref.ObjectRef objectRef3, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.e(W8.this, savedPaymentMethod, objectRef, objectRef2, map, dVar, str, l3, objectRef3, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit e(X8 x8, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        String str = x8.a;
        Intrinsics.checkNotNull(str);
        Ge.a(element, "uppTransactionId", str, 12);
        a(map, element);
        b(x8.k, element);
        Ge.a(element, "reqtype", x8.f.a, 12);
        String str2 = x8.h;
        if (str2 != null) {
            Ge.a(element, "acqAuthorizationCode", str2, 12);
        }
        a(element, x8.d, x8.e);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final String merchantId, final Card card, final Map merchantProperties) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        final String a2 = x4.a(Ua.a(a, merchantId + card.getNumber()));
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(merchantId, card, a2, metaInformation, merchantProperties, (Ge) obj);
            }
        });
    }

    public static final Unit c(final Y8 y8, final G0 g0, final SavedPaymentMethod savedPaymentMethod, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", y8.d)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(G0.this, y8, savedPaymentMethod, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(final String str, final Card card, final String str2, final Map map, final Map map2, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "aliasCCService", MapsKt.mapOf(TuplesKt.to("version", 1)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(str, card, str2, map, map2, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final Y8 y8, final PaymentMethodType paymentMethodType, final Map map, final Q5 q5, final Object obj, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", y8.a)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Lf.c(Y8.this, paymentMethodType, map, q5, obj, (Ge) obj2);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit e(C0142fe c0142fe, PaymentMethodType paymentMethodType, C0130de c0130de, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "refno", c0142fe.d, 12);
        int i = ug.a[paymentMethodType.ordinal()];
        if (i == 1) {
            Ge.a(element, "googlePayData", "<![CDATA[" + c0130de.a + "]]>", 8);
            element.a(c0142fe.a);
            Ge.a(element, "currency", c0142fe.b, 12);
            String str = c0130de.b;
            Intrinsics.checkNotNull(str);
            Ge.a(element, "cardDetails", str, 12);
        } else if (i == 2) {
            Ge.a(element, "samsungPayData", c0130de.a, 12);
        }
        a(element, c0142fe.e, c0142fe.f);
        a(map, element);
        return Unit.INSTANCE;
    }

    public static final Unit a(final Card card, final String str, final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SavedPaymentMethod.ALIAS_KEY, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(Card.this, str, map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit c(final Y8 y8, final PaymentMethodType paymentMethodType, final Map map, final Q5 q5, final Object obj, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", y8.d)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Lf.d(Y8.this, paymentMethodType, map, q5, obj, (Ge) obj2);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final String merchantId, final String paymentMethodType, final String str, final String str2) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(merchantId, paymentMethodType, str, str2, metaInformation, (Ge) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit e(W8 w8, SavedPaymentMethod savedPaymentMethod, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Map map, final d dVar, String str, L3 l3, Ref.ObjectRef objectRef3, Ge element) {
        String str2;
        Intrinsics.checkNotNullParameter(element, "$this$element");
        element.a(w8.a);
        Ge.a(element, "currency", w8.b, 12);
        Ge.a(element, "aliasCC", savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), 12);
        Ge.a(element, "expm", (String) objectRef.element, 12);
        Ge.a(element, "expy", (String) objectRef2.element, 12);
        Ge.a(element, "pmethod", savedPaymentMethod.getType().getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), 12);
        a(map, element);
        b(dVar.k, element);
        Ge.a(element, "reqtype", dVar.b.a, 12);
        String str3 = dVar.e;
        if (str3 != null) {
            Ge.a(element, "cvv", str3, 12);
        }
        String str4 = dVar.f;
        if (str4 != null) {
            Ge.a(element, "uppFraudSessionId", str4, 12);
        }
        C0195p0 c0195p0 = dVar.g;
        if (c0195p0 != null) {
            a(c0195p0.a(), element);
        }
        String str5 = dVar.h;
        if (str5 != null) {
            Ge.a(element, "easyPaymentInfo", str5, 12);
        }
        if (dVar.a != null) {
            Ge.a(element, "parameters_3d", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Lf.a(d.this, (Ge) obj);
                }
            }, 14);
        }
        if (str != null) {
            Ge.a(element, SavedSEPA.BANK_CODE_KEY, str, 12);
        }
        if (l3 != null && (str2 = l3.a) != null) {
            Ge.a(element, "dccId", str2, 12);
        }
        if (savedPaymentMethod.getType() == PaymentMethodType.KLARNA) {
            Ge.a(element, "uppCustomerDetails", MapsKt.mapOf(TuplesKt.to("storeDetails", BooleanUtils.YES)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Lf.b(d.this, (Ge) obj);
                }
            }, 12);
        } else {
            final String str6 = (String) objectRef3.element;
            if (str6 != null) {
                Ge.a(element, "uppCustomerDetails", MapsKt.mapOf(TuplesKt.to("storeDetails", BooleanUtils.YES)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Lf.a(str6, (Ge) obj);
                    }
                }, 12);
            }
        }
        a(element, w8.e, w8.f);
        return Unit.INSTANCE;
    }

    public static final Unit a(final String str, final String str2, final String str3, final String str4, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "dccService", MapsKt.mapOf(TuplesKt.to("version", 2)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(str, str2, str3, str4, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(final String str, final String str2, final String str3, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(str, str2, str3, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit d(final Y8 y8, final PaymentMethodType paymentMethodType, final Map map, final Q5 q5, final Object obj, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Lf.e(Y8.this, paymentMethodType, map, q5, obj, (Ge) obj2);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final X8 authorizationRequest) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(authorizationRequest, "authorizationRequest");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(X8.this, metaInformation, (Ge) obj);
            }
        });
    }

    public static final Unit a(final X8 x8, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "authorizationSplit", MapsKt.mapOf(TuplesKt.to("version", 4)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(X8.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final H h, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", h.b)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(H.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.String] */
    public static String a(final Map metaInformation, final d authorizationRequest) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(authorizationRequest, "authorizationRequest");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = "";
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        final W8 w8 = authorizationRequest.l;
        final SavedPaymentMethod savedPaymentMethod = authorizationRequest.m;
        if (savedPaymentMethod instanceof SavedCard) {
            SavedCard savedCard = (SavedCard) savedPaymentMethod;
            CardExpiryDate cardExpiryDate = savedCard.getCardExpiryDate();
            if (cardExpiryDate != null) {
                objectRef.element = cardExpiryDate.getFormattedMonth();
                objectRef2.element = cardExpiryDate.getFormattedYear();
            }
            objectRef3.element = savedCard.getCardholder();
        }
        SavedSEPA savedSEPA = savedPaymentMethod instanceof SavedSEPA ? (SavedSEPA) savedPaymentMethod : null;
        final String bankCode = savedSEPA != null ? savedSEPA.getBankCode() : null;
        final L3 l3 = authorizationRequest.n;
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(W8.this, savedPaymentMethod, objectRef, objectRef2, metaInformation, authorizationRequest, bankCode, l3, objectRef3, (Ge) obj);
            }
        });
    }

    public static final Unit c(final W8 w8, final SavedPaymentMethod savedPaymentMethod, final Ref.ObjectRef objectRef, final Ref.ObjectRef objectRef2, final Map map, final d dVar, final String str, final L3 l3, final Ref.ObjectRef objectRef3, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", w8.d)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.d(W8.this, savedPaymentMethod, objectRef, objectRef2, map, dVar, str, l3, objectRef3, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit e(Y8 y8, PaymentMethodType paymentMethodType, Map map, final Q5 q5, Object obj, Ge element) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "sub_pmethod", "INIT", 12);
        element.a(y8.b);
        Ge.a(element, "currency", y8.c, 12);
        Ge.a(element, "pmethod", paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), 12);
        a(map, element);
        b(q5.k, element);
        Ge.a(element, "reqtype", q5.n.a, 12);
        if (q5.l) {
            Intrinsics.checkNotNullParameter("useAlias", "name");
            Ge.a(element, "useAlias", null, String.valueOf(true), null, 24);
        }
        if (paymentMethodType == PaymentMethodType.PAY_PAL && q5.l) {
            Ge.a(element, "PayPalBillingAgreementId", "get", 12);
        }
        if (paymentMethodType == PaymentMethodType.KLARNA) {
            Ge.a(element, "uppCustomerDetails", MapsKt.mapOf(TuplesKt.to("storeDetails", BooleanUtils.YES)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda57
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Lf.a(Q5.this, (Ge) obj2);
                }
            }, 12);
        }
        if (paymentMethodType == PaymentMethodType.VIPPS) {
            C0127ce c0127ce = obj instanceof C0127ce ? (C0127ce) obj : null;
            if (c0127ce != null) {
                boolean z = c0127ce.b;
                Intrinsics.checkNotNullParameter("isAppInstalled", "name");
                Ge.a(element, "isAppInstalled", null, String.valueOf(z), null, 24);
                Ge.a(element, "appCallbackUrl", c0127ce.a, 12);
            }
            C0113ae c0113ae = q5.z;
            if (c0113ae != null && (str2 = c0113ae.a) != null) {
                Ge.a(element, "paymentText", str2, 12);
            }
            C0113ae c0113ae2 = q5.z;
            if (c0113ae2 != null && (str = c0113ae2.b) != null) {
                Ge.a(element, "customerMobileNumber", str, 12);
            }
        } else if (paymentMethodType == PaymentMethodType.POST_FINANCE_PAY) {
            kg kgVar = obj instanceof kg ? (kg) obj : null;
            if (kgVar != null) {
                Intrinsics.checkNotNullParameter("isAppInstalled", "name");
                Ge.a(element, "isAppInstalled", null, String.valueOf(true), null, 24);
                String str3 = kgVar.a;
                if (str3 != null) {
                    Ge.a(element, "appCallbackUrl", str3, 12);
                }
            }
            String str4 = y8.g;
            if (str4 != null) {
                Ge.a(element, "aliasCC", str4, 12);
            }
        }
        AuthorizationInitRequestConfig authorizationInitRequestConfig = obj instanceof AuthorizationInitRequestConfig ? (AuthorizationInitRequestConfig) obj : null;
        if (authorizationInitRequestConfig != null) {
            Ge.a(element, "appCallbackUrl", authorizationInitRequestConfig.getAppCallbackUrl(), 12);
        }
        a(element, y8.e, y8.f);
        return Unit.INSTANCE;
    }

    public static final Unit b(final C0142fe c0142fe, final PaymentMethodType paymentMethodType, final C0130de c0130de, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", c0142fe.c)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(C0142fe.this, paymentMethodType, c0130de, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(final W8 w8, final SavedPaymentMethod savedPaymentMethod, final Ref.ObjectRef objectRef, final Ref.ObjectRef objectRef2, final Map map, final d dVar, final String str, final L3 l3, final Ref.ObjectRef objectRef3, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "authorizationService", MapsKt.mapOf(TuplesKt.to("version", 5)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(W8.this, savedPaymentMethod, objectRef, objectRef2, map, dVar, str, l3, objectRef3, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit d(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, final String str, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.e(W8.this, paymentMethodType, q5, str, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit b(C0131e c0131e, Map map, Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "merchantId", c0131e.a, 12);
        Ge.a(element, "currency", "CHF", 12);
        Ge.a(element, "refno", c0131e.c, 12);
        a(element, c0131e.d, c0131e.e);
        a(map, element);
        b(map2, element);
        return Unit.INSTANCE;
    }

    public static final Unit d(final C0142fe c0142fe, final PaymentMethodType paymentMethodType, final C0130de c0130de, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.e(C0142fe.this, paymentMethodType, c0130de, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit b(C0131e c0131e, String str, Map map, Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "merchantId", c0131e.a, 12);
        Ge.a(element, "currency", "CHF", 12);
        Ge.a(element, "transactionId", str, 12);
        a(map, element);
        b(map2, element);
        return Unit.INSTANCE;
    }

    public static final Unit b(final W8 w8, final SavedPaymentMethod savedPaymentMethod, final Ref.ObjectRef objectRef, final Ref.ObjectRef objectRef2, final Map map, final d dVar, final String str, final L3 l3, final Ref.ObjectRef objectRef3, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", w8.c)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(W8.this, savedPaymentMethod, objectRef, objectRef2, map, dVar, str, l3, objectRef3, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit d(final H h, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.e(H.this, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit c(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, final String str, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", w8.d)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.d(W8.this, paymentMethodType, q5, str, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(d dVar, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "ACS", "Datatrans", 12);
        Ge.a(element, "uppTransactionId", dVar.a, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(String str, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "uppCustomerName", str, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(final Y8 y8, final G0 g0, final SavedPaymentMethod savedPaymentMethod, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "authorizationService", MapsKt.mapOf(TuplesKt.to("version", 5)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(Y8.this, g0, savedPaymentMethod, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit c(final X8 x8, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", MapsKt.mapOf(TuplesKt.to("refno", x8.c)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.d(X8.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit c(Card card, String str, Map map, Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "cardno", card.getNumber(), 12);
        Ge.a(element, "expm", card.getExpiryDate().getFormattedMonth(), 12);
        Ge.a(element, "expy", card.getExpiryDate().getFormattedYear(), 12);
        Ge.a(element, "iSign", str, 12);
        a(map, element);
        b(map2, element);
        return Unit.INSTANCE;
    }

    public static final Unit c(final C0142fe c0142fe, final PaymentMethodType paymentMethodType, final C0130de c0130de, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "walletTransaction", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.d(C0142fe.this, paymentMethodType, c0130de, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit a(final G0 g0, final Y8 y8, final SavedPaymentMethod savedPaymentMethod, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(G0.this, y8, savedPaymentMethod, map, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit e(W8 w8, PaymentMethodType paymentMethodType, Q5 q5, String str, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "sub_pmethod", "INIT", 12);
        element.a(w8.a);
        Ge.a(element, "currency", w8.b, 12);
        Ge.a(element, "pmethod", paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), 12);
        Ge.a(element, "reqtype", q5.n.a, 12);
        if (str != null) {
            Ge.a(element, "aliasCC", str, 12);
        }
        a(map, element);
        b(q5.k, element);
        a(element, w8.e, w8.f);
        return Unit.INSTANCE;
    }

    public static final Unit b(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, final String str, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", w8.c)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(W8.this, paymentMethodType, q5, str, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit c(Map map, Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        a(map, element);
        a(map2, element);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final Y8 paymentData, final PaymentMethodType paymentMethod, final Q5 options, final Object obj) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(paymentData, "paymentData");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        Intrinsics.checkNotNullParameter(options, "options");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Lf.a(Y8.this, paymentMethod, metaInformation, options, obj, (Ge) obj2);
            }
        });
    }

    public static final Unit a(final Y8 y8, final PaymentMethodType paymentMethodType, final Map map, final Q5 q5, final Object obj, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "authorizationService", MapsKt.mapOf(TuplesKt.to("version", 5)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Lf.b(Y8.this, paymentMethodType, map, q5, obj, (Ge) obj2);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit c(Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Ge.a(element, "parameter", MapsKt.mapOf(TuplesKt.to("name", str)), (String) entry.getValue(), null, 24);
        }
        return Unit.INSTANCE;
    }

    public static final Unit b(final X8 x8, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", x8.b)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(X8.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final G0 g0, Y8 y8, SavedPaymentMethod savedPaymentMethod, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "reqtype", g0.n.a, 12);
        element.a(y8.b);
        Ge.a(element, "currency", y8.c, 12);
        if (savedPaymentMethod != null) {
            Ge.a(element, "aliasCC", savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), 12);
            Ge.a(element, "pmethod", savedPaymentMethod.getType().getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), 12);
        } else {
            Ge.a(element, "pmethod", g0.p.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), 12);
        }
        if (g0.a != null) {
            Ge.a(element, "parameters_3d", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda55
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Lf.a(G0.this, (Ge) obj);
                }
            }, 14);
        }
        a(map, element);
        b(g0.o, element);
        a(element, y8.e, y8.f);
        return Unit.INSTANCE;
    }

    public static final Unit b(d dVar, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        K3 k3 = dVar.d;
        if (k3 != null) {
            a(k3.a(), element);
        }
        a9 a9Var = dVar.j;
        if (a9Var != null) {
            a(a9Var.a(), element);
        }
        x6 x6Var = dVar.i;
        if (x6Var != null) {
            a(x6Var.a, element);
        }
        return Unit.INSTANCE;
    }

    public static final Unit b(String str, final Card card, final String str2, final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", str)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(Card.this, str2, map, map2, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final W8 payment, final PaymentMethodType paymentMethod, final Q5 options, final String str) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        Intrinsics.checkNotNullParameter(options, "options");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(W8.this, paymentMethod, options, str, metaInformation, (Ge) obj);
            }
        });
    }

    public static final Unit a(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, final String str, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "authorizationService", MapsKt.mapOf(TuplesKt.to("version", 5)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(W8.this, paymentMethodType, q5, str, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final Card card, final String str, final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(Card.this, str, map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit b(String str, final String str2, final String str3, final String str4, final Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", str)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(str2, str3, str4, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final C0131e aliasRequest, final Map merchantProperties) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(C0131e.this, metaInformation, merchantProperties, (Ge) obj);
            }
        });
    }

    public static final Unit a(final C0131e c0131e, final Map map, final Map map2, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "StartRegistrationRequest", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(C0131e.this, map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit b(String str, String str2, String str3, Map map, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "pmethod", str, 12);
        if (str2 != null) {
            Ge.a(element, "cardno", str2, 12);
        }
        if (str3 != null) {
            Ge.a(element, "aliasCC", str3, 12);
        }
        a(map, element);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final C0131e aliasRequest, final String transactionId, final Map merchantProperties) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(C0131e.this, transactionId, metaInformation, merchantProperties, (Ge) obj);
            }
        });
    }

    public static final Unit a(final C0131e c0131e, final String str, final Map map, final Map map2, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "MonitorRegistrationRequest", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(C0131e.this, str, map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static final Unit b(String str, final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryLogEvent.JsonKeys.BODY, MapsKt.mapOf(TuplesKt.to("merchantId", str)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(map, map2, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static String a(final Map metaInformation, final C0130de walletInfo, final C0142fe transactionInfo, final PaymentMethodType paymentMethod) {
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        Intrinsics.checkNotNullParameter(walletInfo, "walletInfo");
        Intrinsics.checkNotNullParameter(transactionInfo, "transactionInfo");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(C0142fe.this, paymentMethod, walletInfo, metaInformation, (Ge) obj);
            }
        });
    }

    public static final Unit a(final C0142fe c0142fe, final PaymentMethodType paymentMethodType, final C0130de c0130de, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "walletTransactionService", MapsKt.mapOf(TuplesKt.to("version", 3)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(C0142fe.this, paymentMethodType, c0130de, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit b(final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, SentryBaseEvent.JsonKeys.REQUEST, null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static void b(final Map map, Ge ge) {
        if (map.isEmpty()) {
            return;
        }
        Ge.a(ge, "merchantParameters", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.c(map, (Ge) obj);
            }
        }, 14);
    }

    public static String a(final H cancelServiceRequest, final Map metaInformation) {
        Intrinsics.checkNotNullParameter(cancelServiceRequest, "cancelServiceRequest");
        Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(H.this, metaInformation, (Ge) obj);
            }
        });
    }

    public static final Unit a(final H h, final Map map, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "paymentService", MapsKt.mapOf(TuplesKt.to("version", "1")), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(H.this, map, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static String a(final Map map, final String str, final Map map2) {
        return Fe.a(new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.a(str, map2, map, (Ge) obj);
            }
        });
    }

    public static final Unit a(final String str, final Map map, final Map map2, Ge buildXML) {
        Intrinsics.checkNotNullParameter(buildXML, "$this$buildXML");
        Ge.a(buildXML, "directory3dService", MapsKt.mapOf(TuplesKt.to("version", 1)), null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(str, map, map2, (Ge) obj);
            }
        }, 12);
        return Unit.INSTANCE;
    }

    public static final Unit a(final Map map, final Map map2, Ge element) {
        Intrinsics.checkNotNullParameter(element, "$this$element");
        Ge.a(element, "transaction", null, null, new Function1() { // from class: ch.datatrans.payment.Lf$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Lf.b(map, map2, (Ge) obj);
            }
        }, 14);
        return Unit.INSTANCE;
    }

    public static void a(String str) {
        String a2 = He.a(str, "errorCode", true);
        String a3 = He.a(str, "acqErrorCode", true);
        String a4 = He.a(str, "errorMessage", true);
        String a5 = He.a(str, "errorDetail", true);
        String a6 = He.a(str, "uppTransactionId", true);
        if (a2 == null && a4 == null && a5 == null) {
            return;
        }
        int parseInt = a2 != null ? Integer.parseInt(a2) : -1;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (a3 != null) {
            linkedHashMap.put("acqErrorCode", a3);
        }
        throw new BackendException(a4, a5, parseInt, a6, linkedHashMap);
    }

    public static void a(Ge ge, String str, String str2) {
        if (str != null) {
            Ge.a(ge, "refno2", str, 12);
        }
        if (str2 != null) {
            Ge.a(ge, "refno3", str2, 12);
        }
    }

    public static void a(Map map, Ge ge) {
        for (Map.Entry entry : map.entrySet()) {
            Ge.a(ge, (String) entry.getKey(), (String) entry.getValue(), 12);
        }
    }
}
