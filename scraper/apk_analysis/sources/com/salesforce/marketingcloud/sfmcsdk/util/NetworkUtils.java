package com.salesforce.marketingcloud.sfmcsdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.security.ProviderInstaller;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkUtils.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/util/NetworkUtils;", "", "()V", "TAG", "", "hasConnectivity", "", "context", "Landroid/content/Context;", "installProvidersIfNeeded", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NetworkUtils {
    public static final NetworkUtils INSTANCE = new NetworkUtils();
    public static final String TAG = "~$NetworkUtils";

    private NetworkUtils() {
    }

    @JvmStatic
    public static final boolean hasConnectivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils$hasConnectivity$network$1$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Device has _no_ connectivity.";
                }
            });
            return false;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities == null) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils$hasConnectivity$activeNetwork$1$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Device has _no_ connectivity.";
                }
            });
            return false;
        }
        if (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(2)) {
            return true;
        }
        SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils$hasConnectivity$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "Device has _no_ connectivity.";
            }
        });
        return false;
    }

    @JvmStatic
    public static final void installProvidersIfNeeded(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProviderInstaller.installIfNeeded(context);
    }
}
