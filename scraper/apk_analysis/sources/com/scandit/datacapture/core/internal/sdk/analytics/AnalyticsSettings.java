package com.scandit.datacapture.core.internal.sdk.analytics;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003H\u0097\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/analytics/AnalyticsSettings;", "Lcom/scandit/datacapture/core/internal/sdk/analytics/AnalyticsSettingsProxy;", "impl", "Lcom/scandit/datacapture/core/internal/sdk/analytics/NativeAnalyticsSettings;", "(Lcom/scandit/datacapture/core/internal/sdk/analytics/NativeAnalyticsSettings;)V", "_impl", "setProperty", "", "key", "", "value", "", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AnalyticsSettings implements AnalyticsSettingsProxy {
    private final /* synthetic */ AnalyticsSettingsProxyAdapter a;

    public AnalyticsSettings(NativeAnalyticsSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new AnalyticsSettingsProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.analytics.AnalyticsSettingsProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeAnalyticsSettings getA() {
        return this.a.getA();
    }

    public final void setProperty(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (value == null) {
            getA().setStringProperty(key, null);
        } else if (value instanceof Boolean) {
            getA().setBoolProperty(key, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
            getA().setStringProperty(key, (String) value);
        }
    }
}
