package com.scandit.datacapture.barcode.internal.module.shared;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c {
    public final Function0 a;
    public final Function0 b;
    public final Function0 c;
    public final Function0 d;
    public final Function0 e;
    public a f;
    public b g;

    public c(Function0 startScanning, Function0 stopScanning, Function0 onFreezeScanning, Function0 release, Function0 resetScanning, b initialScanState) {
        a initialLifecycleState = a.a;
        Intrinsics.checkNotNullParameter(startScanning, "startScanning");
        Intrinsics.checkNotNullParameter(stopScanning, "stopScanning");
        Intrinsics.checkNotNullParameter(onFreezeScanning, "onFreezeScanning");
        Intrinsics.checkNotNullParameter(release, "release");
        Intrinsics.checkNotNullParameter(resetScanning, "resetScanning");
        Intrinsics.checkNotNullParameter(initialScanState, "initialScanState");
        Intrinsics.checkNotNullParameter(initialLifecycleState, "initialLifecycleState");
        this.a = startScanning;
        this.b = stopScanning;
        this.c = onFreezeScanning;
        this.d = release;
        this.e = resetScanning;
        this.f = initialLifecycleState;
        this.g = initialScanState;
    }

    public final synchronized void a() {
        b c = c();
        b bVar = b.d;
        if (c == bVar) {
            return;
        }
        this.g = bVar;
        a b = b();
        b.getClass();
        if (b == a.b) {
            this.c.invoke();
        }
    }

    public final synchronized a b() {
        return this.f;
    }

    public final synchronized b c() {
        return this.g;
    }

    public final synchronized void d() {
        b c = c();
        b bVar = b.b;
        if (c == bVar) {
            return;
        }
        this.g = bVar;
        a b = b();
        b.getClass();
        if (b == a.b) {
            this.a.invoke();
        }
    }

    public final synchronized void e() {
        b c = c();
        b bVar = b.c;
        if (c == bVar) {
            return;
        }
        this.g = bVar;
        this.b.invoke();
    }
}
