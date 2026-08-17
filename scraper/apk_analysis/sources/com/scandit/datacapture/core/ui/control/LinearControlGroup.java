package com.scandit.datacapture.core.ui.control;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.common.linearcontrolgroup.LinearControlGroupDeserializer;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.FloatWithUnitExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.gesture.ZoomGesture;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\u0018\u0000 :2\u00020\u0001:\u0002:;J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0018\u001a\u00020\u0004\"\b\b\u0000\u0010\u0015*\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0018\u0010\u001bR\u0017\u0010!\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010'\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010-\u001a\u00020(8\u0016X\u0096D¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u001b\u00103\u001a\u00020.8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u0001048F¢\u0006\u0006\u001a\u0004\b5\u00106R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020\u0001048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00106¨\u0006<"}, d2 = {"Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "Lcom/scandit/datacapture/core/ui/control/Control;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "_onDataCaptureContextChanged", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "dataCaptureView", "_onDataCaptureViewChanged", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;)V", "Lcom/scandit/datacapture/core/source/FrameSource;", "frameSource", "_onFrameSourceChanged", "(Lcom/scandit/datacapture/core/source/FrameSource;)V", "Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;", "zoomGesture", "_onZoomGestureChanged", "(Lcom/scandit/datacapture/core/ui/gesture/ZoomGesture;)V", "_onRemoveControl", "()V", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/Class;", "type", "_removeChildControl", "(Ljava/lang/Class;)V", "control", "(Lcom/scandit/datacapture/core/ui/control/Control;)V", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "b", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "getOrientation", "()Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "orientation", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "c", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getSpacing", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "spacing", "", "e", "Z", "get_isControlGroup", "()Z", "_isControlGroup", "Landroid/view/ViewGroup;", "f", "Lkotlin/Lazy;", "get_view", "()Landroid/view/ViewGroup;", "_view", "", "getControls", "()Ljava/util/List;", "controls", "get_childControls", "_childControls", "Companion", "Defaults", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class LinearControlGroup implements Control {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context a;

    /* renamed from: b, reason: from kotlin metadata */
    private final LinearControlGroupOrientation orientation;

    /* renamed from: c, reason: from kotlin metadata */
    private final FloatWithUnit spacing;
    private final List d;

    /* renamed from: e, reason: from kotlin metadata */
    private final boolean _isControlGroup = true;

    /* renamed from: f, reason: from kotlin metadata */
    private final Lazy _view = LazyKt.lazy(new f(this));

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J3\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/core/ui/control/LinearControlGroup$Companion;", "", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroupBuilder;", "builder", "(Landroid/content/Context;)Lcom/scandit/datacapture/core/ui/control/LinearControlGroupBuilder;", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "orientation", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "spacing", "", "Lcom/scandit/datacapture/core/ui/control/Control;", "controls", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "_fromBuilderProperties", "(Landroid/content/Context;Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;Ljava/util/List;)Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "", "json", "fromJson", "(Landroid/content/Context;Ljava/lang/String;)Lcom/scandit/datacapture/core/ui/control/LinearControlGroup;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final LinearControlGroup _fromBuilderProperties(Context context, LinearControlGroupOrientation orientation, FloatWithUnit spacing, List<? extends Control> controls) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(orientation, "orientation");
            Intrinsics.checkNotNullParameter(spacing, "spacing");
            Intrinsics.checkNotNullParameter(controls, "controls");
            return new LinearControlGroup(context, orientation, spacing, controls, null);
        }

        @JvmStatic
        public final LinearControlGroupBuilder builder(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new LinearControlGroupBuilder(context);
        }

        @JvmStatic
        public final LinearControlGroup fromJson(Context context, String json) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(json, "json");
            return LinearControlGroupDeserializer.fromJson(context, new JsonValue(json));
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/core/ui/control/LinearControlGroup$Defaults;", "", "controls", "", "Lcom/scandit/datacapture/core/ui/control/Control;", "getControls", "()Ljava/util/List;", "orientation", "Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "getOrientation", "()Lcom/scandit/datacapture/core/ui/control/LinearControlGroupOrientation;", "spacing", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getSpacing", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Defaults {
        List<Control> getControls();

        LinearControlGroupOrientation getOrientation();

        FloatWithUnit getSpacing();
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LinearControlGroupOrientation.values().length];
            try {
                iArr[LinearControlGroupOrientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LinearControlGroupOrientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LinearControlGroup(Context context, LinearControlGroupOrientation linearControlGroupOrientation, FloatWithUnit floatWithUnit, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this.a = context;
        this.orientation = linearControlGroupOrientation;
        this.spacing = floatWithUnit;
        this.d = CollectionsKt.toMutableList((Collection) list);
    }

    public static final ViewGroup access$createView(LinearControlGroup linearControlGroup) {
        int i;
        int pixels;
        int i2;
        linearControlGroup.getClass();
        LinearLayout linearLayout = new LinearLayout(linearControlGroup.a);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int i3 = WhenMappings.$EnumSwitchMapping$0[linearControlGroup.orientation.ordinal()];
        if (i3 == 1) {
            i = 0;
        } else {
            if (i3 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i = 1;
        }
        linearLayout.setOrientation(i);
        int i4 = 0;
        for (Object obj : linearControlGroup.d) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Control control = (Control) obj;
            View view = control.get_view();
            String simpleName = control.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            view.setTag(simpleName);
            if (i4 != CollectionsKt.getLastIndex(linearControlGroup.d)) {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
                int marginEnd = marginLayoutParams.getMarginEnd();
                LinearControlGroupOrientation linearControlGroupOrientation = linearControlGroup.orientation;
                int[] iArr = WhenMappings.$EnumSwitchMapping$0;
                int i6 = iArr[linearControlGroupOrientation.ordinal()];
                if (i6 == 1) {
                    pixels = (int) FloatWithUnitExtensionsKt.toPixels(linearControlGroup.spacing, control.get_view().getLayoutParams().width, ContextExtensionsKt.getDisplayDensity(linearControlGroup.a));
                } else {
                    if (i6 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    pixels = 0;
                }
                marginLayoutParams.setMarginEnd(marginEnd + pixels);
                int i7 = marginLayoutParams.bottomMargin;
                int i8 = iArr[linearControlGroup.orientation.ordinal()];
                if (i8 == 1) {
                    i2 = 0;
                } else {
                    if (i8 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i2 = (int) FloatWithUnitExtensionsKt.toPixels(linearControlGroup.spacing, control.get_view().getLayoutParams().height, ContextExtensionsKt.getDisplayDensity(linearControlGroup.a));
                }
                marginLayoutParams.bottomMargin = i7 + i2;
                view.setLayoutParams(marginLayoutParams);
            }
            ViewExtensionsKt.runOnMainThread(linearLayout, new g(linearLayout, view));
            i4 = i5;
        }
        return linearLayout;
    }

    @JvmStatic
    public static final LinearControlGroupBuilder builder(Context context) {
        return INSTANCE.builder(context);
    }

    @JvmStatic
    public static final LinearControlGroup fromJson(Context context, String str) {
        return INSTANCE.fromJson(context, str);
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _onDataCaptureContextChanged(DataCaptureContext dataCaptureContext) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onDataCaptureContextChanged(dataCaptureContext);
        }
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _onDataCaptureViewChanged(DataCaptureView dataCaptureView) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onDataCaptureViewChanged(dataCaptureView);
        }
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _onFrameSourceChanged(FrameSource frameSource) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onFrameSourceChanged(frameSource);
        }
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _onRemoveControl() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onRemoveControl();
        }
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _onZoomGestureChanged(ZoomGesture zoomGesture) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((Control) it.next())._onZoomGestureChanged(zoomGesture);
        }
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public <T extends Control> void _removeChildControl(Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Iterator it = CollectionsKt.filterIsInstance(this.d, type).iterator();
        while (it.hasNext()) {
            _removeChildControl((Control) it.next());
        }
    }

    public final List<Control> getControls() {
        return this.d;
    }

    public final LinearControlGroupOrientation getOrientation() {
        return this.orientation;
    }

    public final FloatWithUnit getSpacing() {
        return this.spacing;
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public List<Control> get_childControls() {
        return this.d;
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public boolean get_isControlGroup() {
        return this._isControlGroup;
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public ViewGroup get_view() {
        return (ViewGroup) this._view.getValue();
    }

    @Override // com.scandit.datacapture.core.ui.control.Control
    public void _removeChildControl(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        if (this.d.remove(control)) {
            control._onRemoveControl();
            ViewExtensionsKt.runOnMainThread(get_view(), new e(this, control));
        }
    }
}
