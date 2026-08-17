package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewAsyncCallback;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewResponse;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements BarcodePickViewHighlightStyleCustomViewAsyncCallback {
    public final e a;
    public final Function0 b;
    public final /* synthetic */ f c;

    public d(f fVar, e key, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.c whenDone) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        this.c = fVar;
        this.a = key;
        this.b = whenDone;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewAsyncCallback
    public final void onData(BarcodePickViewHighlightStyleCustomViewResponse barcodePickViewHighlightStyleCustomViewResponse) {
        f fVar = this.c;
        ReentrantReadWriteLock reentrantReadWriteLock = fVar.c;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (barcodePickViewHighlightStyleCustomViewResponse == null) {
                fVar.d.remove(this.a);
            } else {
                fVar.d.put(this.a, new h(barcodePickViewHighlightStyleCustomViewResponse.getCom.tealium.library.DataSources.EventTypeValue.VIEW_EVENT_TYPE java.lang.String(), barcodePickViewHighlightStyleCustomViewResponse.getStatusIconStyle()));
            }
            fVar.e.remove(this.a);
            this.b.invoke();
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }
}
