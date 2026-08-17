package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.SentryLogEvent;
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
import kotlin.math.MathKt;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aá\u0002\u0010\u0003\u001a\u00020\u00042\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\b2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¢\u0006\u0002\b\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u00012 \b\u0002\u0010\u001e\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\u0002\b\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\u001f\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010$\u001a\u00020\u001b2\b\b\u0002\u0010%\u001a\u00020\u001b2\b\b\u0002\u0010&\u001a\u00020\u001b2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\b\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001aw\u0010+\u001a\u00020\u00042\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\b2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010\u0013\u001a\u00020\u0014H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102\u001a+\u00103\u001a\u00020\r2\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\u0011H\u0007¢\u0006\u0002\u00109\u001a;\u0010:\u001a\u0002072\u0006\u0010;\u001a\u00020<2\u000e\b\u0002\u0010=\u001a\b\u0012\u0004\u0012\u0002000>2\u0014\b\u0002\u0010?\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00160\u0006H\u0007¢\u0006\u0002\u0010@\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006A"}, d2 = {"FabEndSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "topBar", "Lkotlin/Function0;", "snackbarHost", "Landroidx/compose/material/SnackbarHostState;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetPeekHeight", "drawerContent", "drawerGesturesEnabled", "drawerShape", "drawerElevation", "drawerBackgroundColor", "drawerContentColor", "drawerScrimColor", "backgroundColor", "contentColor", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-bGncdBI", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;IIII)V", "BottomSheetScaffoldStack", SentryLogEvent.JsonKeys.BODY, "bottomSheet", "bottomSheetOffset", "Landroidx/compose/runtime/State;", "", "BottomSheetScaffoldStack-SlNgfk0", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/State;ILandroidx/compose/runtime/Composer;I)V", "rememberBottomSheetScaffoldState", "drawerState", "Landroidx/compose/material/DrawerState;", "bottomSheetState", "Landroidx/compose/material/BottomSheetState;", "snackbarHostState", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "rememberBottomSheetState", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class BottomSheetScaffoldKt {
    private static final float FabEndSpacing = Dp.m3007constructorimpl(16);

    @ExperimentalMaterialApi
    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        composer.startReplaceableGroup(1877845424);
        ComposerKt.sourceInformation(composer, "C(rememberBottomSheetState)P(2)156@5647L371:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$rememberBottomSheetState$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2(BottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return true;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(BottomSheetValue bottomSheetValue) {
                    return Boolean.valueOf(invoke2(bottomSheetValue));
                }
            };
        }
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m954rememberSaveable(new Object[]{animationSpec}, (Saver) BottomSheetState.INSTANCE.Saver(animationSpec, function1), (String) null, (Function0) new Function0<BottomSheetState>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$rememberBottomSheetState$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BottomSheetState invoke() {
                return new BottomSheetState(BottomSheetValue.this, animationSpec, function1);
            }
        }, composer, 72, 4);
        composer.endReplaceableGroup();
        return bottomSheetState;
    }

    @ExperimentalMaterialApi
    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(DrawerState drawerState, BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(1807566285);
        ComposerKt.sourceInformation(composer, "C(rememberBottomSheetScaffoldState)P(1)196@6892L39,197@6974L52,198@7071L32,200@7145L248:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, composer, 6, 2);
        }
        if ((i2 & 2) != 0) {
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, composer, 6, 6);
        }
        if ((i2 & 4) != 0) {
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
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean changed = composer.changed(drawerState) | composer.changed(bottomSheetState) | composer.changed(snackbarHostState);
        Object rememberedValue2 = composer.rememberedValue();
        if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new BottomSheetScaffoldState(drawerState, bottomSheetState, snackbarHostState);
            composer.updateRememberedValue(rememberedValue2);
        }
        composer.endReplaceableGroup();
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) rememberedValue2;
        composer.endReplaceableGroup();
        return bottomSheetScaffoldState;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x059e  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x049c  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f6  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: BottomSheetScaffold-bGncdBI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m634BottomSheetScaffoldbGncdBI(final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, androidx.compose.ui.Modifier r69, androidx.compose.material.BottomSheetScaffoldState r70, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r71, kotlin.jvm.functions.Function3<? super androidx.compose.material.SnackbarHostState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r73, int r74, boolean r75, androidx.compose.ui.graphics.Shape r76, float r77, long r78, long r80, float r82, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r83, boolean r84, androidx.compose.ui.graphics.Shape r85, float r86, long r87, long r89, long r91, long r93, long r95, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r97, androidx.compose.runtime.Composer r98, final int r99, final int r100, final int r101, final int r102) {
        /*
            Method dump skipped, instructions count: 1645
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BottomSheetScaffoldKt.m634BottomSheetScaffoldbGncdBI(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.BottomSheetScaffoldState, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, int, boolean, androidx.compose.ui.graphics.Shape, float, long, long, float, kotlin.jvm.functions.Function3, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: BottomSheetScaffoldStack-SlNgfk0, reason: not valid java name */
    public static final void m635BottomSheetScaffoldStackSlNgfk0(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final State<Float> state, final int i, Composer composer, final int i2) {
        int i3;
        Composer startRestartGroup = composer.startRestartGroup(1491542599);
        ComposerKt.sourceInformation(startRestartGroup, "C(BottomSheetScaffoldStack)P(!2,3,5!,4:c#material.FabPosition)390@16235L1315:BottomSheetScaffold.kt#jmzs0o");
        if ((i2 & 14) == 0) {
            i3 = (startRestartGroup.changed(function2) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & b.p) == 0) {
            i3 |= startRestartGroup.changed(function22) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i3 |= startRestartGroup.changed(function23) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i3 |= startRestartGroup.changed(function24) ? 2048 : 1024;
        }
        if ((57344 & i2) == 0) {
            i3 |= startRestartGroup.changed(state) ? 16384 : 8192;
        }
        if ((458752 & i2) == 0) {
            i3 |= startRestartGroup.changed(i) ? 131072 : 65536;
        }
        if (((374491 & i3) ^ 74898) != 0 || !startRestartGroup.getSkipping()) {
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i4) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i4);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo40measure3p2s80s(final MeasureScope Layout, final List<? extends Measurable> measurables, final long j) {
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final Placeable mo2510measureBRTryo0 = ((Measurable) CollectionsKt.first((List) measurables)).mo2510measureBRTryo0(j);
                    int width = mo2510measureBRTryo0.getWidth();
                    int height = mo2510measureBRTryo0.getHeight();
                    final State<Float> state2 = state;
                    final int i4 = i;
                    return MeasureScope.DefaultImpls.layout$default(Layout, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$2$measure$1
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
                            float f;
                            int i5;
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            Placeable.PlacementScope.placeRelative$default(layout, Placeable.this, 0, 0, 0.0f, 4, null);
                            List drop = CollectionsKt.drop(measurables, 1);
                            long j2 = j;
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(drop, 10));
                            Iterator it = drop.iterator();
                            while (it.hasNext()) {
                                arrayList.add(((Measurable) it.next()).mo2510measureBRTryo0(Constraints.m2968copyZbe2FdA$default(j2, 0, 0, 0, 0, 10, null)));
                            }
                            ArrayList arrayList2 = arrayList;
                            Placeable placeable = (Placeable) arrayList2.get(0);
                            Placeable placeable2 = (Placeable) arrayList2.get(1);
                            Placeable placeable3 = (Placeable) arrayList2.get(2);
                            int roundToInt = MathKt.roundToInt(state2.getValue().floatValue());
                            Placeable.PlacementScope.placeRelative$default(layout, placeable, 0, roundToInt, 0.0f, 4, null);
                            if (FabPosition.m744equalsimpl0(i4, FabPosition.INSTANCE.m748getCenter5ygKITE())) {
                                i5 = (Placeable.this.getWidth() - placeable2.getWidth()) / 2;
                            } else {
                                int width2 = Placeable.this.getWidth() - placeable2.getWidth();
                                MeasureScope measureScope = Layout;
                                f = BottomSheetScaffoldKt.FabEndSpacing;
                                i5 = width2 - measureScope.mo222roundToPx0680j_4(f);
                            }
                            Placeable.PlacementScope.placeRelative$default(layout, placeable2, i5, roundToInt - (placeable2.getHeight() / 2), 0.0f, 4, null);
                            Placeable.PlacementScope.placeRelative$default(layout, placeable3, (Placeable.this.getWidth() - placeable3.getWidth()) / 2, Placeable.this.getHeight() - placeable3.getHeight(), 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            startRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(startRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Density density = (Density) consume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume2 = startRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) consume2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(companion);
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            startRestartGroup.disableReusing();
            Composer m941constructorimpl = Updater.m941constructorimpl(startRestartGroup);
            Updater.m948setimpl(m941constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m948setimpl(m941constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            startRestartGroup.enableReusing();
            materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(startRestartGroup)), startRestartGroup, 0);
            startRestartGroup.startReplaceableGroup(2058660585);
            startRestartGroup.startReplaceableGroup(-526644304);
            ComposerKt.sourceInformation(startRestartGroup, "C392@16275L6,393@16294L13,394@16320L22,395@16355L14:BottomSheetScaffold.kt#jmzs0o");
            function2.invoke(startRestartGroup, Integer.valueOf(i3 & 14));
            function22.invoke(startRestartGroup, Integer.valueOf((i3 >> 3) & 14));
            function23.invoke(startRestartGroup, Integer.valueOf((i3 >> 6) & 14));
            function24.invoke(startRestartGroup, Integer.valueOf((i3 >> 9) & 14));
            startRestartGroup.endReplaceableGroup();
            startRestartGroup.endReplaceableGroup();
            startRestartGroup.endNode();
            startRestartGroup.endReplaceableGroup();
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldStack$3
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

            public final void invoke(Composer composer2, int i4) {
                BottomSheetScaffoldKt.m635BottomSheetScaffoldStackSlNgfk0(function2, function22, function23, function24, state, i, composer2, i2 | 1);
            }
        });
    }
}
