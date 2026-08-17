package io.gsonfire.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes2.dex */
abstract class DateUnixtimeTypeAdapter extends TypeAdapter<Date> {
    private final boolean allowNegativeTimestamp;

    protected abstract Date fromTimestamp(long j);

    protected abstract long toTimestamp(Date date);

    public DateUnixtimeTypeAdapter(boolean z) {
        this.allowNegativeTimestamp = z;
    }

    @Override // com.google.gson.TypeAdapter
    public final void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date.getTime() >= 0 || this.allowNegativeTimestamp) {
            jsonWriter.value(toTimestamp(date));
        } else {
            jsonWriter.nullValue();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public final Date read(JsonReader jsonReader) throws IOException {
        long nextLong = jsonReader.nextLong();
        if (nextLong >= 0 || this.allowNegativeTimestamp) {
            return fromTimestamp(nextLong);
        }
        return null;
    }
}
