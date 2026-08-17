package com.snapchat.djinni;

import com.snapchat.djinni.Future;
import com.snapchat.djinni.SharedState;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class Future<T> implements java.util.concurrent.Future<T> {
    private AtomicReference<SharedState<T>> _sharedState;

    @FunctionalInterface
    public interface FutureHandler<U> {
        void handleResult(Future<U> future) throws Throwable;
    }

    @FunctionalInterface
    public interface FutureHandlerWithReturn<U, R> {
        R handleResult(Future<U> future) throws Throwable;
    }

    Future(SharedState<T> sharedState) {
        this._sharedState = new AtomicReference<>(sharedState);
    }

    static /* synthetic */ void lambda$then$0(FutureHandler futureHandler, Promise promise, SharedState sharedState) {
        try {
            futureHandler.handleResult(new Future(sharedState));
            promise.setValue(null);
        } catch (Throwable th) {
            promise.setException(th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$then$1(Promise promise, FutureHandlerWithReturn futureHandlerWithReturn, SharedState sharedState) {
        try {
            promise.setValue(futureHandlerWithReturn.handleResult(new Future(sharedState)));
        } catch (Throwable th) {
            promise.setException(th);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        T t;
        SharedState<T> andSet = this._sharedState.getAndSet(null);
        synchronized (andSet) {
            while (!andSet.isReady()) {
                andSet.wait(timeUnit.toMillis(j));
            }
            if (andSet.exception != null) {
                throw new ExecutionException(andSet.exception.getMessage(), andSet.exception);
            }
            t = andSet.value;
        }
        return t;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return isReady();
    }

    public boolean isReady() {
        boolean isReady;
        SharedState<T> sharedState = this._sharedState.get();
        synchronized (sharedState) {
            isReady = sharedState.isReady();
        }
        return isReady;
    }

    public Future<Void> then(final FutureHandler<T> futureHandler) {
        final Promise promise = new Promise();
        Future<Void> future = promise.getFuture();
        SharedState.Continuation<T> continuation = new SharedState.Continuation() { // from class: com.snapchat.djinni.Future$$ExternalSyntheticLambda1
            @Override // com.snapchat.djinni.SharedState.Continuation
            public final void handleResult(SharedState sharedState) {
                Future.lambda$then$0(Future.FutureHandler.this, promise, sharedState);
            }
        };
        SharedState<T> sharedState = null;
        SharedState<T> andSet = this._sharedState.getAndSet(null);
        synchronized (andSet) {
            if (andSet.isReady()) {
                sharedState = andSet;
            } else {
                andSet.handler = continuation;
            }
        }
        if (sharedState != null) {
            continuation.handleResult(sharedState);
        }
        return future;
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        try {
            return get(0L, TimeUnit.MILLISECONDS);
        } catch (TimeoutException unused) {
            return null;
        }
    }

    public <R> Future<R> then(final FutureHandlerWithReturn<T, R> futureHandlerWithReturn) {
        final Promise promise = new Promise();
        Future<R> future = promise.getFuture();
        SharedState.Continuation<T> continuation = new SharedState.Continuation() { // from class: com.snapchat.djinni.Future$$ExternalSyntheticLambda0
            @Override // com.snapchat.djinni.SharedState.Continuation
            public final void handleResult(SharedState sharedState) {
                Future.lambda$then$1(Promise.this, futureHandlerWithReturn, sharedState);
            }
        };
        SharedState<T> sharedState = null;
        SharedState<T> andSet = this._sharedState.getAndSet(null);
        synchronized (andSet) {
            if (andSet.isReady()) {
                sharedState = andSet;
            } else {
                andSet.handler = continuation;
            }
        }
        if (sharedState != null) {
            continuation.handleResult(sharedState);
        }
        return future;
    }
}
