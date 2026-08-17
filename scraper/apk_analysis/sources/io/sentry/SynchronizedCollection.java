package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
class SynchronizedCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 2412805092710877986L;
    private final Collection<E> collection;
    final AutoClosableReentrantLock lock;

    public static <T> SynchronizedCollection<T> synchronizedCollection(Collection<T> collection) {
        return new SynchronizedCollection<>(collection);
    }

    SynchronizedCollection(Collection<E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null.");
        }
        this.collection = collection;
        this.lock = new AutoClosableReentrantLock();
    }

    SynchronizedCollection(Collection<E> collection, AutoClosableReentrantLock autoClosableReentrantLock) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null.");
        }
        if (autoClosableReentrantLock == null) {
            throw new NullPointerException("Lock must not be null.");
        }
        this.collection = collection;
        this.lock = autoClosableReentrantLock;
    }

    protected Collection<E> decorated() {
        return this.collection;
    }

    @Override // java.util.Collection
    public boolean add(E e) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean add = decorated().add(e);
            if (acquire != null) {
                acquire.close();
            }
            return add;
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

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean addAll = decorated().addAll(collection);
            if (acquire != null) {
                acquire.close();
            }
            return addAll;
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

    @Override // java.util.Collection
    public void clear() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            decorated().clear();
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

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean contains = decorated().contains(obj);
            if (acquire != null) {
                acquire.close();
            }
            return contains;
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

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean containsAll = decorated().containsAll(collection);
            if (acquire != null) {
                acquire.close();
            }
            return containsAll;
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

    @Override // java.util.Collection
    public boolean isEmpty() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean isEmpty = decorated().isEmpty();
            if (acquire != null) {
                acquire.close();
            }
            return isEmpty;
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

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return decorated().iterator();
    }

    @Override // java.util.Collection
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

    @Override // java.util.Collection
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

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean remove = decorated().remove(obj);
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

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean removeAll = decorated().removeAll(collection);
            if (acquire != null) {
                acquire.close();
            }
            return removeAll;
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

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean retainAll = decorated().retainAll(collection);
            if (acquire != null) {
                acquire.close();
            }
            return retainAll;
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

    @Override // java.util.Collection
    public int size() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            int size = decorated().size();
            if (acquire != null) {
                acquire.close();
            }
            return size;
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

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        boolean z = true;
        if (obj == this) {
            if (acquire != null) {
                acquire.close();
            }
            return true;
        }
        if (obj != this) {
            try {
                if (!decorated().equals(obj)) {
                    z = false;
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
        if (acquire != null) {
            acquire.close();
        }
        return z;
    }

    @Override // java.util.Collection
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

    public String toString() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            String obj = decorated().toString();
            if (acquire != null) {
                acquire.close();
            }
            return obj;
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
