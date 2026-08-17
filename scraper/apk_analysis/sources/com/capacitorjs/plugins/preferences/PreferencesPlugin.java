package com.capacitorjs.plugins.preferences;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;

@CapacitorPlugin(name = "Preferences")
/* loaded from: classes3.dex */
public class PreferencesPlugin extends Plugin {
    private Preferences preferences;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.preferences = new Preferences(getContext(), PreferencesConfiguration.DEFAULTS);
    }

    @PluginMethod
    public void configure(PluginCall pluginCall) {
        try {
            PreferencesConfiguration m3527clone = PreferencesConfiguration.DEFAULTS.m3527clone();
            m3527clone.group = pluginCall.getString("group", PreferencesConfiguration.DEFAULTS.group);
            this.preferences = new Preferences(getContext(), m3527clone);
            pluginCall.resolve();
        } catch (CloneNotSupportedException e) {
            pluginCall.reject("Error while configuring", e);
        }
    }

    @PluginMethod
    public void get(PluginCall pluginCall) {
        String string = pluginCall.getString("key");
        if (string == null) {
            pluginCall.reject("Must provide key");
            return;
        }
        Object obj = this.preferences.get(string);
        JSObject jSObject = new JSObject();
        if (obj == null) {
            obj = JSObject.NULL;
        }
        jSObject.put("value", obj);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void set(PluginCall pluginCall) {
        String string = pluginCall.getString("key");
        if (string == null) {
            pluginCall.reject("Must provide key");
            return;
        }
        this.preferences.set(string, pluginCall.getString("value"));
        pluginCall.resolve();
    }

    @PluginMethod
    public void remove(PluginCall pluginCall) {
        String string = pluginCall.getString("key");
        if (string == null) {
            pluginCall.reject("Must provide key");
        } else {
            this.preferences.remove(string);
            pluginCall.resolve();
        }
    }

    @PluginMethod
    public void keys(PluginCall pluginCall) {
        String[] strArr = (String[]) this.preferences.keys().toArray(new String[0]);
        JSObject jSObject = new JSObject();
        try {
            jSObject.put("keys", (Object) new JSArray(strArr));
            pluginCall.resolve(jSObject);
        } catch (JSONException e) {
            pluginCall.reject("Unable to serialize response.", e);
        }
    }

    @PluginMethod
    public void clear(PluginCall pluginCall) {
        this.preferences.clear();
        pluginCall.resolve();
    }

    @PluginMethod
    public void migrate(PluginCall pluginCall) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Preferences preferences = new Preferences(getContext(), PreferencesConfiguration.DEFAULTS);
        for (String str : preferences.keys()) {
            String str2 = preferences.get(str);
            if (this.preferences.get(str) == null) {
                this.preferences.set(str, str2);
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        JSObject jSObject = new JSObject();
        jSObject.put("migrated", (Object) new JSArray((Collection) arrayList));
        jSObject.put("existing", (Object) new JSArray((Collection) arrayList2));
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void removeOld(PluginCall pluginCall) {
        pluginCall.resolve();
    }
}
