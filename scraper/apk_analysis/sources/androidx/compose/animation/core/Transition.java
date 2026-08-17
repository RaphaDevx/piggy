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
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.protocol.Request;
import io.sentry.protocol.SentryThread;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004cdefB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B!\b\u0001\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ'\u0010G\u001a\u00020\u001b2\u0018\u0010H\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\bIJ\u0019\u0010J\u001a\u00020\u001b2\n\u0010K\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\bLJ\u0017\u0010M\u001a\u00020N2\u0006\u0010:\u001a\u00028\u0000H\u0001¢\u0006\u0004\bO\u0010PJ\b\u0010Q\u001a\u00020NH\u0002J\u0015\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020'H\u0000¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020NH\u0000¢\u0006\u0002\bVJ\u0015\u0010W\u001a\u00020N2\u0006\u0010S\u001a\u00020'H\u0000¢\u0006\u0002\bXJ'\u0010Y\u001a\u00020N2\u0018\u0010Z\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030[R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\b\\J'\u0010Y\u001a\u00020N2\u0018\u0010H\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\b\\J\u0019\u0010]\u001a\u00020\u001b2\n\u0010K\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\b^J%\u0010_\u001a\u00020N2\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00002\u0006\u0010(\u001a\u00020'H\u0007¢\u0006\u0002\u0010`J\u0017\u0010a\u001a\u00020N2\u0006\u0010:\u001a\u00028\u0000H\u0001¢\u0006\u0004\bb\u0010PR&\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u00000\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00008F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR1\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b8@@@X\u0081\u008e\u0002¢\u0006\u0018\n\u0004\b\"\u0010#\u0012\u0004\b\u001f\u0010\u0011\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b \u0010!R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010(\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020'8F@FX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b.\u0010#\u0012\u0004\b)\u0010\u0011\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R7\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000/2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000/8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010#\u001a\u0004\b1\u00102\"\u0004\b3\u00104R+\u00106\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020'8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b9\u0010#\u001a\u0004\b7\u0010+\"\u0004\b8\u0010-R+\u0010:\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u00008F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010#\u001a\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019R1\u0010>\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020'8F@BX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\bB\u0010#\u0012\u0004\b?\u0010\u0011\u001a\u0004\b@\u0010+\"\u0004\bA\u0010-R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010C\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bF\u0010#\u001a\u0004\bD\u0010\u001c\"\u0004\bE\u0010!¨\u0006g"}, d2 = {"Landroidx/compose/animation/core/Transition;", ExifInterface.LATITUDE_SOUTH, "", "initialState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "transitionState", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;)V", "_animations", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "_transitions", "animations", "", "getAnimations$annotations", "()V", "getAnimations", "()Ljava/util/List;", "value", "currentState", "getCurrentState", "()Ljava/lang/Object;", "setCurrentState$animation_core_release", "(Ljava/lang/Object;)V", "isRunning", "", "()Z", "<set-?>", "isSeeking", "isSeeking$annotations", "setSeeking", "(Z)V", "isSeeking$delegate", "Landroidx/compose/runtime/MutableState;", "getLabel", "()Ljava/lang/String;", "lastSeekedTimeNanos", "", "playTimeNanos", "getPlayTimeNanos$annotations", "getPlayTimeNanos", "()J", "setPlayTimeNanos", "(J)V", "playTimeNanos$delegate", "Landroidx/compose/animation/core/Transition$Segment;", "segment", "getSegment", "()Landroidx/compose/animation/core/Transition$Segment;", "setSegment", "(Landroidx/compose/animation/core/Transition$Segment;)V", "segment$delegate", "startTimeNanos", "getStartTimeNanos", "setStartTimeNanos", "startTimeNanos$delegate", "targetState", "getTargetState", "setTargetState$animation_core_release", "targetState$delegate", "totalDurationNanos", "getTotalDurationNanos$annotations", "getTotalDurationNanos", "setTotalDurationNanos", "totalDurationNanos$delegate", "updateChildrenNeeded", "getUpdateChildrenNeeded$animation_core_release", "setUpdateChildrenNeeded$animation_core_release", "updateChildrenNeeded$delegate", "addAnimation", "animation", "addAnimation$animation_core_release", "addTransition", "transition", "addTransition$animation_core_release", "animateTo", "", "animateTo$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "onChildAnimationUpdated", "onFrame", "frameTimeNanos", "onFrame$animation_core_release", "onTransitionEnd", "onTransitionEnd$animation_core_release", "onTransitionStart", "onTransitionStart$animation_core_release", "removeAnimation", "deferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "removeAnimation$animation_core_release", "removeTransition", "removeTransition$animation_core_release", "seek", "(Ljava/lang/Object;Ljava/lang/Object;J)V", "updateTarget", "updateTarget$animation_core_release", "DeferredAnimation", "Segment", "SegmentImpl", "TransitionAnimationState", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class Transition<S> {
    private final MutableVector<Transition<S>.TransitionAnimationState<?, ?>> _animations;
    private final MutableVector<Transition<?>> _transitions;
    private final List<Transition<S>.TransitionAnimationState<?, ?>> animations;

    /* renamed from: isSeeking$delegate, reason: from kotlin metadata */
    private final MutableState isSeeking;
    private final String label;
    private long lastSeekedTimeNanos;

    /* renamed from: playTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableState playTimeNanos;

    /* renamed from: segment$delegate, reason: from kotlin metadata */
    private final MutableState segment;

    /* renamed from: startTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableState startTimeNanos;

    /* renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;

    /* renamed from: totalDurationNanos$delegate, reason: from kotlin metadata */
    private final MutableState totalDurationNanos;
    private final MutableTransitionState<S> transitionState;

    /* renamed from: updateChildrenNeeded$delegate, reason: from kotlin metadata */
    private final MutableState updateChildrenNeeded;

    @InternalAnimationApi
    public static /* synthetic */ void getAnimations$annotations() {
    }

    @InternalAnimationApi
    public static /* synthetic */ void getPlayTimeNanos$annotations() {
    }

    @InternalAnimationApi
    public static /* synthetic */ void getTotalDurationNanos$annotations() {
    }

    public static /* synthetic */ void isSeeking$annotations() {
    }

    public Transition(MutableTransitionState<S> transitionState, String str) {
        Intrinsics.checkNotNullParameter(transitionState, "transitionState");
        this.transitionState = transitionState;
        this.label = str;
        this.targetState = SnapshotStateKt.mutableStateOf$default(getCurrentState(), null, 2, null);
        this.segment = SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()), null, 2, null);
        this.playTimeNanos = SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
        this.startTimeNanos = SnapshotStateKt.mutableStateOf$default(Long.MIN_VALUE, null, 2, null);
        this.updateChildrenNeeded = SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        MutableVector<Transition<S>.TransitionAnimationState<?, ?>> mutableVector = new MutableVector<>(new TransitionAnimationState[16], 0);
        this._animations = mutableVector;
        this._transitions = new MutableVector<>(new Transition[16], 0);
        this.animations = mutableVector.asMutableList();
        this.isSeeking = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.totalDurationNanos = SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
    }

    public /* synthetic */ Transition(MutableTransitionState mutableTransitionState, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableTransitionState, (i & 2) != 0 ? null : str);
    }

    public final String getLabel() {
        return this.label;
    }

    public Transition(S s, String str) {
        this(new MutableTransitionState(s), str);
    }

    public final S getCurrentState() {
        return this.transitionState.getCurrentState();
    }

    public final void setCurrentState$animation_core_release(S s) {
        this.transitionState.setCurrentState$animation_core_release(s);
    }

    public final S getTargetState() {
        return (S) this.targetState.getValue();
    }

    public final void setTargetState$animation_core_release(S s) {
        this.targetState.setValue(s);
    }

    private final void setSegment(Segment<S> segment) {
        this.segment.setValue(segment);
    }

    public final Segment<S> getSegment() {
        return (Segment) this.segment.getValue();
    }

    public final boolean isRunning() {
        return getStartTimeNanos() != Long.MIN_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long getPlayTimeNanos() {
        return ((Number) this.playTimeNanos.getValue()).longValue();
    }

    public final void setPlayTimeNanos(long j) {
        this.playTimeNanos.setValue(Long.valueOf(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long getStartTimeNanos() {
        return ((Number) this.startTimeNanos.getValue()).longValue();
    }

    private final void setStartTimeNanos(long j) {
        this.startTimeNanos.setValue(Long.valueOf(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getUpdateChildrenNeeded$animation_core_release() {
        return ((Boolean) this.updateChildrenNeeded.getValue()).booleanValue();
    }

    public final void setUpdateChildrenNeeded$animation_core_release(boolean z) {
        this.updateChildrenNeeded.setValue(Boolean.valueOf(z));
    }

    public final List<Transition<S>.TransitionAnimationState<?, ?>> getAnimations() {
        return this.animations;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSeeking() {
        return ((Boolean) this.isSeeking.getValue()).booleanValue();
    }

    public final void setSeeking(boolean z) {
        this.isSeeking.setValue(Boolean.valueOf(z));
    }

    private final void setTotalDurationNanos(long j) {
        this.totalDurationNanos.setValue(Long.valueOf(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long getTotalDurationNanos() {
        return ((Number) this.totalDurationNanos.getValue()).longValue();
    }

    public final void onFrame$animation_core_release(long frameTimeNanos) {
        if (getStartTimeNanos() == Long.MIN_VALUE) {
            onTransitionStart$animation_core_release(frameTimeNanos);
        }
        setUpdateChildrenNeeded$animation_core_release(false);
        setPlayTimeNanos(frameTimeNanos - getStartTimeNanos());
        MutableVector<Transition<S>.TransitionAnimationState<?, ?>> mutableVector = this._animations;
        int size = mutableVector.getSize();
        boolean z = true;
        if (size > 0) {
            Transition<S>.TransitionAnimationState<?, ?>[] content = mutableVector.getContent();
            int i = 0;
            do {
                Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState = content[i];
                if (!transitionAnimationState.isFinished$animation_core_release()) {
                    transitionAnimationState.onPlayTimeChanged$animation_core_release(getPlayTimeNanos());
                }
                if (!transitionAnimationState.isFinished$animation_core_release()) {
                    z = false;
                }
                i++;
            } while (i < size);
        }
        MutableVector<Transition<?>> mutableVector2 = this._transitions;
        int size2 = mutableVector2.getSize();
        if (size2 > 0) {
            Transition<?>[] content2 = mutableVector2.getContent();
            int i2 = 0;
            do {
                Transition<?> transition = content2[i2];
                if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                    transition.onFrame$animation_core_release(getPlayTimeNanos());
                }
                if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                    z = false;
                }
                i2++;
            } while (i2 < size2);
        }
        if (z) {
            onTransitionEnd$animation_core_release();
        }
    }

    public final void onTransitionStart$animation_core_release(long frameTimeNanos) {
        setStartTimeNanos(frameTimeNanos);
        this.transitionState.setRunning$animation_core_release(true);
    }

    public final void onTransitionEnd$animation_core_release() {
        setStartTimeNanos(Long.MIN_VALUE);
        setCurrentState$animation_core_release(getTargetState());
        setPlayTimeNanos(0L);
        this.transitionState.setRunning$animation_core_release(false);
    }

    @InternalAnimationApi
    public final void seek(S initialState, S targetState, long playTimeNanos) {
        setStartTimeNanos(Long.MIN_VALUE);
        int i = 0;
        this.transitionState.setRunning$animation_core_release(false);
        if (!isSeeking() || !Intrinsics.areEqual(getCurrentState(), initialState) || !Intrinsics.areEqual(getTargetState(), targetState)) {
            setCurrentState$animation_core_release(initialState);
            setTargetState$animation_core_release(targetState);
            setSeeking(true);
            setSegment(new SegmentImpl(initialState, targetState));
        }
        if (playTimeNanos != this.lastSeekedTimeNanos) {
            MutableVector<Transition<S>.TransitionAnimationState<?, ?>> mutableVector = this._animations;
            int size = mutableVector.getSize();
            if (size > 0) {
                Transition<S>.TransitionAnimationState<?, ?>[] content = mutableVector.getContent();
                do {
                    content[i].seekTo$animation_core_release(playTimeNanos);
                    i++;
                } while (i < size);
            }
            this.lastSeekedTimeNanos = playTimeNanos;
        }
    }

    public final boolean addTransition$animation_core_release(Transition<?> transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        return this._transitions.add(transition);
    }

    public final boolean removeTransition$animation_core_release(Transition<?> transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        return this._transitions.remove(transition);
    }

    public final boolean addAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        return this._animations.add(animation);
    }

    public final void removeAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this._animations.remove(animation);
    }

    public final void updateTarget$animation_core_release(final S s, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(-1598253712);
        ComposerKt.sourceInformation(startRestartGroup, "C(updateTarget):Transition.kt#pdpnli");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(s) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(this) ? 32 : 16;
        }
        if (((i2 & 91) ^ 18) != 0 || !startRestartGroup.getSkipping()) {
            if (!isSeeking() && !Intrinsics.areEqual(getTargetState(), s)) {
                setSegment(new SegmentImpl(getTargetState(), s));
                setCurrentState$animation_core_release(getTargetState());
                setTargetState$animation_core_release(s);
                if (!isRunning()) {
                    setUpdateChildrenNeeded$animation_core_release(true);
                }
                MutableVector<Transition<S>.TransitionAnimationState<?, ?>> mutableVector = this._animations;
                int size = mutableVector.getSize();
                if (size > 0) {
                    Transition<S>.TransitionAnimationState<?, ?>[] content = mutableVector.getContent();
                    int i3 = 0;
                    do {
                        content[i3].resetAnimation$animation_core_release();
                        i3++;
                    } while (i3 < size);
                }
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>(this) { // from class: androidx.compose.animation.core.Transition$updateTarget$2
            final /* synthetic */ Transition<S> $tmp0_rcvr;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.$tmp0_rcvr = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                this.$tmp0_rcvr.updateTarget$animation_core_release(s, composer2, i | 1);
            }
        });
    }

    public final void animateTo$animation_core_release(final S s, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(-1097580081);
        ComposerKt.sourceInformation(startRestartGroup, "C(animateTo):Transition.kt#pdpnli");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(s) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(this) ? 32 : 16;
        }
        if (((i2 & 91) ^ 18) != 0 || !startRestartGroup.getSkipping()) {
            if (!isSeeking()) {
                startRestartGroup.startReplaceableGroup(-1097580025);
                ComposerKt.sourceInformation(startRestartGroup, "372@14684L25");
                updateTarget$animation_core_release(s, startRestartGroup, i2 & WebSocketProtocol.PAYLOAD_SHORT);
                if (!Intrinsics.areEqual(s, getCurrentState()) || isRunning() || getUpdateChildrenNeeded$animation_core_release()) {
                    startRestartGroup.startReplaceableGroup(-1097579780);
                    ComposerKt.sourceInformation(startRestartGroup, "376@14954L213,376@14933L234");
                    int i3 = (i2 >> 3) & 14;
                    startRestartGroup.startReplaceableGroup(-3686930);
                    ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
                    boolean changed = startRestartGroup.changed(this);
                    Transition$animateTo$1$1 rememberedValue = startRestartGroup.rememberedValue();
                    if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new Transition$animateTo$1$1(this, null);
                        startRestartGroup.updateRememberedValue(rememberedValue);
                    }
                    startRestartGroup.endReplaceableGroup();
                    EffectsKt.LaunchedEffect(this, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, startRestartGroup, i3);
                    startRestartGroup.endReplaceableGroup();
                } else {
                    startRestartGroup.startReplaceableGroup(-1097579514);
                    startRestartGroup.endReplaceableGroup();
                }
                startRestartGroup.endReplaceableGroup();
            } else {
                startRestartGroup.startReplaceableGroup(-1097579504);
                startRestartGroup.endReplaceableGroup();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>(this) { // from class: androidx.compose.animation.core.Transition$animateTo$2
            final /* synthetic */ Transition<S> $tmp0_rcvr;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.$tmp0_rcvr = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                this.$tmp0_rcvr.animateTo$animation_core_release(s, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChildAnimationUpdated() {
        setUpdateChildrenNeeded$animation_core_release(true);
        if (isSeeking()) {
            MutableVector<Transition<S>.TransitionAnimationState<?, ?>> mutableVector = this._animations;
            int size = mutableVector.getSize();
            long j = 0;
            if (size > 0) {
                Transition<S>.TransitionAnimationState<?, ?>[] content = mutableVector.getContent();
                int i = 0;
                do {
                    Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState = content[i];
                    j = Math.max(j, transitionAnimationState.getDurationNanos$animation_core_release());
                    transitionAnimationState.seekTo$animation_core_release(this.lastSeekedTimeNanos);
                    i++;
                } while (i < size);
            }
            setTotalDurationNanos(j);
            setUpdateChildrenNeeded$animation_core_release(false);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B3\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00028\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0015\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001dH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020BH\u0000¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001dH\u0000¢\u0006\u0002\bHJ!\u0010I\u001a\u00020B2\b\b\u0002\u0010\u0005\u001a\u00028\u00012\b\b\u0002\u0010J\u001a\u00020!H\u0002¢\u0006\u0002\u0010KJ-\u0010L\u001a\u00020B2\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u00103\u001a\u00028\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0000¢\u0006\u0004\bM\u0010NJ%\u0010O\u001a\u00020B2\u0006\u00103\u001a\u00028\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0000¢\u0006\u0004\bP\u0010QRC\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R7\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020!8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0014\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R+\u0010*\u001a\u00020!2\u0006\u0010\f\u001a\u00020!8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u0014\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R+\u0010.\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u0014\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u00101R+\u00103\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u0014\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R+\u0010;\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u00018V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\u0014\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\u0010\u0010?\u001a\u00028\u0002X\u0082\u000e¢\u0006\u0004\n\u0002\u0010@¨\u0006R"}, d2 = {"Landroidx/compose/animation/core/Transition$TransitionAnimationState;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "initialValue", "initialVelocityVector", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "<set-?>", "Landroidx/compose/animation/core/TargetBasedAnimation;", "animation", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "animation$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animationSpec$delegate", "durationNanos", "", "getDurationNanos$animation_core_release", "()J", "interruptionSpec", "", "isFinished", "isFinished$animation_core_release", "()Z", "setFinished$animation_core_release", "(Z)V", "isFinished$delegate", "getLabel", "()Ljava/lang/String;", "needsReset", "getNeedsReset", "setNeedsReset", "needsReset$delegate", "offsetTimeNanos", "getOffsetTimeNanos", "setOffsetTimeNanos", "(J)V", "offsetTimeNanos$delegate", "targetValue", "getTargetValue", "()Ljava/lang/Object;", "setTargetValue", "(Ljava/lang/Object;)V", "targetValue$delegate", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "value", "getValue", "setValue$animation_core_release", "value$delegate", "velocityVector", "Landroidx/compose/animation/core/AnimationVector;", "onPlayTimeChanged", "", "playTimeNanos", "onPlayTimeChanged$animation_core_release", "resetAnimation", "resetAnimation$animation_core_release", "seekTo", "seekTo$animation_core_release", "updateAnimation", "isInterrupted", "(Ljava/lang/Object;Z)V", "updateInitialAndTargetValue", "updateInitialAndTargetValue$animation_core_release", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "updateTargetValue", "updateTargetValue$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @InternalAnimationApi
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {

        /* renamed from: animation$delegate, reason: from kotlin metadata */
        private final MutableState animation;

        /* renamed from: animationSpec$delegate, reason: from kotlin metadata */
        private final MutableState animationSpec;
        private final FiniteAnimationSpec<T> interruptionSpec;

        /* renamed from: isFinished$delegate, reason: from kotlin metadata */
        private final MutableState isFinished;
        private final String label;

        /* renamed from: needsReset$delegate, reason: from kotlin metadata */
        private final MutableState needsReset;

        /* renamed from: offsetTimeNanos$delegate, reason: from kotlin metadata */
        private final MutableState offsetTimeNanos;

        /* renamed from: targetValue$delegate, reason: from kotlin metadata */
        private final MutableState targetValue;
        final /* synthetic */ Transition<S> this$0;
        private final TwoWayConverter<T, V> typeConverter;

        /* renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;
        private V velocityVector;

        public TransitionAnimationState(Transition this$0, T t, V initialVelocityVector, TwoWayConverter<T, V> typeConverter, String label) {
            T invoke;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(initialVelocityVector, "initialVelocityVector");
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = this$0;
            this.typeConverter = typeConverter;
            this.label = label;
            this.targetValue = SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            this.animationSpec = SnapshotStateKt.mutableStateOf$default(AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), null, 2, null);
            this.animation = SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation(getAnimationSpec(), typeConverter, t, getTargetValue(), initialVelocityVector), null, 2, null);
            this.isFinished = SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
            this.offsetTimeNanos = SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
            int i = 0;
            this.needsReset = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            this.value = SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            this.velocityVector = initialVelocityVector;
            Float f = VisibilityThresholdsKt.getVisibilityThresholdMap().get(typeConverter);
            if (f == null) {
                invoke = null;
            } else {
                float floatValue = f.floatValue();
                V invoke2 = getTypeConverter().getConvertToVector().invoke(t);
                int size = invoke2.getSize();
                if (size > 0) {
                    while (true) {
                        int i2 = i + 1;
                        invoke2.set$animation_core_release(i, floatValue);
                        if (i2 >= size) {
                            break;
                        } else {
                            i = i2;
                        }
                    }
                }
                invoke = getTypeConverter().getConvertFromVector().invoke(invoke2);
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, invoke, 3, null);
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public final String getLabel() {
            return this.label;
        }

        private final T getTargetValue() {
            return this.targetValue.getValue();
        }

        private final void setTargetValue(T t) {
            this.targetValue.setValue(t);
        }

        private final FiniteAnimationSpec<T> getAnimationSpec() {
            return (FiniteAnimationSpec) this.animationSpec.getValue();
        }

        private final void setAnimationSpec(FiniteAnimationSpec<T> finiteAnimationSpec) {
            this.animationSpec.setValue(finiteAnimationSpec);
        }

        private final TargetBasedAnimation<T, V> getAnimation() {
            return (TargetBasedAnimation) this.animation.getValue();
        }

        private final void setAnimation(TargetBasedAnimation<T, V> targetBasedAnimation) {
            this.animation.setValue(targetBasedAnimation);
        }

        public final boolean isFinished$animation_core_release() {
            return ((Boolean) this.isFinished.getValue()).booleanValue();
        }

        public final void setFinished$animation_core_release(boolean z) {
            this.isFinished.setValue(Boolean.valueOf(z));
        }

        private final long getOffsetTimeNanos() {
            return ((Number) this.offsetTimeNanos.getValue()).longValue();
        }

        private final void setOffsetTimeNanos(long j) {
            this.offsetTimeNanos.setValue(Long.valueOf(j));
        }

        private final boolean getNeedsReset() {
            return ((Boolean) this.needsReset.getValue()).booleanValue();
        }

        private final void setNeedsReset(boolean z) {
            this.needsReset.setValue(Boolean.valueOf(z));
        }

        @Override // androidx.compose.runtime.State
        public T getValue() {
            return this.value.getValue();
        }

        public void setValue$animation_core_release(T t) {
            this.value.setValue(t);
        }

        public final long getDurationNanos$animation_core_release() {
            return getAnimation().getDurationNanos();
        }

        public final void onPlayTimeChanged$animation_core_release(long playTimeNanos) {
            long offsetTimeNanos = playTimeNanos - getOffsetTimeNanos();
            setValue$animation_core_release(getAnimation().getValueFromNanos(offsetTimeNanos));
            this.velocityVector = getAnimation().getVelocityVectorFromNanos(offsetTimeNanos);
            if (getAnimation().isFinishedFromNanos(offsetTimeNanos)) {
                setFinished$animation_core_release(true);
                setOffsetTimeNanos(0L);
            }
        }

        public final void seekTo$animation_core_release(long playTimeNanos) {
            setValue$animation_core_release(getAnimation().getValueFromNanos(playTimeNanos));
            this.velocityVector = getAnimation().getVelocityVectorFromNanos(playTimeNanos);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* synthetic */ void updateAnimation$default(TransitionAnimationState transitionAnimationState, Object obj, boolean z, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = transitionAnimationState.getValue();
            }
            if ((i & 2) != 0) {
                z = false;
            }
            transitionAnimationState.updateAnimation(obj, z);
        }

        private final void updateAnimation(T initialValue, boolean isInterrupted) {
            FiniteAnimationSpec<T> animationSpec;
            if (isInterrupted) {
                animationSpec = getAnimationSpec() instanceof SpringSpec ? getAnimationSpec() : this.interruptionSpec;
            } else {
                animationSpec = getAnimationSpec();
            }
            setAnimation(new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, getTargetValue(), this.velocityVector));
            this.this$0.onChildAnimationUpdated();
        }

        public final void resetAnimation$animation_core_release() {
            setNeedsReset(true);
        }

        public final void updateTargetValue$animation_core_release(T targetValue, FiniteAnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            if (!Intrinsics.areEqual(getTargetValue(), targetValue) || getNeedsReset()) {
                setTargetValue(targetValue);
                setAnimationSpec(animationSpec);
                updateAnimation$default(this, null, !isFinished$animation_core_release(), 1, null);
                setFinished$animation_core_release(false);
                setOffsetTimeNanos(this.this$0.getPlayTimeNanos());
                setNeedsReset(false);
            }
        }

        public final void updateInitialAndTargetValue$animation_core_release(T initialValue, T targetValue, FiniteAnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            setTargetValue(targetValue);
            setAnimationSpec(animationSpec);
            if (Intrinsics.areEqual(getAnimation().getInitialValue(), initialValue)) {
                Intrinsics.areEqual(getAnimation().getTargetValue(), targetValue);
            }
            updateAnimation$default(this, initialValue, false, 2, null);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/Transition$SegmentImpl;", ExifInterface.LATITUDE_SOUTH, "Landroidx/compose/animation/core/Transition$Segment;", "initialState", "targetState", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTargetState", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class SegmentImpl<S> implements Segment<S> {
        private final S initialState;
        private final S targetState;

        public SegmentImpl(S s, S s2) {
            this.initialState = s;
            this.targetState = s2;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public boolean isTransitioningTo(S s, S s2) {
            return Segment.DefaultImpls.isTransitioningTo(this, s, s2);
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getInitialState() {
            return this.initialState;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getTargetState() {
            return this.targetState;
        }

        public boolean equals(Object other) {
            if (other instanceof Segment) {
                Segment segment = (Segment) other;
                if (Intrinsics.areEqual(getInitialState(), segment.getInitialState()) && Intrinsics.areEqual(getTargetState(), segment.getTargetState())) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            S initialState = getInitialState();
            int hashCode = (initialState != null ? initialState.hashCode() : 0) * 31;
            S targetState = getTargetState();
            return hashCode + (targetState != null ? targetState.hashCode() : 0);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001a\u0010\b\u001a\u00020\t*\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0001H\u0096\u0004¢\u0006\u0002\u0010\nR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\u000b"}, d2 = {"Landroidx/compose/animation/core/Transition$Segment;", ExifInterface.LATITUDE_SOUTH, "", "initialState", "getInitialState", "()Ljava/lang/Object;", "targetState", "getTargetState", "isTransitioningTo", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Segment<S> {
        S getInitialState();

        S getTargetState();

        boolean isTransitioningTo(S s, S s2);

        /* compiled from: Transition.kt */
        @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class DefaultImpls {
            public static <S> boolean isTransitioningTo(Segment<S> segment, S s, S s2) {
                Intrinsics.checkNotNullParameter(segment, "this");
                return Intrinsics.areEqual(s, segment.getInitialState()) && Intrinsics.areEqual(s2, segment.getTargetState());
            }
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\u00020\u0004:\u0001#B#\b\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJT\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00162#\u0010\u0017\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a0\u0018¢\u0006\u0002\b\u001b2!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00028\u00010\u0018J\r\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"RB\u0010\n\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000bR\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "data", "Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "Landroidx/compose/animation/core/Transition;", "getData$animation_core_release", "()Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "setData$animation_core_release", "(Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;)V", "getLabel", "()Ljava/lang/String;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "animate", "Landroidx/compose/runtime/State;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", SentryThread.JsonKeys.STATE, "setupSeeking", "", "setupSeeking$animation_core_release", "DeferredAnimationData", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @InternalAnimationApi
    public final class DeferredAnimation<T, V extends AnimationVector> {
        private Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data;
        private final String label;
        final /* synthetic */ Transition<S> this$0;
        private final TwoWayConverter<T, V> typeConverter;

        public DeferredAnimation(Transition this$0, TwoWayConverter<T, V> typeConverter, String label) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = this$0;
            this.typeConverter = typeConverter;
            this.label = label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public final String getLabel() {
            return this.label;
        }

        public final Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> getData$animation_core_release() {
            return this.data;
        }

        public final void setData$animation_core_release(Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData) {
            this.data = deferredAnimationData;
        }

        /* compiled from: Transition.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0080\u0004\u0018\u0000*\u0004\b\u0003\u0010\u0001*\b\b\u0004\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004Bk\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\f\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\t¢\u0006\u0002\u0010\u0011R'\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R5\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R7\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "animation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", SentryThread.JsonKeys.STATE, "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getAnimation", "()Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "getTargetValueByState", "()Lkotlin/jvm/functions/Function1;", "setTargetValueByState", "(Lkotlin/jvm/functions/Function1;)V", "getTransitionSpec", "setTransitionSpec", "value", "getValue", "()Ljava/lang/Object;", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public final class DeferredAnimationData<T, V extends AnimationVector> implements State<T> {
            private final Transition<S>.TransitionAnimationState<T, V> animation;
            private Function1<? super S, ? extends T> targetValueByState;
            final /* synthetic */ Transition<S>.DeferredAnimation<T, V> this$0;
            private Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec;

            public DeferredAnimationData(DeferredAnimation this$0, Transition<S>.TransitionAnimationState<T, V> animation, Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, Function1<? super S, ? extends T> targetValueByState) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(animation, "animation");
                Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
                Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
                this.this$0 = this$0;
                this.animation = animation;
                this.transitionSpec = transitionSpec;
                this.targetValueByState = targetValueByState;
            }

            public final Transition<S>.TransitionAnimationState<T, V> getAnimation() {
                return this.animation;
            }

            public final Function1<Segment<S>, FiniteAnimationSpec<T>> getTransitionSpec() {
                return this.transitionSpec;
            }

            public final void setTransitionSpec(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> function1) {
                Intrinsics.checkNotNullParameter(function1, "<set-?>");
                this.transitionSpec = function1;
            }

            public final Function1<S, T> getTargetValueByState() {
                return this.targetValueByState;
            }

            public final void setTargetValueByState(Function1<? super S, ? extends T> function1) {
                Intrinsics.checkNotNullParameter(function1, "<set-?>");
                this.targetValueByState = function1;
            }

            @Override // androidx.compose.runtime.State
            public T getValue() {
                this.animation.updateTargetValue$animation_core_release(this.targetValueByState.invoke(this.this$0.this$0.getTargetState()), this.transitionSpec.invoke(this.this$0.this$0.getSegment()));
                return this.animation.getValue();
            }
        }

        public final State<T> animate(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, Function1<? super S, ? extends T> targetValueByState) {
            Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
            Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData = this.data;
            if (deferredAnimationData == null) {
                Transition<S> transition = this.this$0;
                deferredAnimationData = new DeferredAnimationData<>(this, new TransitionAnimationState(transition, targetValueByState.invoke(transition.getCurrentState()), AnimationStateKt.createZeroVectorFrom(this.typeConverter, targetValueByState.invoke(this.this$0.getCurrentState())), this.typeConverter, this.label), transitionSpec, targetValueByState);
                Transition<S> transition2 = this.this$0;
                setData$animation_core_release(deferredAnimationData);
                transition2.addAnimation$animation_core_release(deferredAnimationData.getAnimation());
            }
            Transition<S> transition3 = this.this$0;
            deferredAnimationData.setTargetValueByState(targetValueByState);
            deferredAnimationData.setTransitionSpec(transitionSpec);
            deferredAnimationData.getAnimation().updateTargetValue$animation_core_release(targetValueByState.invoke(transition3.getTargetState()), transitionSpec.invoke(transition3.getSegment()));
            return deferredAnimationData;
        }

        public final void setupSeeking$animation_core_release() {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData = this.data;
            if (deferredAnimationData == null) {
                return;
            }
            Transition<S> transition = this.this$0;
            deferredAnimationData.getAnimation().updateInitialAndTargetValue$animation_core_release(deferredAnimationData.getTargetValueByState().invoke(transition.getSegment().getInitialState()), deferredAnimationData.getTargetValueByState().invoke(transition.getSegment().getTargetState()), deferredAnimationData.getTransitionSpec().invoke(transition.getSegment()));
        }
    }

    public final void removeAnimation$animation_core_release(Transition<S>.DeferredAnimation<?, ?> deferredAnimation) {
        Intrinsics.checkNotNullParameter(deferredAnimation, "deferredAnimation");
        Transition<S>.DeferredAnimationData<?, V>.DeferredAnimationData<?, ?> data$animation_core_release = deferredAnimation.getData$animation_core_release();
        if (data$animation_core_release == null) {
            return;
        }
        removeAnimation$animation_core_release(data$animation_core_release.getAnimation());
    }
}
