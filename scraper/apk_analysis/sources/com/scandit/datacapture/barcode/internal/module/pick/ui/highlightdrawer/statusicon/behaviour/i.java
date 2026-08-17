package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconSettings;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class i implements j {
    public final BarcodePickStatusIconSettings a;

    public i(BarcodePickStatusIconSettings statusIconSettings) {
        Intrinsics.checkNotNullParameter(statusIconSettings, "statusIconSettings");
        this.a = statusIconSettings;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j
    public final int a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.c viewHolder, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a drawData) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        float height = QuadrilateralUtilsKt.getHeight(drawData.b);
        float width = QuadrilateralUtilsKt.getWidth(drawData.b);
        int max = Math.max(0, this.a.getMinSize());
        int max2 = Math.max(max, this.a.getMaxSize());
        return RangesKt.coerceIn((int) (this.a.getRatioToHighlightSize() * Math.min(height, width)), PixelExtensionsKt.pxFromDp$default(max, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(max2, (Context) null, 1, (Object) null));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j
    public final a b(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.c viewHolder, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a drawData) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        Iterator it = CollectionsKt.listOf((Object[]) new Point[]{drawData.b.getTopLeft(), drawData.b.getTopRight(), drawData.b.getBottomRight(), drawData.b.getBottomLeft()}).iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            Point point = (Point) next;
            float x = point.getX() - point.getY();
            do {
                Object next2 = it.next();
                Point point2 = (Point) next2;
                float x2 = point2.getX() - point2.getY();
                if (Float.compare(x, x2) < 0) {
                    next = next2;
                    x = x2;
                }
            } while (it.hasNext());
        }
        Point point3 = (Point) next;
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h hVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h) viewHolder;
        int i = hVar.i / 2;
        int x3 = ((int) point3.getX()) - i;
        int y = ((int) point3.getY()) - i;
        ViewParent parent = hVar.b.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        int width = ((viewGroup != null ? viewGroup.getWidth() : 0) - x3) - (hVar.i + hVar.j);
        if (width < 0 && (x3 = x3 + width) < 0) {
            x3 = 0;
        }
        return new a(y, x3, false, 0);
    }
}
