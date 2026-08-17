package com.scandit.datacapture.core.internal.sdk;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ ReentrantLock a;
    public final /* synthetic */ AtomicBoolean b;
    public final /* synthetic */ AtomicReference c;
    public final /* synthetic */ Condition d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, AtomicReference atomicReference, Condition condition) {
        super(1);
        this.a = reentrantLock;
        this.b = atomicBoolean;
        this.c = atomicReference;
        this.d = condition;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ReentrantLock reentrantLock = this.a;
        AtomicBoolean atomicBoolean = this.b;
        AtomicReference atomicReference = this.c;
        Condition condition = this.d;
        reentrantLock.lock();
        try {
            atomicBoolean.set(true);
            atomicReference.set(obj);
            condition.signalAll();
            return Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
