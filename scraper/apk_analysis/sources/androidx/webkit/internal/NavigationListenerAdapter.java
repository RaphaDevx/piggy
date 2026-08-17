package androidx.webkit.internal;

import androidx.webkit.NavigationListener;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface;
import org.chromium.support_lib_boundary.util.Features;

/* loaded from: classes3.dex */
public class NavigationListenerAdapter implements WebViewNavigationListenerBoundaryInterface {
    private static final String[] SUPPORTED_FEATURES = {Features.WEB_VIEW_NAVIGATION_LISTENER_V1};
    private final NavigationListener mImpl;

    public NavigationListenerAdapter(NavigationListener navigationListener) {
        this.mImpl = navigationListener;
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onNavigationStarted(InvocationHandler invocationHandler) {
        this.mImpl.onNavigationStarted(NavigationImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onNavigationRedirected(InvocationHandler invocationHandler) {
        this.mImpl.onNavigationRedirected(NavigationImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onNavigationCompleted(InvocationHandler invocationHandler) {
        this.mImpl.onNavigationCompleted(NavigationImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onPageDeleted(InvocationHandler invocationHandler) {
        this.mImpl.onPageDeleted(PageImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onPageLoadEventFired(InvocationHandler invocationHandler) {
        this.mImpl.onPageLoadEvent(PageImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onPageDOMContentLoadedEventFired(InvocationHandler invocationHandler) {
        this.mImpl.onPageDomContentLoadedEvent(PageImpl.forInvocationHandler(invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewNavigationListenerBoundaryInterface
    public void onFirstContentfulPaint(InvocationHandler invocationHandler, long j) {
        this.mImpl.onFirstContentfulPaint(PageImpl.forInvocationHandler(invocationHandler), j);
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return SUPPORTED_FEATURES;
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof NavigationListenerAdapter)) {
            return this.mImpl.equals(((NavigationListenerAdapter) obj).mImpl);
        }
        return false;
    }
}
