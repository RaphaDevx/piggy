package com.tealium.library;

import android.app.Application;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.tealium.collect.listeners.CollectUpdateListener;
import com.tealium.collect.listeners.VisitorProfileUpdateListener;
import com.tealium.collect.visitor.VisitorProfile;
import com.tealium.internal.QueryParameterProvider;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.h;
import com.tealium.internal.j.k;
import com.tealium.internal.j.n;
import com.tealium.internal.j.p;
import com.tealium.internal.j.r;
import com.tealium.internal.j.s;
import com.tealium.internal.listeners.RequestFlushListener;
import com.tealium.library.DataSources;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.ProfileChunk;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes2.dex */
public final class Tealium {
    private static final Map<String, Tealium> m = new ConcurrentHashMap();
    private final com.tealium.internal.d a;
    private final DataSources b;
    private final com.tealium.internal.f c;
    private final com.tealium.internal.b d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private ConsentManager i;
    private volatile VisitorProfile j;
    private boolean k = false;
    private Queue<n> l;

    public static abstract class Config {
        protected static final ConsentManager DEFAULT_CONSENT_MANAGER = null;
        protected static final String DEFAULT_DATASOURCE_ID = null;
        protected static final String DEFAULT_FORCE_OVERRIDE_LOGLEVEL = null;
        protected static final boolean DEFAULT_IS_CONSENT_MANAGER_ENABLED = false;
        protected static final boolean DEFAULT_IS_COOKIE_MANAGER_ENABLED = true;
        protected static final boolean DEFAULT_IS_DEEP_LINK_TRACKING_ENABLED = true;
        protected static final boolean DEFAULT_IS_QR_TRACE_ENABLED = true;
        protected static final boolean DEFAULT_IS_REMOTE_COMMAND_ENABLED = true;
        protected static final boolean DEFAULT_IS_SESSION_COUNTING_ENABLED = true;
        protected static final boolean DEFAULT_IS_VDATA_COLLECT_ENDPOINT_ENABLED = false;
        protected static final long DEFAULT_MINUTES_BETWEEN_SESSION_ID = 30;
        protected static final String DEFAULT_OVERRIDE_COLLECT_DISPATCH_PROFILE = null;
        protected static final String DEFAULT_OVERRIDE_COLLECT_DISPATCH_URL = null;
        protected static final String DEFAULT_OVERRIDE_PUBLISH_SETTINGS_URL = null;
        protected static final String DEFAULT_OVERRIDE_TAG_MANAGEMENT_URL = null;
        protected static final String DEFAULT_OVERRIDE_VISITOR_SERVICE_DOMAIN = null;
        protected static final String DEFAULT_OVERRIDE_VISITOR_SERVICE_PROFILE = null;
        protected static final long DEFAULT_SECONDS_BEFORE_BATCH_TIMEOUT = -1;
        private long A;
        private long B;
        private ConsentManager C;
        private final Application a;
        private final String b;
        private final String c;
        private final String d;
        private final PublishSettings e;
        private final List<DispatchValidator> f;
        private final List<EventListener> g;
        private final List<QueryParameterProvider> h;
        private final File i;
        private final com.tealium.internal.c j;
        private final String k;
        private String l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        private boolean q;
        private boolean r;
        private boolean s;
        private String t;
        private String u;
        private String v;
        private String w;
        private String x;
        private String y;
        private String z;

        class a extends Config {
            a(Config config) {
                super(config, null);
            }

            @Override // com.tealium.library.Tealium.Config
            public Config setCookieManagerEnabled(boolean z) {
                throw new UnsupportedOperationException();
            }

            @Override // com.tealium.library.Tealium.Config
            public Config setOverrideCollectDispatchUrl(String str) {
                throw new UnsupportedOperationException();
            }

            @Override // com.tealium.library.Tealium.Config
            public Config setOverridePublishSettingsUrl(String str) {
                throw new UnsupportedOperationException();
            }

            @Override // com.tealium.library.Tealium.Config
            public Config setOverrideTagManagementUrl(String str) {
                throw new UnsupportedOperationException();
            }

            @Override // com.tealium.library.Tealium.Config
            public Config setRemoteCommandEnabled(boolean z) {
                throw new UnsupportedOperationException();
            }
        }

        class b extends Config {
            b(Application application, String str, String str2, String str3) {
                super(application, str, str2, str3, null);
            }
        }

        /* synthetic */ Config(Application application, String str, String str2, String str3, a aVar) {
            this(application, str, str2, str3);
        }

        static Config a(Config config) {
            return new a(config);
        }

        private static String b(List<?> list) {
            int size = list.size() - 1;
            String str = "[";
            int i = 0;
            while (i <= size) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(list.get(i));
                sb.append(i == size ? "" : ", ");
                str = sb.toString();
                i++;
            }
            return str + "]";
        }

        public static Config create(Application application, String str, String str2, String str3) {
            return new b(application, str, str2, str3);
        }

        public Config enableConsentManager(String str) {
            this.o = true;
            this.C = ConsentManager.a(str, this);
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Config)) {
                return false;
            }
            Config config = (Config) obj;
            if (!this.b.equals(config.b) || !this.c.equals(config.c) || !this.d.equals(config.d) || !this.f.equals(config.f) || !this.g.equals(config.g)) {
                return false;
            }
            List<QueryParameterProvider> list = this.h;
            return list.equals(list) && this.m == config.m && this.n == config.n && this.r == config.r && this.s == config.s && TextUtils.equals(this.t, config.t) && TextUtils.equals(this.l, config.l) && TextUtils.equals(this.u, config.u) && TextUtils.equals(this.v, config.v) && TextUtils.equals(this.w, config.w) && TextUtils.equals(this.x, config.x) && TextUtils.equals(this.y, config.y) && TextUtils.equals(this.z, config.z);
        }

        public final String getAccountName() {
            return this.b;
        }

        public final Application getApplication() {
            return this.a;
        }

        public final VisitorProfile getCachedVisitorProfile() {
            return d.c(this.i);
        }

        public final ConsentManager getConsentManager() {
            return this.C;
        }

        public final String getDatasourceId() {
            return this.l;
        }

        public final String getDefaultTagManagementUrl() {
            return this.k;
        }

        public final List<DispatchValidator> getDispatchValidators() {
            return this.f;
        }

        public final String getEnvironmentName() {
            return this.d;
        }

        public final List<EventListener> getEventListeners() {
            return this.g;
        }

        public final String getForceOverrideLogLevel() {
            return this.t;
        }

        public final com.tealium.internal.c getLogger() {
            return this.j;
        }

        public final long getMinutesBetweenSessionId() {
            return this.A;
        }

        public final String getOverrideCollectDispatchProfile() {
            return this.x;
        }

        public final String getOverrideCollectDispatchUrl() {
            return this.w;
        }

        public final String getOverridePublishSettingsUrl() {
            return this.v;
        }

        public final String getOverrideTagManagementUrl() {
            return this.u;
        }

        public String getOverrideVisitorServiceDomain() {
            return this.y;
        }

        public String getOverrideVisitorServiceProfile() {
            return this.z;
        }

        public final String getProfileName() {
            return this.c;
        }

        public final PublishSettings getPublishSettings() {
            return this.e;
        }

        public final List<QueryParameterProvider> getQueryParameterProviders() {
            return this.h;
        }

        public final long getSecondsBeforeBatchTimeout() {
            return this.B;
        }

        public final File getTealiumDir() {
            return this.i;
        }

        public boolean isConsentManagerEnabled() {
            return this.o;
        }

        public final boolean isCookieManagerEnabled() {
            return this.n;
        }

        public boolean isDeepLinkTrackingEnabled() {
            return this.s;
        }

        public boolean isQrTraceEnabled() {
            return this.r;
        }

        public final boolean isRemoteCommandEnabled() {
            return this.m;
        }

        public final boolean isSessionCountingEnabled() {
            return this.q;
        }

        public final boolean isVdataCollectEndpointEnabled() {
            return this.p;
        }

        public Config setCookieManagerEnabled(boolean z) {
            this.n = z;
            return this;
        }

        public Config setDatasourceId(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.l = str;
            return this;
        }

        public Config setDeepLinkTrackingEnabled(boolean z) {
            this.s = z;
            return this;
        }

        public Config setForceOverrideLogLevel(String str) {
            this.t = str;
            this.j.a(str);
            return this;
        }

        public Config setMinutesBetweenSessionId(long j) {
            this.A = j;
            return this;
        }

        public Config setOverrideCollectDispatchProfile(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.x = str;
            return this;
        }

        public Config setOverrideCollectDispatchUrl(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.w = str;
            return this;
        }

        public Config setOverridePublishSettingsUrl(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.v = str;
            return this;
        }

        public Config setOverrideTagManagementUrl(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.u = str;
            return this;
        }

        public Config setOverrideVisitorServiceDomain(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.y = str;
            return this;
        }

        public Config setOverrideVisitorServiceProfile(String str) {
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.z = str;
            return this;
        }

        public Config setQrTraceEnabled(boolean z) {
            this.r = z;
            return this;
        }

        public Config setRemoteCommandEnabled(boolean z) {
            this.m = z;
            return this;
        }

        public Config setSecondsBeforeBatchTimeout(long j) {
            this.B = j;
            return this;
        }

        public Config setSessionCountingEnabled(boolean z) {
            this.q = z;
            return this;
        }

        public Config setVdataCollectEndpointEnabled(boolean z) {
            this.p = z;
            return this;
        }

        public String toString() {
            String property = System.getProperty("line.separator");
            String string = this.a.getString(R.string.enabled);
            String string2 = this.a.getString(R.string.disabled);
            String string3 = this.a.getString(R.string.config_account_name);
            String string4 = this.a.getString(R.string.config_profile_name);
            String string5 = this.a.getString(R.string.config_environment_name);
            String string6 = this.a.getString(R.string.config_datasource_id);
            String string7 = this.a.getString(R.string.config_override_collect_dispatch_url);
            String string8 = this.a.getString(R.string.config_override_collect_dispatch_profile);
            String string9 = this.a.getString(R.string.config_override_publish_settings_url);
            String string10 = this.a.getString(R.string.config_override_publish_url);
            String string11 = this.a.getString(R.string.config_override_visitor_service_domain);
            String string12 = this.a.getString(R.string.config_override_visitor_service_profile);
            String string13 = this.a.getString(R.string.config_dispatch_validators);
            String string14 = this.a.getString(R.string.config_event_listeners);
            String string15 = this.a.getString(R.string.config_query_param_providers);
            String string16 = this.a.getString(R.string.config_remote_commands);
            String string17 = this.a.getString(R.string.config_cookie_manager_enabled);
            String string18 = this.a.getString(R.string.config_qr_trace_enabled);
            String string19 = this.a.getString(R.string.config_deep_link_handling_enabled);
            String string20 = this.a.getString(R.string.config_force_override_loglevel);
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.getString(R.string.config_publish_settings));
            sb.append(this.e.getSource() == null ? "(default)" : "(cached)");
            String sb2 = sb.toString();
            String str = "{" + property + "    " + string3 + ": " + this.b + property + "    " + string4 + ": " + this.c + property + "    " + string5 + ": " + this.d + property;
            if (this.l != null) {
                str = str + "    " + string6 + ": " + this.l + property;
            }
            if (this.w != null) {
                str = str + "    " + string7 + ": " + this.w + property;
            }
            if (this.x != null) {
                str = str + "    " + string8 + ": " + this.x + property;
            }
            if (this.v != null) {
                str = str + "    " + string9 + ": " + this.v + property;
            }
            if (this.u != null) {
                str = str + "    " + string10 + ": " + this.u + property;
            }
            if (this.y != null) {
                str = str + "    " + string11 + ": " + this.y + property;
            }
            if (this.z != null) {
                str = str + "    " + string12 + ": " + this.z + property;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("    ");
            sb3.append(string13);
            sb3.append(": ");
            sb3.append(b(this.f));
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string14);
            sb3.append(": ");
            sb3.append(b(this.g));
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string15);
            sb3.append(": ");
            sb3.append(b(this.h));
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string16);
            sb3.append(": ");
            sb3.append(this.m ? string : string2);
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string17);
            sb3.append(": ");
            sb3.append(this.n ? string : string2);
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string20);
            sb3.append(": ");
            sb3.append(this.t);
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string6);
            sb3.append(": ");
            sb3.append(this.l);
            sb3.append(property);
            sb3.append("    ");
            sb3.append(sb2);
            sb3.append(": ");
            sb3.append(this.e.toString("    "));
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string18);
            sb3.append(": ");
            sb3.append(this.r ? string : string2);
            sb3.append(property);
            sb3.append("    ");
            sb3.append(string19);
            sb3.append(": ");
            sb3.append(this.s ? string : string2);
            sb3.append(property);
            sb3.append("}");
            return sb3.toString();
        }

        /* synthetic */ Config(Config config, a aVar) {
            this(config);
        }

        private static <T> List<T> a(List<T> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (T t : list) {
                if (t != null) {
                    arrayList.add(t);
                }
            }
            return Collections.unmodifiableList(arrayList);
        }

        private Config(Application application, String str, String str2, String str3) {
            this.a = application;
            if (application != null) {
                this.b = str;
                if (!TextUtils.isEmpty(str)) {
                    this.c = str2;
                    if (!TextUtils.isEmpty(str2)) {
                        this.d = str3;
                        if (!TextUtils.isEmpty(str3)) {
                            this.k = new Uri.Builder().scheme("https").authority("tags.tiqcdn.com").appendPath("utag").appendPath(str).appendPath(str2).appendPath(str3).appendPath("mobile.html").appendQueryParameter("platform", ProfileChunk.PLATFORM_ANDROID).appendQueryParameter("device_os_version", Build.VERSION.RELEASE).appendQueryParameter(DataSources.Key.LIBRARY_VERSION, BuildConfig.VERSION_NAME).appendQueryParameter("sdk_session_count", "true").build().toString();
                            File file = new File(String.format(Locale.ROOT, "%s%ctealium%c%s%c%s%c%s", application.getFilesDir(), Character.valueOf(File.separatorChar), Character.valueOf(File.separatorChar), str, Character.valueOf(File.separatorChar), str2, Character.valueOf(File.separatorChar), str3));
                            this.i = file;
                            file.mkdirs();
                            this.g = new LinkedList();
                            this.h = new LinkedList();
                            this.l = DEFAULT_DATASOURCE_ID;
                            this.m = true;
                            this.n = true;
                            this.o = false;
                            this.r = true;
                            this.s = true;
                            this.t = DEFAULT_FORCE_OVERRIDE_LOGLEVEL;
                            this.w = DEFAULT_OVERRIDE_COLLECT_DISPATCH_URL;
                            this.x = DEFAULT_OVERRIDE_COLLECT_DISPATCH_PROFILE;
                            this.u = DEFAULT_OVERRIDE_TAG_MANAGEMENT_URL;
                            this.p = false;
                            this.q = true;
                            this.v = DEFAULT_OVERRIDE_PUBLISH_SETTINGS_URL;
                            this.y = DEFAULT_OVERRIDE_VISITOR_SERVICE_DOMAIN;
                            this.z = DEFAULT_OVERRIDE_VISITOR_SERVICE_PROFILE;
                            this.f = new LinkedList();
                            this.e = d.b(file);
                            this.C = DEFAULT_CONSENT_MANAGER;
                            this.j = new com.tealium.internal.c(application, str3);
                            this.A = DEFAULT_MINUTES_BETWEEN_SESSION_ID;
                            this.B = -1L;
                            return;
                        }
                    }
                }
            }
            throw new IllegalArgumentException("All parameters must non-null; and account, profile, environment must be provided.");
        }

        private Config(Config config) {
            this.b = config.b;
            this.a = config.a;
            this.g = a(config.getEventListeners());
            this.h = a(config.getQueryParameterProviders());
            this.k = config.k;
            this.f = a(config.f);
            this.d = config.d;
            this.l = config.l;
            this.w = config.w;
            this.x = config.x;
            this.p = config.p;
            this.q = config.q;
            this.v = config.v;
            this.u = config.u;
            this.y = config.y;
            this.z = config.z;
            this.c = config.c;
            this.e = config.e;
            this.C = config.C;
            this.m = config.m;
            this.n = config.n;
            this.o = config.o;
            this.r = config.r;
            this.s = config.s;
            this.t = config.t;
            this.i = config.i;
            this.j = config.j;
            this.A = config.A;
            this.B = config.B;
        }
    }

    class a implements h {
        a() {
        }

        @Override // com.tealium.internal.h
        public void a(Dispatch dispatch) {
            Tealium.this.a(dispatch);
        }
    }

    class b implements VisitorProfileUpdateListener {
        b() {
        }

        @Override // com.tealium.collect.listeners.VisitorProfileUpdateListener
        public void onVisitorProfileUpdated(VisitorProfile visitorProfile, VisitorProfile visitorProfile2) {
            if (visitorProfile2 != null) {
                Tealium.this.j = visitorProfile2;
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ Config a;
        final /* synthetic */ String b;

        c(Config config, String str) {
            this.a = config;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.tealium.internal.d dVar = Tealium.this.a;
                Tealium tealium = Tealium.this;
                dVar.a(new com.tealium.library.c(tealium, this.a, tealium.a, Tealium.this.b));
                Tealium.this.a.a(this.a.getLogger());
                Tealium.this.a.a(new d(this.a, Tealium.this.a));
                Tealium.this.a.a(new g(this.b, this.a, Tealium.this.a));
                if (!com.tealium.internal.g.c(this.a.getApplication())) {
                    Tealium.this.a.a(new com.tealium.library.b(this.a.getApplication(), Tealium.this.a));
                }
                Iterator<EventListener> it = this.a.getEventListeners().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next() instanceof CollectUpdateListener) {
                        Tealium.this.a.a(new com.tealium.internal.i.h(this.a, Tealium.this.a, Tealium.this.b.getVisitorId()));
                        break;
                    }
                }
                Tealium.this.b();
                Tealium.this.k = true;
                if (this.a.getLogger().c()) {
                    this.a.getLogger().c(R.string.tealium_init_with, this.b, this.a.toString());
                }
            } catch (Throwable th) {
                this.a.getLogger().a(R.string.tealium_error_init, th);
                Tealium.destroyInstance(this.b);
            }
        }
    }

    private Tealium(Config config, com.tealium.internal.d dVar) {
        this.e = config.getAccountName();
        this.f = config.getProfileName();
        this.g = config.getEnvironmentName();
        this.h = config.getDatasourceId();
        this.a = dVar;
        com.tealium.internal.f fVar = new com.tealium.internal.f(config, dVar);
        this.c = fVar;
        DataSources dataSources = new DataSources(config, fVar);
        this.b = dataSources;
        this.i = config.getConsentManager();
        com.tealium.internal.b bVar = new com.tealium.internal.b(config, dVar, dataSources, new a());
        this.d = bVar;
        dVar.a(dataSources.b());
        dVar.a(dataSources.a());
        dVar.a(fVar);
        dVar.a(a());
        dVar.a(bVar);
    }

    public static Tealium createInstance(String str, Config config) {
        if (str == null || config == null) {
            throw new IllegalArgumentException("key and config must be non-null");
        }
        Config a2 = Config.a(config);
        com.tealium.internal.d a3 = e.a(a2.getLogger());
        if (a2.isConsentManagerEnabled()) {
            a2.getConsentManager().a(a3);
        }
        Iterator<EventListener> it = a2.getEventListeners().iterator();
        while (it.hasNext()) {
            a3.a(it.next());
        }
        Tealium tealium = new Tealium(a2, a3);
        m.put(str, tealium);
        com.tealium.internal.d dVar = tealium.a;
        dVar.a(new com.tealium.library.a(a2, dVar));
        tealium.a.c(tealium.a(str, a2));
        return tealium;
    }

    public static void destroyInstance(String str) {
        Tealium remove;
        if (str == null || (remove = m.remove(str)) == null) {
            return;
        }
        remove.a.b(new com.tealium.internal.j.h(remove));
    }

    public static Tealium getInstance(String str) {
        if (str == null) {
            return null;
        }
        return m.get(str);
    }

    public void addRemoteCommand(RemoteCommand remoteCommand) {
        if (remoteCommand == null) {
            throw new IllegalArgumentException();
        }
        com.tealium.internal.j.c cVar = new com.tealium.internal.j.c(remoteCommand);
        if (this.k) {
            this.a.a(cVar);
        } else {
            a(cVar);
        }
    }

    public void disableConsentManager() {
        ConsentManager consentManager = this.i;
        if (consentManager != null) {
            consentManager.resetUserConsentPreferences();
            this.i = null;
        }
    }

    public String getAccountName() {
        return this.e;
    }

    public VisitorProfile getCachedVisitorProfile() {
        return this.j;
    }

    public ConsentManager getConsentManager() {
        return this.i;
    }

    public DataSources getDataSources() {
        return this.b;
    }

    public String getDatasourceId() {
        return this.h;
    }

    public String getEnvironmentName() {
        return this.g;
    }

    public String getProfileName() {
        return this.f;
    }

    public boolean isConsentManagerEnabled() {
        return this.i != null;
    }

    public void joinTrace(String str) {
        this.d.a(str);
    }

    public void killTraceVisitorSession() {
        this.d.b(null);
    }

    public void leaveTrace() {
        this.d.a();
    }

    public void removeRemoteCommand(RemoteCommand remoteCommand) {
        if (remoteCommand == null) {
            throw new IllegalArgumentException();
        }
        r rVar = new r(remoteCommand);
        if (this.k) {
            this.a.a(rVar);
        } else {
            a(rVar);
        }
    }

    public void requestFlush() {
        this.a.b(new s(RequestFlushListener.FlushReason.USER_REQUESTED));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void track(java.util.Map<java.lang.String, ?> r7) {
        /*
            r6 = this;
            r0 = -9223372036854775808
            if (r7 == 0) goto L1a
            java.lang.String r2 = "timestamp_unix"
            java.lang.Object r2 = r7.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L1a
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L1a
            long r2 = r2.longValue()     // Catch: java.lang.NumberFormatException -> L1a
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            goto L1b
        L1a:
            r2 = r0
        L1b:
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 != 0) goto L25
            com.tealium.internal.data.Dispatch r0 = new com.tealium.internal.data.Dispatch
            r0.<init>(r7)
            goto L2d
        L25:
            com.tealium.internal.data.Dispatch r0 = new com.tealium.internal.data.Dispatch
            r0.<init>(r2)
            r0.putAll(r7)
        L2d:
            r6.a(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tealium.library.Tealium.track(java.util.Map):void");
    }

    public void trackEvent(String str, Map<String, ?> map) {
        Dispatch dispatch = new Dispatch(map);
        if (!TextUtils.isEmpty(str)) {
            dispatch.put(DataSources.Key.LINK_ID, str);
            dispatch.putIfAbsent(DataSources.Key.EVENT_NAME, str);
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_EVENT, str);
        }
        dispatch.putIfAbsent(DataSources.Key.CALL_TYPE, "link");
        dispatch.putIfAbsent(DataSources.Key.TEALIUM_EVENT_TYPE, DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE);
        a(dispatch);
    }

    public void trackEventType(String str, String str2, Map<String, ?> map) {
        Dispatch dispatch = new Dispatch(map);
        String checkEventTypeValue = DataSources.EventTypeValue.checkEventTypeValue(str);
        if (!TextUtils.isEmpty(str2)) {
            dispatch.put(DataSources.Key.LINK_ID, str2);
            dispatch.putIfAbsent(DataSources.Key.EVENT_NAME, str2);
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_EVENT, str2);
        }
        if (checkEventTypeValue.equals(DataSources.EventTypeValue.VIEW_EVENT_TYPE)) {
            dispatch.putIfAbsent(DataSources.Key.CALL_TYPE, DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        } else {
            dispatch.putIfAbsent(DataSources.Key.CALL_TYPE, "link");
        }
        dispatch.putIfAbsent(DataSources.Key.TEALIUM_EVENT_TYPE, checkEventTypeValue);
        a(dispatch);
    }

    public void trackView(String str, Map<String, ?> map) {
        Dispatch dispatch = new Dispatch(map);
        if (!TextUtils.isEmpty(str)) {
            dispatch.put(DataSources.Key.TEALIUM_EVENT, str);
            dispatch.put(DataSources.Key.SCREEN_TITLE, str);
        }
        dispatch.putIfAbsent(DataSources.Key.PAGE_TYPE, "mobile_view");
        dispatch.putIfAbsent(DataSources.Key.CALL_TYPE, DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        dispatch.putIfAbsent(DataSources.Key.TEALIUM_EVENT_TYPE, DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        a(dispatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Queue<n> queue = this.l;
        if (queue == null || queue.isEmpty()) {
            return;
        }
        while (!this.l.isEmpty()) {
            this.a.a(this.l.poll());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Dispatch dispatch) {
        if (dispatch != null) {
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_ACCOUNT, this.e);
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_PROFILE, this.f);
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_ENVIRONMENT, this.g);
            dispatch.putIfAbsent(DataSources.Key.TEALIUM_VID, getDataSources().getVisitorId());
            this.a.b(new p(dispatch));
            this.a.b(new k(dispatch));
            return;
        }
        throw new IllegalArgumentException();
    }

    private VisitorProfileUpdateListener a() {
        return new b();
    }

    private Runnable a(String str, Config config) {
        return new c(config, str);
    }

    private void a(n nVar) {
        if (this.l == null) {
            this.l = new ConcurrentLinkedQueue();
        }
        this.l.add(nVar);
    }
}
