package androidx.compose.animation.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationSpec.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0016J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\b\b\u0001\u0010\u0017*\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00170\u001aH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Landroidx/compose/animation/core/RepeatableSpec;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/FiniteAnimationSpec;", "iterations", "", "animation", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "repeatMode", "Landroidx/compose/animation/core/RepeatMode;", "(ILandroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;)V", "getAnimation", "()Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "getIterations", "()I", "getRepeatMode", "()Landroidx/compose/animation/core/RepeatMode;", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "vectorize", "Landroidx/compose/animation/core/VectorizedFiniteAnimationSpec;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "animation-core_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class RepeatableSpec<T> implements FiniteAnimationSpec<T> {
    private final DurationBasedAnimationSpec<T> animation;
    private final int iterations;
    private final RepeatMode repeatMode;

    public RepeatableSpec(int i, DurationBasedAnimationSpec<T> animation, RepeatMode repeatMode) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        Intrinsics.checkNotNullParameter(repeatMode, "repeatMode");
        this.iterations = i;
        this.animation = animation;
        this.repeatMode = repeatMode;
    }

    public final int getIterations() {
        return this.iterations;
    }

    public final DurationBasedAnimationSpec<T> getAnimation() {
        return this.animation;
    }

    public /* synthetic */ RepeatableSpec(int i, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, durationBasedAnimationSpec, (i2 & 4) != 0 ? RepeatMode.Restart : repeatMode);
    }

    public final RepeatMode getRepeatMode() {
        return this.repeatMode;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public <V extends AnimationVector> VectorizedFiniteAnimationSpec<V> vectorize(TwoWayConverter<T, V> converter) {
        Intrinsics.checkNotNullParameter(converter, "converter");
        return new VectorizedRepeatableSpec(this.iterations, this.animation.vectorize((TwoWayConverter) converter), this.repeatMode);
    }

    public boolean equals(Object other) {
        if (!(other instanceof RepeatableSpec)) {
            return false;
        }
        RepeatableSpec repeatableSpec = (RepeatableSpec) other;
        return repeatableSpec.iterations == this.iterations && Intrinsics.areEqual(repeatableSpec.animation, this.animation) && repeatableSpec.repeatMode == this.repeatMode;
    }

    public int hashCode() {
        return (((this.iterations * 31) + this.animation.hashCode()) * 31) + this.repeatMode.hashCode();
    }
}
