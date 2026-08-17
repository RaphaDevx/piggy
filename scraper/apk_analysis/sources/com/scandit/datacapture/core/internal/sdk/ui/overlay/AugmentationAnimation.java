package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import android.view.View;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.ui.animation.DynamicAnimation;
import com.scandit.datacapture.core.ui.animation.SpringAnimation;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/AugmentationAnimation;", "", "Landroid/view/View;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/common/geometry/Point;", "start", "<init>", "(Landroid/view/View;Lcom/scandit/datacapture/core/common/geometry/Point;)V", "final", "", "animateTo", "(Lcom/scandit/datacapture/core/common/geometry/Point;)V", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class AugmentationAnimation {
    private final SpringAnimation a;
    private final SpringAnimation b;

    public AugmentationAnimation(View view, Point start) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(start, "start");
        SpringAnimation springAnimation = new SpringAnimation(view, DynamicAnimation.X);
        springAnimation.setStartValue(start.getX());
        this.a = springAnimation;
        SpringAnimation springAnimation2 = new SpringAnimation(view, DynamicAnimation.Y);
        springAnimation2.setStartValue(start.getY());
        this.b = springAnimation2;
    }

    public final void animateTo(Point r3) {
        Intrinsics.checkNotNullParameter(r3, "final");
        this.a.animateToFinalPosition(r3.getX());
        this.b.animateToFinalPosition(r3.getY());
    }
}
