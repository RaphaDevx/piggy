package com.scandit.djinni;

/* loaded from: classes2.dex */
class SharedState<T> {
    public Throwable exception;
    public Continuation<T> handler;
    public boolean ready = false;
    public T value;

    public interface Continuation<U> {
        void handleResult(SharedState<U> sharedState);
    }

    SharedState() {
    }

    public boolean isReady() {
        return this.ready || this.exception != null;
    }
}
