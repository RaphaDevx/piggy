package com.tealium.internal;

import com.tealium.internal.j.n;
import com.tealium.internal.listeners.BackgroundListener;
import com.tealium.internal.listeners.MainListener;
import java.util.EventListener;

/* compiled from: MessageRouter.java */
/* loaded from: classes2.dex */
public interface d {
    <T extends MainListener> void a(n<T> nVar);

    void a(Runnable runnable);

    void a(Runnable runnable, long j);

    void a(EventListener eventListener);

    <T extends BackgroundListener> void b(n<T> nVar);

    void b(Runnable runnable);

    void b(Runnable runnable, long j);

    void b(EventListener eventListener);

    void c(Runnable runnable);
}
