package com.tealium.internal.data;

/* loaded from: classes2.dex */
public interface DispatchStore {
    Dispatch[] dequeueDispatches();

    void enqueueDispatch(Dispatch dispatch);

    int getCount();

    void purgeUserNotConsented(Dispatch dispatch);

    void update(int i, float f);
}
