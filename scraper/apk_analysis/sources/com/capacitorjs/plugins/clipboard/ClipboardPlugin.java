package com.capacitorjs.plugins.clipboard;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Clipboard")
/* loaded from: classes3.dex */
public class ClipboardPlugin extends Plugin {
    private Clipboard implementation;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new Clipboard(getContext());
    }

    @PluginMethod
    public void write(PluginCall pluginCall) {
        ClipboardWriteResponse write;
        String string = pluginCall.getString(TypedValues.Custom.S_STRING);
        String string2 = pluginCall.getString("image");
        String string3 = pluginCall.getString("url");
        String string4 = pluginCall.getString("label");
        if (string != null) {
            write = this.implementation.write(string4, string);
        } else if (string2 != null) {
            write = this.implementation.write(string4, string2);
        } else if (string3 != null) {
            write = this.implementation.write(string4, string3);
        } else {
            pluginCall.reject("No data provided");
            return;
        }
        if (write.isSuccess()) {
            pluginCall.resolve();
        } else {
            pluginCall.reject(write.getErrorMessage());
        }
    }

    @PluginMethod
    public void read(PluginCall pluginCall) {
        ClipboardData read = this.implementation.read();
        if (read == null) {
            pluginCall.reject("Unable to read clipboard from the given Context");
            return;
        }
        if (read.getValue() == null) {
            pluginCall.reject("There is no data on the clipboard");
            return;
        }
        JSObject jSObject = new JSObject();
        jSObject.put("value", read.getValue());
        jSObject.put("type", read.getType());
        pluginCall.resolve(jSObject);
    }
}
