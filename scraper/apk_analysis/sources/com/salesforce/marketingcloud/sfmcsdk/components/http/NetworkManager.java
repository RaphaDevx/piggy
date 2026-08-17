package com.salesforce.marketingcloud.sfmcsdk.components.http;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt;
import com.salesforce.marketingcloud.sfmcsdk.util.NetworkUtils;
import io.sentry.SentryBaseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.ws.rs.core.HttpHeaders;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkManager.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 02\u00020\u0001:\u00010B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u001f\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0016\"\u00020\r¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0001¢\u0006\u0002\b\u001bJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0015\u0010$\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\rH\u0000¢\u0006\u0002\b%J\u0015\u0010&\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b'J\u001f\u0010(\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010)\u001a\u00020\u0019H\u0001¢\u0006\u0002\b*J\u001d\u0010+\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020#H\u0001¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0001¢\u0006\u0002\b/R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u00061"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/NetworkManager;", "", "context", "Landroid/content/Context;", "executors", "Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;", "networkPreferences", "Landroid/content/SharedPreferences;", "authenticator", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Authenticator;", "(Landroid/content/Context;Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;Landroid/content/SharedPreferences;Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Authenticator;)V", "requestsInFlight", "", "", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getRequestsInFlight$sfmcsdk_release$annotations", "()V", "getRequestsInFlight$sfmcsdk_release", "()Ljava/util/Map;", "canMakeRequest", "", "requestNames", "", "([Ljava/lang/String;)Z", "deviceRetryAfterTime", "", "requestName", "deviceRetryAfterTime$sfmcsdk_release", "executeAsync", "", SentryBaseEvent.JsonKeys.REQUEST, "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request;", PluginMethod.RETURN_CALLBACK, "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Callback;", "executeSync", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Response;", "isBlockedByRetryAfter", "isBlockedByRetryAfter$sfmcsdk_release", "makeRequest", "makeRequest$sfmcsdk_release", "recordDeviceRetryAfter", "timestamp", "recordDeviceRetryAfter$sfmcsdk_release", "recordRetryAfter", io.sentry.protocol.Response.TYPE, "recordRetryAfter$sfmcsdk_release", "serverRetryAfterTime", "serverRetryAfterTime$sfmcsdk_release", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NetworkManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long MAX_SERVER_RETRY = 86400000;
    public static final String TAG = "~$NetworkManager";
    private final Authenticator authenticator;
    private final Context context;
    private final SdkExecutors executors;
    private final SharedPreferences networkPreferences;
    private final Map<String, AtomicBoolean> requestsInFlight;

    public static /* synthetic */ void getRequestsInFlight$sfmcsdk_release$annotations() {
    }

    public NetworkManager(Context context, SdkExecutors executors, SharedPreferences networkPreferences, Authenticator authenticator) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executors, "executors");
        Intrinsics.checkNotNullParameter(networkPreferences, "networkPreferences");
        this.context = context;
        this.executors = executors;
        this.networkPreferences = networkPreferences;
        this.authenticator = authenticator;
        this.requestsInFlight = new LinkedHashMap();
    }

    public /* synthetic */ NetworkManager(Context context, SdkExecutors sdkExecutors, SharedPreferences sharedPreferences, Authenticator authenticator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, sdkExecutors, sharedPreferences, (i & 8) != 0 ? null : authenticator);
    }

    /* compiled from: NetworkManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/NetworkManager$Companion;", "", "()V", "MAX_SERVER_RETRY", "", "TAG", "", "getDeviceRetryKey", "requestName", "getDeviceRetryKey$sfmcsdk_release", "getServerRetryKey", "getServerRetryKey$sfmcsdk_release", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getServerRetryKey$sfmcsdk_release(String requestName) {
            Intrinsics.checkNotNullParameter(requestName, "requestName");
            return "retry_server_" + requestName;
        }

        public final String getDeviceRetryKey$sfmcsdk_release(String requestName) {
            Intrinsics.checkNotNullParameter(requestName, "requestName");
            return "retry_device_" + requestName;
        }
    }

    public final Map<String, AtomicBoolean> getRequestsInFlight$sfmcsdk_release() {
        return this.requestsInFlight;
    }

    public final void executeAsync(final Request request, final Callback callback) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callback, "callback");
        SdkExecutorsKt.namedRunnable(this.executors.getNetworkIO(), "network_manager_execute", new Function0<Unit>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$executeAsync$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Callback callback2 = Callback.this;
                Request request2 = request;
                callback2.onResponse(request2, this.executeSync(request2));
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0077 A[Catch: Exception -> 0x01da, TryCatch #0 {Exception -> 0x01da, blocks: (B:3:0x0011, B:5:0x0026, B:8:0x002d, B:10:0x0033, B:13:0x0065, B:15:0x0077, B:16:0x007a, B:18:0x0082, B:20:0x0094, B:21:0x0097, B:23:0x00a2, B:25:0x00b0, B:27:0x00c2, B:28:0x00c5, B:30:0x00e5, B:32:0x00fb, B:34:0x0102, B:36:0x012c, B:38:0x0130, B:39:0x014e, B:41:0x0169, B:43:0x016d, B:45:0x0176, B:47:0x01a2, B:48:0x01a7, B:49:0x01a9, B:51:0x01c6, B:52:0x01c9, B:54:0x0053), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0082 A[Catch: Exception -> 0x01da, TryCatch #0 {Exception -> 0x01da, blocks: (B:3:0x0011, B:5:0x0026, B:8:0x002d, B:10:0x0033, B:13:0x0065, B:15:0x0077, B:16:0x007a, B:18:0x0082, B:20:0x0094, B:21:0x0097, B:23:0x00a2, B:25:0x00b0, B:27:0x00c2, B:28:0x00c5, B:30:0x00e5, B:32:0x00fb, B:34:0x0102, B:36:0x012c, B:38:0x0130, B:39:0x014e, B:41:0x0169, B:43:0x016d, B:45:0x0176, B:47:0x01a2, B:48:0x01a7, B:49:0x01a9, B:51:0x01c6, B:52:0x01c9, B:54:0x0053), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a2 A[Catch: Exception -> 0x01da, TryCatch #0 {Exception -> 0x01da, blocks: (B:3:0x0011, B:5:0x0026, B:8:0x002d, B:10:0x0033, B:13:0x0065, B:15:0x0077, B:16:0x007a, B:18:0x0082, B:20:0x0094, B:21:0x0097, B:23:0x00a2, B:25:0x00b0, B:27:0x00c2, B:28:0x00c5, B:30:0x00e5, B:32:0x00fb, B:34:0x0102, B:36:0x012c, B:38:0x0130, B:39:0x014e, B:41:0x0169, B:43:0x016d, B:45:0x0176, B:47:0x01a2, B:48:0x01a7, B:49:0x01a9, B:51:0x01c6, B:52:0x01c9, B:54:0x0053), top: B:2:0x0011 }] */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, com.salesforce.marketingcloud.sfmcsdk.components.http.Request] */
    /* JADX WARN: Type inference failed for: r3v16, types: [T, com.salesforce.marketingcloud.sfmcsdk.components.http.Response] */
    /* JADX WARN: Type inference failed for: r3v31, types: [T, com.salesforce.marketingcloud.sfmcsdk.components.http.Request] */
    /* JADX WARN: Type inference failed for: r3v34, types: [T, com.salesforce.marketingcloud.sfmcsdk.components.http.Response] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.salesforce.marketingcloud.sfmcsdk.components.http.Response executeSync(com.salesforce.marketingcloud.sfmcsdk.components.http.Request r13) {
        /*
            Method dump skipped, instructions count: 559
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager.executeSync(com.salesforce.marketingcloud.sfmcsdk.components.http.Request):com.salesforce.marketingcloud.sfmcsdk.components.http.Response");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x014d, code lost:
    
        if (r12 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x014f, code lost:
    
        r12.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0183, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0180, code lost:
    
        if (r12 == null) goto L46;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [T, java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.salesforce.marketingcloud.sfmcsdk.components.http.Response makeRequest$sfmcsdk_release(final com.salesforce.marketingcloud.sfmcsdk.components.http.Request r13) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager.makeRequest$sfmcsdk_release(com.salesforce.marketingcloud.sfmcsdk.components.http.Request):com.salesforce.marketingcloud.sfmcsdk.components.http.Response");
    }

    public final boolean isBlockedByRetryAfter$sfmcsdk_release(final String requestName) {
        Intrinsics.checkNotNullParameter(requestName, "requestName");
        long currentTimeMillis = System.currentTimeMillis();
        long serverRetryAfterTime$sfmcsdk_release = serverRetryAfterTime$sfmcsdk_release(requestName);
        long deviceRetryAfterTime$sfmcsdk_release = deviceRetryAfterTime$sfmcsdk_release(requestName);
        if (currentTimeMillis > serverRetryAfterTime$sfmcsdk_release && currentTimeMillis > deviceRetryAfterTime$sfmcsdk_release) {
            return false;
        }
        SFMCSdkLogger.INSTANCE.w(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$isBlockedByRetryAfter$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "Route " + requestName + " _blocked_ by Retry-After.";
            }
        });
        return true;
    }

    public final boolean canMakeRequest(String... requestNames) {
        Intrinsics.checkNotNullParameter(requestNames, "requestNames");
        if (!NetworkUtils.hasConnectivity(this.context)) {
            return false;
        }
        for (String str : requestNames) {
            if (isBlockedByRetryAfter$sfmcsdk_release(str)) {
                return false;
            }
        }
        return true;
    }

    public final long serverRetryAfterTime$sfmcsdk_release(String requestName) {
        Intrinsics.checkNotNullParameter(requestName, "requestName");
        return this.networkPreferences.getLong(INSTANCE.getServerRetryKey$sfmcsdk_release(requestName), 0L);
    }

    public final long deviceRetryAfterTime$sfmcsdk_release(String requestName) {
        Intrinsics.checkNotNullParameter(requestName, "requestName");
        return this.networkPreferences.getLong(INSTANCE.getDeviceRetryKey$sfmcsdk_release(requestName), 0L);
    }

    public static /* synthetic */ void recordDeviceRetryAfter$sfmcsdk_release$default(NetworkManager networkManager, Request request, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        networkManager.recordDeviceRetryAfter$sfmcsdk_release(request, j);
    }

    public final void recordDeviceRetryAfter$sfmcsdk_release(Request request, long timestamp) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.getRateLimit() > 0) {
            SharedPreferences.Editor edit = this.networkPreferences.edit();
            edit.putLong(INSTANCE.getDeviceRetryKey$sfmcsdk_release(request.getName()), request.getRateLimit() + timestamp);
            edit.apply();
        }
    }

    public final void recordRetryAfter$sfmcsdk_release(Request request, Response response) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(response, "response");
        SharedPreferences.Editor edit = this.networkPreferences.edit();
        recordDeviceRetryAfter$sfmcsdk_release(request, response.getEndTimeMillis());
        List<String> list = response.getHeaders().get(HttpHeaders.RETRY_AFTER);
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            long parseLong = Long.parseLong(list.get(0)) * 1000;
            String serverRetryKey$sfmcsdk_release = INSTANCE.getServerRetryKey$sfmcsdk_release(request.getName());
            long endTimeMillis = response.getEndTimeMillis();
            if (parseLong > 86400000) {
                parseLong = 86400000;
            }
            edit.putLong(serverRetryKey$sfmcsdk_release, endTimeMillis + parseLong);
            edit.apply();
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.d(TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager$recordRetryAfter$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Unable to parse Retry-After value.";
                }
            });
        }
    }
}
