package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorizedAnimationSpec.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedFiniteAnimationSpec;", "delayMillis", "", "getDelayMillis", "()I", "durationMillis", "getDurationMillis", "getDurationNanos", "", "initialValue", "targetValue", "initialVelocity", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)J", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface VectorizedDurationBasedAnimationSpec<V extends AnimationVector> extends VectorizedFiniteAnimationSpec<V> {
    int getDelayMillis();

    int getDurationMillis();

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    long getDurationNanos(V initialValue, V targetValue, V initialVelocity);

    /* compiled from: VectorizedAnimationSpec.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static <V extends AnimationVector> V getEndVelocity(VectorizedDurationBasedAnimationSpec<V> vectorizedDurationBasedAnimationSpec, V initialValue, V targetValue, V initialVelocity) {
            Intrinsics.checkNotNullParameter(vectorizedDurationBasedAnimationSpec, "this");
            Intrinsics.checkNotNullParameter(initialValue, "initialValue");
            Intrinsics.checkNotNullParameter(targetValue, "targetValue");
            Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
            return (V) VectorizedFiniteAnimationSpec.DefaultImpls.getEndVelocity(vectorizedDurationBasedAnimationSpec, initialValue, targetValue, initialVelocity);
        }

        public static <V extends AnimationVector> boolean isInfinite(VectorizedDurationBasedAnimationSpec<V> vectorizedDurationBasedAnimationSpec) {
            Intrinsics.checkNotNullParameter(vectorizedDurationBasedAnimationSpec, "this");
            return VectorizedFiniteAnimationSpec.DefaultImpls.isInfinite(vectorizedDurationBasedAnimationSpec);
        }

        public static <V extends AnimationVector> long getDurationNanos(VectorizedDurationBasedAnimationSpec<V> vectorizedDurationBasedAnimationSpec, V initialValue, V targetValue, V initialVelocity) {
            Intrinsics.checkNotNullParameter(vectorizedDurationBasedAnimationSpec, "this");
            Intrinsics.checkNotNullParameter(initialValue, "initialValue");
            Intrinsics.checkNotNullParameter(targetValue, "targetValue");
            Intrinsics.checkNotNullParameter(initialVelocity, "initialVelocity");
            return (vectorizedDurationBasedAnimationSpec.getDelayMillis() + vectorizedDurationBasedAnimationSpec.getDurationMillis()) * AnimationKt.MillisToNanos;
        }
    }
}
