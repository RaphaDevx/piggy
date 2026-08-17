package com.scandit.datacapture.frameworks.core.result;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NoopFrameworksResult.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/result/NoopFrameworksResult;", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "()V", "error", "", "errorCode", "", "errorMessage", "errorDetails", "", "success", "result", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NoopFrameworksResult implements FrameworksResult {
    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void error(String errorCode, String errorMessage, Object errorDetails) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
    }

    @Override // com.scandit.datacapture.frameworks.core.result.FrameworksResult
    public void success(Object result) {
    }
}
