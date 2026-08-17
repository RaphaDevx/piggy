package com.scandit.datacapture.core.internal.module.https;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements b {
    public final ConnectivityManager a;

    public d() {
        Context applicationContext = AppAndroidEnvironment.INSTANCE.getApplicationContext();
        Intrinsics.checkNotNullParameter(applicationContext, "<this>");
        Object systemService = applicationContext.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Intrinsics.checkNotNullParameter(connectivityManager, "connectivityManager");
        this.a = connectivityManager;
    }

    public final a a() {
        Network activeNetwork = this.a.getActiveNetwork();
        if (activeNetwork == null) {
            return a.a;
        }
        NetworkCapabilities networkCapabilities = this.a.getNetworkCapabilities(activeNetwork);
        return networkCapabilities == null ? a.a : networkCapabilities.hasTransport(0) ? a.b : a.c;
    }

    public final String toString() {
        return "RealConnectivity@" + hashCode();
    }
}
