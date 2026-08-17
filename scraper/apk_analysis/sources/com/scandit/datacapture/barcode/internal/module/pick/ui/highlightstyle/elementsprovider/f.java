package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleRequest;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements BarcodePickElementsCache {
    public final BarcodePickViewHighlightStyleCustomViewProvider a;
    public final boolean b = false;
    public final ReentrantReadWriteLock c = new ReentrantReadWriteLock(true);
    public final LinkedHashMap d = new LinkedHashMap();
    public final LinkedHashMap e = new LinkedHashMap();

    public f(BarcodePickViewHighlightStyleCustomViewProvider barcodePickViewHighlightStyleCustomViewProvider) {
        this.a = barcodePickViewHighlightStyleCustomViewProvider;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.j
    public final i a(String str, BarcodePickState state, BarcodePickState barcodePickState) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (str != null) {
            e eVar = new e(str, state);
            ReentrantReadWriteLock.ReadLock readLock = this.c.readLock();
            readLock.lock();
            try {
                h hVar = (h) this.d.get(eVar);
                if (hVar != null) {
                    return hVar;
                }
                if (barcodePickState != null && this.e.get(eVar) != null) {
                    return (h) this.d.get(new e(str, barcodePickState));
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                readLock.unlock();
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void a(String str) {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void b(String str) {
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void a(String itemData, String str, BarcodePickState state, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.c whenDone) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        BarcodePickViewHighlightStyleCustomViewProvider barcodePickViewHighlightStyleCustomViewProvider = this.a;
        if (barcodePickViewHighlightStyleCustomViewProvider == null) {
            return;
        }
        ReentrantReadWriteLock.ReadLock readLock = this.c.readLock();
        readLock.lock();
        try {
            e eVar = new e(itemData, state);
            if (this.b && (this.d.containsKey(eVar) || this.e.containsKey(eVar))) {
                return;
            }
            d dVar = new d(this, eVar, whenDone);
            ReentrantReadWriteLock reentrantReadWriteLock = this.c;
            ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
            int i = 0;
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                readLock2.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                this.e.put(eVar, dVar);
                Unit unit = Unit.INSTANCE;
                while (i < readHoldCount) {
                    readLock2.lock();
                    i++;
                }
                writeLock.unlock();
                barcodePickViewHighlightStyleCustomViewProvider.customViewForRequest(new BarcodePickViewHighlightStyleRequest(itemData, str, state), dVar);
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                while (i < readHoldCount) {
                    readLock2.lock();
                    i++;
                }
                writeLock.unlock();
                throw th;
            }
        } finally {
            readLock.unlock();
        }
    }
}
