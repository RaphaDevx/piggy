package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasicTextField.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ComposableSingletons$BasicTextFieldKt {
    public static final ComposableSingletons$BasicTextFieldKt INSTANCE = new ComposableSingletons$BasicTextFieldKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> f0lambda1 = ComposableLambdaKt.composableLambdaInstance(-985535355, false, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
            invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
            ComposerKt.sourceInformation(composer, "C131@7642L16:BasicTextField.kt#423gt5");
            if ((i & 14) == 0) {
                i |= composer.changed(innerTextField) ? 4 : 2;
            }
            if (((i & 91) ^ 18) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            } else {
                innerTextField.invoke(composer, Integer.valueOf(i & 14));
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> f1lambda2 = ComposableLambdaKt.composableLambdaInstance(-985544588, false, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
            invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2<? super Composer, ? super Integer, Unit> innerTextField, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(innerTextField, "innerTextField");
            ComposerKt.sourceInformation(composer, "C254@14667L16:BasicTextField.kt#423gt5");
            if ((i & 14) == 0) {
                i |= composer.changed(innerTextField) ? 4 : 2;
            }
            if (((i & 91) ^ 18) == 0 && composer.getSkipping()) {
                composer.skipToGroupEnd();
            } else {
                innerTextField.invoke(composer, Integer.valueOf(i & 14));
            }
        }
    });

    /* renamed from: getLambda-1$foundation_release, reason: not valid java name */
    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> m444getLambda1$foundation_release() {
        return f0lambda1;
    }

    /* renamed from: getLambda-2$foundation_release, reason: not valid java name */
    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> m445getLambda2$foundation_release() {
        return f1lambda2;
    }
}
