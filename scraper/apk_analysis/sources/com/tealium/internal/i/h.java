package com.tealium.internal.i;

import android.net.Uri;
import android.os.SystemClock;
import com.tealium.collect.visitor.VisitorProfile;
import com.tealium.internal.NetworkRequestBuilder;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.BulkDispatchSendListener;
import com.tealium.internal.listeners.DispatchSendListener;
import com.tealium.library.R;
import com.tealium.library.Tealium;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;

/* compiled from: VisitorProfileRetriever.java */
/* loaded from: classes2.dex */
public final class h implements DispatchSendListener, BulkDispatchSendListener {
    private final String a;
    private final com.tealium.internal.c d;
    private final com.tealium.internal.d e;
    private VisitorProfile g;
    private final AtomicLong b = new AtomicLong(0);
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final NetworkRequestBuilder.HttpResponseListener f = a();

    /* compiled from: VisitorProfileRetriever.java */
    class a implements NetworkRequestBuilder.HttpResponseListener {
        a() {
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpError(String str, Throwable th) {
            h.this.d.a(R.string.profile_retriever_error_http, th, str);
            h.this.c.set(false);
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr) {
            String str3;
            if (bArr == null) {
                str3 = "";
            } else {
                try {
                    str3 = new String(bArr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            h.this.c.set(false);
            VisitorProfile visitorProfile = null;
            if (str3.length() <= 2) {
                h.this.d.a(R.string.profile_retriever_error_bad_profile, null, str3);
                return;
            }
            h.this.b.set(SystemClock.uptimeMillis());
            try {
                visitorProfile = VisitorProfile.fromJSON(str3);
            } catch (JSONException e2) {
                h.this.d.a(e2);
            }
            if ((h.this.g == null || !h.this.g.equals(visitorProfile)) && visitorProfile != null) {
                h hVar = h.this;
                hVar.a(hVar.g, visitorProfile);
                h.this.g = visitorProfile;
            }
        }
    }

    public h(Tealium.Config config, com.tealium.internal.d dVar, String str) {
        this.e = dVar;
        this.d = config.getLogger();
        this.g = config.getCachedVisitorProfile();
        this.a = new Uri.Builder().scheme("https").authority(config.getOverrideVisitorServiceDomain() == null ? "visitor-service.tealiumiq.com" : config.getOverrideVisitorServiceDomain()).appendPath(config.getAccountName()).appendPath(config.getOverrideVisitorServiceProfile() == null ? config.getProfileName() : config.getOverrideVisitorServiceProfile()).appendPath(str).build().toString();
    }

    @Override // com.tealium.internal.listeners.BulkDispatchSendListener
    public void onBulkDispatchSend(List<Dispatch> list) {
        onDispatchSend(list.get(0));
    }

    @Override // com.tealium.internal.listeners.DispatchSendListener
    public void onDispatchSend(Dispatch dispatch) {
        if (this.c.compareAndSet(false, true)) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.b.get();
            if (uptimeMillis > 10000) {
                this.e.a(NetworkRequestBuilder.createGetRequest(this.a).createRunnable(), 0L);
                uptimeMillis = 0;
            }
            this.e.a(NetworkRequestBuilder.createGetRequest(this.a).setListener(this.f).createRunnable(), uptimeMillis + 10000);
            if (this.d.d()) {
                this.d.d(R.string.visitor_profile_retriever_fetching, this.a);
            }
        }
    }

    private NetworkRequestBuilder.HttpResponseListener a() {
        return new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(VisitorProfile visitorProfile, VisitorProfile visitorProfile2) {
        boolean z = visitorProfile == null;
        boolean z2 = visitorProfile2 == null;
        if (z && z2) {
            return;
        }
        if (z || !visitorProfile.equals(visitorProfile2)) {
            this.e.a(new i(visitorProfile, visitorProfile2));
            a(new b(z ? null : visitorProfile.getAudiences(), z2 ? null : visitorProfile2.getAudiences()));
            a(new c(z ? null : visitorProfile.getBadges(), z2 ? null : visitorProfile2.getBadges()));
            a(new d(z ? null : visitorProfile.getDates(), z2 ? null : visitorProfile2.getDates()));
            a(new e(z ? null : visitorProfile.getFlags(), z2 ? null : visitorProfile2.getFlags()));
            a(new f(z ? null : visitorProfile.getMetrics(), z2 ? null : visitorProfile2.getMetrics()));
            a(new g(z ? null : visitorProfile.getProperties(), z2 ? null : visitorProfile2.getProperties()));
        }
    }

    private void a(com.tealium.internal.i.a<?, ?> aVar) {
        if (aVar.f()) {
            this.e.a(aVar);
        }
    }
}
