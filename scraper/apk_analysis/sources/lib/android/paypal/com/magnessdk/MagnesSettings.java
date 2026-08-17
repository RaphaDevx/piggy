package lib.android.paypal.com.magnessdk;

import android.content.Context;
import lib.android.paypal.com.magnessdk.g;
import lib.android.paypal.com.magnessdk.network.base.MagnesNetworkingFactoryImpl;

/* loaded from: classes4.dex */
public class MagnesSettings {
    private String appGuid;
    private Context context;
    private boolean disableBeacon;
    private boolean disableRemoteConfig;
    private boolean enableNetworkOnCallerThread;
    private Environment environment;
    private boolean hasUserLocationConsent;
    private MagnesNetworkingFactoryImpl magnesNetworkingFactoryImpl;
    private int magnesSource;
    private String notificationToken;

    public static class Builder {
        private String appGuid;
        private Context context;
        private boolean enableNetworkOnCallerThread;
        private MagnesNetworkingFactoryImpl magnesNetworkingFactoryImpl;
        private String notificationToken;
        private int sourceFlow = MagnesSource.DEFAULT.getVersion();
        private boolean disableRemoteConfig = false;
        private boolean disableBeacon = false;
        private Environment environment = Environment.LIVE;
        private boolean hasUserLocationConsent = false;

        public Builder(Context context) {
            this.context = context;
        }

        public MagnesSettings build() {
            return new MagnesSettings(this);
        }

        public Builder disableBeacon(boolean z) {
            this.disableBeacon = z;
            return this;
        }

        public Builder disableRemoteConfig(boolean z) {
            this.disableRemoteConfig = z;
            return this;
        }

        public Builder enableNetworkOnCallerThread(boolean z) {
            this.enableNetworkOnCallerThread = z;
            return this;
        }

        public Builder setAppGuid(String str) throws InvalidInputException {
            if (!o.c(str)) {
                throw new InvalidInputException(g.b.c.APPGUID_EXCEPTION_MESSAGE.toString());
            }
            this.appGuid = str;
            return this;
        }

        public Builder setHasUserLocationConsent(boolean z) {
            this.hasUserLocationConsent = z;
            return this;
        }

        public Builder setMagnesEnvironment(Environment environment) {
            this.environment = environment;
            return this;
        }

        @Deprecated
        public Builder setMagnesNetworkingFactory(MagnesNetworkingFactoryImpl magnesNetworkingFactoryImpl) {
            this.magnesNetworkingFactoryImpl = magnesNetworkingFactoryImpl;
            return this;
        }

        public Builder setMagnesSource(MagnesSource magnesSource) {
            this.sourceFlow = magnesSource.getVersion();
            return this;
        }

        public Builder setNotificationToken(String str) {
            this.notificationToken = str;
            return this;
        }
    }

    private MagnesSettings(Builder builder) {
        this.disableRemoteConfig = false;
        this.disableBeacon = false;
        this.hasUserLocationConsent = false;
        this.magnesSource = builder.sourceFlow;
        this.appGuid = builder.appGuid;
        this.notificationToken = builder.notificationToken;
        this.disableRemoteConfig = builder.disableRemoteConfig;
        this.disableBeacon = builder.disableBeacon;
        this.context = builder.context;
        this.magnesNetworkingFactoryImpl = builder.magnesNetworkingFactoryImpl;
        this.enableNetworkOnCallerThread = builder.enableNetworkOnCallerThread;
        this.environment = builder.environment;
        this.hasUserLocationConsent = builder.hasUserLocationConsent;
    }

    public String getAppGuid() {
        return this.appGuid;
    }

    public Context getContext() {
        return this.context;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public MagnesNetworkingFactoryImpl getMagnesNetworkingFactoryImpl() {
        return this.magnesNetworkingFactoryImpl;
    }

    public int getMagnesSource() {
        return this.magnesSource;
    }

    public String getNotificationToken() {
        return this.notificationToken;
    }

    public boolean hasUserLocationConsent() {
        return this.hasUserLocationConsent;
    }

    public boolean isDisableBeacon() {
        return this.disableBeacon;
    }

    public boolean isDisableRemoteConfig() {
        return this.disableRemoteConfig;
    }

    public boolean isEnableNetworkOnCallerThread() {
        return this.enableNetworkOnCallerThread;
    }
}
