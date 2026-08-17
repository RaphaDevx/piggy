package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TapGestureEvent;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a-\u0010\t\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a-\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a?\u0010\u0014\u001a\u0004\u0018\u00010\u00122\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001f\u0010\u001a\u001a\u00020\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001aR\u0010\u001c\u001a\u0002H\u001d\"\u0004\b\u0000\u0010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2'\u0010\"\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060#¢\u0006\u0002\b\u0007H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010$\u001a\u001f\u0010%\u001a\u00020&*\u00020'2\b\b\u0002\u0010(\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010)\u001aa\u0010*\u001a\u00020\u0005*\u00020+2/\b\u0002\u0010,\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010.H\u0080@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u0010/\u001a\u0091\u0001\u00100\u001a\u00020\u0005*\u00020+2\u0016\b\u0002\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010.2\u0016\b\u0002\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010.2/\b\u0002\u0010,\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010.H\u0086@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0002\u00103\u001aG\u00104\u001a\u00020\u0005*\u00020'2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\f072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0082@ø\u0001\u0000¢\u0006\u0002\u00109\u001a\u0017\u0010:\u001a\u0004\u0018\u00010&*\u00020'H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010;\"=\u0010\u0000\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"NoPressGesture", "Lkotlin/Function3;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "awaitChannelAllUp", "consumeAllSignal", "Landroidx/compose/runtime/MutableState;", "", "channel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Landroidx/compose/foundation/gestures/TapGestureEvent;", "(Landroidx/compose/runtime/MutableState;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitChannelDown", "Landroidx/compose/foundation/gestures/TapGestureEvent$Down;", "onlyDownsSignal", "awaitChannelSecondDown", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "firstUp", "Landroidx/compose/foundation/gestures/TapGestureEvent$Up;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/platform/ViewConfiguration;Landroidx/compose/foundation/gestures/TapGestureEvent$Up;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitChannelUpOrCancel", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withNullableTimeout", ExifInterface.GPS_DIRECTION_TRUE, "scope", "Lkotlinx/coroutines/CoroutineScope;", "timeout", "", "block", "Lkotlin/Function2;", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "requireUnconsumed", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTapAndPress", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onPress", "onTap", "Lkotlin/Function1;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTapGestures", "onDoubleTap", "onLongPress", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translatePointerEventsToChannel", "Lkotlinx/coroutines/channels/SendChannel;", "detectDownsOnly", "Landroidx/compose/runtime/State;", "consumeAllUntilUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/channels/SendChannel;Landroidx/compose/runtime/State;Landroidx/compose/runtime/MutableState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForUpOrCancellation", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TapGestureDetectorKt {
    private static final Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> NoPressGesture = new TapGestureDetectorKt$NoPressGesture$1(null);

    public static /* synthetic */ Object detectTapGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function1 function12, Function3 function3, Function1 function13, Continuation continuation, int i, Object obj) {
        Function1 function14 = (i & 1) != 0 ? null : function1;
        Function1 function15 = (i & 2) != 0 ? null : function12;
        if ((i & 4) != 0) {
            function3 = NoPressGesture;
        }
        return detectTapGestures(pointerInputScope, function14, function15, function3, (i & 8) != 0 ? null : function13, continuation);
    }

    public static final Object detectTapGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function12, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function13, Continuation<? super Unit> continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new TapGestureDetectorKt$detectTapGestures$2(pointerInputScope, function3, function12, function1, function13, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object withNullableTimeout(kotlinx.coroutines.CoroutineScope r5, java.lang.Long r6, kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super T> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$withNullableTimeout$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.gestures.TapGestureDetectorKt$withNullableTimeout$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$withNullableTimeout$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$withNullableTimeout$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$withNullableTimeout$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L55
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4b
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r6 == 0) goto L4c
            long r5 = r6.longValue()
            r0.label = r4
            java.lang.Object r8 = kotlinx.coroutines.TimeoutKt.withTimeout(r5, r7, r0)
            if (r8 != r1) goto L4b
            return r1
        L4b:
            return r8
        L4c:
            r0.label = r3
            java.lang.Object r8 = r7.invoke(r5, r0)
            if (r8 != r1) goto L55
            return r1
        L55:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.withNullableTimeout(kotlinx.coroutines.CoroutineScope, java.lang.Long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0074 -> B:11:0x0077). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitChannelDown(androidx.compose.runtime.MutableState<java.lang.Boolean> r6, kotlinx.coroutines.channels.ReceiveChannel<? extends androidx.compose.foundation.gestures.TapGestureEvent> r7, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.TapGestureEvent.Down> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelDown$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelDown$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelDown$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelDown$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelDown$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$0
            androidx.compose.runtime.MutableState r7 = (androidx.compose.runtime.MutableState) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L77
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r6 = r0.L$0
            androidx.compose.runtime.MutableState r6 = (androidx.compose.runtime.MutableState) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L61
        L4a:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r6.setValue(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r7.receive(r0)
            if (r8 != r1) goto L61
            return r1
        L61:
            androidx.compose.foundation.gestures.TapGestureEvent r8 = (androidx.compose.foundation.gestures.TapGestureEvent) r8
            r5 = r7
            r7 = r6
            r6 = r5
        L66:
            boolean r2 = r8 instanceof androidx.compose.foundation.gestures.TapGestureEvent.Down
            if (r2 != 0) goto L7a
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r8 = r6.receive(r0)
            if (r8 != r1) goto L77
            return r1
        L77:
            androidx.compose.foundation.gestures.TapGestureEvent r8 = (androidx.compose.foundation.gestures.TapGestureEvent) r8
            goto L66
        L7a:
            r6 = 0
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            r7.setValue(r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitChannelDown(androidx.compose.runtime.MutableState, kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitChannelSecondDown(ReceiveChannel<? extends TapGestureEvent> receiveChannel, MutableState<Boolean> mutableState, ViewConfiguration viewConfiguration, TapGestureEvent.Up up, Continuation<? super TapGestureEvent.Down> continuation) {
        return TimeoutKt.withTimeoutOrNull(viewConfiguration.getDoubleTapTimeoutMillis(), new TapGestureDetectorKt$awaitChannelSecondDown$2(up, viewConfiguration, mutableState, receiveChannel, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x006a -> B:11:0x006d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitChannelAllUp(androidx.compose.runtime.MutableState<java.lang.Boolean> r5, kotlinx.coroutines.channels.ReceiveChannel<? extends androidx.compose.foundation.gestures.TapGestureEvent> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelAllUp$1
            if (r0 == 0) goto L14
            r0 = r7
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelAllUp$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelAllUp$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelAllUp$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelAllUp$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6d
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            java.lang.Object r5 = r0.L$0
            r6 = r5
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L57
        L42:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r5.setValue(r7)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r6.receive(r0)
            if (r7 != r1) goto L57
            return r1
        L57:
            androidx.compose.foundation.gestures.TapGestureEvent r7 = (androidx.compose.foundation.gestures.TapGestureEvent) r7
            r5 = r6
        L5a:
            androidx.compose.foundation.gestures.TapGestureEvent$AllUp r6 = androidx.compose.foundation.gestures.TapGestureEvent.AllUp.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r6)
            if (r6 != 0) goto L70
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = r5.receive(r0)
            if (r7 != r1) goto L6d
            return r1
        L6d:
            androidx.compose.foundation.gestures.TapGestureEvent r7 = (androidx.compose.foundation.gestures.TapGestureEvent) r7
            goto L5a
        L70:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitChannelAllUp(androidx.compose.runtime.MutableState, kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0061 -> B:11:0x0064). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitChannelUpOrCancel(kotlinx.coroutines.channels.ReceiveChannel<? extends androidx.compose.foundation.gestures.TapGestureEvent> r5, kotlin.coroutines.Continuation<? super androidx.compose.foundation.gestures.TapGestureEvent> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelUpOrCancel$1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelUpOrCancel$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelUpOrCancel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelUpOrCancel$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelUpOrCancel$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L41
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L64
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L41:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.receive(r0)
            if (r6 != r1) goto L4f
            return r1
        L4f:
            androidx.compose.foundation.gestures.TapGestureEvent r6 = (androidx.compose.foundation.gestures.TapGestureEvent) r6
        L51:
            boolean r2 = r6 instanceof androidx.compose.foundation.gestures.TapGestureEvent.Up
            if (r2 != 0) goto L67
            boolean r2 = r6 instanceof androidx.compose.foundation.gestures.TapGestureEvent.Cancel
            if (r2 != 0) goto L67
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.receive(r0)
            if (r6 != r1) goto L64
            return r1
        L64:
            androidx.compose.foundation.gestures.TapGestureEvent r6 = (androidx.compose.foundation.gestures.TapGestureEvent) r6
            goto L51
        L67:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitChannelUpOrCancel(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x020e, code lost:
    
        r14 = r6 + 1;
        r6 = r0.get(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x021e, code lost:
    
        if (r6.getConsumed().getDownChange() != false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0228, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.m2415isOutOfBoundsO0kMr_c(r6, r13.mo2398getSizeYbymL2g()) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x022b, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0236, code lost:
    
        if (kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6).booleanValue() == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0241, code lost:
    
        if (r14 <= r4) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0244, code lost:
    
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0238, code lost:
    
        r3.mo5440trySendJP2dKIU(androidx.compose.foundation.gestures.TapGestureEvent.Cancel.INSTANCE);
        r4 = false;
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x022d, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0248, code lost:
    
        r0 = androidx.compose.ui.input.pointer.PointerEventPass.Final;
        r12.L$0 = r13;
        r12.L$1 = r1;
        r12.L$2 = r3;
        r12.L$3 = r10;
        r12.L$4 = r11;
        r7 = 4;
        r12.label = 4;
        r0 = r13.awaitPointerEvent(r0, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x025b, code lost:
    
        if (r0 != r2) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x025d, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x029e, code lost:
    
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02c8, code lost:
    
        r7 = 4;
        r4 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01dc, code lost:
    
        if (r10.getValue().booleanValue() != false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01de, code lost:
    
        r4 = r0.getChanges();
        r6 = r4.size() - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01e8, code lost:
    
        if (r6 < 0) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01ea, code lost:
    
        r14 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01eb, code lost:
    
        r15 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01ff, code lost:
    
        if (kotlin.coroutines.jvm.internal.Boxing.boxBoolean(androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r4.get(r14))).booleanValue() != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0295, code lost:
    
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0296, code lost:
    
        if (r15 <= r6) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0299, code lost:
    
        r14 = r15;
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x029f, code lost:
    
        r0 = r0.getChanges().get(0);
        androidx.compose.ui.input.pointer.PointerEventKt.consumeDownChange(r0);
        r3.mo5440trySendJP2dKIU(new androidx.compose.foundation.gestures.TapGestureEvent.Up(r0.getPosition(), r0.getUptimeMillis(), null));
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0201, code lost:
    
        r0 = r0.getChanges();
        r4 = r0.size() - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x020b, code lost:
    
        if (r4 < 0) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x020d, code lost:
    
        r6 = r8;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x018c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16, types: [int] */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8, types: [int] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v10, types: [int] */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22, types: [int] */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v3, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x0172 -> B:40:0x0118). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x02fc -> B:22:0x00c6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:93:0x025b -> B:13:0x025e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object translatePointerEventsToChannel(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, kotlinx.coroutines.CoroutineScope r22, kotlinx.coroutines.channels.SendChannel<? super androidx.compose.foundation.gestures.TapGestureEvent> r23, androidx.compose.runtime.State<java.lang.Boolean> r24, androidx.compose.runtime.MutableState<java.lang.Boolean> r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            Method dump skipped, instructions count: 774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.translatePointerEventsToChannel(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.channels.SendChannel, androidx.compose.runtime.State, androidx.compose.runtime.MutableState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectTapAndPress$default(PointerInputScope pointerInputScope, Function3 function3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = NoPressGesture;
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        return detectTapAndPress(pointerInputScope, function3, function1, continuation);
    }

    public static final Object detectTapAndPress(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object forEachGesture = ForEachGestureKt.forEachGesture(pointerInputScope, new TapGestureDetectorKt$detectTapAndPress$2(new PressGestureScopeImpl(pointerInputScope), function3, function1, null), continuation);
        return forEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? forEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0049 -> B:10:0x004c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r9, boolean r10, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r10
            r10 = r9
            r9 = r8
            goto L4c
        L33:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
        L3e:
            r0.L$0 = r9
            r0.Z$0 = r10
            r0.label = r3
            r11 = 0
            java.lang.Object r11 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.DefaultImpls.awaitPointerEvent$default(r9, r11, r0, r3, r11)
            if (r11 != r1) goto L4c
            return r1
        L4c:
            androidx.compose.ui.input.pointer.PointerEvent r11 = (androidx.compose.ui.input.pointer.PointerEvent) r11
            java.util.List r2 = r11.getChanges()
            int r4 = r2.size()
            int r4 = r4 + (-1)
            r5 = 0
            if (r4 < 0) goto L7f
            r6 = r5
        L5c:
            int r7 = r6 + 1
            java.lang.Object r6 = r2.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            if (r10 == 0) goto L6b
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r6)
            goto L6f
        L6b:
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r6)
        L6f:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L7a
            goto L3e
        L7a:
            if (r7 <= r4) goto L7d
            goto L7f
        L7d:
            r6 = r7
            goto L5c
        L7f:
            java.util.List r9 = r11.getChanges()
            java.lang.Object r9 = r9.get(r5)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return awaitFirstDown(awaitPointerEventScope, z, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0079, code lost:
    
        r12 = r12.getChanges();
        r2 = r12.size() - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0083, code lost:
    
        if (r2 < 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0085, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0086, code lost:
    
        r8 = r7 + 1;
        r7 = r12.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0096, code lost:
    
        if (r7.getConsumed().getDownChange() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a0, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.m2415isOutOfBoundsO0kMr_c(r7, r11.mo2398getSizeYbymL2g()) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a3, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ae, code lost:
    
        if (kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7).booleanValue() == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b1, code lost:
    
        if (r8 <= r2) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b4, code lost:
    
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b0, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b6, code lost:
    
        r12 = androidx.compose.ui.input.pointer.PointerEventPass.Final;
        r0.L$0 = r11;
        r0.label = 2;
        r12 = r11.awaitPointerEvent(r12, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c0, code lost:
    
        if (r12 != r1) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c2, code lost:
    
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00c0 -> B:11:0x00c3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope r11, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r12) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
