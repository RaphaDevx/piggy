package io.sentry.util;

import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/* loaded from: classes3.dex */
public final class DebugMetaPropertiesApplier {
    public static String DEBUG_META_PROPERTIES_FILENAME = "sentry-debug-meta.properties";

    public static void apply(SentryOptions sentryOptions, List<Properties> list) {
        if (list != null) {
            applyToOptions(sentryOptions, list);
            applyBuildTool(sentryOptions, list);
        }
    }

    public static void applyToOptions(SentryOptions sentryOptions, List<Properties> list) {
        if (list != null) {
            applyBundleIds(sentryOptions, list);
            applyProguardUuid(sentryOptions, list);
        }
    }

    private static void applyBundleIds(SentryOptions sentryOptions, List<Properties> list) {
        if (sentryOptions.getBundleIds().isEmpty()) {
            Iterator<Properties> it = list.iterator();
            while (it.hasNext()) {
                String property = it.next().getProperty("io.sentry.bundle-ids");
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "Bundle IDs found: %s", property);
                if (property != null) {
                    for (String str : property.split(FirebaseConstants.SEPARATOR, -1)) {
                        sentryOptions.addBundleId(str);
                    }
                }
            }
        }
    }

    private static void applyProguardUuid(SentryOptions sentryOptions, List<Properties> list) {
        if (sentryOptions.getProguardUuid() == null) {
            Iterator<Properties> it = list.iterator();
            while (it.hasNext()) {
                String proguardUuid = getProguardUuid(it.next());
                if (proguardUuid != null) {
                    sentryOptions.getLogger().log(SentryLevel.DEBUG, "Proguard UUID found: %s", proguardUuid);
                    sentryOptions.setProguardUuid(proguardUuid);
                    return;
                }
            }
        }
    }

    private static void applyBuildTool(SentryOptions sentryOptions, List<Properties> list) {
        for (Properties properties : list) {
            String buildTool = getBuildTool(properties);
            if (buildTool != null) {
                String buildToolVersion = getBuildToolVersion(properties);
                if (buildToolVersion == null) {
                    buildToolVersion = "unknown";
                }
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "Build tool found: %s, version %s", buildTool, buildToolVersion);
                SentryIntegrationPackageStorage.getInstance().addPackage(buildTool, buildToolVersion);
                return;
            }
        }
    }

    public static String getProguardUuid(Properties properties) {
        return properties.getProperty("io.sentry.ProguardUuids");
    }

    public static String getBuildTool(Properties properties) {
        return properties.getProperty("io.sentry.build-tool");
    }

    public static String getBuildToolVersion(Properties properties) {
        return properties.getProperty("io.sentry.build-tool-version");
    }
}
