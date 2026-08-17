package nl.raphael.settings;

import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "NativeSettings")
/* loaded from: classes4.dex */
public class NativeSettingsPlugin extends Plugin {
    @PluginMethod
    public void open(PluginCall pluginCall) {
        String string = pluginCall.getString("optionAndroid");
        String action = AndroidSettings.getAction(string);
        if (action == null) {
            pluginCall.reject("Could not find native android setting: " + string);
            return;
        }
        openOption(pluginCall, action);
    }

    @PluginMethod
    public void openAndroid(PluginCall pluginCall) {
        String string = pluginCall.getString("option");
        String action = AndroidSettings.getAction(string);
        if (action == null) {
            pluginCall.reject("Could not find native android setting: " + string);
            return;
        }
        openOption(pluginCall, action);
    }

    private void openOption(PluginCall pluginCall, String str) {
        Intent intent = new Intent();
        if ("android.settings.APPLICATION_DETAILS_SETTINGS".equals(str)) {
            intent.setAction(str);
            intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        } else if ("android.settings.APP_NOTIFICATION_SETTINGS".equals(str)) {
            intent.setAction(str);
            intent.putExtra("android.provider.extra.APP_PACKAGE", getActivity().getPackageName());
        } else {
            intent.setAction(str);
        }
        startActivityForResult(pluginCall, intent, "activityResult");
    }

    @ActivityCallback
    private void activityResult(PluginCall pluginCall, ActivityResult activityResult) {
        JSObject jSObject = new JSObject();
        jSObject.put("status", true);
        pluginCall.resolve(jSObject);
    }
}
