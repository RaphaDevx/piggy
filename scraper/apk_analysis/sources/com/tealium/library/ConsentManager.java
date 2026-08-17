package com.tealium.library;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.UserConsentPreferences;
import com.tealium.internal.j.v;
import com.tealium.internal.listeners.DispatchSendListener;
import com.tealium.library.DataSources;
import com.tealium.library.Tealium;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class ConsentManager implements DispatchSendListener {
    private final Set<String> a = new a(this);
    private final Set<String> b = new b(this);
    private final UserConsentPreferences c;
    private final d d;
    private com.tealium.internal.d e;
    private boolean f;
    private String g;

    public static class ConsentCategory {
        public static final String AFFILIATES = "affiliates";
        public static final String ANALYTICS = "analytics";
        public static final String BIG_DATA = "big_data";
        public static final String CDP = "cdp";
        public static final String COOKIEMATCH = "cookiematch";
        public static final String CRM = "crm";
        public static final String DISPLAY_ADS = "display_ads";
        public static final String EMAIL = "email";
        public static final String ENGAGEMENT = "engagement";
        public static final String MISC = "misc";
        public static final String MOBILE = "mobile";
        public static final String MONITORING = "monitoring";
        public static final String PERSONALIZATION = "personalization";
        public static final String SEARCH = "search";
        public static final String SOCIAL = "social";
    }

    public static class ConsentStatus {
        public static final String CONSENTED = "consented";
        public static final String NOT_CONSENTED = "notConsented";
        public static final String UNKNOWN = "unknown";
    }

    class a extends HashSet<String> {
        a(ConsentManager consentManager) {
            add(ConsentCategory.AFFILIATES);
            add(ConsentCategory.ANALYTICS);
            add(ConsentCategory.BIG_DATA);
            add(ConsentCategory.CDP);
            add(ConsentCategory.COOKIEMATCH);
            add(ConsentCategory.CRM);
            add(ConsentCategory.DISPLAY_ADS);
            add("email");
            add(ConsentCategory.ENGAGEMENT);
            add(ConsentCategory.MOBILE);
            add(ConsentCategory.MONITORING);
            add(ConsentCategory.PERSONALIZATION);
            add("search");
            add("social");
            add(ConsentCategory.MISC);
        }
    }

    class b extends HashSet<String> {
        b(ConsentManager consentManager) {
            add("unknown");
            add(ConsentStatus.CONSENTED);
            add(ConsentStatus.NOT_CONSENTED);
        }
    }

    class c implements d {
        final /* synthetic */ String a;

        c(String str) {
            this.a = str;
        }

        @Override // com.tealium.library.ConsentManager.d
        public boolean track(String str, Map<String, ?> map) {
            Tealium tealium = Tealium.getInstance(this.a);
            if (tealium == null) {
                return false;
            }
            tealium.trackEvent(str, map);
            return true;
        }
    }

    interface d {
        boolean track(String str, Map<String, ?> map);
    }

    private ConsentManager(Tealium.Config config, d dVar, UserConsentPreferences userConsentPreferences) {
        config.getEventListeners().add(this);
        this.d = dVar;
        this.c = userConsentPreferences;
        this.f = false;
        this.g = "gdpr";
    }

    private void a(UserConsentPreferences userConsentPreferences) {
        if (ConsentStatus.CONSENTED.equals(userConsentPreferences.getConsentStatus())) {
            HashMap hashMap = new HashMap();
            hashMap.put(DataSources.Key.CONSENT_STATUS, userConsentPreferences.getConsentStatus());
            hashMap.put(DataSources.Key.CONSENT_CATEGORIES, com.tealium.internal.g.a(userConsentPreferences.getConsentedCategories()));
            hashMap.put(DataSources.Key.POLICY, getPolicy());
            if (userConsentPreferences.getConsentedCategories().containsAll(this.a)) {
                hashMap.put(DataSources.Key.CALL_TYPE, "grant_full_consent");
                this.d.track("grant_full_consent", hashMap);
            } else {
                hashMap.put(DataSources.Key.CALL_TYPE, "grant_partial_consent");
                this.d.track("grant_partial_consent", hashMap);
            }
        }
    }

    private void b(UserConsentPreferences userConsentPreferences) {
        HashMap hashMap = new HashMap();
        hashMap.put(DataSources.Key.CONSENT_STATUS, userConsentPreferences.getConsentStatus());
        hashMap.put(DataSources.Key.CONSENT_CATEGORIES, com.tealium.internal.g.a(userConsentPreferences.getConsentedCategories()));
        hashMap.put(DataSources.Key.POLICY, getPolicy());
        hashMap.put(DataSources.Key.CALL_TYPE, "update_consent_cookie");
        this.d.track("update_consent_cookie", hashMap);
    }

    public Set<String> getConsentCategories() {
        return this.a;
    }

    @Deprecated
    public Set<String> getConsentCatergories() {
        return this.a;
    }

    public String getPolicy() {
        return this.g;
    }

    public String[] getUserConsentCategories() {
        return com.tealium.internal.g.a(this.c.getConsentedCategories());
    }

    public UserConsentPreferences getUserConsentPreferences() {
        return this.c;
    }

    public String getUserConsentStatus() {
        return this.c.getConsentStatus();
    }

    public boolean isConsentLogging() {
        return this.f;
    }

    @Override // com.tealium.internal.listeners.DispatchSendListener
    public void onDispatchSend(Dispatch dispatch) {
        if (ConsentStatus.CONSENTED.equals(getUserConsentStatus())) {
            dispatch.putIfAbsent(DataSources.Key.CONSENT_STATUS, getUserConsentStatus());
            dispatch.putIfAbsent(DataSources.Key.CONSENT_CATEGORIES, getUserConsentCategories());
        }
    }

    public void resetUserConsentPreferences() {
        UserConsentPreferences userConsentPreferences = this.c;
        if (userConsentPreferences != null) {
            userConsentPreferences.resetConsentPreferences();
        }
    }

    public void setConsentLoggingEnabled(boolean z) {
        this.f = z;
    }

    public void setPolicy(String str) {
        this.g = str;
    }

    public void setUserConsentCategories(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            setUserConsentStatusWithCategories(ConsentStatus.NOT_CONSENTED, null);
        }
        setUserConsentStatusWithCategories(ConsentStatus.CONSENTED, strArr);
    }

    public void setUserConsentStatus(String str) {
        if (ConsentStatus.CONSENTED.equals(str)) {
            setUserConsentStatusWithCategories(str, com.tealium.internal.g.a(this.a));
        } else {
            setUserConsentStatusWithCategories(str, null);
        }
    }

    public void setUserConsentStatusWithCategories(String str, String[] strArr) {
        if (!b(str)) {
            throw new IllegalArgumentException("Invalid status: " + str);
        }
        if (!a(strArr)) {
            throw new IllegalArgumentException("Invalid category list");
        }
        this.c.setConsentStatus(str);
        this.c.setConsentCategories(com.tealium.internal.g.a(strArr));
        com.tealium.internal.d dVar = this.e;
        if (dVar != null) {
            dVar.b(new v(this.c));
            b(this.c);
            if (isConsentLogging()) {
                a(this.c);
            }
        }
    }

    private boolean b(String str) {
        return this.b.contains(str);
    }

    void a(com.tealium.internal.d dVar) {
        this.e = dVar;
    }

    static ConsentManager a(String str, Tealium.Config config) {
        return new ConsentManager(config, a(str), UserConsentPreferences.create(config));
    }

    private static d a(String str) {
        return new c(str);
    }

    private boolean a(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                if (!this.a.contains(str)) {
                    return false;
                }
            }
        }
        return true;
    }
}
