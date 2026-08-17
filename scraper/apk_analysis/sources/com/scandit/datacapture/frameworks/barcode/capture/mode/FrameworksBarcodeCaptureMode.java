package com.scandit.datacapture.frameworks.barcode.capture.mode;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.capture.BarcodeCapture;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializer;
import com.scandit.datacapture.barcode.feedback.BarcodeCaptureFeedback;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.capture.data.BarcodeCaptureModeCreationData;
import com.scandit.datacapture.frameworks.barcode.capture.listeners.FrameworksBarcodeCaptureListener;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode;
import io.sentry.protocol.Feedback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCaptureMode.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 32\u00020\u0001:\u00013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u001fJ\u0018\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020\u001fH\u0016J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u000eJ\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u000eJ\u0006\u0010-\u001a\u00020\u001fJ\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\"R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/mode/FrameworksBarcodeCaptureMode;", "Lcom/scandit/datacapture/frameworks/core/mode/FrameworksBaseMode;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/frameworks/barcode/capture/listeners/FrameworksBarcodeCaptureListener;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializer", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "(Lcom/scandit/datacapture/frameworks/barcode/capture/listeners/FrameworksBarcodeCaptureListener;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;)V", "_modeId", "", "_parentId", "Ljava/lang/Integer;", "value", "", "isEnabled", "()Z", "setEnabled", "(Z)V", "<set-?>", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "mode", "getMode", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", ExtentionsKt.MODE_ID_KEY, "getModeId", "()I", "parentId", "getParentId", "()Ljava/lang/Integer;", "addListener", "", "applySettings", "modeSettingsJson", "", "cancelPendingEvents", "deserializeMode", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureModeCreationData;", "dispose", "finishDidScan", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSession", "removeListener", "updateFeedback", Feedback.TYPE, "Lcom/scandit/datacapture/barcode/feedback/BarcodeCaptureFeedback;", "updateModeFromJson", "modeJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeCaptureMode implements FrameworksBaseMode {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _modeId;
    private Integer _parentId;
    private final FrameworksCaptureContext captureContext;
    private final BarcodeCaptureDeserializer deserializer;
    private final FrameworksBarcodeCaptureListener listener;
    private BarcodeCapture mode;

    public FrameworksBarcodeCaptureMode(FrameworksBarcodeCaptureListener listener, FrameworksCaptureContext captureContext, BarcodeCaptureDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.listener = listener;
        this.captureContext = captureContext;
        this.deserializer = deserializer;
        this._modeId = -1;
    }

    @Override // com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode
    /* renamed from: getModeId, reason: from getter */
    public int get_modeId() {
        return this._modeId;
    }

    @Override // com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode
    /* renamed from: getParentId, reason: from getter */
    public Integer get_parentId() {
        return this._parentId;
    }

    public final BarcodeCapture getMode() {
        BarcodeCapture barcodeCapture = this.mode;
        if (barcodeCapture != null) {
            return barcodeCapture;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mode");
        return null;
    }

    public final boolean isEnabled() {
        return getMode().isEnabled();
    }

    public final void setEnabled(boolean z) {
        getMode().setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeMode(DataCaptureContext dataCaptureContext, BarcodeCaptureModeCreationData creationData) {
        this.mode = this.deserializer.modeFromJson(dataCaptureContext, creationData.getModeJson());
        this._modeId = creationData.getModeId();
        this._parentId = Integer.valueOf(creationData.getParentId());
        this.captureContext.addMode(getMode());
        if (creationData.getHasListener()) {
            getMode().addListener(this.listener);
        }
        getMode().setEnabled(creationData.getIsEnabled());
    }

    @Override // com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode
    public void dispose() {
        this.listener.reset();
        getMode().removeListener(this.listener);
        this.captureContext.removeMode(getMode());
    }

    public final void addListener() {
        getMode().addListener(this.listener);
    }

    public final void removeListener() {
        getMode().removeListener(this.listener);
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.listener.finishDidUpdateSession(enabled);
    }

    public final void finishDidScan(boolean enabled) {
        this.listener.finishDidScan(enabled);
    }

    public final void applySettings(String modeSettingsJson) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        BarcodeCapture.applySettings$default(getMode(), this.deserializer.settingsFromJson(modeSettingsJson), null, 2, null);
    }

    public final void updateModeFromJson(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        this.deserializer.updateModeFromJson(getMode(), modeJson);
    }

    public final void updateFeedback(BarcodeCaptureFeedback feedback) {
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        getMode().setFeedback(feedback);
    }

    public final void cancelPendingEvents() {
        this.listener.reset();
    }

    /* compiled from: FrameworksBarcodeCaptureMode.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/mode/FrameworksBarcodeCaptureMode$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/capture/mode/FrameworksBarcodeCaptureMode;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureModeCreationData;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "deserializer", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeCaptureMode create(Emitter emitter, FrameworksCaptureContext captureContext, BarcodeCaptureModeCreationData creationData, DataCaptureContext dataCaptureContext, BarcodeCaptureDeserializer deserializer) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(captureContext, "captureContext");
            Intrinsics.checkNotNullParameter(creationData, "creationData");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            FrameworksBarcodeCaptureMode frameworksBarcodeCaptureMode = new FrameworksBarcodeCaptureMode(new FrameworksBarcodeCaptureListener(emitter, creationData.getModeId(), null, null, null, null, 60, null), captureContext, deserializer);
            frameworksBarcodeCaptureMode.deserializeMode(dataCaptureContext, creationData);
            return frameworksBarcodeCaptureMode;
        }
    }
}
