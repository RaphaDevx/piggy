package io.sentry;

import io.sentry.protocol.SentryPackage;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes2.dex */
public final class SentryIntegrationPackageStorage {
    private static volatile SentryIntegrationPackageStorage INSTANCE;
    private final Set<String> integrations = new CopyOnWriteArraySet();
    private final Set<SentryPackage> packages = new CopyOnWriteArraySet();
    private static final AutoClosableReentrantLock staticLock = new AutoClosableReentrantLock();
    private static volatile Boolean mixedVersionsDetected = null;
    private static final AutoClosableReentrantLock mixedVersionsLock = new AutoClosableReentrantLock();

    public static SentryIntegrationPackageStorage getInstance() {
        if (INSTANCE == null) {
            ISentryLifecycleToken acquire = staticLock.acquire();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new SentryIntegrationPackageStorage();
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
        return INSTANCE;
    }

    private SentryIntegrationPackageStorage() {
    }

    public void addIntegration(String str) {
        Objects.requireNonNull(str, "integration is required.");
        this.integrations.add(str);
    }

    public Set<String> getIntegrations() {
        return this.integrations;
    }

    public void addPackage(String str, String str2) {
        Objects.requireNonNull(str, "name is required.");
        Objects.requireNonNull(str2, "version is required.");
        this.packages.add(new SentryPackage(str, str2));
        ISentryLifecycleToken acquire = mixedVersionsLock.acquire();
        try {
            mixedVersionsDetected = null;
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

    public Set<SentryPackage> getPackages() {
        return this.packages;
    }

    public boolean checkForMixedVersions(ILogger iLogger) {
        Boolean bool = mixedVersionsDetected;
        if (bool != null) {
            return bool.booleanValue();
        }
        ISentryLifecycleToken acquire = mixedVersionsLock.acquire();
        try {
            boolean z = false;
            for (SentryPackage sentryPackage : this.packages) {
                if (sentryPackage.getName().startsWith("maven:io.sentry:") && !"8.23.0".equalsIgnoreCase(sentryPackage.getVersion())) {
                    iLogger.log(SentryLevel.ERROR, "The Sentry SDK has been configured with mixed versions. Expected %s to match core SDK version %s but was %s", sentryPackage.getName(), "8.23.0", sentryPackage.getVersion());
                    z = true;
                }
            }
            if (z) {
                iLogger.log(SentryLevel.ERROR, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.log(SentryLevel.ERROR, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.log(SentryLevel.ERROR, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.log(SentryLevel.ERROR, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
            }
            mixedVersionsDetected = Boolean.valueOf(z);
            if (acquire != null) {
                acquire.close();
            }
            return z;
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

    public void clearStorage() {
        this.integrations.clear();
        this.packages.clear();
    }
}
