package com.scandit.datacapture.barcode.count.capture.map;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scandit.datacapture.barcode.count.capture.Coordinate2d;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGrid;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003H\u0097\u0001J\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\t\u0010\u000b\u001a\u00020\nH\u0097\u0001J\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\u000e\u001a\u00020\bH\u0097\u0001J\u001b\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0097\u0001J\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\t\u0010\u0011\u001a\u00020\nH\u0097\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u0097\u0001¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGrid;", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridProxy;", "impl", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeSpatialGrid;", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeSpatialGrid;)V", "_impl", "column", "", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGridElement;", FirebaseAnalytics.Param.INDEX, "", "columns", "coordinatesForElement", "Lcom/scandit/datacapture/barcode/count/capture/Coordinate2d;", "element", "elementAt", "row", "rows", "toJson", "", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeSpatialGrid implements BarcodeSpatialGridProxy {
    private final /* synthetic */ BarcodeSpatialGridProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeSpatialGrid(NativeBarcodeSpatialGrid impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeSpatialGridProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeSpatialGrid getA() {
        return this.a.getA();
    }

    public final List<BarcodeSpatialGridElement> column(int index) {
        ArrayList<NativeBarcodeSpatialGridElement> column = getA().column(index);
        Intrinsics.checkNotNull(column, "null cannot be cast to non-null type java.util.ArrayList<com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement?>{ kotlin.collections.TypeAliasesKt.ArrayList<com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement?> }");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(column, 10));
        for (NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement : column) {
            arrayList.add(nativeBarcodeSpatialGridElement != null ? new BarcodeSpatialGridElement(nativeBarcodeSpatialGridElement) : null);
        }
        return arrayList;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @ProxyFunction
    public int columns() {
        return this.a.columns();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @ProxyFunction
    public List<Coordinate2d> coordinatesForElement(BarcodeSpatialGridElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.a.coordinatesForElement(element);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @ProxyFunction
    public BarcodeSpatialGridElement elementAt(int row, int column) {
        return this.a.elementAt(row, column);
    }

    public final List<BarcodeSpatialGridElement> row(int index) {
        ArrayList<NativeBarcodeSpatialGridElement> row = getA().row(index);
        Intrinsics.checkNotNull(row, "null cannot be cast to non-null type java.util.ArrayList<com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement?>{ kotlin.collections.TypeAliasesKt.ArrayList<com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement?> }");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(row, 10));
        for (NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement : row) {
            arrayList.add(nativeBarcodeSpatialGridElement != null ? new BarcodeSpatialGridElement(nativeBarcodeSpatialGridElement) : null);
        }
        return arrayList;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @ProxyFunction
    public int rows() {
        return this.a.rows();
    }

    @Override // com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGridProxy
    @ProxyFunction
    public String toJson() {
        return this.a.toJson();
    }
}
