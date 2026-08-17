package com.tealium.internal.dispatcher;

import android.text.TextUtils;
import com.tealium.internal.NetworkRequestBuilder;
import com.tealium.internal.c;
import com.tealium.internal.d;
import com.tealium.internal.data.BulkDispatch;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.j.f;
import com.tealium.internal.j.g;
import com.tealium.internal.listeners.BulkDispatchSendListener;
import com.tealium.internal.listeners.DispatchSendListener;
import com.tealium.library.DataSources;
import com.tealium.library.R;
import com.tealium.library.Tealium;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes2.dex */
public final class CollectDispatcher implements BulkDispatchSendListener, DispatchSendListener {
    private static final String KEY_TEALIUM_EVENT = "tealium_event";
    private static final String UPDATE_CONSENT_COOKIE_EVENT = "update_consent_cookie";
    private final String mBaseUrl;
    private final String mBaseUrlBulk;
    private int mGzipThreshold;
    private boolean mIsVdataEndpointEnabled;
    private final c mLogger;
    private final d mMessageRouter;
    private String mOverrideCollectDispatchProfile;
    private String mTraceId;

    class a implements NetworkRequestBuilder.HttpResponseListener {
        a() {
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpError(String str, Throwable th) {
            CollectDispatcher.this.mMessageRouter.b(new f(str, th));
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr) {
            CollectDispatcher.this.mMessageRouter.b(new g(str, str2, i, map, bArr));
        }
    }

    public CollectDispatcher(Tealium.Config config, d dVar, c cVar, String str) {
        if (config == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.mLogger = cVar;
        this.mMessageRouter = dVar;
        this.mIsVdataEndpointEnabled = config.isVdataCollectEndpointEnabled();
        this.mOverrideCollectDispatchProfile = config.getOverrideCollectDispatchProfile();
        this.mGzipThreshold = config.getPublishSettings().getGzipThreshold();
        if (config.getOverrideCollectDispatchUrl() == null) {
            if (this.mIsVdataEndpointEnabled) {
                Locale locale = Locale.ROOT;
                String accountName = config.getAccountName();
                String str2 = this.mOverrideCollectDispatchProfile;
                this.mBaseUrl = String.format(locale, "https://collect.tealiumiq.com/vdata/i.gif?tealium_vid=%s&tealium_account=%s&tealium_profile=%s", str, accountName, str2 == null ? config.getProfileName() : str2);
            } else {
                this.mBaseUrl = String.format(Locale.ROOT, "https://collect.tealiumiq.com/event", new Object[0]);
            }
            this.mBaseUrlBulk = String.format(Locale.ROOT, "https://collect.tealiumiq.com/bulk-event", new Object[0]);
            return;
        }
        if (!config.getOverrideCollectDispatchUrl().contains("?")) {
            Locale locale2 = Locale.ROOT;
            String overrideCollectDispatchUrl = config.getOverrideCollectDispatchUrl();
            String accountName2 = config.getAccountName();
            String str3 = this.mOverrideCollectDispatchProfile;
            String format = String.format(locale2, "%s?tealium_vid=%s&tealium_account=%s&tealium_profile=%s", overrideCollectDispatchUrl, str, accountName2, str3 == null ? config.getProfileName() : str3);
            this.mBaseUrlBulk = format;
            this.mBaseUrl = format;
            return;
        }
        String overrideCollectDispatchUrl2 = config.getOverrideCollectDispatchUrl();
        if (!config.getOverrideCollectDispatchUrl().contains(DataSources.Key.TEALIUM_VID)) {
            overrideCollectDispatchUrl2 = overrideCollectDispatchUrl2 + "&tealium_vid=" + str;
        }
        if (!config.getOverrideCollectDispatchUrl().contains(DataSources.Key.TEALIUM_ACCOUNT)) {
            overrideCollectDispatchUrl2 = overrideCollectDispatchUrl2 + "&tealium_account=" + config.getAccountName();
        }
        if (!config.getOverrideCollectDispatchUrl().contains(DataSources.Key.TEALIUM_PROFILE)) {
            StringBuilder sb = new StringBuilder();
            sb.append(overrideCollectDispatchUrl2);
            sb.append("&tealium_profile=");
            String str4 = this.mOverrideCollectDispatchProfile;
            sb.append(str4 == null ? config.getProfileName() : str4);
            overrideCollectDispatchUrl2 = sb.toString();
        }
        this.mBaseUrlBulk = overrideCollectDispatchUrl2;
        this.mBaseUrl = overrideCollectDispatchUrl2;
    }

    private String createRequest(Dispatch dispatch) throws UnsupportedEncodingException {
        String str = this.mBaseUrl;
        if (!this.mIsVdataEndpointEnabled) {
            return str;
        }
        for (String str2 : dispatch.keys()) {
            Object obj = dispatch.get(str2);
            String str3 = str + "&" + URLEncoder.encode(str2, "UTF-8") + "=";
            if (obj instanceof String[]) {
                String[] strArr = (String[]) obj;
                int length = strArr.length - 1;
                for (int i = 0; i <= length; i++) {
                    str3 = str3 + URLEncoder.encode(strArr[i], "UTF-8");
                    if (i != length) {
                        str3 = str3 + ',';
                    }
                }
                str = str3;
            } else {
                str = str3 + URLEncoder.encode(obj.toString(), "UTF-8");
            }
        }
        return str;
    }

    private boolean isUpdateConsentCookieEvent(Dispatch dispatch) {
        if (dispatch.get("tealium_event") == null) {
            return false;
        }
        return dispatch.get("tealium_event").equals(UPDATE_CONSENT_COOKIE_EVENT);
    }

    public NetworkRequestBuilder.HttpResponseListener createHttpResponseListener() {
        return new a();
    }

    @Override // com.tealium.internal.listeners.BulkDispatchSendListener
    public void onBulkDispatchSend(List<Dispatch> list) {
        if (this.mIsVdataEndpointEnabled) {
            Iterator<Dispatch> it = list.iterator();
            while (it.hasNext()) {
                onDispatchSend(it.next());
            }
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (isUpdateConsentCookieEvent(list.get(i))) {
                list.remove(i);
                if (list.size() == 0) {
                    return;
                }
            }
        }
        if (list.size() == 1) {
            onDispatchSend(list.get(0));
            return;
        }
        BulkDispatch bulkDispatch = new BulkDispatch(this.mLogger, list, false);
        bulkDispatch.removeKnownSharedKeys();
        if (this.mOverrideCollectDispatchProfile != null) {
            bulkDispatch.getShared().remove(DataSources.Key.TEALIUM_PROFILE);
            try {
                bulkDispatch.getShared().put(DataSources.Key.TEALIUM_PROFILE, this.mOverrideCollectDispatchProfile);
            } catch (JSONException unused) {
            }
        }
        if (this.mTraceId != null) {
            try {
                bulkDispatch.getShared().put(DataSources.Key.TEALIUM_TRACE_ID, this.mTraceId);
            } catch (JSONException unused2) {
            }
        }
        if (this.mLogger.d()) {
            this.mLogger.d(R.string.collect_dispatcher_sending, this.mBaseUrlBulk);
            this.mLogger.d(R.string.logger_dispatch_send, "bulk", bulkDispatch.getPayload().toString());
        }
        this.mMessageRouter.a(NetworkRequestBuilder.createPostRequest(this.mBaseUrlBulk).setListener(createHttpResponseListener()).setJsonBody(bulkDispatch.getPayload()).setShouldGzip(bulkDispatch.getPayload().toString().length() > this.mGzipThreshold).createPostRunnable());
    }

    @Override // com.tealium.internal.listeners.DispatchSendListener
    public void onDispatchSend(Dispatch dispatch) {
        try {
            if (isUpdateConsentCookieEvent(dispatch)) {
                return;
            }
            if (this.mOverrideCollectDispatchProfile != null) {
                dispatch.remove(DataSources.Key.TEALIUM_PROFILE);
                dispatch.put(DataSources.Key.TEALIUM_PROFILE, this.mOverrideCollectDispatchProfile);
            }
            String str = this.mTraceId;
            if (str != null) {
                dispatch.put(DataSources.Key.TEALIUM_TRACE_ID, str);
                dispatch.put(DataSources.Key.CP_TRACE_ID, this.mTraceId);
            }
            String string = dispatch.getString("event");
            if (string != null && string.equals("kill_visitor_session")) {
                setTraceId(null);
            }
            String createRequest = createRequest(dispatch);
            if (this.mLogger.d()) {
                this.mLogger.d(R.string.collect_dispatcher_sending, createRequest);
            }
            if (this.mIsVdataEndpointEnabled) {
                this.mMessageRouter.a(NetworkRequestBuilder.createGetRequest(createRequest).setListener(createHttpResponseListener()).createRunnable());
            } else {
                this.mMessageRouter.a(NetworkRequestBuilder.createPostRequest(this.mBaseUrl).setListener(createHttpResponseListener()).setJsonBody(dispatch.toJsonObject()).createPostRunnable());
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTraceId(String str) {
        this.mTraceId = str;
    }
}
