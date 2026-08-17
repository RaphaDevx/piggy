package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.R5;
import ch.datatrans.payment.paymentmethods.SavedCard;
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
import net.openid.appauth.ResponseTypeValues;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 !2\u00020\u0001:\u0002!\"B/\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\u000b¨\u0006#"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedGooglePay;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "", SavedPaymentMethod.ALIAS_KEY, "Lch/datatrans/payment/paymentmethods/SavedCard;", ResponseTypeValues.TOKEN, "last4", "description", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/SavedCard;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "clone", "()Lch/datatrans/payment/paymentmethods/SavedGooglePay;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroid/content/Context;", "context", "getDisplayTitle", "(Landroid/content/Context;)Ljava/lang/String;", "c", "Lch/datatrans/payment/paymentmethods/SavedCard;", "getToken", "()Lch/datatrans/payment/paymentmethods/SavedCard;", "d", "Ljava/lang/String;", "getLast4", "Companion", "SavedGooglePaySerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SavedGooglePay extends SavedPaymentMethod {
    public static final String DESCRIPTION = "description";
    public static final String LAST_FOUR = "lastFour";
    public static final String TOKENIZED_CARD = "tokenizedCard";

    /* renamed from: c, reason: from kotlin metadata and from toString */
    public final SavedCard token;

    /* renamed from: d, reason: from kotlin metadata and from toString */
    public final String last4;

    /* renamed from: e, reason: from toString */
    public final String description;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedGooglePay$SavedGooglePaySerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedGooglePay;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "src", "Ljava/lang/reflect/Type;", "typeOfSrc", "Lcom/google/gson/JsonSerializationContext;", "context", "Lcom/google/gson/JsonElement;", "serialize", "(Lch/datatrans/payment/paymentmethods/SavedGooglePay;Ljava/lang/reflect/Type;Lcom/google/gson/JsonSerializationContext;)Lcom/google/gson/JsonElement;", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "deserialize", "(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lch/datatrans/payment/paymentmethods/SavedGooglePay;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedGooglePaySerializer implements JsonSerializer<SavedGooglePay>, JsonDeserializer<SavedGooglePay> {
        public final SavedCard.SavedCardSerializer a = new SavedCard.SavedCardSerializer();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedGooglePay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            SavedCard savedCard;
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = json.getAsJsonObject();
            String str = asJsonObject.has("aliasCC") ? "aliasCC" : SavedPaymentMethod.ALIAS_KEY;
            if (asJsonObject.has(SavedGooglePay.TOKENIZED_CARD)) {
                SavedCard.SavedCardSerializer savedCardSerializer = this.a;
                JsonElement jsonElement = asJsonObject.get(SavedGooglePay.TOKENIZED_CARD);
                Intrinsics.checkNotNullExpressionValue(jsonElement, "get(...)");
                savedCard = savedCardSerializer.deserialize(jsonElement, typeOfT, context);
            } else {
                savedCard = null;
            }
            String asString = asJsonObject.has(SavedGooglePay.LAST_FOUR) ? asJsonObject.get(SavedGooglePay.LAST_FOUR).getAsString() : null;
            String asString2 = asJsonObject.has("description") ? asJsonObject.get("description").getAsString() : null;
            if (!asJsonObject.has(str)) {
                throw new IllegalArgumentException(R5.a(str).toString());
            }
            String asString3 = asJsonObject.get(str).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString3, "getAsString(...)");
            return new SavedGooglePay(asString3, savedCard, asString, asString2);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedGooglePay src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(PaymentMethodType.PAYMENT_METHOD_KEY, context.serialize(src.getType()));
            jsonObject.addProperty(SavedPaymentMethod.ALIAS_KEY, src.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String());
            jsonObject.addProperty(SavedGooglePay.LAST_FOUR, src.getLast4());
            jsonObject.addProperty("description", src.description);
            SavedCard token = src.getToken();
            if (token != null) {
                jsonObject.add(SavedGooglePay.TOKENIZED_CARD, this.a.serialize(token, typeOfSrc, context));
            }
            return jsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedGooglePay(String alias, SavedCard savedCard, String str, String str2) {
        super(PaymentMethodType.GOOGLE_PAY, alias);
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.token = savedCard;
        this.last4 = str;
        this.description = str2;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(SavedGooglePay.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedGooglePay");
        SavedGooglePay savedGooglePay = (SavedGooglePay) other;
        return getType() == savedGooglePay.getType() && Intrinsics.areEqual(getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), savedGooglePay.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String()) && Intrinsics.areEqual(this.token, savedGooglePay.token) && Intrinsics.areEqual(this.last4, savedGooglePay.last4) && Intrinsics.areEqual(this.description, savedGooglePay.description);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getDisplayTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.description != null) {
            String str = getInternalDisplayTitle$lib_release(context) + ": " + this.description;
            if (str != null) {
                return str;
            }
        }
        return getInternalDisplayTitle$lib_release(context);
    }

    public final String getLast4() {
        return this.last4;
    }

    public final SavedCard getToken() {
        return this.token;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int hashCode() {
        int hashCode = getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String().hashCode() + (getType().hashCode() * 31);
        SavedCard savedCard = this.token;
        int hashCode2 = hashCode + (savedCard != null ? savedCard.hashCode() : 0);
        String str = this.last4;
        int hashCode3 = hashCode2 + (str != null ? str.hashCode() : 0);
        String str2 = this.description;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(SavedGooglePay.class).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', token='" + this.token + "', last4='" + this.last4 + "', description='" + this.description + "')";
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedGooglePay mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedGooglePay");
        return (SavedGooglePay) mo3512clone;
    }
}
