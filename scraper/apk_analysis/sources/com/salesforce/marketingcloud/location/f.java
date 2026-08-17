package com.salesforce.marketingcloud.location;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class f extends com.salesforce.marketingcloud.f {
    public static final String d = "NO_GPS_HARDWARE";
    public static final String e = "RECEIVER_NOT_DECLARED_IN_MANIFEST";
    public static final int f = -1;
    protected static final String g = "com.salesforce.marketingcloud.location.LOCATION_UPDATE";
    protected static final String h = "com.salesforce.marketingcloud.location.GEOFENCE_ERROR";
    protected static final String i = "com.salesforce.marketingcloud.location.GEOFENCE_EVENT";
    protected static final String j = "extra_location";
    protected static final String k = "extra_transition";
    protected static final String l = "extra_fence_ids";
    protected static final String m = "extra_error_code";
    protected static final String n = "extra_error_message";
    private static final String o = "LocationManager";
    static final String p = com.salesforce.marketingcloud.g.a(o);

    public abstract void a(c cVar);

    public abstract void a(e eVar);

    public abstract void a(List<String> list);

    public abstract void a(b... bVarArr);

    public boolean a() {
        return false;
    }

    public abstract void b();

    public abstract void b(c cVar);

    public abstract void b(e eVar);

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return o;
    }

    public static Intent a(Location location) {
        return new Intent(g).putExtra(j, location);
    }

    public static Intent a(int i2, List<String> list, Location location) {
        Intent intent = new Intent(i);
        intent.putExtra(k, i2);
        if (list instanceof ArrayList) {
            intent.putStringArrayListExtra(l, (ArrayList) list);
        } else {
            intent.putStringArrayListExtra(l, new ArrayList<>(list));
        }
        if (location != null) {
            intent.putExtra(j, location);
        }
        return intent;
    }

    public static Intent a(int i2, String str) {
        return new Intent(h).putExtra(m, i2).putExtra(n, str);
    }

    public static f a(Context context, MarketingCloudConfig marketingCloudConfig) {
        Exception exc;
        boolean b = com.salesforce.marketingcloud.util.b.b();
        Boolean bool = null;
        Exception exc2 = null;
        if (b) {
            boolean a = LocationReceiver.a(context);
            Boolean valueOf = Boolean.valueOf(a);
            if (a) {
                try {
                    return new h(context, marketingCloudConfig);
                } catch (Exception e2) {
                    exc2 = e2;
                    com.salesforce.marketingcloud.g.b(p, exc2, "Unable to create real instance of %s", o);
                }
            }
            exc = exc2;
            bool = valueOf;
        } else {
            com.salesforce.marketingcloud.g.e(p, "GooglePlayServices Location dependency missing from build.", new Object[0]);
            exc = null;
        }
        return new a(marketingCloudConfig, bool, b, exc);
    }

    static JSONObject a(MarketingCloudConfig marketingCloudConfig, Boolean bool, boolean z, Exception exc) {
        JSONObject a = a(marketingCloudConfig);
        try {
            a.put("serviceAvailable", bool);
            a.put("gmsLocationDependencyAvailable", z);
            if (exc != null) {
                a.put("exceptionMessage", exc.getMessage());
            }
        } catch (JSONException e2) {
            com.salesforce.marketingcloud.g.b(p, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return a;
    }

    static JSONObject a(MarketingCloudConfig marketingCloudConfig, int i2, String str) {
        JSONObject a = a(marketingCloudConfig);
        try {
            a.put("apiCode", i2);
            a.put("apiMessage", str);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(p, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return a;
    }

    private static JSONObject a(MarketingCloudConfig marketingCloudConfig) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("geofencingEnabled", marketingCloudConfig.geofencingEnabled());
            jSONObject.put("proximityEnabled", marketingCloudConfig.proximityEnabled());
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(p, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return jSONObject;
    }
}
