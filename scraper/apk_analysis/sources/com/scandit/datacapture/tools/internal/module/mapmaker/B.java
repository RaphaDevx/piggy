package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public final class B extends AbstractC0624l {
    public B(N n) {
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
            return this.g.b;
        } catch (Q unused) {
            throw new NoSuchElementException();
        }
    }
}
