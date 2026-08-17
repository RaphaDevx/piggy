package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.utils;

import com.scandit.datacapture.core.common.geometry.Anchor;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

/* loaded from: classes2.dex */
public abstract class a {
    public static final Map a;

    static {
        Anchor anchor = Anchor.TOP_LEFT;
        Anchor anchor2 = Anchor.BOTTOM_LEFT;
        a = MapsKt.mapOf(TuplesKt.to(anchor, anchor2), TuplesKt.to(Anchor.TOP_CENTER, Anchor.CENTER_LEFT), TuplesKt.to(Anchor.TOP_RIGHT, anchor), TuplesKt.to(anchor2, Anchor.BOTTOM_RIGHT));
    }
}
