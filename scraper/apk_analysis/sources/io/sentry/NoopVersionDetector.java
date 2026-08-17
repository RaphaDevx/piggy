package io.sentry;

/* loaded from: classes2.dex */
public final class NoopVersionDetector implements IVersionDetector {
    private static final NoopVersionDetector instance = new NoopVersionDetector();

    @Override // io.sentry.IVersionDetector
    public boolean checkForMixedVersions() {
        return false;
    }

    private NoopVersionDetector() {
    }

    public static NoopVersionDetector getInstance() {
        return instance;
    }
}
