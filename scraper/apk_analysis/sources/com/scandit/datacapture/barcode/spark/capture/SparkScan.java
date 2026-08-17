package com.scandit.datacapture.barcode.spark.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanEnableDisableListener;
import com.scandit.datacapture.barcode.internal.module.spark.capture.SparkScanModeViewListener;
import com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal;
import com.scandit.datacapture.barcode.internal.module.spark.internal.j;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanDeserializer;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.analytics.NativePropertyPushSource;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 U2\u00020\u0001:\u0001UB\u0019\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\tB\t\b\u0016¢\u0006\u0004\b\u0006\u0010\nJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0015\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\tJ\u000f\u0010\u0018\u001a\u00020\rH\u0000¢\u0006\u0004\b\u0017\u0010\nJ\u0017\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ\u000f\u0010!\u001a\u00020\rH\u0000¢\u0006\u0004\b \u0010\nJ\u000f\u0010#\u001a\u00020\rH\u0000¢\u0006\u0004\b\"\u0010\nJ\u0017\u0010%\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b$\u0010\u000fJ\u0017\u0010*\u001a\u00020\r2\u0006\u0010'\u001a\u00020&H\u0000¢\u0006\u0004\b(\u0010)J\u000f\u0010,\u001a\u00020\rH\u0000¢\u0006\u0004\b+\u0010\nJ\u0015\u0010.\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020-¢\u0006\u0004\b.\u0010/J\u0015\u00100\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020-¢\u0006\u0004\b0\u0010/R\"\u0010\u0005\u001a\u00020\u00048\u0001@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u0010\tR.\u0010>\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u0001068\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010C\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR.\u0010K\u001a\u0004\u0018\u00010D2\b\u00107\u001a\u0004\u0018\u00010D8\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0013\u0010O\u001a\u0004\u0018\u00010L8F¢\u0006\u0006\u001a\u0004\bM\u0010NR$\u0010P\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010\u000fR\u0014\u0010T\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bS\u0010Q¨\u0006V"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "", "Lcom/scandit/datacapture/barcode/internal/module/spark/internal/SparkScanInternal;", "sparkScanInternal", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "sparkScanSettings", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/spark/internal/SparkScanInternal;Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;)V", "settings", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;)V", "()V", "", ExtentionsKt.ENABLED_KEY, "", "setSingleScanModeEnabled$scandit_barcode_capture", "(Z)V", "setSingleScanModeEnabled", "Lcom/scandit/datacapture/core/internal/sdk/analytics/NativePropertyPushSource;", "propertyPushSource", "setPropertyPushSource$scandit_barcode_capture", "(Lcom/scandit/datacapture/core/internal/sdk/analytics/NativePropertyPushSource;)V", "setPropertyPushSource", "applySettings", "_applySettings$scandit_barcode_capture", "_applySettings", "Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanEnableDisableListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "_addEnableDisableListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanEnableDisableListener;)V", "_addEnableDisableListener", "_removeEnableDisableListener$scandit_barcode_capture", "_removeEnableDisableListener", "_disable$scandit_barcode_capture", "_disable", "_enable$scandit_barcode_capture", "_enable", "_setResultSuppressionEnabled$scandit_barcode_capture", "_setResultSuppressionEnabled", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanScanningMode;", "scanningMode", "_applyNewSettingsForScanningMode$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/spark/ui/SparkScanScanningMode;)V", "_applyNewSettingsForScanningMode", "_selectBarcodeWithSmartScanSelection$scandit_barcode_capture", "_selectBarcodeWithSmartScanSelection", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanListener;", "addListener", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScanListener;)V", "removeListener", "a", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "getSparkScanSettings$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "setSparkScanSettings$scandit_barcode_capture", "Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanModeViewListener;", "value", "b", "Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanModeViewListener;", "get_sparkScanModeViewListener$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanModeViewListener;", "set_sparkScanModeViewListener$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/spark/capture/SparkScanModeViewListener;)V", "_sparkScanModeViewListener", "e", "Lcom/scandit/datacapture/barcode/internal/module/spark/internal/SparkScanInternal;", "get_sparkScanInternal$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/spark/internal/SparkScanInternal;", "_sparkScanInternal", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "f", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getDataCaptureContext$scandit_barcode_capture", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "setDataCaptureContext$scandit_barcode_capture", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanLicenseInfo;", "getSparkScanLicenseInfo", "()Lcom/scandit/datacapture/barcode/spark/capture/SparkScanLicenseInfo;", "sparkScanLicenseInfo", "isEnabled", "()Z", "setEnabled", "isSuppressed$scandit_barcode_capture", "isSuppressed", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class SparkScan {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: a, reason: from kotlin metadata */
    private SparkScanSettings sparkScanSettings;

    /* renamed from: b, reason: from kotlin metadata */
    private /* synthetic */ SparkScanModeViewListener _sparkScanModeViewListener;
    private final CopyOnWriteArrayList c;
    private final CopyOnWriteArraySet d;

    /* renamed from: e, reason: from kotlin metadata */
    private final SparkScanInternal _sparkScanInternal;

    /* renamed from: f, reason: from kotlin metadata */
    private DataCaptureContext dataCaptureContext;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/capture/SparkScan$Companion;", "", "", "jsonData", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final SparkScan fromJson(String jsonData) {
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new SparkScanDeserializer().modeFromJson(jsonData);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SparkScan(SparkScanSettings settings) {
        this(new SparkScanInternal(settings), settings);
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(settings, "settings");
    }

    @JvmStatic
    public static final SparkScan fromJson(String str) {
        return INSTANCE.fromJson(str);
    }

    public final void _addEnableDisableListener$scandit_barcode_capture(SparkScanEnableDisableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.add(listener);
    }

    public final void _applyNewSettingsForScanningMode$scandit_barcode_capture(SparkScanScanningMode scanningMode) {
        Intrinsics.checkNotNullParameter(scanningMode, "scanningMode");
        this.sparkScanSettings._applySettingsForScanningMode$scandit_barcode_capture(scanningMode);
        _applySettings$scandit_barcode_capture();
    }

    public final void _applySettings$scandit_barcode_capture() {
        SparkScanInternal.a(this._sparkScanInternal, this.sparkScanSettings);
    }

    public final void _disable$scandit_barcode_capture() {
        if (this._sparkScanInternal.isEnabled() || isSuppressed$scandit_barcode_capture()) {
            this._sparkScanInternal.setEnabled(false);
            DataCaptureContext dataCaptureContext = this.dataCaptureContext;
            if (dataCaptureContext != null) {
                dataCaptureContext.removeMode(this._sparkScanInternal);
            }
        }
    }

    public final void _enable$scandit_barcode_capture() {
        if (isSuppressed$scandit_barcode_capture()) {
            _setResultSuppressionEnabled$scandit_barcode_capture(false);
        } else {
            if (this._sparkScanInternal.isEnabled()) {
                return;
            }
            DataCaptureContext dataCaptureContext = this.dataCaptureContext;
            if (dataCaptureContext != null) {
                dataCaptureContext.setMode(this._sparkScanInternal);
            }
            this._sparkScanInternal.setEnabled(true);
        }
    }

    public final void _removeEnableDisableListener$scandit_barcode_capture(SparkScanEnableDisableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.remove(listener);
    }

    public final void _selectBarcodeWithSmartScanSelection$scandit_barcode_capture() {
        this._sparkScanInternal.d();
    }

    public final void _setResultSuppressionEnabled$scandit_barcode_capture(boolean enabled) {
        this._sparkScanInternal.a().setResultSuppressionEnabled(enabled);
    }

    public final void addListener(SparkScanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.add(listener);
    }

    public final void applySettings(SparkScanSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.sparkScanSettings = settings;
        SparkScanModeViewListener sparkScanModeViewListener = this._sparkScanModeViewListener;
        if (sparkScanModeViewListener == null) {
            _applySettings$scandit_barcode_capture();
        } else {
            sparkScanModeViewListener.onApplySettings(settings);
            _applyNewSettingsForScanningMode$scandit_barcode_capture(sparkScanModeViewListener.getScanningMode());
        }
    }

    /* renamed from: getDataCaptureContext$scandit_barcode_capture, reason: from getter */
    public final DataCaptureContext getDataCaptureContext() {
        return this.dataCaptureContext;
    }

    public final SparkScanLicenseInfo getSparkScanLicenseInfo() {
        return this._sparkScanInternal.c();
    }

    /* renamed from: getSparkScanSettings$scandit_barcode_capture, reason: from getter */
    public final SparkScanSettings getSparkScanSettings() {
        return this.sparkScanSettings;
    }

    /* renamed from: get_sparkScanInternal$scandit_barcode_capture, reason: from getter */
    public final SparkScanInternal get_sparkScanInternal() {
        return this._sparkScanInternal;
    }

    /* renamed from: get_sparkScanModeViewListener$scandit_barcode_capture, reason: from getter */
    public final SparkScanModeViewListener get_sparkScanModeViewListener() {
        return this._sparkScanModeViewListener;
    }

    public final boolean isEnabled() {
        return this._sparkScanInternal.isEnabled();
    }

    public final boolean isSuppressed$scandit_barcode_capture() {
        return this._sparkScanInternal.a().isSuppressed();
    }

    public final void removeListener(SparkScanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.remove(listener);
    }

    public final void setDataCaptureContext$scandit_barcode_capture(DataCaptureContext dataCaptureContext) {
        if (Intrinsics.areEqual(dataCaptureContext, this.dataCaptureContext)) {
            return;
        }
        DataCaptureContext dataCaptureContext2 = this.dataCaptureContext;
        if (dataCaptureContext2 != null) {
            dataCaptureContext2.removeMode(this._sparkScanInternal);
        }
        if (this._sparkScanInternal.isEnabled() && dataCaptureContext != null) {
            dataCaptureContext.setMode(this._sparkScanInternal);
        }
        this.dataCaptureContext = dataCaptureContext;
    }

    public final void setEnabled(boolean z) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((SparkScanEnableDisableListener) it.next()).onEnabledChange(z);
        }
    }

    public final void setPropertyPushSource$scandit_barcode_capture(NativePropertyPushSource propertyPushSource) {
        this._sparkScanInternal.a().setPropertyPushSource(propertyPushSource);
    }

    public final void setSingleScanModeEnabled$scandit_barcode_capture(boolean enabled) {
        this._sparkScanInternal.a(enabled);
    }

    public final void setSparkScanSettings$scandit_barcode_capture(SparkScanSettings sparkScanSettings) {
        Intrinsics.checkNotNullParameter(sparkScanSettings, "<set-?>");
        this.sparkScanSettings = sparkScanSettings;
    }

    public final void set_sparkScanModeViewListener$scandit_barcode_capture(SparkScanModeViewListener sparkScanModeViewListener) {
        this._sparkScanModeViewListener = sparkScanModeViewListener;
        if (sparkScanModeViewListener != null) {
            sparkScanModeViewListener.onApplySettings(this.sparkScanSettings);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.scandit.datacapture.barcode.spark.capture.SparkScan$sparkScanListenerInternal$1] */
    public SparkScan(SparkScanInternal sparkScanInternal, SparkScanSettings sparkScanSettings) {
        Intrinsics.checkNotNullParameter(sparkScanInternal, "sparkScanInternal");
        Intrinsics.checkNotNullParameter(sparkScanSettings, "sparkScanSettings");
        this.sparkScanSettings = sparkScanSettings;
        ?? r3 = new j() { // from class: com.scandit.datacapture.barcode.spark.capture.SparkScan$sparkScanListenerInternal$1
            @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
            public void onBarcodeScanned(SparkScanInternal sparkScan, SparkScanSession session, FrameData data) {
                Collection collection;
                Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
                Intrinsics.checkNotNullParameter(session, "session");
                Intrinsics.checkNotNullParameter(data, "data");
                collection = SparkScan.this.c;
                SparkScan sparkScan2 = SparkScan.this;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    ((SparkScanListener) it.next()).onBarcodeScanned(sparkScan2, session, data);
                }
            }

            @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
            @ProxyFunction
            public void onObservationStarted(SparkScanInternal sparkScan) {
                Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
            }

            @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
            @ProxyFunction
            public void onObservationStopped(SparkScanInternal sparkScan) {
                Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
            }

            @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
            public void onSessionUpdated(SparkScanInternal sparkScan, SparkScanSession session, FrameData data) {
                Collection collection;
                Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
                Intrinsics.checkNotNullParameter(session, "session");
                Intrinsics.checkNotNullParameter(data, "data");
                collection = SparkScan.this.c;
                SparkScan sparkScan2 = SparkScan.this;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    ((SparkScanListener) it.next()).onSessionUpdated(sparkScan2, session, data);
                }
            }
        };
        this.c = new CopyOnWriteArrayList();
        this.d = new CopyOnWriteArraySet();
        sparkScanInternal.setEnabled(false);
        sparkScanInternal.a((SparkScan$sparkScanListenerInternal$1) r3);
        this._sparkScanInternal = sparkScanInternal;
    }

    public SparkScan() {
        this(new SparkScanSettings());
    }
}
