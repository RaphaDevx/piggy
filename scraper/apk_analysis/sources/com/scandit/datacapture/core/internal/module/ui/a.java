package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter;
import com.scandit.datacapture.core.internal.sdk.ui.ContextStatusView;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements ContextStatusPresenter {
    public ContextStatusView a;

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter
    public final ContextStatusView getView() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter
    public final void onStatusChanged(ContextStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        ContextStatusView contextStatusView = this.a;
        if (contextStatusView != null) {
            contextStatusView.displayContextStatus(status);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter
    public final void onWarningsChanged(ArrayList warnings) {
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        ContextStatusView contextStatusView = this.a;
        if (contextStatusView != null) {
            contextStatusView.displayWarnings(warnings);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.ContextStatusPresenter
    public final void setView(ContextStatusView contextStatusView) {
        this.a = contextStatusView;
    }
}
