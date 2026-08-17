package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: TextSelectionDelegate.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
/* synthetic */ class TextSelectionDelegateKt$adjustSelection$boundaryFun$1 extends FunctionReferenceImpl implements Function1<Integer, TextRange> {
    TextSelectionDelegateKt$adjustSelection$boundaryFun$1(TextLayoutResult textLayoutResult) {
        super(1, textLayoutResult, TextLayoutResult.class, "getWordBoundary", "getWordBoundary--jx7JFs(I)J", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ TextRange invoke(Integer num) {
        return TextRange.m2754boximpl(m591invokejx7JFsjx7JFs(num.intValue()));
    }

    /* renamed from: invoke--jx7JFs--jx7JFs, reason: not valid java name */
    public final long m591invokejx7JFsjx7JFs(int i) {
        return ((TextLayoutResult) this.receiver).m2751getWordBoundaryjx7JFs(i);
    }
}
