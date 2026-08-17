package com.scandit.datacapture.core.ui.serialization;

import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements DataCaptureViewDeserializerListener {
    public final WeakReference a;

    public a(DataCaptureViewDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerListener
    public final void onViewDeserializationFinished(DataCaptureViewDeserializer deserializer, DataCaptureView view, JsonValue json) {
        DataCaptureViewDeserializerListener dataCaptureViewDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureViewDeserializer dataCaptureViewDeserializer = (DataCaptureViewDeserializer) this.a.get();
        if (dataCaptureViewDeserializer == null || (dataCaptureViewDeserializerListener = dataCaptureViewDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        dataCaptureViewDeserializerListener.onViewDeserializationFinished(deserializer, view, json);
    }

    @Override // com.scandit.datacapture.core.ui.serialization.DataCaptureViewDeserializerListener
    public final void onViewDeserializationStarted(DataCaptureViewDeserializer deserializer, DataCaptureView view, JsonValue json) {
        DataCaptureViewDeserializerListener dataCaptureViewDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(json, "json");
        DataCaptureViewDeserializer dataCaptureViewDeserializer = (DataCaptureViewDeserializer) this.a.get();
        if (dataCaptureViewDeserializer == null || (dataCaptureViewDeserializerListener = dataCaptureViewDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        dataCaptureViewDeserializerListener.onViewDeserializationStarted(deserializer, view, json);
    }
}
