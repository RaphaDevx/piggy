package com.capacitorjs.plugins.network;

import android.util.Log;
import com.capacitorjs.plugins.network.Network;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Network")
/* loaded from: classes3.dex */
public class NetworkPlugin extends Plugin {
    public static final String NETWORK_CHANGE_EVENT = "networkStatusChange";
    private Network implementation;
    private NetworkStatus prePauseNetworkStatus = null;

    @Override // com.getcapacitor.Plugin
    public void load() {
        this.implementation = new Network(getContext());
        this.implementation.setStatusChangeListener(new Network.NetworkStatusChangeListener() { // from class: com.capacitorjs.plugins.network.NetworkPlugin$$ExternalSyntheticLambda0
            @Override // com.capacitorjs.plugins.network.Network.NetworkStatusChangeListener
            public final void onNetworkStatusChanged(boolean z) {
                NetworkPlugin.this.lambda$load$0(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(boolean z) {
        if (z) {
            JSObject jSObject = new JSObject();
            jSObject.put("connected", false);
            jSObject.put("connectionType", "none");
            notifyListeners(NETWORK_CHANGE_EVENT, jSObject);
            return;
        }
        updateNetworkStatus();
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnDestroy() {
        this.implementation.setStatusChangeListener(null);
    }

    @PluginMethod
    public void getStatus(PluginCall pluginCall) {
        pluginCall.resolve(parseNetworkStatus(this.implementation.getNetworkStatus()));
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnResume() {
        this.implementation.startMonitoring();
        NetworkStatus networkStatus = this.implementation.getNetworkStatus();
        if (this.prePauseNetworkStatus != null && !networkStatus.connected && (this.prePauseNetworkStatus.connected || networkStatus.connectionType != this.prePauseNetworkStatus.connectionType)) {
            Log.d("Capacitor/NetworkPlugin", "Detected pre-pause and after-pause network status mismatch. Updating network status and notifying listeners.");
            updateNetworkStatus();
        }
        this.prePauseNetworkStatus = null;
    }

    @Override // com.getcapacitor.Plugin
    protected void handleOnPause() {
        this.prePauseNetworkStatus = this.implementation.getNetworkStatus();
        this.implementation.stopMonitoring();
    }

    private void updateNetworkStatus() {
        notifyListeners(NETWORK_CHANGE_EVENT, parseNetworkStatus(this.implementation.getNetworkStatus()));
    }

    private JSObject parseNetworkStatus(NetworkStatus networkStatus) {
        JSObject jSObject = new JSObject();
        jSObject.put("connected", networkStatus.connected);
        jSObject.put("connectionType", networkStatus.connectionType.getConnectionType());
        return jSObject;
    }
}
