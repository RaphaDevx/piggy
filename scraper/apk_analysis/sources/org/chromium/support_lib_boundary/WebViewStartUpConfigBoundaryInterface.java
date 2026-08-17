package org.chromium.support_lib_boundary;

import java.util.Set;
import java.util.concurrent.Executor;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes4.dex */
public interface WebViewStartUpConfigBoundaryInterface {
    Executor getBackgroundExecutor();

    Set<String> getProfileNamesToLoad();

    boolean shouldRunUiThreadStartUpTasks();
}
