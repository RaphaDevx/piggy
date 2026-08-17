package com.scandit.datacapture.core.internal.sdk.ui.animation;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpecInterpolator;", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", "from", TypedValues.TransitionType.S_TO, "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;)V", "", "value", "times", "(Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;F)Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", Request.JsonKeys.OTHER, "plus", "(Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;)Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", "input", "getInterpolation", "(F)Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", "getFrom", "()Lcom/scandit/datacapture/core/internal/sdk/ui/animation/AnimationSpec;", "b", "getTo", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class AnimationSpecInterpolator {

    /* renamed from: a, reason: from kotlin metadata */
    private final AnimationSpec from;

    /* renamed from: b, reason: from kotlin metadata */
    private final AnimationSpec to;

    public AnimationSpecInterpolator(AnimationSpec from, AnimationSpec to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        this.from = from;
        this.to = to;
    }

    public final AnimationSpec getFrom() {
        return this.from;
    }

    public final AnimationSpec getInterpolation(float input) {
        return plus(times(this.from, 1.0f - input), times(this.to, input));
    }

    public final AnimationSpec getTo() {
        return this.to;
    }

    public final AnimationSpec plus(AnimationSpec animationSpec, AnimationSpec other) {
        Intrinsics.checkNotNullParameter(animationSpec, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return new AnimationSpec(other.getAlpha() + animationSpec.getAlpha(), other.getElevation() + animationSpec.getElevation(), other.getScale() + animationSpec.getScale(), other.getTranslationY() + animationSpec.getTranslationY());
    }

    public final AnimationSpec times(AnimationSpec animationSpec, float f) {
        Intrinsics.checkNotNullParameter(animationSpec, "<this>");
        return new AnimationSpec(animationSpec.getAlpha() * f, animationSpec.getElevation() * f, animationSpec.getScale() * f, animationSpec.getTranslationY() * f);
    }
}
