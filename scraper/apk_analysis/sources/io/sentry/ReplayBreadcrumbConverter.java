package io.sentry;

import io.sentry.rrweb.RRWebEvent;

/* loaded from: classes2.dex */
public interface ReplayBreadcrumbConverter {
    RRWebEvent convert(Breadcrumb breadcrumb);
}
