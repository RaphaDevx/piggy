package com.mycompany.plugins.example;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AppIcon")
/* loaded from: classes2.dex */
public class AppIconPlugin extends Plugin {
    private AppIconBase implementation;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new AppIconBase(getActivity(), getContext());
    }

    @PluginMethod
    public void isSupported(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", (Object) this.implementation.isSupported());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getName(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", this.implementation.getName());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void change(PluginCall pluginCall) {
        if (!pluginCall.getData().has("name")) {
            pluginCall.reject("Must provide an icon name");
        } else if (!pluginCall.getData().has("disable")) {
            pluginCall.reject("Must provide an array of icon names to disable");
        } else {
            this.implementation.change(pluginCall.getString("name", null), pluginCall.getArray("disable", null));
            pluginCall.resolve();
        }
    }

    @PluginMethod
    public void reset(PluginCall pluginCall) {
        if (!pluginCall.getData().has("disable")) {
            pluginCall.reject("Must provide an array of icon names to disable");
        } else {
            this.implementation.reset(pluginCall.getArray("disable", null));
            pluginCall.resolve();
        }
    }
}
