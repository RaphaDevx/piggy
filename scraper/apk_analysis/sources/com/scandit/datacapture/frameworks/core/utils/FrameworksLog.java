package com.scandit.datacapture.frameworks.core.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: FrameworksLog.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "", "error", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "info", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface FrameworksLog {
    void error(Exception e);

    void error(String message);

    void error(String message, Exception e);

    void info(String message);
}
