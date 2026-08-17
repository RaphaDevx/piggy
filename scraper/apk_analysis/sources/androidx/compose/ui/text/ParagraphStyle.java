package androidx.compose.ui.text;

import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ParagraphStyle.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B6\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tø\u0001\u0000¢\u0006\u0002\u0010\nJA\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0000H\u0007J\u0011\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0087\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u001c\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/ui/text/ParagraphStyle;", "", "textAlign", "Landroidx/compose/ui/text/style/TextAlign;", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "lineHeight", "Landroidx/compose/ui/unit/TextUnit;", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getLineHeight-XSAIIZE", "()J", "J", "getTextAlign-buA522U", "()Landroidx/compose/ui/text/style/TextAlign;", "getTextDirection-mmuk1to", "()Landroidx/compose/ui/text/style/TextDirection;", "getTextIndent", "()Landroidx/compose/ui/text/style/TextIndent;", "copy", "copy-Elsmlbk", "(Landroidx/compose/ui/text/style/TextAlign;Landroidx/compose/ui/text/style/TextDirection;JLandroidx/compose/ui/text/style/TextIndent;)Landroidx/compose/ui/text/ParagraphStyle;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "merge", "plus", "toString", "", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ParagraphStyle {
    private final long lineHeight;
    private final TextAlign textAlign;
    private final TextDirection textDirection;
    private final TextIndent textIndent;

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, DefaultConstructorMarker defaultConstructorMarker) {
        this(textAlign, textDirection, j, textIndent);
    }

    private ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent) {
        this.textAlign = textAlign;
        this.textDirection = textDirection;
        this.lineHeight = j;
        this.textIndent = textIndent;
        if (TextUnit.m3145equalsimpl0(getLineHeight(), TextUnit.INSTANCE.m3159getUnspecifiedXSAIIZE())) {
            return;
        }
        if (TextUnit.m3148getValueimpl(getLineHeight()) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + TextUnit.m3148getValueimpl(getLineHeight()) + ')').toString());
    }

    /* renamed from: getTextAlign-buA522U, reason: not valid java name and from getter */
    public final TextAlign getTextAlign() {
        return this.textAlign;
    }

    /* renamed from: getTextDirection-mmuk1to, reason: not valid java name and from getter */
    public final TextDirection getTextDirection() {
        return this.textDirection;
    }

    public /* synthetic */ ParagraphStyle(TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : textAlign, (i & 2) != 0 ? null : textDirection, (i & 4) != 0 ? TextUnit.INSTANCE.m3159getUnspecifiedXSAIIZE() : j, (i & 8) != 0 ? null : textIndent, null);
    }

    /* renamed from: getLineHeight-XSAIIZE, reason: not valid java name and from getter */
    public final long getLineHeight() {
        return this.lineHeight;
    }

    public final TextIndent getTextIndent() {
        return this.textIndent;
    }

    public static /* synthetic */ ParagraphStyle merge$default(ParagraphStyle paragraphStyle, ParagraphStyle paragraphStyle2, int i, Object obj) {
        if ((i & 1) != 0) {
            paragraphStyle2 = null;
        }
        return paragraphStyle.merge(paragraphStyle2);
    }

    public final ParagraphStyle merge(ParagraphStyle other) {
        long lineHeight;
        if (other == null) {
            return this;
        }
        if (TextUnitKt.m3166isUnspecifiedR2X_6o(other.getLineHeight())) {
            lineHeight = getLineHeight();
        } else {
            lineHeight = other.getLineHeight();
        }
        long j = lineHeight;
        TextIndent textIndent = other.textIndent;
        if (textIndent == null) {
            textIndent = this.textIndent;
        }
        TextIndent textIndent2 = textIndent;
        TextAlign textAlign = other.getTextAlign();
        if (textAlign == null) {
            textAlign = getTextAlign();
        }
        TextAlign textAlign2 = textAlign;
        TextDirection textDirection = other.getTextDirection();
        return new ParagraphStyle(textAlign2, textDirection == null ? getTextDirection() : textDirection, j, textIndent2, null);
    }

    public final ParagraphStyle plus(ParagraphStyle other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return merge(other);
    }

    /* renamed from: copy-Elsmlbk$default, reason: not valid java name */
    public static /* synthetic */ ParagraphStyle m2699copyElsmlbk$default(ParagraphStyle paragraphStyle, TextAlign textAlign, TextDirection textDirection, long j, TextIndent textIndent, int i, Object obj) {
        if ((i & 1) != 0) {
            textAlign = paragraphStyle.getTextAlign();
        }
        if ((i & 2) != 0) {
            textDirection = paragraphStyle.getTextDirection();
        }
        TextDirection textDirection2 = textDirection;
        if ((i & 4) != 0) {
            j = paragraphStyle.getLineHeight();
        }
        long j2 = j;
        if ((i & 8) != 0) {
            textIndent = paragraphStyle.textIndent;
        }
        return paragraphStyle.m2700copyElsmlbk(textAlign, textDirection2, j2, textIndent);
    }

    /* renamed from: copy-Elsmlbk, reason: not valid java name */
    public final ParagraphStyle m2700copyElsmlbk(TextAlign textAlign, TextDirection textDirection, long lineHeight, TextIndent textIndent) {
        return new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParagraphStyle)) {
            return false;
        }
        ParagraphStyle paragraphStyle = (ParagraphStyle) other;
        return Intrinsics.areEqual(getTextAlign(), paragraphStyle.getTextAlign()) && Intrinsics.areEqual(getTextDirection(), paragraphStyle.getTextDirection()) && TextUnit.m3145equalsimpl0(getLineHeight(), paragraphStyle.getLineHeight()) && Intrinsics.areEqual(this.textIndent, paragraphStyle.textIndent);
    }

    public int hashCode() {
        TextAlign textAlign = getTextAlign();
        int m2930hashCodeimpl = (textAlign == null ? 0 : TextAlign.m2930hashCodeimpl(textAlign.getValue())) * 31;
        TextDirection textDirection = getTextDirection();
        int m2943hashCodeimpl = (((m2930hashCodeimpl + (textDirection == null ? 0 : TextDirection.m2943hashCodeimpl(textDirection.getValue()))) * 31) + TextUnit.m3149hashCodeimpl(getLineHeight())) * 31;
        TextIndent textIndent = this.textIndent;
        return m2943hashCodeimpl + (textIndent != null ? textIndent.hashCode() : 0);
    }

    public String toString() {
        return "ParagraphStyle(textAlign=" + getTextAlign() + ", textDirection=" + getTextDirection() + ", lineHeight=" + ((Object) TextUnit.m3155toStringimpl(getLineHeight())) + ", textIndent=" + this.textIndent + ')';
    }
}
