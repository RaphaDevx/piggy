package com.scandit.datacapture.frameworks.barcode.generator.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.generator.QrCodeErrorCorrectionLevel;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeGeneratorDataParser.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 )2\u00020\u0001:\u0001)BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJj\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0006HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0016\u0010\u000fR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0019\u0010\u000f¨\u0006*"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/generator/data/BarcodeGeneratorDataParser;", "", "id", "", "type", "backgroundColor", "", "foregroundColor", "errorCorrectionLevel", "Lcom/scandit/datacapture/barcode/generator/QrCodeErrorCorrectionLevel;", "versionNumber", "minimumErrorCorrectionPercent", "layers", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/scandit/datacapture/barcode/generator/QrCodeErrorCorrectionLevel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getBackgroundColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorCorrectionLevel", "()Lcom/scandit/datacapture/barcode/generator/QrCodeErrorCorrectionLevel;", "getForegroundColor", "getId", "()Ljava/lang/String;", "getLayers", "getMinimumErrorCorrectionPercent", "getType", "getVersionNumber", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/scandit/datacapture/barcode/generator/QrCodeErrorCorrectionLevel;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/scandit/datacapture/frameworks/barcode/generator/data/BarcodeGeneratorDataParser;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "toString", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class BarcodeGeneratorDataParser {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer backgroundColor;
    private final QrCodeErrorCorrectionLevel errorCorrectionLevel;
    private final Integer foregroundColor;
    private final String id;
    private final Integer layers;
    private final Integer minimumErrorCorrectionPercent;
    private final String type;
    private final Integer versionNumber;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getForegroundColor() {
        return this.foregroundColor;
    }

    /* renamed from: component5, reason: from getter */
    public final QrCodeErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getVersionNumber() {
        return this.versionNumber;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getMinimumErrorCorrectionPercent() {
        return this.minimumErrorCorrectionPercent;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getLayers() {
        return this.layers;
    }

    public final BarcodeGeneratorDataParser copy(String id, String type, Integer backgroundColor, Integer foregroundColor, QrCodeErrorCorrectionLevel errorCorrectionLevel, Integer versionNumber, Integer minimumErrorCorrectionPercent, Integer layers) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        return new BarcodeGeneratorDataParser(id, type, backgroundColor, foregroundColor, errorCorrectionLevel, versionNumber, minimumErrorCorrectionPercent, layers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BarcodeGeneratorDataParser)) {
            return false;
        }
        BarcodeGeneratorDataParser barcodeGeneratorDataParser = (BarcodeGeneratorDataParser) other;
        return Intrinsics.areEqual(this.id, barcodeGeneratorDataParser.id) && Intrinsics.areEqual(this.type, barcodeGeneratorDataParser.type) && Intrinsics.areEqual(this.backgroundColor, barcodeGeneratorDataParser.backgroundColor) && Intrinsics.areEqual(this.foregroundColor, barcodeGeneratorDataParser.foregroundColor) && this.errorCorrectionLevel == barcodeGeneratorDataParser.errorCorrectionLevel && Intrinsics.areEqual(this.versionNumber, barcodeGeneratorDataParser.versionNumber) && Intrinsics.areEqual(this.minimumErrorCorrectionPercent, barcodeGeneratorDataParser.minimumErrorCorrectionPercent) && Intrinsics.areEqual(this.layers, barcodeGeneratorDataParser.layers);
    }

    public int hashCode() {
        int hashCode = ((this.id.hashCode() * 31) + this.type.hashCode()) * 31;
        Integer num = this.backgroundColor;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.foregroundColor;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        QrCodeErrorCorrectionLevel qrCodeErrorCorrectionLevel = this.errorCorrectionLevel;
        int hashCode4 = (hashCode3 + (qrCodeErrorCorrectionLevel == null ? 0 : qrCodeErrorCorrectionLevel.hashCode())) * 31;
        Integer num3 = this.versionNumber;
        int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.minimumErrorCorrectionPercent;
        int hashCode6 = (hashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.layers;
        return hashCode6 + (num5 != null ? num5.hashCode() : 0);
    }

    public String toString() {
        return "BarcodeGeneratorDataParser(id=" + this.id + ", type=" + this.type + ", backgroundColor=" + this.backgroundColor + ", foregroundColor=" + this.foregroundColor + ", errorCorrectionLevel=" + this.errorCorrectionLevel + ", versionNumber=" + this.versionNumber + ", minimumErrorCorrectionPercent=" + this.minimumErrorCorrectionPercent + ", layers=" + this.layers + ')';
    }

    public BarcodeGeneratorDataParser(String id, String type, Integer num, Integer num2, QrCodeErrorCorrectionLevel qrCodeErrorCorrectionLevel, Integer num3, Integer num4, Integer num5) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = id;
        this.type = type;
        this.backgroundColor = num;
        this.foregroundColor = num2;
        this.errorCorrectionLevel = qrCodeErrorCorrectionLevel;
        this.versionNumber = num3;
        this.minimumErrorCorrectionPercent = num4;
        this.layers = num5;
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }

    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public final Integer getForegroundColor() {
        return this.foregroundColor;
    }

    public final QrCodeErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public final Integer getVersionNumber() {
        return this.versionNumber;
    }

    public final Integer getMinimumErrorCorrectionPercent() {
        return this.minimumErrorCorrectionPercent;
    }

    public final Integer getLayers() {
        return this.layers;
    }

    /* compiled from: BarcodeGeneratorDataParser.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/generator/data/BarcodeGeneratorDataParser$Companion;", "", "()V", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/generator/data/BarcodeGeneratorDataParser;", "json", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00d7  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0092  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0062  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.scandit.datacapture.frameworks.barcode.generator.data.BarcodeGeneratorDataParser fromJson(java.lang.String r12) {
            /*
                Method dump skipped, instructions count: 248
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.frameworks.barcode.generator.data.BarcodeGeneratorDataParser.Companion.fromJson(java.lang.String):com.scandit.datacapture.frameworks.barcode.generator.data.BarcodeGeneratorDataParser");
        }
    }
}
