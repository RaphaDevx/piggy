package androidx.webkit.internal;

import androidx.webkit.BackForwardCacheSettings;
import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.WebViewBackForwardCacheSettingsBoundaryInterface;

/* loaded from: classes3.dex */
public class BackForwardCacheSettingsImpl implements WebViewBackForwardCacheSettingsBoundaryInterface {
    BackForwardCacheSettings mSettings;

    public BackForwardCacheSettingsImpl(BackForwardCacheSettings backForwardCacheSettings) {
        this.mSettings = backForwardCacheSettings;
    }

    @Override // org.chromium.support_lib_boundary.WebViewBackForwardCacheSettingsBoundaryInterface
    public int getTimeoutInSeconds() {
        return (int) this.mSettings.getTimeoutSeconds();
    }

    @Override // org.chromium.support_lib_boundary.WebViewBackForwardCacheSettingsBoundaryInterface
    public int getMaxPagesInCache() {
        return this.mSettings.getMaxPagesInCache();
    }

    @Override // org.chromium.support_lib_boundary.IsomorphicObjectBoundaryInterface
    public Object getOrCreatePeer(Callable<Object> callable) {
        return this.mSettings;
    }
}
