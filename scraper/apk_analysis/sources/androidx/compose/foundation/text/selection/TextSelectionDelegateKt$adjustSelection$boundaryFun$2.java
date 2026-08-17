package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.ui.text.TextRange;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: TextSelectionDelegate.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
/* synthetic */ class TextSelectionDelegateKt$adjustSelection$boundaryFun$2 extends FunctionReferenceImpl implements Function1<Integer, TextRange> {
    TextSelectionDelegateKt$adjustSelection$boundaryFun$2(String str) {
        super(1, str, StringHelpersKt.class, "getParagraphBoundary", "getParagraphBoundary(Ljava/lang/String;I)J", 1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ TextRange invoke(Integer num) {
        return TextRange.m2754boximpl(m592invokefzxv0v0jx7JFs(num.intValue()));
    }

    /* renamed from: invoke-fzxv0v0--jx7JFs, reason: not valid java name */
    public final long m592invokefzxv0v0jx7JFs(int i) {
        return StringHelpersKt.getParagraphBoundary((String) this.receiver, i);
    }
}
