package com.scandit.datacapture.barcode.find.capture;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindSessionImpl;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindSession;", "Lcom/scandit/datacapture/barcode/internal/module/find/capture/NativeBarcodeFindSession;", "impl", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/find/capture/NativeBarcodeFindSession;)V", "", "toJson", "()Ljava/lang/String;", "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "getTrackedBarcodes", "()Ljava/util/List;", "trackedBarcodes", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeFindSessionImpl implements BarcodeFindSession {
    private final NativeBarcodeFindSession a;

    public BarcodeFindSessionImpl(NativeBarcodeFindSession impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = impl;
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindSession
    public List<TrackedBarcode> getTrackedBarcodes() {
        Collection<NativeTrackedBarcode> values = this.a.getTrackedBarcodes().values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        for (NativeTrackedBarcode nativeTrackedBarcode : values) {
            Intrinsics.checkNotNull(nativeTrackedBarcode);
            arrayList.add(new TrackedBarcode(nativeTrackedBarcode));
        }
        return arrayList;
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindSession
    public String toJson() {
        String publicJson = this.a.toPublicJson();
        Intrinsics.checkNotNullExpressionValue(publicJson, "toPublicJson(...)");
        return publicJson;
    }
}
