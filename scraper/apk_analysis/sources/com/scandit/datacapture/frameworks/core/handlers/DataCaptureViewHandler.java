package com.scandit.datacapture.frameworks.core.handlers;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.tealium.library.DataSources;
import kotlin.Metadata;

/* compiled from: DataCaptureViewHandler.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0007H&J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "", "topmostDataCaptureView", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "getTopmostDataCaptureView", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "addView", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "getView", "viewId", "", "removeAllViews", "removeTopmostView", "removeView", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface DataCaptureViewHandler {
    void addView(FrameworksDataCaptureView view);

    FrameworksDataCaptureView getTopmostDataCaptureView();

    FrameworksDataCaptureView getView(int viewId);

    void removeAllViews();

    FrameworksDataCaptureView removeTopmostView();

    void removeView(int viewId);
}
