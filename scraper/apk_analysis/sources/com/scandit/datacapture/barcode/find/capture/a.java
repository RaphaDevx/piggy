package com.scandit.datacapture.barcode.find.capture;

import com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession;
import com.scandit.datacapture.barcode.internal.module.find.ui.listener.SearchedItemsUpdateListener;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class a implements InternalBarcodeFindListener {
    public final WeakReference a;

    public a(BarcodeFind owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(BarcodeFind mode) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).a(barcodeFind);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(BarcodeFind mode) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).b(barcodeFind);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void c(HashSet barcodes) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        CopyOnWriteArraySet copyOnWriteArraySet2;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.h;
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
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                linkedHashMap.put(BarcodeFindKt.toKeyable(((BarcodeFindItem) next).getSearchOptions().getBarcodeRawData()), next);
            }
            copyOnWriteArraySet2 = barcodeFind.h;
            Iterator it3 = copyOnWriteArraySet2.iterator();
            while (it3.hasNext()) {
                ((SearchedItemsUpdateListener) it3.next()).a(linkedHashMap);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void onSearchStarted() {
        CopyOnWriteArraySet copyOnWriteArraySet;
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).onSearchStarted();
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(HashSet barcodes) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).b(barcodes);
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
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).a(mode, session, data);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(HashSet barcodes) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFind barcodeFind = (BarcodeFind) this.a.get();
        if (barcodeFind != null) {
            copyOnWriteArraySet = barcodeFind.g;
            Iterator it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                ((InternalBarcodeFindListener) it.next()).a(barcodes);
            }
        }
    }
}
