package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleRequest;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements BarcodePickElementsCache {
    public final BarcodePickViewHighlightStyleAsyncProvider a;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.i b;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c c;
    public final boolean d;
    public final ReentrantReadWriteLock e;
    public final HashSet f;
    public final LinkedHashMap g;
    public final LinkedHashMap h;
    public final LinkedHashMap i;

    public c(BarcodePickViewHighlightStyleAsyncProvider barcodePickViewHighlightStyleAsyncProvider, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j brushProvider, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c iconProvider, boolean z) {
        Intrinsics.checkNotNullParameter(brushProvider, "brushProvider");
        Intrinsics.checkNotNullParameter(iconProvider, "iconProvider");
        this.a = barcodePickViewHighlightStyleAsyncProvider;
        this.b = brushProvider;
        this.c = iconProvider;
        this.d = z;
        this.e = new ReentrantReadWriteLock(true);
        this.f = new HashSet();
        this.g = new LinkedHashMap();
        this.h = new LinkedHashMap();
        this.i = new LinkedHashMap();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.j
    public final i a(String str, BarcodePickState state, BarcodePickState barcodePickState) {
        g gVar;
        Intrinsics.checkNotNullParameter(state, "state");
        ReentrantReadWriteLock.ReadLock readLock = this.e.readLock();
        readLock.lock();
        try {
            if (str == null) {
                gVar = new g(this.c.a(state), ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) this.b).a(state), null);
            } else if (this.f.contains(str)) {
                gVar = a(str, state);
            } else {
                gVar = (g) this.h.get(new b(str, state));
                if (gVar == null) {
                    gVar = new g(this.c.a(state), ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) this.b).a(state), null);
                }
            }
            return gVar;
        } finally {
            readLock.unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void b(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = this.e;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (CollectionsKt.contains(this.f, str)) {
                a(str);
            } else if (str != null) {
                ReentrantReadWriteLock reentrantReadWriteLock2 = this.e;
                ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock2.readLock();
                int readHoldCount2 = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
                for (int i3 = 0; i3 < readHoldCount2; i3++) {
                    readLock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock2 = reentrantReadWriteLock2.writeLock();
                writeLock2.lock();
                try {
                    this.f.add(str);
                    for (int i4 = 0; i4 < readHoldCount2; i4++) {
                        readLock2.lock();
                    }
                    writeLock2.unlock();
                } catch (Throwable th) {
                    for (int i5 = 0; i5 < readHoldCount2; i5++) {
                        readLock2.lock();
                    }
                    writeLock2.unlock();
                    throw th;
                }
            }
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } catch (Throwable th2) {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            throw th2;
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void a(String itemData, String str, BarcodePickState state, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.c whenDone) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        BarcodePickViewHighlightStyleAsyncProvider barcodePickViewHighlightStyleAsyncProvider = this.a;
        if (barcodePickViewHighlightStyleAsyncProvider == null) {
            return;
        }
        ReentrantReadWriteLock.ReadLock readLock = this.e.readLock();
        readLock.lock();
        try {
            b bVar = new b(itemData, state);
            if (this.d && (this.h.containsKey(bVar) || this.i.containsKey(bVar))) {
                return;
            }
            a aVar = new a(this, bVar, whenDone);
            ReentrantReadWriteLock reentrantReadWriteLock = this.e;
            ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
            int i = 0;
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                readLock2.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                this.i.put(bVar, aVar);
                Unit unit = Unit.INSTANCE;
                while (i < readHoldCount) {
                    readLock2.lock();
                    i++;
                }
                writeLock.unlock();
                barcodePickViewHighlightStyleAsyncProvider.styleForRequest(new BarcodePickViewHighlightStyleRequest(itemData, str, state), aVar);
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

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.k
    public final void a(String str) {
        if (str != null) {
            ReentrantReadWriteLock reentrantReadWriteLock = this.e;
            ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
            int i = 0;
            int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
            for (int i2 = 0; i2 < readHoldCount; i2++) {
                readLock.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
            writeLock.lock();
            try {
                this.f.remove(str);
            } finally {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
            }
        }
    }

    public final g a(String str, BarcodePickState state) {
        BarcodePickBrush pVar;
        g gVar = (g) this.g.get(new b(str, state));
        if (gVar != null) {
            return gVar;
        }
        BarcodePickIcon b = this.c.b(state);
        if (Intrinsics.areEqual(b, m.a)) {
            b = this.c.a(state);
        }
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j jVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) this.b;
        jVar.getClass();
        Intrinsics.checkNotNullParameter(state, "state");
        Brush brush = (Brush) jVar.b.invoke(state);
        if (brush == null) {
            pVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b;
        } else {
            Intrinsics.checkNotNullParameter(brush, "brush");
            pVar = new p(brush);
        }
        if (Intrinsics.areEqual(pVar, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b)) {
            pVar = ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) this.b).a(state);
        }
        return new g(b, pVar, null);
    }
}
