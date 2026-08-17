package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.R;
import ch.datatrans.payment.R5;
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

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0010¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\r\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\r\u0010\nJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000f¨\u0006\u001e"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPostFinancePay;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "", SavedPaymentMethod.ALIAS_KEY, "maskedAccountNumber", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroid/content/Context;", "context", "getDisplayTitle", "(Landroid/content/Context;)Ljava/lang/String;", "getInternalDisplayTitle$lib_release", "getInternalDisplayTitle", "getAccessibilityTitle", "toString", "()Ljava/lang/String;", "clone", "()Lch/datatrans/payment/paymentmethods/SavedPostFinancePay;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "f", "Ljava/lang/String;", "getMaskedAccountNumber", "SavedPostFinancePaySerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SavedPostFinancePay extends SavedPaymentMethod {

    /* renamed from: f, reason: from kotlin metadata and from toString */
    public final String maskedAccountNumber;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPostFinancePay$SavedPostFinancePaySerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedPostFinancePay;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedPostFinancePaySerializer implements JsonSerializer<SavedPostFinancePay>, JsonDeserializer<SavedPostFinancePay> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedPostFinancePay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = json.getAsJsonObject();
            String str = asJsonObject.has("aliasCC") ? "aliasCC" : SavedPaymentMethod.ALIAS_KEY;
            String asString = asJsonObject.has("maskedCC") ? asJsonObject.get("maskedCC").getAsString() : null;
            if (!asJsonObject.has(str)) {
                throw new IllegalArgumentException(R5.a(str).toString());
            }
            String asString2 = asJsonObject.get(str).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString2, "getAsString(...)");
            return new SavedPostFinancePay(asString2, asString);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedPostFinancePay src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(PaymentMethodType.PAYMENT_METHOD_KEY, context.serialize(src.getType()));
            jsonObject.addProperty(SavedPaymentMethod.ALIAS_KEY, src.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String());
            jsonObject.addProperty("maskedCC", src.getMaskedAccountNumber());
            return jsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedPostFinancePay(String alias, String str) {
        super(PaymentMethodType.POST_FINANCE_PAY, alias);
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.maskedAccountNumber = str;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(SavedPostFinancePay.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPostFinancePay");
        SavedPostFinancePay savedPostFinancePay = (SavedPostFinancePay) other;
        return getType() == savedPostFinancePay.getType() && Intrinsics.areEqual(getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), savedPostFinancePay.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String()) && Intrinsics.areEqual(this.maskedAccountNumber, savedPostFinancePay.maskedAccountNumber);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getAccessibilityTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.maskedAccountNumber == null) {
            return super.getInternalDisplayTitle$lib_release(context);
        }
        String string = context.getString(R.string.datatrans_sdk_iban, super.getInternalDisplayTitle$lib_release(context));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = context.getString(R.string.datatrans_sdk_accessibility_payment_method_ending_in, string, StringsKt.takeLast(this.maskedAccountNumber, 2));
        Intrinsics.checkNotNull(string2);
        return string2;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getDisplayTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        sb.append(super.getInternalDisplayTitle$lib_release(context));
        String str = "";
        sb.append(this.maskedAccountNumber != null ? ": " : "");
        String str2 = this.maskedAccountNumber;
        if (str2 != null) {
            String replace$default = StringsKt.replace$default(str2, "*", "", false, 4, (Object) null);
            str = StringsKt.take(replace$default, 4) + " ****** " + StringsKt.takeLast(replace$default, 2);
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getInternalDisplayTitle$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String internalDisplayTitle$lib_release = super.getInternalDisplayTitle$lib_release(context);
        String str = this.maskedAccountNumber;
        if (str == null) {
            return internalDisplayTitle$lib_release;
        }
        String replace$default = StringsKt.replace$default(str, "*", "", false, 4, (Object) null);
        return StringsKt.take(replace$default, 4) + " ****** " + StringsKt.takeLast(replace$default, 2);
    }

    public final String getMaskedAccountNumber() {
        return this.maskedAccountNumber;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int hashCode() {
        int hashCode = getType().hashCode() + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String().hashCode() + (getType().hashCode() * 31);
        String str = this.maskedAccountNumber;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(SavedPostFinancePay.class).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', maskedAccountNumber='" + this.maskedAccountNumber + "')";
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedPostFinancePay mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPostFinancePay");
        return (SavedPostFinancePay) mo3512clone;
    }
}
