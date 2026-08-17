package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.InterfaceC0235ud;
import ch.datatrans.payment.R5;
import ch.datatrans.payment.y4;
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

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0010¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\f\u0010\nJ\u000f\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u000e¨\u0006\u001e"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPayPal;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "", SavedPaymentMethod.ALIAS_KEY, "payPalEmail", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroid/content/Context;", "context", "getInternalDisplayTitle$lib_release", "(Landroid/content/Context;)Ljava/lang/String;", "getInternalDisplayTitle", "getAccessibilityTitle", "toString", "()Ljava/lang/String;", "clone", "()Lch/datatrans/payment/paymentmethods/SavedPayPal;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "c", "Ljava/lang/String;", "getPayPalEmail", "Companion", "SavedPayPalSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SavedPayPal extends SavedPaymentMethod {
    public static final String EMAIL_KEY = "email";
    private static final long serialVersionUID = 20110112;

    /* renamed from: c, reason: from kotlin metadata and from toString */
    public final String payPalEmail;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPayPal$SavedPayPalSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedPayPal;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedPayPalSerializer implements JsonSerializer<SavedPayPal>, JsonDeserializer<SavedPayPal> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedPayPal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) context.deserialize(json, SavedPaymentMethod.class);
            if (savedPaymentMethod.getType() != PaymentMethodType.PAY_PAL) {
                throw new IllegalStateException("Check failed.");
            }
            JsonObject asJsonObject = json.getAsJsonObject();
            Intrinsics.checkNotNull(asJsonObject);
            return new SavedPayPal(savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), R5.a(asJsonObject, "email"));
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedPayPal src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = context.serialize(src, SavedPaymentMethod.class).getAsJsonObject();
            asJsonObject.addProperty("email", src.getPayPalEmail());
            Intrinsics.checkNotNull(asJsonObject);
            return asJsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedPayPal(String alias, String str) {
        super(PaymentMethodType.PAY_PAL, alias);
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.payPalEmail = str;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(SavedPayPal.class, other != null ? other.getClass() : null) || !super.equals(other)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPayPal");
        return Intrinsics.areEqual(this.payPalEmail, ((SavedPayPal) other).payPalEmail);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getAccessibilityTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.payPalEmail == null) {
            return super.getAccessibilityTitle(context);
        }
        StringBuilder sb = new StringBuilder();
        InterfaceC0235ud uiString = y4.a(getType());
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uiString, "uiString");
        sb.append(uiString.a(context));
        sb.append(", ");
        sb.append(this.payPalEmail);
        return sb.toString();
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String getInternalDisplayTitle$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.payPalEmail;
        return str == null ? super.getInternalDisplayTitle$lib_release(context) : str;
    }

    public final String getPayPalEmail() {
        return this.payPalEmail;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.payPalEmail;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(SavedPayPal.class).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', payPalEmail='" + this.payPalEmail + "')";
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedPayPal mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPayPal");
        return (SavedPayPal) mo3512clone;
    }
}
