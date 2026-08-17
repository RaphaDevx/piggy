package androidx.compose.foundation.layout;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/foundation/layout/Direction;", "", "(Ljava/lang/String;I)V", "Vertical", "Horizontal", "Both", "foundation-layout_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public enum Direction {
    Vertical,
    Horizontal,
    Both;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static Direction[] valuesCustom() {
        Direction[] valuesCustom = values();
        return (Direction[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
    }
}
