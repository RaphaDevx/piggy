package androidx.compose.animation;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;

/* compiled from: EnterExitTransition.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/animation/AnimStates;", "", "(Ljava/lang/String;I)V", "Entering", "Visible", "Exiting", "Gone", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public enum AnimStates {
    Entering,
    Visible,
    Exiting,
    Gone;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static AnimStates[] valuesCustom() {
        AnimStates[] valuesCustom = values();
        return (AnimStates[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
    }
}
