package com.scandit.datacapture.core.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.internal.module.ui.ErrorOverlay;
import com.scandit.datacapture.core.internal.sdk.common.NativeContextStatusCompat;
import com.scandit.datacapture.core.internal.sdk.common.NativeError;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.ContextStatusView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0016¨\u0006\u000b"}, d2 = {"com/scandit/datacapture/core/ui/DataCaptureView$contextStatusView$1", "Lcom/scandit/datacapture/core/internal/sdk/ui/ContextStatusView;", "displayContextStatus", "", "status", "Lcom/scandit/datacapture/core/common/ContextStatus;", "displayWarnings", "warnings", "Ljava/util/ArrayList;", "Lcom/scandit/datacapture/core/internal/sdk/common/NativeError;", "Lkotlin/collections/ArrayList;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataCaptureView$contextStatusView$1 implements ContextStatusView {
    final /* synthetic */ DataCaptureView a;

    DataCaptureView$contextStatusView$1(DataCaptureView dataCaptureView) {
        this.a = dataCaptureView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(ContextStatus status, DataCaptureView this$0) {
        ErrorOverlay errorOverlay;
        ErrorOverlay errorOverlay2;
        ErrorOverlay errorOverlay3;
        ErrorOverlay errorOverlay4;
        Intrinsics.checkNotNullParameter(status, "$status");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (NativeContextStatusCompat.isSuccessOrUnknown(status.getCode())) {
            errorOverlay3 = this$0.m;
            errorOverlay3.a();
            errorOverlay4 = this$0.m;
            errorOverlay4.setVisibility(4);
            return;
        }
        errorOverlay = this$0.m;
        errorOverlay.a(status);
        errorOverlay2 = this$0.m;
        errorOverlay2.setVisibility(0);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusView
    public void displayContextStatus(final ContextStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        final DataCaptureView dataCaptureView = this.a;
        dataCaptureView.post(new Runnable() { // from class: com.scandit.datacapture.core.ui.DataCaptureView$contextStatusView$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureView$contextStatusView$1.a(ContextStatus.this, dataCaptureView);
            }
        });
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusView
    public void displayWarnings(ArrayList<NativeError> warnings) {
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        if (warnings.isEmpty()) {
            return;
        }
        ViewExtensionsKt.runOnMainThread(this.a, new c(warnings, this.a));
    }
}
