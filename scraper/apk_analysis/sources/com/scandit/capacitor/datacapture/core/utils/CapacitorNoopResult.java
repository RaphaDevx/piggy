package com.scandit.capacitor.datacapture.core.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CapacitorResult.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\r"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/utils/CapacitorNoopResult;", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "<init>", "()V", "success", "", "result", "", "error", "errorCode", "", "errorMessage", "errorDetails", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CapacitorNoopResult implements FrameworksResult {
    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void error(String errorCode, String errorMessage, Object errorDetails) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    }

    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void success(Object result) {
    }
}
