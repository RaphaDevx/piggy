package com.capacitorjs.plugins.device;

import android.os.Build;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import io.sentry.ProfileChunk;
import io.sentry.protocol.Device;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Locale;

@CapacitorPlugin(name = "Device")
/* loaded from: classes3.dex */
public class DevicePlugin extends Plugin {
    private Device implementation;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new Device(getContext());
    }

    @PluginMethod
    public void getId(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put(ViewHierarchyNode.JsonKeys.IDENTIFIER, this.implementation.getUuid());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getInfo(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("memUsed", this.implementation.getMemUsed());
        jSObject.put(Device.JsonKeys.MODEL, Build.MODEL);
        jSObject.put("operatingSystem", ProfileChunk.PLATFORM_ANDROID);
        jSObject.put(LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_OS_VERSION, Build.VERSION.RELEASE);
        jSObject.put("androidSDKVersion", Build.VERSION.SDK_INT);
        jSObject.put("platform", this.implementation.getPlatform());
        jSObject.put(Device.JsonKeys.MANUFACTURER, Build.MANUFACTURER);
        jSObject.put("isVirtual", this.implementation.isVirtual());
        jSObject.put("name", this.implementation.getName());
        jSObject.put("webViewVersion", this.implementation.getWebViewVersion());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getBatteryInfo(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("batteryLevel", this.implementation.getBatteryLevel());
        jSObject.put("isCharging", this.implementation.isCharging());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getLanguageCode(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", Locale.getDefault().getLanguage());
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void getLanguageTag(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("value", Locale.getDefault().toLanguageTag());
        pluginCall.resolve(jSObject);
    }
}
