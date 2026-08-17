package androidx.webkit;

/* loaded from: classes3.dex */
public class BackForwardCacheSettings {
    private static final int DEFAULT_MAX_PAGES_IN_CACHE = 6;
    private static final long DEFAULT_TIMEOUT_IN_SECONDS = 600;
    private final int mMaxPagesInCache;
    private final long mTimeoutSeconds;

    private BackForwardCacheSettings(long j, int i) {
        this.mTimeoutSeconds = j;
        this.mMaxPagesInCache = i;
    }

    public long getTimeoutSeconds() {
        return this.mTimeoutSeconds;
    }

    public int getMaxPagesInCache() {
        return this.mMaxPagesInCache;
    }

    public static final class Builder {
        private long mTimeoutInSeconds = BackForwardCacheSettings.DEFAULT_TIMEOUT_IN_SECONDS;
        private int mMaxPagesInCache = 6;

        public Builder setTimeoutSeconds(long j) {
            this.mTimeoutInSeconds = j;
            return this;
        }

        public Builder setMaxPagesInCache(int i) {
            this.mMaxPagesInCache = i;
            return this;
        }

        public BackForwardCacheSettings build() {
            return new BackForwardCacheSettings(this.mTimeoutInSeconds, this.mMaxPagesInCache);
        }
    }
}
