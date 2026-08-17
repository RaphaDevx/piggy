package androidx.webkit.internal;

import androidx.webkit.WebViewStartUpConfig;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface;

/* loaded from: classes3.dex */
public class WebViewStartUpConfigAdapter implements WebViewStartUpConfigBoundaryInterface {
    private final WebViewStartUpConfig mWebViewStartUpConfig;

    public WebViewStartUpConfigAdapter(WebViewStartUpConfig webViewStartUpConfig) {
        this.mWebViewStartUpConfig = webViewStartUpConfig;
    }

    @Override // org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface
    public Executor getBackgroundExecutor() {
        return this.mWebViewStartUpConfig.getBackgroundExecutor();
    }

    @Override // org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface
    public boolean shouldRunUiThreadStartUpTasks() {
        return this.mWebViewStartUpConfig.shouldRunUiThreadStartUpTasks();
    }

    @Override // org.chromium.support_lib_boundary.WebViewStartUpConfigBoundaryInterface
    public Set<String> getProfileNamesToLoad() {
        return this.mWebViewStartUpConfig.getProfilesToLoadDuringStartup();
    }
}
