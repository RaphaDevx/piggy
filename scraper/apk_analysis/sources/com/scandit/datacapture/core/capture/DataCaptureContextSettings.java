package com.scandit.datacapture.core.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.analytics.AnalyticsSettings;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0004H\u0097\u0001J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\b¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/core/capture/DataCaptureContextSettings;", "Lcom/scandit/datacapture/core/capture/DataCaptureContextSettingsProxy;", "()V", "impl", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContextSettings;", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContextSettings;)V", "_impl", "getProperty", "", "key", "", "setProperty", "", "name", "value", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataCaptureContextSettings implements DataCaptureContextSettingsProxy {
    private final /* synthetic */ DataCaptureContextSettingsProxyAdapter a;

    public DataCaptureContextSettings(NativeDataCaptureContextSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new DataCaptureContextSettingsProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeDataCaptureContextSettings getA() {
        return this.a.getA();
    }

    public final Object getProperty(String key) {
        Object analyticsSettings;
        Intrinsics.checkNotNullParameter(key, "key");
        int hashCode = key.hashCode();
        if (hashCode == -1988438583) {
            if (key.equals("analyticsSettings")) {
                analyticsSettings = getA().getAnalyticsSettings();
            }
            analyticsSettings = -1;
        } else if (hashCode != -1918698521) {
            if (hashCode == -1785958911 && key.equals("enabledCpusBitset")) {
                analyticsSettings = getA().getEnabledCpusBitset();
            }
            analyticsSettings = -1;
        } else {
            if (key.equals("numberOfEngineThreads")) {
                analyticsSettings = Integer.valueOf(getA().getNumberOfEngineThreads());
            }
            analyticsSettings = -1;
        }
        Intrinsics.checkNotNull(analyticsSettings);
        return analyticsSettings;
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(name, "analyticsSettings") && (value instanceof AnalyticsSettings)) {
            getA().setAnalyticsSettings(((AnalyticsSettings) value).getA());
            return;
        }
        if (Intrinsics.areEqual(name, "numberOfEngineThreads") && (value instanceof Integer)) {
            getA().setNumberOfEngineThreads(((Number) value).intValue());
            return;
        }
        if (Intrinsics.areEqual(name, "enabledCpusBitset") && (value instanceof String)) {
            getA().setEnabledCpusBitset((String) value);
            return;
        }
        if (value instanceof Boolean) {
            getA().setBoolProperty(name, ((Boolean) value).booleanValue());
            return;
        }
        if (value instanceof Integer) {
            getA().setIntProperty(name, ((Number) value).intValue());
        } else if (value instanceof Float) {
            getA().setFloatProperty(name, ((Number) value).floatValue());
        } else if (value instanceof String) {
            getA().setStringProperty(name, (String) value);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DataCaptureContextSettings() {
        /*
            r2 = this;
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings r0 = com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings.create()
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.capture.DataCaptureContextSettings.<init>():void");
    }
}
