package com.tealium.internal.j;

import java.util.EventListener;

/* compiled from: Messenger.java */
/* loaded from: classes2.dex */
public abstract class n<T extends EventListener> {
    private final Class<T> a;

    protected n(Class<T> cls) {
        this.a = cls;
        if (cls == null) {
            throw new IllegalArgumentException();
        }
    }

    public final Class<T> a() {
        return this.a;
    }

    public abstract void a(T t);
}
