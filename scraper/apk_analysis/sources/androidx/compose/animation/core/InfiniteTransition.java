package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InfiniteTransition.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0001!B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J!\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005R\u00020\u0000H\u0000¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0015H\u0002J!\u0010\u001c\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005R\u00020\u0000H\u0000¢\u0006\u0002\b\u001dJ\u000f\u0010\u001e\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001f\u0010 R&\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005R\u00020\u00000\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0010\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/compose/animation/core/InfiniteTransition;", "", "()V", "animations", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", "getAnimations$animation_core_release", "()Landroidx/compose/runtime/collection/MutableVector;", "<set-?>", "", "isRunning", "()Z", "setRunning", "(Z)V", "isRunning$delegate", "Landroidx/compose/runtime/MutableState;", "refreshChildNeeded", "getRefreshChildNeeded", "setRefreshChildNeeded", "refreshChildNeeded$delegate", "startTimeNanos", "", "addAnimation", "", "animation", "addAnimation$animation_core_release", "onFrame", "frameTimeNanos", "removeAnimation", "removeAnimation$animation_core_release", "run", "run$animation_core_release", "(Landroidx/compose/runtime/Composer;I)V", "TransitionAnimationState", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class InfiniteTransition {
    public static final int $stable = 8;
    private final MutableVector<TransitionAnimationState<?, ?>> animations = new MutableVector<>(new TransitionAnimationState[16], 0);

    /* renamed from: refreshChildNeeded$delegate, reason: from kotlin metadata */
    private final MutableState refreshChildNeeded = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private long startTimeNanos = Long.MIN_VALUE;

    /* renamed from: isRunning$delegate, reason: from kotlin metadata */
    private final MutableState isRunning = SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* compiled from: InfiniteTransition.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B7\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u0010\u000bJ\u000e\u00103\u001a\u0002042\u0006\u00105\u001a\u00020!J)\u00106\u001a\u0002042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u00107R&\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0005\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\u001c\u0010\u0006\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b)\u0010\u0017\"\u0004\b*\u0010\u0019R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R+\u0010.\u001a\u00028\u00002\u0006\u0010-\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019¨\u00068"}, d2 = {"Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "initialValue", "targetValue", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/animation/core/InfiniteTransition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;)V", "animation", "Landroidx/compose/animation/core/TargetBasedAnimation;", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/AnimationSpec;)V", "getInitialValue", "()Ljava/lang/Object;", "setInitialValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "isFinished", "", "()Z", "setFinished", "(Z)V", "playTimeNanosOffset", "", "getPlayTimeNanosOffset", "()J", "setPlayTimeNanosOffset", "(J)V", "startOnTheNextFrame", "getStartOnTheNextFrame", "setStartOnTheNextFrame", "getTargetValue", "setTargetValue", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "<set-?>", "value", "getValue", "setValue$animation_core_release", "value$delegate", "Landroidx/compose/runtime/MutableState;", "onPlayTimeChanged", "", "playTimeNanos", "updateValues", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;)V", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {
        private TargetBasedAnimation<T, V> animation;
        private AnimationSpec<T> animationSpec;
        private T initialValue;
        private boolean isFinished;
        private long playTimeNanosOffset;
        private boolean startOnTheNextFrame;
        private T targetValue;
        final /* synthetic */ InfiniteTransition this$0;
        private final TwoWayConverter<T, V> typeConverter;

        /* renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;

        public TransitionAnimationState(InfiniteTransition this$0, T t, T t2, TwoWayConverter<T, V> typeConverter, AnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            this.this$0 = this$0;
            this.initialValue = t;
            this.targetValue = t2;
            this.typeConverter = typeConverter;
            this.animationSpec = animationSpec;
            this.value = SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            this.animation = new TargetBasedAnimation<>(this.animationSpec, typeConverter, this.initialValue, this.targetValue, (AnimationVector) null, 16, (DefaultConstructorMarker) null);
        }

        public final T getInitialValue() {
            return this.initialValue;
        }

        public final void setInitialValue(T t) {
            this.initialValue = t;
        }

        public final T getTargetValue() {
            return this.targetValue;
        }

        public final void setTargetValue(T t) {
            this.targetValue = t;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public final AnimationSpec<T> getAnimationSpec() {
            return this.animationSpec;
        }

        public final void setAnimationSpec(AnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "<set-?>");
            this.animationSpec = animationSpec;
        }

        @Override // androidx.compose.runtime.State
        public T getValue() {
            return this.value.getValue();
        }

        public void setValue$animation_core_release(T t) {
            this.value.setValue(t);
        }

        public final TargetBasedAnimation<T, V> getAnimation() {
            return this.animation;
        }

        public final void setAnimation(TargetBasedAnimation<T, V> targetBasedAnimation) {
            Intrinsics.checkNotNullParameter(targetBasedAnimation, "<set-?>");
            this.animation = targetBasedAnimation;
        }

        /* renamed from: isFinished, reason: from getter */
        public final boolean getIsFinished() {
            return this.isFinished;
        }

        public final void setFinished(boolean z) {
            this.isFinished = z;
        }

        public final boolean getStartOnTheNextFrame() {
            return this.startOnTheNextFrame;
        }

        public final void setStartOnTheNextFrame(boolean z) {
            this.startOnTheNextFrame = z;
        }

        public final long getPlayTimeNanosOffset() {
            return this.playTimeNanosOffset;
        }

        public final void setPlayTimeNanosOffset(long j) {
            this.playTimeNanosOffset = j;
        }

        public final void updateValues(T initialValue, T targetValue, AnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            this.initialValue = initialValue;
            this.targetValue = targetValue;
            this.animationSpec = animationSpec;
            this.animation = new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, targetValue, (AnimationVector) null, 16, (DefaultConstructorMarker) null);
            this.this$0.setRefreshChildNeeded(true);
            this.isFinished = false;
            this.startOnTheNextFrame = true;
        }

        public final void onPlayTimeChanged(long playTimeNanos) {
            this.this$0.setRefreshChildNeeded(false);
            if (this.startOnTheNextFrame) {
                this.startOnTheNextFrame = false;
                this.playTimeNanosOffset = playTimeNanos;
            }
            long j = playTimeNanos - this.playTimeNanosOffset;
            setValue$animation_core_release(this.animation.getValueFromNanos(j));
            this.isFinished = this.animation.isFinishedFromNanos(j);
        }
    }

    public final MutableVector<TransitionAnimationState<?, ?>> getAnimations$animation_core_release() {
        return this.animations;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getRefreshChildNeeded() {
        return ((Boolean) this.refreshChildNeeded.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRefreshChildNeeded(boolean z) {
        this.refreshChildNeeded.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isRunning() {
        return ((Boolean) this.isRunning.getValue()).booleanValue();
    }

    private final void setRunning(boolean z) {
        this.isRunning.setValue(Boolean.valueOf(z));
    }

    public final void addAnimation$animation_core_release(TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.animations.add(animation);
        setRefreshChildNeeded(true);
    }

    public final void removeAnimation$animation_core_release(TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.animations.remove(animation);
    }

    public final void run$animation_core_release(Composer composer, final int i) {
        Composer startRestartGroup = composer.startRestartGroup(2102343854);
        ComposerKt.sourceInformation(startRestartGroup, "C(run):InfiniteTransition.kt#pdpnli");
        if (isRunning() || getRefreshChildNeeded()) {
            startRestartGroup.startReplaceableGroup(2102343911);
            ComposerKt.sourceInformation(startRestartGroup, "131@5279L148");
            EffectsKt.LaunchedEffect(this, new InfiniteTransition$run$1(this, null), startRestartGroup, 8);
            startRestartGroup.endReplaceableGroup();
        } else {
            startRestartGroup.startReplaceableGroup(2102344083);
            startRestartGroup.endReplaceableGroup();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.core.InfiniteTransition$run$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                InfiniteTransition.this.run$animation_core_release(composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFrame(long frameTimeNanos) {
        boolean z;
        if (this.startTimeNanos == Long.MIN_VALUE) {
            this.startTimeNanos = frameTimeNanos;
        }
        long j = frameTimeNanos - this.startTimeNanos;
        MutableVector<TransitionAnimationState<?, ?>> mutableVector = this.animations;
        int size = mutableVector.getSize();
        if (size > 0) {
            TransitionAnimationState<?, ?>[] content = mutableVector.getContent();
            z = true;
            int i = 0;
            do {
                TransitionAnimationState<?, ?> transitionAnimationState = content[i];
                if (!transitionAnimationState.getIsFinished()) {
                    transitionAnimationState.onPlayTimeChanged(j);
                }
                if (!transitionAnimationState.getIsFinished()) {
                    z = false;
                }
                i++;
            } while (i < size);
        } else {
            z = true;
        }
        setRunning(!z);
    }
}
