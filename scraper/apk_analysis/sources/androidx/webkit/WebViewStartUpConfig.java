package androidx.webkit;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final class WebViewStartUpConfig {
    private final Executor mExecutor;
    private final Set<String> mProfilesToLoadDuringStartup;
    private final boolean mShouldRunUiThreadStartUpTasks;

    private WebViewStartUpConfig(Executor executor, boolean z, Set<String> set) {
        this.mExecutor = executor;
        this.mShouldRunUiThreadStartUpTasks = z;
        this.mProfilesToLoadDuringStartup = set;
    }

    public Executor getBackgroundExecutor() {
        return this.mExecutor;
    }

    public boolean shouldRunUiThreadStartUpTasks() {
        return this.mShouldRunUiThreadStartUpTasks;
    }

    public Set<String> getProfilesToLoadDuringStartup() {
        return this.mProfilesToLoadDuringStartup;
    }

    public static final class Builder {
        private final Executor mExecutor;
        private boolean mShouldRunUiThreadStartUpTasks = true;
        private Set<String> mProfilesToLoadDuringStartup = null;

        public Builder(Executor executor) {
            this.mExecutor = executor;
        }

        public Builder setShouldRunUiThreadStartUpTasks(boolean z) {
            if (this.mProfilesToLoadDuringStartup != null && !z) {
                throw new IllegalArgumentException("Can't specify profiles to load without running UI thread startup tasks");
            }
            this.mShouldRunUiThreadStartUpTasks = z;
            return this;
        }

        public Builder setProfilesToLoadDuringStartup(Set<String> set) {
            if (!this.mShouldRunUiThreadStartUpTasks) {
                throw new IllegalArgumentException("Can't specify profiles to load without running UI thread startup tasks");
            }
            this.mProfilesToLoadDuringStartup = new HashSet(set);
            return this;
        }

        public WebViewStartUpConfig build() {
            return new WebViewStartUpConfig(this.mExecutor, this.mShouldRunUiThreadStartUpTasks, this.mProfilesToLoadDuringStartup);
        }
    }
}
