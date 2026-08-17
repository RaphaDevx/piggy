package com.capacitorjs.plugins.dialog;

import androidx.appcompat.app.AppCompatActivity;
import com.capacitorjs.plugins.dialog.Dialog;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Dialog")
/* loaded from: classes3.dex */
public class DialogPlugin extends Plugin {
    @PluginMethod
    public void alert(final PluginCall pluginCall) {
        AppCompatActivity activity = getActivity();
        String string = pluginCall.getString("title");
        String string2 = pluginCall.getString("message");
        String string3 = pluginCall.getString("buttonTitle", "OK");
        if (string2 == null) {
            pluginCall.reject("Please provide a message for the dialog");
        } else if (activity.isFinishing()) {
            pluginCall.reject("App is finishing");
        } else {
            Dialog.alert(activity, string2, string, string3, new Dialog.OnResultListener() { // from class: com.capacitorjs.plugins.dialog.DialogPlugin$$ExternalSyntheticLambda2
                @Override // com.capacitorjs.plugins.dialog.Dialog.OnResultListener
                public final void onResult(boolean z, boolean z2, String str) {
                    PluginCall.this.resolve();
                }
            });
        }
    }

    @PluginMethod
    public void confirm(final PluginCall pluginCall) {
        AppCompatActivity activity = getActivity();
        String string = pluginCall.getString("title");
        String string2 = pluginCall.getString("message");
        String string3 = pluginCall.getString("okButtonTitle", "OK");
        String string4 = pluginCall.getString("cancelButtonTitle", "Cancel");
        if (string2 == null) {
            pluginCall.reject("Please provide a message for the dialog");
        } else if (activity.isFinishing()) {
            pluginCall.reject("App is finishing");
        } else {
            Dialog.confirm(activity, string2, string, string3, string4, new Dialog.OnResultListener() { // from class: com.capacitorjs.plugins.dialog.DialogPlugin$$ExternalSyntheticLambda0
                @Override // com.capacitorjs.plugins.dialog.Dialog.OnResultListener
                public final void onResult(boolean z, boolean z2, String str) {
                    DialogPlugin.lambda$confirm$1(PluginCall.this, z, z2, str);
                }
            });
        }
    }

    static /* synthetic */ void lambda$confirm$1(PluginCall pluginCall, boolean z, boolean z2, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", z);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void prompt(final PluginCall pluginCall) {
        AppCompatActivity activity = getActivity();
        String string = pluginCall.getString("title");
        String string2 = pluginCall.getString("message");
        String string3 = pluginCall.getString("okButtonTitle", "OK");
        String string4 = pluginCall.getString("cancelButtonTitle", "Cancel");
        String string5 = pluginCall.getString("inputPlaceholder", "");
        String string6 = pluginCall.getString("inputText", "");
        if (string2 == null) {
            pluginCall.reject("Please provide a message for the dialog");
        } else if (activity.isFinishing()) {
            pluginCall.reject("App is finishing");
        } else {
            Dialog.prompt(activity, string2, string, string3, string4, string5, string6, new Dialog.OnResultListener() { // from class: com.capacitorjs.plugins.dialog.DialogPlugin$$ExternalSyntheticLambda1
                @Override // com.capacitorjs.plugins.dialog.Dialog.OnResultListener
                public final void onResult(boolean z, boolean z2, String str) {
                    DialogPlugin.lambda$prompt$2(PluginCall.this, z, z2, str);
                }
            });
        }
    }

    static /* synthetic */ void lambda$prompt$2(PluginCall pluginCall, boolean z, boolean z2, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("cancelled", z2);
        if (str == null) {
            str = "";
        }
        jSObject.put("value", str);
        pluginCall.resolve(jSObject);
    }
}
