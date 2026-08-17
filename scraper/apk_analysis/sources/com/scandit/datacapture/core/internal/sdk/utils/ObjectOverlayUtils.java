package com.scandit.datacapture.core.internal.sdk.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.PointWithUnitExtensionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/utils/ObjectOverlayUtils;", "", "<init>", "()V", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "objectPosition", "Lcom/scandit/datacapture/core/common/geometry/Size2;", "overlayViewSize", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "anchor", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", TypedValues.CycleType.S_WAVE_OFFSET, "Lcom/scandit/datacapture/core/common/geometry/Point;", "getObjectOverlayPosition", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Size2;Lcom/scandit/datacapture/core/common/geometry/Anchor;Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)Lcom/scandit/datacapture/core/common/geometry/Point;", "anchorPoint", "viewSize", "calculateViewTopLeft", "(Lcom/scandit/datacapture/core/common/geometry/Point;Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;Lcom/scandit/datacapture/core/common/geometry/Size2;)Lcom/scandit/datacapture/core/common/geometry/Point;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ObjectOverlayUtils {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Anchor.values().length];
            try {
                iArr[Anchor.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Anchor.TOP_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Anchor.TOP_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Anchor.CENTER_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Anchor.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Anchor.CENTER_RIGHT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Anchor.BOTTOM_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Anchor.BOTTOM_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[Anchor.BOTTOM_RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static Point a(Point... pointArr) {
        float f = 0.0f;
        float f2 = 0.0f;
        for (Point point : pointArr) {
            f2 += point.getX();
        }
        float length = f2 / pointArr.length;
        for (Point point2 : pointArr) {
            f += point2.getY();
        }
        return new Point(length, f / pointArr.length);
    }

    public static /* synthetic */ Point getObjectOverlayPosition$default(ObjectOverlayUtils objectOverlayUtils, Quadrilateral quadrilateral, Size2 size2, Anchor anchor, PointWithUnit pointWithUnit, int i, Object obj) {
        if ((i & 4) != 0) {
            anchor = Anchor.CENTER;
        }
        if ((i & 8) != 0) {
            pointWithUnit = PointWithUnitUtilsKt.PointWithUnit(0.0f, 0.0f, MeasureUnit.PIXEL);
        }
        return objectOverlayUtils.getObjectOverlayPosition(quadrilateral, size2, anchor, pointWithUnit);
    }

    public final Point calculateViewTopLeft(Point anchorPoint, PointWithUnit offset, Size2 viewSize) {
        Intrinsics.checkNotNullParameter(anchorPoint, "anchorPoint");
        Intrinsics.checkNotNullParameter(offset, "offset");
        Intrinsics.checkNotNullParameter(viewSize, "viewSize");
        return PointExtensionsKt.minus(PointExtensionsKt.plus(anchorPoint, PointWithUnitExtensionsKt.toPixels(offset, viewSize, AppAndroidEnvironment.INSTANCE.getApplicationContext().getResources().getDisplayMetrics().density)), Size2UtilsKt.getCenter(viewSize));
    }

    public final Point getObjectOverlayPosition(Quadrilateral objectPosition, Size2 overlayViewSize, Anchor anchor, PointWithUnit offset) {
        Point a;
        Intrinsics.checkNotNullParameter(objectPosition, "objectPosition");
        Intrinsics.checkNotNullParameter(overlayViewSize, "overlayViewSize");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        switch (WhenMappings.$EnumSwitchMapping$0[anchor.ordinal()]) {
            case 1:
                Point topLeft = objectPosition.getTopLeft();
                Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
                a = a(topLeft);
                break;
            case 2:
                Point topLeft2 = objectPosition.getTopLeft();
                Intrinsics.checkNotNullExpressionValue(topLeft2, "getTopLeft(...)");
                Point topRight = objectPosition.getTopRight();
                Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
                a = a(topLeft2, topRight);
                break;
            case 3:
                Point topRight2 = objectPosition.getTopRight();
                Intrinsics.checkNotNullExpressionValue(topRight2, "getTopRight(...)");
                a = a(topRight2);
                break;
            case 4:
                Point topLeft3 = objectPosition.getTopLeft();
                Intrinsics.checkNotNullExpressionValue(topLeft3, "getTopLeft(...)");
                Point bottomLeft = objectPosition.getBottomLeft();
                Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
                a = a(topLeft3, bottomLeft);
                break;
            case 5:
                Point topRight3 = objectPosition.getTopRight();
                Intrinsics.checkNotNullExpressionValue(topRight3, "getTopRight(...)");
                Point topLeft4 = objectPosition.getTopLeft();
                Intrinsics.checkNotNullExpressionValue(topLeft4, "getTopLeft(...)");
                Point bottomLeft2 = objectPosition.getBottomLeft();
                Intrinsics.checkNotNullExpressionValue(bottomLeft2, "getBottomLeft(...)");
                Point bottomRight = objectPosition.getBottomRight();
                Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
                a = a(topRight3, topLeft4, bottomLeft2, bottomRight);
                break;
            case 6:
                Point topRight4 = objectPosition.getTopRight();
                Intrinsics.checkNotNullExpressionValue(topRight4, "getTopRight(...)");
                Point bottomRight2 = objectPosition.getBottomRight();
                Intrinsics.checkNotNullExpressionValue(bottomRight2, "getBottomRight(...)");
                a = a(topRight4, bottomRight2);
                break;
            case 7:
                Point bottomLeft3 = objectPosition.getBottomLeft();
                Intrinsics.checkNotNullExpressionValue(bottomLeft3, "getBottomLeft(...)");
                a = a(bottomLeft3);
                break;
            case 8:
                Point bottomLeft4 = objectPosition.getBottomLeft();
                Intrinsics.checkNotNullExpressionValue(bottomLeft4, "getBottomLeft(...)");
                Point bottomRight3 = objectPosition.getBottomRight();
                Intrinsics.checkNotNullExpressionValue(bottomRight3, "getBottomRight(...)");
                a = a(bottomLeft4, bottomRight3);
                break;
            case 9:
                Point bottomRight4 = objectPosition.getBottomRight();
                Intrinsics.checkNotNullExpressionValue(bottomRight4, "getBottomRight(...)");
                a = a(bottomRight4);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return calculateViewTopLeft(a, offset, overlayViewSize);
    }
}
