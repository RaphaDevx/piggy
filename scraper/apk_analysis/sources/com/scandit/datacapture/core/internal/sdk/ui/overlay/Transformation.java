package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.PointUtilsKt;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.RotationExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation;", "", "", "scale", "Lcom/scandit/datacapture/core/common/geometry/Point;", "rotationPivot", "", "rotationDegrees", "<init>", "(FLcom/scandit/datacapture/core/common/geometry/Point;I)V", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "quadrilateral", "transform", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "point", "(Lcom/scandit/datacapture/core/common/geometry/Point;)Lcom/scandit/datacapture/core/common/geometry/Point;", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class Transformation {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Transformation d = new Transformation(1.0f, PointUtilsKt.getPOINT_ZERO(), 0);
    private final float a;
    private final Point b;
    private final int c;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation$Companion;", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/DataCaptureViewSize;", "from", TypedValues.TransitionType.S_TO, "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation;", "between", "(Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/DataCaptureViewSize;Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/DataCaptureViewSize;)Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation;", "ID", "Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation;", "getID", "()Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/Transformation;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final Transformation between(DataCaptureViewSize from, DataCaptureViewSize to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            if (Intrinsics.areEqual(from, to)) {
                return getID();
            }
            Size2 size2 = from.getIo.sentry.rrweb.RRWebVideoEvent.JsonKeys.SIZE java.lang.String();
            Size2 size22 = to.getIo.sentry.rrweb.RRWebVideoEvent.JsonKeys.SIZE java.lang.String();
            return new Transformation(Math.max(size2.getWidth() / size22.getWidth(), size2.getHeight() / size22.getHeight()), Size2UtilsKt.getCenter(from.getIo.sentry.rrweb.RRWebVideoEvent.JsonKeys.SIZE java.lang.String()), RotationExtensionsKt.toAngle(to.getAndroidx.constraintlayout.motion.widget.Key.ROTATION java.lang.String()) - RotationExtensionsKt.toAngle(from.getAndroidx.constraintlayout.motion.widget.Key.ROTATION java.lang.String()));
        }

        public final Transformation getID() {
            return Transformation.d;
        }
    }

    public Transformation(float f, Point rotationPivot, int i) {
        Intrinsics.checkNotNullParameter(rotationPivot, "rotationPivot");
        this.a = f;
        this.b = rotationPivot;
        this.c = i;
    }

    public final Quadrilateral transform(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
        int i = this.c;
        if (i != 0) {
            quadrilateral = QuadrilateralUtilsKt.rotatedDegrees(quadrilateral, this.b, i);
        }
        float f = this.a;
        return f == 1.0f ? quadrilateral : QuadrilateralUtilsKt.scaled(quadrilateral, f);
    }

    public final Point transform(Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        int i = this.c;
        if (i != 0) {
            point = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedDegrees(point, this.b, i);
        }
        float f = this.a;
        return f == 1.0f ? point : com.scandit.datacapture.core.common.geometry.PointUtilsKt.scaled(point, f);
    }
}
