package lib.android.paypal.com.magnessdk;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellIdentityNr;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import androidx.compose.material.MenuKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.auth.AuthBrowserView;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView;
import com.tealium.library.DataSources;
import java.io.File;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class t extends q {
    private static final int S2 = 255;
    private static final int T2 = 256;
    private static final int U2 = 16;
    private static final int V2 = 32;
    private static final int W2 = 32;
    private static final String X2 = "invalid_input";
    private static final String Y2 = "SG1hY1NIQTI1Ng==";
    private static final String Z2 = "RiskManagerCT";
    private static final String a3 = "fb.bin";
    private static final String b3 = "00:00:00:00:00:00";
    private static final String c3 = "external";
    private TelephonyManager B2;
    private WifiManager C2;
    private String D1;
    private ConnectivityManager D2;
    private String E1;
    private BatteryManager E2;
    private String F1;
    private LocationManager F2;
    private String G1;
    private PowerManager G2;
    private String H1;
    private PackageManager H2;
    private String I1;
    private Location I2;
    private String J1;
    private JSONObject J2;
    private String K1;
    private JSONObject K2;
    private String L1;
    private JSONObject L2;
    private String M1;
    private JSONObject M2;
    private String N1;
    private JSONObject N2;
    private String O1;
    private Handler O2;
    private String P1;
    private boolean P2;
    private String Q1;
    private k Q2;
    private String R1;
    private String S1;
    private String T1;
    private String U1;
    private String V1;
    private String W1;
    private String X1;
    private String Y1;
    private String Z1;
    private String a2;
    private List<String> b2;
    private List<String> c2;
    private List<String> d2;
    private boolean g2;
    private boolean h2;
    private boolean i2;
    private boolean j2;
    private boolean k2;
    private boolean l2;
    private boolean m2;
    private boolean n2;
    private boolean o2;
    private boolean p2;
    private boolean q2;
    private boolean r2;
    private boolean s2;
    private Map<String, String> t2;
    private NetworkInfo u2;
    private WifiInfo v2;
    private int x1 = -1;
    private long y1 = -1;
    private int z1 = -1;
    private int A1 = -1;
    private int B1 = -1;
    private int C1 = -1;
    private long e2 = -1;
    private long f2 = -1;
    private long w2 = -1;
    private int x2 = -1;
    private int y2 = -1;
    private int z2 = -1;
    private int A2 = -1;
    private u R2 = u.b();

    t(boolean z) {
        this.P2 = z;
    }

    private Location a(LocationManager locationManager) {
        Location location = null;
        if (locationManager == null) {
            return null;
        }
        try {
            List<String> providers = locationManager.getProviders(true);
            for (int size = providers.size() - 1; size >= 0; size--) {
                location = locationManager.getLastKnownLocation(providers.get(size));
                if (location != null) {
                    break;
                }
            }
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return location;
    }

    private String a(TelephonyManager telephonyManager) {
        try {
            return telephonyManager.getSimOperatorName();
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
            return null;
        }
    }

    private String a(String str, String str2, long j, String str3) throws Exception {
        StringBuilder sb;
        String sb2;
        if (o.b((Object) str) && o.b((Object) str2) && o.b(Long.valueOf(j))) {
            sb2 = X2;
        } else {
            if (o.b((Object) str)) {
                str = "";
            }
            if (o.b((Object) str2)) {
                str2 = "";
            }
            if (o.b(Long.valueOf(j))) {
                sb = new StringBuilder();
                sb.append(str);
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(j);
            }
            sb.append(str2);
            sb2 = sb.toString();
        }
        String a = o.a(Y2);
        String a2 = o.b(Long.valueOf(j)) ? o.a(str3) : o.a(str3) + j;
        Mac mac = Mac.getInstance(a);
        mac.init(new SecretKeySpec(a2.getBytes(), a));
        byte[] doFinal = mac.doFinal(sb2.getBytes());
        StringBuilder sb3 = new StringBuilder();
        for (byte b : doFinal) {
            sb3.append(Integer.toString((b & UByte.MAX_VALUE) + 256, 16).substring(1));
        }
        return sb3.toString().substring(0, 32);
    }

    private ArrayList<String> a(WifiManager wifiManager) {
        String bssid;
        int i;
        if (wifiManager == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (scanResults == null || scanResults.size() == 0 || (bssid = wifiManager.getConnectionInfo().getBSSID()) == null || bssid.equals(b3)) {
            return null;
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < scanResults.size(); i4++) {
            if (!bssid.equals(scanResults.get(i4).BSSID) && i2 < (i = scanResults.get(i4).level)) {
                i3 = i4;
                i2 = i;
            }
        }
        arrayList.add(bssid);
        if (i3 != -1) {
            arrayList.add(scanResults.get(i3).BSSID);
        }
        return arrayList;
    }

    private List<String> a(boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses != null && inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (z || !nextElement.isLinkLocalAddress())) {
                        String hostAddress = nextElement.getHostAddress();
                        if (nextElement instanceof Inet6Address) {
                            arrayList3.add(hostAddress);
                        } else {
                            arrayList2.add(hostAddress);
                        }
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList.addAll(arrayList2);
            }
            if (!arrayList3.isEmpty()) {
                arrayList.addAll(arrayList3);
            }
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private JSONObject a(Context context, BatteryManager batteryManager, PowerManager powerManager) {
        int i;
        int i2;
        int i3;
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        JSONObject jSONObject = new JSONObject();
        int i4 = 12345;
        double d = 12345.0d;
        if (registerReceiver != null) {
            double intExtra = registerReceiver.getIntExtra("level", 12345);
            int intExtra2 = registerReceiver.getIntExtra("scale", 12345);
            i2 = registerReceiver.getIntExtra("temperature", 12345);
            int intExtra3 = registerReceiver.getIntExtra("voltage", 12345);
            i3 = registerReceiver.getIntExtra("status", 12345);
            i = registerReceiver.getIntExtra("plugged", 12345);
            d = (intExtra == 12345.0d || intExtra2 == 12345) ? intExtra : intExtra / intExtra2;
            i4 = intExtra3;
        } else {
            i = 12345;
            i2 = 12345;
            i3 = 12345;
        }
        int intProperty = batteryManager.getIntProperty(2);
        boolean isPowerSaveMode = powerManager.isPowerSaveMode();
        try {
            jSONObject.put(g.e.a.CURRENT.toString(), a(Integer.valueOf(intProperty)));
            jSONObject.put(g.e.a.LEVEL.toString(), new DecimalFormat(".##").format(a(Double.valueOf(d))));
            jSONObject.put(g.e.a.METHOD.toString(), a(Integer.valueOf(i)));
            jSONObject.put(g.e.a.LOW_POWER.toString(), a(Integer.valueOf(isPowerSaveMode ? 1 : 0)));
            jSONObject.put(g.e.a.STATE.toString(), a(Integer.valueOf(i3)));
            jSONObject.put(g.e.a.TEMP.toString(), a(Integer.valueOf(i2)));
            jSONObject.put(g.e.a.VOLTAGE.toString(), a(Integer.valueOf(i4)));
        } catch (JSONException e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return jSONObject;
    }

    private JSONObject a(Location location) {
        if (location != null) {
            try {
                return new JSONObject("{\"lat\":" + location.getLatitude() + ",\"lng\":" + location.getLongitude() + ",\"acc\":" + location.getAccuracy() + ",\"timestamp\":" + location.getTime() + "}");
            } catch (Exception e) {
                e.a((Class<?>) t.class, 3, e);
            }
        }
        return null;
    }

    private String b(String str) throws Exception {
        if (str == null || str.isEmpty()) {
            str = "invalid input in dc method";
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toString((b & UByte.MAX_VALUE) + 256, 16).substring(1));
        }
        return sb.toString().substring(0, 32);
    }

    private List<String> b() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String str : this.Q2.e()) {
                if (p.a(this.H2, new Intent().setComponent(ComponentName.unflattenFromString(str)))) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return arrayList;
    }

    private void b(TelephonyManager telephonyManager) {
        String str;
        List<CellInfo> allCellInfo;
        if (telephonyManager == null) {
            return;
        }
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType != 0) {
            try {
                if (phoneType == 1) {
                    this.R1 = "gsm";
                    allCellInfo = h() ? telephonyManager.getAllCellInfo() : null;
                    if (allCellInfo == null || allCellInfo.size() == 0) {
                        return;
                    }
                    for (CellInfo cellInfo : allCellInfo) {
                        if (cellInfo instanceof CellInfoGsm) {
                            this.x2 = ((CellInfoGsm) cellInfo).getCellIdentity().getLac();
                            this.w2 = ((CellInfoGsm) cellInfo).getCellIdentity().getCid();
                        }
                        if (cellInfo instanceof CellInfoLte) {
                            this.x2 = ((CellInfoLte) cellInfo).getCellIdentity().getTac();
                            this.w2 = ((CellInfoLte) cellInfo).getCellIdentity().getCi();
                        }
                        if (cellInfo instanceof CellInfoWcdma) {
                            this.x2 = ((CellInfoWcdma) cellInfo).getCellIdentity().getLac();
                            this.w2 = ((CellInfoWcdma) cellInfo).getCellIdentity().getCid();
                        }
                        if (cellInfo instanceof CellInfoTdscdma) {
                            this.x2 = ((CellInfoTdscdma) cellInfo).getCellIdentity().getLac();
                            this.w2 = ((CellInfoTdscdma) cellInfo).getCellIdentity().getCid();
                        }
                        if (cellInfo instanceof CellInfoNr) {
                            CellIdentityNr cellIdentityNr = (CellIdentityNr) ((CellInfoNr) cellInfo).getCellIdentity();
                            this.x2 = cellIdentityNr.getTac();
                            this.w2 = cellIdentityNr.getNci();
                        }
                    }
                    return;
                }
                if (phoneType == 2) {
                    this.R1 = "cdma";
                    allCellInfo = h() ? telephonyManager.getAllCellInfo() : null;
                    if (allCellInfo == null || allCellInfo.size() == 0) {
                        return;
                    }
                    for (CellInfo cellInfo2 : allCellInfo) {
                        if (cellInfo2 instanceof CellInfoCdma) {
                            this.y2 = ((CellInfoCdma) cellInfo2).getCellIdentity().getBasestationId();
                            this.A2 = ((CellInfoCdma) cellInfo2).getCellIdentity().getNetworkId();
                            this.z2 = ((CellInfoCdma) cellInfo2).getCellIdentity().getSystemId();
                        }
                    }
                    return;
                }
                str = "unknown (" + telephonyManager.getPhoneType() + ")";
            } catch (Exception e) {
                e.a((Class<?>) t.class, 3, e);
                return;
            }
        } else {
            str = "none";
        }
        this.R1 = str;
    }

    private void b(JSONObject jSONObject) {
        Map<String, String> map = this.t2;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    jSONObject.put(entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    e.a((Class<?>) t.class, 3, e);
                }
            }
        }
    }

    private long c(String str) {
        StatFs statFs = new StatFs(str);
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    private String c() {
        g.b.a aVar;
        ConnectivityManager connectivityManager = this.D2;
        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities = this.r2 ? connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()) : null;
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(0)) {
                    aVar = g.b.a.MOBILE;
                } else if (networkCapabilities.hasTransport(1)) {
                    aVar = g.b.a.WIFI;
                } else if (networkCapabilities.hasTransport(3)) {
                    aVar = g.b.a.ETHERNET;
                } else if (networkCapabilities.hasTransport(4)) {
                    aVar = g.b.a.VPN;
                } else if (networkCapabilities.hasTransport(2)) {
                    aVar = g.b.a.BLUETOOTH;
                } else if (networkCapabilities.hasTransport(5)) {
                    aVar = g.b.a.WIFI_AWARE;
                } else if (networkCapabilities.hasTransport(6)) {
                    aVar = g.b.a.LOWPAN;
                }
                return aVar.toString();
            }
        }
        return null;
    }

    private String c(Context context) {
        return context.getSharedPreferences(Z2, 0).getInt(Z2, 0) + "";
    }

    private String d() {
        List<String> a = a(false);
        if (a == null) {
            return null;
        }
        return a.get(0);
    }

    private JSONObject d(Context context) {
        File file;
        JSONObject jSONObject = new JSONObject();
        boolean equals = android.os.Environment.getExternalStorageState().equals("mounted");
        File[] externalFilesDirs = context.getExternalFilesDirs("");
        boolean z = false;
        if (externalFilesDirs != null && externalFilesDirs.length > 1 && externalFilesDirs[0] != null && (file = externalFilesDirs[1]) != null && equals && android.os.Environment.isExternalStorageRemovable(file)) {
            z = true;
        }
        long c = c(android.os.Environment.getDataDirectory().getPath());
        long a = super.a(600);
        try {
            jSONObject.put(g.e.b.MOUNTED.toString(), z);
            jSONObject.put(g.e.b.FREE_SD.toString(), a(Long.valueOf(a)));
            jSONObject.put(g.e.b.FREE_UD.toString(), a(Long.valueOf(c)));
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return jSONObject;
    }

    private String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.j2 ? 1 : 0);
        sb.append(this.l2 ? 1 : 0);
        sb.append(this.q2 ? 1 : 0);
        sb.append(this.r2 ? 1 : 0);
        sb.append(this.m2 ? 1 : 0);
        sb.append(this.n2 ? 1 : 0);
        return sb.toString();
    }

    private boolean e(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
    }

    private String f() {
        String property;
        String property2 = System.getProperty("http.proxyHost");
        if (property2 == null || (property = System.getProperty("http.proxyPort")) == null) {
            return null;
        }
        return "host=" + property2 + ",port=" + property;
    }

    private JSONObject f(Context context) {
        long j;
        long j2;
        JSONObject jSONObject = new JSONObject();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE);
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long j3 = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            j = memoryInfo.availMem;
            j2 = memoryInfo.totalMem;
        } else {
            j = 12345;
            j2 = 12345;
        }
        try {
            jSONObject.put(g.e.c.FREE.toString(), a(Long.valueOf(j)));
            jSONObject.put(g.e.c.TOTAL.toString(), a(Long.valueOf(j2)));
            jSONObject.put(g.e.c.FREE_RUNTIME.toString(), a(Long.valueOf(freeMemory)));
            jSONObject.put(g.e.c.TOTAL_RUNTIME.toString(), a(Long.valueOf(j3)));
            jSONObject.put(g.e.c.MAX_RUNTIME.toString(), a(Long.valueOf(maxMemory)));
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return jSONObject;
    }

    private String g() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp() && !networkInterface.getInterfaceAddresses().isEmpty()) {
                    String name = networkInterface.getName();
                    String[] strArr = {"ppp", "tun", "tap", "ipsec", "utun"};
                    for (int i = 0; i < 5; i++) {
                        if (name.toLowerCase().startsWith(strArr[i])) {
                            return name;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
            return null;
        }
    }

    private JSONObject g(Context context) {
        int i;
        JSONObject jSONObject = new JSONObject();
        try {
            i = Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.a((Class<?>) t.class, 3, e);
            i = q.d;
        }
        try {
            jSONObject.put("brightness", a(Integer.valueOf(i)));
        } catch (JSONException e2) {
            e.a((Class<?>) t.class, 3, e2);
        }
        return jSONObject;
    }

    private JSONObject h(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = false;
            jSONObject.put(g.l.AC.toString(), (sensorManager == null || sensorManager.getDefaultSensor(1) == null) ? false : true);
            jSONObject.put(g.l.GY.toString(), (sensorManager == null || sensorManager.getDefaultSensor(4) == null) ? false : true);
            String lVar = g.l.MG.toString();
            if (sensorManager != null && sensorManager.getDefaultSensor(2) != null) {
                z = true;
            }
            jSONObject.put(lVar, z);
        } catch (JSONException e) {
            e.a((Class<?>) t.class, 3, e);
        }
        return jSONObject;
    }

    private boolean h() {
        return this.o2 && this.k2;
    }

    private void i(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Z2, 0);
        int i = sharedPreferences.getInt(Z2, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i2 = 1;
        if (i > 0 && i < Integer.MAX_VALUE) {
            i2 = 1 + i;
        }
        edit.putInt(Z2, i2);
        edit.apply();
    }

    private boolean i() {
        return this.o2 && this.j2;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(g.b.a, this.F1);
            String eVar = g.e.BASE_STATION_ID.toString();
            int i = this.x1;
            Integer num = null;
            jSONObject.put(eVar, i == -1 ? null : Integer.valueOf(i));
            jSONObject.put(g.e.BSSID.toString(), this.D1);
            jSONObject.put(g.e.BSSID_ARRAY.toString(), this.d2 == null ? null : new JSONArray((Collection) this.d2));
            String eVar2 = g.e.CELL_ID.toString();
            long j = this.y1;
            jSONObject.put(eVar2, j == -1 ? null : Long.valueOf(j));
            jSONObject.put(g.e.CONN_TYPE.toString(), this.L1);
            jSONObject.put(g.e.CONF_VERSION.toString(), this.W1);
            jSONObject.put(g.e.IS_DEV_MODE_ON.toString(), this.s2);
            jSONObject.put(g.e.DEVICE_ID.toString(), this.M1);
            jSONObject.put(g.e.DC_ID.toString(), this.K1);
            String eVar3 = g.e.DEVICE_UPTIME.toString();
            long j2 = this.f2;
            jSONObject.put(eVar3, j2 == -1 ? null : Long.valueOf(j2));
            jSONObject.put(g.e.IP_ADDRS.toString(), this.N1);
            jSONObject.put(g.e.IP_ADDRESSES.toString(), this.b2 == null ? null : new JSONArray((Collection) this.b2));
            jSONObject.put(g.e.KNOWN_APPS.toString(), this.c2 == null ? null : new JSONArray((Collection) this.c2));
            jSONObject.put(g.e.LOCALE_COUNTRY.toString(), this.P1);
            jSONObject.put(g.e.LOCALE_LANG.toString(), this.Q1);
            jSONObject.put(g.e.LOCATION.toString(), a(this.I2));
            String eVar4 = g.e.LOCATION_AREA_CODE.toString();
            int i2 = this.C1;
            jSONObject.put(eVar4, i2 == -1 ? null : Integer.valueOf(i2));
            jSONObject.put(g.e.PHONE_TYPE.toString(), this.R1);
            jSONObject.put(g.e.RISK_COMP_SESSION_ID.toString(), this.S1);
            jSONObject.put(g.e.ROAMING.toString(), this.g2);
            jSONObject.put(g.e.SIM_OPERATOR_NAME.toString(), this.Y1);
            jSONObject.put(g.e.SIM_SERIAL_NUMBER.toString(), this.T1);
            jSONObject.put(g.e.SSID.toString(), this.U1);
            String eVar5 = g.e.CDMA_NETWORK_ID.toString();
            int i3 = this.B1;
            jSONObject.put(eVar5, i3 == -1 ? null : Integer.valueOf(i3));
            String eVar6 = g.e.CDMA_SYSTEM_ID.toString();
            int i4 = this.A1;
            jSONObject.put(eVar6, i4 == -1 ? null : Integer.valueOf(i4));
            jSONObject.put(g.e.SUBSCRIBER_ID.toString(), this.V1);
            String eVar7 = g.e.TIMESTAMP.toString();
            long j3 = this.e2;
            jSONObject.put(eVar7, j3 == -1 ? null : Long.valueOf(j3));
            jSONObject.put(g.e.TZ_NAME.toString(), this.O1);
            jSONObject.put(g.e.DS.toString(), this.h2);
            String eVar8 = g.e.TZ.toString();
            int i5 = this.z1;
            if (i5 != -1) {
                num = Integer.valueOf(i5);
            }
            jSONObject.put(eVar8, num);
            jSONObject.put(g.e.NETWORK_OPERATOR.toString(), this.E1);
            jSONObject.put(g.e.SERIAL_NUMBER.toString(), this.G1);
            jSONObject.put(g.e.VPN_SETTING.toString(), this.I1);
            jSONObject.put(g.e.PROXY_SETTING.toString(), this.H1);
            jSONObject.put(g.e.C.toString(), this.J1);
            jSONObject.put(g.e.MG_ID.toString(), this.X1);
            jSONObject.put(g.e.PL.toString(), this.Z1);
            jSONObject.put(g.e.BATTERY.toString(), this.J2);
            jSONObject.put(g.e.MEMORY.toString(), this.K2);
            jSONObject.put(g.e.DISK.toString(), this.L2);
            jSONObject.put(g.e.SCREEN.toString(), this.M2);
            jSONObject.put(g.e.SR.toString(), this.N2);
            jSONObject.put(g.e.T.toString(), q.j1);
            b(jSONObject);
            return jSONObject;
        } catch (Exception e) {
            e.a((Class<?>) t.class, 3, e);
            return jSONObject;
        }
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar) {
        return a(magnesSettings, jVar, kVar, this.a2, null, null, this.O2);
    }

    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar, String str, String str2, HashMap<String, String> hashMap, Handler handler) {
        TelephonyManager telephonyManager;
        e.a((Class<?>) t.class, 0, "collecting RiskBlobDynamicData");
        this.Q2 = kVar;
        Context context = magnesSettings.getContext();
        this.o2 = magnesSettings.hasUserLocationConsent();
        this.B2 = (TelephonyManager) context.getSystemService("phone");
        this.C2 = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.F2 = (LocationManager) context.getSystemService("location");
        this.D2 = (ConnectivityManager) context.getSystemService("connectivity");
        this.E2 = (BatteryManager) context.getSystemService("batterymanager");
        this.G2 = (PowerManager) context.getSystemService("power");
        this.H2 = context.getPackageManager();
        boolean a = a(context, "android.permission.ACCESS_FINE_LOCATION");
        this.k2 = a;
        boolean z = true;
        this.j2 = a || a(context, "android.permission.ACCESS_COARSE_LOCATION");
        this.m2 = a(context, "android.permission.READ_EXTERNAL_STORAGE");
        this.n2 = a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        this.l2 = a(context, "android.permission.READ_PHONE_STATE");
        this.r2 = a(context, "android.permission.ACCESS_NETWORK_STATE");
        this.q2 = a(context, "android.permission.ACCESS_WIFI_STATE");
        this.t2 = hashMap;
        this.e2 = System.currentTimeMillis();
        this.W1 = kVar.f();
        this.F1 = str2;
        this.a2 = str;
        this.O2 = handler;
        if (str2 == null) {
            this.F1 = o.a(false);
        }
        b(this.B2);
        WifiManager wifiManager = this.C2;
        if (wifiManager != null) {
            this.v2 = this.q2 ? wifiManager.getConnectionInfo() : null;
        }
        ConnectivityManager connectivityManager = this.D2;
        if (connectivityManager != null) {
            this.u2 = this.r2 ? connectivityManager.getActiveNetworkInfo() : null;
        }
        if (!a(context, "android.permission.READ_PRIVILEGED_PHONE_STATE") && ((telephonyManager = this.B2) == null || !telephonyManager.hasCarrierPrivileges())) {
            z = false;
        }
        this.i2 = z;
        this.R2.a(kVar, handler, magnesSettings);
        a(82, magnesSettings);
        a(81, magnesSettings);
        a(16, magnesSettings);
        a(21, magnesSettings);
        a(75, magnesSettings);
        a(23, magnesSettings);
        a(27, magnesSettings);
        a(28, magnesSettings);
        a(56, magnesSettings);
        a(72, magnesSettings);
        a(42, magnesSettings);
        a(43, magnesSettings);
        a(45, magnesSettings);
        a(53, magnesSettings);
        a(80, magnesSettings);
        a(71, magnesSettings);
        a(4, magnesSettings);
        a(57, magnesSettings);
        a(58, magnesSettings);
        a(6, magnesSettings);
        a(30, magnesSettings);
        a(29, magnesSettings);
        a(13, magnesSettings);
        a(68, magnesSettings);
        a(49, magnesSettings);
        a(84, magnesSettings);
        a(5, magnesSettings);
        a(48, magnesSettings);
        a(11, magnesSettings);
        a(85, magnesSettings);
        a(46, magnesSettings);
        a(79, magnesSettings);
        a(87, magnesSettings);
        a(98, magnesSettings);
        a(99, magnesSettings);
        q.j1 = false;
        if (this.P2 && a(jVar, magnesSettings.getMagnesSource(), q.k1, "s", magnesSettings.getContext())) {
            this.R2.a(magnesSettings, this.F1, this.N2);
        }
        if (a(jVar, magnesSettings.getMagnesSource(), q.k1, q.r1, magnesSettings.getContext())) {
            a(89, magnesSettings);
            a(92, magnesSettings);
            a(93, magnesSettings);
            a(91, magnesSettings);
        }
        e.a((Class<?>) t.class, 0, "finishing RiskBlobDynamicData");
        return a();
    }

    @Override // lib.android.paypal.com.magnessdk.q
    void a(int i, MagnesSettings magnesSettings) {
        Context context;
        WifiInfo wifiInfo;
        TelephonyManager telephonyManager;
        WifiInfo wifiInfo2;
        TelephonyManager telephonyManager2;
        TelephonyManager telephonyManager3;
        String meid;
        try {
            context = magnesSettings.getContext();
        } catch (AssertionError | Exception e) {
            e.a((Class<?>) t.class, 3, e);
        }
        if (i == 4) {
            if (this.Q2.a(i)) {
                this.x1 = this.y2;
                return;
            }
            return;
        }
        String str = null;
        String networkOperator = null;
        List<String> list = null;
        r1 = null;
        String str2 = null;
        String a = null;
        str = null;
        if (i == 5) {
            if (this.Q2.a(i)) {
                if (i() && (wifiInfo = this.v2) != null) {
                    str = wifiInfo.getBSSID();
                }
                this.D1 = str;
                return;
            }
            return;
        }
        if (i == 6) {
            if (this.Q2.a(i)) {
                this.y1 = this.w2;
                return;
            }
            return;
        }
        if (i == 42) {
            if (this.Q2.a(i)) {
                this.S1 = o.a(true);
                return;
            }
            return;
        }
        if (i == 43) {
            if (this.Q2.a(i)) {
                this.g2 = new ServiceState().getRoaming();
                return;
            }
            return;
        }
        if (i == 45) {
            if (this.Q2.a(i)) {
                TelephonyManager telephonyManager4 = this.B2;
                if (telephonyManager4 != null) {
                    a = a(telephonyManager4);
                }
                this.Y1 = a;
                return;
            }
            return;
        }
        if (i == 46) {
            if (this.Q2.a(i) && this.l2 && (telephonyManager = this.B2) != null && this.i2) {
                this.T1 = telephonyManager.getSimSerialNumber();
                return;
            }
            return;
        }
        if (i == 48) {
            if (this.Q2.a(i)) {
                if (i() && (wifiInfo2 = this.v2) != null) {
                    str2 = wifiInfo2.getSSID();
                }
                this.U1 = str2;
                return;
            }
            return;
        }
        if (i == 49) {
            if (this.Q2.a(i) && this.l2 && (telephonyManager2 = this.B2) != null && this.i2) {
                this.V1 = telephonyManager2.getSubscriberId();
                return;
            }
            return;
        }
        if (i == 71) {
            if (this.Q2.a(i)) {
                this.I1 = g();
                return;
            }
            return;
        }
        if (i == 72) {
            if (this.Q2.a(i)) {
                this.H1 = f();
                return;
            }
            return;
        }
        if (i == 84) {
            if (this.Q2.a(i)) {
                this.d2 = h() ? a(this.C2) : null;
                return;
            }
            return;
        }
        if (i == 85) {
            this.X1 = a(this.a2, this.F1, this.e2, this.Q2.h());
            return;
        }
        switch (i) {
            case 11:
                if (this.Q2.a(i)) {
                    this.L1 = c();
                    break;
                }
                break;
            case 13:
                if (this.Q2.a(i) && this.l2 && (telephonyManager3 = this.B2) != null && this.i2) {
                    if (telephonyManager3.getPhoneType() == 1) {
                        meid = this.B2.getImei();
                    } else if (this.B2.getPhoneType() == 2) {
                        meid = this.B2.getMeid();
                    }
                    this.M1 = meid;
                    break;
                }
                break;
            case 16:
                if (this.Q2.a(i)) {
                    this.f2 = SystemClock.uptimeMillis();
                    break;
                }
                break;
            case 21:
                if (this.Q2.a(i)) {
                    this.N1 = d();
                    break;
                }
                break;
            case 23:
                if (this.Q2.a(i)) {
                    List<String> b = b();
                    if (!b.isEmpty()) {
                        list = b;
                    }
                    this.c2 = list;
                    break;
                }
                break;
            case 27:
                if (this.Q2.a(i)) {
                    this.P1 = Locale.getDefault().getCountry();
                    break;
                }
                break;
            case 28:
                if (this.Q2.a(i)) {
                    this.Q1 = Locale.getDefault().getLanguage();
                    break;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                if (this.Q2.a(i)) {
                    this.I2 = i() ? a(this.F2) : null;
                    break;
                }
                break;
            case 30:
                if (this.Q2.a(i)) {
                    this.C1 = this.x2;
                    break;
                }
                break;
            case 53:
                if (this.Q2.a(i)) {
                    this.O1 = TimeZone.getDefault().getDisplayName(TimeZone.getDefault().inDaylightTime(new Date()), 1, Locale.ENGLISH);
                    break;
                }
                break;
            case 68:
                if (this.Q2.a(i) && this.l2 && this.B2 != null && this.i2) {
                    this.G1 = Build.getSerial();
                    break;
                }
                break;
            case MenuKt.OutTransitionDuration /* 75 */:
                if (this.Q2.a(i)) {
                    this.b2 = a(true);
                    break;
                }
                break;
            case 87:
                this.Z1 = e();
                break;
            case 89:
                if (this.Q2.a(i)) {
                    this.J2 = a(context, this.E2, this.G2);
                    break;
                }
                break;
            case 98:
                if (this.Q2.a(i)) {
                    this.N2 = h(context);
                    break;
                }
                break;
            case 99:
                if (this.Q2.a(i)) {
                    this.s2 = e(context);
                    break;
                }
                break;
            default:
                switch (i) {
                    case AuthBrowserView.TOOLBAR_HEIGHT_DP /* 56 */:
                        if (this.Q2.a(i)) {
                            TelephonyManager telephonyManager5 = this.B2;
                            if (telephonyManager5 != null) {
                                networkOperator = telephonyManager5.getNetworkOperator();
                            }
                            this.E1 = networkOperator;
                            break;
                        }
                        break;
                    case 57:
                        if (this.Q2.a(i)) {
                            this.B1 = this.A2;
                            break;
                        }
                        break;
                    case 58:
                        if (this.Q2.a(i)) {
                            this.A1 = this.z2;
                            break;
                        }
                        break;
                    default:
                        switch (i) {
                            case 79:
                                if (this.Q2.a(i)) {
                                    this.h2 = TimeZone.getDefault().inDaylightTime(new Date());
                                    break;
                                }
                                break;
                            case BinsView.LABEL_WIDTH_DP /* 80 */:
                                if (this.Q2.a(i)) {
                                    this.z1 = TimeZone.getDefault().getOffset(new Date().getTime());
                                    break;
                                }
                                break;
                            case 81:
                                this.K1 = b(this.a2 + this.e2);
                                break;
                            case 82:
                                if (this.Q2.a(i) && MagnesSDK.getInstance().b.getMagnesSource() == MagnesSource.PAYPAL.getVersion()) {
                                    i(context);
                                    this.J1 = c(context);
                                    break;
                                }
                                break;
                            default:
                                switch (i) {
                                    case 91:
                                        if (this.Q2.a(i)) {
                                            this.M2 = g(context);
                                            break;
                                        }
                                        break;
                                    case 92:
                                        if (this.Q2.a(i)) {
                                            this.K2 = f(context);
                                            break;
                                        }
                                        break;
                                    case 93:
                                        if (this.Q2.a(i)) {
                                            this.L2 = d(context);
                                            break;
                                        }
                                        break;
                                }
                        }
                }
        }
    }
}
