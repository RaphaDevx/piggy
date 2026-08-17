package com.tealium.library;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.g;
import com.tealium.internal.listeners.NewSessionListener;
import com.tealium.internal.listeners.PopulateDispatchListener;
import com.tealium.library.ConsentManager;
import com.tealium.library.Tealium;
import io.sentry.ProfileChunk;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class DataSources {
    private final SecureRandom a = new SecureRandom();
    private final Map<String, Object> b;
    private final SharedPreferences c;
    private final TelephonyManager d;
    private final WindowManager e;
    private final ConnectivityManager f;
    private final ActivityManager g;
    private final SimpleDateFormat h;
    private final SimpleDateFormat i;
    private final String j;
    private final com.tealium.internal.f k;
    private final boolean l;

    public static class EventTypeValue {
        public static final String ACTIVITY_EVENT_TYPE = "activity";
        public static final String CONVERSION_EVENT_TYPE = "conversion";
        public static final String DERIVED_EVENT_TYPE = "derived";
        public static final String INTERACTION_EVENT_TYPE = "interaction";
        public static final String VIEW_EVENT_TYPE = "view";

        private EventTypeValue() {
        }

        public static String checkEventTypeValue(String str) {
            if (str == null) {
                return ACTIVITY_EVENT_TYPE;
            }
            str.hashCode();
            switch (str) {
            }
            return ACTIVITY_EVENT_TYPE;
        }
    }

    public static class Key {
        public static final String APP_BUILD = "app_build";
        public static final String APP_MEMORY_USAGE = "app_memory_usage";
        public static final String APP_NAME = "app_name";
        public static final String APP_RDNS = "app_rdns";
        public static final String APP_UUID = "app_uuid";
        public static final String APP_VERSION = "app_version";
        public static final String ASSOCIATED_SCREEN_TITLE = "associated_screen_title";
        public static final String AUTOTRACKED = "autotracked";
        public static final String CALL_TYPE = "call_type";
        public static final String CARRIER = "carrier";
        public static final String CARRIER_ISO = "carrier_iso";
        public static final String CARRIER_MCC = "carrier_mcc";
        public static final String CARRIER_MNC = "carrier_mnc";
        public static final String CONNECTION_TYPE = "connection_type";
        public static final String CONSENT_CATEGORIES = "consent_categories";
        public static final String CONSENT_STATUS = "consent_status";
        public static final String CP_TRACE_ID = "cp.trace_id";
        public static final String CRASH_BUILD_ID = "crash_build_id";
        public static final String CRASH_COUNT = "crash_count";
        public static final String CRASH_EXCEPTION_CAUSE = "crash_cause";
        public static final String CRASH_EXCEPTION_NAME = "crash_name";
        public static final String CRASH_THREADS = "crash_threads";
        public static final String CRASH_UUID = "crash_uuid";
        public static final String DEEP_LINK_QUERY_PREFIX = "deep_link_param";
        public static final String DEEP_LINK_URL = "deep_link_url";
        public static final String DEVICE = "device";
        public static final String DEVICE_ARCHITECTURE = "device_architecture";
        public static final String DEVICE_AVAILABLE_EXTERNAL_STORAGE = "device_free_external_storage";
        public static final String DEVICE_AVAILABLE_SYSTEM_STORAGE = "device_free_system_storage";
        public static final String DEVICE_BATTERY_PERCENT = "device_battery_percent";
        public static final String DEVICE_CPUTYPE = "device_cputype";
        public static final String DEVICE_ISCHARGING = "device_ischarging";
        public static final String DEVICE_LANGUAGE = "device_language";
        public static final String DEVICE_ORIENTATION = "device_orientation";
        public static final String DEVICE_OS_BUILD = "device_os_build";
        public static final String DEVICE_OS_VERSION = "device_os_version";
        public static final String DEVICE_PUSH_TOKEN = "device_push_token";
        public static final String DEVICE_RESOLUTION = "device_resolution";
        public static final String DEVICE_RUNTIME = "device_android_runtime";
        public static final String EVENT = "event";
        public static final String EVENT_NAME = "event_name";
        public static final String EXCEPTION_NAME = "exception_name";
        public static final String EXCEPTION_REASON = "exception_reason";
        public static final String EXCEPTION_TOTALCRASHCOUNT = "exception_totalcrashcount";
        public static final String EXCEPTION_TRACE = "exception_trace";
        public static final String LIBRARY_VERSION = "library_version";
        public static final String LIFECYCLE_DAYOFWEEK_LOCAL = "lifecycle_dayofweek_local";
        public static final String LIFECYCLE_DAYSSINCELASTWAKE = "lifecycle_dayssincelastwake";
        public static final String LIFECYCLE_DAYSSINCELAUNCH = "lifecycle_dayssincelaunch";
        public static final String LIFECYCLE_DAYSSINCEUPDATE = "lifecycle_dayssinceupdate";
        public static final String LIFECYCLE_DIDDETECTCRASH = "lifecycle_diddetectcrash";
        public static final String LIFECYCLE_FIRSTLAUNCHDATE = "lifecycle_firstlaunchdate";
        public static final String LIFECYCLE_FIRSTLAUNCHDATE_MMDDYYYY = "lifecycle_firstlaunchdate_MMDDYYYY";
        public static final String LIFECYCLE_HOUROFDAY_LOCAL = "lifecycle_hourofday_local";
        public static final String LIFECYCLE_ISFIRSTLAUNCH = "lifecycle_isfirstlaunch";
        public static final String LIFECYCLE_ISFIRSTLAUNCHUPDATE = "lifecycle_isfirstlaunchupdate";
        public static final String LIFECYCLE_ISFIRSTWAKEMONTH = "lifecycle_isfirstwakemonth";
        public static final String LIFECYCLE_ISFIRSTWAKETODAY = "lifecycle_isfirstwaketoday";
        public static final String LIFECYCLE_LASTLAUNCHDATE = "lifecycle_lastlaunchdate";
        public static final String LIFECYCLE_LASTSLEEPDATE = "lifecycle_lastsleepdate";
        public static final String LIFECYCLE_LASTWAKEDATE = "lifecycle_lastwakedate";
        public static final String LIFECYCLE_LAUNCHCOUNT = "lifecycle_launchcount";
        public static final String LIFECYCLE_PRIORSECONDSAWAKE = "lifecycle_priorsecondsawake";
        public static final String LIFECYCLE_SECONDSAWAKE = "lifecycle_secondsawake";
        public static final String LIFECYCLE_SLEEPCOUNT = "lifecycle_sleepcount";
        public static final String LIFECYCLE_TOTALCRASHCOUNT = "lifecycle_totalcrashcount";
        public static final String LIFECYCLE_TOTALLAUNCHCOUNT = "lifecycle_totallaunchcount";
        public static final String LIFECYCLE_TOTALSECONDSAWAKE = "lifecycle_totalsecondsawake";
        public static final String LIFECYCLE_TOTALSLEEPCOUNT = "lifecycle_totalsleepcount";
        public static final String LIFECYCLE_TOTALWAKECOUNT = "lifecycle_totalwakecount";
        public static final String LIFECYCLE_TYPE = "lifecycle_type";
        public static final String LIFECYCLE_UPDATELAUNCHDATE = "lifecycle_updatelaunchdate";
        public static final String LIFECYCLE_WAKECOUNT = "lifecycle_wakecount";
        public static final String LINK_ID = "link_id";
        public static final String OBJECT_CLASS = "object_class";

        @Deprecated
        public static final String ORIENTATION = "orientation";
        public static final String ORIGIN = "origin";

        @Deprecated
        public static final String OS_VERSION = "os_version";
        public static final String PAGE_TYPE = "page_type";
        public static final String PLATFORM = "platform";
        public static final String POLICY = "policy";
        public static final String SCREEN_TITLE = "screen_title";
        public static final String SELECTED_ROW = "selected_row";
        public static final String SELECTED_TITLE = "selected_title";
        public static final String SELECTED_VALUE = "selected_value";
        public static final String TEALIUM_ACCOUNT = "tealium_account";
        public static final String TEALIUM_DATASOURCE_ID = "tealium_datasource";
        public static final String TEALIUM_ENVIRONMENT = "tealium_environment";
        public static final String TEALIUM_EVENT = "tealium_event";
        public static final String TEALIUM_EVENT_TYPE = "tealium_event_type";
        public static final String TEALIUM_ID = "tealium_id";
        public static final String TEALIUM_LIBRARY_NAME = "tealium_library_name";
        public static final String TEALIUM_LIBRARY_VERSION = "tealium_library_version";
        public static final String TEALIUM_PROFILE = "tealium_profile";
        public static final String TEALIUM_RANDOM = "tealium_random";
        public static final String TEALIUM_SESSION_ID = "tealium_session_id";
        public static final String TEALIUM_TIMESTAMP_EPOCH = "tealium_timestamp_epoch";
        public static final String TEALIUM_TRACE_ID = "tealium_trace_id";
        public static final String TEALIUM_VID = "tealium_vid";
        public static final String TEALIUM_VISITOR_ID = "tealium_visitor_id";
        public static final String TIMESTAMP = "timestamp";
        public static final String TIMESTAMP_LOCAL = "timestamp_local";
        public static final String TIMESTAMP_OFFSET = "timestamp_offset";
        public static final String TIMESTAMP_UNIX = "timestamp_unix";
        public static final String TIMESTAMP_UNIX_MILLISECONDS = "timestamp_unix_milliseconds";
        public static final String UUID = "uuid";
        public static final String VIDEO_ID = "video_id";
        public static final String VIDEO_LENGTH = "video_length";
        public static final String VIDEO_MILESTONE = "video_milestone";
        public static final String VIDEO_NAME = "video_name";
        public static final String VIDEO_PLATFORM = "video_platform";
        public static final String VIDEO_PLAYHEAD = "video_playhead";
        public static final String VIEW_HEIGHT = "view_height";
        public static final String VIEW_WIDTH = "view_width";
        public static final String VISITOR_ID = "visitor_id";
        public static final String WAS_QUEUED = "was_queued";
        public static final String WEBVIEW_SERVICETYPE = "webview_servicetype";
        public static final String WEBVIEW_URL = "webview_url";

        private Key() {
        }
    }

    class a implements PopulateDispatchListener {
        a() {
        }

        @Override // com.tealium.internal.listeners.PopulateDispatchListener
        public void onPopulateDispatch(Dispatch dispatch) {
            dispatch.putAllIfAbsent(DataSources.this.b);
            dispatch.putAllIfAbsent(DataSources.this.c.getAll());
            DataSources.this.a(dispatch);
        }
    }

    class b implements NewSessionListener {
        b() {
        }

        @Override // com.tealium.internal.listeners.NewSessionListener
        public void onNewSession(String str) {
            DataSources.this.b.put(Key.TEALIUM_SESSION_ID, str);
        }
    }

    DataSources(Tealium.Config config, com.tealium.internal.f fVar) {
        Context applicationContext = config.getApplication().getApplicationContext();
        this.d = (TelephonyManager) applicationContext.getSystemService("phone");
        WindowManager windowManager = (WindowManager) applicationContext.getSystemService("window");
        this.e = windowManager;
        this.f = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        this.g = (ActivityManager) applicationContext.getSystemService(EventTypeValue.ACTIVITY_EVENT_TYPE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        this.i = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.h = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT);
        this.k = fVar;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(7);
        this.b = concurrentHashMap;
        a(applicationContext, concurrentHashMap, fVar.a());
        SharedPreferences a2 = g.b.a(config);
        this.c = a2;
        a(applicationContext, windowManager, a2);
        String string = a2.getString(Key.VISITOR_ID, null);
        if (TextUtils.isEmpty(string) || !string.matches("^[0-9a-f]+$")) {
            string = a2.getString("uuid", "").replace("-", "").toLowerCase(Locale.ROOT);
            string = string.matches("^[0-9a-f]+$") ? string : UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.ROOT);
            a2.edit().putString(Key.VISITOR_ID, string).putString(Key.TEALIUM_VISITOR_ID, string).apply();
        }
        if (config.getDatasourceId() != null) {
            a2.edit().putString(Key.TEALIUM_DATASOURCE_ID, config.getDatasourceId()).apply();
        }
        this.j = string;
        this.l = com.tealium.internal.g.c(applicationContext);
    }

    private String c() {
        return String.format(Locale.ROOT, "%016d", Long.valueOf(Math.abs(this.a.nextLong() % 10000000000000000L)));
    }

    public long getLastTrackEvent() {
        return this.k.a().c();
    }

    public long getMinutesBetweenSessionId() {
        return this.k.b();
    }

    public SharedPreferences getPersistentDataSources() {
        return this.c;
    }

    public String getVisitorId() {
        return this.j;
    }

    public Map<String, Object> getVolatileDataSources() {
        return this.b;
    }

    public void refreshSessionIdIfNeeded() {
        this.k.c();
    }

    public void setLastTrackEvent(long j) {
        this.k.a().a(j);
    }

    public void setMinutesBetweenSessionId(long j) {
        this.k.a(j);
    }

    PopulateDispatchListener b() {
        return new a();
    }

    private static String b(String str) {
        if (str == null || str.length() <= 3) {
            return null;
        }
        return str.substring(3, str.length());
    }

    NewSessionListener a() {
        return new b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Dispatch dispatch) {
        String a2 = a(this.f);
        String language = Locale.getDefault().getLanguage();
        Date date = new Date(dispatch.getCreationTime());
        if (!this.l) {
            String networkOperatorName = this.d.getNetworkOperatorName();
            String networkCountryIso = this.d.getNetworkCountryIso();
            String networkOperator = this.d.getNetworkOperator();
            String b2 = b(this.e);
            dispatch.putIfAbsent(Key.CARRIER, networkOperatorName);
            dispatch.putIfAbsent(Key.CARRIER_ISO, networkCountryIso);
            dispatch.putIfAbsent(Key.CARRIER_MCC, a(networkOperator));
            dispatch.putIfAbsent(Key.CARRIER_MNC, b(networkOperator));
            dispatch.putIfAbsent("orientation", b2);
            dispatch.putIfAbsent(Key.DEVICE_ORIENTATION, b2);
        }
        dispatch.putIfAbsent("connection_type", a2);
        dispatch.putIfAbsent(Key.APP_MEMORY_USAGE, Long.toString(com.tealium.internal.g.a(this.g)));
        dispatch.putIfAbsent(Key.DEVICE_AVAILABLE_SYSTEM_STORAGE, Long.toString(com.tealium.internal.g.b()));
        dispatch.putIfAbsent(Key.DEVICE_AVAILABLE_EXTERNAL_STORAGE, Long.toString(com.tealium.internal.g.a()));
        dispatch.putIfAbsent(Key.DEVICE_LANGUAGE, language);
        dispatch.putIfAbsent("timestamp", this.i.format(date));
        dispatch.putIfAbsent(Key.TIMESTAMP_LOCAL, this.h.format(date));
        dispatch.putIfAbsent(Key.TIMESTAMP_OFFSET, String.format(Locale.ROOT, "%.0f", Float.valueOf(TimeZone.getDefault().getOffset(dispatch.getCreationTime()) / 3600000.0f)));
        dispatch.putIfAbsent(Key.TIMESTAMP_UNIX, Long.toString(dispatch.getCreationTime() / 1000));
        dispatch.putIfAbsent(Key.TIMESTAMP_UNIX_MILLISECONDS, Long.toString(dispatch.getCreationTime()));
        dispatch.putIfAbsent(Key.TEALIUM_TIMESTAMP_EPOCH, Long.toString(dispatch.getCreationTime() / 1000));
        dispatch.putIfAbsent(Key.TEALIUM_RANDOM, c());
    }

    private static String b(WindowManager windowManager) {
        int rotation = windowManager.getDefaultDisplay().getRotation();
        return rotation != 1 ? rotation != 2 ? rotation != 3 ? "Portrait" : "Landscape Left" : "Portrait UpsideDown" : "Landscape Right";
    }

    private void a(Context context, WindowManager windowManager, SharedPreferences sharedPreferences) {
        String property;
        String a2;
        String property2;
        String str;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!sharedPreferences.contains("device")) {
            String str2 = Build.MANUFACTURER;
            String str3 = Build.MODEL;
            if (str3.startsWith(str2)) {
                str = g.c.a(str3);
            } else {
                str = g.c.a(str2) + StringUtils.SPACE + str3;
            }
            edit.putString("device", str);
        }
        if (!sharedPreferences.contains(Key.DEVICE_ARCHITECTURE)) {
            edit.putString(Key.DEVICE_ARCHITECTURE, "32");
        }
        if (!sharedPreferences.contains(Key.DEVICE_CPUTYPE) && (property2 = System.getProperty("os.arch", null)) != null) {
            edit.putString(Key.DEVICE_CPUTYPE, property2);
        }
        if (!sharedPreferences.contains(Key.DEVICE_RESOLUTION) && (a2 = a(windowManager)) != null) {
            edit.putString(Key.DEVICE_RESOLUTION, a2);
        }
        if (!sharedPreferences.contains(Key.DEVICE_RUNTIME) && (property = System.getProperty("java.vm.version")) != null) {
            edit.putString(Key.DEVICE_RUNTIME, property);
        }
        if (!sharedPreferences.contains("origin")) {
            edit.putString("origin", com.tealium.internal.g.c(context) ? "tv" : ConsentManager.ConsentCategory.MOBILE);
        }
        if (!sharedPreferences.contains("platform")) {
            edit.putString("platform", ProfileChunk.PLATFORM_ANDROID);
        }
        if (!sharedPreferences.contains("uuid")) {
            edit.putString("uuid", UUID.randomUUID().toString());
            edit.putString(Key.APP_UUID, UUID.randomUUID().toString());
        }
        edit.apply();
    }

    private static void a(Context context, Map<String, Object> map, com.tealium.internal.e eVar) {
        String packageName = context.getPackageName();
        int i = context.getApplicationInfo().labelRes;
        if (i != 0) {
            map.put("app_name", context.getString(i));
        }
        String b2 = com.tealium.internal.g.b(context);
        String a2 = com.tealium.internal.g.a(context);
        if (b2 != null) {
            map.put("app_version", b2);
        }
        if (a2 != null) {
            map.put("app_build", a2);
        }
        map.put(Key.APP_RDNS, packageName);
        map.put(Key.DEVICE_OS_BUILD, Build.VERSION.INCREMENTAL);
        map.put("device_os_version", Build.VERSION.RELEASE);
        map.put(Key.LIBRARY_VERSION, BuildConfig.VERSION_NAME);
        map.put(Key.OS_VERSION, Build.VERSION.RELEASE);
        map.put(Key.TEALIUM_LIBRARY_NAME, ProfileChunk.PLATFORM_ANDROID);
        map.put(Key.TEALIUM_LIBRARY_VERSION, BuildConfig.VERSION_NAME);
        map.put(Key.TEALIUM_SESSION_ID, Long.valueOf(eVar.b()));
    }

    private static String a(String str) {
        if (str == null || str.length() < 3) {
            return null;
        }
        return str.substring(0, 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(android.net.ConnectivityManager r4) {
        /*
            android.net.NetworkInfo r0 = r4.getActiveNetworkInfo()
            r1 = 0
            if (r0 == 0) goto L26
            boolean r2 = r0.isConnectedOrConnecting()
            if (r2 == 0) goto L26
            int r2 = r0.getType()
            r3 = 1
            if (r2 != r3) goto L17
            java.lang.String r4 = "WIFI"
            return r4
        L17:
            int r0 = r0.getType()
            if (r0 != 0) goto L26
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()
            java.lang.String r4 = r4.getSubtypeName()
            goto L27
        L26:
            r4 = r1
        L27:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L2e
            goto L2f
        L2e:
            r1 = r4
        L2f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tealium.library.DataSources.a(android.net.ConnectivityManager):java.lang.String");
    }

    private static String a(WindowManager windowManager) {
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        if (point.x <= 0 || point.y <= 0) {
            return null;
        }
        return point.x + "x" + point.y;
    }
}
