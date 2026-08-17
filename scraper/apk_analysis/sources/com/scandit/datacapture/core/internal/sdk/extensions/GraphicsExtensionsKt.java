package com.scandit.datacapture.core.internal.sdk.extensions;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.geometry.Size2;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003\u001a\u001a\u0010\u0007\u001a\u00020\b*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¨\u0006\f"}, d2 = {"createSdcRect", "Lcom/scandit/datacapture/core/common/geometry/Rect;", RRWebVideoEvent.JsonKeys.LEFT, "", RRWebVideoEvent.JsonKeys.TOP, "right", "bottom", "toGraphicRect", "Landroid/graphics/Rect;", "min", "", CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.SERIALIZED_NAME_MAX, "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GraphicsExtensionsKt {
    public static final Rect createSdcRect(float f, float f2, float f3, float f4) {
        return new Rect(new Point(f, f2), new Size2(f3 - f, f4 - f2));
    }

    public static final android.graphics.Rect toGraphicRect(Rect rect, int i, int i2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        int i3 = i2 - i;
        float f = i;
        float f2 = i3;
        android.graphics.Rect rect2 = new android.graphics.Rect(MathKt.roundToInt((rect.getOrigin().getX() * f2) + f), MathKt.roundToInt((rect.getOrigin().getY() * f2) + f), MathKt.roundToInt((rect.getSize().getWidth() * f2) + (rect.getOrigin().getX() * f2) + f), MathKt.roundToInt((rect.getSize().getHeight() * f2) + (rect.getOrigin().getY() * f2) + f));
        rect2.sort();
        return rect2;
    }
}
