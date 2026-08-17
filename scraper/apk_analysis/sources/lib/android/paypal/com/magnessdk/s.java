package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.webkit.WebSettings;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import io.sentry.protocol.ViewHierarchy;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class s extends q {
    private String A1;
    private String B1;
    private String C1;
    private String D1;
    private String E1;
    private String F1;
    private String G1;
    private String H1;
    private String I1;
    private String J1;
    private String K1;
    private int L1;
    private boolean M1;
    private boolean N1;
    private String O1;
    private boolean P1;
    private String Q1;
    private long R1 = -1;
    private long S1 = -1;
    private long T1 = -1;
    private JSONObject U1;
    private JSONObject V1;
    private JSONObject W1;
    private JSONObject X1;
    private JSONObject Y1;
    private JSONArray Z1;
    private JSONObject a2;
    private JSONArray b2;
    private k c2;
    private String x1;
    private String y1;
    private String z1;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]+", file.getName());
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.k.b.values().length];
            a = iArr;
            try {
                iArr[g.k.b.IS_TEST_KEYS_FOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.k.b.IS_SU_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.k.b.IS_SUPER_USER_APK_FOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.k.b.DETECT_ROOT_MANAGEMENT_APPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[g.k.b.CHECK_FOR_BINARY_SU.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[g.k.b.CHECK_FOR_BINARY_BUSYBOX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[g.k.b.CHECK_FOR_BINARY_MAGISK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static final class c {
        private static int a = 5;
        private static Boolean[] b;

        private c() {
        }

        private static boolean a() {
            return g() || lib.android.paypal.com.magnessdk.d.a(g.f.f, "");
        }

        private static boolean a(Context context) {
            return p.a(context, new ArrayList(Arrays.asList(g.f.a)));
        }

        static String b(Context context) {
            if (b == null) {
                b = new Boolean[a];
                int i = 0;
                while (i < a) {
                    b[i] = Boolean.valueOf(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? false : b() : a(context) : c() : a() : h());
                    i++;
                }
            }
            return o.a(b);
        }

        private static boolean b() {
            return d() || f() || e();
        }

        private static boolean c() {
            return lib.android.paypal.com.magnessdk.d.a(g.f.b, "");
        }

        static boolean c(Context context) {
            String b2 = b(context);
            return b2 != null && b2.contains("1");
        }

        private static boolean d() {
            return lib.android.paypal.com.magnessdk.d.a(g.f.d, "");
        }

        private static boolean e() {
            return lib.android.paypal.com.magnessdk.d.a(g.f.c, "");
        }

        private static boolean f() {
            return lib.android.paypal.com.magnessdk.d.a(g.f.e, "");
        }

        private static boolean g() {
            return new File(android.os.Environment.getExternalStorageDirectory().toString() + File.separatorChar + ViewHierarchy.JsonKeys.WINDOWS + File.separatorChar + "BstSharedFolder").exists();
        }

        private static boolean h() {
            return m() || i() || j() || l() || n() || k() || o();
        }

        private static boolean i() {
            String str = Build.BRAND;
            return str.equals(g.d.c.GENERIC.toString()) || str.equals(g.d.c.GENERIC_X86.toString()) || str.equals("Android") || str.equals(g.d.c.ANDY_OS.toString());
        }

        private static boolean j() {
            String str = Build.DEVICE;
            return str.equals(g.d.c.ANDY_OSX.toString()) || str.equals(g.d.c.DROID_4X.toString()) || str.equals(g.d.c.GENERIC.toString()) || str.equals(g.d.c.GENERIC_X86.toString()) || str.equals(g.d.c.VBOX_86P.toString());
        }

        private static boolean k() {
            return Build.FINGERPRINT.startsWith(g.d.c.GENERIC.toString());
        }

        private static boolean l() {
            String str = Build.HARDWARE;
            return str.equals(g.d.c.GOLDFISH.toString()) || str.equals(g.d.c.VBOX_86.toString()) || str.equals(g.d.c.ANDY.toString()) || str.equals(g.d.c.RANCHU.toString()) || str.equals(g.d.c.TTVM_X86.toString()) || str.equals(g.d.c.ANDROID_X86.toString());
        }

        private static boolean m() {
            String str = Build.MANUFACTURER;
            return str.equals(g.d.c.UNKNOWN.toString()) || str.equals(g.d.c.GENY_MOTION.toString()) || str.contains(g.d.c.ANDY_OS.toString());
        }

        private static boolean n() {
            String str = Build.MODEL;
            return str.equals(g.d.c.SDK.toString()) || str.equals(g.d.c.GOODLE_SDK.toString()) || str.equals(g.d.c.ANDROID_SDK_BUILD_FOR_X86.toString());
        }

        private static boolean o() {
            String str = Build.PRODUCT;
            return str.matches(".*_?sdk_?.*") || str.equals(g.d.c.VBOX_86P.toString()) || str.equals(g.d.c.GENY_MOTION.toString()) || str.equals(g.d.c.DRIOD_4X.toString()) || str.equals(g.d.c.ANDY_OSX.toString()) || str.equals(g.d.c.REMIXEMU.toString());
        }
    }

    static final class d {
        private static int a = g.k.b.NUMBER_OF_ROOTED_FLAGS.b();
        private static Boolean[] b = null;

        private d() {
        }

        private static boolean a() {
            try {
                return new File(b("suFileName")).exists();
            } catch (Exception e) {
                e.a((Class<?>) d.class, 3, e);
                return false;
            }
        }

        private static boolean a(Context context) {
            return p.a(context, new ArrayList(Arrays.asList(g.k.KNOWN_ROOT_APPS_PACKAGES.b())));
        }

        private static boolean a(String str) {
            return lib.android.paypal.com.magnessdk.d.a(g.k.SU_PATHS.b(), str);
        }

        static String b(Context context) {
            boolean c;
            g.k.a aVar;
            if (b == null) {
                b = new Boolean[a];
                for (int i = 0; i < a; i++) {
                    g.k.b a2 = g.k.b.a(i);
                    if (a2 == null) {
                        return null;
                    }
                    switch (b.a[a2.ordinal()]) {
                        case 1:
                            c = c();
                            continue;
                        case 2:
                            c = a();
                            continue;
                        case 3:
                            c = b();
                            continue;
                        case 4:
                            c = a(context);
                            continue;
                        case 5:
                            aVar = g.k.a.SU;
                            break;
                        case 6:
                            aVar = g.k.a.BUSYBOX;
                            break;
                        case 7:
                            aVar = g.k.a.MAGISK;
                            break;
                        default:
                            c = false;
                            continue;
                    }
                    c = a(aVar.toString());
                    b[i] = Boolean.valueOf(c);
                }
            }
            return o.a(b);
        }

        private static String b(String str) throws IOException {
            Properties properties = new Properties();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(lib.android.paypal.com.magnessdk.c.h.getBytes("UTF-8"));
            try {
                try {
                    properties.load(byteArrayInputStream);
                } catch (Exception e) {
                    e.a((Class<?>) d.class, 3, e);
                }
                byteArrayInputStream.close();
                return properties.getProperty(str);
            } catch (Throwable th) {
                byteArrayInputStream.close();
                throw th;
            }
        }

        private static boolean b() {
            try {
                return new File(b("superUserApk")).exists();
            } catch (Exception e) {
                e.a((Class<?>) d.class, 3, e);
                return false;
            }
        }

        private static boolean c() {
            String str = Build.TAGS;
            return str != null && str.contains("test-keys");
        }

        static boolean c(Context context) {
            String b2 = b(context);
            if (b2 != null) {
                return b2.contains("1");
            }
            return false;
        }
    }

    s() {
    }

    private JSONArray a(MagnesSettings magnesSettings) {
        try {
            JSONArray jSONArray = new JSONArray();
            SensorManager sensorManager = (SensorManager) magnesSettings.getContext().getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            Sensor defaultSensor2 = sensorManager.getDefaultSensor(4);
            Sensor defaultSensor3 = sensorManager.getDefaultSensor(2);
            if (defaultSensor != null) {
                JSONObject a2 = o.a(defaultSensor);
                a2.put(g.a.SENSOR_TYPE.toString(), g.l.AC.toString());
                jSONArray.put(a2);
            }
            if (defaultSensor2 != null) {
                JSONObject a3 = o.a(defaultSensor2);
                a3.put(g.a.SENSOR_TYPE.toString(), g.l.GY.toString());
                jSONArray.put(a3);
            }
            if (defaultSensor3 != null) {
                JSONObject a4 = o.a(defaultSensor3);
                a4.put(g.a.SENSOR_TYPE.toString(), g.l.MG.toString());
                jSONArray.put(a4);
            }
            return jSONArray;
        } catch (Exception e) {
            e.a((Class<?>) s.class, 3, e);
            return null;
        }
    }

    private int b(int i) {
        int i2;
        int i3;
        File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new a());
        if (listFiles != null) {
            if (i == 3) {
                i2 = listFiles.length;
            } else {
                int i4 = 0;
                if (i == 2) {
                    int length = listFiles.length;
                    i3 = Integer.MAX_VALUE;
                    while (i4 < length) {
                        String c2 = lib.android.paypal.com.magnessdk.d.c(new File(listFiles[i4].getPath() + "/cpufreq/cpuinfo_min_freq"));
                        if (c2 != null && !c2.equals("-403")) {
                            int parseInt = Integer.parseInt(c2);
                            if (parseInt < i3) {
                                i3 = parseInt;
                            }
                            i4++;
                        }
                        i2 = -403;
                        break;
                    }
                    i2 = i3;
                } else if (i == 1) {
                    int length2 = listFiles.length;
                    i3 = 0;
                    while (i4 < length2) {
                        File file = new File(listFiles[i4].getPath() + "/cpufreq/cpuinfo_max_freq");
                        String c3 = lib.android.paypal.com.magnessdk.d.c(file);
                        if (c3 != null && !c3.equals("-403")) {
                            int parseInt2 = Integer.parseInt(lib.android.paypal.com.magnessdk.d.c(file));
                            if (parseInt2 > i3) {
                                i3 = parseInt2;
                            }
                            i4++;
                        }
                        i2 = -403;
                        break;
                    }
                    i2 = i3;
                }
            }
            if (i2 == 0 && i2 != Integer.MAX_VALUE) {
                return i2;
            }
        }
        i2 = 12345;
        return i2 == 0 ? 12345 : 12345;
    }

    private long b(String str) {
        StatFs statFs = new StatFs(str);
        return statFs.getBlockSize() * statFs.getBlockCount();
    }

    private String b(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(lib.android.paypal.com.magnessdk.c.c, 0);
        String string = sharedPreferences.getString(lib.android.paypal.com.magnessdk.c.c, "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (str == null || str.equals(string)) {
            if (!string.equals("")) {
                return string;
            }
            str = o.a(true);
        }
        edit.putString(lib.android.paypal.com.magnessdk.c.c, str);
        edit.apply();
        return str;
    }

    private String c(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), lib.android.paypal.com.magnessdk.c.f);
    }

    private JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            int b2 = b(3);
            int b3 = b(2);
            int b4 = b(1);
            jSONObject.put(g.d.a.MIN_FREQUENCY.toString(), a(Integer.valueOf(b3)));
            jSONObject.put(g.d.a.MAX_FREQUENCY.toString(), a(Integer.valueOf(b4)));
            jSONObject.put(g.d.a.CORES.toString(), a(Integer.valueOf(b2)));
        } catch (JSONException e) {
            e.a((Class<?>) s.class, 3, e);
        }
        return jSONObject;
    }

    private long d(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
    }

    private JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        long b2 = b(android.os.Environment.getDataDirectory().getPath());
        try {
            jSONObject.put(g.d.b.TOTAL_SD.toString(), a(Long.valueOf(a(601))));
            jSONObject.put(g.d.b.TOTAL_UD.toString(), a(Long.valueOf(b2)));
        } catch (JSONException e) {
            e.a((Class<?>) s.class, 3, e);
        }
        return jSONObject;
    }

    private long e(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            String property = System.getProperty("os.name");
            String property2 = System.getProperty("os.version");
            if (TextUtils.isEmpty(property)) {
                property = "";
            }
            if (TextUtils.isEmpty(property2)) {
                property2 = "";
            }
            String format = String.format("%s %s", property, property2);
            if (!TextUtils.isEmpty(format)) {
                jSONObject.put(g.d.f.VERSION.toString(), a((Object) format.trim()));
            }
            jSONObject.put(g.d.f.BOARD.toString(), a((Object) Build.BOARD));
            jSONObject.put(g.d.f.BOOTLOADER.toString(), a((Object) Build.BOOTLOADER));
            jSONObject.put(g.d.f.CPU_ABI1.toString(), a((Object) Build.SUPPORTED_ABIS[0]));
            jSONObject.put(g.d.f.DISPLAY.toString(), a((Object) Build.DISPLAY));
            jSONObject.put(g.d.f.RADIO.toString(), a((Object) Build.getRadioVersion()));
            jSONObject.put(g.d.f.FINGERPRINT.toString(), a((Object) Build.FINGERPRINT));
            jSONObject.put(g.d.f.HARDWARE.toString(), a((Object) Build.HARDWARE));
            jSONObject.put(g.d.f.MANUFACTURER.toString(), a((Object) Build.MANUFACTURER));
            jSONObject.put(g.d.f.PRODUCT.toString(), a((Object) Build.PRODUCT));
            jSONObject.put(g.d.f.TIME.toString(), a(Long.valueOf(Build.TIME)));
            jSONObject.put(g.d.f.SYSTEM_TYPE.toString(), a((Object) System.getProperty("os.arch")));
        } catch (JSONException e) {
            e.a((Class<?>) s.class, 3, e);
        }
        return jSONObject;
    }

    private long f() throws IllegalArgumentException {
        StatFs statFs = new StatFs(android.os.Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSize() * statFs.getBlockCount();
    }

    private String f(Context context) throws PackageManager.NameNotFoundException {
        return o.a(context);
    }

    private String g(Context context) {
        Uri uri;
        try {
            uri = Uri.parse("content://com.google.android.gsf.gservices");
        } catch (Exception unused) {
            uri = null;
        }
        if (uri == null || !a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES")) {
            return null;
        }
        Cursor query = context.getContentResolver().query(uri, null, null, new String[]{lib.android.paypal.com.magnessdk.c.f}, null);
        if (query == null) {
            return null;
        }
        try {
            if (query.moveToFirst() && query.getColumnCount() >= 2) {
                return Long.toHexString(Long.parseLong(query.getString(1)));
            }
            return null;
        } catch (NumberFormatException e) {
            e.a((Class<?>) s.class, 3, e);
            return null;
        } finally {
            query.close();
        }
    }

    private String h(Context context) {
        WifiInfo connectionInfo = a(context, "android.permission.ACCESS_WIFI_STATE") ? ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo() : null;
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getMacAddress();
    }

    private JSONObject i(Context context) {
        int i;
        float f;
        int i2;
        int i3;
        float f2;
        float f3;
        float f4;
        WindowMetrics currentWindowMetrics;
        Rect bounds;
        JSONObject jSONObject = new JSONObject();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            if (Build.VERSION.SDK_INT >= 30) {
                currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                bounds = currentWindowMetrics.getBounds();
                i2 = bounds.width();
                i = bounds.height();
            } else {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                int i4 = point.x;
                int i5 = point.y;
                i2 = i4;
                i = i5;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            f = displayMetrics.density;
            i3 = displayMetrics.densityDpi;
            f3 = displayMetrics.scaledDensity;
            f4 = displayMetrics.xdpi;
            f2 = displayMetrics.ydpi;
        } else {
            i = 12345;
            f = 12345.0f;
            i2 = 12345;
            i3 = 12345;
            f2 = 12345.0f;
            f3 = 12345.0f;
            f4 = 12345.0f;
        }
        try {
            jSONObject.put(g.d.EnumC0108d.WIDTH.toString(), a(Integer.valueOf(i2)));
            jSONObject.put(g.d.EnumC0108d.HEIGHT.toString(), a(Integer.valueOf(i)));
            jSONObject.put(g.d.EnumC0108d.DENSITY.toString(), a(Float.valueOf(f)));
            jSONObject.put(g.d.EnumC0108d.DENSITY_DPI.toString(), a(Integer.valueOf(i3)));
            jSONObject.put(g.d.EnumC0108d.SCALE.toString(), a(Float.valueOf(f3)));
            jSONObject.put(g.d.EnumC0108d.X_DPI.toString(), a(Float.valueOf(f4)));
            jSONObject.put(g.d.EnumC0108d.Y_DPI.toString(), a(Float.valueOf(f2)));
        } catch (Exception e) {
            e.a((Class<?>) s.class, 3, e);
        }
        return jSONObject;
    }

    private JSONObject j(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dua", a((Object) WebSettings.getDefaultUserAgent(context)));
        } catch (Exception e) {
            e.a((Class<?>) s.class, 3, e);
        }
        return jSONObject;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(g.d.APP_GUID.toString(), this.x1);
            jSONObject.put(g.d.APP_ID.toString(), this.y1);
            jSONObject.put(g.d.ANDROID_ID.toString(), this.D1);
            jSONObject.put(g.d.APP_VERSION.toString(), this.z1);
            String dVar = g.d.APP_FIRST_INSTALL_TIME.toString();
            long j = this.S1;
            Long l = null;
            jSONObject.put(dVar, j == -1 ? null : Long.valueOf(j));
            String dVar2 = g.d.APP_LAST_UPDATE_TIME.toString();
            long j2 = this.T1;
            jSONObject.put(dVar2, j2 == -1 ? null : Long.valueOf(j2));
            jSONObject.put(g.d.CONF_URL.toString(), this.J1);
            jSONObject.put(g.d.COMP_VERSION.toString(), this.K1);
            jSONObject.put(g.d.DEVICE_MODEL.toString(), this.A1);
            jSONObject.put(g.d.DEVICE_NAME.toString(), this.B1);
            jSONObject.put(g.d.GSF_ID.toString(), this.E1);
            jSONObject.put(g.d.IS_EMULATOR.toString(), this.N1);
            jSONObject.put(g.d.EMULATOR_FLAGS.toString(), this.O1);
            jSONObject.put(g.d.IS_ROOTED.toString(), this.P1);
            jSONObject.put(g.d.ROOTED_FLAGS.toString(), this.Q1);
            jSONObject.put(g.d.OS_TYPE.toString(), "Android");
            jSONObject.put(g.d.OS_VERSION.toString(), this.C1);
            jSONObject.put(g.d.PAYLOAD_TYPE.toString(), this.G1);
            jSONObject.put(g.d.SMS_ENABLED.toString(), this.M1);
            jSONObject.put(g.d.MAC_ADDRS.toString(), this.F1);
            jSONObject.put(g.d.MAGNES_GUID.toString(), this.U1);
            String dVar3 = g.d.MAGNES_SOURCE.toString();
            int i = this.L1;
            jSONObject.put(dVar3, i == 0 ? null : Integer.valueOf(i));
            jSONObject.put(g.d.NOTIF_TOKEN.toString(), this.I1);
            jSONObject.put(g.d.SOURCE_APP_VERSION.toString(), this.H1);
            String dVar4 = g.d.TOTAL_STORAGE_SPACE.toString();
            long j3 = this.R1;
            if (j3 != -1) {
                l = Long.valueOf(j3);
            }
            jSONObject.put(dVar4, l);
            jSONObject.put(g.d.NOT_COLLECTIBLE_LIST.toString(), this.Z1);
            jSONObject.put(g.d.SENSOR_METADATA.toString(), this.b2);
            jSONObject.put(g.d.SCREEN.toString(), this.V1);
            jSONObject.put(g.d.CPU.toString(), this.W1);
            jSONObject.put(g.d.DISK.toString(), this.X1);
            jSONObject.put(g.d.SYSTEM.toString(), this.Y1);
            jSONObject.put(g.d.USER_AGENT.toString(), this.a2);
            jSONObject.put(g.d.IN_TREATMENT.toString(), q.j1);
            return jSONObject;
        } catch (JSONException e) {
            e.a((Class<?>) s.class, 3, e);
            return jSONObject;
        }
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar) {
        this.c2 = kVar;
        e.a((Class<?>) s.class, 0, "collecting RiskBlobCoreData");
        a(1, magnesSettings);
        a(2, magnesSettings);
        a(3, magnesSettings);
        a(65, magnesSettings);
        a(66, magnesSettings);
        a(69, magnesSettings);
        a(8, magnesSettings);
        a(9, magnesSettings);
        a(14, magnesSettings);
        a(15, magnesSettings);
        a(70, magnesSettings);
        a(59, magnesSettings);
        a(com.salesforce.marketingcloud.analytics.stats.b.i, magnesSettings);
        a(60, magnesSettings);
        a(100, magnesSettings);
        a(32, magnesSettings);
        a(86, magnesSettings);
        a(62, magnesSettings);
        a(34, magnesSettings);
        a(37, magnesSettings);
        a(38, magnesSettings);
        a(63, magnesSettings);
        a(47, magnesSettings);
        a(52, magnesSettings);
        a(88, magnesSettings);
        q.j1 = false;
        if (a(jVar, magnesSettings.getMagnesSource(), q.k1, q.r1, magnesSettings.getContext())) {
            a(91, magnesSettings);
            a(90, magnesSettings);
            a(93, magnesSettings);
            a(94, magnesSettings);
            a(95, magnesSettings);
            a(101, magnesSettings);
        }
        return a();
    }

    @Override // lib.android.paypal.com.magnessdk.q
    void a(int i, MagnesSettings magnesSettings) {
        try {
            Context context = magnesSettings.getContext();
            switch (i) {
                case 1:
                    this.x1 = b(context, magnesSettings.getAppGuid());
                    break;
                case 2:
                    this.y1 = a(context);
                    break;
                case 3:
                    if (this.c2.a(i)) {
                        this.z1 = f(context);
                        break;
                    }
                    break;
                case 8:
                    this.K1 = lib.android.paypal.com.magnessdk.a.o;
                    break;
                case 9:
                    if (this.c2.a(i)) {
                        this.J1 = g.h.d.REMOTE_CONFIG_URL.toString();
                        break;
                    }
                    break;
                case 14:
                    if (this.c2.a(i)) {
                        this.A1 = Build.MODEL;
                        break;
                    }
                    break;
                case 15:
                    if (this.c2.a(i)) {
                        this.B1 = Build.DEVICE;
                        break;
                    }
                    break;
                case 32:
                    if (this.c2.a(i)) {
                        this.F1 = h(context);
                        break;
                    }
                    break;
                case 34:
                    if (this.c2.a(i)) {
                        this.I1 = magnesSettings.getNotificationToken();
                        break;
                    }
                    break;
                case 37:
                    if (this.c2.a(i)) {
                        this.C1 = Build.VERSION.RELEASE;
                        break;
                    }
                    break;
                case 38:
                    if (this.c2.a(i)) {
                        this.G1 = lib.android.paypal.com.magnessdk.c.g;
                        break;
                    }
                    break;
                case 47:
                    if (this.c2.a(i)) {
                        this.M1 = context.getPackageManager().hasSystemFeature("android.hardware.telephony");
                        break;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (this.c2.a(i)) {
                        this.R1 = f();
                        break;
                    }
                    break;
                case 59:
                    if (this.c2.a(i)) {
                        this.N1 = c.c(context);
                        break;
                    }
                    break;
                case 60:
                    if (this.c2.a(i)) {
                        this.P1 = d.c(context);
                        break;
                    }
                    break;
                case 62:
                    if (this.c2.a(i)) {
                        this.L1 = magnesSettings.getMagnesSource();
                        break;
                    }
                    break;
                case 63:
                    if (this.c2.a(i)) {
                        this.H1 = f(context);
                        break;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                    if (this.c2.a(i)) {
                        this.S1 = d(context);
                        break;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (this.c2.a(i)) {
                        this.T1 = e(context);
                        break;
                    }
                    break;
                case 69:
                    if (this.c2.a(i)) {
                        this.D1 = c(context);
                        break;
                    }
                    break;
                case 70:
                    if (this.c2.a(i)) {
                        this.E1 = g(context);
                        break;
                    }
                    break;
                case ModuleDescriptor.MODULE_VERSION /* 86 */:
                    JSONObject b2 = b(context);
                    this.U1 = b2;
                    q.k1 = b2.optString("id");
                    break;
                case 88:
                    if (this.c2.k()) {
                        this.Z1 = this.c2.i();
                        break;
                    }
                    break;
                case 90:
                    if (this.c2.a(i)) {
                        this.W1 = c();
                        break;
                    }
                    break;
                case 91:
                    if (this.c2.a(i)) {
                        this.V1 = i(context);
                        break;
                    }
                    break;
                case 93:
                    if (this.c2.a(i)) {
                        this.X1 = d();
                        break;
                    }
                    break;
                case 94:
                    if (this.c2.a(i)) {
                        this.Y1 = e();
                        break;
                    }
                    break;
                case 95:
                    if (this.c2.a(i)) {
                        this.a2 = j(context);
                        break;
                    }
                    break;
                case 100:
                    if (this.c2.a(i)) {
                        this.Q1 = d.b(context);
                        break;
                    }
                    break;
                case 101:
                    if (this.c2.a(i)) {
                        this.b2 = a(magnesSettings);
                        break;
                    }
                    break;
                case com.salesforce.marketingcloud.analytics.stats.b.i /* 103 */:
                    if (this.c2.a(i)) {
                        this.O1 = c.b(context);
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
            e.a((Class<?>) s.class, 3, e);
        }
    }

    String b() {
        return this.x1;
    }
}
