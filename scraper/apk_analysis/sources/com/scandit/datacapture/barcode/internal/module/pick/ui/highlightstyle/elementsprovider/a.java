package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncCallback;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleResponse;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodePickViewHighlightStyleAsyncCallback {
    public final b a;
    public final Function0 b;
    public final /* synthetic */ c c;

    public a(c cVar, b key, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.c whenDone) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        this.c = cVar;
        this.a = key;
        this.b = whenDone;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncCallback
    public final void onData(BarcodePickViewHighlightStyleResponse barcodePickViewHighlightStyleResponse) {
        c cVar = this.c;
        ReentrantReadWriteLock reentrantReadWriteLock = cVar.e;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (barcodePickViewHighlightStyleResponse == null) {
                cVar.h.remove(this.a);
                cVar.g.remove(this.a);
            } else {
                cVar.h.put(this.a, new g(barcodePickViewHighlightStyleResponse.getCom.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer.FIELD_ICON java.lang.String(), barcodePickViewHighlightStyleResponse.getBrush(), barcodePickViewHighlightStyleResponse.getStatusIconStyle()));
                LinkedHashMap linkedHashMap = cVar.g;
                b bVar = this.a;
                BarcodePickState barcodePickState = bVar.b;
                BarcodePickIcon selectedIcon = barcodePickViewHighlightStyleResponse.getSelectedIcon();
                m mVar = m.a;
                if (Intrinsics.areEqual(selectedIcon, mVar)) {
                    selectedIcon = cVar.c.b(barcodePickState);
                    if (Intrinsics.areEqual(selectedIcon, mVar)) {
                        selectedIcon = barcodePickViewHighlightStyleResponse.getCom.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer.FIELD_ICON java.lang.String();
                        if (Intrinsics.areEqual(selectedIcon, mVar)) {
                            selectedIcon = cVar.c.a(barcodePickState);
                        }
                    }
                }
                BarcodePickState state = this.a.b;
                BarcodePickBrush selectedBrush = barcodePickViewHighlightStyleResponse.getSelectedBrush();
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k kVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b;
                if (Intrinsics.areEqual(selectedBrush, kVar)) {
                    com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j jVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) cVar.b;
                    jVar.getClass();
                    Intrinsics.checkNotNullParameter(state, "state");
                    Brush brush = (Brush) jVar.b.invoke(state);
                    if (brush == null) {
                        selectedBrush = kVar;
                    } else {
                        Intrinsics.checkNotNullParameter(brush, "brush");
                        selectedBrush = new p(brush);
                    }
                    if (Intrinsics.areEqual(selectedBrush, kVar)) {
                        selectedBrush = barcodePickViewHighlightStyleResponse.getBrush();
                        if (Intrinsics.areEqual(selectedBrush, kVar)) {
                            selectedBrush = ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j) cVar.b).a(state);
                        }
                    }
                }
                linkedHashMap.put(bVar, new g(selectedIcon, selectedBrush, barcodePickViewHighlightStyleResponse.getStatusIconStyle()));
            }
            cVar.i.remove(this.a);
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
