package com.scandit.datacapture.frameworks.core.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: EventForResult.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/events/Cancellation;", "Lcom/scandit/datacapture/frameworks/core/events/PendingResult;", "()V", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class Cancellation extends PendingResult {
    public static final Cancellation INSTANCE = new Cancellation();

    private Cancellation() {
        super(null);
    }
}
