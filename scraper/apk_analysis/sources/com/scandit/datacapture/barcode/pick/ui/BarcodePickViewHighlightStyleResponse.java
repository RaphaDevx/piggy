package com.scandit.datacapture.barcode.pick.ui;

import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconDeserializer;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.style.BrushDeserializer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u0000 +2\u00020\u0001:\u0002,+B3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bB;\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\r\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000eB;\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u000f\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u0012B'\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\r\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u0013B'\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u0014B9\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u0006\u001a\u00020\u0015\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u0016B%\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u0006\u001a\u00020\u0015\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010\u0007\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b%\u0010\"\u001a\u0004\b&\u0010$R\u001c\u0010\t\u001a\u0004\u0018\u00010\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse;", "", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickBrush;", "brush", "selectedBrush", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickIcon;", BarcodePickDeserializer.FIELD_ICON, "selectedIcon", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "statusIconStyle", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickBrush;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickBrush;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickIcon;Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickIcon;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "Lcom/scandit/datacapture/core/ui/style/Brush;", "Landroid/graphics/Bitmap;", "(Lcom/scandit/datacapture/core/ui/style/Brush;Lcom/scandit/datacapture/core/ui/style/Brush;Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "", "iconResource", "selectedIconResource", "(Lcom/scandit/datacapture/core/ui/style/Brush;Lcom/scandit/datacapture/core/ui/style/Brush;IILcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "(Lcom/scandit/datacapture/core/ui/style/Brush;Landroid/graphics/Bitmap;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "(Lcom/scandit/datacapture/core/ui/style/Brush;ILcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "(Lcom/scandit/datacapture/core/ui/style/Brush;Lcom/scandit/datacapture/core/ui/style/Brush;Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "(Lcom/scandit/datacapture/core/ui/style/Brush;Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)V", "", "toJson", "()Ljava/lang/String;", "a", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickBrush;", "getBrush$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickBrush;", "b", "getSelectedBrush$scandit_barcode_capture", "c", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickIcon;", "getIcon$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/pick/ui/highlightstyle/BarcodePickIcon;", "d", "getSelectedIcon$scandit_barcode_capture", "e", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "getStatusIconStyle$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Companion", "Builder", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickViewHighlightStyleResponse {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: a, reason: from kotlin metadata */
    private final BarcodePickBrush brush;

    /* renamed from: b, reason: from kotlin metadata */
    private final BarcodePickBrush selectedBrush;

    /* renamed from: c, reason: from kotlin metadata */
    private final BarcodePickIcon icon;

    /* renamed from: d, reason: from kotlin metadata */
    private final BarcodePickIcon selectedIcon;

    /* renamed from: e, reason: from kotlin metadata */
    private final BarcodePickStatusIconStyle statusIconStyle;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Companion;", "", "", "jsonData", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "builder", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }

        @JvmStatic
        public final BarcodePickViewHighlightStyleResponse fromJson(String jsonData) {
            Object m3945constructorimpl;
            Object m3945constructorimpl2;
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JsonValue json = new JsonValue(jsonData);
            Intrinsics.checkNotNullParameter(json, "json");
            JsonValue byKeyAsObject = json.getByKeyAsObject("brush", null);
            Brush fromJson = byKeyAsObject != null ? BrushDeserializer.fromJson(byKeyAsObject.jsonString()) : null;
            JsonValue byKeyAsObject2 = json.getByKeyAsObject("selectedBrush", null);
            Brush fromJson2 = byKeyAsObject2 != null ? BrushDeserializer.fromJson(byKeyAsObject2.jsonString()) : null;
            try {
                Result.Companion companion = Result.INSTANCE;
                JsonValue byKeyAsObject3 = json.getByKeyAsObject(BarcodePickDeserializer.FIELD_ICON, null);
                Intrinsics.checkNotNull(byKeyAsObject3);
                m3945constructorimpl = Result.m3945constructorimpl(ScanditIconDeserializer.fromJson(byKeyAsObject3.jsonString()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m3951isFailureimpl(m3945constructorimpl)) {
                m3945constructorimpl = null;
            }
            ScanditIcon scanditIcon = (ScanditIcon) m3945constructorimpl;
            Bitmap bitmapFromBase64 = scanditIcon == null ? BitmapExtensionsKt.bitmapFromBase64(json.getByKeyAsString(BarcodePickDeserializer.FIELD_ICON, "")) : null;
            try {
                Result.Companion companion3 = Result.INSTANCE;
                JsonValue byKeyAsObject4 = json.getByKeyAsObject("selectedIcon", null);
                Intrinsics.checkNotNull(byKeyAsObject4);
                m3945constructorimpl2 = Result.m3945constructorimpl(ScanditIconDeserializer.fromJson(byKeyAsObject4.jsonString()));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                m3945constructorimpl2 = Result.m3945constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m3951isFailureimpl(m3945constructorimpl2)) {
                m3945constructorimpl2 = null;
            }
            ScanditIcon scanditIcon2 = (ScanditIcon) m3945constructorimpl2;
            Bitmap bitmapFromBase642 = scanditIcon2 == null ? BitmapExtensionsKt.bitmapFromBase64(json.getByKeyAsString("selectedIcon", "")) : null;
            JsonValue byKeyAsObject5 = json.getByKeyAsObject("statusIconStyle", null);
            BarcodePickStatusIconStyle fromJson3 = byKeyAsObject5 != null ? BarcodePickStatusIconStyle.INSTANCE.fromJson(byKeyAsObject5.jsonString()) : null;
            Builder builder = BarcodePickViewHighlightStyleResponse.INSTANCE.builder();
            builder.setBrush(fromJson);
            builder.setSelectedBrush(fromJson2);
            if (scanditIcon != null) {
                builder.setIcon(scanditIcon);
            } else {
                builder.setIcon(bitmapFromBase64);
            }
            if (scanditIcon2 != null) {
                builder.setSelectedIcon(scanditIcon2);
            } else {
                builder.setSelectedIcon(bitmapFromBase642);
            }
            builder.setStatusIconStyle(fromJson3);
            return builder.build();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickViewHighlightStyleResponse(com.scandit.datacapture.core.ui.style.Brush r9, com.scandit.datacapture.core.ui.style.Brush r10, int r11, int r12, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle r13) {
        /*
            r8 = this;
            java.lang.String r0 = "brush"
            if (r9 != 0) goto L8
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            r3 = r9
            goto L11
        L8:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r1 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r1.<init>(r9)
            r3 = r1
        L11:
            if (r10 != 0) goto L16
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            goto L1e
        L16:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r9 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r9.<init>(r10)
        L1e:
            r4 = r9
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r5 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r11, r9)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r6 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r12, r9)
            r2 = r8
            r7 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleResponse.<init>(com.scandit.datacapture.core.ui.style.Brush, com.scandit.datacapture.core.ui.style.Brush, int, int, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle):void");
    }

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    @JvmStatic
    public static final BarcodePickViewHighlightStyleResponse fromJson(String str) {
        return INSTANCE.fromJson(str);
    }

    /* renamed from: getBrush$scandit_barcode_capture, reason: from getter */
    public final BarcodePickBrush getBrush() {
        return this.brush;
    }

    /* renamed from: getIcon$scandit_barcode_capture, reason: from getter */
    public final BarcodePickIcon getIcon() {
        return this.icon;
    }

    /* renamed from: getSelectedBrush$scandit_barcode_capture, reason: from getter */
    public final BarcodePickBrush getSelectedBrush() {
        return this.selectedBrush;
    }

    /* renamed from: getSelectedIcon$scandit_barcode_capture, reason: from getter */
    public final BarcodePickIcon getSelectedIcon() {
        return this.selectedIcon;
    }

    /* renamed from: getStatusIconStyle$scandit_barcode_capture, reason: from getter */
    public final BarcodePickStatusIconStyle getStatusIconStyle() {
        return this.statusIconStyle;
    }

    public final String toJson() {
        String json;
        JSONObject jSONObject = new JSONObject();
        if (this.brush.asBrush() != null) {
            jSONObject.put("brush", new JSONObject(this.brush.toJson()));
        }
        if (this.selectedBrush.asBrush() != null) {
            jSONObject.put("selectedBrush", new JSONObject(this.selectedBrush.toJson()));
        }
        String json2 = this.icon.toJson();
        if (json2 != null) {
            if (this.icon instanceof com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.n) {
                jSONObject.put(BarcodePickDeserializer.FIELD_ICON, new JSONObject(json2));
            } else {
                jSONObject.put(BarcodePickDeserializer.FIELD_ICON, json2);
            }
        }
        String json3 = this.selectedIcon.toJson();
        if (json3 != null) {
            if (this.icon instanceof com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.n) {
                jSONObject.put("selectedIcon", new JSONObject(json3));
            } else {
                jSONObject.put("selectedIcon", json3);
            }
        }
        BarcodePickStatusIconStyle barcodePickStatusIconStyle = this.statusIconStyle;
        if (barcodePickStatusIconStyle != null && (json = barcodePickStatusIconStyle.toJson()) != null) {
            jSONObject.put("statusIconStyle", new JSONObject(json));
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\f\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000f\u0010\rJ\u0017\u0010\f\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\f\u0010\u0012J\u0017\u0010\u000f\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u000f\u0010\u0012J\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0014¢\u0006\u0004\b\f\u0010\u0015J\u0017\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u000f\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "", "<init>", "()V", "Lcom/scandit/datacapture/core/ui/style/Brush;", "brush", "setBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "selectedBrush", "setSelectedBrush", "Landroid/graphics/Bitmap;", BarcodePickDeserializer.FIELD_ICON, "setIcon", "(Landroid/graphics/Bitmap;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "selectedIcon", "setSelectedIcon", "", "iconResource", "(I)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "selectedIconResource", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "statusIconStyle", "setStatusIconStyle", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse$Builder;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse;", "build", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleResponse;", "com/scandit/datacapture/barcode/pick/ui/A", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Builder {
        private Brush a;
        private Brush b;
        private Bitmap c;
        private Bitmap d;
        private int e;
        private int f;
        private ScanditIcon g;
        private ScanditIcon h;
        private BarcodePickStatusIconStyle i;
        private A j;
        private A k;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[A.values().length];
                try {
                    A a = A.a;
                    iArr[1] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    A a2 = A.a;
                    iArr[2] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    A a3 = A.a;
                    iArr[3] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    A a4 = A.a;
                    iArr[0] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Builder() {
            A a = A.a;
            this.j = a;
            this.k = a;
        }

        public final BarcodePickViewHighlightStyleResponse build() {
            BarcodePickIcon barcodePickIcon;
            BarcodePickIcon barcodePickIcon2;
            BarcodePickBrush pVar;
            BarcodePickBrush pVar2;
            int ordinal = this.j.ordinal();
            if (ordinal == 0) {
                barcodePickIcon = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a;
            } else if (ordinal == 1) {
                barcodePickIcon = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.c, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a);
            } else if (ordinal == 2) {
                barcodePickIcon = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.e, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a);
            } else {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                barcodePickIcon = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.g, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a);
            }
            BarcodePickIcon barcodePickIcon3 = barcodePickIcon;
            int ordinal2 = this.k.ordinal();
            if (ordinal2 == 0) {
                barcodePickIcon2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a;
            } else if (ordinal2 == 1) {
                barcodePickIcon2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.d, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a);
            } else if (ordinal2 == 2) {
                barcodePickIcon2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.f, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a);
            } else {
                if (ordinal2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                barcodePickIcon2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(this.h, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a);
            }
            BarcodePickIcon barcodePickIcon4 = barcodePickIcon2;
            Brush brush = this.a;
            if (brush == null) {
                pVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b;
            } else {
                Intrinsics.checkNotNullParameter(brush, "brush");
                pVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p(brush);
            }
            Brush brush2 = this.b;
            if (brush2 == null) {
                pVar2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b;
            } else {
                Intrinsics.checkNotNullParameter(brush2, "brush");
                pVar2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p(brush2);
            }
            return new BarcodePickViewHighlightStyleResponse(pVar, pVar2, barcodePickIcon3, barcodePickIcon4, this.i);
        }

        public final Builder setBrush(Brush brush) {
            this.a = brush;
            return this;
        }

        public final Builder setIcon(Bitmap icon) {
            this.c = icon;
            this.j = A.b;
            return this;
        }

        public final Builder setSelectedBrush(Brush selectedBrush) {
            this.b = selectedBrush;
            return this;
        }

        public final Builder setSelectedIcon(Bitmap selectedIcon) {
            this.d = selectedIcon;
            this.k = A.b;
            return this;
        }

        public final Builder setStatusIconStyle(BarcodePickStatusIconStyle statusIconStyle) {
            this.i = statusIconStyle;
            return this;
        }

        public final Builder setIcon(int iconResource) {
            this.e = iconResource;
            this.j = A.c;
            return this;
        }

        public final Builder setSelectedIcon(int selectedIconResource) {
            this.f = selectedIconResource;
            this.k = A.c;
            return this;
        }

        public final Builder setIcon(ScanditIcon icon) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            this.g = icon;
            this.j = A.d;
            return this;
        }

        public final Builder setSelectedIcon(ScanditIcon selectedIcon) {
            this.h = selectedIcon;
            this.k = A.d;
            return this;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickViewHighlightStyleResponse(com.scandit.datacapture.core.ui.style.Brush r9, com.scandit.datacapture.core.ui.style.Brush r10, android.graphics.Bitmap r11, android.graphics.Bitmap r12, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle r13) {
        /*
            r8 = this;
            java.lang.String r0 = "brush"
            if (r9 != 0) goto L8
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            r3 = r9
            goto L11
        L8:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r1 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r1.<init>(r9)
            r3 = r1
        L11:
            if (r10 != 0) goto L16
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            goto L1e
        L16:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r9 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r9.<init>(r10)
        L1e:
            r4 = r9
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r5 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r11, r9)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r6 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r12, r9)
            r2 = r8
            r7 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleResponse.<init>(com.scandit.datacapture.core.ui.style.Brush, com.scandit.datacapture.core.ui.style.Brush, android.graphics.Bitmap, android.graphics.Bitmap, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodePickViewHighlightStyleResponse(com.scandit.datacapture.core.ui.style.Brush r9, com.scandit.datacapture.core.ui.style.Brush r10, com.scandit.datacapture.core.ui.icon.ScanditIcon r11, com.scandit.datacapture.core.ui.icon.ScanditIcon r12, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle r13) {
        /*
            r8 = this;
            java.lang.String r0 = "icon"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "brush"
            if (r9 != 0) goto Ld
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            r3 = r9
            goto L16
        Ld:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r1 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r1.<init>(r9)
            r3 = r1
        L16:
            if (r10 != 0) goto L1b
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k.b
            goto L23
        L1b:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p r9 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p
            r9.<init>(r10)
        L23:
            r4 = r9
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r5 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r11, r9)
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m r9 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m.a
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon r6 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.i.a(r12, r9)
            r2 = r8
            r7 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleResponse.<init>(com.scandit.datacapture.core.ui.style.Brush, com.scandit.datacapture.core.ui.style.Brush, com.scandit.datacapture.core.ui.icon.ScanditIcon, com.scandit.datacapture.core.ui.icon.ScanditIcon, com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle):void");
    }

    public BarcodePickViewHighlightStyleResponse(BarcodePickBrush brush, BarcodePickBrush selectedBrush, BarcodePickIcon icon, BarcodePickIcon selectedIcon, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(selectedBrush, "selectedBrush");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(selectedIcon, "selectedIcon");
        this.brush = brush;
        this.selectedBrush = selectedBrush;
        this.icon = icon;
        this.selectedIcon = selectedIcon;
        this.statusIconStyle = barcodePickStatusIconStyle;
    }

    public BarcodePickViewHighlightStyleResponse(Brush brush, Bitmap bitmap, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        this(brush, (Brush) null, bitmap, (Bitmap) null, barcodePickStatusIconStyle);
    }

    public BarcodePickViewHighlightStyleResponse(Brush brush, int i, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        this(brush, (Brush) null, i, 0, barcodePickStatusIconStyle);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BarcodePickViewHighlightStyleResponse(Brush brush, ScanditIcon icon, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        this(brush, (Brush) null, icon, (ScanditIcon) null, barcodePickStatusIconStyle);
        Intrinsics.checkNotNullParameter(icon, "icon");
    }
}
