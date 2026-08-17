package lib.android.paypal.com.magnessdk;

import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.storage.db.k;
import com.tealium.library.BuildConfig;
import com.tealium.library.DataSources;
import io.sentry.MonitorConfig;
import io.sentry.ProfilingTraceData;
import io.sentry.SentryEvent;
import io.sentry.Session;
import io.sentry.protocol.Device;
import io.sentry.protocol.SentryThread;

/* loaded from: classes4.dex */
public final class g {

    public enum a {
        AUDIT_KEY("audit"),
        FEATURE("type"),
        PAYLOAD("payload"),
        SENSOR_TYPE(com.salesforce.marketingcloud.push.g.g),
        SENSOR_PAYLOAD("p");

        private final String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public static final class b {
        public static final String a = "pairing_id";
        public static final String b = "correlation-id";
        public static final String c = "Android";
        static final int d = 36;
        static final int e = 30;
        public static final long f = 1000;
        static final long g = 0;
        static final int h = 50000;
        static final int i = 150;
        static final int j = 25;
        static final int k = 120;
        static final String l = "os.name";
        static final String m = "os.version";
        static final String n = "os.arch";
        public static final int o = 10;
        public static final int p = 10;
        public static final int q = 60000;
        public static final int r = 256;

        public enum a {
            MOBILE("MOBILE"),
            WIFI("WIFI"),
            VPN("VPN"),
            BLUETOOTH("Bluetooth Tethering"),
            WIFI_AWARE("WIFI AWARE"),
            LOWPAN("LOW PAN"),
            ETHERNET("Ethernet");

            private final String a;

            a(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        /* renamed from: lib.android.paypal.com.magnessdk.g$b$b, reason: collision with other inner class name */
        public enum EnumC0107b {
            EXCEPTION("Network Setup Exception, Check PPNetworkEnvironment for details");

            private final String a;

            EnumC0107b(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum c {
            CMID_EXCEPTION_MESSAGE("PayPal-Client-Metadata-Id exceeds the maximum length allowed. This is your own unique identifier for the payload. If you do not pass in this value, a new PayPal-Client-Metadata-Id is generated per method call. ***Maximum length: 32 characters***"),
            APPGUID_EXCEPTION_MESSAGE("Application’s Globally Unique Identifier (AppGUID) does not match the criteria, This is a string that identifies the merchant application that sets up Magnes on the mobile device. If the merchant app does not pass an AppGuid, Magnes creates one to identify the app. An AppGuid is an application identifier per-installation; that is, if a new instance of the app is installed on the mobile device, or the app is reinstalled, it will have a new AppGuid.\n ***AppGuid Criteria*** \n   Max length: 36 characters \n   Min Length: 30 characters \n   Regex: Letters, numbers and dashes only \n");

            private final String a;

            c(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        private b() {
        }
    }

    public enum c {
        RAMP("ramp"),
        REMOTE("remote");

        private final String a;

        c(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum d {
        ANDROID_ID(lib.android.paypal.com.magnessdk.c.f),
        APP_FIRST_INSTALL_TIME("app_first_install_time"),
        APP_GUID("app_guid"),
        APP_ID("app_id"),
        APP_LAST_UPDATE_TIME("app_last_update_time"),
        APP_VERSION("app_version"),
        COMP_VERSION("comp_version"),
        CONF_URL("conf_url"),
        CPU("cpu"),
        DEVICE_MODEL(ProfilingTraceData.JsonKeys.DEVICE_MODEL),
        DEVICE_NAME("device_name"),
        DISK("disk"),
        EMULATOR_FLAGS("ef"),
        GSF_ID("gsf_id"),
        IN_TREATMENT(com.salesforce.marketingcloud.push.g.g),
        IS_EMULATOR("is_emulator"),
        IS_ROOTED("is_rooted"),
        MAC_ADDRS("mac_addrs"),
        MAGNES_GUID("magnes_guid"),
        MAGNES_SOURCE("magnes_source"),
        NOT_COLLECTIBLE_LIST("nc"),
        NOTIF_TOKEN("notif_token"),
        OS_TYPE("os_type"),
        OS_VERSION(DataSources.Key.OS_VERSION),
        PAYLOAD_TYPE("payload_type"),
        ROOTED_FLAGS("rf"),
        SCREEN("screen"),
        SENSOR_METADATA("smd"),
        SMS_ENABLED("sms_enabled"),
        SOURCE_APP_VERSION("source_app_version"),
        SYSTEM("system"),
        T(com.salesforce.marketingcloud.push.g.g),
        TOTAL_STORAGE_SPACE("total_storage_space"),
        USER_AGENT(Session.JsonKeys.USER_AGENT);

        private final String a;

        public enum a {
            CORES("cores"),
            MAX_FREQUENCY("maxFreq"),
            MIN_FREQUENCY("minFreq");

            private final String a;

            a(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum b {
            TOTAL_SD("total_sd"),
            TOTAL_UD("total");

            private final String a;

            b(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum c {
            ANDROID_SDK_BUILD_FOR_X86("Android SDK built for x86"),
            ANDROID_X86("android_x86"),
            ANDY("andy"),
            ANDY_OS("AndyOS"),
            ANDY_OSX("AndyOSX"),
            DRIOD_4X("Driod4X"),
            DROID_4X("Droid4X"),
            GENERIC("generic"),
            GENERIC_X86("generic_x86"),
            GENY_MOTION("Genymotion"),
            GOLDFISH("goldfish"),
            GOODLE_SDK("google_sdk"),
            SDK("sdk"),
            UNKNOWN("unknown"),
            VBOX_86("vbox86"),
            VBOX_86P("vbox86p"),
            RANCHU("ranchu"),
            REMIXEMU("remixemu"),
            TTVM_X86("ttVM_x86");

            private final String a;

            c(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        /* renamed from: lib.android.paypal.com.magnessdk.g$d$d, reason: collision with other inner class name */
        public enum EnumC0108d {
            DENSITY("density"),
            DENSITY_DPI("densityDpi"),
            HEIGHT("height"),
            SCALE("scale"),
            WIDTH("width"),
            X_DPI("xdpi"),
            Y_DPI("ydpi");

            private final String a;

            EnumC0108d(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum e {
            FIFO_MAX_EVENT_COUNT("mec"),
            MAX_RANGE("mr"),
            NAME("n"),
            POWER("pwr"),
            RESOLUTION("re"),
            VENDOR("v"),
            VERSION("ver");

            private final String a;

            e(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum f {
            VERSION("version"),
            BOARD("board"),
            BOOTLOADER("bootloader"),
            CPU_ABI1("cpu_abi1"),
            DISPLAY(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION),
            RADIO("radio"),
            FINGERPRINT(SentryEvent.JsonKeys.FINGERPRINT),
            HARDWARE("hardware"),
            MANUFACTURER(Device.JsonKeys.MANUFACTURER),
            PRODUCT("product"),
            TIME("time"),
            SYSTEM_TYPE("system_type");

            private final String a;

            f(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        d(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum e {
        BASE_STATION_ID("base_station_id"),
        BATTERY("battery"),
        BSSID("bssid"),
        BSSID_ARRAY("bssid_array"),
        C("c"),
        CDMA_NETWORK_ID("cdma_network_id"),
        CDMA_SYSTEM_ID("cdma_system_id"),
        CELL_ID("cell_id"),
        CONF_VERSION("conf_version"),
        CONN_TYPE("conn_type"),
        DC_ID("dc_id"),
        DEVICE_ID(k.a.p),
        DEVICE_UPTIME("device_uptime"),
        DISK("disk"),
        DS("ds"),
        IP_ADDRESSES("ip_addresses"),
        IP_ADDRS("ip_addrs"),
        IS_DEV_MODE_ON("dmo"),
        KNOWN_APPS("known_apps"),
        LINKER_ID("linker_id"),
        LOCALE_COUNTRY("locale_country"),
        LOCALE_LANG("locale_lang"),
        LOCATION("location"),
        LOCATION_AREA_CODE("location_area_code"),
        MEMORY("memory"),
        MG_ID("mg_id"),
        NETWORK_OPERATOR("network_operator"),
        PHONE_TYPE("phone_type"),
        PL("pl"),
        PROXY_SETTING("proxy_setting"),
        RISK_COMP_SESSION_ID("risk_comp_session_id"),
        ROAMING("roaming"),
        SCREEN("screen"),
        SERIAL_NUMBER("serial_number"),
        SIM_OPERATOR_NAME("sim_operator_name"),
        SIM_SERIAL_NUMBER("sim_serial_number"),
        SR("sr"),
        SSID("ssid"),
        SUBSCRIBER_ID("subscriber_id"),
        T(com.salesforce.marketingcloud.push.g.g),
        TIMESTAMP("timestamp"),
        TZ("tz"),
        TZ_NAME("tz_name"),
        VPN_SETTING("VPN_setting");

        private final String a;

        public enum a {
            CURRENT(SentryThread.JsonKeys.CURRENT),
            LEVEL("level"),
            METHOD("method"),
            LOW_POWER("low_power"),
            STATE(SentryThread.JsonKeys.STATE),
            TEMP("temp"),
            VOLTAGE("voltage");

            private final String a;

            a(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum b {
            FREE_SD("free_sd"),
            FREE_UD("free"),
            MOUNTED("mounted");

            private final String a;

            b(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum c {
            FREE("free"),
            FREE_RUNTIME("free_runtime"),
            MAX_RUNTIME(MonitorConfig.JsonKeys.MAX_RUNTIME),
            TOTAL("total"),
            TOTAL_RUNTIME("total_runtime");

            private final String a;

            c(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        e(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    static class f {
        static final String[] a = {"com.bignox.appcenter", "com.bluestacks.settings", "com.bluestacks.filemanager", "com.genymotion.superuser", "org.greatfruit.andy.ime", "com.kaopu001.tiantianserver", "com.tiantian.ime", "com.microvirt.installer", "com.android.ld.appstore", "com.ldmnq.launcher3", "com.jide.Appstore"};
        static final String[] b = {"init.android_x86.rc", "ueventd.android_x86.rc", "fstab.android_x86", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc", "ueventd.android_x86_64.rc", "init.android_x86_64.rc", "fstab.goldfish", "init.goldfish.rc", "init.superuser.rc"};
        static final String[] c = {"/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props"};
        static final String[] d = {"/dev/socket/qemud", "/dev/qemu_pipe"};
        static final String[] e = {"goldfish"};
        static final String[] f = {"init.ranchu.rc", "init.remixos.rc", "init.andy.rc", "ueventd.andy.rc", "bin/genybaseband", "bin/genymotion-vbox-sf", "ueventd.nox.rc", "init.nox.rc", "/system/bin/noxd"};

        static final class a {
            public static final int a = 5;
            public static final int b = 0;
            public static final int c = 1;
            public static final int d = 2;
            public static final int e = 3;
            public static final int f = 4;

            private a() {
            }
        }

        private f() {
        }
    }

    /* renamed from: lib.android.paypal.com.magnessdk.g$g, reason: collision with other inner class name */
    public enum EnumC0109g {
        PRODUCTION(lib.android.paypal.com.magnessdk.a.d),
        STAGE("stage");

        private final String a;

        EnumC0109g(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum h {
        NETWORKING("MagnesNetworking");

        private final String a;

        public enum a {
            HTTP_CONNECT_TIMEOUT(60000),
            HTTP_READ_TIMEOUT(60000),
            READ_BYTE(1024);

            private final int a;

            a(int i) {
                this.a = i;
            }

            public int b() {
                return this.a;
            }
        }

        public enum b {
            POST("POST"),
            GET("GET");

            private final String a;

            b(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum c {
            GET_REQUEST_STARTED(50),
            GET_REQUEST_ERROR(51),
            GET_REQUEST_SUCCEEDED(52),
            POST_REQUEST_STARTED(53),
            POST_REQUEST_ERROR(54),
            POST_REQUEST_SUCCEEDED(55),
            HTTP_STATUS_FAILED(-1),
            HTTP_STATUS_200(200);

            private final int a;

            c(int i) {
                this.a = i;
            }

            public static c a(int i) {
                c cVar = GET_REQUEST_STARTED;
                if (i == cVar.b()) {
                    return cVar;
                }
                c cVar2 = GET_REQUEST_ERROR;
                if (i == cVar2.b()) {
                    return cVar2;
                }
                c cVar3 = GET_REQUEST_SUCCEEDED;
                if (i == cVar3.b()) {
                    return cVar3;
                }
                c cVar4 = POST_REQUEST_STARTED;
                if (i == cVar4.b()) {
                    return cVar4;
                }
                c cVar5 = POST_REQUEST_ERROR;
                if (i == cVar5.b()) {
                    return cVar5;
                }
                c cVar6 = POST_REQUEST_SUCCEEDED;
                if (i == cVar6.b()) {
                    return cVar6;
                }
                c cVar7 = HTTP_STATUS_FAILED;
                if (i == cVar7.b()) {
                    return cVar7;
                }
                c cVar8 = HTTP_STATUS_200;
                if (i == cVar8.b()) {
                    return cVar8;
                }
                return null;
            }

            public int b() {
                return this.a;
            }
        }

        public enum d {
            AUDIT_JSON_URL(lib.android.paypal.com.magnessdk.a.e),
            DEVICE_INFO_URL(lib.android.paypal.com.magnessdk.a.g),
            PRODUCTION_BEACON_URL(lib.android.paypal.com.magnessdk.a.h),
            PRODUCTION_JSON_URL(lib.android.paypal.com.magnessdk.a.i),
            RAMP_CONFIG_URL(lib.android.paypal.com.magnessdk.a.j),
            REMOTE_CONFIG_URL(lib.android.paypal.com.magnessdk.a.k),
            SANDBOX_DEVICE_INFO_URL(lib.android.paypal.com.magnessdk.a.m),
            SANDBOX_AUDIT_JSON_URL(lib.android.paypal.com.magnessdk.a.l),
            SANDBOX_PROD_JSON_URL(lib.android.paypal.com.magnessdk.a.l);

            private final String a;

            d(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        h(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum i {
        OPEN("o"),
        EXCLUDED("e"),
        MIN_VERSION("m"),
        RAMP_THRESHOLD("r"),
        APP_IDS("ai"),
        APP_SOURCES("as"),
        CONF_REFRESH_TIME_KEY("cr_ti");

        private final String a;

        i(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum j {
        CONF_VERSION("conf_version"),
        CONF_ENDPOINT_URL("endpoint_url"),
        CONF_REFRESH_TIME_KEY("conf_refresh_time_interval"),
        ANDROID_APPS_TO_CHECK("android_apps_to_check"),
        NOT_COLLECTABLE("nc"),
        MG_ID("m"),
        SENSOR_COLLECT_TIME("s");

        private final String a;

        j(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum k {
        KNOWN_ROOT_APPS_PACKAGES("com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.topjohnwu.magisk"),
        SU_PATHS("/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/system/xbin/daemonsu/", "/system/etc/init.d/99SuperSUDaemon/", "/system/bin/.ext/.su/", "/system/etc/.has_su_daemon/", "/system/etc/.installed_su_daemon/", "/cache/", "/data/", "/dev/");

        private final String[] a;

        public enum a {
            SU("su"),
            BUSYBOX("busybox"),
            MAGISK("magisk");

            private final String a;

            a(String str) {
                this.a = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.a;
            }
        }

        public enum b {
            NUMBER_OF_ROOTED_FLAGS(7),
            IS_TEST_KEYS_FOUND(0),
            IS_SU_FOUND(1),
            IS_SUPER_USER_APK_FOUND(2),
            DETECT_ROOT_MANAGEMENT_APPS(3),
            CHECK_FOR_BINARY_SU(4),
            CHECK_FOR_BINARY_BUSYBOX(5),
            CHECK_FOR_BINARY_MAGISK(6);

            private final int a;

            b(int i) {
                this.a = i;
            }

            public static b a(int i) {
                b bVar = IS_TEST_KEYS_FOUND;
                if (i == bVar.b()) {
                    return bVar;
                }
                b bVar2 = IS_SU_FOUND;
                if (i == bVar2.b()) {
                    return bVar2;
                }
                b bVar3 = IS_SUPER_USER_APK_FOUND;
                if (i == bVar3.b()) {
                    return bVar3;
                }
                b bVar4 = DETECT_ROOT_MANAGEMENT_APPS;
                if (i == bVar4.b()) {
                    return bVar4;
                }
                b bVar5 = CHECK_FOR_BINARY_SU;
                if (i == bVar5.b()) {
                    return bVar5;
                }
                b bVar6 = CHECK_FOR_BINARY_BUSYBOX;
                if (i == bVar6.b()) {
                    return bVar6;
                }
                b bVar7 = CHECK_FOR_BINARY_MAGISK;
                if (i == bVar7.b()) {
                    return bVar7;
                }
                return null;
            }

            public int b() {
                return this.a;
            }
        }

        k(String... strArr) {
            this.a = strArr;
        }

        public String[] b() {
            return this.a;
        }
    }

    public enum l {
        AC(com.salesforce.marketingcloud.push.g.l),
        GY("gy"),
        MG("mg");

        private final String a;

        l(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum m {
        FLIGHT_TIME("flightTime"),
        TELEMETRY_EVENTS("Events"),
        TYPING_SPEED(q.s1),
        VIEW_ID("viewId"),
        AUTO_COMPLETE_EVENT("wsac"),
        BACKSPACE_EVENT("wsbs"),
        CUT_EVENT("wsct"),
        PRE_FILLED_EVENT("wspf"),
        REGULAR_TYPING_EVENT("wsrt"),
        FAILED_TO_CONVERT_TO_JSON("Failed to convert to JSON");

        private final String a;

        m(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum n {
        TOUCH_EVENT("te"),
        FORCE("f"),
        RADIUS("r"),
        TOUCH_LOCATION_X("x"),
        TOUCH_LOCATION_Y("y"),
        TOUCH_COUNT("tc"),
        START_TIME("st"),
        END_TIME("et"),
        MAXIMUM_PRECISION("10000000d"),
        UNINITIALIZED_TIME("-1000"),
        MAXIMUM_EVENT_COUNT("400"),
        MAXIMUM_TIME_ALLOWED("5000"),
        MAXIMUM_TOUCH_COUNT(BuildConfig.PUBLISH_SETTINGS_VERSION);

        private final String a;

        n(String str) {
            this.a = str;
        }

        public double b() {
            return Double.parseDouble(this.a);
        }

        public int c() {
            return Integer.parseInt(this.a);
        }

        public long d() {
            return Long.parseLong(this.a);
        }

        public String e() {
            return this.a;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    private g() {
    }
}
