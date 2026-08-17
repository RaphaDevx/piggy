package com.tealium.remotecommands.firebase;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import com.tealium.remotecommands.RemoteCommand;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FirebaseRemoteCommand extends RemoteCommand {
    public static final String DEFAULT_COMMAND_DESCRIPTION = "Tealium Firebase Analytics integration";
    public static final String DEFAULT_COMMAND_ID = "firebaseAnalytics";
    private static Activity b;
    com.tealium.remotecommands.firebase.a a;

    static class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Activity unused = FirebaseRemoteCommand.b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Activity unused = FirebaseRemoteCommand.b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Activity unused = FirebaseRemoteCommand.b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public FirebaseRemoteCommand(Application application) {
        this(application, DEFAULT_COMMAND_ID, DEFAULT_COMMAND_DESCRIPTION);
    }

    public FirebaseRemoteCommand(Application application, String str, String str2) {
        super(str == null ? DEFAULT_COMMAND_ID : str, str2 == null ? DEFAULT_COMMAND_DESCRIPTION : str2);
        application.registerActivityLifecycleCallbacks(a());
        this.a = new b(application.getApplicationContext());
    }

    private static Application.ActivityLifecycleCallbacks a() {
        return new a();
    }

    private JSONArray a(JSONObject jSONObject) {
        try {
            return jSONObject.get(FirebaseConstants.ItemProperties.ID) instanceof JSONArray ? a(jSONObject, jSONObject.getJSONArray(FirebaseConstants.ItemProperties.ID).length()) : a(jSONObject, 1);
        } catch (JSONException e) {
            Log.d(FirebaseConstants.TAG, "Error formatting items param: " + e.toString());
            return new JSONArray();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v12 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v16 */
    /* JADX WARN: Type inference failed for: r17v18 */
    /* JADX WARN: Type inference failed for: r17v20 */
    /* JADX WARN: Type inference failed for: r17v22 */
    /* JADX WARN: Type inference failed for: r17v23 */
    /* JADX WARN: Type inference failed for: r17v24 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v8 */
    private JSONArray a(JSONObject jSONObject, int i) {
        ?? r17;
        JSONObject jSONObject2 = jSONObject;
        int i2 = i;
        JSONArray jSONArray = new JSONArray();
        boolean z = true;
        try {
            if (i2 > 1) {
                int i3 = 0;
                while (i3 < i2) {
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.ID) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.ID, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.ID).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.BRAND) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.BRAND, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.BRAND).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.CATEGORY) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.CATEGORY, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.CATEGORY).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.NAME) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.NAME, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.NAME).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.PRICE) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.PRICE, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.PRICE).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.QUANTITY) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.QUANTITY, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.QUANTITY).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.INDEX) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.INDEX, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.INDEX).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.LIST) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.LIST, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.LIST).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.LOCATION_ID) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.LOCATION_ID, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.LOCATION_ID).get(i3));
                    }
                    if (jSONObject2.optJSONArray(FirebaseConstants.ItemProperties.VARIANT) != null) {
                        jSONObject3.put(FirebaseConstants.ItemProperties.VARIANT, jSONObject2.getJSONArray(FirebaseConstants.ItemProperties.VARIANT).get(i3));
                    }
                    jSONArray.put(jSONObject3);
                    i3++;
                    i2 = i;
                }
            } else {
                JSONObject jSONObject4 = new JSONObject();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    switch (next.hashCode()) {
                        case -1834063592:
                            if (next.equals(FirebaseConstants.ItemProperties.LIST)) {
                                r17 = 7;
                                break;
                            }
                            r17 = -1;
                            break;
                        case -1834011899:
                            if (next.equals(FirebaseConstants.ItemProperties.NAME)) {
                                r17 = 3;
                                break;
                            }
                            r17 = -1;
                            break;
                        case -1030380979:
                            if (next.equals(FirebaseConstants.ItemProperties.BRAND)) {
                                r17 = z;
                                break;
                            }
                            r17 = -1;
                            break;
                        case -997995523:
                            if (next.equals(FirebaseConstants.ItemProperties.QUANTITY)) {
                                r17 = 5;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 127510648:
                            if (next.equals(FirebaseConstants.ItemProperties.CATEGORY)) {
                                r17 = 2;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 933258283:
                            if (next.equals(FirebaseConstants.ItemProperties.LOCATION_ID)) {
                                r17 = 8;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 1154238112:
                            if (next.equals(FirebaseConstants.ItemProperties.INDEX)) {
                                r17 = 6;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 1160826647:
                            if (next.equals(FirebaseConstants.ItemProperties.PRICE)) {
                                r17 = 4;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 1294179349:
                            if (next.equals(FirebaseConstants.ItemProperties.ID)) {
                                r17 = 0;
                                break;
                            }
                            r17 = -1;
                            break;
                        case 2040384971:
                            if (next.equals(FirebaseConstants.ItemProperties.VARIANT)) {
                                r17 = 9;
                                break;
                            }
                            r17 = -1;
                            break;
                        default:
                            r17 = -1;
                            break;
                    }
                    switch (r17) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            jSONObject4.put(next, jSONObject2.get(next));
                            break;
                    }
                    Log.d(FirebaseConstants.TAG, "Invalid item param key: " + next + ".");
                    jSONObject2 = jSONObject;
                    z = true;
                }
                jSONArray.put(jSONObject4);
            }
        } catch (JSONException e) {
            Log.d(FirebaseConstants.TAG, "Error formatting items param: " + e.toString());
        }
        return jSONArray;
    }

    private void a(String[] strArr, JSONObject jSONObject) {
        char c;
        for (String str : strArr) {
            String lowerCase = str.trim().toLowerCase();
            try {
                Log.i(FirebaseConstants.TAG, "Processing command: " + lowerCase + " with payload: " + jSONObject.toString());
                switch (lowerCase.hashCode()) {
                    case -1933685031:
                        if (lowerCase.equals(FirebaseConstants.Commands.SET_SCREEN_NAME)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1354792126:
                        if (lowerCase.equals(FirebaseConstants.Commands.CONFIGURE)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 547227650:
                        if (lowerCase.equals(FirebaseConstants.Commands.SET_USER_PROPERTY)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1561500936:
                        if (lowerCase.equals(FirebaseConstants.Commands.SET_USER_ID)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2019310038:
                        if (lowerCase.equals(FirebaseConstants.Commands.LOG_EVENT)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2024263033:
                        if (lowerCase.equals(FirebaseConstants.Commands.RESET_DATA)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    this.a.a(Integer.valueOf(jSONObject.optInt(FirebaseConstants.Keys.SESSION_TIMEOUT, -1) * 1000), Integer.valueOf(jSONObject.optInt(FirebaseConstants.Keys.MIN_SECONDS, -1) * 1000), Boolean.valueOf(jSONObject.optBoolean(FirebaseConstants.Keys.ANALYTICS_ENABLED, true)));
                } else if (c == 1) {
                    String optString = jSONObject.optString(FirebaseConstants.Keys.EVENT_NAME, null);
                    JSONObject optJSONObject = jSONObject.optJSONObject("event");
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("items");
                    if (optJSONObject == null) {
                        optJSONObject = jSONObject.optJSONObject(FirebaseConstants.Keys.TAG_EVENT_PARAMS);
                    }
                    if (optJSONObject2 != null) {
                        optJSONObject.put("param_items", a(optJSONObject2));
                    }
                    this.a.a(optString, optJSONObject);
                } else if (c == 2) {
                    this.a.a(b, jSONObject.optString(FirebaseConstants.Keys.SCREEN_NAME, null), jSONObject.optString(FirebaseConstants.Keys.SCREEN_CLASS, null));
                } else if (c == 3) {
                    this.a.a(jSONObject.optString(FirebaseConstants.Keys.USER_PROPERTY_NAME, null), jSONObject.optString(FirebaseConstants.Keys.USER_PROPERTY_VALUE, null));
                } else if (c == 4) {
                    this.a.a(jSONObject.optString(FirebaseConstants.Keys.USER_ID, null));
                } else if (c == 5) {
                    this.a.a();
                }
            } catch (Exception e) {
                Log.w(FirebaseConstants.TAG, "Error processing command: " + lowerCase, e);
            }
        }
    }

    private String[] b(JSONObject jSONObject) {
        return jSONObject.optString(FirebaseConstants.Keys.COMMAND_NAME, "").split(FirebaseConstants.SEPARATOR);
    }

    @Override // com.tealium.remotecommands.RemoteCommand
    protected void onInvoke(RemoteCommand.Response response) {
        JSONObject requestPayload = response.getRequestPayload();
        a(b(requestPayload), requestPayload);
        response.send();
    }
}
