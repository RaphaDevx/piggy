package ch.datatrans.payment.paymentmethods;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.Ae;
import ch.datatrans.payment.Be;
import ch.datatrans.payment.C0185n5;
import ch.datatrans.payment.C0230u5;
import ch.datatrans.payment.I9;
import ch.datatrans.payment.InterfaceC0235ud;
import ch.datatrans.payment.M7;
import ch.datatrans.payment.R;
import ch.datatrans.payment.R5;
import ch.datatrans.payment.Z9;
import ch.datatrans.payment.d9;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.ranges.RangesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b8\b\u0086\u0081\u0002\u0018\u0000 Q2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002QRJ\u000f\u0010\u0005\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0016\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR$\u0010#\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001e\u0018\u00010\u001d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001c\u0010(\u001a\u0004\u0018\u00010\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010,\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010\u0004R\u001a\u0010/\u001a\u00020\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b-\u0010\u0019\u001a\u0004\b.\u0010\u001bR\u001a\u00102\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b0\u0010\u0013\u001a\u0004\b1\u0010\u0015R\u001a\u00105\u001a\u00020\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b3\u0010\u0019\u001a\u0004\b4\u0010\u001bR\"\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u001a\u0010>\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b<\u0010*\u001a\u0004\b=\u0010\u0004R\u001a\u0010D\u001a\u00020?8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0014\u0010F\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u0004R\u0014\u0010H\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0004R\u0014\u0010J\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010\u0004R\u0014\u0010L\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0004R\u0014\u0010N\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010\u0004R\u0014\u0010P\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\u0004j\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bv¨\u0006w"}, d2 = {"Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "", "", "hasAliasSupport$lib_release", "()Z", "hasAliasSupport", "", "appCallbackScheme", "generateAppCallbackUrl$lib_release", "(Ljava/lang/String;)Ljava/lang/String;", "generateAppCallbackUrl", "Lch/datatrans/payment/Be;", "webProcessListener", "url", "Lch/datatrans/payment/Ae;", "getWebProcess$lib_release", "(Lch/datatrans/payment/Be;Ljava/lang/String;Ljava/lang/String;)Lch/datatrans/payment/Ae;", "getWebProcess", "a", "Ljava/lang/String;", "getIdentifier", "()Ljava/lang/String;", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "b", "I", "getLogo$lib_release", "()I", "logo", "Ljava/lang/Class;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "c", "Ljava/lang/Class;", "getSavedPaymentMethodClass$lib_release", "()Ljava/lang/Class;", "savedPaymentMethodClass", "d", "Ljava/lang/Boolean;", "getHasSavedPaymentMethodSupport$lib_release", "()Ljava/lang/Boolean;", "hasSavedPaymentMethodSupport", "e", "Z", "getUsesFakeAlias$lib_release", "usesFakeAlias", "f", "getViewPortWidth$lib_release", "viewPortWidth", "g", "getViewPortAdjustment$lib_release", "viewPortAdjustment", "h", "getCvvLength$lib_release", "cvvLength", "Lkotlin/Function0;", "i", "Lkotlin/jvm/functions/Function0;", "isModuleAvailable$lib_release", "()Lkotlin/jvm/functions/Function0;", "isModuleAvailable", "j", "isCvvOptional$lib_release", "isCvvOptional", "Lch/datatrans/payment/ud;", "k", "Lch/datatrans/payment/ud;", "getTitle$lib_release", "()Lch/datatrans/payment/ud;", "title", "isCreditCard$lib_release", "isCreditCard", "getRequiresExternalWebProcess$lib_release", "requiresExternalWebProcess", "getRequiresAppCallbackScheme$lib_release", "requiresAppCallbackScheme", "getRequiresGooglePayConfig$lib_release", "requiresGooglePayConfig", "getRequiresSamsungPayConfig$lib_release", "requiresSamsungPayConfig", "getHasCvv$lib_release", "hasCvv", "Companion", "PaymentMethodTypeSerializer", "MAESTRO", "UNION_PAY", "VISA", "MASTER_CARD", "DINERS_CLUB", "AMERICAN_EXPRESS", "JCB", "UATP", "DISCOVER", "SUPERCARD", "ELO_CARD", "HIPERCARD", "DANKORT", "BONCARD", "POST_FINANCE_CARD", "POST_FINANCE_EFINANCE", "PAY_PAL", "EASYPAY", "SEPA", "SWISS_BILLING", "IDEAL", "TWINT", "GOOGLE_PAY", "SAMSUNG_PAY", "REKA", "CEMBRA_PAY", "SWISS_PASS", "POWERPAY", "PAYCARD", "PAYSAFECARD", "KLARNA", "SWISH", "VIPPS", "POST_FINANCE_PAY", "MOBILE_PAY", "HALF_FARE_PLUS", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PaymentMethodType {
    public static final PaymentMethodType AMERICAN_EXPRESS;
    public static final PaymentMethodType BONCARD;
    public static final PaymentMethodType CEMBRA_PAY;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final PaymentMethodType DANKORT;
    public static final PaymentMethodType DINERS_CLUB;
    public static final PaymentMethodType DISCOVER;
    public static final PaymentMethodType EASYPAY;
    public static final PaymentMethodType ELO_CARD;
    public static final PaymentMethodType GOOGLE_PAY;
    public static final PaymentMethodType HALF_FARE_PLUS;
    public static final PaymentMethodType HIPERCARD;
    public static final PaymentMethodType IDEAL;
    public static final PaymentMethodType JCB;
    public static final PaymentMethodType KLARNA;
    public static final PaymentMethodType MAESTRO;
    public static final PaymentMethodType MASTER_CARD;
    public static final PaymentMethodType MOBILE_PAY;
    public static final PaymentMethodType PAYCARD;
    public static final String PAYMENT_METHOD_KEY = "paymentMethod";
    public static final PaymentMethodType PAYSAFECARD;
    public static final PaymentMethodType PAY_PAL;
    public static final PaymentMethodType POST_FINANCE_CARD;
    public static final PaymentMethodType POST_FINANCE_EFINANCE;
    public static final PaymentMethodType POST_FINANCE_PAY;
    public static final PaymentMethodType POWERPAY;
    public static final PaymentMethodType REKA;
    public static final PaymentMethodType SAMSUNG_PAY;
    public static final PaymentMethodType SEPA;
    public static final PaymentMethodType SUPERCARD;
    public static final PaymentMethodType SWISH;
    public static final PaymentMethodType SWISS_BILLING;
    public static final PaymentMethodType SWISS_PASS;
    public static final PaymentMethodType TWINT;
    public static final PaymentMethodType UATP;
    public static final PaymentMethodType UNION_PAY;
    public static final PaymentMethodType VIPPS;
    public static final PaymentMethodType VISA;
    public static final Map m;
    public static final /* synthetic */ PaymentMethodType[] n;
    public static final /* synthetic */ EnumEntries o;

    /* renamed from: a, reason: from kotlin metadata */
    public final String identifier;

    /* renamed from: b, reason: from kotlin metadata */
    public final int logo;

    /* renamed from: c, reason: from kotlin metadata */
    public final Class savedPaymentMethodClass;

    /* renamed from: d, reason: from kotlin metadata */
    public final Boolean hasSavedPaymentMethodSupport;

    /* renamed from: e, reason: from kotlin metadata */
    public final boolean usesFakeAlias;

    /* renamed from: f, reason: from kotlin metadata */
    public final int viewPortWidth;

    /* renamed from: g, reason: from kotlin metadata */
    public final String viewPortAdjustment;

    /* renamed from: h, reason: from kotlin metadata */
    public final int cvvLength;

    /* renamed from: i, reason: from kotlin metadata */
    public final Function0 isModuleAvailable;

    /* renamed from: j, reason: from kotlin metadata */
    public final boolean isCvvOptional;
    public final Z9 k;
    public final EnumSet l;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lch/datatrans/payment/paymentmethods/PaymentMethodType$Companion;", "", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "fromIdentifier", "(Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "PAYMENT_METHOD_KEY", "Ljava/lang/String;", "", "typesByIdentifier", "Ljava/util/Map;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final PaymentMethodType fromIdentifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            PaymentMethodType paymentMethodType = (PaymentMethodType) PaymentMethodType.m.get(identifier);
            if (paymentMethodType == null) {
                Log.e("DTPL", "Invalid payment method identifier: " + identifier);
            }
            return paymentMethodType;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/PaymentMethodType$PaymentMethodTypeSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "serialize", "paymentMethodType", "typeOfSrc", "Lcom/google/gson/JsonSerializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class PaymentMethodTypeSerializer implements JsonSerializer<PaymentMethodType>, JsonDeserializer<PaymentMethodType> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public PaymentMethodType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            String asString;
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = json.getAsJsonObject();
            if (asJsonObject.has("paymentmethod") && asJsonObject.get("paymentmethod").isJsonArray()) {
                asString = asJsonObject.get("paymentmethod").getAsJsonArray().get(0).getAsString();
            } else if (asJsonObject.has("pmethod")) {
                asString = asJsonObject.get("pmethod").getAsString();
            } else {
                if (!asJsonObject.has(PaymentMethodType.PAYMENT_METHOD_KEY)) {
                    throw new IllegalArgumentException(R5.a(PaymentMethodType.PAYMENT_METHOD_KEY).toString());
                }
                asString = asJsonObject.get(PaymentMethodType.PAYMENT_METHOD_KEY).getAsString();
            }
            Companion companion = PaymentMethodType.INSTANCE;
            Intrinsics.checkNotNull(asString);
            return companion.fromIdentifier(asString);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(PaymentMethodType paymentMethodType, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonElement serialize = context.serialize(paymentMethodType.getIdentifier());
            Intrinsics.checkNotNullExpressionValue(serialize, "serialize(...)");
            return serialize;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PaymentMethodType.values().length];
            try {
                iArr[PaymentMethodType.IDEAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        int i = R.string.datatrans_sdk_payment_method_maestro;
        int i2 = R.drawable.dtpl_payment_method_mau;
        d9 d9Var = d9.b;
        PaymentMethodType paymentMethodType = new PaymentMethodType("MAESTRO", 0, "MAU", i, i2, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 1200);
        MAESTRO = paymentMethodType;
        PaymentMethodType paymentMethodType2 = new PaymentMethodType("UNION_PAY", 1, "CUP", R.string.datatrans_sdk_payment_method_union_pay, R.drawable.dtpl_payment_method_cup, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        UNION_PAY = paymentMethodType2;
        PaymentMethodType paymentMethodType3 = new PaymentMethodType("VISA", 2, "VIS", R.string.datatrans_sdk_payment_method_visa, R.drawable.dtpl_payment_method_vis, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        VISA = paymentMethodType3;
        PaymentMethodType paymentMethodType4 = new PaymentMethodType("MASTER_CARD", 3, "ECA", R.string.datatrans_sdk_payment_method_mastercard, R.drawable.dtpl_payment_method_eca, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        MASTER_CARD = paymentMethodType4;
        PaymentMethodType paymentMethodType5 = new PaymentMethodType("DINERS_CLUB", 4, "DIN", R.string.datatrans_sdk_payment_method_diners_club, R.drawable.dtpl_payment_method_din, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        DINERS_CLUB = paymentMethodType5;
        PaymentMethodType paymentMethodType6 = new PaymentMethodType("AMERICAN_EXPRESS", 5, "AMX", R.string.datatrans_sdk_payment_method_american_express, R.drawable.dtpl_payment_method_amx, SavedCard.class, 4, CollectionsKt.listOf(d9Var), null, 3248);
        AMERICAN_EXPRESS = paymentMethodType6;
        PaymentMethodType paymentMethodType7 = new PaymentMethodType("JCB", 6, "JCB", R.string.datatrans_sdk_payment_method_jcb, R.drawable.dtpl_payment_method_jcb, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        JCB = paymentMethodType7;
        PaymentMethodType paymentMethodType8 = new PaymentMethodType("UATP", 7, "UAP", R.string.datatrans_sdk_payment_method_uatp, R.drawable.dtpl_payment_method_uap, SavedCard.class, 0, CollectionsKt.listOf(d9Var), null, 3248);
        UATP = paymentMethodType8;
        PaymentMethodType paymentMethodType9 = new PaymentMethodType("DISCOVER", 8, "DIS", R.string.datatrans_sdk_payment_method_discover, R.drawable.dtpl_payment_method_dis, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        DISCOVER = paymentMethodType9;
        PaymentMethodType paymentMethodType10 = new PaymentMethodType("SUPERCARD", 9, "SCX", R.string.datatrans_sdk_payment_method_supercard, R.drawable.dtpl_payment_method_scx, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3248);
        SUPERCARD = paymentMethodType10;
        PaymentMethodType paymentMethodType11 = new PaymentMethodType("ELO_CARD", 10, "ELO", R.string.datatrans_sdk_payment_method_elo_card, R.drawable.dtpl_payment_method_elo, null, 3, CollectionsKt.listOf(d9Var), null, 3320);
        ELO_CARD = paymentMethodType11;
        PaymentMethodType paymentMethodType12 = new PaymentMethodType("HIPERCARD", 11, "HPC", R.string.datatrans_sdk_payment_method_hipercard, R.drawable.dtpl_payment_method_hip, null, 3, CollectionsKt.listOf(d9Var), null, 3320);
        HIPERCARD = paymentMethodType12;
        PaymentMethodType paymentMethodType13 = new PaymentMethodType("DANKORT", 12, "DNK", R.string.datatrans_sdk_payment_method_dankort, R.drawable.dtpl_payment_method_dnk, SavedCard.class, 3, CollectionsKt.listOf(d9Var), null, 3312);
        DANKORT = paymentMethodType13;
        PaymentMethodType paymentMethodType14 = new PaymentMethodType("BONCARD", 13, "BON", R.string.datatrans_sdk_payment_method_boncard_gift_card, R.drawable.dtpl_payment_method_bon_gift_card, SavedBoncard.class, 0, null, null, 4080);
        BONCARD = paymentMethodType14;
        int i3 = R.string.datatrans_sdk_payment_method_postfinance_card;
        int i4 = R.drawable.dtpl_payment_method_pfc;
        d9 d9Var2 = d9.c;
        PaymentMethodType paymentMethodType15 = new PaymentMethodType("POST_FINANCE_CARD", 14, "PFC", i3, i4, SavedPostFinanceCard.class, 0, CollectionsKt.listOf(d9Var2), null, 3440);
        POST_FINANCE_CARD = paymentMethodType15;
        PaymentMethodType paymentMethodType16 = new PaymentMethodType("POST_FINANCE_EFINANCE", 15, "PEF", R.string.datatrans_sdk_payment_method_postfinance, R.drawable.dtpl_payment_method_pef, null, 0, null, null, 3960);
        POST_FINANCE_EFINANCE = paymentMethodType16;
        int i5 = R.string.datatrans_sdk_payment_method_paypal;
        int i6 = R.drawable.dtpl_payment_method_pap;
        List listOf = CollectionsKt.listOf(d9.a);
        M7 m7 = M7.a;
        PaymentMethodType paymentMethodType17 = new PaymentMethodType("PAY_PAL", 16, "PAP", i5, i6, SavedPayPal.class, 0, listOf, new PropertyReference0Impl() { // from class: ch.datatrans.payment.paymentmethods.PaymentMethodType.1
            {
                M7 m72 = M7.a;
            }

            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                ((M7) this.receiver).getClass();
                Boolean bool = (Boolean) M7.f.getValue();
                bool.booleanValue();
                return bool;
            }
        }, 2416);
        PAY_PAL = paymentMethodType17;
        PaymentMethodType paymentMethodType18 = new PaymentMethodType("EASYPAY", 17, "ESY", R.string.datatrans_sdk_payment_method_easypay, R.drawable.dtpl_payment_method_esy, SavedPaymentMethod.class, 0, null, null, 4080);
        EASYPAY = paymentMethodType18;
        PaymentMethodType paymentMethodType19 = new PaymentMethodType("SEPA", 18, "ELV", R.string.datatrans_sdk_payment_method_elv, R.drawable.dtpl_payment_method_elv, SavedSEPA.class, 0, null, null, 4080);
        SEPA = paymentMethodType19;
        PaymentMethodType paymentMethodType20 = new PaymentMethodType("SWISS_BILLING", 19, "SWB", R.string.datatrans_sdk_payment_method_swissbilling, R.drawable.dtpl_payment_method_swb, null, 0, null, null, 4088);
        SWISS_BILLING = paymentMethodType20;
        PaymentMethodType paymentMethodType21 = new PaymentMethodType("IDEAL", 20, "DEA", R.string.datatrans_sdk_payment_method_ideal, R.drawable.dtpl_payment_method_dea, null, 0, CollectionsKt.listOf(d9Var2), null, 3576);
        IDEAL = paymentMethodType21;
        PaymentMethodType paymentMethodType22 = new PaymentMethodType("TWINT", 21, "TWI", R.string.datatrans_sdk_payment_method_twint, R.drawable.dtpl_payment_method_twi, SavedPaymentMethod.class, 0, CollectionsKt.listOf(d9Var2), new PropertyReference0Impl() { // from class: ch.datatrans.payment.paymentmethods.PaymentMethodType.2
            {
                M7 m72 = M7.a;
            }

            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                ((M7) this.receiver).getClass();
                Boolean bool = (Boolean) M7.c.getValue();
                bool.booleanValue();
                return bool;
            }
        }, 2544);
        TWINT = paymentMethodType22;
        PaymentMethodType paymentMethodType23 = new PaymentMethodType("GOOGLE_PAY", 22, "PAY", R.string.datatrans_sdk_payment_method_googlepay, R.drawable.dtpl_payment_method_pay, SavedGooglePay.class, 0, CollectionsKt.listOf(d9.d), new PropertyReference0Impl() { // from class: ch.datatrans.payment.paymentmethods.PaymentMethodType.3
            {
                M7 m72 = M7.a;
            }

            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                ((M7) this.receiver).getClass();
                Boolean bool = (Boolean) M7.e.getValue();
                bool.booleanValue();
                return bool;
            }
        }, 2512);
        GOOGLE_PAY = paymentMethodType23;
        PaymentMethodType paymentMethodType24 = new PaymentMethodType("SAMSUNG_PAY", 23, "SAM", R.string.datatrans_sdk_payment_method_samsungpay, R.drawable.dtpl_payment_method_sam, SavedPaymentMethod.class, 0, CollectionsKt.listOf(d9.e), new PropertyReference0Impl() { // from class: ch.datatrans.payment.paymentmethods.PaymentMethodType.4
            {
                M7 m72 = M7.a;
            }

            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                ((M7) this.receiver).getClass();
                Boolean bool = (Boolean) M7.d.getValue();
                bool.booleanValue();
                return bool;
            }
        }, 2512);
        SAMSUNG_PAY = paymentMethodType24;
        PaymentMethodType paymentMethodType25 = new PaymentMethodType("REKA", 24, "REK", R.string.datatrans_sdk_payment_method_reka, R.drawable.dtpl_payment_method_rek, SavedReka.class, 0, null, null, 4080);
        REKA = paymentMethodType25;
        PaymentMethodType paymentMethodType26 = new PaymentMethodType("CEMBRA_PAY", 25, "INT", R.string.datatrans_sdk_payment_method_int, R.drawable.dtpl_payment_method_int, SavedPaymentMethod.class, 0, null, null, 4080);
        CEMBRA_PAY = paymentMethodType26;
        PaymentMethodType paymentMethodType27 = new PaymentMethodType("SWISS_PASS", 26, "SWP", R.string.datatrans_sdk_payment_method_swisspass, R.drawable.dtpl_payment_method_swp, SavedPaymentMethod.class, 0, null, null, 4080);
        SWISS_PASS = paymentMethodType27;
        PaymentMethodType paymentMethodType28 = new PaymentMethodType("POWERPAY", 27, "MFX", R.string.datatrans_sdk_payment_method_powerpay, R.drawable.dtpl_payment_method_mfx, SavedPaymentMethod.class, 0, null, null, 4080);
        POWERPAY = paymentMethodType28;
        PaymentMethodType paymentMethodType29 = new PaymentMethodType("PAYCARD", 28, "MPX", R.string.datatrans_sdk_payment_method_paycard, R.drawable.dtpl_payment_method_mpx, SavedPaymentMethod.class, 0, null, null, 4080);
        PAYCARD = paymentMethodType29;
        PaymentMethodType paymentMethodType30 = new PaymentMethodType("PAYSAFECARD", 29, "PSC", R.string.datatrans_sdk_payment_method_paysafecard, R.drawable.dtpl_payment_method_psc, null, 0, null, null, 4088);
        PAYSAFECARD = paymentMethodType30;
        PaymentMethodType paymentMethodType31 = new PaymentMethodType("KLARNA", 30, "KLN", R.string.datatrans_sdk_payment_method_klarna, R.drawable.dtpl_payment_method_kln, null, 0, CollectionsKt.listOf(d9Var2), new PropertyReference0Impl() { // from class: ch.datatrans.payment.paymentmethods.PaymentMethodType.5
            {
                M7 m72 = M7.a;
            }

            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                ((M7) this.receiver).getClass();
                Boolean bool = (Boolean) M7.g.getValue();
                bool.booleanValue();
                return bool;
            }
        }, 2536);
        KLARNA = paymentMethodType31;
        PaymentMethodType paymentMethodType32 = new PaymentMethodType("SWISH", 31, "SWH", R.string.datatrans_sdk_payment_method_swish, R.drawable.dtpl_payment_method_swh, null, 0, CollectionsKt.listOf(d9Var2), null, 3560);
        SWISH = paymentMethodType32;
        PaymentMethodType paymentMethodType33 = new PaymentMethodType("VIPPS", 32, "VPS", R.string.datatrans_sdk_payment_method_vipps, R.drawable.dtpl_payment_method_vps, null, 0, CollectionsKt.listOf(d9Var2), null, 3560);
        VIPPS = paymentMethodType33;
        PaymentMethodType paymentMethodType34 = new PaymentMethodType("POST_FINANCE_PAY", 33, "PFP", R.string.datatrans_sdk_payment_method_post_finance_pay, R.drawable.dtpl_payment_method_pfp, SavedPostFinancePay.class, 0, CollectionsKt.listOf(d9Var2), null, 3568);
        POST_FINANCE_PAY = paymentMethodType34;
        PaymentMethodType paymentMethodType35 = new PaymentMethodType("MOBILE_PAY", 34, "MBP", R.string.datatrans_sdk_payment_method_mobile_pay, R.drawable.dtpl_payment_method_mbp, null, 0, CollectionsKt.listOf(d9Var2), null, 3560);
        MOBILE_PAY = paymentMethodType35;
        PaymentMethodType paymentMethodType36 = new PaymentMethodType("HALF_FARE_PLUS", 35, "PLU", R.string.datatrans_sdk_payment_method_half_fare_plus, R.drawable.dtpl_payment_method_plu, SavedPaymentMethod.class, 0, null, null, 4080);
        HALF_FARE_PLUS = paymentMethodType36;
        PaymentMethodType[] paymentMethodTypeArr = {paymentMethodType, paymentMethodType2, paymentMethodType3, paymentMethodType4, paymentMethodType5, paymentMethodType6, paymentMethodType7, paymentMethodType8, paymentMethodType9, paymentMethodType10, paymentMethodType11, paymentMethodType12, paymentMethodType13, paymentMethodType14, paymentMethodType15, paymentMethodType16, paymentMethodType17, paymentMethodType18, paymentMethodType19, paymentMethodType20, paymentMethodType21, paymentMethodType22, paymentMethodType23, paymentMethodType24, paymentMethodType25, paymentMethodType26, paymentMethodType27, paymentMethodType28, paymentMethodType29, paymentMethodType30, paymentMethodType31, paymentMethodType32, paymentMethodType33, paymentMethodType34, paymentMethodType35, paymentMethodType36};
        n = paymentMethodTypeArr;
        o = EnumEntriesKt.enumEntries(paymentMethodTypeArr);
        INSTANCE = new Companion(null);
        PaymentMethodType[] values = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
        for (PaymentMethodType paymentMethodType37 : values) {
            linkedHashMap.put(paymentMethodType37.identifier, paymentMethodType37);
        }
        Map mutableMap = MapsKt.toMutableMap(linkedHashMap);
        m = mutableMap;
        PaymentMethodType paymentMethodType38 = REKA;
        mutableMap.put("RER", paymentMethodType38);
        mutableMap.put("REL", paymentMethodType38);
        PaymentMethodType paymentMethodType39 = SUPERCARD;
        mutableMap.put("SCM", paymentMethodType39);
        mutableMap.put("SCP", paymentMethodType39);
        mutableMap.put("SCV", paymentMethodType39);
    }

    public PaymentMethodType(String str, int i, String str2, int i2, int i3, Class cls, int i4, List list, PropertyReference0Impl propertyReference0Impl, int i5) {
        EnumSet of;
        Boolean bool = Boolean.FALSE;
        Class cls2 = (i5 & 8) != 0 ? null : cls;
        bool = (i5 & 16) != 0 ? null : bool;
        boolean z = (i5 & 32) == 0;
        int i6 = (i5 & 64) != 0 ? 0 : RemoteCommand.Response.STATUS_BAD_REQUEST;
        String str3 = (i5 & 128) != 0 ? "user-scalable=yes" : "width=device-width, initial-scale=1, maximum-scale=1.2, user-scalable=yes";
        int i7 = (i5 & 256) != 0 ? 0 : i4;
        List emptyList = (i5 & 512) != 0 ? CollectionsKt.emptyList() : list;
        PropertyReference0Impl propertyReference0Impl2 = (i5 & 1024) == 0 ? propertyReference0Impl : null;
        boolean z2 = (i5 & 2048) == 0;
        this.identifier = str2;
        this.logo = i3;
        this.savedPaymentMethodClass = cls2;
        this.hasSavedPaymentMethodSupport = bool;
        this.usesFakeAlias = z;
        this.viewPortWidth = i6;
        this.viewPortAdjustment = str3;
        this.cvvLength = i7;
        this.isModuleAvailable = propertyReference0Impl2;
        this.isCvvOptional = z2;
        this.k = new Z9(i2, new Object[0]);
        if (emptyList.isEmpty()) {
            of = EnumSet.noneOf(d9.class);
            Intrinsics.checkNotNull(of);
        } else {
            Enum r1 = (Enum) emptyList.get(0);
            d9[] d9VarArr = (d9[]) emptyList.toArray(new d9[0]);
            of = EnumSet.of(r1, (Enum[]) Arrays.copyOf(d9VarArr, d9VarArr.length));
            Intrinsics.checkNotNull(of);
        }
        this.l = of;
    }

    @JvmStatic
    public static final PaymentMethodType fromIdentifier(String str) {
        return INSTANCE.fromIdentifier(str);
    }

    public static EnumEntries<PaymentMethodType> getEntries() {
        return o;
    }

    public static PaymentMethodType valueOf(String str) {
        return (PaymentMethodType) Enum.valueOf(PaymentMethodType.class, str);
    }

    public static PaymentMethodType[] values() {
        return (PaymentMethodType[]) n.clone();
    }

    public final String generateAppCallbackUrl$lib_release(String appCallbackScheme) {
        Intrinsics.checkNotNullParameter(appCallbackScheme, "appCallbackScheme");
        return appCallbackScheme + "://datatrans-sdk/" + this.identifier;
    }

    /* renamed from: getCvvLength$lib_release, reason: from getter */
    public final int getCvvLength() {
        return this.cvvLength;
    }

    public final boolean getHasCvv$lib_release() {
        return this.cvvLength > 0;
    }

    /* renamed from: getHasSavedPaymentMethodSupport$lib_release, reason: from getter */
    public final Boolean getHasSavedPaymentMethodSupport() {
        return this.hasSavedPaymentMethodSupport;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    /* renamed from: getLogo$lib_release, reason: from getter */
    public final int getLogo() {
        return this.logo;
    }

    public final boolean getRequiresAppCallbackScheme$lib_release() {
        return this.l.contains(d9.a) || this.l.contains(d9.c);
    }

    public final boolean getRequiresExternalWebProcess$lib_release() {
        return this.l.contains(d9.a);
    }

    public final boolean getRequiresGooglePayConfig$lib_release() {
        return this.l.contains(d9.d);
    }

    public final boolean getRequiresSamsungPayConfig$lib_release() {
        return this.l.contains(d9.e);
    }

    public final Class<? extends SavedPaymentMethod> getSavedPaymentMethodClass$lib_release() {
        return this.savedPaymentMethodClass;
    }

    public final InterfaceC0235ud getTitle$lib_release() {
        return this.k;
    }

    /* renamed from: getUsesFakeAlias$lib_release, reason: from getter */
    public final boolean getUsesFakeAlias() {
        return this.usesFakeAlias;
    }

    /* renamed from: getViewPortAdjustment$lib_release, reason: from getter */
    public final String getViewPortAdjustment() {
        return this.viewPortAdjustment;
    }

    /* renamed from: getViewPortWidth$lib_release, reason: from getter */
    public final int getViewPortWidth() {
        return this.viewPortWidth;
    }

    public final Ae getWebProcess$lib_release(Be webProcessListener, String url, String appCallbackScheme) {
        Intrinsics.checkNotNullParameter(webProcessListener, "webProcessListener");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(appCallbackScheme, "appCallbackScheme");
        if (WhenMappings.$EnumSwitchMapping$0[ordinal()] != 1) {
            return new I9(url, this, webProcessListener);
        }
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(appCallbackScheme, "appCallbackScheme");
        Intrinsics.checkNotNullParameter(this, "type");
        Intrinsics.checkNotNullParameter(webProcessListener, "listener");
        return new C0230u5(new C0185n5(url), appCallbackScheme, this, webProcessListener);
    }

    public final boolean hasAliasSupport$lib_release() {
        return (this.savedPaymentMethodClass == null || Intrinsics.areEqual(this.hasSavedPaymentMethodSupport, Boolean.FALSE)) ? false : true;
    }

    public final boolean isCreditCard$lib_release() {
        return this.l.contains(d9.b);
    }

    /* renamed from: isCvvOptional$lib_release, reason: from getter */
    public final boolean getIsCvvOptional() {
        return this.isCvvOptional;
    }

    public final Function0<Boolean> isModuleAvailable$lib_release() {
        return this.isModuleAvailable;
    }
}
