package com.tealium.internal.data;

import com.tealium.internal.c;
import com.tealium.library.DataSources;
import com.tealium.library.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class BulkDispatch {
    private static final List<String> g = new a();
    private final c a;
    private final JSONObject b;
    private final JSONObject c;
    private final JSONArray d;
    private final List<Dispatch> e;
    private boolean f;

    class a extends ArrayList<String> {
        a() {
            add(DataSources.Key.TEALIUM_ACCOUNT);
            add(DataSources.Key.TEALIUM_PROFILE);
            add(DataSources.Key.TEALIUM_DATASOURCE_ID);
            add(DataSources.Key.TEALIUM_VID);
            add(DataSources.Key.TEALIUM_VISITOR_ID);
            add(DataSources.Key.TEALIUM_LIBRARY_NAME);
            add("device");
            add(DataSources.Key.DEVICE_ARCHITECTURE);
            add(DataSources.Key.DEVICE_CPUTYPE);
            add(DataSources.Key.DEVICE_LANGUAGE);
            add(DataSources.Key.DEVICE_RESOLUTION);
            add("uuid");
        }
    }

    public BulkDispatch(c cVar, List<Dispatch> list) {
        this(cVar, list, true);
    }

    private void a() {
        try {
            if (this.e.get(0).containsKey(DataSources.Key.TEALIUM_ACCOUNT)) {
                this.c.put(DataSources.Key.TEALIUM_ACCOUNT, this.e.get(0).get(DataSources.Key.TEALIUM_ACCOUNT));
            } else {
                this.a.c(R.string.bulk_dispatch_warning_known_keys_missing, DataSources.Key.TEALIUM_ACCOUNT);
            }
            if (this.e.get(0).containsKey(DataSources.Key.TEALIUM_PROFILE)) {
                this.c.put(DataSources.Key.TEALIUM_PROFILE, this.e.get(0).get(DataSources.Key.TEALIUM_PROFILE));
            } else {
                this.a.c(R.string.bulk_dispatch_warning_known_keys_missing, DataSources.Key.TEALIUM_PROFILE);
            }
            if (this.e.get(0).containsKey(DataSources.Key.TEALIUM_VISITOR_ID)) {
                this.c.put(DataSources.Key.TEALIUM_VISITOR_ID, this.e.get(0).get(DataSources.Key.TEALIUM_VISITOR_ID));
            } else {
                this.a.c(R.string.bulk_dispatch_warning_known_keys_missing, DataSources.Key.TEALIUM_VISITOR_ID);
            }
            Iterator<Dispatch> it = this.e.iterator();
            while (it.hasNext()) {
                this.d.put(it.next().toJsonObject());
            }
        } catch (JSONException e) {
            this.a.a(R.string.bulk_dispatch_error_object_initialize, e, new Object[0]);
        }
    }

    public void compress() {
        if (this.f) {
            return;
        }
        try {
            removeKnownSharedKeys();
            if (this.d.length() > 1) {
                JSONObject jSONObject = this.d.getJSONObject(0);
                for (String str : a(jSONObject)) {
                    int i = 1;
                    while (true) {
                        if (i >= this.d.length()) {
                            this.c.put(str, jSONObject.get(str));
                            a(str);
                            break;
                        } else if (this.d.getJSONObject(i).has(str) && jSONObject.get(str).equals(this.d.getJSONObject(i).get(str))) {
                            i++;
                        }
                    }
                }
            }
            this.f = true;
        } catch (JSONException unused) {
            a();
        }
    }

    public List<Dispatch> getDispatches() {
        return this.e;
    }

    public JSONArray getEventList() {
        return this.d;
    }

    public JSONObject getPayload() {
        try {
            this.b.put("shared", this.c);
            this.b.put("events", this.d);
        } catch (JSONException unused) {
        }
        return this.b;
    }

    public JSONObject getShared() {
        return this.c;
    }

    public boolean isCompressed() {
        return this.f;
    }

    public void removeKnownSharedKeys() {
        for (String str : g) {
            try {
                if (this.d.getJSONObject(0).has(str)) {
                    this.c.put(str, this.d.getJSONObject(0).get(str));
                    a(str);
                }
            } catch (JSONException unused) {
                this.a.e(R.string.bulk_dispatch_warning_known_keys_missing, str);
            }
        }
    }

    public BulkDispatch(c cVar, List<Dispatch> list, boolean z) {
        this.f = false;
        this.a = cVar;
        this.b = new JSONObject();
        this.c = new JSONObject();
        this.d = new JSONArray();
        this.e = list;
        a();
        if (z) {
            compress();
        }
    }

    private void a(String str) throws JSONException {
        for (int i = 0; i < this.d.length(); i++) {
            this.d.getJSONObject(i).remove(str);
        }
    }

    private String[] a(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        String[] strArr = new String[jSONObject.length()];
        int i = 0;
        while (keys.hasNext()) {
            strArr[i] = keys.next();
            i++;
        }
        return strArr;
    }
}
