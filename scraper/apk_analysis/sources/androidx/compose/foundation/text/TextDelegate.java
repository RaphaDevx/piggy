package androidx.compose.foundation.text;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TextDelegate.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 E2\u00020\u0001:\u0001EB\\\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011ø\u0001\u0000¢\u0006\u0002\u0010\u0014J/\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00182\n\b\u0002\u0010<\u001a\u0004\u0018\u000108ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010>J\u000e\u0010?\u001a\u00020@2\u0006\u0010;\u001a\u00020\u0018J%\u0010A\u001a\u00020B2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0018H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010DR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010!\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001fR\u0014\u0010#\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001c\u0010\n\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010(\u001a\u0004\b'\u0010\u001fR\u001c\u0010)\u001a\u0004\u0018\u00010$X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010&\"\u0004\b+\u0010,R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u00106\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006F"}, d2 = {"Landroidx/compose/foundation/text/TextDelegate;", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "maxLines", "", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;IZILandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDensity", "()Landroidx/compose/ui/unit/Density;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getIntrinsicsLayoutDirection$foundation_release", "()Landroidx/compose/ui/unit/LayoutDirection;", "setIntrinsicsLayoutDirection$foundation_release", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "()I", "getMaxLines", "minIntrinsicWidth", "getMinIntrinsicWidth", "nonNullIntrinsics", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "getNonNullIntrinsics", "()Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "getOverflow-gIe3tQ8", "I", "paragraphIntrinsics", "getParagraphIntrinsics$foundation_release", "setParagraphIntrinsics$foundation_release", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;)V", "getPlaceholders", "()Ljava/util/List;", "getResourceLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getSoftWrap", "()Z", "getStyle", "()Landroidx/compose/ui/text/TextStyle;", "getText", "()Landroidx/compose/ui/text/AnnotatedString;", "layout", "Landroidx/compose/ui/text/TextLayoutResult;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutDirection", "prevResult", "layout-NN6Ew-U", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextLayoutResult;)Landroidx/compose/ui/text/TextLayoutResult;", "layoutIntrinsics", "", "layoutText", "Landroidx/compose/ui/text/MultiParagraph;", "layoutText-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/MultiParagraph;", "Companion", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@InternalFoundationTextApi
/* loaded from: classes.dex */
public final class TextDelegate {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Density density;
    private LayoutDirection intrinsicsLayoutDirection;
    private final int maxLines;
    private final int overflow;
    private MultiParagraphIntrinsics paragraphIntrinsics;
    private final List<AnnotatedString.Range<Placeholder>> placeholders;
    private final Font.ResourceLoader resourceLoader;
    private final boolean softWrap;
    private final TextStyle style;
    private final AnnotatedString text;

    public /* synthetic */ TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, boolean z, int i2, Density density, Font.ResourceLoader resourceLoader, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, i, z, i2, density, resourceLoader, list);
    }

    private TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, boolean z, int i2, Density density, Font.ResourceLoader resourceLoader, List<AnnotatedString.Range<Placeholder>> list) {
        this.text = annotatedString;
        this.style = textStyle;
        this.maxLines = i;
        this.softWrap = z;
        this.overflow = i2;
        this.density = density;
        this.resourceLoader = resourceLoader;
        this.placeholders = list;
        if (!(i > 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public final AnnotatedString getText() {
        return this.text;
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    public final boolean getSoftWrap() {
        return this.softWrap;
    }

    public /* synthetic */ TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, boolean z, int i2, Density density, Font.ResourceLoader resourceLoader, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, (i3 & 4) != 0 ? Integer.MAX_VALUE : i, (i3 & 8) != 0 ? true : z, (i3 & 16) != 0 ? TextOverflow.INSTANCE.m2962getClipgIe3tQ8() : i2, density, resourceLoader, (i3 & 128) != 0 ? CollectionsKt.emptyList() : list, null);
    }

    /* renamed from: getOverflow-gIe3tQ8, reason: not valid java name and from getter */
    public final int getOverflow() {
        return this.overflow;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final Font.ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public final List<AnnotatedString.Range<Placeholder>> getPlaceholders() {
        return this.placeholders;
    }

    /* renamed from: getParagraphIntrinsics$foundation_release, reason: from getter */
    public final MultiParagraphIntrinsics getParagraphIntrinsics() {
        return this.paragraphIntrinsics;
    }

    public final void setParagraphIntrinsics$foundation_release(MultiParagraphIntrinsics multiParagraphIntrinsics) {
        this.paragraphIntrinsics = multiParagraphIntrinsics;
    }

    /* renamed from: getIntrinsicsLayoutDirection$foundation_release, reason: from getter */
    public final LayoutDirection getIntrinsicsLayoutDirection() {
        return this.intrinsicsLayoutDirection;
    }

    public final void setIntrinsicsLayoutDirection$foundation_release(LayoutDirection layoutDirection) {
        this.intrinsicsLayoutDirection = layoutDirection;
    }

    private final MultiParagraphIntrinsics getNonNullIntrinsics() {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.paragraphIntrinsics;
        if (multiParagraphIntrinsics != null) {
            return multiParagraphIntrinsics;
        }
        throw new IllegalStateException("layoutForIntrinsics must be called first");
    }

    public final int getMinIntrinsicWidth() {
        return (int) Math.ceil(getNonNullIntrinsics().getMinIntrinsicWidth());
    }

    public final int getMaxIntrinsicWidth() {
        return (int) Math.ceil(getNonNullIntrinsics().getMaxIntrinsicWidth());
    }

    public final void layoutIntrinsics(LayoutDirection layoutDirection) {
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.paragraphIntrinsics;
        if (multiParagraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection) {
            this.intrinsicsLayoutDirection = layoutDirection;
            multiParagraphIntrinsics = new MultiParagraphIntrinsics(this.text, TextStyleKt.resolveDefaults(this.style, layoutDirection), this.placeholders, this.density, this.resourceLoader);
        }
        this.paragraphIntrinsics = multiParagraphIntrinsics;
    }

    /* renamed from: layoutText-K40F9xA, reason: not valid java name */
    private final MultiParagraph m502layoutTextK40F9xA(long constraints, LayoutDirection layoutDirection) {
        layoutIntrinsics(layoutDirection);
        float m2979getMinWidthimpl = Constraints.m2979getMinWidthimpl(constraints);
        float m2977getMaxWidthimpl = ((this.softWrap || TextOverflow.m2958equalsimpl0(getOverflow(), TextOverflow.INSTANCE.m2963getEllipsisgIe3tQ8())) && Constraints.m2973getHasBoundedWidthimpl(constraints)) ? Constraints.m2977getMaxWidthimpl(constraints) : Float.POSITIVE_INFINITY;
        int i = (this.softWrap || !TextOverflow.m2958equalsimpl0(getOverflow(), TextOverflow.INSTANCE.m2963getEllipsisgIe3tQ8())) ? this.maxLines : 1;
        if (m2979getMinWidthimpl != m2977getMaxWidthimpl) {
            m2977getMaxWidthimpl = RangesKt.coerceIn(getNonNullIntrinsics().getMaxIntrinsicWidth(), m2979getMinWidthimpl, m2977getMaxWidthimpl);
        }
        return new MultiParagraph(getNonNullIntrinsics(), i, TextOverflow.m2958equalsimpl0(getOverflow(), TextOverflow.INSTANCE.m2963getEllipsisgIe3tQ8()), m2977getMaxWidthimpl);
    }

    /* renamed from: layout-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ TextLayoutResult m501layoutNN6EwU$default(TextDelegate textDelegate, long j, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult, int i, Object obj) {
        if ((i & 4) != 0) {
            textLayoutResult = null;
        }
        return textDelegate.m504layoutNN6EwU(j, layoutDirection, textLayoutResult);
    }

    /* renamed from: layout-NN6Ew-U, reason: not valid java name */
    public final TextLayoutResult m504layoutNN6EwU(long constraints, LayoutDirection layoutDirection, TextLayoutResult prevResult) {
        TextLayoutInput m2744copyhu1Yfo;
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        if (prevResult != null && TextLayoutHelperKt.m524canReuse7_7YC6M(prevResult, this.text, this.style, this.placeholders, this.maxLines, this.softWrap, getOverflow(), this.density, layoutDirection, this.resourceLoader, constraints)) {
            m2744copyhu1Yfo = r1.m2744copyhu1Yfo((r25 & 1) != 0 ? r1.text : null, (r25 & 2) != 0 ? r1.style : getStyle(), (r25 & 4) != 0 ? r1.placeholders : null, (r25 & 8) != 0 ? r1.maxLines : 0, (r25 & 16) != 0 ? r1.softWrap : false, (r25 & 32) != 0 ? r1.getOverflow() : 0, (r25 & 64) != 0 ? r1.density : null, (r25 & 128) != 0 ? r1.layoutDirection : null, (r25 & 256) != 0 ? r1.resourceLoader : null, (r25 & 512) != 0 ? prevResult.getLayoutInput().getConstraints() : constraints);
            return prevResult.m2748copyO0kMr_c(m2744copyhu1Yfo, ConstraintsKt.m2988constrain4WqzIAM(constraints, IntSizeKt.IntSize((int) Math.ceil(prevResult.getMultiParagraph().getWidth()), (int) Math.ceil(prevResult.getMultiParagraph().getHeight()))));
        }
        return new TextLayoutResult(new TextLayoutInput(this.text, this.style, this.placeholders, this.maxLines, this.softWrap, getOverflow(), this.density, layoutDirection, this.resourceLoader, constraints, null), m502layoutTextK40F9xA(constraints, layoutDirection), ConstraintsKt.m2988constrain4WqzIAM(constraints, IntSizeKt.IntSize((int) Math.ceil(r13.getWidth()), (int) Math.ceil(r13.getHeight()))), null);
    }

    /* compiled from: TextDelegate.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/TextDelegate$Companion;", "", "()V", "paint", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void paint(Canvas canvas, TextLayoutResult textLayoutResult) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
            TextPainter.INSTANCE.paint(canvas, textLayoutResult);
        }
    }
}
