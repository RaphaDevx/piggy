package de.einfachhans.emailcomposer;

import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import org.json.JSONException;

@CapacitorPlugin(name = "EmailComposer")
/* loaded from: classes2.dex */
public class EmailComposerPlugin extends Plugin {
    private EmailComposer implementation;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new EmailComposer(getContext());
        AssetUtil.cleanupAttachmentFolder(getContext());
    }

    @PluginMethod
    public void hasAccount(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("hasAccount", true);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void open(PluginCall pluginCall) throws JSONException {
        try {
            startActivityForResult(pluginCall, this.implementation.getIntent(pluginCall), "openCallback");
        } catch (RuntimeException e) {
            pluginCall.reject(e.getLocalizedMessage());
        }
    }

    @ActivityCallback
    private void openCallback(PluginCall pluginCall, ActivityResult activityResult) {
        pluginCall.resolve();
    }
}
