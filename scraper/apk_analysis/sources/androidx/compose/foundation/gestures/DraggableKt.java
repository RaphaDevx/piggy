package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;

/* compiled from: Draggable.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u001a!\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001aY\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t*\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\u00030\r2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00100\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aO\u0010\u0014\u001a\u00020\u000e*\u00020\u000b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aé\u0001\u0010\u001d\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u000e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102>\b\u0002\u0010#\u001a8\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050*\u0012\u0006\u0012\u0004\u0018\u00010+0$¢\u0006\u0002\b,2>\b\u0002\u0010-\u001a8\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050*\u0012\u0006\u0012\u0004\u0018\u00010+0$¢\u0006\u0002\b,2\b\b\u0002\u0010\u001b\u001a\u00020\u000eH\u0000ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010/\u001aÏ\u0001\u0010\u001d\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020\u000e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2>\b\u0002\u0010#\u001a8\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050*\u0012\u0006\u0012\u0004\u0018\u00010+0$¢\u0006\u0002\b,2>\b\u0002\u0010-\u001a8\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050*\u0012\u0006\u0012\u0004\u0018\u00010+0$¢\u0006\u0002\b,2\b\b\u0002\u0010\u001b\u001a\u00020\u000eø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u00100\u001a!\u00101\u001a\u00020\u0004*\u00020&2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103\u001a!\u00101\u001a\u00020\u0004*\u0002042\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b5\u00103\u001a\u001c\u00106\u001a\u00020&*\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0002\u00107\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00068"}, d2 = {"DraggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "onDelta", "Lkotlin/Function1;", "", "", "rememberDraggableState", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/gestures/DraggableState;", "awaitDownAndSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "canDrag", "Landroidx/compose/runtime/State;", "", "startDragImmediately", "Lkotlin/Function0;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/runtime/State;Landroidx/compose/runtime/State;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDrag", "dragStart", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "Landroidx/compose/foundation/gestures/DragEvent;", "reverseDirection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/Pair;Landroidx/compose/ui/input/pointer/util/VelocityTracker;Lkotlinx/coroutines/channels/SendChannel;ZLandroidx/compose/foundation/gestures/Orientation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "draggable", "Landroidx/compose/ui/Modifier;", SentryThread.JsonKeys.STATE, ExtentionsKt.ENABLED_KEY, "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "onDragStarted", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", "name", "startedPosition", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "onDragStopped", "velocity", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/DraggableState;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Z)Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/DraggableState;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Z)Landroidx/compose/ui/Modifier;", "toFloat", "toFloat-3MmeM6k", "(JLandroidx/compose/foundation/gestures/Orientation;)F", "Landroidx/compose/ui/unit/Velocity;", "toFloat-sF-c-tU", "toOffset", "(FLandroidx/compose/foundation/gestures/Orientation;)J", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DraggableKt {
    public static final DraggableState DraggableState(Function1<? super Float, Unit> onDelta) {
        Intrinsics.checkNotNullParameter(onDelta, "onDelta");
        return new DefaultDraggableState(onDelta);
    }

    public static final DraggableState rememberDraggableState(Function1<? super Float, Unit> onDelta, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(onDelta, "onDelta");
        composer.startReplaceableGroup(-1066220250);
        ComposerKt.sourceInformation(composer, "C(rememberDraggableState)133@5631L29,134@5672L61:Draggable.kt#8bwon0");
        final State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onDelta, composer, i & 14);
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = DraggableState(new Function1<Float, Unit>() { // from class: androidx.compose.foundation.gestures.DraggableKt$rememberDraggableState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                    invoke(f.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float f) {
                    rememberUpdatedState.getValue().invoke(Float.valueOf(f));
                }
            });
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        DraggableState draggableState = (DraggableState) rememberedValue;
        composer.endReplaceableGroup();
        return draggableState;
    }

    public static final Modifier draggable(Modifier modifier, DraggableState state, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, final boolean z2, Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> onDragStarted, Function3<? super CoroutineScope, ? super Float, ? super Continuation<? super Unit>, ? extends Object> onDragStopped, boolean z3) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(onDragStarted, "onDragStarted");
        Intrinsics.checkNotNullParameter(onDragStopped, "onDragStopped");
        return draggable(modifier, state, new Function1<PointerInputChange, Boolean>() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$3
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(PointerInputChange it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(PointerInputChange pointerInputChange) {
                return Boolean.valueOf(invoke2(pointerInputChange));
            }
        }, orientation, z, mutableInteractionSource, new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                return z2;
            }
        }, onDragStarted, onDragStopped, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitDownAndSlop(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, androidx.compose.runtime.State<? extends kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean>> r8, androidx.compose.runtime.State<? extends kotlin.jvm.functions.Function0<java.lang.Boolean>> r9, androidx.compose.foundation.gestures.Orientation r10, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r11) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt.awaitDownAndSlop(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.foundation.gestures.Orientation, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitDrag(AwaitPointerEventScope awaitPointerEventScope, Pair<PointerInputChange, Float> pair, final VelocityTracker velocityTracker, final SendChannel<? super DragEvent> sendChannel, final boolean z, final Orientation orientation, Continuation<? super Boolean> continuation) {
        float floatValue = pair.getSecond().floatValue();
        PointerInputChange first = pair.getFirst();
        velocityTracker.m2498addPositionUv8p0NA(first.getUptimeMillis(), first.getPosition());
        sendChannel.mo5440trySendJP2dKIU(new DragEvent.DragStarted(Offset.m1029minusMKHz9U(first.getPosition(), Offset.m1032timestuRUvjQ(toOffset(floatValue, orientation), Math.signum(m205toFloat3MmeM6k(first.getPosition(), orientation)))), null));
        if (z) {
            floatValue *= -1;
        }
        sendChannel.mo5440trySendJP2dKIU(new DragEvent.DragDelta(floatValue));
        Function1<PointerInputChange, Unit> function1 = new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DraggableKt$awaitDrag$dragTick$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                invoke2(pointerInputChange);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PointerInputChange event) {
                float m205toFloat3MmeM6k;
                Intrinsics.checkNotNullParameter(event, "event");
                VelocityTracker.this.m2498addPositionUv8p0NA(event.getUptimeMillis(), event.getPosition());
                m205toFloat3MmeM6k = DraggableKt.m205toFloat3MmeM6k(PointerEventKt.positionChange(event), orientation);
                PointerEventKt.consumePositionChange(event);
                SendChannel<DragEvent> sendChannel2 = sendChannel;
                if (z) {
                    m205toFloat3MmeM6k *= -1;
                }
                sendChannel2.mo5440trySendJP2dKIU(new DragEvent.DragDelta(m205toFloat3MmeM6k));
            }
        };
        if (orientation == Orientation.Vertical) {
            return DragGestureDetectorKt.m197verticalDragjO51t88(awaitPointerEventScope, first.getId(), function1, continuation);
        }
        return DragGestureDetectorKt.m195horizontalDragjO51t88(awaitPointerEventScope, first.getId(), function1, continuation);
    }

    private static final long toOffset(float f, Orientation orientation) {
        return orientation == Orientation.Vertical ? OffsetKt.Offset(0.0f, f) : OffsetKt.Offset(f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toFloat-3MmeM6k, reason: not valid java name */
    public static final float m205toFloat3MmeM6k(long j, Orientation orientation) {
        return orientation == Orientation.Vertical ? Offset.m1026getYimpl(j) : Offset.m1025getXimpl(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toFloat-sF-c-tU, reason: not valid java name */
    public static final float m206toFloatsFctU(long j, Orientation orientation) {
        return orientation == Orientation.Vertical ? Velocity.m3193getYimpl(j) : Velocity.m3192getXimpl(j);
    }

    public static final Modifier draggable(Modifier modifier, final DraggableState state, final Function1<? super PointerInputChange, Boolean> canDrag, final Orientation orientation, final boolean z, final MutableInteractionSource mutableInteractionSource, final Function0<Boolean> startDragImmediately, final Function3<? super CoroutineScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> onDragStarted, final Function3<? super CoroutineScope, ? super Float, ? super Continuation<? super Unit>, ? extends Object> onDragStopped, final boolean z2) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(canDrag, "canDrag");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(startDragImmediately, "startDragImmediately");
        Intrinsics.checkNotNullParameter(onDragStarted, "onDragStarted");
        Intrinsics.checkNotNullParameter(onDragStopped, "onDragStopped");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("draggable");
                inspectorInfo.getProperties().set("canDrag", Function1.this);
                inspectorInfo.getProperties().set("orientation", orientation);
                inspectorInfo.getProperties().set(ExtentionsKt.ENABLED_KEY, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("reverseDirection", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("startDragImmediately", startDragImmediately);
                inspectorInfo.getProperties().set("onDragStarted", onDragStarted);
                inspectorInfo.getProperties().set("onDragStopped", onDragStopped);
                inspectorInfo.getProperties().set(SentryThread.JsonKeys.STATE, state);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-1197726693);
                ComposerKt.sourceInformation(composer, "C215@9628L57,216@9690L274,224@9983L61,225@10077L42,226@10143L29,227@10194L114,230@10313L945:Draggable.kt#8bwon0");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue = composer.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composer.updateRememberedValue(rememberedValue);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState = (MutableState) rememberedValue;
                MutableInteractionSource mutableInteractionSource2 = MutableInteractionSource.this;
                final MutableInteractionSource mutableInteractionSource3 = MutableInteractionSource.this;
                EffectsKt.DisposableEffect(mutableInteractionSource2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$8.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        final MutableState<DragInteraction.Start> mutableState2 = mutableState;
                        final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource3;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.gestures.DraggableKt$draggable$8$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                DragInteraction.Start start = (DragInteraction.Start) MutableState.this.getValue();
                                if (start == null) {
                                    return;
                                }
                                MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                                if (mutableInteractionSource5 != null) {
                                    mutableInteractionSource5.tryEmit(new DragInteraction.Cancel(start));
                                }
                                MutableState.this.setValue(null);
                            }
                        };
                    }
                }, composer, 0);
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue2 = composer.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                    composer.updateRememberedValue(rememberedValue2);
                }
                composer.endReplaceableGroup();
                Channel channel = (Channel) rememberedValue2;
                State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(startDragImmediately, composer, 0);
                State rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(canDrag, composer, 0);
                EffectsKt.LaunchedEffect(state, new AnonymousClass2(channel, state, SnapshotStateKt.rememberUpdatedState(new DragLogic(onDragStarted, onDragStopped, mutableState, MutableInteractionSource.this), composer, 0), null), composer, 0);
                Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput((Modifier) Modifier.INSTANCE, new Object[]{orientation, Boolean.valueOf(z), Boolean.valueOf(z2)}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass3(z, rememberUpdatedState2, rememberUpdatedState, orientation, channel, z2, null));
                composer.endReplaceableGroup();
                return pointerInput;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: invoke$lambda-2, reason: not valid java name */
            public static final DragLogic m210invoke$lambda2(State<DragLogic> state2) {
                return state2.getValue();
            }

            /* compiled from: Draggable.kt */
            @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$3", f = "Draggable.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$3, reason: invalid class name */
            static final class AnonymousClass3 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ State<Function1<PointerInputChange, Boolean>> $canDragState;
                final /* synthetic */ Channel<DragEvent> $channel;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ Orientation $orientation;
                final /* synthetic */ boolean $reverseDirection;
                final /* synthetic */ State<Function0<Boolean>> $startImmediatelyState;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass3(boolean z, State<? extends Function1<? super PointerInputChange, Boolean>> state, State<? extends Function0<Boolean>> state2, Orientation orientation, Channel<DragEvent> channel, boolean z2, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$enabled = z;
                    this.$canDragState = state;
                    this.$startImmediatelyState = state2;
                    this.$orientation = orientation;
                    this.$channel = channel;
                    this.$reverseDirection = z2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$enabled, this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, continuation);
                    anonymousClass3.L$0 = obj;
                    return anonymousClass3;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass3) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: Draggable.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1", f = "Draggable.kt", i = {}, l = {258}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ PointerInputScope $$this$pointerInput;
                    final /* synthetic */ State<Function1<PointerInputChange, Boolean>> $canDragState;
                    final /* synthetic */ Channel<DragEvent> $channel;
                    final /* synthetic */ Orientation $orientation;
                    final /* synthetic */ boolean $reverseDirection;
                    final /* synthetic */ State<Function0<Boolean>> $startImmediatelyState;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass1(PointerInputScope pointerInputScope, State<? extends Function1<? super PointerInputChange, Boolean>> state, State<? extends Function0<Boolean>> state2, Orientation orientation, Channel<DragEvent> channel, boolean z, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$$this$pointerInput = pointerInputScope;
                        this.$canDragState = state;
                        this.$startImmediatelyState = state2;
                        this.$orientation = orientation;
                        this.$channel = channel;
                        this.$reverseDirection = z;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$pointerInput, this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* compiled from: Draggable.kt */
                    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1$1", f = "Draggable.kt", i = {}, l = {259}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C00081 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ CoroutineScope $$this$coroutineScope;
                        final /* synthetic */ State<Function1<PointerInputChange, Boolean>> $canDragState;
                        final /* synthetic */ Channel<DragEvent> $channel;
                        final /* synthetic */ Orientation $orientation;
                        final /* synthetic */ boolean $reverseDirection;
                        final /* synthetic */ State<Function0<Boolean>> $startImmediatelyState;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        C00081(State<? extends Function1<? super PointerInputChange, Boolean>> state, State<? extends Function0<Boolean>> state2, Orientation orientation, Channel<DragEvent> channel, boolean z, CoroutineScope coroutineScope, Continuation<? super C00081> continuation) {
                            super(2, continuation);
                            this.$canDragState = state;
                            this.$startImmediatelyState = state2;
                            this.$orientation = orientation;
                            this.$channel = channel;
                            this.$reverseDirection = z;
                            this.$$this$coroutineScope = coroutineScope;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C00081 c00081 = new C00081(this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, this.$$this$coroutineScope, continuation);
                            c00081.L$0 = obj;
                            return c00081;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                            return ((C00081) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* compiled from: Draggable.kt */
                        @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1$1$1", f = "Draggable.kt", i = {0, 0, 1}, l = {261, 264}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "velocityTracker", "isDragSuccessful"}, s = {"L$0", "L$1", "I$0"})
                        /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$3$1$1$1, reason: invalid class name and collision with other inner class name */
                        static final class C00091 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ CoroutineScope $$this$coroutineScope;
                            final /* synthetic */ State<Function1<PointerInputChange, Boolean>> $canDragState;
                            final /* synthetic */ Channel<DragEvent> $channel;
                            final /* synthetic */ Orientation $orientation;
                            final /* synthetic */ boolean $reverseDirection;
                            final /* synthetic */ State<Function0<Boolean>> $startImmediatelyState;
                            int I$0;
                            private /* synthetic */ Object L$0;
                            Object L$1;
                            Object L$2;
                            Object L$3;
                            boolean Z$0;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            C00091(State<? extends Function1<? super PointerInputChange, Boolean>> state, State<? extends Function0<Boolean>> state2, Orientation orientation, Channel<DragEvent> channel, boolean z, CoroutineScope coroutineScope, Continuation<? super C00091> continuation) {
                                super(2, continuation);
                                this.$canDragState = state;
                                this.$startImmediatelyState = state2;
                                this.$orientation = orientation;
                                this.$channel = channel;
                                this.$reverseDirection = z;
                                this.$$this$coroutineScope = coroutineScope;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                C00091 c00091 = new C00091(this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, this.$$this$coroutineScope, continuation);
                                c00091.L$0 = obj;
                                return c00091;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                                return ((C00091) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            /* JADX WARN: Removed duplicated region for block: B:11:0x00ae  */
                            /* JADX WARN: Removed duplicated region for block: B:19:0x00c4  */
                            /* JADX WARN: Removed duplicated region for block: B:25:0x00e4  */
                            /* JADX WARN: Removed duplicated region for block: B:26:0x00ee A[Catch: all -> 0x00ef, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x00ef, blocks: (B:23:0x00de, B:26:0x00ee), top: B:22:0x00de }] */
                            /* JADX WARN: Removed duplicated region for block: B:30:0x00f2  */
                            /* JADX WARN: Removed duplicated region for block: B:36:0x0108  */
                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object invokeSuspend(java.lang.Object r18) {
                                /*
                                    Method dump skipped, instructions count: 273
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt$draggable$8.AnonymousClass3.AnonymousClass1.C00081.C00091.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00091(this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, this.$$this$coroutineScope, null), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            this.label = 1;
                            if (ForEachGestureKt.forEachGesture(this.$$this$pointerInput, new C00081(this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, coroutineScope, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                        if (!this.$enabled) {
                            return Unit.INSTANCE;
                        }
                        this.label = 1;
                        if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(pointerInputScope, this.$canDragState, this.$startImmediatelyState, this.$orientation, this.$channel, this.$reverseDirection, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: Draggable.kt */
            @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$2", f = "Draggable.kt", i = {0, 0}, l = {233, 235, 237, 245, 247, 251}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "event"}, s = {"L$0", "L$1"})
            /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$2, reason: invalid class name */
            static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Channel<DragEvent> $channel;
                final /* synthetic */ State<DragLogic> $dragLogic$delegate;
                final /* synthetic */ DraggableState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass2(Channel<DragEvent> channel, DraggableState draggableState, State<DragLogic> state, Continuation<? super AnonymousClass2> continuation) {
                    super(2, continuation);
                    this.$channel = channel;
                    this.$state = draggableState;
                    this.$dragLogic$delegate = state;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$channel, this.$state, this.$dragLogic$delegate, continuation);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* compiled from: Draggable.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.foundation.gestures.DraggableKt$draggable$8$2$2", f = "Draggable.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.gestures.DraggableKt$draggable$8$2$2, reason: invalid class name and collision with other inner class name */
                static final class C00072 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Channel<DragEvent> $channel;
                    final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00072(Ref.ObjectRef<DragEvent> objectRef, Channel<DragEvent> channel, Continuation<? super C00072> continuation) {
                        super(2, continuation);
                        this.$event = objectRef;
                        this.$channel = channel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00072 c00072 = new C00072(this.$event, this.$channel, continuation);
                        c00072.L$0 = obj;
                        return c00072;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                        return ((C00072) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x005e -> B:5:0x0061). Please report as a decompilation issue!!! */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                        /*
                            r5 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r5.label
                            r2 = 1
                            if (r1 == 0) goto L1f
                            if (r1 != r2) goto L17
                            java.lang.Object r1 = r5.L$1
                            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
                            java.lang.Object r3 = r5.L$0
                            androidx.compose.foundation.gestures.DragScope r3 = (androidx.compose.foundation.gestures.DragScope) r3
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L61
                        L17:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L1f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            java.lang.Object r6 = r5.L$0
                            androidx.compose.foundation.gestures.DragScope r6 = (androidx.compose.foundation.gestures.DragScope) r6
                            r3 = r6
                        L27:
                            kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r6 = r5.$event
                            T r6 = r6.element
                            boolean r6 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragStopped
                            if (r6 != 0) goto L64
                            kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r6 = r5.$event
                            T r6 = r6.element
                            boolean r6 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragCancelled
                            if (r6 != 0) goto L64
                            kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r6 = r5.$event
                            T r6 = r6.element
                            boolean r1 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragDelta
                            if (r1 == 0) goto L42
                            androidx.compose.foundation.gestures.DragEvent$DragDelta r6 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r6
                            goto L43
                        L42:
                            r6 = 0
                        L43:
                            if (r6 != 0) goto L46
                            goto L4d
                        L46:
                            float r6 = r6.getDelta()
                            r3.dragBy(r6)
                        L4d:
                            kotlin.jvm.internal.Ref$ObjectRef<androidx.compose.foundation.gestures.DragEvent> r1 = r5.$event
                            kotlinx.coroutines.channels.Channel<androidx.compose.foundation.gestures.DragEvent> r6 = r5.$channel
                            r4 = r5
                            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                            r5.L$0 = r3
                            r5.L$1 = r1
                            r5.label = r2
                            java.lang.Object r6 = r6.receive(r4)
                            if (r6 != r0) goto L61
                            return r0
                        L61:
                            r1.element = r6
                            goto L27
                        L64:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt$draggable$8.AnonymousClass2.C00072.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: Code restructure failed: missing block: B:35:0x00e2, code lost:
                
                    r1 = r3;
                 */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:10:0x0062  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x0087  */
                /* JADX WARN: Removed duplicated region for block: B:17:0x0089  */
                /* JADX WARN: Removed duplicated region for block: B:23:0x00c2 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:26:0x00cf A[Catch: CancellationException -> 0x0036, TryCatch #1 {CancellationException -> 0x0036, blocks: (B:21:0x00a5, B:24:0x00c3, B:26:0x00cf, B:30:0x00e5, B:32:0x00eb, B:52:0x0031), top: B:51:0x0031 }] */
                /* JADX WARN: Removed duplicated region for block: B:30:0x00e5 A[Catch: CancellationException -> 0x0036, TryCatch #1 {CancellationException -> 0x0036, blocks: (B:21:0x00a5, B:24:0x00c3, B:26:0x00cf, B:30:0x00e5, B:32:0x00eb, B:52:0x0031), top: B:51:0x0031 }] */
                /* JADX WARN: Removed duplicated region for block: B:41:0x010f A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:42:0x0110  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0087 -> B:8:0x005c). Please report as a decompilation issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00f9 -> B:8:0x005c). Please report as a decompilation issue!!! */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x010d -> B:7:0x00e2). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                    /*
                        Method dump skipped, instructions count: 294
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DraggableKt$draggable$8.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        });
    }
}
