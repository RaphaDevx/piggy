package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.C0193oa;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0016\u0018\u0000 %2\u00020\u0001:\u0001%B3\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bB)\b\u0016\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0018\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\t\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lch/datatrans/payment/paymentmethods/SamsungPayConfig;", "", "", "serviceId", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "supportedNetworks", "merchantName", "", "showSamsungPayAsSavedPaymentMethod", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Z)V", "(Ljava/util/List;Ljava/lang/String;Z)V", "isTesting", "getServiceId$lib_release", "(Z)Ljava/lang/String;", "getServiceId", "Lcom/samsung/android/sdk/samsungpay/v2/SpaySdk$Brand;", "getCardBrands$lib_release", "(Ljava/util/List;)Ljava/util/List;", "getCardBrands", "Lcom/samsung/android/sdk/samsungpay/v2/PartnerInfo;", "createPartnerInfo$lib_release", "(Z)Lcom/samsung/android/sdk/samsungpay/v2/PartnerInfo;", "createPartnerInfo", "b", "Ljava/util/List;", "getSupportedNetworks$lib_release", "()Ljava/util/List;", "c", "Ljava/lang/String;", "getMerchantName$lib_release", "()Ljava/lang/String;", "d", "Z", "getShowSamsungPayAsSavedPaymentMethod$lib_release", "()Z", "Companion", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class SamsungPayConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public final String a;

    /* renamed from: b, reason: from kotlin metadata */
    public final List supportedNetworks;

    /* renamed from: c, reason: from kotlin metadata */
    public final String merchantName;

    /* renamed from: d, reason: from kotlin metadata */
    public final boolean showSamsungPayAsSavedPaymentMethod;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J0\u0010\t\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0087@¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/paymentmethods/SamsungPayConfig$Companion;", "", "Landroid/content/Context;", "context", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "supportedNetworks", "", "isTesting", "hasSamsungPay", "(Landroid/content/Context;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static /* synthetic */ Object hasSamsungPay$default(Companion companion, Context context, List list, boolean z, Continuation continuation, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.hasSamsungPay(context, list, z, continuation);
        }

        @JvmStatic
        public final Object hasSamsungPay(Context context, List<? extends PaymentMethodType> list, boolean z, Continuation<? super Boolean> continuation) {
            return new C0193oa(context, new SamsungPayConfig(list, "", false, 4, null), z).a(continuation);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PaymentMethodType.values().length];
            try {
                iArr[PaymentMethodType.VISA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PaymentMethodType.MASTER_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PaymentMethodType.AMERICAN_EXPRESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PaymentMethodType.DISCOVER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SamsungPayConfig(String str, List<? extends PaymentMethodType> supportedNetworks, String merchantName, boolean z) {
        Intrinsics.checkNotNullParameter(supportedNetworks, "supportedNetworks");
        Intrinsics.checkNotNullParameter(merchantName, "merchantName");
        this.a = str;
        this.supportedNetworks = supportedNetworks;
        this.merchantName = merchantName;
        this.showSamsungPayAsSavedPaymentMethod = z;
    }

    @JvmStatic
    public static final Object hasSamsungPay(Context context, List<? extends PaymentMethodType> list, boolean z, Continuation<? super Boolean> continuation) {
        return INSTANCE.hasSamsungPay(context, list, z, continuation);
    }

    public final PartnerInfo createPartnerInfo$lib_release(boolean isTesting) {
        Bundle bundle = new Bundle();
        bundle.putString(SpaySdk.PARTNER_SERVICE_TYPE, SpaySdk.ServiceType.INAPP_PAYMENT.toString());
        return new PartnerInfo(getServiceId$lib_release(isTesting), bundle);
    }

    public final List<SpaySdk.Brand> getCardBrands$lib_release(List<? extends PaymentMethodType> supportedNetworks) {
        SpaySdk.Brand brand;
        Intrinsics.checkNotNullParameter(supportedNetworks, "supportedNetworks");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supportedNetworks, 10));
        for (PaymentMethodType paymentMethodType : supportedNetworks) {
            int i = WhenMappings.$EnumSwitchMapping$0[paymentMethodType.ordinal()];
            if (i == 1) {
                brand = SpaySdk.Brand.VISA;
            } else if (i == 2) {
                brand = SpaySdk.Brand.MASTERCARD;
            } else if (i == 3) {
                brand = SpaySdk.Brand.AMERICANEXPRESS;
            } else {
                if (i != 4) {
                    throw new IllegalArgumentException("The card network '" + paymentMethodType + "' is not supported by Samsung Pay.");
                }
                brand = SpaySdk.Brand.DISCOVER;
            }
            arrayList.add(brand);
        }
        return arrayList;
    }

    /* renamed from: getMerchantName$lib_release, reason: from getter */
    public final String getMerchantName() {
        return this.merchantName;
    }

    public final String getServiceId$lib_release(boolean isTesting) {
        String str = this.a;
        return str == null ? isTesting ? "78c9767230584e7990d50b" : "7365d84a5ba0466c9ea86c" : str;
    }

    /* renamed from: getShowSamsungPayAsSavedPaymentMethod$lib_release, reason: from getter */
    public final boolean getShowSamsungPayAsSavedPaymentMethod() {
        return this.showSamsungPayAsSavedPaymentMethod;
    }

    public final List<PaymentMethodType> getSupportedNetworks$lib_release() {
        return this.supportedNetworks;
    }

    public /* synthetic */ SamsungPayConfig(String str, List list, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2, (i & 8) != 0 ? false : z);
    }

    public /* synthetic */ SamsungPayConfig(List list, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, (i & 4) != 0 ? false : z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SamsungPayConfig(List<? extends PaymentMethodType> supportedNetworks, String merchantName, boolean z) {
        this(null, supportedNetworks, merchantName, z);
        Intrinsics.checkNotNullParameter(supportedNetworks, "supportedNetworks");
        Intrinsics.checkNotNullParameter(merchantName, "merchantName");
    }
}
