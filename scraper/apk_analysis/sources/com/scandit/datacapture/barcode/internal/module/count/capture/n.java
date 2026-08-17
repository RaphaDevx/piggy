package com.scandit.datacapture.barcode.internal.module.count.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n {
    public final NativeCluster a;
    public final List b;

    public n(NativeCluster nativeCluster, Collection nativeRecognizedBarcode, ArrayList nativeUnrecognizedBarcode) {
        Intrinsics.checkNotNullParameter(nativeCluster, "native");
        Intrinsics.checkNotNullParameter(nativeRecognizedBarcode, "recognizedBarcode");
        Intrinsics.checkNotNullParameter(nativeUnrecognizedBarcode, "unrecognizedBarcode");
        Intrinsics.checkNotNullParameter(nativeCluster, "nativeCluster");
        Intrinsics.checkNotNullParameter(nativeRecognizedBarcode, "nativeRecognizedBarcode");
        Intrinsics.checkNotNullParameter(nativeUnrecognizedBarcode, "nativeUnrecognizedBarcode");
        List<NativeTrackedBarcode> plus = CollectionsKt.plus(nativeRecognizedBarcode, (Iterable) nativeUnrecognizedBarcode);
        ArrayList trackedBarcodes = new ArrayList();
        for (NativeTrackedBarcode nativeTrackedBarcode : plus) {
            Integer clusterIdentifier = nativeTrackedBarcode.getClusterIdentifier();
            TrackedBarcode trackedBarcode = (clusterIdentifier != null && clusterIdentifier.intValue() == nativeCluster.getIdentifier()) ? new TrackedBarcode(nativeTrackedBarcode) : null;
            if (trackedBarcode != null) {
                trackedBarcodes.add(trackedBarcode);
            }
        }
        Intrinsics.checkNotNullParameter(nativeCluster, "nativeCluster");
        Intrinsics.checkNotNullParameter(trackedBarcodes, "trackedBarcodes");
        this.a = nativeCluster;
        this.b = trackedBarcodes;
    }
}
