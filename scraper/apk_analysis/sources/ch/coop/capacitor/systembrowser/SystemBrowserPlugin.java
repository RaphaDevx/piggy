package ch.coop.capacitor.systembrowser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.Iterator;

@CapacitorPlugin(name = "SystemBrowser")
/* loaded from: classes3.dex */
public class SystemBrowserPlugin extends Plugin {
    @PluginMethod
    public void open(PluginCall pluginCall) {
        String str;
        String string = pluginCall.getString("url");
        if (string == null) {
            pluginCall.reject("Must supply a URL");
            return;
        }
        Uri parse = Uri.parse(string);
        if (parse == null) {
            pluginCall.reject("Invalid URL");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        Iterator<ResolveInfo> it = getActivityPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ResolveInfo next = it.next();
            if (!next.activityInfo.packageName.equals(getActivity().getPackageName())) {
                str = next.activityInfo.packageName;
                break;
            }
        }
        if (str != null) {
            intent.setPackage(str);
            try {
                getAppActivity().startActivity(intent);
                JSObject jSObject = new JSObject();
                jSObject.put("completed", true);
                pluginCall.resolve(jSObject);
                return;
            } catch (ActivityNotFoundException unused) {
                pluginCall.reject("Cannot launch system browser");
                return;
            }
        }
        pluginCall.reject("No package found to launch.");
    }

    public AppCompatActivity getAppActivity() {
        return getActivity();
    }

    public PackageManager getActivityPackageManager() {
        return getActivity().getPackageManager();
    }
}
