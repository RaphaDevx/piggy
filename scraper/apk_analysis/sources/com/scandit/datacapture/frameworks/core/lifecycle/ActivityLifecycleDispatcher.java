package com.scandit.datacapture.frameworks.core.lifecycle;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: ActivityLifecycleDispatcher.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycleDispatcher;", "", "dispatchOnCreate", "", "dispatchOnDestroy", "dispatchOnPause", "dispatchOnResume", "dispatchOnStart", "dispatchOnStop", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface ActivityLifecycleDispatcher {
    void dispatchOnCreate();

    void dispatchOnDestroy();

    void dispatchOnPause();

    void dispatchOnResume();

    void dispatchOnStart();

    void dispatchOnStop();
}
