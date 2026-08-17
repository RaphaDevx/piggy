package androidx.webkit.internal;

import androidx.webkit.Navigation;
import androidx.webkit.Page;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.WebViewNavigationBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes3.dex */
public class NavigationImpl implements Navigation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    WebViewNavigationBoundaryInterface mImpl;
    Page mPage;

    public static Navigation forInvocationHandler(InvocationHandler invocationHandler) {
        final WebViewNavigationBoundaryInterface webViewNavigationBoundaryInterface = (WebViewNavigationBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewNavigationBoundaryInterface.class, invocationHandler);
        return (Navigation) Objects.requireNonNull(webViewNavigationBoundaryInterface.getOrCreatePeer(new Callable() { // from class: androidx.webkit.internal.NavigationImpl$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NavigationImpl.lambda$forInvocationHandler$0(WebViewNavigationBoundaryInterface.this);
            }
        }));
    }

    static /* synthetic */ Object lambda$forInvocationHandler$0(WebViewNavigationBoundaryInterface webViewNavigationBoundaryInterface) throws Exception {
        return new NavigationImpl(webViewNavigationBoundaryInterface);
    }

    private NavigationImpl(WebViewNavigationBoundaryInterface webViewNavigationBoundaryInterface) {
        this.mImpl = webViewNavigationBoundaryInterface;
    }

    @Override // androidx.webkit.Navigation
    public Page getPage() {
        if (this.mImpl.getPage() == null) {
            return null;
        }
        if (this.mPage == null) {
            this.mPage = PageImpl.forInvocationHandler(this.mImpl.getPage());
        }
        return this.mPage;
    }

    @Override // androidx.webkit.Navigation
    public String getUrl() {
        return this.mImpl.getUrl();
    }

    @Override // androidx.webkit.Navigation
    public boolean wasInitiatedByPage() {
        return this.mImpl.wasInitiatedByPage();
    }

    @Override // androidx.webkit.Navigation
    public boolean isSameDocument() {
        return this.mImpl.isSameDocument();
    }

    @Override // androidx.webkit.Navigation
    public boolean isReload() {
        return this.mImpl.isReload();
    }

    @Override // androidx.webkit.Navigation
    public boolean isHistory() {
        return this.mImpl.isHistory();
    }

    @Override // androidx.webkit.Navigation
    public boolean isRestore() {
        return this.mImpl.isRestore();
    }

    @Override // androidx.webkit.Navigation
    public boolean isBack() {
        return this.mImpl.isBack();
    }

    @Override // androidx.webkit.Navigation
    public boolean isForward() {
        return this.mImpl.isForward();
    }

    @Override // androidx.webkit.Navigation
    public boolean didCommit() {
        return this.mImpl.didCommit();
    }

    @Override // androidx.webkit.Navigation
    public boolean didCommitErrorPage() {
        return this.mImpl.didCommitErrorPage();
    }

    @Override // androidx.webkit.Navigation
    public int getStatusCode() {
        return this.mImpl.getStatusCode();
    }
}
