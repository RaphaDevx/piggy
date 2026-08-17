package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0002\u000b\fB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedReka;", "Lch/datatrans/payment/paymentmethods/SavedCard;", "", SavedPaymentMethod.ALIAS_KEY, "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "cardExpiryDate", "maskedCardNumber", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;Ljava/lang/String;)V", "clone", "()Lch/datatrans/payment/paymentmethods/SavedReka;", "Companion", "SavedRekaSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SavedReka extends SavedCard {

    @Deprecated
    public static final long serialVersionUID = 20110112;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedReka$Companion;", "", "", "serialVersionUID", "J", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedReka$SavedRekaSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedReka;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedRekaSerializer implements JsonSerializer<SavedReka>, JsonDeserializer<SavedReka> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedReka deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            SavedCard savedCard = (SavedCard) context.deserialize(json, SavedCard.class);
            if (savedCard.getType() != PaymentMethodType.REKA) {
                throw new IllegalStateException("Check failed.");
            }
            String str = savedCard.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String();
            CardExpiryDate cardExpiryDate = savedCard.getCardExpiryDate();
            Intrinsics.checkNotNull(cardExpiryDate);
            return new SavedReka(str, cardExpiryDate, savedCard.getMaskedCardNumber());
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedReka src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonElement serialize = context.serialize(src, SavedCard.class);
            Intrinsics.checkNotNullExpressionValue(serialize, "serialize(...)");
            return serialize;
        }
    }

    static {
        new Companion(null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedReka(String alias, CardExpiryDate cardExpiryDate, String str) {
        super(PaymentMethodType.REKA, alias, cardExpiryDate, str, null);
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(cardExpiryDate, "cardExpiryDate");
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedCard, ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedReka mo3512clone() {
        SavedCard mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedReka");
        return (SavedReka) mo3512clone;
    }
}
