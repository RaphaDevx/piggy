package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.R;
import ch.datatrans.payment.R5;
import ch.datatrans.payment.paymentmethods.boncard.BoncardType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.sentry.protocol.Request;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0016\u0018\u0000 %2\u00020\u0001:\u0002&%B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u0018J\u0017\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0010¢\u0006\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\nR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedBoncard;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "", SavedPaymentMethod.ALIAS_KEY, "maskedCardNumber", "Lch/datatrans/payment/paymentmethods/boncard/BoncardType;", SavedBoncard.BONCARD_TYPE_KEY, "<init>", "(Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/boncard/BoncardType;)V", "toString", "()Ljava/lang/String;", "clone", "()Lch/datatrans/payment/paymentmethods/SavedBoncard;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroid/content/Context;", "context", "getInternalDisplayTitle$lib_release", "(Landroid/content/Context;)Ljava/lang/String;", "getInternalDisplayTitle", "getAccessibilityTitle", "getLogo$lib_release", "(Landroid/content/Context;)I", "getLogo", "c", "Ljava/lang/String;", "getMaskedCardNumber", "d", "Lch/datatrans/payment/paymentmethods/boncard/BoncardType;", "getBoncardType", "()Lch/datatrans/payment/paymentmethods/boncard/BoncardType;", "Companion", "SavedBoncardSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class SavedBoncard extends SavedPaymentMethod {
    public static final String BONCARD_TYPE_KEY = "boncardType";
    public static final String MASKED_CARD_NUMBER_KEY = "maskedCC";

    /* renamed from: c, reason: from kotlin metadata and from toString */
    public final String maskedCardNumber;

    /* renamed from: d, reason: from kotlin metadata */
    public final BoncardType boncardType;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedBoncard$SavedBoncardSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedBoncard;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedBoncardSerializer implements JsonSerializer<SavedBoncard>, JsonDeserializer<SavedBoncard> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedBoncard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            BoncardType boncardType;
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) context.deserialize(json, SavedPaymentMethod.class);
            if (savedPaymentMethod.getType() != PaymentMethodType.BONCARD) {
                throw new IllegalStateException("Check failed.");
            }
            JsonObject asJsonObject = json.getAsJsonObject();
            Intrinsics.checkNotNull(asJsonObject);
            String a = R5.a(asJsonObject, SavedBoncard.BONCARD_TYPE_KEY);
            BoncardType[] values = BoncardType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    boncardType = null;
                    break;
                }
                boncardType = values[i];
                if (StringsKt.equals(boncardType.name(), a, true)) {
                    break;
                }
                i++;
            }
            if (boncardType == null) {
                boncardType = BoncardType.GIFT_CARD;
            }
            return new SavedBoncard(savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), R5.a(asJsonObject, "maskedCC"), boncardType);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedBoncard src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = context.serialize(src, SavedPaymentMethod.class).getAsJsonObject();
            asJsonObject.addProperty(SavedBoncard.BONCARD_TYPE_KEY, src.getBoncardType().name());
            asJsonObject.addProperty("maskedCC", src.getMaskedCardNumber());
            Intrinsics.checkNotNullExpressionValue(asJsonObject, "apply(...)");
            return asJsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedBoncard(String alias, String str, BoncardType boncardType) {
        super(PaymentMethodType.BONCARD, alias);
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(boncardType, "boncardType");
        this.maskedCardNumber = str;
        this.boncardType = boncardType;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null) || !super.equals(other)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedBoncard");
        SavedBoncard savedBoncard = (SavedBoncard) other;
        return Intrinsics.areEqual(this.maskedCardNumber, savedBoncard.maskedCardNumber) && this.boncardType == savedBoncard.boncardType;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getAccessibilityTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.maskedCardNumber;
        if (str == null) {
            return context.getString(this.boncardType.getTitleId());
        }
        String takeLast = StringsKt.takeLast(str, 4);
        String string = context.getString(this.boncardType.getTitleId());
        Intrinsics.checkNotNullExpressionValue(string, "let(...)");
        String string2 = context.getString(R.string.datatrans_sdk_accessibility_payment_method_ending_in, string, takeLast);
        Intrinsics.checkNotNull(string2);
        return string2;
    }

    public final BoncardType getBoncardType() {
        return this.boncardType;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getInternalDisplayTitle$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.maskedCardNumber;
        if (str == null) {
            return context.getString(this.boncardType.getTitleId());
        }
        return "•••• " + StringsKt.takeLast(str, 4);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int getLogo$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.boncardType.getLogo();
    }

    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int hashCode() {
        int hashCode = (this.boncardType.hashCode() + (super.hashCode() * 31)) * 31;
        String str = this.maskedCardNumber;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', maskedCardNumber='" + this.maskedCardNumber + "', boncardType='" + this.boncardType.name() + "')";
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedBoncard mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedBoncard");
        return (SavedBoncard) mo3512clone;
    }
}
