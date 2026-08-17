package ch.datatrans.payment.paymentmethods;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.H6;
import ch.datatrans.payment.InterfaceC0235ud;
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
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0016\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0002,-B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0010¢\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000bH\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0015\u0010\u000eJ\u000f\u0010\u0016\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b\u0018\u0010\u0017J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\"\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\u001b8\u0016X\u0096D¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b)\u0010+¨\u0006."}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "Ljava/io/Serializable;", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "type", "", SavedPaymentMethod.ALIAS_KEY, "<init>", "(Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;)V", "clone", "()Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "Landroid/content/Context;", "context", "getDisplayTitle", "(Landroid/content/Context;)Ljava/lang/String;", "getInternalDisplayTitle$lib_release", "getInternalDisplayTitle", "", "getLogo$lib_release", "(Landroid/content/Context;)I", "getLogo", "getAccessibilityTitle", "toString", "()Ljava/lang/String;", "toJson", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "a", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getType", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "b", "Ljava/lang/String;", "getAlias", "setAlias", "(Ljava/lang/String;)V", "isValid", "Z", "()Z", "Companion", "SavedPaymentMethodSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class SavedPaymentMethod implements Serializable, Cloneable {
    public static final String ALIAS_KEY = "alias";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 20110112;

    /* renamed from: a, reason: from kotlin metadata and from toString */
    public final PaymentMethodType type;

    /* renamed from: b, reason: from kotlin metadata */
    public String alias;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPaymentMethod$SavedPaymentMethodSerializer;", "Lcom/google/gson/JsonSerializer;", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "Lcom/google/gson/JsonDeserializer;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "deserialize", "json", "typeOfT", "Lcom/google/gson/JsonDeserializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SavedPaymentMethodSerializer implements JsonSerializer<SavedPaymentMethod>, JsonDeserializer<SavedPaymentMethod> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public SavedPaymentMethod deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            PaymentMethodType paymentMethodType = (PaymentMethodType) context.deserialize(json, PaymentMethodType.class);
            JsonObject asJsonObject = json.getAsJsonObject();
            String str = asJsonObject.has("aliasCC") ? "aliasCC" : SavedPaymentMethod.ALIAS_KEY;
            if (!asJsonObject.has(str)) {
                throw new IllegalArgumentException(R5.a(str).toString());
            }
            Intrinsics.checkNotNull(paymentMethodType);
            String asString = asJsonObject.get(str).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
            return new SavedPaymentMethod(paymentMethodType, asString);
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(SavedPaymentMethod src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(typeOfSrc, "typeOfSrc");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(PaymentMethodType.PAYMENT_METHOD_KEY, context.serialize(src.getType()));
            jsonObject.addProperty(SavedPaymentMethod.ALIAS_KEY, src.getAlias());
            return jsonObject;
        }
    }

    public SavedPaymentMethod(PaymentMethodType type, String alias) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(alias, "alias");
        this.type = type;
        this.alias = alias;
    }

    @JvmStatic
    public static final SavedPaymentMethod create(String str) {
        return INSTANCE.create(str);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPaymentMethod");
        SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) other;
        return this.type == savedPaymentMethod.type && Intrinsics.areEqual(getAlias(), savedPaymentMethod.getAlias());
    }

    public String getAccessibilityTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getInternalDisplayTitle$lib_release(context);
    }

    public String getAlias() {
        return this.alias;
    }

    public String getDisplayTitle(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getInternalDisplayTitle$lib_release(context);
    }

    public String getInternalDisplayTitle$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        InterfaceC0235ud uiString = y4.a(this.type);
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uiString, "uiString");
        return uiString.a(context);
    }

    public int getLogo$lib_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.type.getLogo();
    }

    public final PaymentMethodType getType() {
        return this.type;
    }

    public int hashCode() {
        return getAlias().hashCode() + (this.type.hashCode() * 31);
    }

    public boolean isValid() {
        return true;
    }

    public void setAlias(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.alias = str;
    }

    public final String toJson() {
        Object value = r4.g.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        String json = ((Gson) value).toJson(this);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        return json;
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + "(alias='" + getAlias() + "', type='" + this.type + "')";
    }

    @JvmStatic
    public static final SavedPaymentMethod create(byte[] bArr) {
        return INSTANCE.create(bArr);
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SavedPaymentMethod mo3512clone() {
        try {
            Object clone = super.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPaymentMethod");
            return (SavedPaymentMethod) clone;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0005\u0010\tR\u0014\u0010\n\u001a\u00020\u00028\u0000X\u0080T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/SavedPaymentMethod$Companion;", "", "", "jsonString", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "create", "(Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "", "legacySavedPaymentMethodData", "([B)Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "ALIAS_KEY", "Ljava/lang/String;", "ALIAS_NOT_SUPPORTED_MESSAGE", "UNSUPPORTED_SERIALIZED_PAYMENTMETHOD_MESSAGE", "", "serialVersionUID", "J", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final SavedPaymentMethod create(String jsonString) {
            Intrinsics.checkNotNullParameter(jsonString, "jsonString");
            try {
                Lazy lazy = r4.g;
                Object value = lazy.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                PaymentMethodType paymentMethodType = (PaymentMethodType) ((Gson) value).fromJson(jsonString, PaymentMethodType.class);
                if (paymentMethodType == null) {
                    throw new IllegalArgumentException("A serialized payment method could not be restored.");
                }
                Class<? extends SavedPaymentMethod> savedPaymentMethodClass$lib_release = paymentMethodType.getSavedPaymentMethodClass$lib_release();
                if (savedPaymentMethodClass$lib_release == null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("Payment method '%s' does not support alias registration.", Arrays.copyOf(new Object[]{paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    throw new IllegalArgumentException(format);
                }
                Object value2 = lazy.getValue();
                Intrinsics.checkNotNullExpressionValue(value2, "getValue(...)");
                Object fromJson = ((Gson) value2).fromJson(jsonString, (Class<Object>) savedPaymentMethodClass$lib_release);
                Intrinsics.checkNotNull(fromJson, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.SavedPaymentMethod");
                SavedPaymentMethod savedPaymentMethod = (SavedPaymentMethod) fromJson;
                if (savedPaymentMethod.getAlias() != null) {
                    return savedPaymentMethod;
                }
                throw new IllegalArgumentException(R5.a(SavedPaymentMethod.ALIAS_KEY).toString());
            } catch (IllegalArgumentException e) {
                String localizedMessage = e.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                Log.e("DTPL", localizedMessage);
                return null;
            } catch (Exception e2) {
                Log.e("DTPL", "A serialized payment method could not be restored: " + e2.getLocalizedMessage());
                return null;
            }
        }

        @JvmStatic
        public final SavedPaymentMethod create(byte[] legacySavedPaymentMethodData) {
            Intrinsics.checkNotNullParameter(legacySavedPaymentMethodData, "legacySavedPaymentMethodData");
            try {
                Object readObject = new H6(new ByteArrayInputStream(legacySavedPaymentMethodData)).readObject();
                Intrinsics.checkNotNull(readObject, "null cannot be cast to non-null type ch.datatrans.payment.legacy.LegacyAliasPaymentMethod");
                if (readObject == null) {
                    Intrinsics.checkNotNullParameter(null, "method");
                    throw null;
                }
                throw new ClassCastException();
            } catch (Exception e) {
                Log.e("DTPL", "Could not read serialized legacy saved payment method: " + e.getLocalizedMessage());
                return null;
            }
        }
    }
}
