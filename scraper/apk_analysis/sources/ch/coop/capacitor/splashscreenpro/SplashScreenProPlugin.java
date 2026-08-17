package ch.coop.capacitor.splashscreenpro;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SplashScreenPro")
/* loaded from: classes3.dex */
public class SplashScreenProPlugin extends Plugin {
    public SplashScreenProView splashScreenProView;

    @Override // com.getcapacitor.Plugin
    public void load() {
        if (this.splashScreenProView == null) {
            this.splashScreenProView = new SplashScreenProView(getContext());
        }
        if (this.bridge.isMinimumWebViewInstalled() || this.bridge.getConfig().getErrorPath() == null) {
            this.splashScreenProView.show(getActivity());
        }
    }

    @PluginMethod
    public void show(PluginCall pluginCall) {
        this.splashScreenProView.show(getActivity());
        pluginCall.resolve();
    }

    @PluginMethod
    public void hide(PluginCall pluginCall) {
        this.splashScreenProView.hide(getActivity());
        pluginCall.resolve();
    }

    @PluginMethod
    public void isVisible(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("isVisible", this.splashScreenProView.isVisible());
        pluginCall.resolve(jSObject);
    }
}
