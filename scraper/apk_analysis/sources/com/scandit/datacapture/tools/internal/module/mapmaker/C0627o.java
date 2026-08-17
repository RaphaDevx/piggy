package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.NoSuchElementException;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.o, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0627o extends AbstractC0624l {
    public C0627o(N n) {
        super(n);
    }

    @Override // java.util.Iterator
    public final Object next() {
        try {
            if (!hasNext()) {
                throw Q.a;
            }
            this.g = this.f;
            a();
            return this.g.a;
        } catch (Q unused) {
            throw new NoSuchElementException();
        }
    }
}
