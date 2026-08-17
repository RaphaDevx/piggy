package org.chromium.support_lib_boundary;

import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes4.dex */
public interface WebViewStartUpResultBoundaryInterface {
    List<Throwable> getAsyncStartUpLocations();

    List<Throwable> getBlockingStartUpLocations();

    Long getMaxTimePerTaskInUiThreadMillis();

    Long getTotalTimeInUiThreadMillis();
}
