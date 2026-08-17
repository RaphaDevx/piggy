package com.capacitorjs.plugins.applauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.InternalUtils;

@CapacitorPlugin(name = "AppLauncher")
/* loaded from: classes3.dex */
public class AppLauncherPlugin extends Plugin {
    @PluginMethod
    public void canOpenUrl(PluginCall pluginCall) {
        String string = pluginCall.getString("url");
        if (string == null) {
            pluginCall.reject("Must supply a url");
            return;
        }
        PackageManager packageManager = getActivity().getApplicationContext().getPackageManager();
        JSObject jSObject = new JSObject();
        try {
            InternalUtils.getPackageInfo(packageManager, string, 1L);
            jSObject.put("value", true);
            pluginCall.resolve(jSObject);
        } catch (PackageManager.NameNotFoundException unused) {
            Logger.error(getLogTag(), "Package name '" + string + "' not found!", null);
            jSObject.put("value", false);
            pluginCall.resolve(jSObject);
        }
    }

    @PluginMethod
    public void openUrl(PluginCall pluginCall) {
        String string = pluginCall.getString("url");
        if (string == null) {
            pluginCall.reject("Must provide a url to open");
            return;
        }
        JSObject jSObject = new JSObject();
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(string));
        try {
            getActivity().startActivity(intent);
            jSObject.put("completed", true);
        } catch (Exception unused) {
            try {
                getActivity().startActivity(packageManager.getLaunchIntentForPackage(string));
                jSObject.put("completed", true);
            } catch (Exception unused2) {
                jSObject.put("completed", false);
            }
        }
        pluginCall.resolve(jSObject);
    }
}
