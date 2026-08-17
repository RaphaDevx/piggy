package com.scandit.datacapture.frameworks.barcode.batch.mode;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.batch.data.BarcodeBatchModeCreationData;
import com.scandit.datacapture.frameworks.barcode.batch.data.FrameworksBarcodeBatchSession;
import com.scandit.datacapture.frameworks.barcode.batch.listeners.FrameworksBarcodeBatchListener;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeBatchMode.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u001fJ\u0018\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020\u001fH\u0016J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u000eJ\u0006\u0010,\u001a\u00020\u001fJ\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\"R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00060"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/mode/FrameworksBarcodeBatchMode;", "Lcom/scandit/datacapture/frameworks/core/mode/FrameworksBaseMode;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchListener;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializer", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializer;", "(Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchListener;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializer;)V", "_modeId", "", "_parentId", "Ljava/lang/Integer;", "value", "", "isEnabled", "()Z", "setEnabled", "(Z)V", "<set-?>", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;", "mode", "getMode", "()Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;", ExtentionsKt.MODE_ID_KEY, "getModeId", "()I", "parentId", "getParentId", "()Ljava/lang/Integer;", "addListener", "", "applySettings", "modeSettingsJson", "", "cancelPendingEvents", "deserializeMode", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/BarcodeBatchModeCreationData;", "dispose", "finishDidUpdateSession", ExtentionsKt.ENABLED_KEY, "removeListener", "updateModeFromJson", "modeJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeBatchMode implements FrameworksBaseMode {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _modeId;
    private Integer _parentId;
    private final FrameworksCaptureContext captureContext;
    private final BarcodeBatchDeserializer deserializer;
    private final FrameworksBarcodeBatchListener listener;
    private BarcodeBatch mode;

    public FrameworksBarcodeBatchMode(FrameworksBarcodeBatchListener listener, FrameworksCaptureContext captureContext, BarcodeBatchDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.listener = listener;
        this.captureContext = captureContext;
        this.deserializer = deserializer;
        this._modeId = -1;
    }

    public /* synthetic */ FrameworksBarcodeBatchMode(FrameworksBarcodeBatchListener frameworksBarcodeBatchListener, FrameworksCaptureContext frameworksCaptureContext, BarcodeBatchDeserializer barcodeBatchDeserializer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodeBatchListener, frameworksCaptureContext, (i & 4) != 0 ? new BarcodeBatchDeserializer() : barcodeBatchDeserializer);
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

    public final BarcodeBatch getMode() {
        BarcodeBatch barcodeBatch = this.mode;
        if (barcodeBatch != null) {
            return barcodeBatch;
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
    public final void deserializeMode(DataCaptureContext dataCaptureContext, BarcodeBatchModeCreationData creationData) {
        this.mode = this.deserializer.modeFromJson(dataCaptureContext, creationData.getModeJson());
        this._modeId = creationData.getModeId();
        this._parentId = Integer.valueOf(creationData.getParentId());
        this.captureContext.addMode(getMode());
        getMode().addListener(this.listener);
        this.listener.setEnabled(creationData.getHasListener());
        getMode().setEnabled(creationData.getIsEnabled());
    }

    @Override // com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode
    public void dispose() {
        this.listener.reset();
        getMode().removeListener(this.listener);
        this.captureContext.removeMode(getMode());
    }

    public final void addListener() {
        this.listener.setEnabled(true);
    }

    public final void removeListener() {
        this.listener.setEnabled(false);
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.listener.finishDidUpdateSession(enabled);
    }

    public final void applySettings(String modeSettingsJson) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        BarcodeBatch.applySettings$default(getMode(), this.deserializer.settingsFromJson(modeSettingsJson), null, 2, null);
    }

    public final void updateModeFromJson(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        this.deserializer.updateModeFromJson(getMode(), modeJson);
    }

    public final void cancelPendingEvents() {
        this.listener.reset();
    }

    /* compiled from: FrameworksBarcodeBatchMode.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/mode/FrameworksBarcodeBatchMode$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/batch/mode/FrameworksBarcodeBatchMode;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "creationData", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/BarcodeBatchModeCreationData;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "cachedBatchSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeBatchMode create(Emitter emitter, FrameworksCaptureContext captureContext, BarcodeBatchModeCreationData creationData, DataCaptureContext dataCaptureContext, AtomicReference<FrameworksBarcodeBatchSession> cachedBatchSession) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(captureContext, "captureContext");
            Intrinsics.checkNotNullParameter(creationData, "creationData");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(cachedBatchSession, "cachedBatchSession");
            BarcodeBatchDeserializer barcodeBatchDeserializer = null;
            FrameworksBarcodeBatchMode frameworksBarcodeBatchMode = new FrameworksBarcodeBatchMode(new FrameworksBarcodeBatchListener(emitter, creationData.getModeId(), cachedBatchSession, null, null, 24, null), captureContext, barcodeBatchDeserializer, 4, null);
            frameworksBarcodeBatchMode.deserializeMode(dataCaptureContext, creationData);
            return frameworksBarcodeBatchMode;
        }
    }
}
