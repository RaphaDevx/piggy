package com.scandit.datacapture.barcode.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeCompositeTypeDescription;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003H\u0097\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u0097\u0001R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/data/CompositeTypeDescription;", "Lcom/scandit/datacapture/barcode/data/CompositeTypeDescriptionProxy;", "impl", "Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeCompositeTypeDescription;", "(Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeCompositeTypeDescription;)V", "symbologies", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "getSymbologies", "()Ljava/util/Set;", "types", "Ljava/util/EnumSet;", "Lcom/scandit/datacapture/barcode/data/CompositeType;", "getTypes", "()Ljava/util/EnumSet;", "_impl", "toJson", "", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class CompositeTypeDescription implements CompositeTypeDescriptionProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ CompositeTypeDescriptionProxyAdapter a;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/barcode/data/CompositeTypeDescription$Companion;", "", "Ljava/util/EnumSet;", "Lcom/scandit/datacapture/barcode/data/CompositeType;", "type", "Lcom/scandit/datacapture/barcode/data/CompositeTypeDescription;", "create", "(Ljava/util/EnumSet;)Lcom/scandit/datacapture/barcode/data/CompositeTypeDescription;", "", "all", "()Ljava/util/List;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final List<CompositeTypeDescription> all() {
            ArrayList<NativeCompositeTypeDescription> all = NativeCompositeTypeDescription.all();
            Intrinsics.checkNotNullExpressionValue(all, "all(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
            for (NativeCompositeTypeDescription nativeCompositeTypeDescription : all) {
                Intrinsics.checkNotNull(nativeCompositeTypeDescription);
                arrayList.add(new CompositeTypeDescription(nativeCompositeTypeDescription));
            }
            return arrayList;
        }

        @JvmStatic
        public final CompositeTypeDescription create(EnumSet<CompositeType> type) {
            Intrinsics.checkNotNullParameter(type, "type");
            NativeCompositeTypeDescription create = NativeCompositeTypeDescription.create(type);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return new CompositeTypeDescription(create);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeTypeDescription(NativeCompositeTypeDescription impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new CompositeTypeDescriptionProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final List<CompositeTypeDescription> all() {
        return INSTANCE.all();
    }

    @JvmStatic
    public static final CompositeTypeDescription create(EnumSet<CompositeType> enumSet) {
        return INSTANCE.create(enumSet);
    }

    @Override // com.scandit.datacapture.barcode.data.CompositeTypeDescriptionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeCompositeTypeDescription getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.data.CompositeTypeDescriptionProxy
    @ProxyFunction(property = "symbologies")
    public Set<Symbology> getSymbologies() {
        return this.a.getSymbologies();
    }

    @Override // com.scandit.datacapture.barcode.data.CompositeTypeDescriptionProxy
    @ProxyFunction(nativeName = "getTypesBits", property = "types")
    public EnumSet<CompositeType> getTypes() {
        return this.a.getTypes();
    }

    @Override // com.scandit.datacapture.barcode.data.CompositeTypeDescriptionProxy
    @ProxyFunction(nativeName = "toJson")
    public String toJson() {
        return this.a.toJson();
    }
}
