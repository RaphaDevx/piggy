package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.AnchorExtensionsKt;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class n1 implements i1 {
    public static final /* synthetic */ KProperty[] h = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(n1.class, "showTorchButton", "getShowTorchButton()Z", 0)};
    public final Context a;
    public final BarcodeCountViewUiLayoutHelper b;
    public final BarcodeCountDataCaptureViewHolder c;
    public final m1 d;
    public Anchor e;
    public Set f;
    public final TorchSwitchControl g;

    public n1(Context context, C0353h layoutHelper, C0339a controlHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(controlHandler, "controlHandler");
        this.a = context;
        this.b = layoutHelper;
        this.c = controlHandler;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        Boolean valueOf = Boolean.valueOf(barcodeCountViewDefaults.getShouldShowTorchControl());
        Delegates delegates = Delegates.INSTANCE;
        this.d = new m1(valueOf, this);
        this.e = barcodeCountViewDefaults.getTorchControlPosition();
        this.f = SetsKt.emptySet();
        this.g = new TorchSwitchControl(context);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        c();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
    }

    public final void c() {
        Anchor anchor;
        PointWithUnit PointWithUnit;
        this.c.a(this.g);
        if (((Boolean) this.d.getValue(this, h[0])).booleanValue()) {
            boolean a = com.scandit.datacapture.barcode.internal.module.extensions.b.a(this.a);
            Anchor anchor2 = this.e;
            if (a) {
                int i = j1.a[anchor2.ordinal()];
                anchor = AnchorExtensionsKt.rotate90DegreesCounterClockwise(i != 1 ? i != 2 ? i != 3 ? i != 4 ? Anchor.TOP_LEFT : Anchor.BOTTOM_RIGHT : Anchor.TOP_RIGHT : Anchor.BOTTOM_LEFT : Anchor.TOP_LEFT);
            } else {
                int i2 = j1.a[anchor2.ordinal()];
                anchor = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? Anchor.TOP_LEFT : Anchor.BOTTOM_RIGHT : Anchor.TOP_RIGHT : Anchor.BOTTOM_LEFT : Anchor.TOP_LEFT;
            }
            if (a) {
                k1 k1Var = new k1(this);
                int i3 = j1.a[anchor.ordinal()];
                if (i3 == 1 || i3 == 2) {
                    PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(AbstractC0341b.i, this.b.g() + AbstractC0341b.f, MeasureUnit.PIXEL);
                } else if (i3 == 3) {
                    PointWithUnit = (PointWithUnit) k1Var.invoke(Boolean.valueOf(this.f.contains(Anchor.BOTTOM_RIGHT)));
                } else {
                    if (i3 != 4) {
                        throw new IllegalStateException("Unsupported anchor".toString());
                    }
                    PointWithUnit = (PointWithUnit) k1Var.invoke(Boolean.valueOf(this.f.contains(Anchor.BOTTOM_LEFT)));
                }
            } else {
                l1 l1Var = new l1(this);
                int i4 = j1.a[anchor.ordinal()];
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 != 4) {
                                throw new IllegalStateException(("Unreachable anchor " + anchor).toString());
                            }
                        }
                    }
                    PointWithUnit = (PointWithUnit) l1Var.invoke(Boolean.valueOf(this.f.contains(anchor)));
                }
                float f = AbstractC0341b.g;
                PointWithUnit = PointWithUnitUtilsKt.PointWithUnit(f, f, MeasureUnit.PIXEL);
            }
            Pair pair = TuplesKt.to(anchor, PointWithUnit);
            this.c.a(this.g, (Anchor) pair.component1(), (PointWithUnit) pair.component2());
        }
    }
}
