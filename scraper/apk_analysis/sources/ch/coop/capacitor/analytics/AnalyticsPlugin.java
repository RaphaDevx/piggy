package ch.coop.capacitor.analytics;

import android.app.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.tealium.library.DataSources;
import com.tealium.library.Tealium;
import com.tealium.lifecycle.LifeCycle;
import com.tealium.remotecommands.firebase.FirebaseRemoteCommand;
import java.util.Map;
import org.json.JSONException;

@CapacitorPlugin(name = "Analytics")
/* loaded from: classes3.dex */
public class AnalyticsPlugin extends Plugin {
    public static final String TEALIUM_MAIN = "main";

    @PluginMethod
    public void trackEvent(PluginCall pluginCall) throws JSONException, JsonProcessingException {
        Tealium tealium = Tealium.getInstance("main");
        JSObject object = pluginCall.getObject("event");
        if (object != null) {
            String string = object.getString("name");
            Map<String, ?> map = (Map) new ObjectMapper().readValue(object.getJSONObject("data").toString(), Map.class);
            if (string == null || string.isEmpty()) {
                pluginCall.reject("Missing the property 'name' of the track event");
                return;
            } else if (tealium != null) {
                tealium.trackEvent(string, map);
                pluginCall.resolve();
                return;
            } else {
                pluginCall.reject("Tealium instance doesn't exists");
                return;
            }
        }
        pluginCall.reject("Missing the event object");
    }

    @PluginMethod
    public void trackView(PluginCall pluginCall) throws JSONException, JsonProcessingException {
        Tealium tealium = Tealium.getInstance("main");
        JSObject object = pluginCall.getObject(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        if (object != null) {
            String string = object.getString("name");
            Map<String, ?> map = (Map) new ObjectMapper().readValue(object.getJSONObject("data").toString(), Map.class);
            if (string == null || string.isEmpty()) {
                pluginCall.reject("Missing the property 'name' of the view event");
                return;
            } else if (tealium != null) {
                tealium.trackView(string, map);
                pluginCall.resolve();
                return;
            } else {
                pluginCall.reject("Tealium instance doesn't exists");
                return;
            }
        }
        pluginCall.reject("Missing the view object");
    }

    @PluginMethod
    public void tealiumInit(PluginCall pluginCall) {
        Application application = getActivity().getApplication();
        JSObject object = pluginCall.getObject("tealiumConfig");
        if (object != null) {
            String string = object.getString("account");
            String string2 = object.getString("profile");
            String string3 = object.getString("environment");
            if (string != null && string2 != null && string3 != null) {
                Tealium.Config create = Tealium.Config.create(application, string, string2, string3);
                create.setDatasourceId("supercard-app");
                create.setRemoteCommandEnabled(true);
                if (string3.equals("qa")) {
                    create.setForceOverrideLogLevel("dev");
                }
                LifeCycle.setupInstance("main", create, false);
                Tealium.createInstance("main", create).addRemoteCommand(new FirebaseRemoteCommand(application));
                pluginCall.resolve();
                return;
            }
            pluginCall.reject("Tealium credentials are not properly implemented");
            return;
        }
        pluginCall.reject("Tealium config not provided");
    }
}
