package com.scandit.datacapture.frameworks.core.context;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import kotlin.Metadata;

/* compiled from: FrameworksCaptureContext.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0007H&J\b\u0010\u0010\u001a\u00020\u0007H&J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "getContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "addMode", "", "mode", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "initialize", "json", "", "androidContext", "Landroid/content/Context;", "release", "removeAllModes", "removeMode", "update", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FrameworksCaptureContext {
    void addMode(DataCaptureMode mode);

    DataCaptureContext getContext();

    DataCaptureContext initialize(String json, Context androidContext);

    void release();

    void removeAllModes();

    void removeMode(DataCaptureMode mode);

    void update(String json);
}
