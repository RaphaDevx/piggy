package com.scandit.datacapture.barcode.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeSymbologyCompat;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeSymbologyDescription;
import com.scandit.datacapture.core.data.Range;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\"\u001a\u00020\u0003H\u0097\u0001J\t\u0010#\u001a\u00020\fH\u0097\u0001R\u0014\u0010\u0005\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\f8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00108WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\f8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000eR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001f8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/scandit/datacapture/barcode/data/SymbologyDescription;", "Lcom/scandit/datacapture/barcode/data/SymbologyDescriptionProxy;", "impl", "Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeSymbologyDescription;", "(Lcom/scandit/datacapture/barcode/internal/sdk/data/NativeSymbologyDescription;)V", "activeSymbolCountRange", "Lcom/scandit/datacapture/core/data/Range;", "getActiveSymbolCountRange", "()Lcom/scandit/datacapture/core/data/Range;", "defaultSymbolCountRange", "getDefaultSymbolCountRange", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "getIdentifier", "()Ljava/lang/String;", "isAvailable", "", "()Z", "isColorInvertible", "readableName", "getReadableName", "supportedChecksums", "Ljava/util/EnumSet;", "Lcom/scandit/datacapture/barcode/data/Checksum;", "getSupportedChecksums", "()Ljava/util/EnumSet;", "supportedExtensions", "", "getSupportedExtensions", "()Ljava/util/Set;", "symbology", "Lcom/scandit/datacapture/barcode/data/Symbology;", "getSymbology", "()Lcom/scandit/datacapture/barcode/data/Symbology;", "_impl", "toJson", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class SymbologyDescription implements SymbologyDescriptionProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ SymbologyDescriptionProxyAdapter a;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0007¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/data/SymbologyDescription$Companion;", "", "Lcom/scandit/datacapture/barcode/data/Symbology;", "symbology", "Lcom/scandit/datacapture/barcode/data/SymbologyDescription;", "create", "(Lcom/scandit/datacapture/barcode/data/Symbology;)Lcom/scandit/datacapture/barcode/data/SymbologyDescription;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "forIdentifier", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/data/SymbologyDescription;", "", "all", "()Ljava/util/List;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final List<SymbologyDescription> all() {
            ArrayList<Symbology> all = NativeSymbologyCompat.all();
            Intrinsics.checkNotNullExpressionValue(all, "all(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
            for (Symbology symbology : all) {
                Companion companion = SymbologyDescription.INSTANCE;
                Intrinsics.checkNotNull(symbology);
                arrayList.add(companion.create(symbology));
            }
            return arrayList;
        }

        @JvmStatic
        public final SymbologyDescription create(Symbology symbology) {
            Intrinsics.checkNotNullParameter(symbology, "symbology");
            NativeSymbologyDescription create = NativeSymbologyDescription.create(symbology);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            return new SymbologyDescription(create);
        }

        @JvmStatic
        public final SymbologyDescription forIdentifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            NativeSymbologyDescription symbologyDescriptionFromIdentifier = NativeSymbologyDescription.symbologyDescriptionFromIdentifier(identifier);
            if (symbologyDescriptionFromIdentifier != null) {
                return new SymbologyDescription(symbologyDescriptionFromIdentifier);
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SymbologyDescription(NativeSymbologyDescription impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new SymbologyDescriptionProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final List<SymbologyDescription> all() {
        return INSTANCE.all();
    }

    @JvmStatic
    public static final SymbologyDescription create(Symbology symbology) {
        return INSTANCE.create(symbology);
    }

    @JvmStatic
    public static final SymbologyDescription forIdentifier(String str) {
        return INSTANCE.forIdentifier(str);
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeSymbologyDescription getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "activeSymbolCountRange")
    public Range getActiveSymbolCountRange() {
        return this.a.getActiveSymbolCountRange();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "defaultSymbolCountRange")
    public Range getDefaultSymbolCountRange() {
        return this.a.getDefaultSymbolCountRange();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = ViewHierarchyNode.JsonKeys.IDENTIFIER)
    public String getIdentifier() {
        return this.a.getIdentifier();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "readableName")
    public String getReadableName() {
        return this.a.getReadableName();
    }

    public final EnumSet<Checksum> getSupportedChecksums() {
        EnumSet<Checksum> supportedChecksumsBits = getA().getSupportedChecksumsBits();
        Intrinsics.checkNotNullExpressionValue(supportedChecksumsBits, "getSupportedChecksumsBits(...)");
        return supportedChecksumsBits;
    }

    public final Set<String> getSupportedExtensions() {
        HashSet<String> publicExtensions = getA().getPublicExtensions();
        Intrinsics.checkNotNullExpressionValue(publicExtensions, "getPublicExtensions(...)");
        return publicExtensions;
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "symbology")
    public Symbology getSymbology() {
        return this.a.getSymbology();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "isAvailable")
    public boolean isAvailable() {
        return this.a.isAvailable();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(property = "isColorInvertible")
    public boolean isColorInvertible() {
        return this.a.isColorInvertible();
    }

    @Override // com.scandit.datacapture.barcode.data.SymbologyDescriptionProxy
    @ProxyFunction(nativeName = "toJson")
    public String toJson() {
        return this.a.toJson();
    }
}
