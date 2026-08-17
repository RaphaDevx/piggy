package com.scandit.datacapture.barcode.pick.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconDeserializer;
import com.scandit.datacapture.core.ui.icon.ScanditIconSerializer;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00052\u00020\u0001:\u0004\u0006\u0007\b\u0005J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "", "", "toJson", "()Ljava/lang/String;", "Companion", "BarcodePickStatusIconStyleWithColors", "BarcodePickStatusIconStyleWithIcon", "BarcodePickStatusIconStyleWithScanditIcon", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithColors;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithIcon;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithScanditIcon;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public abstract class BarcodePickStatusIconStyle {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000e\u0010\nJ.\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0011\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\fJ\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0019\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\n¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithColors;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "", "iconColor", "iconBackgroundColor", "", "text", "<init>", "(IILjava/lang/String;)V", "toJson", "()Ljava/lang/String;", "component1", "()I", "component2", "component3", "copy", "(IILjava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithColors;", "toString", "hashCode", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "I", "getIconColor", "b", "getIconBackgroundColor", "c", "Ljava/lang/String;", "getText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final /* data */ class BarcodePickStatusIconStyleWithColors extends BarcodePickStatusIconStyle {

        /* renamed from: a, reason: from kotlin metadata */
        private final int iconColor;

        /* renamed from: b, reason: from kotlin metadata */
        private final int iconBackgroundColor;

        /* renamed from: c, reason: from kotlin metadata */
        private final String text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BarcodePickStatusIconStyleWithColors(int i, int i2, String text) {
            super(null);
            Intrinsics.checkNotNullParameter(text, "text");
            this.iconColor = i;
            this.iconBackgroundColor = i2;
            this.text = text;
        }

        public static /* synthetic */ BarcodePickStatusIconStyleWithColors copy$default(BarcodePickStatusIconStyleWithColors barcodePickStatusIconStyleWithColors, int i, int i2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = barcodePickStatusIconStyleWithColors.iconColor;
            }
            if ((i3 & 2) != 0) {
                i2 = barcodePickStatusIconStyleWithColors.iconBackgroundColor;
            }
            if ((i3 & 4) != 0) {
                str = barcodePickStatusIconStyleWithColors.text;
            }
            return barcodePickStatusIconStyleWithColors.copy(i, i2, str);
        }

        /* renamed from: component1, reason: from getter */
        public final int getIconColor() {
            return this.iconColor;
        }

        /* renamed from: component2, reason: from getter */
        public final int getIconBackgroundColor() {
            return this.iconBackgroundColor;
        }

        /* renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final BarcodePickStatusIconStyleWithColors copy(int iconColor, int iconBackgroundColor, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithColors(iconColor, iconBackgroundColor, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodePickStatusIconStyleWithColors)) {
                return false;
            }
            BarcodePickStatusIconStyleWithColors barcodePickStatusIconStyleWithColors = (BarcodePickStatusIconStyleWithColors) other;
            return this.iconColor == barcodePickStatusIconStyleWithColors.iconColor && this.iconBackgroundColor == barcodePickStatusIconStyleWithColors.iconBackgroundColor && Intrinsics.areEqual(this.text, barcodePickStatusIconStyleWithColors.text);
        }

        public final int getIconBackgroundColor() {
            return this.iconBackgroundColor;
        }

        public final int getIconColor() {
            return this.iconColor;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return this.text.hashCode() + ((Integer.hashCode(this.iconBackgroundColor) + (Integer.hashCode(this.iconColor) * 31)) * 31);
        }

        @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle
        public String toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "colors");
            jSONObject.put("text", this.text);
            jSONObject.put("iconColor", this.iconColor);
            jSONObject.put("iconBackgroundColor", this.iconBackgroundColor);
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            return jSONObject2;
        }

        public String toString() {
            return "BarcodePickStatusIconStyleWithColors(iconColor=" + this.iconColor + ", iconBackgroundColor=" + this.iconBackgroundColor + ", text=" + this.text + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\tJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000f\u0010\tJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\t¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithIcon;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Landroid/graphics/Bitmap;", BarcodePickDeserializer.FIELD_ICON, "", "text", "<init>", "(Landroid/graphics/Bitmap;Ljava/lang/String;)V", "toJson", "()Ljava/lang/String;", "component1", "()Landroid/graphics/Bitmap;", "component2", "copy", "(Landroid/graphics/Bitmap;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithIcon;", "toString", "", "hashCode", "()I", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Landroid/graphics/Bitmap;", "getIcon", "b", "Ljava/lang/String;", "getText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final /* data */ class BarcodePickStatusIconStyleWithIcon extends BarcodePickStatusIconStyle {

        /* renamed from: a, reason: from kotlin metadata */
        private final Bitmap icon;

        /* renamed from: b, reason: from kotlin metadata */
        private final String text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BarcodePickStatusIconStyleWithIcon(Bitmap icon, String text) {
            super(null);
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            this.icon = icon;
            this.text = text;
        }

        public static /* synthetic */ BarcodePickStatusIconStyleWithIcon copy$default(BarcodePickStatusIconStyleWithIcon barcodePickStatusIconStyleWithIcon, Bitmap bitmap, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                bitmap = barcodePickStatusIconStyleWithIcon.icon;
            }
            if ((i & 2) != 0) {
                str = barcodePickStatusIconStyleWithIcon.text;
            }
            return barcodePickStatusIconStyleWithIcon.copy(bitmap, str);
        }

        /* renamed from: component1, reason: from getter */
        public final Bitmap getIcon() {
            return this.icon;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final BarcodePickStatusIconStyleWithIcon copy(Bitmap icon, String text) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithIcon(icon, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodePickStatusIconStyleWithIcon)) {
                return false;
            }
            BarcodePickStatusIconStyleWithIcon barcodePickStatusIconStyleWithIcon = (BarcodePickStatusIconStyleWithIcon) other;
            return Intrinsics.areEqual(this.icon, barcodePickStatusIconStyleWithIcon.icon) && Intrinsics.areEqual(this.text, barcodePickStatusIconStyleWithIcon.text);
        }

        public final Bitmap getIcon() {
            return this.icon;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return this.text.hashCode() + (this.icon.hashCode() * 31);
        }

        @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle
        public String toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", BarcodePickDeserializer.FIELD_ICON);
            jSONObject.put("text", this.text);
            jSONObject.put(BarcodePickDeserializer.FIELD_ICON, BitmapExtensionsKt.toBase64(this.icon));
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            return jSONObject2;
        }

        public String toString() {
            return "BarcodePickStatusIconStyleWithIcon(icon=" + this.icon + ", text=" + this.text + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\tJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000f\u0010\tJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\t¨\u0006\u001e"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithScanditIcon;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", BarcodePickDeserializer.FIELD_ICON, "", "text", "<init>", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Ljava/lang/String;)V", "toJson", "()Ljava/lang/String;", "component1", "()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "component2", "copy", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$BarcodePickStatusIconStyleWithScanditIcon;", "toString", "", "hashCode", "()I", "", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "getIcon", "b", "Ljava/lang/String;", "getText", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final /* data */ class BarcodePickStatusIconStyleWithScanditIcon extends BarcodePickStatusIconStyle {

        /* renamed from: a, reason: from kotlin metadata */
        private final ScanditIcon icon;

        /* renamed from: b, reason: from kotlin metadata */
        private final String text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BarcodePickStatusIconStyleWithScanditIcon(ScanditIcon icon, String text) {
            super(null);
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            this.icon = icon;
            this.text = text;
        }

        public static /* synthetic */ BarcodePickStatusIconStyleWithScanditIcon copy$default(BarcodePickStatusIconStyleWithScanditIcon barcodePickStatusIconStyleWithScanditIcon, ScanditIcon scanditIcon, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                scanditIcon = barcodePickStatusIconStyleWithScanditIcon.icon;
            }
            if ((i & 2) != 0) {
                str = barcodePickStatusIconStyleWithScanditIcon.text;
            }
            return barcodePickStatusIconStyleWithScanditIcon.copy(scanditIcon, str);
        }

        /* renamed from: component1, reason: from getter */
        public final ScanditIcon getIcon() {
            return this.icon;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final BarcodePickStatusIconStyleWithScanditIcon copy(ScanditIcon icon, String text) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithScanditIcon(icon, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodePickStatusIconStyleWithScanditIcon)) {
                return false;
            }
            BarcodePickStatusIconStyleWithScanditIcon barcodePickStatusIconStyleWithScanditIcon = (BarcodePickStatusIconStyleWithScanditIcon) other;
            return Intrinsics.areEqual(this.icon, barcodePickStatusIconStyleWithScanditIcon.icon) && Intrinsics.areEqual(this.text, barcodePickStatusIconStyleWithScanditIcon.text);
        }

        public final ScanditIcon getIcon() {
            return this.icon;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return this.text.hashCode() + (this.icon.hashCode() * 31);
        }

        @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle
        public String toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", BarcodePickDeserializer.FIELD_ICON);
            jSONObject.put("text", this.text);
            jSONObject.put(BarcodePickDeserializer.FIELD_ICON, new JSONObject(ScanditIconSerializer.toJson(this.icon)));
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            return jSONObject2;
        }

        public String toString() {
            return "BarcodePickStatusIconStyleWithScanditIcon(icon=" + this.icon + ", text=" + this.text + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\n\u0010\rJ'\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle$Companion;", "", "", "jsonData", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Landroid/graphics/Bitmap;", BarcodePickDeserializer.FIELD_ICON, "text", "withIcon", "(Landroid/graphics/Bitmap;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "", "iconColor", "iconBackgroundColor", "withColors", "(IILjava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final BarcodePickStatusIconStyle fromJson(String jsonData) {
            Object m3945constructorimpl;
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JsonValue jsonValue = new JsonValue(jsonData);
            String requireByKeyAsString = jsonValue.requireByKeyAsString("type");
            String requireByKeyAsString2 = jsonValue.requireByKeyAsString("text");
            if (!Intrinsics.areEqual(requireByKeyAsString, BarcodePickDeserializer.FIELD_ICON)) {
                if (Intrinsics.areEqual(requireByKeyAsString, "colors")) {
                    return withColors(jsonValue.requireByKeyAsInt("iconColor"), jsonValue.requireByKeyAsInt("iconBackgroundColor"), requireByKeyAsString2);
                }
                throw new IllegalStateException((requireByKeyAsString + " is not a valid BarcodePickStatusIconStyle type").toString());
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                JsonValue byKeyAsObject = jsonValue.getByKeyAsObject(BarcodePickDeserializer.FIELD_ICON, null);
                Intrinsics.checkNotNull(byKeyAsObject);
                m3945constructorimpl = Result.m3945constructorimpl(ScanditIconDeserializer.fromJson(byKeyAsObject.jsonString()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m3952isSuccessimpl(m3945constructorimpl)) {
                return BarcodePickStatusIconStyle.INSTANCE.withIcon((ScanditIcon) m3945constructorimpl, requireByKeyAsString2);
            }
            Bitmap bitmapFromBase64 = BitmapExtensionsKt.bitmapFromBase64(jsonValue.requireByKeyAsString(BarcodePickDeserializer.FIELD_ICON));
            if (bitmapFromBase64 == null) {
                bitmapFromBase64 = BitmapFactory.decodeResource(AppAndroidEnvironment.INSTANCE.getApplicationContext().getResources(), R.drawable.sc_pick_status_icon_default);
                Intrinsics.checkNotNullExpressionValue(bitmapFromBase64, "decodeResource(...)");
            }
            return withIcon(bitmapFromBase64, requireByKeyAsString2);
        }

        @JvmStatic
        public final BarcodePickStatusIconStyle withColors(int iconColor, int iconBackgroundColor, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithColors(iconColor, iconBackgroundColor, text);
        }

        @JvmStatic
        public final BarcodePickStatusIconStyle withIcon(Bitmap icon, String text) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithIcon(icon, text);
        }

        @JvmStatic
        public final BarcodePickStatusIconStyle withIcon(ScanditIcon icon, String text) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(text, "text");
            return new BarcodePickStatusIconStyleWithScanditIcon(icon, text);
        }
    }

    public BarcodePickStatusIconStyle(DefaultConstructorMarker defaultConstructorMarker) {
    }

    @JvmStatic
    public static final BarcodePickStatusIconStyle fromJson(String str) {
        return INSTANCE.fromJson(str);
    }

    @JvmStatic
    public static final BarcodePickStatusIconStyle withColors(int i, int i2, String str) {
        return INSTANCE.withColors(i, i2, str);
    }

    @JvmStatic
    public static final BarcodePickStatusIconStyle withIcon(Bitmap bitmap, String str) {
        return INSTANCE.withIcon(bitmap, str);
    }

    public abstract String toJson();

    @JvmStatic
    public static final BarcodePickStatusIconStyle withIcon(ScanditIcon scanditIcon, String str) {
        return INSTANCE.withIcon(scanditIcon, str);
    }
}
