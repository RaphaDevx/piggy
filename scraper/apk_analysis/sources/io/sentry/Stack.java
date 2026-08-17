package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: classes2.dex */
final class Stack {
    private final Deque<StackItem> items;
    private final AutoClosableReentrantLock itemsLock;
    private final ILogger logger;

    static final class StackItem {
        private volatile ISentryClient client;
        private final SentryOptions options;
        private volatile IScope scope;

        StackItem(SentryOptions sentryOptions, ISentryClient iSentryClient, IScope iScope) {
            this.client = (ISentryClient) Objects.requireNonNull(iSentryClient, "ISentryClient is required.");
            this.scope = (IScope) Objects.requireNonNull(iScope, "Scope is required.");
            this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "Options is required");
        }

        StackItem(StackItem stackItem) {
            this.options = stackItem.options;
            this.client = stackItem.client;
            this.scope = stackItem.scope.m3814clone();
        }

        public ISentryClient getClient() {
            return this.client;
        }

        public void setClient(ISentryClient iSentryClient) {
            this.client = iSentryClient;
        }

        public IScope getScope() {
            return this.scope;
        }

        public SentryOptions getOptions() {
            return this.options;
        }
    }

    public Stack(ILogger iLogger, StackItem stackItem) {
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        this.items = linkedBlockingDeque;
        this.itemsLock = new AutoClosableReentrantLock();
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "logger is required");
        linkedBlockingDeque.push((StackItem) Objects.requireNonNull(stackItem, "rootStackItem is required"));
    }

    public Stack(Stack stack) {
        this(stack.logger, new StackItem(stack.items.getLast()));
        Iterator<StackItem> descendingIterator = stack.items.descendingIterator();
        if (descendingIterator.hasNext()) {
            descendingIterator.next();
        }
        while (descendingIterator.hasNext()) {
            push(new StackItem(descendingIterator.next()));
        }
    }

    StackItem peek() {
        return this.items.peek();
    }

    void pop() {
        ISentryLifecycleToken acquire = this.itemsLock.acquire();
        try {
            if (this.items.size() != 1) {
                this.items.pop();
            } else {
                this.logger.log(SentryLevel.WARNING, "Attempt to pop the root scope.", new Object[0]);
            }
            if (acquire != null) {
                acquire.close();
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    void push(StackItem stackItem) {
        this.items.push(stackItem);
    }

    int size() {
        return this.items.size();
    }
}
