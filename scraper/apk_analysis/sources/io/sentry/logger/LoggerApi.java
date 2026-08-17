package io.sentry.logger;

import io.sentry.HostnameCache;
import io.sentry.IScope;
import io.sentry.ISpan;
import io.sentry.PropagationContext;
import io.sentry.Scopes;
import io.sentry.SentryAttribute;
import io.sentry.SentryAttributeType;
import io.sentry.SentryAttributes;
import io.sentry.SentryDate;
import io.sentry.SentryLevel;
import io.sentry.SentryLogEvent;
import io.sentry.SentryLogEventAttributeValue;
import io.sentry.SentryLogLevel;
import io.sentry.SentryOptions;
import io.sentry.SpanId;
import io.sentry.protocol.SdkVersion;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import io.sentry.util.Platform;
import io.sentry.util.TracingUtils;
import java.util.HashMap;

/* loaded from: classes2.dex */
public final class LoggerApi implements ILoggerApi {
    private final Scopes scopes;

    public LoggerApi(Scopes scopes) {
        this.scopes = scopes;
    }

    @Override // io.sentry.logger.ILoggerApi
    public void trace(String str, Object... objArr) {
        log(SentryLogLevel.TRACE, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void debug(String str, Object... objArr) {
        log(SentryLogLevel.DEBUG, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void info(String str, Object... objArr) {
        log(SentryLogLevel.INFO, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void warn(String str, Object... objArr) {
        log(SentryLogLevel.WARN, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void error(String str, Object... objArr) {
        log(SentryLogLevel.ERROR, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void fatal(String str, Object... objArr) {
        log(SentryLogLevel.FATAL, str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, String str, Object... objArr) {
        captureLog(sentryLogLevel, SentryLogParameters.create(null, null), str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, SentryDate sentryDate, String str, Object... objArr) {
        captureLog(sentryLogLevel, SentryLogParameters.create(sentryDate, null), str, objArr);
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, SentryLogParameters sentryLogParameters, String str, Object... objArr) {
        captureLog(sentryLogLevel, sentryLogParameters, str, objArr);
    }

    private void captureLog(SentryLogLevel sentryLogLevel, SentryLogParameters sentryLogParameters, String str, Object... objArr) {
        SentryOptions options = this.scopes.getOptions();
        try {
            if (!this.scopes.isEnabled()) {
                options.getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'logger' call is a no-op.", new Object[0]);
                return;
            }
            if (!options.getLogs().isEnabled()) {
                options.getLogger().log(SentryLevel.WARNING, "Sentry Log is disabled and this 'logger' call is a no-op.", new Object[0]);
                return;
            }
            if (str == null) {
                return;
            }
            SentryDate timestamp = sentryLogParameters.getTimestamp();
            if (timestamp == null) {
                timestamp = options.getDateProvider().now();
            }
            String maybeFormatMessage = maybeFormatMessage(str, objArr);
            IScope combinedScopeView = this.scopes.getCombinedScopeView();
            PropagationContext propagationContext = combinedScopeView.getPropagationContext();
            ISpan span = combinedScopeView.getSpan();
            if (span == null) {
                TracingUtils.maybeUpdateBaggage(combinedScopeView, options);
            }
            SentryId traceId = span == null ? propagationContext.getTraceId() : span.getSpanContext().getTraceId();
            SpanId spanId = span == null ? propagationContext.getSpanId() : span.getSpanContext().getSpanId();
            SentryLogEvent sentryLogEvent = new SentryLogEvent(traceId, timestamp, maybeFormatMessage, sentryLogLevel);
            sentryLogEvent.setAttributes(createAttributes(sentryLogParameters, str, spanId, objArr));
            sentryLogEvent.setSeverityNumber(Integer.valueOf(sentryLogLevel.getSeverityNumber()));
            this.scopes.getClient().captureLog(sentryLogEvent, combinedScopeView);
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Error while capturing log event", th);
        }
    }

    private String maybeFormatMessage(String str, Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            try {
                return String.format(str, objArr);
            } catch (Throwable th) {
                this.scopes.getOptions().getLogger().log(SentryLevel.ERROR, "Error while running log through String.format", th);
            }
        }
        return str;
    }

    private HashMap<String, SentryLogEventAttributeValue> createAttributes(SentryLogParameters sentryLogParameters, String str, SpanId spanId, Object... objArr) {
        HashMap<String, SentryLogEventAttributeValue> hashMap = new HashMap<>();
        hashMap.put("sentry.origin", new SentryLogEventAttributeValue(SentryAttributeType.STRING, sentryLogParameters.getOrigin()));
        SentryAttributes attributes = sentryLogParameters.getAttributes();
        if (attributes != null) {
            for (SentryAttribute sentryAttribute : attributes.getAttributes().values()) {
                Object value = sentryAttribute.getValue();
                hashMap.put(sentryAttribute.getName(), new SentryLogEventAttributeValue(sentryAttribute.getType() == null ? getType(value) : sentryAttribute.getType(), value));
            }
        }
        if (objArr != null) {
            int i = 0;
            for (Object obj : objArr) {
                hashMap.put("sentry.message.parameter." + i, new SentryLogEventAttributeValue(getType(obj), obj));
                i++;
            }
            if (i > 0 && hashMap.get("sentry.message.template") == null) {
                hashMap.put("sentry.message.template", new SentryLogEventAttributeValue(SentryAttributeType.STRING, str));
            }
        }
        SdkVersion sdkVersion = this.scopes.getOptions().getSdkVersion();
        if (sdkVersion != null) {
            hashMap.put("sentry.sdk.name", new SentryLogEventAttributeValue(SentryAttributeType.STRING, sdkVersion.getName()));
            hashMap.put("sentry.sdk.version", new SentryLogEventAttributeValue(SentryAttributeType.STRING, sdkVersion.getVersion()));
        }
        String environment = this.scopes.getOptions().getEnvironment();
        if (environment != null) {
            hashMap.put("sentry.environment", new SentryLogEventAttributeValue(SentryAttributeType.STRING, environment));
        }
        SentryId replayId = this.scopes.getCombinedScopeView().getReplayId();
        if (!SentryId.EMPTY_ID.equals(replayId)) {
            hashMap.put("sentry.replay_id", new SentryLogEventAttributeValue(SentryAttributeType.STRING, replayId.toString()));
        } else {
            SentryId replayId2 = this.scopes.getOptions().getReplayController().getReplayId();
            if (!SentryId.EMPTY_ID.equals(replayId2)) {
                hashMap.put("sentry.replay_id", new SentryLogEventAttributeValue(SentryAttributeType.STRING, replayId2.toString()));
                hashMap.put("sentry._internal.replay_is_buffering", new SentryLogEventAttributeValue(SentryAttributeType.BOOLEAN, (Object) true));
            }
        }
        String release = this.scopes.getOptions().getRelease();
        if (release != null) {
            hashMap.put("sentry.release", new SentryLogEventAttributeValue(SentryAttributeType.STRING, release));
        }
        hashMap.put("sentry.trace.parent_span_id", new SentryLogEventAttributeValue(SentryAttributeType.STRING, spanId));
        if (Platform.isJvm()) {
            setServerName(hashMap);
        }
        setUser(hashMap);
        return hashMap;
    }

    private void setServerName(HashMap<String, SentryLogEventAttributeValue> hashMap) {
        String hostname;
        SentryOptions options = this.scopes.getOptions();
        String serverName = options.getServerName();
        if (serverName != null) {
            hashMap.put("server.address", new SentryLogEventAttributeValue(SentryAttributeType.STRING, serverName));
        } else {
            if (!options.isAttachServerName() || (hostname = HostnameCache.getInstance().getHostname()) == null) {
                return;
            }
            hashMap.put("server.address", new SentryLogEventAttributeValue(SentryAttributeType.STRING, hostname));
        }
    }

    private void setUser(HashMap<String, SentryLogEventAttributeValue> hashMap) {
        User user = this.scopes.getCombinedScopeView().getUser();
        if (user != null) {
            String id = user.getId();
            if (id != null) {
                hashMap.put("user.id", new SentryLogEventAttributeValue(SentryAttributeType.STRING, id));
            }
            String username = user.getUsername();
            if (username != null) {
                hashMap.put("user.name", new SentryLogEventAttributeValue(SentryAttributeType.STRING, username));
            }
            String email = user.getEmail();
            if (email != null) {
                hashMap.put("user.email", new SentryLogEventAttributeValue(SentryAttributeType.STRING, email));
            }
        }
    }

    private SentryAttributeType getType(Object obj) {
        if (obj instanceof Boolean) {
            return SentryAttributeType.BOOLEAN;
        }
        if (obj instanceof Integer) {
            return SentryAttributeType.INTEGER;
        }
        if (obj instanceof Number) {
            return SentryAttributeType.DOUBLE;
        }
        return SentryAttributeType.STRING;
    }
}
