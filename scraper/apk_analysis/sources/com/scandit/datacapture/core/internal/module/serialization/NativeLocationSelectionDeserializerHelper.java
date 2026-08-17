package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.sdk.area.NativeNoLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRadiusLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection;

/* loaded from: classes2.dex */
public abstract class NativeLocationSelectionDeserializerHelper {
    public abstract NativeNoLocationSelection createNoLocationSelection();

    public abstract NativeRadiusLocationSelection createRadiusLocationSelection();

    public abstract NativeRectangularLocationSelection createRectangularLocationSelection();
}
