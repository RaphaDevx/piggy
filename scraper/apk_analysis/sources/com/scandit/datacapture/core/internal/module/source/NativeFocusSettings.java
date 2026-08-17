package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.source.FocusGestureStrategy;

/* loaded from: classes2.dex */
public final class NativeFocusSettings {
    final FocusGestureStrategy focusGestureStrategy;
    final Point manualFocusPoint;
    final float manualLensPosition;
    final NativeJsonValue properties;
    final NativeFocusStrategy selectedFocusStrategy;
    final boolean shouldPreferSmoothAutoFocus;

    public NativeFocusSettings(float f, NativeFocusStrategy nativeFocusStrategy, boolean z, NativeJsonValue nativeJsonValue, Point point, FocusGestureStrategy focusGestureStrategy) {
        this.manualLensPosition = f;
        this.selectedFocusStrategy = nativeFocusStrategy;
        this.shouldPreferSmoothAutoFocus = z;
        this.properties = nativeJsonValue;
        this.manualFocusPoint = point;
        this.focusGestureStrategy = focusGestureStrategy;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeFocusSettings)) {
            return false;
        }
        NativeFocusSettings nativeFocusSettings = (NativeFocusSettings) obj;
        if (this.manualLensPosition != nativeFocusSettings.manualLensPosition || this.selectedFocusStrategy != nativeFocusSettings.selectedFocusStrategy || this.shouldPreferSmoothAutoFocus != nativeFocusSettings.shouldPreferSmoothAutoFocus) {
            return false;
        }
        NativeJsonValue nativeJsonValue = this.properties;
        if (!(nativeJsonValue == null && nativeFocusSettings.properties == null) && (nativeJsonValue == null || !nativeJsonValue.equals(nativeFocusSettings.properties))) {
            return false;
        }
        Point point = this.manualFocusPoint;
        return ((point == null && nativeFocusSettings.manualFocusPoint == null) || (point != null && point.equals(nativeFocusSettings.manualFocusPoint))) && this.focusGestureStrategy == nativeFocusSettings.focusGestureStrategy;
    }

    public FocusGestureStrategy getFocusGestureStrategy() {
        return this.focusGestureStrategy;
    }

    public Point getManualFocusPoint() {
        return this.manualFocusPoint;
    }

    public float getManualLensPosition() {
        return this.manualLensPosition;
    }

    public NativeJsonValue getProperties() {
        return this.properties;
    }

    public NativeFocusStrategy getSelectedFocusStrategy() {
        return this.selectedFocusStrategy;
    }

    public boolean getShouldPreferSmoothAutoFocus() {
        return this.shouldPreferSmoothAutoFocus;
    }

    public int hashCode() {
        int hashCode = (((this.selectedFocusStrategy.hashCode() + ((Float.floatToIntBits(this.manualLensPosition) + 527) * 31)) * 31) + (this.shouldPreferSmoothAutoFocus ? 1 : 0)) * 31;
        NativeJsonValue nativeJsonValue = this.properties;
        int hashCode2 = (hashCode + (nativeJsonValue == null ? 0 : nativeJsonValue.hashCode())) * 31;
        Point point = this.manualFocusPoint;
        return this.focusGestureStrategy.hashCode() + ((hashCode2 + (point != null ? point.hashCode() : 0)) * 31);
    }

    public String toString() {
        return "NativeFocusSettings{manualLensPosition=" + this.manualLensPosition + ",selectedFocusStrategy=" + this.selectedFocusStrategy + ",shouldPreferSmoothAutoFocus=" + this.shouldPreferSmoothAutoFocus + ",properties=" + this.properties + ",manualFocusPoint=" + this.manualFocusPoint + ",focusGestureStrategy=" + this.focusGestureStrategy + "}";
    }
}
