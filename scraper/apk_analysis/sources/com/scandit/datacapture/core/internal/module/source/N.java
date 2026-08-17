package com.scandit.datacapture.core.internal.module.source;

import java.util.Stack;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class N implements BufferStack {
    public final int a = 0;
    public final Stack b;

    public N() {
        Stack stack = new Stack();
        this.b = stack;
        stack.clear();
        for (int i = 0; i < 3; i++) {
            a(new byte[this.a]);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.BufferStack
    public final void a(byte[] byteArray) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        if (this.b.size() < 3) {
            this.b.push(byteArray);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.BufferStack
    public final boolean isEmpty() {
        return this.b.size() == 0;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.BufferStack
    public final byte[] a(int i) {
        byte[] bArr = (byte[]) this.b.pop();
        if (bArr.length != i) {
            bArr = new byte[i];
        }
        Intrinsics.checkNotNull(bArr);
        return bArr;
    }
}
