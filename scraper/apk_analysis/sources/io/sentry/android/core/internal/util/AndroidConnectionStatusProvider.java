package io.sentry.android.core.internal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import io.sentry.IConnectionStatusProvider;
import io.sentry.ILogger;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.AppState;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.android.core.ContextUtils;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.util.AutoClosableReentrantLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public final class AndroidConnectionStatusProvider implements IConnectionStatusProvider, AppState.AppStateListener {
    private static final long CACHE_TTL_MS = 120000;
    private static volatile ConnectivityManager connectivityManager;
    private final BuildInfoProvider buildInfoProvider;
    private volatile NetworkCapabilities cachedNetworkCapabilities;
    private final Context context;
    private volatile Network currentNetwork;
    private volatile ConnectivityManager.NetworkCallback networkCallback;
    private final SentryOptions options;
    private final ICurrentDateProvider timeProvider;
    private static final AutoClosableReentrantLock connectivityManagerLock = new AutoClosableReentrantLock();
    private static final AutoClosableReentrantLock childCallbacksLock = new AutoClosableReentrantLock();
    private static final List<ConnectivityManager.NetworkCallback> childCallbacks = new ArrayList();
    private static final int[] transports = {1, 0, 3, 2};
    private static final int[] capabilities = new int[2];
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();
    private volatile long lastCacheUpdateTime = 0;
    private final AtomicBoolean isConnected = new AtomicBoolean(false);
    private final List<IConnectionStatusProvider.IConnectionStatusObserver> connectionStatusObservers = new ArrayList();

    public AndroidConnectionStatusProvider(Context context, SentryOptions sentryOptions, BuildInfoProvider buildInfoProvider, ICurrentDateProvider iCurrentDateProvider) {
        this.context = ContextUtils.getApplicationContext(context);
        this.options = sentryOptions;
        this.buildInfoProvider = buildInfoProvider;
        this.timeProvider = iCurrentDateProvider;
        int[] iArr = capabilities;
        iArr[0] = 12;
        if (buildInfoProvider.getSdkInfoVersion() >= 23) {
            iArr[1] = 16;
        }
        submitSafe(new Runnable() { // from class: io.sentry.android.core.internal.util.AndroidConnectionStatusProvider$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AndroidConnectionStatusProvider.this.m3877x4aaee1e1();
            }
        });
        AppState.getInstance().addAppStateListener(this);
    }

    private boolean isNetworkEffectivelyConnected(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return false;
        }
        boolean hasCapability = networkCapabilities.hasCapability(12);
        if (this.buildInfoProvider.getSdkInfoVersion() >= 23) {
            hasCapability = hasCapability && networkCapabilities.hasCapability(16);
        }
        if (!hasCapability) {
            return false;
        }
        for (int i : transports) {
            if (networkCapabilities.hasTransport(i)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IConnectionStatusProvider.ConnectionStatus getConnectionStatusFromCache() {
        if (this.cachedNetworkCapabilities != null) {
            if (isNetworkEffectivelyConnected(this.cachedNetworkCapabilities)) {
                return IConnectionStatusProvider.ConnectionStatus.CONNECTED;
            }
            return IConnectionStatusProvider.ConnectionStatus.DISCONNECTED;
        }
        ConnectivityManager connectivityManager2 = getConnectivityManager(this.context, this.options.getLogger());
        if (connectivityManager2 != null) {
            return getConnectionStatus(this.context, connectivityManager2, this.options.getLogger());
        }
        return IConnectionStatusProvider.ConnectionStatus.UNKNOWN;
    }

    private String getConnectionTypeFromCache() {
        NetworkCapabilities networkCapabilities = this.cachedNetworkCapabilities;
        if (networkCapabilities != null) {
            return getConnectionType(networkCapabilities);
        }
        return getConnectionType(this.context, this.options.getLogger(), this.buildInfoProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ensureNetworkCallbackRegistered, reason: merged with bridge method [inline-methods] */
    public void m3877x4aaee1e1() {
        if (ContextUtils.isForegroundImportance() && this.networkCallback == null) {
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                if (this.networkCallback != null) {
                    if (acquire != null) {
                        acquire.close();
                        return;
                    }
                    return;
                }
                ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: io.sentry.android.core.internal.util.AndroidConnectionStatusProvider.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        AndroidConnectionStatusProvider.this.currentNetwork = network;
                        if (AndroidConnectionStatusProvider.this.isConnected.getAndSet(true)) {
                            return;
                        }
                        ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.childCallbacksLock.acquire();
                        try {
                            Iterator it = AndroidConnectionStatusProvider.childCallbacks.iterator();
                            while (it.hasNext()) {
                                ((ConnectivityManager.NetworkCallback) it.next()).onAvailable(network);
                            }
                            if (acquire2 != null) {
                                acquire2.close();
                            }
                        } catch (Throwable th) {
                            if (acquire2 != null) {
                                try {
                                    acquire2.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onUnavailable() {
                        clearCacheAndNotifyObservers();
                        ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.childCallbacksLock.acquire();
                        try {
                            Iterator it = AndroidConnectionStatusProvider.childCallbacks.iterator();
                            while (it.hasNext()) {
                                ((ConnectivityManager.NetworkCallback) it.next()).onUnavailable();
                            }
                            if (acquire2 != null) {
                                acquire2.close();
                            }
                        } catch (Throwable th) {
                            if (acquire2 != null) {
                                try {
                                    acquire2.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onLost(Network network) {
                        if (network.equals(AndroidConnectionStatusProvider.this.currentNetwork)) {
                            clearCacheAndNotifyObservers();
                            ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.childCallbacksLock.acquire();
                            try {
                                Iterator it = AndroidConnectionStatusProvider.childCallbacks.iterator();
                                while (it.hasNext()) {
                                    ((ConnectivityManager.NetworkCallback) it.next()).onLost(network);
                                }
                                if (acquire2 != null) {
                                    acquire2.close();
                                }
                            } catch (Throwable th) {
                                if (acquire2 != null) {
                                    try {
                                        acquire2.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        }
                    }

                    private void clearCacheAndNotifyObservers() {
                        AndroidConnectionStatusProvider.this.isConnected.set(false);
                        ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.this.lock.acquire();
                        try {
                            AndroidConnectionStatusProvider.this.cachedNetworkCapabilities = null;
                            AndroidConnectionStatusProvider.this.currentNetwork = null;
                            AndroidConnectionStatusProvider androidConnectionStatusProvider = AndroidConnectionStatusProvider.this;
                            androidConnectionStatusProvider.lastCacheUpdateTime = androidConnectionStatusProvider.timeProvider.getCurrentTimeMillis();
                            AndroidConnectionStatusProvider.this.options.getLogger().log(SentryLevel.DEBUG, "Cache cleared - network lost/unavailable", new Object[0]);
                            Iterator it = AndroidConnectionStatusProvider.this.connectionStatusObservers.iterator();
                            while (it.hasNext()) {
                                ((IConnectionStatusProvider.IConnectionStatusObserver) it.next()).onConnectionStatusChanged(IConnectionStatusProvider.ConnectionStatus.DISCONNECTED);
                            }
                            if (acquire2 != null) {
                                acquire2.close();
                            }
                        } catch (Throwable th) {
                            if (acquire2 != null) {
                                try {
                                    acquire2.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                        if (network.equals(AndroidConnectionStatusProvider.this.currentNetwork)) {
                            updateCacheAndNotifyObservers(network, networkCapabilities);
                            ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.childCallbacksLock.acquire();
                            try {
                                Iterator it = AndroidConnectionStatusProvider.childCallbacks.iterator();
                                while (it.hasNext()) {
                                    ((ConnectivityManager.NetworkCallback) it.next()).onCapabilitiesChanged(network, networkCapabilities);
                                }
                                if (acquire2 != null) {
                                    acquire2.close();
                                }
                            } catch (Throwable th) {
                                if (acquire2 != null) {
                                    try {
                                        acquire2.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        }
                    }

                    private void updateCacheAndNotifyObservers(Network network, NetworkCapabilities networkCapabilities) {
                        if (isSignificantChange(networkCapabilities)) {
                            AndroidConnectionStatusProvider.this.updateCache(networkCapabilities);
                            IConnectionStatusProvider.ConnectionStatus connectionStatusFromCache = AndroidConnectionStatusProvider.this.getConnectionStatusFromCache();
                            ISentryLifecycleToken acquire2 = AndroidConnectionStatusProvider.this.lock.acquire();
                            try {
                                Iterator it = AndroidConnectionStatusProvider.this.connectionStatusObservers.iterator();
                                while (it.hasNext()) {
                                    ((IConnectionStatusProvider.IConnectionStatusObserver) it.next()).onConnectionStatusChanged(connectionStatusFromCache);
                                }
                                if (acquire2 != null) {
                                    acquire2.close();
                                }
                            } catch (Throwable th) {
                                if (acquire2 != null) {
                                    try {
                                        acquire2.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        }
                    }

                    private boolean isSignificantChange(NetworkCapabilities networkCapabilities) {
                        NetworkCapabilities networkCapabilities2 = AndroidConnectionStatusProvider.this.cachedNetworkCapabilities;
                        if ((networkCapabilities2 == null) != (networkCapabilities == null)) {
                            return true;
                        }
                        if (networkCapabilities2 == null && networkCapabilities == null) {
                            return false;
                        }
                        return hasSignificantCapabilityChanges(networkCapabilities2, networkCapabilities) || hasSignificantTransportChanges(networkCapabilities2, networkCapabilities);
                    }

                    private boolean hasSignificantCapabilityChanges(NetworkCapabilities networkCapabilities, NetworkCapabilities networkCapabilities2) {
                        for (int i : AndroidConnectionStatusProvider.capabilities) {
                            if (i != 0 && networkCapabilities.hasCapability(i) != networkCapabilities2.hasCapability(i)) {
                                return true;
                            }
                        }
                        return false;
                    }

                    private boolean hasSignificantTransportChanges(NetworkCapabilities networkCapabilities, NetworkCapabilities networkCapabilities2) {
                        for (int i : AndroidConnectionStatusProvider.transports) {
                            if (networkCapabilities.hasTransport(i) != networkCapabilities2.hasTransport(i)) {
                                return true;
                            }
                        }
                        return false;
                    }
                };
                if (registerNetworkCallback(this.context, this.options.getLogger(), this.buildInfoProvider, networkCallback)) {
                    this.networkCallback = networkCallback;
                    this.options.getLogger().log(SentryLevel.DEBUG, "Network callback registered successfully", new Object[0]);
                } else {
                    this.options.getLogger().log(SentryLevel.WARNING, "Failed to register network callback", new Object[0]);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCache(NetworkCapabilities networkCapabilities) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (networkCapabilities != null) {
                this.cachedNetworkCapabilities = networkCapabilities;
            } else {
                if (!Permissions.hasPermission(this.context, "android.permission.ACCESS_NETWORK_STATE")) {
                    this.options.getLogger().log(SentryLevel.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
                    this.cachedNetworkCapabilities = null;
                    this.lastCacheUpdateTime = this.timeProvider.getCurrentTimeMillis();
                    if (acquire != null) {
                        acquire.close();
                        return;
                    }
                    return;
                }
                if (this.buildInfoProvider.getSdkInfoVersion() < 23) {
                    this.cachedNetworkCapabilities = null;
                    this.lastCacheUpdateTime = this.timeProvider.getCurrentTimeMillis();
                    if (acquire != null) {
                        acquire.close();
                        return;
                    }
                    return;
                }
                ConnectivityManager connectivityManager2 = getConnectivityManager(this.context, this.options.getLogger());
                if (connectivityManager2 != null) {
                    Network activeNetwork = connectivityManager2.getActiveNetwork();
                    this.cachedNetworkCapabilities = activeNetwork != null ? connectivityManager2.getNetworkCapabilities(activeNetwork) : null;
                } else {
                    this.cachedNetworkCapabilities = null;
                }
            }
            this.lastCacheUpdateTime = this.timeProvider.getCurrentTimeMillis();
            this.options.getLogger().log(SentryLevel.DEBUG, "Cache updated - Status: " + getConnectionStatusFromCache() + ", Type: " + getConnectionTypeFromCache(), new Object[0]);
        } catch (Throwable th) {
            try {
                this.options.getLogger().log(SentryLevel.WARNING, "Failed to update connection status cache", th);
                this.cachedNetworkCapabilities = null;
                this.lastCacheUpdateTime = this.timeProvider.getCurrentTimeMillis();
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
        if (acquire != null) {
            acquire.close();
        }
    }

    private boolean isCacheValid() {
        return this.timeProvider.getCurrentTimeMillis() - this.lastCacheUpdateTime < CACHE_TTL_MS;
    }

    @Override // io.sentry.IConnectionStatusProvider
    public IConnectionStatusProvider.ConnectionStatus getConnectionStatus() {
        if (!isCacheValid()) {
            updateCache(null);
        }
        return getConnectionStatusFromCache();
    }

    @Override // io.sentry.IConnectionStatusProvider
    public String getConnectionType() {
        if (!isCacheValid()) {
            updateCache(null);
        }
        return getConnectionTypeFromCache();
    }

    @Override // io.sentry.IConnectionStatusProvider
    public boolean addConnectionStatusObserver(IConnectionStatusProvider.IConnectionStatusObserver iConnectionStatusObserver) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            this.connectionStatusObservers.add(iConnectionStatusObserver);
            if (acquire != null) {
                acquire.close();
            }
            m3877x4aaee1e1();
            return this.networkCallback != null;
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

    @Override // io.sentry.IConnectionStatusProvider
    public void removeConnectionStatusObserver(IConnectionStatusProvider.IConnectionStatusObserver iConnectionStatusObserver) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            this.connectionStatusObservers.remove(iConnectionStatusObserver);
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

    private void unregisterNetworkCallback(boolean z) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        if (z) {
            try {
                this.connectionStatusObservers.clear();
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
        ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
        this.networkCallback = null;
        if (networkCallback != null) {
            unregisterNetworkCallback(this.context, this.options.getLogger(), networkCallback);
        }
        this.cachedNetworkCapabilities = null;
        this.currentNetwork = null;
        this.lastCacheUpdateTime = 0L;
        if (acquire != null) {
            acquire.close();
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Network callback unregistered", new Object[0]);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        submitSafe(new Runnable() { // from class: io.sentry.android.core.internal.util.AndroidConnectionStatusProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AndroidConnectionStatusProvider.this.m3876xef656fe8();
            }
        });
    }

    /* renamed from: lambda$close$1$io-sentry-android-core-internal-util-AndroidConnectionStatusProvider, reason: not valid java name */
    /* synthetic */ void m3876xef656fe8() {
        unregisterNetworkCallback(true);
        ISentryLifecycleToken acquire = childCallbacksLock.acquire();
        try {
            childCallbacks.clear();
            if (acquire != null) {
                acquire.close();
            }
            acquire = connectivityManagerLock.acquire();
            try {
                connectivityManager = null;
                if (acquire != null) {
                    acquire.close();
                }
                AppState.getInstance().removeAppStateListener(this);
            } finally {
            }
        } finally {
        }
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onForeground() {
        if (this.networkCallback != null) {
            return;
        }
        submitSafe(new Runnable() { // from class: io.sentry.android.core.internal.util.AndroidConnectionStatusProvider$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AndroidConnectionStatusProvider.this.m3879x5bf694a3();
            }
        });
    }

    /* renamed from: lambda$onForeground$2$io-sentry-android-core-internal-util-AndroidConnectionStatusProvider, reason: not valid java name */
    /* synthetic */ void m3879x5bf694a3() {
        ISentryLifecycleToken acquire;
        updateCache(null);
        IConnectionStatusProvider.ConnectionStatus connectionStatusFromCache = getConnectionStatusFromCache();
        if (connectionStatusFromCache == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED) {
            this.isConnected.set(false);
            acquire = childCallbacksLock.acquire();
            try {
                Iterator<ConnectivityManager.NetworkCallback> it = childCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onLost(null);
                }
                if (acquire != null) {
                    acquire.close();
                }
            } finally {
            }
        }
        acquire = this.lock.acquire();
        try {
            Iterator<IConnectionStatusProvider.IConnectionStatusObserver> it2 = this.connectionStatusObservers.iterator();
            while (it2.hasNext()) {
                it2.next().onConnectionStatusChanged(connectionStatusFromCache);
            }
            if (acquire != null) {
                acquire.close();
            }
            m3877x4aaee1e1();
        } finally {
        }
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onBackground() {
        if (this.networkCallback == null) {
            return;
        }
        submitSafe(new Runnable() { // from class: io.sentry.android.core.internal.util.AndroidConnectionStatusProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AndroidConnectionStatusProvider.this.m3878x1a44b2f7();
            }
        });
    }

    /* renamed from: lambda$onBackground$3$io-sentry-android-core-internal-util-AndroidConnectionStatusProvider, reason: not valid java name */
    /* synthetic */ void m3878x1a44b2f7() {
        unregisterNetworkCallback(false);
    }

    public NetworkCapabilities getCachedNetworkCapabilities() {
        return this.cachedNetworkCapabilities;
    }

    private static IConnectionStatusProvider.ConnectionStatus getConnectionStatus(Context context, ConnectivityManager connectivityManager2, ILogger iLogger) {
        if (!Permissions.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            iLogger.log(SentryLevel.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return IConnectionStatusProvider.ConnectionStatus.NO_PERMISSION;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager2.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                iLogger.log(SentryLevel.INFO, "NetworkInfo is null, there's no active network.", new Object[0]);
                return IConnectionStatusProvider.ConnectionStatus.DISCONNECTED;
            }
            if (activeNetworkInfo.isConnected()) {
                return IConnectionStatusProvider.ConnectionStatus.CONNECTED;
            }
            return IConnectionStatusProvider.ConnectionStatus.DISCONNECTED;
        } catch (Throwable th) {
            iLogger.log(SentryLevel.WARNING, "Could not retrieve Connection Status", th);
            return IConnectionStatusProvider.ConnectionStatus.UNKNOWN;
        }
    }

    public static String getConnectionType(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        boolean z;
        boolean z2;
        ConnectivityManager connectivityManager2 = getConnectivityManager(context, iLogger);
        if (connectivityManager2 == null) {
            return null;
        }
        boolean z3 = false;
        if (!Permissions.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            iLogger.log(SentryLevel.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return null;
        }
        try {
            z = true;
            if (buildInfoProvider.getSdkInfoVersion() >= 23) {
                Network activeNetwork = connectivityManager2.getActiveNetwork();
                if (activeNetwork == null) {
                    iLogger.log(SentryLevel.INFO, "Network is null and cannot check network status", new Object[0]);
                    return null;
                }
                NetworkCapabilities networkCapabilities = connectivityManager2.getNetworkCapabilities(activeNetwork);
                if (networkCapabilities == null) {
                    iLogger.log(SentryLevel.INFO, "NetworkCapabilities is null and cannot check network type", new Object[0]);
                    return null;
                }
                boolean hasTransport = networkCapabilities.hasTransport(3);
                z2 = networkCapabilities.hasTransport(1);
                z = networkCapabilities.hasTransport(0);
                z3 = hasTransport;
            } else {
                NetworkInfo activeNetworkInfo = connectivityManager2.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    iLogger.log(SentryLevel.INFO, "NetworkInfo is null, there's no active network.", new Object[0]);
                    return null;
                }
                int type = activeNetworkInfo.getType();
                if (type == 0) {
                    z2 = false;
                } else if (type != 1) {
                    if (type != 9) {
                        z2 = false;
                    } else {
                        z2 = false;
                        z3 = true;
                    }
                    z = z2;
                } else {
                    z2 = true;
                    z = false;
                }
            }
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Failed to retrieve network info", th);
        }
        if (z3) {
            return "ethernet";
        }
        if (z2) {
            return "wifi";
        }
        if (z) {
            return "cellular";
        }
        return null;
    }

    public static String getConnectionType(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities.hasTransport(3)) {
            return "ethernet";
        }
        if (networkCapabilities.hasTransport(1)) {
            return "wifi";
        }
        if (networkCapabilities.hasTransport(0)) {
            return "cellular";
        }
        return null;
    }

    private static ConnectivityManager getConnectivityManager(Context context, ILogger iLogger) {
        if (connectivityManager != null) {
            return connectivityManager;
        }
        ISentryLifecycleToken acquire = connectivityManagerLock.acquire();
        try {
            if (connectivityManager != null) {
                ConnectivityManager connectivityManager2 = connectivityManager;
                if (acquire != null) {
                    acquire.close();
                }
                return connectivityManager2;
            }
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                iLogger.log(SentryLevel.INFO, "ConnectivityManager is null and cannot check network status", new Object[0]);
            }
            ConnectivityManager connectivityManager3 = connectivityManager;
            if (acquire != null) {
                acquire.close();
            }
            return connectivityManager3;
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

    public static boolean addNetworkCallback(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider, ConnectivityManager.NetworkCallback networkCallback) {
        if (buildInfoProvider.getSdkInfoVersion() < 24) {
            iLogger.log(SentryLevel.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
            return false;
        }
        if (!Permissions.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            iLogger.log(SentryLevel.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return false;
        }
        ISentryLifecycleToken acquire = childCallbacksLock.acquire();
        try {
            childCallbacks.add(networkCallback);
            if (acquire == null) {
                return true;
            }
            acquire.close();
            return true;
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

    public static void removeNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
        ISentryLifecycleToken acquire = childCallbacksLock.acquire();
        try {
            childCallbacks.remove(networkCallback);
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

    static boolean registerNetworkCallback(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider, ConnectivityManager.NetworkCallback networkCallback) {
        if (buildInfoProvider.getSdkInfoVersion() < 24) {
            iLogger.log(SentryLevel.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
            return false;
        }
        ConnectivityManager connectivityManager2 = getConnectivityManager(context, iLogger);
        if (connectivityManager2 == null) {
            return false;
        }
        if (!Permissions.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            iLogger.log(SentryLevel.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return false;
        }
        try {
            connectivityManager2.registerDefaultNetworkCallback(networkCallback);
            return true;
        } catch (Throwable th) {
            iLogger.log(SentryLevel.WARNING, "registerDefaultNetworkCallback failed", th);
            return false;
        }
    }

    static void unregisterNetworkCallback(Context context, ILogger iLogger, ConnectivityManager.NetworkCallback networkCallback) {
        ConnectivityManager connectivityManager2 = getConnectivityManager(context, iLogger);
        if (connectivityManager2 == null) {
            return;
        }
        try {
            connectivityManager2.unregisterNetworkCallback(networkCallback);
        } catch (Throwable th) {
            iLogger.log(SentryLevel.WARNING, "unregisterNetworkCallback failed", th);
        }
    }

    public List<IConnectionStatusProvider.IConnectionStatusObserver> getStatusObservers() {
        return this.connectionStatusObservers;
    }

    public ConnectivityManager.NetworkCallback getNetworkCallback() {
        return this.networkCallback;
    }

    public static List<ConnectivityManager.NetworkCallback> getChildCallbacks() {
        return childCallbacks;
    }

    private void submitSafe(Runnable runnable) {
        try {
            this.options.getExecutorService().submit(runnable);
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "AndroidConnectionStatusProvider submit failed", th);
        }
    }
}
