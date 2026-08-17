package com.scandit.datacapture.core.capture.serialization;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements DataCaptureContextDeserializerListener {
    public final WeakReference a;

    public a(DataCaptureContextDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerListener
    public final void onContextDeserializationFinished(DataCaptureContextDeserializer deserializer, DataCaptureContext dataCaptureContext, JsonValue json) {
        DataCaptureContextDeserializerListener dataCaptureContextDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureContextDeserializer dataCaptureContextDeserializer = (DataCaptureContextDeserializer) this.a.get();
        if (dataCaptureContextDeserializer == null || (dataCaptureContextDeserializerListener = dataCaptureContextDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        dataCaptureContextDeserializerListener.onContextDeserializationFinished(deserializer, dataCaptureContext, json);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureContextDeserializerListener
    public final void onContextDeserializationStarted(DataCaptureContextDeserializer deserializer, DataCaptureContext dataCaptureContext, JsonValue json) {
        DataCaptureContextDeserializerListener dataCaptureContextDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureContextDeserializer dataCaptureContextDeserializer = (DataCaptureContextDeserializer) this.a.get();
        if (dataCaptureContextDeserializer == null || (dataCaptureContextDeserializerListener = dataCaptureContextDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        dataCaptureContextDeserializerListener.onContextDeserializationStarted(deserializer, dataCaptureContext, json);
    }
}
