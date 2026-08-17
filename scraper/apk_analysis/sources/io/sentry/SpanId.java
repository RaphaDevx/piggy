package io.sentry;

import io.sentry.util.LazyEvaluator;
import io.sentry.util.StringUtils;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class SpanId implements JsonSerializable {
    public static final SpanId EMPTY_ID = new SpanId(StringUtils.PROPER_NIL_UUID.replace("-", "").substring(0, 16));
    private final LazyEvaluator<String> lazyValue;

    static /* synthetic */ String lambda$new$0(String str) {
        return str;
    }

    public SpanId(final String str) {
        Objects.requireNonNull(str, "value is required");
        this.lazyValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SpanId$$ExternalSyntheticLambda1
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SpanId.lambda$new$0(str);
            }
        });
    }

    public SpanId() {
        this.lazyValue = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SpanId$$ExternalSyntheticLambda0
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SentryUUID.generateSpanId();
            }
        });
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.lazyValue.getValue().equals(((SpanId) obj).lazyValue.getValue());
    }

    public int hashCode() {
        return this.lazyValue.getValue().hashCode();
    }

    public String toString() {
        return this.lazyValue.getValue();
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(this.lazyValue.getValue());
    }

    public static final class Deserializer implements JsonDeserializer<SpanId> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SpanId deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return new SpanId(objectReader.nextString());
        }
    }
}
