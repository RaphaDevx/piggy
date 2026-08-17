package io.sentry;

import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.TransactionNameSource;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.SampleRateUtils;
import io.sentry.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class Baggage {
    static final String CHARSET = "UTF-8";
    static final String SENTRY_BAGGAGE_PREFIX = "sentry-";
    private final ConcurrentHashMap<String, String> keyValues;
    private final AutoClosableReentrantLock keyValuesLock;
    final ILogger logger;
    private boolean mutable;
    private Double sampleRand;
    private Double sampleRate;
    private final boolean shouldFreeze;
    private final String thirdPartyHeader;
    static final Integer MAX_BAGGAGE_STRING_LENGTH = 8192;
    static final Integer MAX_BAGGAGE_LIST_MEMBER_COUNT = 64;
    private static final DecimalFormatterThreadLocal decimalFormatter = new DecimalFormatterThreadLocal();

    public static final class DSCKeys {
        public static final String TRACE_ID = "sentry-trace_id";
        public static final String PUBLIC_KEY = "sentry-public_key";
        public static final String RELEASE = "sentry-release";
        public static final String USER_ID = "sentry-user_id";
        public static final String ENVIRONMENT = "sentry-environment";
        public static final String TRANSACTION = "sentry-transaction";
        public static final String SAMPLE_RATE = "sentry-sample_rate";
        public static final String SAMPLE_RAND = "sentry-sample_rand";
        public static final String SAMPLED = "sentry-sampled";
        public static final String REPLAY_ID = "sentry-replay_id";
        public static final List<String> ALL = Arrays.asList(TRACE_ID, PUBLIC_KEY, RELEASE, USER_ID, ENVIRONMENT, TRANSACTION, SAMPLE_RATE, SAMPLE_RAND, SAMPLED, REPLAY_ID);
    }

    private static class DecimalFormatterThreadLocal extends ThreadLocal<DecimalFormat> {
        private DecimalFormatterThreadLocal() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DecimalFormat initialValue() {
            return new DecimalFormat("#.################", DecimalFormatSymbols.getInstance(Locale.ROOT));
        }
    }

    public static Baggage fromHeader(String str) {
        return fromHeader(str, false, ScopesAdapter.getInstance().getOptions().getLogger());
    }

    public static Baggage fromHeader(List<String> list) {
        return fromHeader(list, false, ScopesAdapter.getInstance().getOptions().getLogger());
    }

    public static Baggage fromHeader(String str, ILogger iLogger) {
        return fromHeader(str, false, iLogger);
    }

    public static Baggage fromHeader(List<String> list, ILogger iLogger) {
        return fromHeader(list, false, iLogger);
    }

    public static Baggage fromHeader(List<String> list, boolean z, ILogger iLogger) {
        if (list != null) {
            return fromHeader(StringUtils.join(FirebaseConstants.SEPARATOR, list), z, iLogger);
        }
        return fromHeader((String) null, z, iLogger);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static io.sentry.Baggage fromHeader(java.lang.String r16, boolean r17, io.sentry.ILogger r18) {
        /*
            r1 = r16
            r8 = r18
            java.util.concurrent.ConcurrentHashMap r2 = new java.util.concurrent.ConcurrentHashMap
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.String r4 = ","
            r5 = 0
            if (r1 == 0) goto La5
            r0 = -1
            java.lang.String[] r7 = r1.split(r4, r0)     // Catch: java.lang.Throwable -> L94
            int r9 = r7.length     // Catch: java.lang.Throwable -> L94
            r10 = r5
            r11 = r10
            r12 = 0
            r13 = 0
        L1d:
            if (r10 >= r9) goto L92
            r14 = r7[r10]     // Catch: java.lang.Throwable -> L8f
            java.lang.String r0 = r14.trim()     // Catch: java.lang.Throwable -> L8f
            java.lang.String r15 = "sentry-"
            boolean r0 = r0.startsWith(r15)     // Catch: java.lang.Throwable -> L8f
            if (r0 == 0) goto L80
            java.lang.String r0 = "="
            int r0 = r14.indexOf(r0)     // Catch: java.lang.Throwable -> L73
            java.lang.String r15 = r14.substring(r5, r0)     // Catch: java.lang.Throwable -> L73
            java.lang.String r15 = r15.trim()     // Catch: java.lang.Throwable -> L73
            java.lang.String r5 = decode(r15)     // Catch: java.lang.Throwable -> L73
            int r0 = r0 + 1
            java.lang.String r0 = r14.substring(r0)     // Catch: java.lang.Throwable -> L73
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L73
            java.lang.String r0 = decode(r0)     // Catch: java.lang.Throwable -> L73
            java.lang.String r6 = "sentry-sample_rate"
            boolean r6 = r6.equals(r5)     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = "sentry-sample_rand"
            if (r6 == 0) goto L5c
            java.lang.Double r12 = toDouble(r0)     // Catch: java.lang.Throwable -> L73
            goto L6a
        L5c:
            boolean r6 = r1.equals(r5)     // Catch: java.lang.Throwable -> L73
            if (r6 == 0) goto L67
            java.lang.Double r13 = toDouble(r0)     // Catch: java.lang.Throwable -> L73
            goto L6a
        L67:
            r2.put(r5, r0)     // Catch: java.lang.Throwable -> L73
        L6a:
            boolean r0 = r1.equalsIgnoreCase(r15)     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L89
            r0 = 1
            r11 = r0
            goto L89
        L73:
            r0 = move-exception
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> L8f
            java.lang.String r5 = "Unable to decode baggage key value pair %s"
            java.lang.Object[] r6 = new java.lang.Object[]{r14}     // Catch: java.lang.Throwable -> L8f
            r8.log(r1, r0, r5, r6)     // Catch: java.lang.Throwable -> L8f
            goto L89
        L80:
            if (r17 == 0) goto L89
            java.lang.String r0 = r14.trim()     // Catch: java.lang.Throwable -> L8f
            r3.add(r0)     // Catch: java.lang.Throwable -> L8f
        L89:
            int r10 = r10 + 1
            r5 = 0
            r1 = r16
            goto L1d
        L8f:
            r0 = move-exception
            r5 = r11
            goto L98
        L92:
            r7 = r11
            goto La8
        L94:
            r0 = move-exception
            r5 = 0
            r12 = 0
            r13 = 0
        L98:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.ERROR
            java.lang.String r6 = "Unable to decode baggage header %s"
            java.lang.Object[] r7 = new java.lang.Object[]{r16}
            r8.log(r1, r0, r6, r7)
            r7 = r5
            goto La8
        La5:
            r7 = 0
            r12 = 0
            r13 = 0
        La8:
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto Lb0
            r5 = 0
            goto Lb5
        Lb0:
            java.lang.String r0 = io.sentry.util.StringUtils.join(r4, r3)
            r5 = r0
        Lb5:
            io.sentry.Baggage r0 = new io.sentry.Baggage
            r6 = 1
            r1 = r0
            r3 = r12
            r4 = r13
            r8 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.Baggage.fromHeader(java.lang.String, boolean, io.sentry.ILogger):io.sentry.Baggage");
    }

    public static Baggage fromEvent(SentryBaseEvent sentryBaseEvent, String str, SentryOptions sentryOptions) {
        Baggage baggage = new Baggage(sentryOptions.getLogger());
        SpanContext trace = sentryBaseEvent.getContexts().getTrace();
        baggage.setTraceId(trace != null ? trace.getTraceId().toString() : null);
        baggage.setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        baggage.setRelease(sentryBaseEvent.getRelease());
        baggage.setEnvironment(sentryBaseEvent.getEnvironment());
        baggage.setTransaction(str);
        baggage.setSampleRate(null);
        baggage.setSampled(null);
        baggage.setSampleRand(null);
        Object obj = sentryBaseEvent.getContexts().get("replay_id");
        if (obj != null && !obj.toString().equals(SentryId.EMPTY_ID.toString())) {
            baggage.setReplayId(obj.toString());
            sentryBaseEvent.getContexts().remove("replay_id");
        }
        baggage.freeze();
        return baggage;
    }

    public Baggage(ILogger iLogger) {
        this(new ConcurrentHashMap(), null, null, null, true, false, iLogger);
    }

    public Baggage(Baggage baggage) {
        this(baggage.keyValues, baggage.sampleRate, baggage.sampleRand, baggage.thirdPartyHeader, baggage.mutable, baggage.shouldFreeze, baggage.logger);
    }

    public Baggage(ConcurrentHashMap<String, String> concurrentHashMap, Double d, Double d2, String str, boolean z, boolean z2, ILogger iLogger) {
        this.keyValuesLock = new AutoClosableReentrantLock();
        this.keyValues = concurrentHashMap;
        this.sampleRate = d;
        this.sampleRand = d2;
        this.logger = iLogger;
        this.thirdPartyHeader = str;
        this.mutable = z;
        this.shouldFreeze = z2;
    }

    public void freeze() {
        this.mutable = false;
    }

    public boolean isMutable() {
        return this.mutable;
    }

    public boolean isShouldFreeze() {
        return this.shouldFreeze;
    }

    public String getThirdPartyHeader() {
        return this.thirdPartyHeader;
    }

    public String toHeaderString(String str) {
        String str2;
        int i;
        String str3;
        StringBuilder sb = new StringBuilder();
        if (str != null && !str.isEmpty()) {
            sb.append(str);
            i = StringUtils.countOf(str, ',') + 1;
            str2 = FirebaseConstants.SEPARATOR;
        } else {
            str2 = "";
            i = 0;
        }
        ISentryLifecycleToken acquire = this.keyValuesLock.acquire();
        try {
            TreeSet<String> treeSet = new TreeSet(Collections.list(this.keyValues.keys()));
            if (acquire != null) {
                acquire.close();
            }
            treeSet.add(DSCKeys.SAMPLE_RATE);
            treeSet.add(DSCKeys.SAMPLE_RAND);
            for (String str4 : treeSet) {
                if (DSCKeys.SAMPLE_RATE.equals(str4)) {
                    str3 = sampleRateToString(this.sampleRate);
                } else if (DSCKeys.SAMPLE_RAND.equals(str4)) {
                    str3 = sampleRateToString(this.sampleRand);
                } else {
                    str3 = this.keyValues.get(str4);
                }
                if (str3 != null) {
                    Integer num = MAX_BAGGAGE_LIST_MEMBER_COUNT;
                    if (i >= num.intValue()) {
                        this.logger.log(SentryLevel.ERROR, "Not adding baggage value %s as the total number of list members would exceed the maximum of %s.", str4, num);
                    } else {
                        try {
                            String str5 = str2 + encode(str4) + "=" + encode(str3);
                            int length = sb.length() + str5.length();
                            Integer num2 = MAX_BAGGAGE_STRING_LENGTH;
                            if (length > num2.intValue()) {
                                this.logger.log(SentryLevel.ERROR, "Not adding baggage value %s as the total header value length would exceed the maximum of %s.", str4, num2);
                            } else {
                                i++;
                                sb.append(str5);
                                str2 = FirebaseConstants.SEPARATOR;
                            }
                        } catch (Throwable th) {
                            this.logger.log(SentryLevel.ERROR, th, "Unable to encode baggage key value pair (key=%s,value=%s).", str4, str3);
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Throwable th2) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            throw th2;
        }
    }

    private String encode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static String decode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, "UTF-8");
    }

    public String get(String str) {
        if (str == null) {
            return null;
        }
        return this.keyValues.get(str);
    }

    public String getTraceId() {
        return get(DSCKeys.TRACE_ID);
    }

    public void setTraceId(String str) {
        set(DSCKeys.TRACE_ID, str);
    }

    public String getPublicKey() {
        return get(DSCKeys.PUBLIC_KEY);
    }

    public void setPublicKey(String str) {
        set(DSCKeys.PUBLIC_KEY, str);
    }

    public String getEnvironment() {
        return get(DSCKeys.ENVIRONMENT);
    }

    public void setEnvironment(String str) {
        set(DSCKeys.ENVIRONMENT, str);
    }

    public String getRelease() {
        return get(DSCKeys.RELEASE);
    }

    public void setRelease(String str) {
        set(DSCKeys.RELEASE, str);
    }

    public String getUserId() {
        return get(DSCKeys.USER_ID);
    }

    public void setUserId(String str) {
        set(DSCKeys.USER_ID, str);
    }

    public String getTransaction() {
        return get(DSCKeys.TRANSACTION);
    }

    public void setTransaction(String str) {
        set(DSCKeys.TRANSACTION, str);
    }

    public Double getSampleRate() {
        return this.sampleRate;
    }

    public String getSampled() {
        return get(DSCKeys.SAMPLED);
    }

    public void setSampleRate(Double d) {
        if (isMutable()) {
            this.sampleRate = d;
        }
    }

    public void forceSetSampleRate(Double d) {
        this.sampleRate = d;
    }

    public Double getSampleRand() {
        return this.sampleRand;
    }

    public void setSampleRand(Double d) {
        if (isMutable()) {
            this.sampleRand = d;
        }
    }

    public void setSampled(String str) {
        set(DSCKeys.SAMPLED, str);
    }

    public String getReplayId() {
        return get(DSCKeys.REPLAY_ID);
    }

    public void setReplayId(String str) {
        set(DSCKeys.REPLAY_ID, str);
    }

    public void set(String str, String str2) {
        if (this.mutable) {
            if (str2 == null) {
                this.keyValues.remove(str);
            } else {
                this.keyValues.put(str, str2);
            }
        }
    }

    public Map<String, Object> getUnknown() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ISentryLifecycleToken acquire = this.keyValuesLock.acquire();
        try {
            for (Map.Entry<String, String> entry : this.keyValues.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!DSCKeys.ALL.contains(key) && value != null) {
                    concurrentHashMap.put(key.replaceFirst(SENTRY_BAGGAGE_PREFIX, ""), value);
                }
            }
            if (acquire != null) {
                acquire.close();
            }
            return concurrentHashMap;
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void setValuesFromTransaction(SentryId sentryId, SentryId sentryId2, SentryOptions sentryOptions, TracesSamplingDecision tracesSamplingDecision, String str, TransactionNameSource transactionNameSource) {
        setTraceId(sentryId.toString());
        setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        setRelease(sentryOptions.getRelease());
        setEnvironment(sentryOptions.getEnvironment());
        if (!isHighQualityTransactionName(transactionNameSource)) {
            str = null;
        }
        setTransaction(str);
        if (sentryId2 != null && !SentryId.EMPTY_ID.equals(sentryId2)) {
            setReplayId(sentryId2.toString());
        }
        setSampleRate(sampleRate(tracesSamplingDecision));
        setSampled(StringUtils.toString(sampled(tracesSamplingDecision)));
        setSampleRand(sampleRand(tracesSamplingDecision));
    }

    public void setValuesFromSamplingDecision(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return;
        }
        setSampled(StringUtils.toString(sampled(tracesSamplingDecision)));
        if (tracesSamplingDecision.getSampleRand() != null) {
            setSampleRand(sampleRand(tracesSamplingDecision));
        }
        if (tracesSamplingDecision.getSampleRate() != null) {
            forceSetSampleRate(sampleRate(tracesSamplingDecision));
        }
    }

    public void setValuesFromScope(IScope iScope, SentryOptions sentryOptions) {
        PropagationContext propagationContext = iScope.getPropagationContext();
        SentryId replayId = iScope.getReplayId();
        setTraceId(propagationContext.getTraceId().toString());
        setPublicKey(sentryOptions.retrieveParsedDsn().getPublicKey());
        setRelease(sentryOptions.getRelease());
        setEnvironment(sentryOptions.getEnvironment());
        if (!SentryId.EMPTY_ID.equals(replayId)) {
            setReplayId(replayId.toString());
        }
        setTransaction(null);
        setSampleRate(null);
        setSampled(null);
    }

    private static Double sampleRate(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampleRate();
    }

    private static Double sampleRand(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampleRand();
    }

    private static String sampleRateToString(Double d) {
        if (SampleRateUtils.isValidTracesSampleRate(d, false)) {
            return decimalFormatter.get().format(d);
        }
        return null;
    }

    private static Boolean sampled(TracesSamplingDecision tracesSamplingDecision) {
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampled();
    }

    private static boolean isHighQualityTransactionName(TransactionNameSource transactionNameSource) {
        return (transactionNameSource == null || TransactionNameSource.URL.equals(transactionNameSource)) ? false : true;
    }

    private static Double toDouble(String str) {
        if (str != null) {
            try {
                double parseDouble = Double.parseDouble(str);
                if (SampleRateUtils.isValidTracesSampleRate(Double.valueOf(parseDouble), false)) {
                    return Double.valueOf(parseDouble);
                }
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public TraceContext toTraceContext() {
        String traceId = getTraceId();
        String replayId = getReplayId();
        String publicKey = getPublicKey();
        if (traceId == null || publicKey == null) {
            return null;
        }
        TraceContext traceContext = new TraceContext(new SentryId(traceId), publicKey, getRelease(), getEnvironment(), getUserId(), getTransaction(), sampleRateToString(getSampleRate()), getSampled(), replayId == null ? null : new SentryId(replayId), sampleRateToString(getSampleRand()));
        traceContext.setUnknown(getUnknown());
        return traceContext;
    }
}
