package io.sentry.android.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.salesforce.marketingcloud.UrlHandler;
import io.sentry.Breadcrumb;
import io.sentry.DateUtils;
import io.sentry.Hint;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.SentryDateProvider;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.AndroidConnectionStatusProvider;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class NetworkBreadcrumbsIntegration implements Integration, Closeable {
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();
    volatile NetworkBreadcrumbsNetworkCallback networkCallback;
    private SentryOptions options;

    public NetworkBreadcrumbsIntegration(Context context, BuildInfoProvider buildInfoProvider) {
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "Context is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
    }

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        Objects.requireNonNull(iScopes, "Scopes are required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryOptions;
        sentryOptions.getLogger().log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
        if (sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()) {
            if (this.buildInfoProvider.getSdkInfoVersion() < 24) {
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
                return;
            }
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                this.networkCallback = new NetworkBreadcrumbsNetworkCallback(iScopes, this.buildInfoProvider, sentryOptions.getDateProvider());
                if (AndroidConnectionStatusProvider.addNetworkCallback(this.context, sentryOptions.getLogger(), this.buildInfoProvider, this.networkCallback)) {
                    sentryOptions.getLogger().log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration installed.", new Object[0]);
                    IntegrationUtils.addIntegrationToSdkVersion("NetworkBreadcrumbs");
                } else {
                    sentryOptions.getLogger().log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration not installed.", new Object[0]);
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
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            NetworkBreadcrumbsNetworkCallback networkBreadcrumbsNetworkCallback = this.networkCallback;
            this.networkCallback = null;
            if (acquire != null) {
                acquire.close();
            }
            if (networkBreadcrumbsNetworkCallback != null) {
                AndroidConnectionStatusProvider.removeNetworkCallback(networkBreadcrumbsNetworkCallback);
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

    static final class NetworkBreadcrumbsNetworkCallback extends ConnectivityManager.NetworkCallback {
        final BuildInfoProvider buildInfoProvider;
        final SentryDateProvider dateProvider;
        NetworkCapabilities lastCapabilities = null;
        long lastCapabilityNanos = 0;
        final IScopes scopes;

        NetworkBreadcrumbsNetworkCallback(IScopes iScopes, BuildInfoProvider buildInfoProvider, SentryDateProvider sentryDateProvider) {
            this.scopes = (IScopes) Objects.requireNonNull(iScopes, "Scopes are required");
            this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
            this.dateProvider = (SentryDateProvider) Objects.requireNonNull(sentryDateProvider, "SentryDateProvider is required");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            this.scopes.addBreadcrumb(createBreadcrumb("NETWORK_AVAILABLE"));
            this.lastCapabilities = null;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            long nanoTimestamp = this.dateProvider.now().nanoTimestamp();
            NetworkBreadcrumbConnectionDetail newConnectionDetails = getNewConnectionDetails(this.lastCapabilities, networkCapabilities, this.lastCapabilityNanos, nanoTimestamp);
            if (newConnectionDetails == null) {
                return;
            }
            this.lastCapabilities = networkCapabilities;
            this.lastCapabilityNanos = nanoTimestamp;
            Breadcrumb createBreadcrumb = createBreadcrumb("NETWORK_CAPABILITIES_CHANGED");
            createBreadcrumb.setData("download_bandwidth", Integer.valueOf(newConnectionDetails.downBandwidth));
            createBreadcrumb.setData("upload_bandwidth", Integer.valueOf(newConnectionDetails.upBandwidth));
            createBreadcrumb.setData("vpn_active", Boolean.valueOf(newConnectionDetails.isVpn));
            createBreadcrumb.setData("network_type", newConnectionDetails.type);
            if (newConnectionDetails.signalStrength != 0) {
                createBreadcrumb.setData("signal_strength", Integer.valueOf(newConnectionDetails.signalStrength));
            }
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_NETWORK_CAPABILITIES, newConnectionDetails);
            this.scopes.addBreadcrumb(createBreadcrumb, hint);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            this.scopes.addBreadcrumb(createBreadcrumb("NETWORK_LOST"));
            this.lastCapabilities = null;
        }

        private Breadcrumb createBreadcrumb(String str) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setType("system");
            breadcrumb.setCategory("network.event");
            breadcrumb.setData(UrlHandler.ACTION, str);
            breadcrumb.setLevel(SentryLevel.INFO);
            return breadcrumb;
        }

        private NetworkBreadcrumbConnectionDetail getNewConnectionDetails(NetworkCapabilities networkCapabilities, NetworkCapabilities networkCapabilities2, long j, long j2) {
            if (networkCapabilities == null) {
                return new NetworkBreadcrumbConnectionDetail(networkCapabilities2, this.buildInfoProvider, j2);
            }
            NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail = new NetworkBreadcrumbConnectionDetail(networkCapabilities, this.buildInfoProvider, j);
            NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail2 = new NetworkBreadcrumbConnectionDetail(networkCapabilities2, this.buildInfoProvider, j2);
            if (networkBreadcrumbConnectionDetail.isSimilar(networkBreadcrumbConnectionDetail2)) {
                return null;
            }
            return networkBreadcrumbConnectionDetail2;
        }
    }

    static class NetworkBreadcrumbConnectionDetail {
        final int downBandwidth;
        final boolean isVpn;
        final int signalStrength;
        private long timestampNanos;
        final String type;
        final int upBandwidth;

        NetworkBreadcrumbConnectionDetail(NetworkCapabilities networkCapabilities, BuildInfoProvider buildInfoProvider, long j) {
            Objects.requireNonNull(networkCapabilities, "NetworkCapabilities is required");
            Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
            this.downBandwidth = networkCapabilities.getLinkDownstreamBandwidthKbps();
            this.upBandwidth = networkCapabilities.getLinkUpstreamBandwidthKbps();
            int signalStrength = buildInfoProvider.getSdkInfoVersion() >= 29 ? networkCapabilities.getSignalStrength() : 0;
            this.signalStrength = signalStrength > -100 ? signalStrength : 0;
            this.isVpn = networkCapabilities.hasTransport(4);
            String connectionType = AndroidConnectionStatusProvider.getConnectionType(networkCapabilities);
            this.type = connectionType == null ? "" : connectionType;
            this.timestampNanos = j;
        }

        boolean isSimilar(NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail) {
            int abs = Math.abs(this.signalStrength - networkBreadcrumbConnectionDetail.signalStrength);
            int abs2 = Math.abs(this.downBandwidth - networkBreadcrumbConnectionDetail.downBandwidth);
            int abs3 = Math.abs(this.upBandwidth - networkBreadcrumbConnectionDetail.upBandwidth);
            boolean z = DateUtils.nanosToMillis((double) Math.abs(this.timestampNanos - networkBreadcrumbConnectionDetail.timestampNanos)) < 5000.0d;
            return this.isVpn == networkBreadcrumbConnectionDetail.isVpn && this.type.equals(networkBreadcrumbConnectionDetail.type) && (z || abs <= 5) && (z || (((double) abs2) > Math.max(1000.0d, ((double) Math.abs(this.downBandwidth)) * 0.1d) ? 1 : (((double) abs2) == Math.max(1000.0d, ((double) Math.abs(this.downBandwidth)) * 0.1d) ? 0 : -1)) <= 0) && (z || (((double) abs3) > Math.max(1000.0d, ((double) Math.abs(this.upBandwidth)) * 0.1d) ? 1 : (((double) abs3) == Math.max(1000.0d, ((double) Math.abs(this.upBandwidth)) * 0.1d) ? 0 : -1)) <= 0);
        }
    }
}
