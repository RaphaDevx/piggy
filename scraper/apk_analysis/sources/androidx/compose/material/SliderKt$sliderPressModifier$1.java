package androidx.compose.material;

import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1", f = "Slider.kt", i = {}, l = {793}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SliderKt$sliderPressModifier$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DraggableState $draggableState;
    final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ boolean $isRtl;
    final /* synthetic */ float $maxPx;
    final /* synthetic */ State<Float> $rawOffset;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SliderKt$sliderPressModifier$1(DraggableState draggableState, MutableInteractionSource mutableInteractionSource, State<? extends Function1<? super Float, Unit>> state, boolean z, float f, State<Float> state2, Continuation<? super SliderKt$sliderPressModifier$1> continuation) {
        super(2, continuation);
        this.$draggableState = draggableState;
        this.$interactionSource = mutableInteractionSource;
        this.$gestureEndAction = state;
        this.$isRtl = z;
        this.$maxPx = f;
        this.$rawOffset = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SliderKt$sliderPressModifier$1 sliderKt$sliderPressModifier$1 = new SliderKt$sliderPressModifier$1(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, continuation);
        sliderKt$sliderPressModifier$1.L$0 = obj;
        return sliderKt$sliderPressModifier$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((SliderKt$sliderPressModifier$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (TapGestureDetectorKt.detectTapGestures$default((PointerInputScope) this.L$0, null, null, new AnonymousClass1(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, null), null, this, 11, null) == coroutine_suspended) {
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

    /* compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "pos", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1$1", f = "Slider.kt", i = {0, 0, 1, 1, 2}, l = {795, 800, 803, 813}, m = "invokeSuspend", n = {"$this$detectTapGestures", "pos", "$this$detectTapGestures", DataSources.EventTypeValue.INTERACTION_EVENT_TYPE, DataSources.EventTypeValue.INTERACTION_EVENT_TYPE}, s = {"L$0", "J$0", "L$0", "L$1", "L$0"})
    /* renamed from: androidx.compose.material.SliderKt$sliderPressModifier$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ DraggableState $draggableState;
        final /* synthetic */ State<Function1<Float, Unit>> $gestureEndAction;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ State<Float> $rawOffset;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(DraggableState draggableState, MutableInteractionSource mutableInteractionSource, State<? extends Function1<? super Float, Unit>> state, boolean z, float f, State<Float> state2, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$draggableState = draggableState;
            this.$interactionSource = mutableInteractionSource;
            this.$gestureEndAction = state;
            this.$isRtl = z;
            this.$maxPx = f;
            this.$rawOffset = state2;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m838invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
        }

        /* renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m838invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$draggableState, this.$interactionSource, this.$gestureEndAction, this.$isRtl, this.$maxPx, this.$rawOffset, continuation);
            anonymousClass1.L$0 = pressGestureScope;
            anonymousClass1.J$0 = j;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.material.SliderKt$sliderPressModifier$1$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.material.SliderKt$sliderPressModifier$1$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00321 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isRtl;
            final /* synthetic */ float $maxPx;
            final /* synthetic */ long $pos;
            final /* synthetic */ State<Float> $rawOffset;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00321(boolean z, float f, long j, State<Float> state, Continuation<? super C00321> continuation) {
                super(2, continuation);
                this.$isRtl = z;
                this.$maxPx = f;
                this.$pos = j;
                this.$rawOffset = state;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00321 c00321 = new C00321(this.$isRtl, this.$maxPx, this.$pos, this.$rawOffset, continuation);
                c00321.L$0 = obj;
                return c00321;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                return ((C00321) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ((DragScope) this.L$0).dragBy((this.$isRtl ? this.$maxPx - Offset.m1025getXimpl(this.$pos) : Offset.m1025getXimpl(this.$pos)) - this.$rawOffset.getValue().floatValue());
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x00c6 A[Catch: CancellationException -> 0x00d6, TryCatch #0 {CancellationException -> 0x00d6, blocks: (B:15:0x0028, B:17:0x00ae, B:19:0x00c6, B:23:0x00ce, B:25:0x009d), top: B:2:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00f1 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00ce A[Catch: CancellationException -> 0x00d6, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d6, blocks: (B:15:0x0028, B:17:0x00ae, B:19:0x00c6, B:23:0x00ce, B:25:0x009d), top: B:2:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00ad A[RETURN] */
        /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.foundation.interaction.PressInteraction$Press, int] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instructions count: 245
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt$sliderPressModifier$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
