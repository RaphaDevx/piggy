package com.elylucas.capscreenbrightness;

import android.app.Activity;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ScreenBrightness")
/* loaded from: classes3.dex */
public class ScreenBrightnessPlugin extends Plugin {
    @PluginMethod
    public void setBrightness(final PluginCall pluginCall) {
        final Float f = pluginCall.getFloat("brightness");
        final AppCompatActivity activity = getActivity();
        final WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        activity.runOnUiThread(new Runnable() { // from class: com.elylucas.capscreenbrightness.ScreenBrightnessPlugin$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ScreenBrightnessPlugin.lambda$setBrightness$0(attributes, f, activity, pluginCall);
            }
        });
    }

    static /* synthetic */ void lambda$setBrightness$0(WindowManager.LayoutParams layoutParams, Float f, Activity activity, PluginCall pluginCall) {
        layoutParams.screenBrightness = f.floatValue();
        activity.getWindow().setAttributes(layoutParams);
        pluginCall.resolve();
    }

    @PluginMethod
    public void getBrightness(PluginCall pluginCall) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        new JSObject();
        pluginCall.resolve(new JSObject(this, attributes) { // from class: com.elylucas.capscreenbrightness.ScreenBrightnessPlugin.1
            final /* synthetic */ WindowManager.LayoutParams val$layoutParams;

            {
                this.val$layoutParams = attributes;
                put("brightness", attributes.screenBrightness);
            }
        });
    }
}
