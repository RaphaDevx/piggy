package com.scandit.capacitor.datacapture.core.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* compiled from: Callback.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/utils/Callback;", "", "<init>", "()V", "disposed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getDisposed", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "dispose", "", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class Callback {
    private final AtomicBoolean disposed = new AtomicBoolean(false);

    protected final AtomicBoolean getDisposed() {
        return this.disposed;
    }

    public void dispose() {
        this.disposed.set(true);
    }
}
