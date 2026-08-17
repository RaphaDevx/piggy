package ch.coop.apidia.appGateway.employeeDiscountCard;

import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCreateRequest;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCreateRequestAllOf;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardCredentials;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardGiftcardBalance;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardGiftcardsResponse;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardProtectedDeleteRequest;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardRekaInfo;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Image;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Rest401;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Rest404;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Rest500;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.Rest503;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.RestError;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.RestSuccess;
import ch.coop.apidia.appGateway.employeeDiscountCard.model.SupercardPayLinkedSavedPaymentMethodDeleteResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.GsonFireBuilder;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import okio.ByteString;

/* loaded from: classes3.dex */
public class JSON {
    private static Gson gson = null;
    private static boolean isLenientOnJson = false;
    private static DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
    private static SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    private static OffsetDateTimeTypeAdapter offsetDateTimeTypeAdapter = new OffsetDateTimeTypeAdapter();
    private static LocalDateTypeAdapter localDateTypeAdapter = new LocalDateTypeAdapter();
    private static ByteArrayAdapter byteArrayAdapter = new ByteArrayAdapter();

    public JSON() {
        GsonBuilder createGson = createGson();
        createGson.registerTypeAdapter(Date.class, dateTypeAdapter);
        createGson.registerTypeAdapter(java.sql.Date.class, sqlDateTypeAdapter);
        createGson.registerTypeAdapter(OffsetDateTime.class, offsetDateTimeTypeAdapter);
        createGson.registerTypeAdapter(LocalDate.class, localDateTypeAdapter);
        createGson.registerTypeAdapter(byte[].class, byteArrayAdapter);
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCard.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardCreateRequest.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardCreateRequestAllOf.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardCredentials.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardGiftcardBalance.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardGiftcardsResponse.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardProtectedDeleteRequest.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new EmployeeDiscountCardRekaInfo.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new Image.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new Rest401.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new Rest404.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new Rest500.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new Rest503.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new RestError.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new RestSuccess.CustomTypeAdapterFactory());
        createGson.registerTypeAdapterFactory(new SupercardPayLinkedSavedPaymentMethodDeleteResponse.CustomTypeAdapterFactory());
        gson = createGson.create();
    }

    public static GsonBuilder createGson() {
        return new GsonFireBuilder().createGsonBuilder();
    }

    private static String getDiscriminatorValue(JsonElement jsonElement, String str) {
        JsonElement jsonElement2 = jsonElement.getAsJsonObject().get(str);
        if (jsonElement2 == null) {
            throw new IllegalArgumentException("missing discriminator field: <" + str + ">");
        }
        return jsonElement2.getAsString();
    }

    private static Class getClassByDiscriminator(Map map, String str) {
        Class cls = (Class) map.get(str);
        if (cls != null) {
            return cls;
        }
        throw new IllegalArgumentException("cannot determine model class of name: <" + str + ">");
    }

    public static Gson getGson() {
        return gson;
    }

    public static void setGson(Gson gson2) {
        gson = gson2;
    }

    public static void setLenientOnJson(boolean z) {
        isLenientOnJson = z;
    }

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T deserialize(String str, Type type) {
        try {
            if (isLenientOnJson) {
                JsonReader jsonReader = new JsonReader(new StringReader(str));
                jsonReader.setLenient(true);
                return (T) gson.fromJson(jsonReader, type);
            }
            return (T) gson.fromJson(str, type);
        } catch (JsonParseException e) {
            if (type.equals(String.class)) {
                return str;
            }
            throw e;
        }
    }

    public static class ByteArrayAdapter extends TypeAdapter<byte[]> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, byte[] bArr) throws IOException {
            if (bArr == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(ByteString.of(bArr).base64());
            }
        }

        @Override // com.google.gson.TypeAdapter
        public byte[] read(JsonReader jsonReader) throws IOException {
            if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] == 1) {
                jsonReader.nextNull();
                return null;
            }
            return ByteString.decodeBase64(jsonReader.nextString()).toByteArray();
        }
    }

    /* renamed from: ch.coop.apidia.appGateway.employeeDiscountCard.JSON$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static class OffsetDateTimeTypeAdapter extends TypeAdapter<OffsetDateTime> {
        private DateTimeFormatter formatter;

        public OffsetDateTimeTypeAdapter() {
            this(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        public OffsetDateTimeTypeAdapter(DateTimeFormatter dateTimeFormatter) {
            this.formatter = dateTimeFormatter;
        }

        public void setFormat(DateTimeFormatter dateTimeFormatter) {
            this.formatter = dateTimeFormatter;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, OffsetDateTime offsetDateTime) throws IOException {
            if (offsetDateTime == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(this.formatter.format(offsetDateTime));
            }
        }

        @Override // com.google.gson.TypeAdapter
        public OffsetDateTime read(JsonReader jsonReader) throws IOException {
            if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] == 1) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (nextString.endsWith("+0000")) {
                nextString = nextString.substring(0, nextString.length() - 5) + "Z";
            }
            return OffsetDateTime.parse(nextString, this.formatter);
        }
    }

    public static class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
        private DateTimeFormatter formatter;

        public LocalDateTypeAdapter() {
            this(DateTimeFormatter.ISO_LOCAL_DATE);
        }

        public LocalDateTypeAdapter(DateTimeFormatter dateTimeFormatter) {
            this.formatter = dateTimeFormatter;
        }

        public void setFormat(DateTimeFormatter dateTimeFormatter) {
            this.formatter = dateTimeFormatter;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
            if (localDate == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(this.formatter.format(localDate));
            }
        }

        @Override // com.google.gson.TypeAdapter
        public LocalDate read(JsonReader jsonReader) throws IOException {
            if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] == 1) {
                jsonReader.nextNull();
                return null;
            }
            return LocalDate.parse(jsonReader.nextString(), this.formatter);
        }
    }

    public static void setOffsetDateTimeFormat(DateTimeFormatter dateTimeFormatter) {
        offsetDateTimeTypeAdapter.setFormat(dateTimeFormatter);
    }

    public static void setLocalDateFormat(DateTimeFormatter dateTimeFormatter) {
        localDateTypeAdapter.setFormat(dateTimeFormatter);
    }

    public static class SqlDateTypeAdapter extends TypeAdapter<java.sql.Date> {
        private DateFormat dateFormat;

        public SqlDateTypeAdapter() {
        }

        public SqlDateTypeAdapter(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, java.sql.Date date) throws IOException {
            String date2;
            if (date == null) {
                jsonWriter.nullValue();
                return;
            }
            DateFormat dateFormat = this.dateFormat;
            if (dateFormat != null) {
                date2 = dateFormat.format((Date) date);
            } else {
                date2 = date.toString();
            }
            jsonWriter.value(date2);
        }

        @Override // com.google.gson.TypeAdapter
        public java.sql.Date read(JsonReader jsonReader) throws IOException {
            if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] == 1) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            try {
                if (this.dateFormat != null) {
                    return new java.sql.Date(this.dateFormat.parse(nextString).getTime());
                }
                return new java.sql.Date(ISO8601Utils.parse(nextString, new ParsePosition(0)).getTime());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }

    public static class DateTypeAdapter extends TypeAdapter<Date> {
        private DateFormat dateFormat;

        public DateTypeAdapter() {
        }

        public DateTypeAdapter(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        public void setFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Date date) throws IOException {
            String format;
            if (date == null) {
                jsonWriter.nullValue();
                return;
            }
            DateFormat dateFormat = this.dateFormat;
            if (dateFormat != null) {
                format = dateFormat.format(date);
            } else {
                format = ISO8601Utils.format(date, true);
            }
            jsonWriter.value(format);
        }

        @Override // com.google.gson.TypeAdapter
        public Date read(JsonReader jsonReader) throws IOException {
            try {
                if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] == 1) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                try {
                    DateFormat dateFormat = this.dateFormat;
                    if (dateFormat != null) {
                        return dateFormat.parse(nextString);
                    }
                    return ISO8601Utils.parse(nextString, new ParsePosition(0));
                } catch (ParseException e) {
                    throw new JsonParseException(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new JsonParseException(e2);
            }
        }
    }

    public static void setDateFormat(DateFormat dateFormat) {
        dateTypeAdapter.setFormat(dateFormat);
    }

    public static void setSqlDateFormat(DateFormat dateFormat) {
        sqlDateTypeAdapter.setFormat(dateFormat);
    }
}
