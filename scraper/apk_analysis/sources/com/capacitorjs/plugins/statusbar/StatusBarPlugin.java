package com.capacitorjs.plugins.statusbar;

import android.content.res.Configuration;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.capacitorjs.plugins.statusbar.StatusBar;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;
import java.util.Locale;

@CapacitorPlugin(name = "StatusBar")
/* loaded from: classes3.dex */
public class StatusBarPlugin extends Plugin {
    private StatusBar implementation;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new StatusBar(getActivity(), getStatusBarConfig(), new StatusBar.ChangeListener() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda5
            @Override // com.capacitorjs.plugins.statusbar.StatusBar.ChangeListener
            public final void onChange(String str, StatusBarInfo statusBarInfo) {
                StatusBarPlugin.this.lambda$load$0(str, statusBarInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(String str, StatusBarInfo statusBarInfo) {
        notifyListeners(str, toJSObject(statusBarInfo), true);
    }

    private StatusBarConfig getStatusBarConfig() {
        StatusBarConfig statusBarConfig = new StatusBarConfig();
        String string = getConfig().getString("backgroundColor");
        if (string != null) {
            try {
                statusBarConfig.setBackgroundColor(Integer.valueOf(WebColor.parseColor(string)));
            } catch (IllegalArgumentException unused) {
                Logger.debug("Background color not applied");
            }
        }
        statusBarConfig.setStyle(styleFromConfig(getConfig().getString("style", statusBarConfig.getStyle())));
        statusBarConfig.setOverlaysWebView(getConfig().getBoolean("overlaysWebView", statusBarConfig.isOverlaysWebView()));
        return statusBarConfig;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String styleFromConfig(String str) {
        char c;
        String lowerCase = str.toLowerCase();
        switch (lowerCase.hashCode()) {
            case 3075958:
                if (lowerCase.equals("dark")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 102970646:
                if (lowerCase.equals("light")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 551041699:
                if (lowerCase.equals("lightcontent")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1544803905:
                if (lowerCase.equals("default")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1668813891:
                if (lowerCase.equals("darkcontent")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            return "DARK";
        }
        if (c == 2 || c == 3) {
            return "LIGHT";
        }
        return "DEFAULT";
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnConfigurationChanged(Configuration configuration) {
        super.handleOnConfigurationChanged(configuration);
        this.implementation.updateStyle();
    }

    @PluginMethod
    public void setStyle(final PluginCall pluginCall) {
        final String string = pluginCall.getString("style");
        if (string == null) {
            pluginCall.reject("Style must be provided");
        } else {
            getBridge().executeOnMainThread(new Runnable() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    StatusBarPlugin.this.lambda$setStyle$1(string, pluginCall);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setStyle$1(String str, PluginCall pluginCall) {
        this.implementation.setStyle(str);
        pluginCall.resolve();
    }

    @PluginMethod
    public void setBackgroundColor(final PluginCall pluginCall) {
        final String string = pluginCall.getString(TypedValues.Custom.S_COLOR);
        if (string == null) {
            pluginCall.reject("Color must be provided");
        } else {
            getBridge().executeOnMainThread(new Runnable() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    StatusBarPlugin.this.lambda$setBackgroundColor$2(string, pluginCall);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setBackgroundColor$2(String str, PluginCall pluginCall) {
        try {
            this.implementation.setBackgroundColor(WebColor.parseColor(str.toUpperCase(Locale.ROOT)));
            pluginCall.resolve();
        } catch (IllegalArgumentException unused) {
            pluginCall.reject("Invalid color provided. Must be a hex string (ex: #ff0000");
        }
    }

    @PluginMethod
    public void hide(final PluginCall pluginCall) {
        getBridge().executeOnMainThread(new Runnable() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                StatusBarPlugin.this.lambda$hide$3(pluginCall);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$3(PluginCall pluginCall) {
        this.implementation.hide();
        pluginCall.resolve();
    }

    @PluginMethod
    public void show(final PluginCall pluginCall) {
        getBridge().executeOnMainThread(new Runnable() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                StatusBarPlugin.this.lambda$show$4(pluginCall);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$show$4(PluginCall pluginCall) {
        this.implementation.show();
        pluginCall.resolve();
    }

    @PluginMethod
    public void getInfo(PluginCall pluginCall) {
        pluginCall.resolve(toJSObject(this.implementation.getInfo()));
    }

    @PluginMethod
    public void setOverlaysWebView(final PluginCall pluginCall) {
        final Boolean bool = pluginCall.getBoolean("overlay", true);
        getBridge().executeOnMainThread(new Runnable() { // from class: com.capacitorjs.plugins.statusbar.StatusBarPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                StatusBarPlugin.this.lambda$setOverlaysWebView$5(bool, pluginCall);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setOverlaysWebView$5(Boolean bool, PluginCall pluginCall) {
        this.implementation.setOverlaysWebView(bool);
        pluginCall.resolve();
    }

    private JSObject toJSObject(StatusBarInfo statusBarInfo) {
        JSObject jSObject = new JSObject();
        jSObject.put("visible", statusBarInfo.isVisible());
        jSObject.put("style", statusBarInfo.getStyle());
        jSObject.put(TypedValues.Custom.S_COLOR, statusBarInfo.getColor());
        jSObject.put("overlays", statusBarInfo.isOverlays());
        jSObject.put("height", statusBarInfo.getHeight());
        return jSObject;
    }
}
