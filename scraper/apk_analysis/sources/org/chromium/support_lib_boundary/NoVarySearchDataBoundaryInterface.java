package org.chromium.support_lib_boundary;

import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes4.dex */
public interface NoVarySearchDataBoundaryInterface {
    List<String> getConsideredQueryParameters();

    boolean getIgnoreDifferencesInParameters();

    List<String> getIgnoredQueryParameters();

    boolean getVaryOnKeyOrder();
}
