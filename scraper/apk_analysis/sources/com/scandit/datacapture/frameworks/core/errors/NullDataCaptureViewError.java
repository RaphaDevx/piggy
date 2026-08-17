package com.scandit.datacapture.frameworks.core.errors;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Error.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/errors/NullDataCaptureViewError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NullDataCaptureViewError extends Exception {
    public NullDataCaptureViewError() {
        super("DataCaptureView is null");
    }
}
