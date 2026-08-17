package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.source.NativeNoiseReduction;
import com.scandit.datacapture.core.internal.module.source.NativePreferredFrameRateRange;
import kotlin.Metadata;

/* compiled from: CameraSettingsPropertiesDefaults.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0002"}, d2 = {"toJson", "", "scandit-datacapture-frameworks-core_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CameraSettingsPropertiesDefaultsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object toJson(Object obj) {
        if (obj instanceof NativeNoiseReduction) {
            if (obj == NativeNoiseReduction.FAST) {
                return "fast";
            }
            if (obj == NativeNoiseReduction.HIGH_QUALITY) {
                return "highQuality";
            }
            if (obj == NativeNoiseReduction.OFF) {
                return "off";
            }
        }
        if (!(obj instanceof NativePreferredFrameRateRange)) {
            return obj;
        }
        if (obj == NativePreferredFrameRateRange.HIGHEST_NARROWEST) {
            return "highestNarrowest";
        }
        return obj == NativePreferredFrameRateRange.HIGHEST_WIDEST ? "highestWidest" : obj;
    }
}
