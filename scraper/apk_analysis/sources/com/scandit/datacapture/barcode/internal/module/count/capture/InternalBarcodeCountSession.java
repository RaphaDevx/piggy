package com.scandit.datacapture.barcode.internal.module.count.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGrid;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSession;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class InternalBarcodeCountSession {
    private final Function0 a;
    private final Lazy b;

    public InternalBarcodeCountSession(Function0 getImpl) {
        Intrinsics.checkNotNullParameter(getImpl, "getImpl");
        this.a = getImpl;
        this.b = LazyKt.lazy(new l(this));
    }

    public final long b() {
        return ((m) this.b.getValue()).a.getFrameSeqIdAndroid();
    }

    public final boolean c() {
        return ((m) this.b.getValue()).a.hasScanningFailed();
    }

    public final Map d() {
        HashMap<Integer, NativeTrackedBarcode> trackedBarcodes = ((m) this.b.getValue()).a.getTrackedBarcodes();
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(trackedBarcodes);
        return barcodeNativeTypeFactory.convert(trackedBarcodes);
    }

    public final ArrayList e() {
        ArrayList<NativeBarcode> additionalBarcodes = ((NativeBarcodeCountSession) this.a.invoke()).getAdditionalBarcodes();
        Intrinsics.checkNotNullExpressionValue(additionalBarcodes, "getAdditionalBarcodes(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(additionalBarcodes, 10));
        for (NativeBarcode nativeBarcode : additionalBarcodes) {
            Intrinsics.checkNotNull(nativeBarcode);
            arrayList.add(new Barcode(nativeBarcode));
        }
        return arrayList;
    }

    public final LinkedHashMap f() {
        HashMap<Integer, NativeTrackedBarcode> clearedBarcodes = ((NativeBarcodeCountSession) this.a.invoke()).getClearedBarcodes();
        Intrinsics.checkNotNullExpressionValue(clearedBarcodes, "getClearedBarcodes(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(clearedBarcodes.size()));
        Iterator<T> it = clearedBarcodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            linkedHashMap.put(key, new TrackedBarcode((NativeTrackedBarcode) value));
        }
        return linkedHashMap;
    }

    public final ArrayList g() {
        ArrayList<NativeCluster> clusters = ((NativeBarcodeCountSession) this.a.invoke()).getClusters();
        Intrinsics.checkNotNullExpressionValue(clusters, "getClusters(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(clusters, 10));
        for (NativeCluster nativeCluster : clusters) {
            Intrinsics.checkNotNull(nativeCluster);
            Collection<NativeTrackedBarcode> values = ((NativeBarcodeCountSession) this.a.invoke()).getTrackedBarcodes().values();
            Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
            ArrayList<NativeTrackedBarcode> unscannedTrackedBarcodes = ((NativeBarcodeCountSession) this.a.invoke()).getUnscannedTrackedBarcodes();
            Intrinsics.checkNotNullExpressionValue(unscannedTrackedBarcodes, "getUnscannedTrackedBarcodes(...)");
            arrayList.add(new n(nativeCluster, values, unscannedTrackedBarcodes));
        }
        return arrayList;
    }

    public final ArrayList h() {
        Collection<NativeTrackedBarcode> values = ((NativeBarcodeCountSession) this.a.invoke()).getFilteredTrackedBarcodes().values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(new TrackedBarcode((NativeTrackedBarcode) it.next()));
        }
        return arrayList;
    }

    public final ArrayList i() {
        Collection<NativeTrackedBarcode> values = ((NativeBarcodeCountSession) this.a.invoke()).getTrackedBarcodes().values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(new TrackedBarcode((NativeTrackedBarcode) it.next()));
        }
        return arrayList;
    }

    public final ArrayList j() {
        ArrayList<Integer> removedTrackedBarcodes = ((NativeBarcodeCountSession) this.a.invoke()).getRemovedTrackedBarcodes();
        Intrinsics.checkNotNullExpressionValue(removedTrackedBarcodes, "getRemovedTrackedBarcodes(...)");
        return removedTrackedBarcodes;
    }

    public final int k() {
        return ((NativeBarcodeCountSession) this.a.invoke()).getScannedBarcodeCount();
    }

    public final ArrayList l() {
        ArrayList<NativeTrackedBarcode> unscannedTrackedBarcodes = ((NativeBarcodeCountSession) this.a.invoke()).getUnscannedTrackedBarcodes();
        Intrinsics.checkNotNullExpressionValue(unscannedTrackedBarcodes, "getUnscannedTrackedBarcodes(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(unscannedTrackedBarcodes, 10));
        for (NativeTrackedBarcode nativeTrackedBarcode : unscannedTrackedBarcodes) {
            Intrinsics.checkNotNull(nativeTrackedBarcode);
            arrayList.add(new TrackedBarcode(nativeTrackedBarcode));
        }
        return arrayList;
    }

    public final void m() {
        ((m) this.b.getValue()).a.reset();
    }

    public final String n() {
        String json = ((m) this.b.getValue()).a.toJson();
        Intrinsics.checkNotNull(json);
        return json;
    }

    public final BarcodeSpatialGrid a(int i, int i2) {
        NativeBarcodeSpatialGrid spatialMap = ((m) this.b.getValue()).a.getSpatialMap(i, i2);
        if (spatialMap != null) {
            return new BarcodeSpatialGrid(spatialMap);
        }
        return null;
    }

    public final NativeBarcodeCountSession a() {
        return (NativeBarcodeCountSession) this.a.invoke();
    }
}
