package io.sentry;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.wallet.WalletConstants;
import com.tealium.remotecommands.RemoteCommand;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes2.dex */
public enum SpanStatus implements JsonSerializable {
    OK(0, 399),
    CANCELLED(499),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    UNKNOWN_ERROR(500),
    INVALID_ARGUMENT(RemoteCommand.Response.STATUS_BAD_REQUEST),
    DEADLINE_EXCEEDED(TypedValues.PositionType.TYPE_PERCENT_HEIGHT),
    NOT_FOUND(404),
    ALREADY_EXISTS(WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR),
    PERMISSION_DENIED(TypedValues.CycleType.TYPE_ALPHA),
    RESOURCE_EXHAUSTED(429),
    FAILED_PRECONDITION(RemoteCommand.Response.STATUS_BAD_REQUEST),
    ABORTED(WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR),
    OUT_OF_RANGE(RemoteCommand.Response.STATUS_BAD_REQUEST),
    UNIMPLEMENTED(TypedValues.PositionType.TYPE_TRANSITION_EASING),
    UNAVAILABLE(TypedValues.PositionType.TYPE_PERCENT_WIDTH),
    DATA_LOSS(500),
    UNAUTHENTICATED(TypedValues.CycleType.TYPE_CURVE_FIT);

    private final int maxHttpStatusCode;
    private final int minHttpStatusCode;

    SpanStatus(int i) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i;
    }

    SpanStatus(int i, int i2) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i2;
    }

    public static SpanStatus fromHttpStatusCode(int i) {
        for (SpanStatus spanStatus : values()) {
            if (spanStatus.matches(i)) {
                return spanStatus;
            }
        }
        return null;
    }

    public static SpanStatus fromHttpStatusCode(Integer num, SpanStatus spanStatus) {
        SpanStatus fromHttpStatusCode = num != null ? fromHttpStatusCode(num.intValue()) : spanStatus;
        return fromHttpStatusCode != null ? fromHttpStatusCode : spanStatus;
    }

    private boolean matches(int i) {
        return i >= this.minHttpStatusCode && i <= this.maxHttpStatusCode;
    }

    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static SpanStatus fromApiNameSafely(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(apiName());
    }

    public static final class Deserializer implements JsonDeserializer<SpanStatus> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SpanStatus deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            return SpanStatus.valueOf(objectReader.nextString().toUpperCase(Locale.ROOT));
        }
    }
}
