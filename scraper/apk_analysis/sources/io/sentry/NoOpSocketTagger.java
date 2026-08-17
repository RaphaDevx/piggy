package io.sentry;

/* loaded from: classes2.dex */
public final class NoOpSocketTagger implements ISocketTagger {
    private static final NoOpSocketTagger instance = new NoOpSocketTagger();

    @Override // io.sentry.ISocketTagger
    public void tagSockets() {
    }

    @Override // io.sentry.ISocketTagger
    public void untagSockets() {
    }

    private NoOpSocketTagger() {
    }

    public static ISocketTagger getInstance() {
        return instance;
    }
}
