package com.tealium.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.j.u;
import com.tealium.internal.listeners.ActivityResumeListener;
import com.tealium.library.DataSources;
import com.tealium.library.Tealium;
import java.util.Map;

/* compiled from: DeepLinkHandler.java */
/* loaded from: classes2.dex */
public final class b implements ActivityResumeListener {
    private final Tealium.Config a;
    private final d b;
    private final Map<String, Object> c;
    private final h d;

    public b(Tealium.Config config, d dVar, DataSources dataSources, h hVar) {
        this(config, dVar, dataSources.getVolatileDataSources(), hVar);
    }

    public void a(String str) {
        this.b.b(new u(str, false));
    }

    public void b(String str) {
        Dispatch dispatch = new Dispatch();
        dispatch.put(DataSources.Key.CALL_TYPE, "kill_visitor_session");
        dispatch.put("event", "kill_visitor_session");
        if (str != null) {
            dispatch.put(DataSources.Key.TEALIUM_TRACE_ID, str);
        }
        this.d.a(dispatch);
    }

    @Override // com.tealium.internal.listeners.ActivityResumeListener
    public void onActivityResume(Activity activity) {
        Intent intent;
        Uri data;
        String queryParameter;
        if (activity == null || (intent = activity.getIntent()) == null || (data = intent.getData()) == null || data.isOpaque()) {
            return;
        }
        if (this.a.isQrTraceEnabled() && (queryParameter = data.getQueryParameter(DataSources.Key.TEALIUM_TRACE_ID)) != null) {
            String queryParameter2 = data.getQueryParameter("kill_visitor_session");
            String queryParameter3 = data.getQueryParameter("leave_trace");
            if (!TextUtils.isEmpty(queryParameter2)) {
                b(queryParameter);
            } else if (TextUtils.isEmpty(queryParameter3)) {
                a(queryParameter);
            } else {
                a();
            }
        }
        if (this.a.isDeepLinkTrackingEnabled()) {
            a(data);
        }
    }

    public b(Tealium.Config config, d dVar, Map<String, Object> map, h hVar) {
        this.a = config;
        this.b = dVar;
        this.c = map;
        this.d = hVar;
    }

    public void a() {
        this.b.b(new u(null, false));
    }

    public void a(Uri uri) {
        if (uri.isOpaque()) {
            return;
        }
        b();
        this.c.put(DataSources.Key.DEEP_LINK_URL, uri.toString());
        for (String str : uri.getQueryParameterNames()) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                this.c.put("deep_link_param_" + str, queryParameter);
            }
        }
    }

    public void b() {
        for (String str : this.c.keySet()) {
            if (str.startsWith(DataSources.Key.DEEP_LINK_QUERY_PREFIX)) {
                this.c.remove(str);
            }
        }
    }
}
