package com.scandit.datacapture.barcode.find.capture;

import com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements InternalBarcodeFindListener {
    public final WeakReference a;

    public b(BarcodeFind owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(BarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(BarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void c(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void onSearchStarted() {
        CopyOnWriteArraySet copyOnWriteArraySet;
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.f;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((BarcodeFindListener) it.next()).onSearchStarted();
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(BarcodeFind mode, NativeBarcodeFindSession session, FrameData data) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.f;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((BarcodeFindListener) it.next()).onSessionUpdated(new BarcodeFindSessionImpl(session));
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(HashSet barcodes) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        CopyOnWriteArraySet copyOnWriteArraySet2;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.f;
            if (copyOnWriteArraySet.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = barcodes.iterator();
            while (it.hasNext()) {
                NativeBarcodeFindItem nativeBarcodeFindItem = (NativeBarcodeFindItem) it.next();
                Map access$getSearchedItems = BarcodeFind.access$getSearchedItems(barcodeFind);
                byte[] findableData = nativeBarcodeFindItem.getFindableData();
                Intrinsics.checkNotNullExpressionValue(findableData, "getFindableData(...)");
                BarcodeFindItem barcodeFindItem = (BarcodeFindItem) access$getSearchedItems.get(BarcodeFindKt.toKeyable(findableData));
                if (barcodeFindItem != null) {
                    arrayList.add(barcodeFindItem);
                }
            }
            Set<BarcodeFindItem> set = CollectionsKt.toSet(arrayList);
            copyOnWriteArraySet2 = barcodeFind.f;
            Iterator it2 = copyOnWriteArraySet2.iterator();
            while (it2.hasNext()) {
                ((BarcodeFindListener) it2.next()).onSearchStopped(set);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(HashSet barcodes) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        CopyOnWriteArraySet copyOnWriteArraySet2;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.f;
            if (copyOnWriteArraySet.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = barcodes.iterator();
            while (it.hasNext()) {
                NativeBarcodeFindItem nativeBarcodeFindItem = (NativeBarcodeFindItem) it.next();
                Map access$getSearchedItems = BarcodeFind.access$getSearchedItems(barcodeFind);
                byte[] findableData = nativeBarcodeFindItem.getFindableData();
                Intrinsics.checkNotNullExpressionValue(findableData, "getFindableData(...)");
                BarcodeFindItem barcodeFindItem = (BarcodeFindItem) access$getSearchedItems.get(BarcodeFindKt.toKeyable(findableData));
                if (barcodeFindItem != null) {
                    arrayList.add(barcodeFindItem);
                }
            }
            Set<BarcodeFindItem> set = CollectionsKt.toSet(arrayList);
            copyOnWriteArraySet2 = barcodeFind.f;
            Iterator it2 = copyOnWriteArraySet2.iterator();
            while (it2.hasNext()) {
                ((BarcodeFindListener) it2.next()).onSearchPaused(set);
            }
        }
    }
}
