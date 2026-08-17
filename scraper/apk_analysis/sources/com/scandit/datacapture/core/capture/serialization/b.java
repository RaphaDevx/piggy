package com.scandit.datacapture.core.capture.serialization;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextBuilder;
import com.scandit.datacapture.core.capture.DataCaptureContextSettings;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements DataCaptureContextDeserializerHelper, DataCaptureDeserializerHelper {
    public DataCaptureContext a;

    public b(FrameSourceDeserializer fsDeserializer) {
        Intrinsics.checkNotNullParameter(fsDeserializer, "fsDeserializer");
        new WeakReference(fsDeserializer);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper
    public final void clear() {
        this.a = null;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerHelper
    public final DataCaptureContext createContext(String licenseKey, String deviceName, String externalId, String frameworkName, String str, DataCaptureContextSettings settings, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(externalId, "externalId");
        Intrinsics.checkNotNullParameter(frameworkName, "frameworkName");
        Intrinsics.checkNotNullParameter(settings, "settings");
        DataCaptureContextBuilder frameworkName2 = DataCaptureContext.INSTANCE.builder(licenseKey).deviceName(deviceName).externalId(externalId).frameworkName(frameworkName);
        if (str != null) {
            frameworkName2.frameworkVersion(str);
        }
        DataCaptureContext build = frameworkName2.build();
        this.a = build;
        return build;
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerHelper
    public final void removeModeFromContext(DataCaptureContext dataCaptureContext, DataCaptureMode mode) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(mode, "mode");
        dataCaptureContext.removeMode(mode);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerHelper
    public final void setFrameSourceOnContext(DataCaptureContext context, FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(context, "context");
        DataCaptureContext.setFrameSource$default(context, frameSource, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerHelper
    public final void updateContextFromJson(DataCaptureContext dataCaptureContext, JsonValue json) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(json, "json");
    }
}
