package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import java.util.Queue;

/* loaded from: classes2.dex */
final class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
    private static final long serialVersionUID = 1;

    static <E> SynchronizedQueue<E> synchronizedQueue(Queue<E> queue) {
        return new SynchronizedQueue<>(queue);
    }

    private SynchronizedQueue(Queue<E> queue) {
        super(queue);
    }

    protected SynchronizedQueue(Queue<E> queue, AutoClosableReentrantLock autoClosableReentrantLock) {
        super(queue, autoClosableReentrantLock);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.sentry.SynchronizedCollection
    public Queue<E> decorated() {
        return (Queue) super.decorated();
    }

    @Override // java.util.Queue
    public E element() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            E element = decorated().element();
            if (acquire != null) {
                acquire.close();
            }
            return element;
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

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean equals = decorated().equals(obj);
            if (acquire != null) {
                acquire.close();
            }
            return equals;
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

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public int hashCode() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            int hashCode = decorated().hashCode();
            if (acquire != null) {
                acquire.close();
            }
            return hashCode;
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

    @Override // java.util.Queue
    public boolean offer(E e) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean offer = decorated().offer(e);
            if (acquire != null) {
                acquire.close();
            }
            return offer;
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

    @Override // java.util.Queue
    public E peek() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            E peek = decorated().peek();
            if (acquire != null) {
                acquire.close();
            }
            return peek;
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

    @Override // java.util.Queue
    public E poll() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            E poll = decorated().poll();
            if (acquire != null) {
                acquire.close();
            }
            return poll;
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

    @Override // java.util.Queue
    public E remove() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            E remove = decorated().remove();
            if (acquire != null) {
                acquire.close();
            }
            return remove;
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

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public Object[] toArray() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            Object[] array = decorated().toArray();
            if (acquire != null) {
                acquire.close();
            }
            return array;
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

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            T[] tArr2 = (T[]) decorated().toArray(tArr);
            if (acquire != null) {
                acquire.close();
            }
            return tArr2;
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
}
