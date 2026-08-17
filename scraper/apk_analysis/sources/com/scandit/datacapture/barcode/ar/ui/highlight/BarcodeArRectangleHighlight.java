package com.scandit.datacapture.barcode.ar.ui.highlight;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Vector;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.style.Brush;
import com.tealium.library.DataSources;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 &2\u00020\u0001:\u0001&B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R*\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010%\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArRectangleHighlight;", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlight;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/data/Barcode;)V", "Landroid/view/View;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "barcodeLocation", "", "update", "(Landroid/view/View;Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)V", "createView", "()Landroid/view/View;", "b", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "Lcom/scandit/datacapture/core/ui/style/Brush;", "value", "c", "Lcom/scandit/datacapture/core/ui/style/Brush;", "getBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "setBrush", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "brush", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "d", "Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "getIcon", "()Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;", "setIcon", "(Lcom/scandit/datacapture/core/ui/icon/ScanditIcon;)V", BarcodePickDeserializer.FIELD_ICON, "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArRectangleHighlight implements BarcodeArHighlight {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy f = LazyKt.lazy(d.a);
    private static final Lazy g = LazyKt.lazy(c.a);
    private static final Lazy h = LazyKt.lazy(b.a);
    private final Context a;

    /* renamed from: b, reason: from kotlin metadata */
    private final Barcode barcode;

    /* renamed from: c, reason: from kotlin metadata */
    private Brush brush;

    /* renamed from: d, reason: from kotlin metadata */
    private ScanditIcon icon;
    private GradientDrawable e;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001R!\u0010\t\u001a\u00020\u00028@X\u0081\u0084\u0002¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R!\u0010\r\u001a\u00020\u00028@X\u0081\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u0012\u0004\b\f\u0010\b\u001a\u0004\b\u000b\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArRectangleHighlight$Companion;", "", "", "MIN_WIDTH$delegate", "Lkotlin/Lazy;", "getMIN_WIDTH$scandit_barcode_capture", "()F", "getMIN_WIDTH$scandit_barcode_capture$annotations", "()V", "MIN_WIDTH", "MIN_HEIGHT$delegate", "getMIN_HEIGHT$scandit_barcode_capture", "getMIN_HEIGHT$scandit_barcode_capture$annotations", "MIN_HEIGHT", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final int access$getICON_SIZE(Companion companion) {
            companion.getClass();
            return ((Number) BarcodeArRectangleHighlight.h.getValue()).intValue();
        }

        public static /* synthetic */ void getMIN_HEIGHT$scandit_barcode_capture$annotations() {
        }

        public static /* synthetic */ void getMIN_WIDTH$scandit_barcode_capture$annotations() {
        }

        public final float getMIN_HEIGHT$scandit_barcode_capture() {
            return ((Number) BarcodeArRectangleHighlight.g.getValue()).floatValue();
        }

        public final float getMIN_WIDTH$scandit_barcode_capture() {
            return ((Number) BarcodeArRectangleHighlight.f.getValue()).floatValue();
        }
    }

    public BarcodeArRectangleHighlight(Context context, Barcode barcode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        this.a = context;
        this.barcode = barcode;
        this.brush = BarcodeArViewDefaults.getDefaultRectangleHighlightBrush();
        this.icon = BarcodeArViewDefaults.getDefaultHighlightIcon();
        Brush brush = this.brush;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke((int) PixelExtensionsKt.pxFromDp$default(brush.getStrokeWidth(), (Context) null, 1, (Object) null), ColorStateList.valueOf(brush.getStrokeColor()));
        gradientDrawable.setColor(brush.getFillColor());
        this.e = gradientDrawable;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight
    public View createView() {
        RelativeLayout relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setBackground(this.e);
        relativeLayout.setContentDescription(relativeLayout.getResources().getString(R.string.sc_barcode_ar_highlight_rectangle_content_description, this.barcode.getData()));
        ImageView imageView = new ImageView(relativeLayout.getContext());
        imageView.setId(R.id.sc_ar_rectangle_highlight_icon);
        Companion companion = INSTANCE;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Companion.access$getICON_SIZE(companion), Companion.access$getICON_SIZE(companion));
        layoutParams.addRule(13);
        relativeLayout.addView(imageView, layoutParams);
        ScanditIcon scanditIcon = this.icon;
        if (scanditIcon != null) {
            Context context = relativeLayout.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            imageView.setImageDrawable(ScanditIconUtilsKt.getDrawable(scanditIcon, context));
        }
        return relativeLayout;
    }

    public final Barcode getBarcode() {
        return this.barcode;
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final ScanditIcon getIcon() {
        return this.icon;
    }

    public final void setBrush(Brush value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.brush = value;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke((int) PixelExtensionsKt.pxFromDp$default(value.getStrokeWidth(), (Context) null, 1, (Object) null), ColorStateList.valueOf(value.getStrokeColor()));
        gradientDrawable.setColor(value.getFillColor());
        this.e = gradientDrawable;
    }

    public final void setIcon(ScanditIcon scanditIcon) {
        this.icon = scanditIcon;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight
    public void update(View view, Quadrilateral barcodeLocation) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        float width = QuadrilateralUtilsKt.getWidth(barcodeLocation);
        Companion companion = INSTANCE;
        int coerceAtLeast = (int) RangesKt.coerceAtLeast(width, companion.getMIN_WIDTH$scandit_barcode_capture());
        int coerceAtLeast2 = (int) RangesKt.coerceAtLeast(QuadrilateralUtilsKt.getHeight(barcodeLocation), companion.getMIN_HEIGHT$scandit_barcode_capture());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(0, 0);
        }
        layoutParams.width = coerceAtLeast;
        layoutParams.height = coerceAtLeast2;
        view.setLayoutParams(layoutParams);
        view.setX(QuadrilateralUtilsKt.getCenter(barcodeLocation).getX() - (coerceAtLeast / 2.0f));
        view.setY(QuadrilateralUtilsKt.getCenter(barcodeLocation).getY() - (coerceAtLeast2 / 2.0f));
        view.setBackground(this.e);
        ImageView imageView = (ImageView) view.findViewById(R.id.sc_ar_rectangle_highlight_icon);
        ScanditIcon scanditIcon = this.icon;
        imageView.setImageDrawable(scanditIcon != null ? ScanditIconUtilsKt.getDrawable(scanditIcon, this.a) : null);
        Point topLeft = barcodeLocation.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point topRight = barcodeLocation.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        view.setRotation(new Vector(topLeft, topRight).getAngleDegrees());
    }
}
