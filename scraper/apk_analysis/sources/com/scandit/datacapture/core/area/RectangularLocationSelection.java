package com.scandit.datacapture.core.area;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.SizeWithUnit;
import com.scandit.datacapture.core.common.geometry.SizeWithUnitAndAspect;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.area.NativeLocationSelection;
import com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection;
import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0004H\u0097\u0001J\t\u0010\u000b\u001a\u00020\fH\u0097\u0001J\t\u0010\r\u001a\u00020\u000eH\u0097\u0001R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/core/area/RectangularLocationSelection;", "Lcom/scandit/datacapture/core/area/LocationSelection;", "Lcom/scandit/datacapture/core/area/RectangularLocationSelectionProxy;", "impl", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;", "(Lcom/scandit/datacapture/core/internal/sdk/area/NativeRectangularLocationSelection;)V", "sizeWithUnitAndAspect", "Lcom/scandit/datacapture/core/common/geometry/SizeWithUnitAndAspect;", "getSizeWithUnitAndAspect", "()Lcom/scandit/datacapture/core/common/geometry/SizeWithUnitAndAspect;", "_impl", "_locationSelectionImpl", "Lcom/scandit/datacapture/core/internal/sdk/area/NativeLocationSelection;", "toJson", "", "Companion", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@Mockable
/* loaded from: classes2.dex */
public final class RectangularLocationSelection implements LocationSelection, RectangularLocationSelectionProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ RectangularLocationSelectionProxyAdapter a;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000f\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/core/area/RectangularLocationSelection$Companion;", "", "Lcom/scandit/datacapture/core/common/geometry/SizeWithUnit;", RRWebVideoEvent.JsonKeys.SIZE, "Lcom/scandit/datacapture/core/area/RectangularLocationSelection;", "withSize", "(Lcom/scandit/datacapture/core/common/geometry/SizeWithUnit;)Lcom/scandit/datacapture/core/area/RectangularLocationSelection;", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "width", "", "heightToWidthAspectRatio", "withWidthAndAspectRatio", "(Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;F)Lcom/scandit/datacapture/core/area/RectangularLocationSelection;", "height", "widthToHeightAspectRatio", "withHeightAndAspectRatio", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final RectangularLocationSelection withHeightAndAspectRatio(FloatWithUnit height, float widthToHeightAspectRatio) {
            Intrinsics.checkNotNullParameter(height, "height");
            NativeRectangularLocationSelection create = NativeRectangularLocationSelection.create();
            create.setHeightAndAspectRatio(height, widthToHeightAspectRatio);
            Intrinsics.checkNotNull(create);
            return new RectangularLocationSelection(create);
        }

        @JvmStatic
        public final RectangularLocationSelection withSize(SizeWithUnit size) {
            Intrinsics.checkNotNullParameter(size, "size");
            NativeRectangularLocationSelection create = NativeRectangularLocationSelection.create();
            create.setWidthAndHeight(size.getWidth(), size.getHeight());
            Intrinsics.checkNotNull(create);
            return new RectangularLocationSelection(create);
        }

        @JvmStatic
        public final RectangularLocationSelection withWidthAndAspectRatio(FloatWithUnit width, float heightToWidthAspectRatio) {
            Intrinsics.checkNotNullParameter(width, "width");
            NativeRectangularLocationSelection create = NativeRectangularLocationSelection.create();
            create.setWidthAndAspectRatio(width, heightToWidthAspectRatio);
            Intrinsics.checkNotNull(create);
            return new RectangularLocationSelection(create);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RectangularLocationSelection(NativeRectangularLocationSelection impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new RectangularLocationSelectionProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @JvmStatic
    public static final RectangularLocationSelection withHeightAndAspectRatio(FloatWithUnit floatWithUnit, float f) {
        return INSTANCE.withHeightAndAspectRatio(floatWithUnit, f);
    }

    @JvmStatic
    public static final RectangularLocationSelection withSize(SizeWithUnit sizeWithUnit) {
        return INSTANCE.withSize(sizeWithUnit);
    }

    @JvmStatic
    public static final RectangularLocationSelection withWidthAndAspectRatio(FloatWithUnit floatWithUnit, float f) {
        return INSTANCE.withWidthAndAspectRatio(floatWithUnit, f);
    }

    @Override // com.scandit.datacapture.core.area.RectangularLocationSelectionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeRectangularLocationSelection getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.core.area.LocationSelection, com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    @NativeImpl
    /* renamed from: _locationSelectionImpl */
    public NativeLocationSelection getC() {
        return this.a.getC();
    }

    public final SizeWithUnitAndAspect getSizeWithUnitAndAspect() {
        NativeSizeWithUnitAndAspect sizeWithUnitAndAspect = getA().getSizeWithUnitAndAspect();
        Intrinsics.checkNotNullExpressionValue(sizeWithUnitAndAspect, "getSizeWithUnitAndAspect(...)");
        return new SizeWithUnitAndAspect(sizeWithUnitAndAspect);
    }

    @Override // com.scandit.datacapture.core.area.LocationSelection, com.scandit.datacapture.core.area.RadiusLocationSelectionProxy
    @ProxyFunction(nativeName = "toJson")
    public String toJson() {
        return this.a.toJson();
    }
}
