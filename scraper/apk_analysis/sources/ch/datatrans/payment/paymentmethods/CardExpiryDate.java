package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.sentry.protocol.Request;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import javax.ws.rs.Priorities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R*\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0016R*\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0013\u001a\u0004\b\u0018\u0010\u0010\"\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\tR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\t¨\u0006#"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "Ljava/io/Serializable;", "", "month", "year", "<init>", "(II)V", "", "toString", "()Ljava/lang/String;", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "value", "a", "I", "getMonth", "setMonth", "(I)V", "b", "getYear", "setYear", "isValid$lib_release", "()Z", "isValid", "getFormattedMonth", "formattedMonth", "getFormattedYear", "formattedYear", "Companion", "CardExpiryDateSerializer", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class CardExpiryDate implements Serializable {
    public static final String INVALID_MONTH_MESSAGE = "Invalid card expiry month";
    public static final String INVALID_YEAR_MESSAGE = "Invalid card expiry year";
    public static final String MONTH_KEY = "expMonth";
    public static final String YEAR_KEY = "expYear";

    /* renamed from: a, reason: from kotlin metadata */
    public int month;

    /* renamed from: b, reason: from kotlin metadata */
    public int year;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J$\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/CardExpiryDate$CardExpiryDateSerializer;", "Lcom/google/gson/JsonDeserializer;", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "Lcom/google/gson/JsonSerializer;", "<init>", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "serialize", "src", "typeOfSrc", "Lcom/google/gson/JsonSerializationContext;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class CardExpiryDateSerializer implements JsonDeserializer<CardExpiryDate>, JsonSerializer<CardExpiryDate> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public CardExpiryDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(typeOfT, "typeOfT");
            Intrinsics.checkNotNullParameter(context, "context");
            JsonObject asJsonObject = json.getAsJsonObject();
            if (asJsonObject.has("expm") && asJsonObject.has("expy")) {
                return new CardExpiryDate(asJsonObject.get("expm").getAsInt(), asJsonObject.get("expy").getAsInt());
            }
            if (asJsonObject.has(CardExpiryDate.MONTH_KEY) && asJsonObject.has(CardExpiryDate.YEAR_KEY)) {
                return new CardExpiryDate(asJsonObject.get(CardExpiryDate.MONTH_KEY).getAsInt(), asJsonObject.get(CardExpiryDate.YEAR_KEY).getAsInt());
            }
            if (asJsonObject.has(CardExpiryDate.MONTH_KEY) || asJsonObject.has(CardExpiryDate.YEAR_KEY)) {
                throw new IllegalArgumentException((!asJsonObject.has(CardExpiryDate.MONTH_KEY) ? CardExpiryDate.INVALID_MONTH_MESSAGE : CardExpiryDate.INVALID_YEAR_MESSAGE).toString());
            }
            return null;
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(CardExpiryDate src, Type typeOfSrc, JsonSerializationContext context) {
            Intrinsics.checkNotNullParameter(src, "src");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CardExpiryDate.MONTH_KEY, String.valueOf(src.getMonth()));
            jsonObject.addProperty(CardExpiryDate.YEAR_KEY, String.valueOf(src.getYear()));
            return jsonObject;
        }
    }

    public CardExpiryDate(int i, int i2) {
        this.month = i;
        this.year = i2 > 2000 ? i2 : i2 + Priorities.AUTHORIZATION;
        if (1 > i || i >= 13) {
            throw new IllegalArgumentException(INVALID_MONTH_MESSAGE);
        }
        if ((i2 < 0 || i2 >= 100) && i2 <= 0) {
            throw new IllegalArgumentException(INVALID_YEAR_MESSAGE);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(CardExpiryDate.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ch.datatrans.payment.paymentmethods.CardExpiryDate");
        CardExpiryDate cardExpiryDate = (CardExpiryDate) other;
        return this.month == cardExpiryDate.month && this.year == cardExpiryDate.year;
    }

    public final String getFormattedMonth() {
        String valueOf = String.valueOf(this.month);
        return valueOf.length() == 1 ? "0".concat(valueOf) : valueOf;
    }

    public final String getFormattedYear() {
        return String.valueOf(this.year - 2000);
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getYear() {
        return this.year;
    }

    public int hashCode() {
        return (this.month * 31) + this.year;
    }

    public final boolean isValid$lib_release() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month - 1, 0);
        calendar.set(5, calendar.getActualMaximum(5));
        return new Date().before(calendar.getTime());
    }

    public final void setMonth(int i) {
        if (1 > i || i >= 13) {
            throw new IllegalArgumentException(INVALID_MONTH_MESSAGE);
        }
        this.month = i;
    }

    public final void setYear(int i) {
        if ((i < 0 || i >= 100) && i <= 0) {
            throw new IllegalArgumentException(INVALID_YEAR_MESSAGE);
        }
        if (i <= 2000) {
            i += Priorities.AUTHORIZATION;
        }
        this.year = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.month);
        sb.append(JsonPointer.SEPARATOR);
        sb.append(this.year);
        return sb.toString();
    }
}
