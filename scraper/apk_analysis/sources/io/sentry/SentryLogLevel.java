package io.sentry;

import java.io.IOException;
import java.util.Locale;

/* loaded from: classes2.dex */
public enum SentryLogLevel implements JsonSerializable {
    TRACE(1),
    DEBUG(5),
    INFO(9),
    WARN(13),
    ERROR(17),
    FATAL(21);

    private final int severityNumber;

    SentryLogLevel(int i) {
        this.severityNumber = i;
    }

    public int getSeverityNumber() {
        return this.severityNumber;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(name().toLowerCase(Locale.ROOT));
    }

    public static final class Deserializer implements JsonDeserializer<SentryLogLevel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryLogLevel deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return SentryLogLevel.valueOf(objectReader.nextString().toUpperCase(Locale.ROOT));
        }
    }
}
