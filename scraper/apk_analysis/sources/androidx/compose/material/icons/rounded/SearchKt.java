package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Search.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_search", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Search", "Landroidx/compose/material/icons/Icons$Rounded;", "getSearch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SearchKt {
    private static ImageVector _search;

    public static final ImageVector getSearch(Icons.Rounded rounded) {
        ImageVector.Builder m1722addPathoIyEayM;
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _search;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Search", Dp.m3007constructorimpl(24.0f), Dp.m3007constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1280getBlack0d7_KjU(), null);
        int m1523getButtKaPHkGw = StrokeCap.INSTANCE.m1523getButtKaPHkGw();
        int m1533getBevelLxFBmk8 = StrokeJoin.INSTANCE.m1533getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.29f, 18.88f);
        pathBuilder.lineToRelative(-5.56f, -5.56f);
        pathBuilder.curveToRelative(1.13f, -1.55f, 1.63f, -3.58f, 0.98f, -5.74f);
        pathBuilder.curveToRelative(-0.68f, -2.23f, -2.57f, -3.98f, -4.85f, -4.44f);
        pathBuilder.curveTo(6.21f, 2.2f, 2.2f, 6.22f, 3.14f, 10.86f);
        pathBuilder.curveToRelative(0.46f, 2.29f, 2.21f, 4.18f, 4.44f, 4.85f);
        pathBuilder.curveToRelative(2.16f, 0.65f, 4.19f, 0.15f, 5.74f, -0.98f);
        pathBuilder.lineToRelative(5.56f, 5.56f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveTo(20.68f, 19.9f, 20.68f, 19.27f, 20.29f, 18.88f);
        pathBuilder.close();
        pathBuilder.moveTo(5.0f, 9.5f);
        pathBuilder.curveTo(5.0f, 7.01f, 7.01f, 5.0f, 9.5f, 5.0f);
        pathBuilder.reflectiveCurveTo(14.0f, 7.01f, 14.0f, 9.5f);
        pathBuilder.reflectiveCurveTo(11.99f, 14.0f, 9.5f, 14.0f);
        pathBuilder.reflectiveCurveTo(5.0f, 11.99f, 5.0f, 9.5f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        m1722addPathoIyEayM = builder.m1722addPathoIyEayM(pathBuilder.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : defaultFillType, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : solidColor, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : m1523getButtKaPHkGw, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : m1533getBevelLxFBmk8, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        ImageVector build = m1722addPathoIyEayM.build();
        _search = build;
        Intrinsics.checkNotNull(build);
        return build;
    }
}
