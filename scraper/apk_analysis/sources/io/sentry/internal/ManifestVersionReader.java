package io.sentry.internal;

import io.sentry.BuildConfig;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.util.AutoClosableReentrantLock;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* loaded from: classes2.dex */
public final class ManifestVersionReader {
    private static volatile ManifestVersionReader INSTANCE;
    private static final AutoClosableReentrantLock staticLock = new AutoClosableReentrantLock();
    private volatile boolean hasManifestBeenRead = false;
    private final VersionInfoHolder versionInfo = new VersionInfoHolder();
    private AutoClosableReentrantLock lock = new AutoClosableReentrantLock();

    public static ManifestVersionReader getInstance() {
        if (INSTANCE == null) {
            ISentryLifecycleToken acquire = staticLock.acquire();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new ManifestVersionReader();
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

    private ManifestVersionReader() {
    }

    public VersionInfoHolder readOpenTelemetryVersion() {
        readManifestFiles();
        if (this.versionInfo.sdkVersion == null) {
            return null;
        }
        return this.versionInfo;
    }

    public void readManifestFiles() {
        ISentryLifecycleToken acquire;
        if (this.hasManifestBeenRead) {
            return;
        }
        try {
            acquire = this.lock.acquire();
            try {
            } finally {
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.hasManifestBeenRead = true;
            throw th;
        }
        if (!this.hasManifestBeenRead) {
            Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                try {
                    Attributes mainAttributes = new Manifest(resources.nextElement().openStream()).getMainAttributes();
                    if (mainAttributes != null) {
                        String value = mainAttributes.getValue("Sentry-Opentelemetry-SDK-Name");
                        String value2 = mainAttributes.getValue("Implementation-Version");
                        String value3 = mainAttributes.getValue("Sentry-SDK-Name");
                        String value4 = mainAttributes.getValue("Sentry-SDK-Package-Name");
                        if (value != null && value2 != null) {
                            this.versionInfo.sdkName = value;
                            this.versionInfo.sdkVersion = value2;
                            String value5 = mainAttributes.getValue("Sentry-Opentelemetry-Version-Name");
                            if (value5 != null) {
                                SentryIntegrationPackageStorage.getInstance().addPackage("maven:io.opentelemetry:opentelemetry-sdk", value5);
                                SentryIntegrationPackageStorage.getInstance().addIntegration("OpenTelemetry");
                            }
                            String value6 = mainAttributes.getValue("Sentry-Opentelemetry-Javaagent-Version-Name");
                            if (value6 != null) {
                                SentryIntegrationPackageStorage.getInstance().addPackage("maven:io.opentelemetry.javaagent:opentelemetry-javaagent", value6);
                                SentryIntegrationPackageStorage.getInstance().addIntegration("OpenTelemetry-Agent");
                            }
                            if (value.equals("sentry.java.opentelemetry.agentless")) {
                                SentryIntegrationPackageStorage.getInstance().addIntegration("OpenTelemetry-Agentless");
                            }
                            if (value.equals("sentry.java.opentelemetry.agentless-spring")) {
                                SentryIntegrationPackageStorage.getInstance().addIntegration("OpenTelemetry-Agentless-Spring");
                            }
                        }
                        if (value3 != null && value2 != null && value4 != null && value3.startsWith(BuildConfig.SENTRY_JAVA_SDK_NAME)) {
                            SentryIntegrationPackageStorage.getInstance().addPackage(value4, value2);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (acquire != null) {
                acquire.close();
            }
            this.hasManifestBeenRead = true;
            return;
        }
        if (acquire != null) {
            acquire.close();
        }
        this.hasManifestBeenRead = true;
    }

    public static final class VersionInfoHolder {
        private volatile String sdkName;
        private volatile String sdkVersion;

        public String getSdkName() {
            return this.sdkName;
        }

        public String getSdkVersion() {
            return this.sdkVersion;
        }
    }
}
