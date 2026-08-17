package com.scandit.datacapture.core.ui.control;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.ui.control.LinearControlGroup;
import java.util.List;
import kotlin.collections.CollectionsKt;

/* loaded from: classes2.dex */
public final class h implements LinearControlGroup.Defaults {
    public static final h a = new h();
    public static final LinearControlGroupOrientation b = LinearControlGroupOrientation.VERTICAL;
    public static final FloatWithUnit c = new FloatWithUnit(16.0f, MeasureUnit.DIP);
    public static final List d = CollectionsKt.emptyList();

    @Override // com.scandit.datacapture.core.ui.control.LinearControlGroup.Defaults
    public final List getControls() {
        return d;
    }

    @Override // com.scandit.datacapture.core.ui.control.LinearControlGroup.Defaults
    public final LinearControlGroupOrientation getOrientation() {
        return b;
    }

    @Override // com.scandit.datacapture.core.ui.control.LinearControlGroup.Defaults
    public final FloatWithUnit getSpacing() {
        return c;
    }
}
