package androidx.compose.material;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Scaffold.kt */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¢\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192 \b\u0002\u0010\u001a\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001a\u008a\u0001\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00172\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u00102\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\u0002\b\u0010H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a!\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u00020\u0014H\u0007¢\u0006\u0002\u00106\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/ScaffoldState;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "isFloatingActionButtonDocked", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "drawerGesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerScrimColor", "backgroundColor", "contentColor", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-27mzLpw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ScaffoldLayout", "isFabDocked", "fabPosition", "snackbar", "fab", "ScaffoldLayout-MDYNRJg", "(ZILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "rememberScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ScaffoldState;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0<FabPlacement>() { // from class: androidx.compose.material.ScaffoldKt$LocalFabPlacement$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FabPlacement invoke() {
            return null;
        }
    });
    private static final float FabSpacing = Dp.m3007constructorimpl(16);

    public static final ScaffoldState rememberScaffoldState(DrawerState drawerState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1962071859);
        ComposerKt.sourceInformation(composer, "C(rememberScaffoldState)62@2223L39,63@2307L32,64@2359L62:Scaffold.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, composer, 6, 2);
        }
        if ((i2 & 2) != 0) {
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new SnackbarHostState();
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            snackbarHostState = (SnackbarHostState) rememberedValue;
        }
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue2 = composer.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new ScaffoldState(drawerState, snackbarHostState);
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        ScaffoldState scaffoldState = (ScaffoldState) rememberedValue2;
        composer.endReplaceableGroup();
        return scaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02f9  */
    /* renamed from: Scaffold-27mzLpw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m829Scaffold27mzLpw(androidx.compose.ui.Modifier r33, androidx.compose.material.ScaffoldState r34, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r37, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, int r39, boolean r40, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, boolean r42, androidx.compose.ui.graphics.Shape r43, float r44, long r45, long r47, long r49, long r51, long r53, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 1290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ScaffoldKt.m829Scaffold27mzLpw(androidx.compose.ui.Modifier, androidx.compose.material.ScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ScaffoldLayout-MDYNRJg, reason: not valid java name */
    public static final void m830ScaffoldLayoutMDYNRJg(final boolean z, final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        int i4;
        Composer startRestartGroup = composer.startRestartGroup(-2103106784);
        ComposerKt.sourceInformation(startRestartGroup, "C(ScaffoldLayout)P(4,3:c#material.FabPosition,6,1,5,2)234@10158L4498,234@10141L4515:Scaffold.kt#jmzs0o");
        if ((i2 & 14) == 0) {
            i3 = (startRestartGroup.changed(z) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & b.p) == 0) {
            i3 |= startRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i3 |= startRestartGroup.changed(function2) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i3 |= startRestartGroup.changed(function3) ? 2048 : 1024;
        }
        if ((57344 & i2) == 0) {
            i3 |= startRestartGroup.changed(function22) ? 16384 : 8192;
        }
        if ((458752 & i2) == 0) {
            i3 |= startRestartGroup.changed(function23) ? 131072 : 65536;
        }
        if ((3670016 & i2) == 0) {
            i3 |= startRestartGroup.changed(function24) ? 1048576 : 524288;
        }
        final int i5 = i3;
        if (((i5 & 2995931) ^ 599186) != 0 || !startRestartGroup.getSkipping()) {
            Object[] objArr = {function2, function22, function23, FabPosition.m741boximpl(i), Boolean.valueOf(z), function24, function3};
            startRestartGroup.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
            int i6 = 0;
            boolean z2 = false;
            while (i6 < 7) {
                Object obj = objArr[i6];
                i6++;
                z2 |= startRestartGroup.changed(obj);
            }
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (z2 || rememberedValue == Composer.INSTANCE.getEmpty()) {
                i4 = 0;
                rememberedValue = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                        return m832invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                    }

                    /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m832invoke0kLqBqw(final SubcomposeMeasureScope SubcomposeLayout, long j) {
                        Intrinsics.checkNotNullParameter(SubcomposeLayout, "$this$SubcomposeLayout");
                        final int m2977getMaxWidthimpl = Constraints.m2977getMaxWidthimpl(j);
                        final int m2976getMaxHeightimpl = Constraints.m2976getMaxHeightimpl(j);
                        final long m2968copyZbe2FdA$default = Constraints.m2968copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        final Function2<Composer, Integer, Unit> function25 = function2;
                        final Function2<Composer, Integer, Unit> function26 = function22;
                        final Function2<Composer, Integer, Unit> function27 = function23;
                        final int i7 = i;
                        final boolean z3 = z;
                        final Function2<Composer, Integer, Unit> function28 = function24;
                        final int i8 = i5;
                        final Function3<PaddingValues, Composer, Integer, Unit> function32 = function3;
                        return MeasureScope.DefaultImpls.layout$default(SubcomposeLayout, m2977getMaxWidthimpl, m2976getMaxHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope layout) {
                                Object obj2;
                                Object obj3;
                                final FabPlacement fabPlacement;
                                Object obj4;
                                int height;
                                float f;
                                int i9;
                                int height2;
                                Integer valueOf;
                                float f2;
                                int i10;
                                Object obj5;
                                Object obj6;
                                int i11;
                                float f3;
                                float f4;
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                List<Measurable> subcompose = SubcomposeMeasureScope.this.subcompose(ScaffoldLayoutContent.TopBar, function25);
                                long j2 = m2968copyZbe2FdA$default;
                                ArrayList arrayList = new ArrayList(subcompose.size());
                                int size = subcompose.size() - 1;
                                if (size >= 0) {
                                    int i12 = 0;
                                    while (true) {
                                        int i13 = i12 + 1;
                                        arrayList.add(subcompose.get(i12).mo2510measureBRTryo0(j2));
                                        if (i13 > size) {
                                            break;
                                        } else {
                                            i12 = i13;
                                        }
                                    }
                                }
                                ArrayList arrayList2 = arrayList;
                                if (arrayList2.isEmpty()) {
                                    obj2 = null;
                                } else {
                                    obj2 = arrayList2.get(0);
                                    int height3 = ((Placeable) obj2).getHeight();
                                    int lastIndex = CollectionsKt.getLastIndex(arrayList2);
                                    if (1 <= lastIndex) {
                                        int i14 = 1;
                                        while (true) {
                                            int i15 = i14 + 1;
                                            Object obj7 = arrayList2.get(i14);
                                            int height4 = ((Placeable) obj7).getHeight();
                                            if (height3 < height4) {
                                                obj2 = obj7;
                                                height3 = height4;
                                            }
                                            if (i14 == lastIndex) {
                                                break;
                                            } else {
                                                i14 = i15;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable = (Placeable) obj2;
                                int height5 = placeable == null ? 0 : placeable.getHeight();
                                List<Measurable> subcompose2 = SubcomposeMeasureScope.this.subcompose(ScaffoldLayoutContent.Snackbar, function26);
                                long j3 = m2968copyZbe2FdA$default;
                                ArrayList arrayList3 = new ArrayList(subcompose2.size());
                                int size2 = subcompose2.size() - 1;
                                if (size2 >= 0) {
                                    int i16 = 0;
                                    while (true) {
                                        int i17 = i16 + 1;
                                        arrayList3.add(subcompose2.get(i16).mo2510measureBRTryo0(j3));
                                        if (i17 > size2) {
                                            break;
                                        } else {
                                            i16 = i17;
                                        }
                                    }
                                }
                                ArrayList arrayList4 = arrayList3;
                                if (arrayList4.isEmpty()) {
                                    obj3 = null;
                                } else {
                                    obj3 = arrayList4.get(0);
                                    int height6 = ((Placeable) obj3).getHeight();
                                    int lastIndex2 = CollectionsKt.getLastIndex(arrayList4);
                                    if (1 <= lastIndex2) {
                                        int i18 = 1;
                                        while (true) {
                                            int i19 = i18 + 1;
                                            Object obj8 = arrayList4.get(i18);
                                            int height7 = ((Placeable) obj8).getHeight();
                                            if (height6 < height7) {
                                                obj3 = obj8;
                                                height6 = height7;
                                            }
                                            if (i18 == lastIndex2) {
                                                break;
                                            } else {
                                                i18 = i19;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable2 = (Placeable) obj3;
                                int height8 = placeable2 == null ? 0 : placeable2.getHeight();
                                List<Measurable> subcompose3 = SubcomposeMeasureScope.this.subcompose(ScaffoldLayoutContent.Fab, function27);
                                long j4 = m2968copyZbe2FdA$default;
                                ArrayList arrayList5 = new ArrayList();
                                Iterator<T> it = subcompose3.iterator();
                                while (it.hasNext()) {
                                    Placeable mo2510measureBRTryo0 = ((Measurable) it.next()).mo2510measureBRTryo0(j4);
                                    if (mo2510measureBRTryo0.getHeight() == 0 || mo2510measureBRTryo0.getWidth() == 0) {
                                        mo2510measureBRTryo0 = null;
                                    }
                                    if (mo2510measureBRTryo0 != null) {
                                        arrayList5.add(mo2510measureBRTryo0);
                                    }
                                }
                                ArrayList arrayList6 = arrayList5;
                                if (arrayList6.isEmpty()) {
                                    fabPlacement = null;
                                } else {
                                    if (arrayList6.isEmpty()) {
                                        obj5 = null;
                                    } else {
                                        obj5 = arrayList6.get(0);
                                        int width = ((Placeable) obj5).getWidth();
                                        int lastIndex3 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex3) {
                                            int i20 = 1;
                                            while (true) {
                                                int i21 = i20 + 1;
                                                Object obj9 = arrayList6.get(i20);
                                                int width2 = ((Placeable) obj9).getWidth();
                                                if (width < width2) {
                                                    width = width2;
                                                    obj5 = obj9;
                                                }
                                                if (i20 == lastIndex3) {
                                                    break;
                                                } else {
                                                    i20 = i21;
                                                }
                                            }
                                        }
                                    }
                                    Placeable placeable3 = (Placeable) obj5;
                                    Intrinsics.checkNotNull(placeable3);
                                    int width3 = placeable3.getWidth();
                                    if (arrayList6.isEmpty()) {
                                        obj6 = null;
                                    } else {
                                        obj6 = arrayList6.get(0);
                                        int height9 = ((Placeable) obj6).getHeight();
                                        int lastIndex4 = CollectionsKt.getLastIndex(arrayList6);
                                        if (1 <= lastIndex4) {
                                            int i22 = 1;
                                            while (true) {
                                                int i23 = i22 + 1;
                                                Object obj10 = arrayList6.get(i22);
                                                int height10 = ((Placeable) obj10).getHeight();
                                                if (height9 < height10) {
                                                    height9 = height10;
                                                    obj6 = obj10;
                                                }
                                                if (i22 == lastIndex4) {
                                                    break;
                                                } else {
                                                    i22 = i23;
                                                }
                                            }
                                        }
                                    }
                                    Placeable placeable4 = (Placeable) obj6;
                                    Intrinsics.checkNotNull(placeable4);
                                    int height11 = placeable4.getHeight();
                                    if (FabPosition.m744equalsimpl0(i7, FabPosition.INSTANCE.m749getEnd5ygKITE())) {
                                        if (SubcomposeMeasureScope.this.getLayoutDirection() == LayoutDirection.Ltr) {
                                            int i24 = m2977getMaxWidthimpl;
                                            SubcomposeMeasureScope subcomposeMeasureScope = SubcomposeMeasureScope.this;
                                            f4 = ScaffoldKt.FabSpacing;
                                            i11 = (i24 - subcomposeMeasureScope.mo222roundToPx0680j_4(f4)) - width3;
                                        } else {
                                            SubcomposeMeasureScope subcomposeMeasureScope2 = SubcomposeMeasureScope.this;
                                            f3 = ScaffoldKt.FabSpacing;
                                            i11 = subcomposeMeasureScope2.mo222roundToPx0680j_4(f3);
                                        }
                                    } else {
                                        i11 = (m2977getMaxWidthimpl - width3) / 2;
                                    }
                                    fabPlacement = new FabPlacement(z3, i11, width3, height11);
                                }
                                SubcomposeMeasureScope subcomposeMeasureScope3 = SubcomposeMeasureScope.this;
                                ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                                final Function2<Composer, Integer, Unit> function29 = function28;
                                final int i25 = i8;
                                List<Measurable> subcompose4 = subcomposeMeasureScope3.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-985538854, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bottomBarPlaceables$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i26) {
                                        ComposerKt.sourceInformation(composer2, "C283@12203L144:Scaffold.kt#jmzs0o");
                                        if (((i26 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                                            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{ScaffoldKt.getLocalFabPlacement().provides(FabPlacement.this)}, function29, composer2, ((i25 >> 15) & b.p) | 8);
                                        } else {
                                            composer2.skipToGroupEnd();
                                        }
                                    }
                                }));
                                long j5 = m2968copyZbe2FdA$default;
                                ArrayList arrayList7 = new ArrayList(subcompose4.size());
                                int size3 = subcompose4.size() - 1;
                                if (size3 >= 0) {
                                    int i26 = 0;
                                    while (true) {
                                        int i27 = i26 + 1;
                                        arrayList7.add(subcompose4.get(i26).mo2510measureBRTryo0(j5));
                                        if (i27 > size3) {
                                            break;
                                        } else {
                                            i26 = i27;
                                        }
                                    }
                                }
                                ArrayList arrayList8 = arrayList7;
                                if (arrayList8.isEmpty()) {
                                    obj4 = null;
                                } else {
                                    obj4 = arrayList8.get(0);
                                    int height12 = ((Placeable) obj4).getHeight();
                                    int lastIndex5 = CollectionsKt.getLastIndex(arrayList8);
                                    if (1 <= lastIndex5) {
                                        int i28 = 1;
                                        while (true) {
                                            int i29 = i28 + 1;
                                            Object obj11 = arrayList8.get(i28);
                                            int height13 = ((Placeable) obj11).getHeight();
                                            if (height12 < height13) {
                                                obj4 = obj11;
                                                height12 = height13;
                                            }
                                            if (i28 == lastIndex5) {
                                                break;
                                            } else {
                                                i28 = i29;
                                            }
                                        }
                                    }
                                }
                                Placeable placeable5 = (Placeable) obj4;
                                final int height14 = placeable5 == null ? 0 : placeable5.getHeight();
                                if (fabPlacement == null) {
                                    valueOf = null;
                                } else {
                                    SubcomposeMeasureScope subcomposeMeasureScope4 = SubcomposeMeasureScope.this;
                                    boolean z4 = z3;
                                    if (height14 == 0) {
                                        height = fabPlacement.getHeight();
                                        f2 = ScaffoldKt.FabSpacing;
                                        i9 = subcomposeMeasureScope4.mo222roundToPx0680j_4(f2);
                                    } else if (z4) {
                                        height2 = height14 + (fabPlacement.getHeight() / 2);
                                        valueOf = Integer.valueOf(height2);
                                    } else {
                                        height = fabPlacement.getHeight() + height14;
                                        f = ScaffoldKt.FabSpacing;
                                        i9 = subcomposeMeasureScope4.mo222roundToPx0680j_4(f);
                                    }
                                    height2 = height + i9;
                                    valueOf = Integer.valueOf(height2);
                                }
                                if (height8 != 0) {
                                    i10 = height8 + (valueOf == null ? height14 : valueOf.intValue());
                                } else {
                                    i10 = 0;
                                }
                                int i30 = m2976getMaxHeightimpl - height5;
                                SubcomposeMeasureScope subcomposeMeasureScope5 = SubcomposeMeasureScope.this;
                                ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                                final SubcomposeMeasureScope subcomposeMeasureScope6 = SubcomposeMeasureScope.this;
                                final Function3<PaddingValues, Composer, Integer, Unit> function33 = function32;
                                final int i31 = i8;
                                List<Measurable> subcompose5 = subcomposeMeasureScope5.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-985545322, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$1$1$1$bodyContentPlaceables$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i32) {
                                        ComposerKt.sourceInformation(composer2, "C315@13625L21:Scaffold.kt#jmzs0o");
                                        if (((i32 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                                            function33.invoke(PaddingKt.m312PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, SubcomposeMeasureScope.this.mo225toDpu2uoSUM(height14), 7, null), composer2, Integer.valueOf((i31 >> 6) & b.p));
                                        } else {
                                            composer2.skipToGroupEnd();
                                        }
                                    }
                                }));
                                long j6 = m2968copyZbe2FdA$default;
                                ArrayList arrayList9 = new ArrayList(subcompose5.size());
                                int size4 = subcompose5.size() - 1;
                                if (size4 >= 0) {
                                    int i32 = 0;
                                    while (true) {
                                        int i33 = i32 + 1;
                                        List<Measurable> list = subcompose5;
                                        long j7 = j6;
                                        arrayList9.add(subcompose5.get(i32).mo2510measureBRTryo0(Constraints.m2968copyZbe2FdA$default(j6, 0, 0, 0, i30, 7, null)));
                                        if (i33 > size4) {
                                            break;
                                        }
                                        i32 = i33;
                                        subcompose5 = list;
                                        j6 = j7;
                                    }
                                }
                                ArrayList arrayList10 = arrayList9;
                                int size5 = arrayList10.size() - 1;
                                if (size5 >= 0) {
                                    int i34 = 0;
                                    while (true) {
                                        ArrayList arrayList11 = arrayList10;
                                        int i35 = size5;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList10.get(i34), 0, height5, 0.0f, 4, null);
                                        i34++;
                                        if (i34 > i35) {
                                            break;
                                        }
                                        size5 = i35;
                                        arrayList10 = arrayList11;
                                    }
                                }
                                int size6 = arrayList2.size() - 1;
                                if (size6 >= 0) {
                                    int i36 = 0;
                                    while (true) {
                                        int i37 = i36 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList2.get(i36), 0, 0, 0.0f, 4, null);
                                        if (i37 > size6) {
                                            break;
                                        } else {
                                            i36 = i37;
                                        }
                                    }
                                }
                                int i38 = m2976getMaxHeightimpl;
                                int size7 = arrayList4.size() - 1;
                                if (size7 >= 0) {
                                    int i39 = 0;
                                    while (true) {
                                        int i40 = i39 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList4.get(i39), 0, i38 - i10, 0.0f, 4, null);
                                        if (i40 > size7) {
                                            break;
                                        } else {
                                            i39 = i40;
                                        }
                                    }
                                }
                                int i41 = m2976getMaxHeightimpl;
                                int size8 = arrayList8.size() - 1;
                                if (size8 >= 0) {
                                    int i42 = 0;
                                    while (true) {
                                        int i43 = i42 + 1;
                                        Placeable.PlacementScope.place$default(layout, (Placeable) arrayList8.get(i42), 0, i41 - height14, 0.0f, 4, null);
                                        if (i43 > size8) {
                                            break;
                                        } else {
                                            i42 = i43;
                                        }
                                    }
                                }
                                if (fabPlacement == null) {
                                    return;
                                }
                                int i44 = m2976getMaxHeightimpl;
                                int size9 = arrayList6.size() - 1;
                                if (size9 >= 0) {
                                    int i45 = 0;
                                    while (true) {
                                        int i46 = i45 + 1;
                                        Placeable placeable6 = (Placeable) arrayList6.get(i45);
                                        int left = fabPlacement.getLeft();
                                        Intrinsics.checkNotNull(valueOf);
                                        Placeable.PlacementScope.place$default(layout, placeable6, left, i44 - valueOf.intValue(), 0.0f, 4, null);
                                        if (i46 > size9) {
                                            break;
                                        } else {
                                            i45 = i46;
                                        }
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }, 4, null);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            } else {
                i4 = 0;
            }
            startRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) rememberedValue, startRestartGroup, i4, 1);
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ScaffoldKt$ScaffoldLayout$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i7) {
                ScaffoldKt.m830ScaffoldLayoutMDYNRJg(z, i, function2, function3, function22, function23, function24, composer2, i2 | 1);
            }
        });
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
