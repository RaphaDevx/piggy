package com.scandit.datacapture.core.capture;

import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\u0007J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u0007J\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/core/capture/DataCaptureContextBuilder;", "", "", "licenseKey", "<init>", "(Ljava/lang/String;)V", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_DEVICE_NAME, "(Ljava/lang/String;)Lcom/scandit/datacapture/core/capture/DataCaptureContextBuilder;", "externalId", "frameworkName", "frameworkVersion", "Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;", "settings", "(Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;)Lcom/scandit/datacapture/core/capture/DataCaptureContextBuilder;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "build", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureContextBuilder {
    private final String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private DataCaptureContextSettings f;

    public DataCaptureContextBuilder(String licenseKey) {
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        this.a = licenseKey;
        this.d = "native";
        this.f = new DataCaptureContextSettings();
    }

    public final DataCaptureContext build() {
        return DataCaptureContext.INSTANCE._forAllProperties$scandit_capture_core(this.a, this.d, this.e, this.b, this.c, this.f);
    }

    public final DataCaptureContextBuilder deviceName(String deviceName) {
        this.b = deviceName;
        return this;
    }

    public final DataCaptureContextBuilder externalId(String externalId) {
        this.c = externalId;
        return this;
    }

    public final DataCaptureContextBuilder frameworkName(String frameworkName) {
        Intrinsics.checkNotNullParameter(frameworkName, "frameworkName");
        this.d = frameworkName;
        return this;
    }

    public final DataCaptureContextBuilder frameworkVersion(String frameworkVersion) {
        Intrinsics.checkNotNullParameter(frameworkVersion, "frameworkVersion");
        this.e = frameworkVersion;
        return this;
    }

    public final DataCaptureContextBuilder settings(DataCaptureContextSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.f = settings;
        return this;
    }
}
