package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0007J\b\u0010\r\u001a\u00020\u0000H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0005R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedSEPA;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", SavedPaymentMethod.ALIAS_KEY, "", "<init>", "(Ljava/lang/String;)V", "bankCode", "(Ljava/lang/String;Ljava/lang/String;)V", "value", "getAlias", "()Ljava/lang/String;", "setAlias", "getBankCode", "clone", "toString", "Companion", "SavedSEPASerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class SavedSEPA extends SavedPaymentMethod {
    public static final String BANK_CODE_KEY = "bankrouting";
    private static final long serialVersionUID = 20150402;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedSEPA$SavedSEPASerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedSEPA;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedSEPASerializer implements JsonSerializer<SavedSEPA>, JsonDeserializer<SavedSEPA> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedSEPA deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) context.deserialize(json, SavedPaymentMethod.class);
            if (savedPaymentMethod.getType() != PaymentMethodType.SEPA) {
                throw new IllegalStateException("Check failed.");
            }
            JsonObject asJsonObject = json.getAsJsonObject();
            if (!asJsonObject.has(SavedSEPA.BANK_CODE_KEY)) {
                return new SavedSEPA(savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String());
            }
            String str = savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String();
            String asString = asJsonObject.get(SavedSEPA.BANK_CODE_KEY).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
            return new SavedSEPA(str, asString);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedSEPA src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = context.serialize(src, SavedPaymentMethod.class).getAsJsonObject();
            asJsonObject.addProperty(SavedSEPA.BANK_CODE_KEY, src.getBankCode());
            Intrinsics.checkNotNull(asJsonObject);
            return asJsonObject;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SavedSEPA(String alias) {
        super(PaymentMethodType.SEPA, alias);
        Intrinsics.checkNotNullParameter(alias, "alias");
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: getAlias */
    public String getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() {
        return (String) StringsKt.split$default((CharSequence) super.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), new String[]{"||"}, false, 0, 6, (Object) null).get(0);
    }

    public final String getBankCode() {
        List split$default = StringsKt.split$default((CharSequence) super.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), new String[]{"||"}, false, 0, 6, (Object) null);
        if (split$default.size() == 2) {
            return (String) split$default.get(1);
        }
        return null;
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public void setAlias(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        super.setAlias(value);
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    public String toString() {
        return Reflection.getOrCreateKotlinClass(SavedSEPA.class).getSimpleName() + "(alias='" + getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String() + "', type='" + getType() + "', bankRouting='" + getBankCode() + "')";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SavedSEPA(String alias, String bankCode) {
        this(alias + "||" + bankCode);
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(bankCode, "bankCode");
    }

    @Override // ch.datatrans.payment.paymentmethods.SavedPaymentMethod
    /* renamed from: clone */
    public SavedSEPA mo3512clone() {
        SavedPaymentMethod mo3512clone = super.mo3512clone();
        Intrinsics.checkNotNull(mo3512clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedSEPA");
        return (SavedSEPA) mo3512clone;
    }
}
