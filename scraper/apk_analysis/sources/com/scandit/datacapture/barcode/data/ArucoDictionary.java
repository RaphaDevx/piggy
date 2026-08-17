package com.scandit.datacapture.barcode.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoDictionary;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003H\u0097\u0001¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "Lcom/scandit/datacapture/barcode/data/ArucoDictionaryProxy;", "impl", "Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeArucoDictionary;", "(Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeArucoDictionary;)V", "_impl", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ArucoDictionary implements ArucoDictionaryProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ ArucoDictionaryProxyAdapter a;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/data/ArucoDictionary$Companion;", "", "Lcom/scandit/datacapture/barcode/data/ArucoDictionaryPreset;", "preset", "Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "fromPreset", "(Lcom/scandit/datacapture/barcode/data/ArucoDictionaryPreset;)Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "", "markerSize", "", "Lcom/scandit/datacapture/barcode/data/ArucoMarker;", "markers", "createWithMarkers", "(ILjava/util/List;)Lcom/scandit/datacapture/barcode/data/ArucoDictionary;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final ArucoDictionary createWithMarkers(int markerSize, List<ArucoMarker> markers) {
            Intrinsics.checkNotNullParameter(markers, "markers");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(markers, 10));
            Iterator<T> it = markers.iterator();
            while (it.hasNext()) {
                arrayList.add(((ArucoMarker) it.next()).getA());
            }
            NativeArucoDictionary createWithMarkers = NativeArucoDictionary.createWithMarkers(markerSize, CollectionsExtensionsKt.toArrayList(arrayList));
            if (createWithMarkers == null) {
                return null;
            }
            return new ArucoDictionary(createWithMarkers);
        }

        @JvmStatic
        public final ArucoDictionary fromPreset(ArucoDictionaryPreset preset) {
            Intrinsics.checkNotNullParameter(preset, "preset");
            NativeArucoDictionary fromPreset = NativeArucoDictionary.fromPreset(preset);
            Intrinsics.checkNotNullExpressionValue(fromPreset, "fromPreset(...)");
            return new ArucoDictionary(fromPreset);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArucoDictionary(NativeArucoDictionary impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new ArucoDictionaryProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final ArucoDictionary createWithMarkers(int i, List<ArucoMarker> list) {
        return INSTANCE.createWithMarkers(i, list);
    }

    @JvmStatic
    public static final ArucoDictionary fromPreset(ArucoDictionaryPreset arucoDictionaryPreset) {
        return INSTANCE.fromPreset(arucoDictionaryPreset);
    }

    @Override // com.scandit.datacapture.barcode.data.ArucoDictionaryProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeArucoDictionary getA() {
        return this.a.getA();
    }
}
