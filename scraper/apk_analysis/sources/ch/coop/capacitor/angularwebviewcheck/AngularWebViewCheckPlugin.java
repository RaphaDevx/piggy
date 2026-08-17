package ch.coop.capacitor.angularwebviewcheck;

import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AngularWebViewCheck")
/* loaded from: classes3.dex */
public class AngularWebViewCheckPlugin extends Plugin {
    @PluginMethod
    public void handleError(PluginCall pluginCall) {
        AppCompatActivity activity = getActivity();
        try {
            this.bridge.getPlugin("SplashScreenPro").invoke("hide", pluginCall);
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() { // from class: ch.coop.capacitor.angularwebviewcheck.AngularWebViewCheckPlugin.1
            @Override // java.lang.Runnable
            public void run() {
                Uri parse = Uri.parse("file:///android_asset/public/assets/error.html");
                if (parse != null) {
                    AngularWebViewCheckPlugin.this.bridge.getWebView().loadUrl(parse.toString());
                }
            }
        });
    }
}
