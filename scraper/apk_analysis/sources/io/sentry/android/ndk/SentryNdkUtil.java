package io.sentry.android.ndk;

import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.protocol.SdkVersion;

/* loaded from: classes2.dex */
final class SentryNdkUtil {
    static {
        SentryIntegrationPackageStorage.getInstance().addPackage("maven:io.sentry:sentry-android-ndk", "8.23.0");
    }

    private SentryNdkUtil() {
    }

    static void addPackage(SdkVersion sdkVersion) {
        if (sdkVersion == null) {
            return;
        }
        sdkVersion.addPackage("maven:io.sentry:sentry-android-ndk", "8.23.0");
    }
}
