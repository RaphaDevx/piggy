package io.sentry;

import io.sentry.util.Objects;

/* loaded from: classes2.dex */
public final class TracesSampler {
    private final SentryOptions options;

    public TracesSampler(SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "options are required");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.TracesSamplingDecision sample(io.sentry.SamplingContext r11) {
        /*
            r10 = this;
            java.lang.Double r3 = r11.getSampleRand()
            io.sentry.TransactionContext r0 = r11.getTransactionContext()
            io.sentry.TracesSamplingDecision r0 = r0.getSamplingDecision()
            if (r0 == 0) goto L13
            io.sentry.TracesSamplingDecision r10 = io.sentry.util.SampleRateUtils.backfilledSampleRand(r0)
            return r10
        L13:
            io.sentry.SentryOptions r0 = r10.options
            io.sentry.SentryOptions$ProfilesSamplerCallback r0 = r0.getProfilesSampler()
            r1 = 0
            if (r0 == 0) goto L35
            io.sentry.SentryOptions r0 = r10.options     // Catch: java.lang.Throwable -> L27
            io.sentry.SentryOptions$ProfilesSamplerCallback r0 = r0.getProfilesSampler()     // Catch: java.lang.Throwable -> L27
            java.lang.Double r0 = r0.sample(r11)     // Catch: java.lang.Throwable -> L27
            goto L36
        L27:
            r0 = move-exception
            io.sentry.SentryOptions r2 = r10.options
            io.sentry.ILogger r2 = r2.getLogger()
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.ERROR
            java.lang.String r5 = "Error in the 'ProfilesSamplerCallback' callback."
            r2.log(r4, r5, r0)
        L35:
            r0 = r1
        L36:
            if (r0 != 0) goto L3e
            io.sentry.SentryOptions r0 = r10.options
            java.lang.Double r0 = r0.getProfilesSampleRate()
        L3e:
            r5 = r0
            r0 = 0
            if (r5 == 0) goto L4a
            boolean r2 = r10.sample(r5, r3)
            if (r2 == 0) goto L4a
            r2 = 1
            goto L4b
        L4a:
            r2 = r0
        L4b:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            io.sentry.SentryOptions r2 = r10.options
            io.sentry.SentryOptions$TracesSamplerCallback r2 = r2.getTracesSampler()
            if (r2 == 0) goto L82
            io.sentry.SentryOptions r2 = r10.options     // Catch: java.lang.Throwable -> L62
            io.sentry.SentryOptions$TracesSamplerCallback r2 = r2.getTracesSampler()     // Catch: java.lang.Throwable -> L62
            java.lang.Double r2 = r2.sample(r11)     // Catch: java.lang.Throwable -> L62
            goto L71
        L62:
            r2 = move-exception
            io.sentry.SentryOptions r6 = r10.options
            io.sentry.ILogger r6 = r6.getLogger()
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.ERROR
            java.lang.String r8 = "Error in the 'TracesSamplerCallback' callback."
            r6.log(r7, r8, r2)
            r2 = r1
        L71:
            if (r2 == 0) goto L82
            io.sentry.TracesSamplingDecision r11 = new io.sentry.TracesSamplingDecision
            boolean r10 = r10.sample(r2, r3)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r10)
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return r11
        L82:
            io.sentry.TransactionContext r11 = r11.getTransactionContext()
            io.sentry.TracesSamplingDecision r11 = r11.getParentSamplingDecision()
            if (r11 == 0) goto L91
            io.sentry.TracesSamplingDecision r10 = io.sentry.util.SampleRateUtils.backfilledSampleRand(r11)
            return r10
        L91:
            io.sentry.SentryOptions r11 = r10.options
            java.lang.Double r11 = r11.getTracesSampleRate()
            io.sentry.SentryOptions r2 = r10.options
            io.sentry.backpressure.IBackpressureMonitor r2 = r2.getBackpressureMonitor()
            int r2 = r2.getDownsampleFactor()
            double r6 = (double) r2
            r8 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r6 = java.lang.Math.pow(r8, r6)
            java.lang.Double r2 = java.lang.Double.valueOf(r6)
            if (r11 != 0) goto Lb0
            r2 = r1
            goto Lbd
        Lb0:
            double r8 = r11.doubleValue()
            r2.getClass()
            double r8 = r8 / r6
            java.lang.Double r11 = java.lang.Double.valueOf(r8)
            r2 = r11
        Lbd:
            if (r2 == 0) goto Lce
            io.sentry.TracesSamplingDecision r11 = new io.sentry.TracesSamplingDecision
            boolean r10 = r10.sample(r2, r3)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r10)
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return r11
        Lce:
            io.sentry.TracesSamplingDecision r10 = new io.sentry.TracesSamplingDecision
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)
            r5 = 0
            r2 = 0
            r0 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.TracesSampler.sample(io.sentry.SamplingContext):io.sentry.TracesSamplingDecision");
    }

    public boolean sampleSessionProfile(double d) {
        Double profileSessionSampleRate = this.options.getProfileSessionSampleRate();
        return profileSessionSampleRate != null && sample(profileSessionSampleRate, Double.valueOf(d));
    }

    private boolean sample(Double d, Double d2) {
        return d.doubleValue() >= d2.doubleValue();
    }
}
