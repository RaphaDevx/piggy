package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionEventAdapter.android.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J9\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010\u001aR+\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "", "()V", "motionEventToComposePointerIdMap", "", "", "Landroidx/compose/ui/input/pointer/PointerId;", "getMotionEventToComposePointerIdMap$ui_release$annotations", "getMotionEventToComposePointerIdMap$ui_release", "()Ljava/util/Map;", "nextId", "", "pointers", "", "Landroidx/compose/ui/input/pointer/PointerInputEventData;", "convertToPointerInputEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "motionEvent", "Landroid/view/MotionEvent;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "convertToPointerInputEvent$ui_release", "createPointerInputEventData", FirebaseAnalytics.Param.INDEX, "downIndex", "upIndex", "(Landroidx/compose/ui/input/pointer/PositionCalculator;Landroid/view/MotionEvent;ILjava/lang/Integer;Ljava/lang/Integer;)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MotionEventAdapter {
    private long nextId;
    private final Map<Integer, PointerId> motionEventToComposePointerIdMap = new LinkedHashMap();
    private final List<PointerInputEventData> pointers = new ArrayList();

    public static /* synthetic */ void getMotionEventToComposePointerIdMap$ui_release$annotations() {
    }

    public final Map<Integer, PointerId> getMotionEventToComposePointerIdMap$ui_release() {
        return this.motionEventToComposePointerIdMap;
    }

    public final PointerInputEvent convertToPointerInputEvent$ui_release(MotionEvent motionEvent, PositionCalculator positionCalculator) {
        Integer num;
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        Intrinsics.checkNotNullParameter(positionCalculator, "positionCalculator");
        Integer num2 = null;
        if (motionEvent.getActionMasked() == 3) {
            this.motionEventToComposePointerIdMap.clear();
            return null;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = 0;
        if (actionMasked != 0) {
            num = actionMasked != 5 ? null : Integer.valueOf(motionEvent.getActionIndex());
        } else {
            num = 0;
        }
        int actionMasked2 = motionEvent.getActionMasked();
        if (actionMasked2 == 1) {
            num2 = 0;
        } else if (actionMasked2 == 6) {
            num2 = Integer.valueOf(motionEvent.getActionIndex());
        }
        this.pointers.clear();
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount > 0) {
            do {
                int i2 = i;
                i = i2 + 1;
                this.pointers.add(createPointerInputEventData(positionCalculator, motionEvent, i2, num, num2));
            } while (i < pointerCount);
        }
        return new PointerInputEvent(motionEvent.getEventTime(), this.pointers, motionEvent);
    }

    private final PointerInputEventData createPointerInputEventData(PositionCalculator positionCalculator, MotionEvent motionEvent, int index, Integer downIndex, Integer upIndex) {
        PointerId pointerId;
        PointerInputEventData m2412createPointerInputEventDataVnAYq1g;
        int pointerId2 = motionEvent.getPointerId(index);
        if (downIndex != null && index == downIndex.intValue()) {
            long j = this.nextId;
            this.nextId = 1 + j;
            pointerId = PointerId.m2416boximpl(PointerId.m2417constructorimpl(j));
            getMotionEventToComposePointerIdMap$ui_release().put(Integer.valueOf(pointerId2), PointerId.m2416boximpl(pointerId.getValue()));
        } else if (upIndex != null && index == upIndex.intValue()) {
            pointerId = this.motionEventToComposePointerIdMap.remove(Integer.valueOf(pointerId2));
        } else {
            pointerId = this.motionEventToComposePointerIdMap.get(Integer.valueOf(pointerId2));
        }
        if (pointerId != null) {
            m2412createPointerInputEventDataVnAYq1g = MotionEventAdapter_androidKt.m2412createPointerInputEventDataVnAYq1g(positionCalculator, pointerId.getValue(), motionEvent.getEventTime(), motionEvent, index, upIndex);
            return m2412createPointerInputEventDataVnAYq1g;
        }
        throw new IllegalStateException("Compose assumes that all pointer ids in MotionEvents are first provided alongside ACTION_DOWN or ACTION_POINTER_DOWN.  This appears not to have been the case");
    }
}
