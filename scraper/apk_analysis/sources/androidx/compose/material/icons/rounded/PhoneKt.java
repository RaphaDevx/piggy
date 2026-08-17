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

/* compiled from: Phone.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Phone", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhone", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class PhoneKt {
    private static ImageVector _phone;

    public static final ImageVector getPhone(Icons.Rounded rounded) {
        ImageVector.Builder m1722addPathoIyEayM;
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _phone;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Phone", Dp.m3007constructorimpl(24.0f), Dp.m3007constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1280getBlack0d7_KjU(), null);
        int m1523getButtKaPHkGw = StrokeCap.INSTANCE.m1523getButtKaPHkGw();
        int m1533getBevelLxFBmk8 = StrokeJoin.INSTANCE.m1533getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.63f, 14.4f);
        pathBuilder.lineToRelative(-2.52f, 2.5f);
        pathBuilder.curveToRelative(-2.5f, -1.43f, -4.57f, -3.5f, -6.0f, -6.0f);
        pathBuilder.lineToRelative(2.5f, -2.52f);
        pathBuilder.curveToRelative(0.23f, -0.24f, 0.33f, -0.57f, 0.27f, -0.9f);
        pathBuilder.lineTo(9.13f, 3.8f);
        pathBuilder.curveTo(9.04f, 3.34f, 8.63f, 3.0f, 8.15f, 3.0f);
        pathBuilder.lineTo(4.0f, 3.0f);
        pathBuilder.curveTo(3.44f, 3.0f, 2.97f, 3.47f, 3.0f, 4.03f);
        pathBuilder.curveTo(3.17f, 6.92f, 4.05f, 9.63f, 5.43f, 12.0f);
        pathBuilder.curveToRelative(1.58f, 2.73f, 3.85f, 4.99f, 6.57f, 6.57f);
        pathBuilder.curveToRelative(2.37f, 1.37f, 5.08f, 2.26f, 7.97f, 2.43f);
        pathBuilder.curveToRelative(0.56f, 0.03f, 1.03f, -0.44f, 1.03f, -1.0f);
        pathBuilder.lineToRelative(0.0f, -4.15f);
        pathBuilder.curveToRelative(0.0f, -0.48f, -0.34f, -0.89f, -0.8f, -0.98f);
        pathBuilder.lineToRelative(-3.67f, -0.73f);
        pathBuilder.curveTo(16.2f, 14.07f, 15.86f, 14.17f, 15.63f, 14.4f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        m1722addPathoIyEayM = builder.m1722addPathoIyEayM(pathBuilder.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : defaultFillType, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : solidColor, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : m1523getButtKaPHkGw, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : m1533getBevelLxFBmk8, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        ImageVector build = m1722addPathoIyEayM.build();
        _phone = build;
        Intrinsics.checkNotNull(build);
        return build;
    }
}
