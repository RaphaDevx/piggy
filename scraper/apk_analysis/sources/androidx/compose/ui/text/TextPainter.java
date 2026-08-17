package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextPainter.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/text/TextPainter;", "", "()V", "paint", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextPainter {
    public static final TextPainter INSTANCE = new TextPainter();

    private TextPainter() {
    }

    public final void paint(Canvas canvas, TextLayoutResult textLayoutResult) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        boolean z = textLayoutResult.getHasVisualOverflow() && TextOverflow.m2958equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m2962getClipgIe3tQ8());
        if (z) {
            Rect m1065Recttz77jQw = RectKt.m1065Recttz77jQw(Offset.INSTANCE.m1041getZeroF1C5BW0(), SizeKt.Size(IntSize.m3127getWidthimpl(textLayoutResult.getSize()), IntSize.m3126getHeightimpl(textLayoutResult.getSize())));
            canvas.save();
            Canvas.DefaultImpls.m1233clipRectmtrdDE$default(canvas, m1065Recttz77jQw, 0, 2, null);
        }
        try {
            textLayoutResult.getMultiParagraph().m2692paintRPmYEkk(canvas, textLayoutResult.getLayoutInput().getStyle().getColor(), textLayoutResult.getLayoutInput().getStyle().getShadow(), textLayoutResult.getLayoutInput().getStyle().getTextDecoration());
        } finally {
            if (z) {
                canvas.restore();
            }
        }
    }
}
