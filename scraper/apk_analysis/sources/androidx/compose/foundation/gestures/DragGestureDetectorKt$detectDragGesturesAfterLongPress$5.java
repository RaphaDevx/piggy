package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
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
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DragGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", i = {0, 1}, l = {251, 255, 259}, m = "invokeSuspend", n = {"$this$forEachGesture", "$this$forEachGesture"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class DragGestureDetectorKt$detectDragGesturesAfterLongPress$5 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
    final /* synthetic */ Function0<Unit> $onDragCancel;
    final /* synthetic */ Function0<Unit> $onDragEnd;
    final /* synthetic */ Function1<Offset, Unit> $onDragStart;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DragGestureDetectorKt$detectDragGesturesAfterLongPress$5(Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super DragGestureDetectorKt$detectDragGesturesAfterLongPress$5> continuation) {
        super(2, continuation);
        this.$onDragStart = function1;
        this.$onDragCancel = function0;
        this.$onDragEnd = function02;
        this.$onDrag = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragGestureDetectorKt$detectDragGesturesAfterLongPress$5 dragGestureDetectorKt$detectDragGesturesAfterLongPress$5 = new DragGestureDetectorKt$detectDragGesturesAfterLongPress$5(this.$onDragStart, this.$onDragCancel, this.$onDragEnd, this.$onDrag, continuation);
        dragGestureDetectorKt$detectDragGesturesAfterLongPress$5.L$0 = obj;
        return dragGestureDetectorKt$detectDragGesturesAfterLongPress$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((DragGestureDetectorKt$detectDragGesturesAfterLongPress$5) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0066 A[Catch: CancellationException -> 0x0017, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x0017, blocks: (B:7:0x0012, B:14:0x0026, B:15:0x0061, B:17:0x0066, B:22:0x0053), top: B:2:0x000a }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L32
            if (r1 == r5) goto L2a
            if (r1 == r4) goto L22
            if (r1 != r3) goto L1a
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.util.concurrent.CancellationException -> L17
            goto L90
        L17:
            r12 = move-exception
            goto L93
        L1a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L22:
            java.lang.Object r1 = r11.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r1 = (androidx.compose.ui.input.pointer.PointerInputScope) r1
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.util.concurrent.CancellationException -> L17
            goto L61
        L2a:
            java.lang.Object r1 = r11.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r1 = (androidx.compose.ui.input.pointer.PointerInputScope) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L51
        L32:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r12 = (androidx.compose.ui.input.pointer.PointerInputScope) r12
            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$down$1 r1 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$down$1
            r1.<init>(r2)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r6 = r11
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r11.L$0 = r12
            r11.label = r5
            java.lang.Object r1 = r12.awaitPointerEventScope(r1, r6)
            if (r1 != r0) goto L4e
            return r0
        L4e:
            r10 = r1
            r1 = r12
            r12 = r10
        L51:
            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
            r5 = r11
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.util.concurrent.CancellationException -> L17
            r11.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> L17
            r11.label = r4     // Catch: java.util.concurrent.CancellationException -> L17
            java.lang.Object r12 = androidx.compose.foundation.gestures.DragGestureDetectorKt.access$awaitLongPressOrCancellation(r1, r12, r5)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r12 != r0) goto L61
            return r0
        L61:
            r5 = r12
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5     // Catch: java.util.concurrent.CancellationException -> L17
            if (r5 == 0) goto L90
            kotlin.jvm.functions.Function1<androidx.compose.ui.geometry.Offset, kotlin.Unit> r12 = r11.$onDragStart     // Catch: java.util.concurrent.CancellationException -> L17
            long r6 = r5.getPosition()     // Catch: java.util.concurrent.CancellationException -> L17
            androidx.compose.ui.geometry.Offset r4 = androidx.compose.ui.geometry.Offset.m1014boximpl(r6)     // Catch: java.util.concurrent.CancellationException -> L17
            r12.invoke(r4)     // Catch: java.util.concurrent.CancellationException -> L17
            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1 r12 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1     // Catch: java.util.concurrent.CancellationException -> L17
            kotlin.jvm.functions.Function0<kotlin.Unit> r6 = r11.$onDragEnd     // Catch: java.util.concurrent.CancellationException -> L17
            kotlin.jvm.functions.Function0<kotlin.Unit> r7 = r11.$onDragCancel     // Catch: java.util.concurrent.CancellationException -> L17
            kotlin.jvm.functions.Function2<androidx.compose.ui.input.pointer.PointerInputChange, androidx.compose.ui.geometry.Offset, kotlin.Unit> r8 = r11.$onDrag     // Catch: java.util.concurrent.CancellationException -> L17
            r9 = 0
            r4 = r12
            r4.<init>(r5, r6, r7, r8, r9)     // Catch: java.util.concurrent.CancellationException -> L17
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12     // Catch: java.util.concurrent.CancellationException -> L17
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.util.concurrent.CancellationException -> L17
            r11.L$0 = r2     // Catch: java.util.concurrent.CancellationException -> L17
            r11.label = r3     // Catch: java.util.concurrent.CancellationException -> L17
            java.lang.Object r11 = r1.awaitPointerEventScope(r12, r4)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r11 != r0) goto L90
            return r0
        L90:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L93:
            kotlin.jvm.functions.Function0<kotlin.Unit> r11 = r11.$onDragCancel
            r11.invoke()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1", f = "DragGestureDetector.kt", i = {0}, l = {261}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputChange $drag;
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(PointerInputChange pointerInputChange, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$drag = pointerInputChange;
            this.$onDragEnd = function0;
            this.$onDragCancel = function02;
            this.$onDrag = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$drag, this.$onDragEnd, this.$onDragCancel, this.$onDrag, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AwaitPointerEventScope awaitPointerEventScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                long id = this.$drag.getId();
                final Function2<PointerInputChange, Offset, Unit> function2 = this.$onDrag;
                this.L$0 = awaitPointerEventScope2;
                this.label = 1;
                Object m194dragjO51t88 = DragGestureDetectorKt.m194dragjO51t88(awaitPointerEventScope2, id, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.5.1.1
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
                    public final void invoke2(PointerInputChange it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        function2.invoke(it, Offset.m1014boximpl(PointerEventKt.positionChange(it)));
                        PointerEventKt.consumePositionChange(it);
                    }
                }, this);
                if (m194dragjO51t88 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                awaitPointerEventScope = awaitPointerEventScope2;
                obj = m194dragjO51t88;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                List<PointerInputChange> changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                int size = changes.size() - 1;
                if (size >= 0) {
                    int i2 = 0;
                    while (true) {
                        int i3 = i2 + 1;
                        PointerInputChange pointerInputChange = changes.get(i2);
                        if (PointerEventKt.changedToUp(pointerInputChange)) {
                            PointerEventKt.consumeDownChange(pointerInputChange);
                        }
                        if (i3 > size) {
                            break;
                        }
                        i2 = i3;
                    }
                }
                this.$onDragEnd.invoke();
            } else {
                this.$onDragCancel.invoke();
            }
            return Unit.INSTANCE;
        }
    }
}
