package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryUUID;
import io.sentry.util.LazyEvaluator;
import io.sentry.util.StringUtils;
import io.sentry.util.UUIDStringUtils;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class SentryId implements JsonSerializable {
    public static final SentryId EMPTY_ID = new SentryId(StringUtils.PROPER_NIL_UUID.replace("-", ""));
    private final LazyEvaluator<String> lazyStringValue;

    static /* synthetic */ String lambda$new$2(String str) {
        return str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SentryId() {
        this((UUID) null);
    }

    public SentryId(final UUID uuid) {
        if (uuid != null) {
            this.lazyStringValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.protocol.SentryId$$ExternalSyntheticLambda0
                @Override // io.sentry.util.LazyEvaluator.Evaluator
                public final Object evaluate() {
                    return SentryId.this.m3930lambda$new$0$iosentryprotocolSentryId(uuid);
                }
            });
        } else {
            this.lazyStringValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.protocol.SentryId$$ExternalSyntheticLambda1
                @Override // io.sentry.util.LazyEvaluator.Evaluator
                public final Object evaluate() {
                    return SentryUUID.generateSentryId();
                }
            });
        }
    }

    /* renamed from: lambda$new$0$io-sentry-protocol-SentryId, reason: not valid java name */
    /* synthetic */ String m3930lambda$new$0$iosentryprotocolSentryId(UUID uuid) {
        return m3931lambda$new$1$iosentryprotocolSentryId(UUIDStringUtils.toSentryIdString(uuid));
    }

    public SentryId(String str) {
        final String normalizeUUID = StringUtils.normalizeUUID(str);
        if (normalizeUUID.length() != 32 && normalizeUUID.length() != 36) {
            throw new IllegalArgumentException("String representation of SentryId has either 32 (UUID no dashes) or 36 characters long (completed UUID). Received: " + str);
        }
        if (normalizeUUID.length() == 36) {
            this.lazyStringValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.protocol.SentryId$$ExternalSyntheticLambda2
                @Override // io.sentry.util.LazyEvaluator.Evaluator
                public final Object evaluate() {
                    return SentryId.this.m3931lambda$new$1$iosentryprotocolSentryId(normalizeUUID);
                }
            });
        } else {
            this.lazyStringValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.protocol.SentryId$$ExternalSyntheticLambda3
                @Override // io.sentry.util.LazyEvaluator.Evaluator
                public final Object evaluate() {
                    return SentryId.lambda$new$2(normalizeUUID);
                }
            });
        }
    }

    public String toString() {
        return this.lazyStringValue.getValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.lazyStringValue.getValue().equals(((SentryId) obj).lazyStringValue.getValue());
    }

    public int hashCode() {
        return this.lazyStringValue.getValue().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: normalize, reason: merged with bridge method [inline-methods] */
    public String m3931lambda$new$1$iosentryprotocolSentryId(String str) {
        return StringUtils.normalizeUUID(str).replace("-", "");
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(toString());
    }

    public static final class Deserializer implements JsonDeserializer<SentryId> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryId deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return new SentryId(objectReader.nextString());
        }
    }
}
