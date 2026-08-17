package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.InterfaceC0235ud;
import ch.datatrans.payment.N7;
import ch.datatrans.payment.R;
import ch.datatrans.payment.R5;
import ch.datatrans.payment.r4;
import ch.datatrans.payment.y4;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.sentry.protocol.Request;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\b\u0016\u0018\u0000 *2\u00020\u0001:\u0002*+B5\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0010¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R(\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010\u0013R(\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b&\u0010$\u001a\u0004\b'\u0010\u0013R\u0014\u0010(\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedCard;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "type", "", SavedPaymentMethod.ALIAS_KEY, "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "cardExpiryDate", "maskedCardNumber", "cardholder", "<init>", "(Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;Ljava/lang/String;Ljava/lang/String;)V", "Landroid/content/Context;", "context", "getInternalDisplayTitle$lib_release", "(Landroid/content/Context;)Ljava/lang/String;", "getInternalDisplayTitle", "getAccessibilityTitle", "toString", "()Ljava/lang/String;", "clone", "()Lch/datatrans/payment/paymentmethods/SavedCard;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "value", "c", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "getCardExpiryDate", "()Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "d", "Ljava/lang/String;", "getMaskedCardNumber", "e", "getCardholder", "isValid", "()Z", "Companion", "SavedCardSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class SavedCard extends SavedPaymentMethod {
    public static final String CARD_HOLDER_KEY = "cardHolder";
    public static final String FIRST_NAME_KEY = "firstName";
    public static final String LAST_NAME_KEY = "lastName";
    public static final String MASKED_CARD_NUMBER_KEY = "maskedCC";
    private static final long serialVersionUID = 20110112;

    /* renamed from: c, reason: from kotlin metadata */
    public final CardExpiryDate cardExpiryDate;

    /* renamed from: d, reason: from kotlin metadata */
    public final String maskedCardNumber;

    /* renamed from: e, reason: from kotlin metadata */
    public final String cardholder;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedCard$SavedCardSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedCard;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedCardSerializer implements JsonSerializer<SavedCard>, JsonDeserializer<SavedCard> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            String joinToString$default;
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) context.deserialize(json, SavedPaymentMethod.class);
            JsonObject asJsonObject = json.getAsJsonObject();
            Intrinsics.checkNotNull(asJsonObject);
            String a = R5.a(asJsonObject, "maskedCC");
            Object value = r4.g.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            CardExpiryDate cardExpiryDate = (CardExpiryDate) ((Gson) value).fromJson(json, CardExpiryDate.class);
            if (asJsonObject.has(SavedCard.CARD_HOLDER_KEY)) {
                joinToString$default = asJsonObject.get(SavedCard.CARD_HOLDER_KEY).getAsString();
            } else {
                joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{R5.a(asJsonObject, SavedCard.FIRST_NAME_KEY), R5.a(asJsonObject, SavedCard.LAST_NAME_KEY)}), StringUtils.SPACE, null, null, 0, null, null, 62, null);
                if (joinToString$default.length() == 0) {
                    joinToString$default = null;
                }
            }
            return new SavedCard(savedPaymentMethod.getType(), savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), cardExpiryDate, a, joinToString$default);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedCard src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = context.serialize(src, SavedPaymentMethod.class).getAsJsonObject();
            CardExpiryDate cardExpiryDate = src.getCardExpiryDate();
            if (cardExpiryDate != null) {
                asJsonObject.addProperty(CardExpiryDate.MONTH_KEY, String.valueOf(cardExpiryDate.getMonth()));
                asJsonObject.addProperty(CardExpiryDate.YEAR_KEY, String.valueOf(cardExpiryDate.getYear()));
            }
            asJsonObject.addProperty("maskedCC", src.getMaskedCardNumber());
            asJsonObject.addProperty(SavedCard.CARD_HOLDER_KEY, src.getCardholder());
            Intrinsics.checkNotNull(asJsonObject);
            return asJsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedCard(PaymentMethodType type, String alias, CardExpiryDate cardExpiryDate, String str, String str2) {
        super(type, alias);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.cardExpiryDate = cardExpiryDate;
        this.maskedCardNumber = str;
        this.cardholder = str2;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null) || !super.equals(other)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedCard");
        SavedCard savedCard = (SavedCard) other;
        return Intrinsics.areEqual(this.cardExpiryDate, savedCard.cardExpiryDate) && Intrinsics.areEqual(this.maskedCardNumber, savedCard.maskedCardNumber) && Intrinsics.areEqual(this.cardholder, savedCard.cardholder);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getAccessibilityTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.maskedCardNumber;
        if (str == null) {
            return super.getAccessibilityTitle(context);
        }
        Intrinsics.checkNotNull(str);
        String takeLast = StringsKt.takeLast(str, 4);
        InterfaceC0235ud uiString = y4.a(getType());
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uiString, "uiString");
        String string = context.getString(R.string.datatrans_sdk_accessibility_payment_method_ending_in, uiString.a(context), takeLast);
        Intrinsics.checkNotNull(string);
        return string;
    }

    public final CardExpiryDate getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public final String getCardholder() {
        return this.cardholder;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getInternalDisplayTitle$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.maskedCardNumber;
        if (str == null) {
            return super.getInternalDisplayTitle$lib_release(context);
        }
        Intrinsics.checkNotNull(str);
        return "•••• " + StringsKt.takeLast(str, 4);
    }

    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        CardExpiryDate cardExpiryDate = this.cardExpiryDate;
        int hashCode2 = (hashCode + (cardExpiryDate != null ? cardExpiryDate.hashCode() : 0)) * 31;
        String str = this.maskedCardNumber;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.cardholder;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean isValid() {
        CardExpiryDate cardExpiryDate = this.cardExpiryDate;
        if (cardExpiryDate != null) {
            return cardExpiryDate.isValid$lib_release();
        }
        return true;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', expiry='" + this.cardExpiryDate + "', maskedCardNumber='" + this.maskedCardNumber + '\'' + (this.cardholder != null ? N7.a(new StringBuilder(", cardHolder='"), this.cardholder, '\'') : "") + ')';
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedCard mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedCard");
        return (SavedCard) mo3512clone;
    }
}
