package com.tealium.library;

import android.os.SystemClock;
import com.tealium.internal.NetworkRequestBuilder;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.j.q;
import com.tealium.internal.listeners.DispatchReadyListener;
import com.tealium.library.Tealium;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.HttpHeaders;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.TimeZones;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PublishSettingsRetriever.java */
/* loaded from: classes2.dex */
final class g implements DispatchReadyListener, NetworkRequestBuilder.HttpResponseListener {
    private final String a;
    private final String b;
    private final com.tealium.internal.d c;
    private final com.tealium.internal.a d;
    private final AtomicInteger e;
    private final SimpleDateFormat f;
    private final com.tealium.internal.c g;
    private volatile PublishSettings h;
    private volatile long i;
    private volatile long j;

    /* compiled from: PublishSettingsRetriever.java */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ byte[] b;
        final /* synthetic */ boolean c;
        final /* synthetic */ int d;

        a(String str, byte[] bArr, boolean z, int i) {
            this.a = str;
            this.b = bArr;
            this.c = z;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!"GET".equals(this.a)) {
                    if ("HEAD".equals(this.a)) {
                        g.this.a(this.d);
                        return;
                    }
                    return;
                }
                g.this.i = SystemClock.elapsedRealtime();
                g.this.j = System.currentTimeMillis();
                g.this.e.set(0);
                try {
                    String str = new String(this.b, "UTF-8");
                    if (this.c) {
                        g.this.a(str);
                    } else {
                        g.this.b(str);
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } catch (Throwable th) {
                g.this.g.b(th);
            }
        }
    }

    g(String str, Tealium.Config config, com.tealium.internal.d dVar) {
        this(str, config, dVar, com.tealium.internal.a.a(config.getApplication().getApplicationContext()));
    }

    @Override // com.tealium.internal.listeners.DispatchReadyListener
    public void onDispatchReady(Dispatch dispatch) {
        boolean z = this.h.getSource() == null;
        if (b() || z) {
            if (z) {
                a(false);
            } else {
                a();
            }
        }
    }

    @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
    public void onHttpError(String str, Throwable th) {
        this.e.set(0);
    }

    @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
    public void onHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr) {
        this.c.c(new a(str2, bArr, (map == null || !map.containsKey(HttpHeaders.CONTENT_TYPE)) ? false : map.get(HttpHeaders.CONTENT_TYPE).toString().toLowerCase(Locale.ROOT).contains("html"), i));
    }

    private g(String str, Tealium.Config config, com.tealium.internal.d dVar, com.tealium.internal.a aVar) {
        String overridePublishSettingsUrl;
        if (config.getOverridePublishSettingsUrl() == null) {
            overridePublishSettingsUrl = config.getDefaultTagManagementUrl();
        } else {
            overridePublishSettingsUrl = config.getOverridePublishSettingsUrl();
        }
        this.b = overridePublishSettingsUrl;
        this.a = str;
        this.h = config.getPublishSettings();
        this.c = dVar;
        this.d = aVar;
        this.g = config.getLogger();
        this.e = new AtomicInteger(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ROOT);
        this.f = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        if (this.h.getSource() == null) {
            a(false);
        }
    }

    private boolean b() {
        return SystemClock.elapsedRealtime() - this.i > ((long) this.h.getMinutesBetweenRefresh()) * DateUtils.MILLIS_PER_MINUTE;
    }

    private void a(boolean z) {
        if ((!this.h.isWifiOnlySending() || this.d.b()) && this.d.a()) {
            if (z || 1 != this.e.getAndSet(1)) {
                if (this.g.d()) {
                    this.g.d(R.string.publish_settings_retriever_fetching, this.b);
                }
                this.c.a(NetworkRequestBuilder.createGetRequest(this.b).setListener(this).createRunnable());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            a(new JSONObject(str));
        } catch (JSONException unused) {
            this.g.b(R.string.publish_settings_retriever_malformed_json, str);
        }
    }

    private void a() {
        if ((!this.h.isWifiOnlySending() || this.d.b()) && this.d.a() && 1 != this.e.getAndSet(1)) {
            this.c.a(NetworkRequestBuilder.createHeadRequest(this.b).setListener(this).addHeader(HttpHeaders.IF_MODIFIED_SINCE, this.f.format(new Date(this.j))).createRunnable());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 200) {
            a(true);
        } else {
            this.e.set(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (str == null) {
            return;
        }
        try {
            String a2 = f.a(str);
            if (a2 != null) {
                a(new JSONObject(a2));
            } else {
                this.g.a(R.string.publish_settings_retriever_no_mps);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            PublishSettings from = PublishSettings.from(jSONObject.optJSONObject(BuildConfig.PUBLISH_SETTINGS_VERSION));
            if (!this.h.equals(from)) {
                this.h = from;
                this.c.b(new q(this.h));
            } else if (this.g.d()) {
                this.g.d(R.string.publish_settings_retriever_no_change, new Object[0]);
            }
        } catch (PublishSettings.DisabledLibraryException unused) {
            if (this.g.c()) {
                this.g.c(R.string.publish_settings_retriever_disabled, this.a);
            }
            Tealium.destroyInstance(this.a);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
