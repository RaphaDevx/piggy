package ch.coop.capacitor.marketingcloud;

import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import ch.coop.capacitor.marketingcloud.MarketingCloudManager;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "MarketingCloud", permissions = {@Permission(alias = MarketingCloudPlugin.PERMISSION_ALIAS, strings = {"android.permission.POST_NOTIFICATIONS"})})
/* loaded from: classes3.dex */
public final class MarketingCloudPlugin extends Plugin {
    private static final String PERMISSION_ALIAS = "receive";
    public MarketingCloudManager marketingCloudManager = MarketingCloudManager.getInstance();
    public NotificationManagerResolver notificationManagerResolver = new NotificationManagerResolver() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda4
        @Override // ch.coop.capacitor.marketingcloud.MarketingCloudPlugin.NotificationManagerResolver
        public final NotificationManagerCompat getNotificationManager() {
            NotificationManagerCompat lambda$new$0;
            lambda$new$0 = MarketingCloudPlugin.this.lambda$new$0();
            return lambda$new$0;
        }
    };
    public BuildVersionProvider buildVersionProvider = new BuildVersionProvider() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda5
        @Override // ch.coop.capacitor.marketingcloud.MarketingCloudPlugin.BuildVersionProvider
        public final boolean isAndroid13OrAbove() {
            return MarketingCloudPlugin.lambda$new$1();
        }
    };
    public PluginMethodProvider pluginMethodProvider = new PluginMethodProvider() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda6
        @Override // ch.coop.capacitor.marketingcloud.MarketingCloudPlugin.PluginMethodProvider
        public final void requestPermissionForAlias(String str, PluginCall pluginCall, String str2) {
            MarketingCloudPlugin.this.lambda$new$2(str, pluginCall, str2);
        }
    };

    @FunctionalInterface
    public interface BuildVersionProvider {
        boolean isAndroid13OrAbove();
    }

    @FunctionalInterface
    public interface NotificationManagerResolver {
        NotificationManagerCompat getNotificationManager();
    }

    @FunctionalInterface
    public interface PluginMethodProvider {
        void requestPermissionForAlias(String str, PluginCall pluginCall, String str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NotificationManagerCompat lambda$new$0() {
        return NotificationManagerCompat.from(getContext());
    }

    static /* synthetic */ boolean lambda$new$1() {
        return Build.VERSION.SDK_INT >= 33;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(String str, PluginCall pluginCall, String str2) {
        super.requestPermissionForAlias(str, pluginCall, str2);
    }

    @PluginMethod
    public void deviceIdentifier(final PluginCall pluginCall) {
        this.marketingCloudManager.getDeviceIdentifier(new MarketingCloudManager.GetStringCallback() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda1
            @Override // ch.coop.capacitor.marketingcloud.MarketingCloudManager.GetStringCallback
            public final void onResult(String str) {
                MarketingCloudPlugin.lambda$deviceIdentifier$3(PluginCall.this, str);
            }
        });
    }

    static /* synthetic */ void lambda$deviceIdentifier$3(PluginCall pluginCall, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("hasDeviceIdentifier", str != null);
        if (str == null) {
            str = "";
        }
        jSObject.put("deviceIdentifier", str);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void deviceToken(final PluginCall pluginCall) {
        this.marketingCloudManager.getDeviceToken(new MarketingCloudManager.GetStringCallback() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda3
            @Override // ch.coop.capacitor.marketingcloud.MarketingCloudManager.GetStringCallback
            public final void onResult(String str) {
                MarketingCloudPlugin.lambda$deviceToken$4(PluginCall.this, str);
            }
        });
    }

    static /* synthetic */ void lambda$deviceToken$4(PluginCall pluginCall, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("hasDeviceToken", str != null);
        if (str == null) {
            str = "";
        }
        jSObject.put("deviceToken", str);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void contactKey(final PluginCall pluginCall) {
        this.marketingCloudManager.getContactKey(new MarketingCloudManager.GetStringCallback() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda2
            @Override // ch.coop.capacitor.marketingcloud.MarketingCloudManager.GetStringCallback
            public final void onResult(String str) {
                MarketingCloudPlugin.lambda$contactKey$5(PluginCall.this, str);
            }
        });
    }

    static /* synthetic */ void lambda$contactKey$5(PluginCall pluginCall, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("hasContactKey", str != null);
        if (str == null) {
            str = "";
        }
        jSObject.put("contactKey", str);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void setContactKey(PluginCall pluginCall) {
        String string = pluginCall.getString("value");
        if (string == null) {
            pluginCall.reject("No value provided");
        } else {
            this.marketingCloudManager.setContactKey(string);
            pluginCall.resolve();
        }
    }

    @PluginMethod
    public void setAttribute(PluginCall pluginCall) {
        String string = pluginCall.getString("name");
        String string2 = pluginCall.getString("value");
        if (string == null) {
            pluginCall.reject("No name provided");
        } else if (string2 == null) {
            pluginCall.reject("No value provided");
        } else {
            this.marketingCloudManager.setAttribute(string, string2);
            pluginCall.resolve();
        }
    }

    @PluginMethod
    public void getAttribute(final PluginCall pluginCall) {
        String string = pluginCall.getString("name");
        if (string == null) {
            pluginCall.reject("No name provided");
        } else {
            this.marketingCloudManager.getAttribute(string, new MarketingCloudManager.GetStringCallback() { // from class: ch.coop.capacitor.marketingcloud.MarketingCloudPlugin$$ExternalSyntheticLambda0
                @Override // ch.coop.capacitor.marketingcloud.MarketingCloudManager.GetStringCallback
                public final void onResult(String str) {
                    MarketingCloudPlugin.lambda$getAttribute$6(PluginCall.this, str);
                }
            });
        }
    }

    static /* synthetic */ void lambda$getAttribute$6(PluginCall pluginCall, String str) {
        JSObject jSObject = new JSObject();
        jSObject.put("hasAttribute", str != null);
        if (str == null) {
            str = "";
        }
        jSObject.put("attribute", str);
        pluginCall.resolve(jSObject);
    }

    @Override // com.getcapacitor.Plugin
    @PluginMethod
    public void checkPermissions(PluginCall pluginCall) {
        String str;
        if ((!this.buildVersionProvider.isAndroid13OrAbove() || getPermissionState(PERMISSION_ALIAS) == PermissionState.GRANTED) && this.notificationManagerResolver.getNotificationManager().areNotificationsEnabled()) {
            str = "granted";
        } else {
            str = "denied";
        }
        JSObject jSObject = new JSObject();
        jSObject.put(PERMISSION_ALIAS, str);
        pluginCall.resolve(jSObject);
    }

    @Override // com.getcapacitor.Plugin
    @PluginMethod
    public void requestPermissions(PluginCall pluginCall) {
        if (this.buildVersionProvider.isAndroid13OrAbove() && getPermissionState(PERMISSION_ALIAS) != PermissionState.GRANTED) {
            this.pluginMethodProvider.requestPermissionForAlias(PERMISSION_ALIAS, pluginCall, "requestPermissionCallback");
            return;
        }
        JSObject jSObject = new JSObject();
        jSObject.put(PERMISSION_ALIAS, (Object) PermissionState.GRANTED);
        pluginCall.resolve(jSObject);
    }

    @PermissionCallback
    private void requestPermissionCallback(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put(PERMISSION_ALIAS, (Object) getPermissionState(PERMISSION_ALIAS));
        pluginCall.resolve(jSObject);
    }
}
