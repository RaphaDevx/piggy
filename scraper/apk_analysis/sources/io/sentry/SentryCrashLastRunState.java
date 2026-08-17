package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;

/* loaded from: classes2.dex */
public final class SentryCrashLastRunState {
    private static final SentryCrashLastRunState INSTANCE = new SentryCrashLastRunState();
    private Boolean crashedLastRun;
    private final AutoClosableReentrantLock crashedLastRunLock = new AutoClosableReentrantLock();
    private boolean readCrashedLastRun;

    private SentryCrashLastRunState() {
    }

    public static SentryCrashLastRunState getInstance() {
        return INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Boolean isCrashedLastRun(java.lang.String r6, boolean r7) {
        /*
            r5 = this;
            io.sentry.util.AutoClosableReentrantLock r0 = r5.crashedLastRunLock
            io.sentry.ISentryLifecycleToken r0 = r0.acquire()
            boolean r1 = r5.readCrashedLastRun     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L12
            java.lang.Boolean r5 = r5.crashedLastRun     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L11
            r0.close()
        L11:
            return r5
        L12:
            if (r6 != 0) goto L1b
            if (r0 == 0) goto L19
            r0.close()
        L19:
            r5 = 0
            return r5
        L1b:
            r1 = 1
            r5.readCrashedLastRun = r1     // Catch: java.lang.Throwable -> L52
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L52
            java.lang.String r3 = "last_crash"
            r2.<init>(r6, r3)     // Catch: java.lang.Throwable -> L52
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L52
            java.lang.String r4 = ".sentry-native/last_crash"
            r3.<init>(r6, r4)     // Catch: java.lang.Throwable -> L52
            r6 = 0
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> L43
            if (r4 == 0) goto L37
            r2.delete()     // Catch: java.lang.Throwable -> L44
            goto L44
        L37:
            boolean r2 = r3.exists()     // Catch: java.lang.Throwable -> L43
            if (r2 == 0) goto L43
            if (r7 == 0) goto L44
            r3.delete()     // Catch: java.lang.Throwable -> L44
            goto L44
        L43:
            r1 = r6
        L44:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L52
            r5.crashedLastRun = r6     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L4f
            r0.close()
        L4f:
            java.lang.Boolean r5 = r5.crashedLastRun
            return r5
        L52:
            r5 = move-exception
            if (r0 == 0) goto L5d
            r0.close()     // Catch: java.lang.Throwable -> L59
            goto L5d
        L59:
            r6 = move-exception
            r5.addSuppressed(r6)
        L5d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.SentryCrashLastRunState.isCrashedLastRun(java.lang.String, boolean):java.lang.Boolean");
    }

    public void setCrashedLastRun(boolean z) {
        ISentryLifecycleToken acquire = this.crashedLastRunLock.acquire();
        try {
            if (!this.readCrashedLastRun) {
                this.crashedLastRun = Boolean.valueOf(z);
                this.readCrashedLastRun = true;
            }
            if (acquire != null) {
                acquire.close();
            }
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

    public void reset() {
        ISentryLifecycleToken acquire = this.crashedLastRunLock.acquire();
        try {
            this.readCrashedLastRun = false;
            this.crashedLastRun = null;
            if (acquire != null) {
                acquire.close();
            }
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
}
