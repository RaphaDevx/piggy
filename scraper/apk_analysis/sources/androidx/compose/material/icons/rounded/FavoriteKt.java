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

/* compiled from: Favorite.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_favorite", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Favorite", "Landroidx/compose/material/icons/Icons$Rounded;", "getFavorite", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FavoriteKt {
    private static ImageVector _favorite;

    public static final ImageVector getFavorite(Icons.Rounded rounded) {
        ImageVector.Builder m1722addPathoIyEayM;
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _favorite;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Favorite", Dp.m3007constructorimpl(24.0f), Dp.m3007constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1280getBlack0d7_KjU(), null);
        int m1523getButtKaPHkGw = StrokeCap.INSTANCE.m1523getButtKaPHkGw();
        int m1533getBevelLxFBmk8 = StrokeJoin.INSTANCE.m1533getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(10.67f, 19.8f);
        pathBuilder.curveTo(5.15f, 14.85f, 1.95f, 12.16f, 2.0f, 8.41f);
        pathBuilder.curveToRelative(0.04f, -2.97f, 2.3f, -4.39f, 2.35f, -4.43f);
        pathBuilder.curveTo(7.96f, 1.52f, 11.24f, 4.2f, 12.0f, 5.09f);
        pathBuilder.curveToRelative(0.75f, -0.88f, 3.99f, -3.51f, 7.56f, -1.16f);
        pathBuilder.curveToRelative(0.52f, 0.34f, 2.23f, 1.65f, 2.42f, 4.12f);
        pathBuilder.curveToRelative(0.32f, 4.28f, -4.14f, 7.76f, -8.65f, 11.76f);
        pathBuilder.curveToRelative(-0.38f, 0.34f, -0.86f, 0.5f, -1.34f, 0.5f);
        pathBuilder.curveTo(11.52f, 20.31f, 11.05f, 20.14f, 10.67f, 19.8f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        m1722addPathoIyEayM = builder.m1722addPathoIyEayM(pathBuilder.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : defaultFillType, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : solidColor, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : m1523getButtKaPHkGw, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : m1533getBevelLxFBmk8, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        ImageVector build = m1722addPathoIyEayM.build();
        _favorite = build;
        Intrinsics.checkNotNull(build);
        return build;
    }
}
