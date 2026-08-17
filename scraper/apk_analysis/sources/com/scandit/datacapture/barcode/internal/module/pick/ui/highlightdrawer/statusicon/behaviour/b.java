package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements j {
    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j
    public final int a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.c viewHolder, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a drawData) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        return ((Number) h.e.getValue()).intValue();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j
    public final a b(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.c viewHolder, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a drawData) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        Point center = QuadrilateralUtilsKt.getCenter(drawData.b);
        int y = (int) center.getY();
        int x = (int) center.getX();
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h hVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h) viewHolder;
        int i = hVar.i + hVar.j;
        int height = y - ((int) (((QuadrilateralUtilsKt.getHeight(drawData.b) / 2) + ((Number) h.a.getValue()).intValue()) + ((Number) h.b.getValue()).intValue()));
        int i2 = x - (hVar.i / 2);
        ViewParent parent = hVar.b.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        int width = ((viewGroup != null ? viewGroup.getWidth() : 0) - i2) - i;
        if (i2 < 0) {
            i2 = 0;
        } else if (width < 0) {
            i2 += width;
        }
        return new a(height, i2, true, (int) ((center.getX() - i2) - (((Number) h.c.getValue()).intValue() / 2)));
    }
}
