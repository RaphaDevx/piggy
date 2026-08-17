package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewDefaults;
import com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategory;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0407s;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0408t;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0409u;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0410v;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public final ScreenSizeCategoryHandler a;
    public final PreviewResolutionRatioHandler b;
    public final Function0 c;
    public final Function0 d;
    public final Function0 e;
    public final Function0 f;
    public final Function1 g;
    public TorchSwitchControl h;

    public g(ScreenSizeCategoryHandler screenSizeHandler, PreviewResolutionRatioHandler previewResolutionHandler, C0407s isShowingProgressBar, C0408t isShowingTorchButton, C0410v isShowingFinishButton, C0409u torchButtonPosition, Function1 isTallFormFactor) {
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(previewResolutionHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(isShowingProgressBar, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(isShowingTorchButton, "isShowingTorchButton");
        Intrinsics.checkNotNullParameter(isShowingFinishButton, "isShowingFinishButton");
        Intrinsics.checkNotNullParameter(torchButtonPosition, "torchButtonPosition");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        this.a = screenSizeHandler;
        this.b = previewResolutionHandler;
        this.c = isShowingProgressBar;
        this.d = isShowingTorchButton;
        this.e = isShowingFinishButton;
        this.f = torchButtonPosition;
        this.g = isTallFormFactor;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        int min;
        int i;
        PointWithUnit PointWithUnit;
        DataCaptureView view2 = (DataCaptureView) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        boolean closeToFourByThree = this.b.getCloseToFourByThree();
        float ratio = this.b.getRatio();
        boolean booleanValue = ((Boolean) this.g.invoke(parent)).booleanValue();
        boolean booleanValue2 = ((Boolean) this.e.invoke()).booleanValue();
        boolean booleanValue3 = ((Boolean) this.c.invoke()).booleanValue();
        boolean booleanValue4 = ((Boolean) this.d.invoke()).booleanValue();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        if (closeToFourByThree) {
            min = Math.min(parent.getWidth(), parent.getHeight());
            i = (min <= 0 || ratio <= 0.0f) ? 0 : (int) (min * ratio);
            if (booleanValue) {
                min = i;
                i = min;
            }
        } else {
            i = parent.getWidth();
            min = parent.getHeight();
        }
        Size size = new Size(i, min);
        layoutParams.width = size.getWidth();
        layoutParams.height = size.getHeight();
        this.a.onPreviewSizeMeasured(size, new Size(parent.getWidth(), parent.getHeight()));
        ScreenSizeCategory screenCategory = this.a.getScreenCategory();
        view2.setLogoOffset(((!closeToFourByThree && booleanValue && booleanValue2) || (closeToFourByThree && screenCategory == ScreenSizeCategory.SMALL && booleanValue2)) ? PointWithUnitUtilsKt.PointWithUnit(0.0f, -80.0f, MeasureUnit.DIP) : PointWithUnitUtilsKt.PointWithUnit(0.0f, 0.0f, MeasureUnit.PIXEL));
        if (closeToFourByThree) {
            if (booleanValue) {
                int i2 = AbstractC0417f.a[screenCategory.ordinal()];
                if (i2 == 1 || i2 == 2) {
                    Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                    layoutParams.addRule(10);
                } else if (i2 == 3) {
                    Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                    layoutParams.addRule(13);
                }
            } else {
                int i3 = AbstractC0417f.a[screenCategory.ordinal()];
                if (i3 == 1 || i3 == 2) {
                    Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                    layoutParams.addRule(20);
                } else if (i3 == 3) {
                    Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                    layoutParams.addRule(13);
                }
            }
        }
        TorchSwitchControl torchSwitchControl = this.h;
        if (torchSwitchControl == null) {
            Context context = view2.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            torchSwitchControl = new TorchSwitchControl(context);
            this.h = torchSwitchControl;
        }
        view2.removeControl(torchSwitchControl);
        if (booleanValue4) {
            Anchor anchor = (Anchor) this.f.invoke();
            Map map = com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.utils.a.a;
            Intrinsics.checkNotNullParameter(anchor, "<this>");
            Map map2 = com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.utils.a.a;
            if (!map2.containsKey(anchor)) {
                anchor = BarcodeFindViewDefaults.getDefaultTorchControlPosition();
            }
            if (!booleanValue) {
                if (booleanValue) {
                    throw new NoWhenBranchMatchedException();
                }
                anchor = (Anchor) map2.get(anchor);
                if (anchor == null) {
                    anchor = BarcodeFindViewDefaults.INSTANCE.getDefaultTorchControlPositionLandscape$scandit_barcode_capture();
                }
            }
            if (booleanValue) {
                boolean z = booleanValue3 && !(screenCategory == ScreenSizeCategory.LARGE && closeToFourByThree);
                int i4 = AbstractC0417f.b[anchor.ordinal()];
                if (i4 == 1) {
                    PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(16.0f, (z ? 40.0f : 0.0f) + 16.0f, MeasureUnit.DIP);
                } else if (i4 == 2) {
                    PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(0.0f, (z ? 40.0f : 0.0f) + 16.0f, MeasureUnit.DIP);
                } else if (i4 != 3) {
                    PointWithUnit = i4 != 4 ? PointWithUnitUtilsKt.PointWithUnit(0.0f, 0.0f, MeasureUnit.PIXEL) : PointWithUnitUtilsKt.PointWithUnit(16.0f, 34.0f, MeasureUnit.DIP);
                } else {
                    PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(16.0f, (z ? 40.0f : 0.0f) + 16.0f, MeasureUnit.DIP);
                }
            } else {
                int i5 = AbstractC0417f.b[anchor.ordinal()];
                if (i5 != 1) {
                    PointWithUnit = i5 != 4 ? i5 != 5 ? i5 != 6 ? i5 != 7 ? PointWithUnitUtilsKt.PointWithUnit(0.0f, 0.0f, MeasureUnit.PIXEL) : PointWithUnitUtilsKt.PointWithUnit(34.0f, 36.0f, MeasureUnit.DIP) : PointWithUnitUtilsKt.PointWithUnit(0.0f, 36.0f, MeasureUnit.DIP) : PointWithUnitUtilsKt.PointWithUnit(16.0f, 0.0f, MeasureUnit.DIP) : PointWithUnitUtilsKt.PointWithUnit(16.0f, 36.0f, MeasureUnit.DIP);
                } else {
                    if (booleanValue3 && closeToFourByThree) {
                        r11 = 40.0f;
                    }
                    PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(16.0f, r11 + 16.0f, MeasureUnit.DIP);
                }
            }
            view2.addControl(torchSwitchControl, anchor, PointWithUnit);
        }
        view2.setLayoutParams(layoutParams);
    }
}
