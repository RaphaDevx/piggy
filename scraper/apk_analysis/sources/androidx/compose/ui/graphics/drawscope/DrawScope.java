package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.config.a;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DrawScope.kt */
@DrawScopeMarker
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u0000 h2\u00020\u0001:\u0001hJs\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#Js\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J[\u0010(\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00162\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010+J[\u0010(\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00162\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-JQ\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u0002002\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102Jo\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u0002002\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002042\b\b\u0002\u00108\u001a\u0002062\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b9\u0010:Jm\u0010;\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00162\b\b\u0002\u0010?\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010DJm\u0010;\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00162\b\b\u0002\u0010?\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u0010FJ[\u0010G\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bH\u0010IJ[\u0010G\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u0010KJO\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020N2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010PJO\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020N2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bQ\u0010RJs\u0010S\u001a\u00020\u00122\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00030U2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010>\u001a\u00020\u00162\b\b\u0002\u0010?\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bX\u0010YJs\u0010S\u001a\u00020\u00122\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00030U2\u0006\u0010V\u001a\u00020W2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010>\u001a\u00020\u00162\b\b\u0002\u0010?\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bZ\u0010[J[\u0010\\\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b]\u0010IJ[\u0010\\\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b^\u0010KJe\u0010_\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010`\u001a\u00020a2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u0010cJe\u0010_\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010`\u001a\u00020a2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001b\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bd\u0010eJ\u001a\u0010f\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010g\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001R\u001d\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006i"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Density;", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "drawArc", "", "brush", "Landroidx/compose/ui/graphics/Brush;", "startAngle", "", "sweepAngle", "useCenter", "", "topLeft", "alpha", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "radius", "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine", "start", "end", "strokeWidth", "cap", "Landroidx/compose/ui/graphics/StrokeCap;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", a.j, "Landroidx/compose/ui/graphics/Path;", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "points", "", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "drawRect-AsUm42w", "drawRect-n-J9OG0", "drawRoundRect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "offsetSize", TypedValues.CycleType.S_WAVE_OFFSET, "Companion", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface DrawScope extends Density {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* renamed from: drawArc-illE91I */
    void mo1608drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawArc-yD3GUKo */
    void mo1609drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawCircle-V9BoPsw */
    void mo1610drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawCircle-VaOC9Bg */
    void mo1611drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawImage-9jGpkUE */
    void mo1612drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawImage-gbVJVH8 */
    void mo1613drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawLine-1RTmtNc */
    void mo1614drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawLine-NGM6Ib0 */
    void mo1615drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawOval-AsUm42w */
    void mo1616drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawOval-n-J9OG0 */
    void mo1617drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawPath-GBMwjPU */
    void mo1618drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawPath-LG529CI */
    void mo1619drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawPoints-F8ZwMP8 */
    void mo1620drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawPoints-Gsft0Ws */
    void mo1621drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawRect-AsUm42w */
    void mo1622drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawRect-n-J9OG0 */
    void mo1623drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawRoundRect-ZuiqVtQ */
    void mo1624drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* renamed from: drawRoundRect-u-Aw5IA */
    void mo1625drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode);

    /* renamed from: getCenter-F1C5BW0 */
    long mo1626getCenterF1C5BW0();

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* renamed from: getSize-NH-jbRc */
    long mo1627getSizeNHjbRc();

    /* compiled from: DrawScope.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m1676roundToPxR2X_6o(DrawScope drawScope, long j) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m2995roundToPxR2X_6o(drawScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m1677roundToPx0680j_4(DrawScope drawScope, float f) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m2996roundToPx0680j_4(drawScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m1678toDpGaN1DYA(DrawScope drawScope, long j) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m2997toDpGaN1DYA(drawScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1679toDpu2uoSUM(DrawScope drawScope, float f) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m2998toDpu2uoSUM(drawScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m1680toDpu2uoSUM(DrawScope drawScope, int i) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m2999toDpu2uoSUM((Density) drawScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m1681toPxR2X_6o(DrawScope drawScope, long j) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m3000toPxR2X_6o(drawScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m1682toPx0680j_4(DrawScope drawScope, float f) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m3001toPx0680j_4(drawScope, f);
        }

        public static Rect toRect(DrawScope drawScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return Density.DefaultImpls.toRect(drawScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m1683toSp0xMU5do(DrawScope drawScope, float f) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m3002toSp0xMU5do(drawScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1684toSpkPz2Gy4(DrawScope drawScope, float f) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m3003toSpkPz2Gy4(drawScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m1685toSpkPz2Gy4(DrawScope drawScope, int i) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return Density.DefaultImpls.m3004toSpkPz2Gy4((Density) drawScope, i);
        }

        /* renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m1673getCenterF1C5BW0(DrawScope drawScope) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return SizeKt.m1104getCenteruvyYCjk(drawScope.getDrawContext().mo1633getSizeNHjbRc());
        }

        /* renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m1674getSizeNHjbRc(DrawScope drawScope) {
            Intrinsics.checkNotNullParameter(drawScope, "this");
            return drawScope.getDrawContext().mo1633getSizeNHjbRc();
        }

        /* renamed from: drawLine-1RTmtNc$default, reason: not valid java name */
        public static /* synthetic */ void m1661drawLine1RTmtNc$default(DrawScope drawScope, Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-1RTmtNc");
            }
            drawScope.mo1614drawLine1RTmtNc(brush, j, j2, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m1709getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i2);
        }

        /* renamed from: drawLine-NGM6Ib0$default, reason: not valid java name */
        public static /* synthetic */ void m1662drawLineNGM6Ib0$default(DrawScope drawScope, long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-NGM6Ib0");
            }
            drawScope.mo1615drawLineNGM6Ib0(j, j2, j3, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m1709getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i2);
        }

        /* renamed from: drawRect-AsUm42w$default, reason: not valid java name */
        public static /* synthetic */ void m1669drawRectAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-AsUm42w");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j;
            drawScope.mo1622drawRectAsUm42w(brush, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawRect-n-J9OG0$default, reason: not valid java name */
        public static /* synthetic */ void m1670drawRectnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-n-J9OG0");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j2;
            drawScope.mo1623drawRectnJ9OG0(j, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawImage-gbVJVH8$default, reason: not valid java name */
        public static /* synthetic */ void m1660drawImagegbVJVH8$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-gbVJVH8");
            }
            drawScope.mo1613drawImagegbVJVH8(imageBitmap, (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawImage-9jGpkUE$default, reason: not valid java name */
        public static /* synthetic */ void m1659drawImage9jGpkUE$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-9jGpkUE");
            }
            long m3095getZeronOccac = (i2 & 2) != 0 ? IntOffset.INSTANCE.m3095getZeronOccac() : j;
            long IntSize = (i2 & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j2;
            drawScope.mo1612drawImage9jGpkUE(imageBitmap, m3095getZeronOccac, IntSize, (i2 & 8) != 0 ? IntOffset.INSTANCE.m3095getZeronOccac() : j3, (i2 & 16) != 0 ? IntSize : j4, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 128) != 0 ? null : colorFilter, (i2 & 256) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
        public static /* synthetic */ void m1671drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-ZuiqVtQ");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j;
            drawScope.mo1624drawRoundRectZuiqVtQ(brush, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m1010getZerokKHJgLs() : j3, (i2 & 16) != 0 ? 1.0f : f, (i2 & 32) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawRoundRect-u-Aw5IA$default, reason: not valid java name */
        public static /* synthetic */ void m1672drawRoundRectuAw5IA$default(DrawScope drawScope, long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-u-Aw5IA");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j2;
            drawScope.mo1625drawRoundRectuAw5IA(j, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m1010getZerokKHJgLs() : j4, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawCircle-V9BoPsw$default, reason: not valid java name */
        public static /* synthetic */ void m1657drawCircleV9BoPsw$default(DrawScope drawScope, Brush brush, float f, long j, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-V9BoPsw");
            }
            drawScope.mo1610drawCircleV9BoPsw(brush, (i2 & 2) != 0 ? Size.m1093getMinDimensionimpl(drawScope.mo1627getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo1626getCenterF1C5BW0() : j, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawCircle-VaOC9Bg$default, reason: not valid java name */
        public static /* synthetic */ void m1658drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-VaOC9Bg");
            }
            drawScope.mo1611drawCircleVaOC9Bg(j, (i2 & 2) != 0 ? Size.m1093getMinDimensionimpl(drawScope.mo1627getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo1626getCenterF1C5BW0() : j2, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawOval-AsUm42w$default, reason: not valid java name */
        public static /* synthetic */ void m1663drawOvalAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-AsUm42w");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j;
            drawScope.mo1616drawOvalAsUm42w(brush, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawOval-n-J9OG0$default, reason: not valid java name */
        public static /* synthetic */ void m1664drawOvalnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-n-J9OG0");
            }
            long m1041getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j2;
            drawScope.mo1617drawOvalnJ9OG0(j, m1041getZeroF1C5BW0, (i2 & 4) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawArc-illE91I$default, reason: not valid java name */
        public static /* synthetic */ void m1655drawArcillE91I$default(DrawScope drawScope, Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-illE91I");
            }
            long m1041getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j;
            drawScope.mo1608drawArcillE91I(brush, f, f2, z, m1041getZeroF1C5BW0, (i2 & 32) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j2, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawArc-yD3GUKo$default, reason: not valid java name */
        public static /* synthetic */ void m1656drawArcyD3GUKo$default(DrawScope drawScope, long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-yD3GUKo");
            }
            long m1041getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m1041getZeroF1C5BW0() : j2;
            drawScope.mo1609drawArcyD3GUKo(j, f, f2, z, m1041getZeroF1C5BW0, (i2 & 32) != 0 ? m1675offsetSizePENXr5M(drawScope, drawScope.mo1627getSizeNHjbRc(), m1041getZeroF1C5BW0) : j3, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawPath-LG529CI$default, reason: not valid java name */
        public static /* synthetic */ void m1666drawPathLG529CI$default(DrawScope drawScope, Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-LG529CI");
            }
            drawScope.mo1619drawPathLG529CI(path, j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i);
        }

        /* renamed from: drawPath-GBMwjPU$default, reason: not valid java name */
        public static /* synthetic */ void m1665drawPathGBMwjPU$default(DrawScope drawScope, Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-GBMwjPU");
            }
            if ((i2 & 4) != 0) {
                f = 1.0f;
            }
            float f2 = f;
            if ((i2 & 8) != 0) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            if ((i2 & 16) != 0) {
                colorFilter = null;
            }
            ColorFilter colorFilter2 = colorFilter;
            if ((i2 & 32) != 0) {
                i = DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU();
            }
            drawScope.mo1618drawPathGBMwjPU(path, brush, f2, drawStyle2, colorFilter2, i);
        }

        /* renamed from: drawPoints-F8ZwMP8$default, reason: not valid java name */
        public static /* synthetic */ void m1667drawPointsF8ZwMP8$default(DrawScope drawScope, List list, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-F8ZwMP8");
            }
            drawScope.mo1620drawPointsF8ZwMP8(list, i, j, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m1523getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i3);
        }

        /* renamed from: drawPoints-Gsft0Ws$default, reason: not valid java name */
        public static /* synthetic */ void m1668drawPointsGsft0Ws$default(DrawScope drawScope, List list, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-Gsft0Ws");
            }
            drawScope.mo1621drawPointsGsft0Ws(list, i, brush, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m1523getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? DrawScope.INSTANCE.m1654getDefaultBlendMode0nO6VwU() : i3);
        }

        /* renamed from: offsetSize-PENXr5M, reason: not valid java name */
        private static long m1675offsetSizePENXr5M(DrawScope drawScope, long j, long j2) {
            return SizeKt.Size(Size.m1094getWidthimpl(j) - Offset.m1025getXimpl(j2), Size.m1091getHeightimpl(j) - Offset.m1026getYimpl(j2));
        }
    }

    /* compiled from: DrawScope.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope$Companion;", "", "()V", "DefaultBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultBlendMode-0nO6VwU", "()I", "I", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int DefaultBlendMode = BlendMode.INSTANCE.m1205getSrcOver0nO6VwU();

        private Companion() {
        }

        /* renamed from: getDefaultBlendMode-0nO6VwU, reason: not valid java name */
        public final int m1654getDefaultBlendMode0nO6VwU() {
            return DefaultBlendMode;
        }
    }
}
