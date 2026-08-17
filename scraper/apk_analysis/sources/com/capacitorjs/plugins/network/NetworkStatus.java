package com.capacitorjs.plugins.network;

/* loaded from: classes3.dex */
public class NetworkStatus {
    public boolean connected = false;
    public ConnectionType connectionType = ConnectionType.NONE;

    public enum ConnectionType {
        WIFI("wifi"),
        CELLULAR("cellular"),
        NONE("none"),
        UNKNOWN("unknown");

        private String connectionType;

        ConnectionType(String str) {
            this.connectionType = str;
        }

        public String getConnectionType() {
            return this.connectionType;
        }
    }
}
