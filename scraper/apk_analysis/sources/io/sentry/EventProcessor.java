package io.sentry;

import io.sentry.protocol.SentryTransaction;

/* loaded from: classes2.dex */
public interface EventProcessor {
    default Long getOrder() {
        return null;
    }

    default SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        return sentryEvent;
    }

    default SentryLogEvent process(SentryLogEvent sentryLogEvent) {
        return sentryLogEvent;
    }

    default SentryReplayEvent process(SentryReplayEvent sentryReplayEvent, Hint hint) {
        return sentryReplayEvent;
    }

    default SentryTransaction process(SentryTransaction sentryTransaction, Hint hint) {
        return sentryTransaction;
    }
}
