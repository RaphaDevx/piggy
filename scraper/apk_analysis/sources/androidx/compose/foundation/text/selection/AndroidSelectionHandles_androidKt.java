package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.DrawScope;
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
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: AndroidSelectionHandles.android.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\n\u001aF\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0012H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001aj\u0010\u0015\u001a\u00020\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001a¢\u0006\u0002\b\u001bH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a`\u0010\u001e\u001a\u00020\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u00052\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b\u001bH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001a\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u0005H\u0000\u001a,\u0010$\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"DefaultSelectionHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "isStartHandle", "", "directions", "Lkotlin/Pair;", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "(Landroidx/compose/ui/Modifier;ZLkotlin/Pair;ZLandroidx/compose/runtime/Composer;I)V", "HandleDrawLayout", "width", "Landroidx/compose/ui/unit/Dp;", "height", "onCanvas", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "HandleDrawLayout-WMci_g0", "(Landroidx/compose/ui/Modifier;FFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "SelectionHandle", "startHandlePosition", "Landroidx/compose/ui/geometry/Offset;", "endHandlePosition", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "SelectionHandle-VGSPTLc", "(Landroidx/compose/ui/geometry/Offset;Landroidx/compose/ui/geometry/Offset;ZLkotlin/Pair;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SelectionHandlePopup", "SelectionHandlePopup-j-EXSnY", "(Landroidx/compose/ui/geometry/Offset;Landroidx/compose/ui/geometry/Offset;ZLkotlin/Pair;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isHandleLtrDirection", "direction", "areHandlesCrossed", "isLeft", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class AndroidSelectionHandles_androidKt {
    /* renamed from: SelectionHandle-VGSPTLc, reason: not valid java name */
    public static final void m537SelectionHandleVGSPTLc(final Offset offset, final Offset offset2, final boolean z, final Pair<? extends ResolvedTextDirection, ? extends ResolvedTextDirection> directions, final boolean z2, final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(directions, "directions");
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Composer startRestartGroup = composer.startRestartGroup(1221597745);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionHandle)P(6:c#ui.geometry.Offset,2:c#ui.geometry.Offset,4,1,3,5)49@1847L524:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(offset) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(offset2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= startRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= startRestartGroup.changed(directions) ? 2048 : 1024;
        }
        if ((i & 57344) == 0) {
            i2 |= startRestartGroup.changed(z2) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= startRestartGroup.changed(modifier) ? 131072 : 65536;
        }
        if ((3670016 & i) == 0) {
            i2 |= startRestartGroup.changed(function2) ? 1048576 : 524288;
        }
        final int i3 = i2;
        if (((2995931 & i3) ^ 599186) != 0 || !startRestartGroup.getSkipping()) {
            m538SelectionHandlePopupjEXSnY(offset, offset2, z, directions, z2, ComposableLambdaKt.composableLambda(startRestartGroup, -819892565, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$1
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
                    ComposerKt.sourceInformation(composer2, "C:AndroidSelectionHandles.android.kt#eksfi3");
                    if (((i4 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                        if (function2 == null) {
                            composer2.startReplaceableGroup(386443002);
                            ComposerKt.sourceInformation(composer2, "57@2130L210");
                            Modifier modifier2 = modifier;
                            boolean z3 = z;
                            Pair<ResolvedTextDirection, ResolvedTextDirection> pair = directions;
                            boolean z4 = z2;
                            int i5 = i3;
                            AndroidSelectionHandles_androidKt.DefaultSelectionHandle(modifier2, z3, pair, z4, composer2, ((i5 >> 15) & 14) | ((i5 >> 3) & b.p) | ((i5 >> 3) & 896) | ((i5 >> 3) & 7168));
                            composer2.endReplaceableGroup();
                            return;
                        }
                        composer2.startReplaceableGroup(386443242);
                        ComposerKt.sourceInformation(composer2, "63@2356L9");
                        function2.invoke(composer2, Integer.valueOf((i3 >> 18) & 14));
                        composer2.endReplaceableGroup();
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }), startRestartGroup, 196608 | (i3 & 14) | (i3 & b.p) | (i3 & 896) | (i3 & 7168) | (i3 & 57344));
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandle$2
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
                AndroidSelectionHandles_androidKt.m537SelectionHandleVGSPTLc(Offset.this, offset2, z, directions, z2, modifier, function2, composer2, i | 1);
            }
        });
    }

    public static final void DefaultSelectionHandle(final Modifier modifier, final boolean z, final Pair<? extends ResolvedTextDirection, ? extends ResolvedTextDirection> directions, final boolean z2, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(directions, "directions");
        Composer startRestartGroup = composer.startRestartGroup(-1892866825);
        ComposerKt.sourceInformation(startRestartGroup, "C(DefaultSelectionHandle)P(3,2)75@2630L35,76@2713L7,77@2737L291:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= startRestartGroup.changed(directions) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= startRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if (((i2 & 5851) ^ 1170) != 0 || !startRestartGroup.getSkipping()) {
            startRestartGroup.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(startRestartGroup, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new SelectionHandleCache();
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            startRestartGroup.endReplaceableGroup();
            final SelectionHandleCache selectionHandleCache = (SelectionHandleCache) rememberedValue;
            ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final long handleColor = ((TextSelectionColors) consume).getHandleColor();
            composer2 = startRestartGroup;
            m536HandleDrawLayoutWMci_g0(modifier, SelectionHandlesKt.getHANDLE_WIDTH(), SelectionHandlesKt.getHANDLE_HEIGHT(), new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$DefaultSelectionHandle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope HandleDrawLayout) {
                    boolean isLeft;
                    Intrinsics.checkNotNullParameter(HandleDrawLayout, "$this$HandleDrawLayout");
                    isLeft = AndroidSelectionHandles_androidKt.isLeft(z, directions, z2);
                    DrawScope.DefaultImpls.m1666drawPathLG529CI$default(HandleDrawLayout, SelectionHandleCache.this.createPath(HandleDrawLayout, isLeft), handleColor, 0.0f, null, null, 0, 60, null);
                }
            }, startRestartGroup, (i2 & 14) | 432);
        } else {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$DefaultSelectionHandle$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i3) {
                AndroidSelectionHandles_androidKt.DefaultSelectionHandle(Modifier.this, z, directions, z2, composer3, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: HandleDrawLayout-WMci_g0, reason: not valid java name */
    public static final void m536HandleDrawLayoutWMci_g0(final Modifier modifier, final float f, final float f2, final Function1<? super DrawScope, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(191751700);
        ComposerKt.sourceInformation(startRestartGroup, "C(HandleDrawLayout)P(1,3:c#ui.unit.Dp,0:c#ui.unit.Dp)152@5355L285:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            i2 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & b.p) == 0) {
            i2 |= startRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= startRestartGroup.changed(f2) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= startRestartGroup.changed(function1) ? 2048 : 1024;
        }
        if (((i2 & 5851) ^ 1170) != 0 || !startRestartGroup.getSkipping()) {
            Modifier drawBehind = DrawModifierKt.drawBehind(modifier, function1);
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandleDrawLayout$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo40measure3p2s80s(MeasureScope Layout, List<? extends Measurable> noName_0, long j) {
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    return MeasureScope.DefaultImpls.layout$default(Layout, Layout.mo222roundToPx0680j_4(f), Layout.mo222roundToPx0680j_4(f2), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandleDrawLayout$2$measure$1
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout) {
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }
                    }, 4, null);
                }
            };
            startRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(startRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
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
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(drawBehind);
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
            startRestartGroup.startReplaceableGroup(26902325);
            ComposerKt.sourceInformation(startRestartGroup, "C:AndroidSelectionHandles.android.kt#eksfi3");
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
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$HandleDrawLayout$3
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

            public final void invoke(Composer composer2, int i3) {
                AndroidSelectionHandles_androidKt.m536HandleDrawLayoutWMci_g0(Modifier.this, f, f2, function1, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: SelectionHandlePopup-j-EXSnY, reason: not valid java name */
    public static final void m538SelectionHandlePopupjEXSnY(final Offset offset, final Offset offset2, final boolean z, final Pair<? extends ResolvedTextDirection, ? extends ResolvedTextDirection> pair, final boolean z2, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        Offset offset3;
        int i2;
        Offset offset4;
        Composer startRestartGroup = composer.startRestartGroup(-1933125601);
        ComposerKt.sourceInformation(startRestartGroup, "C(SelectionHandlePopup)P(5:c#ui.geometry.Offset,2:c#ui.geometry.Offset,4,1,3)178@6257L90,182@6353L217:AndroidSelectionHandles.android.kt#eksfi3");
        if ((i & 14) == 0) {
            offset3 = offset;
            i2 = (startRestartGroup.changed(offset3) ? 4 : 2) | i;
        } else {
            offset3 = offset;
            i2 = i;
        }
        if ((i & b.p) == 0) {
            offset4 = offset2;
            i2 |= startRestartGroup.changed(offset4) ? 32 : 16;
        } else {
            offset4 = offset2;
        }
        if ((i & 896) == 0) {
            i2 |= startRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= startRestartGroup.changed(pair) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= startRestartGroup.changed(z2) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= startRestartGroup.changed(function2) ? 131072 : 65536;
        }
        if (((374491 & i2) ^ 74898) == 0 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
        } else {
            Offset offset5 = z ? offset3 : offset4;
            if (offset5 == null) {
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                    return;
                }
                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandlePopup$offset$1
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

                    public final void invoke(Composer composer2, int i3) {
                        AndroidSelectionHandles_androidKt.m538SelectionHandlePopupjEXSnY(Offset.this, offset2, z, pair, z2, function2, composer2, i | 1);
                    }
                });
                return;
            }
            long packedValue = offset5.getPackedValue();
            boolean isLeft = isLeft(z, pair, z2);
            long IntOffset = IntOffsetKt.IntOffset(MathKt.roundToInt(Offset.m1025getXimpl(packedValue)), MathKt.roundToInt(Offset.m1026getYimpl(packedValue)));
            Boolean valueOf = Boolean.valueOf(isLeft);
            IntOffset m3076boximpl = IntOffset.m3076boximpl(IntOffset);
            startRestartGroup.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(startRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean changed = startRestartGroup.changed(valueOf) | startRestartGroup.changed(m3076boximpl);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new SelectionHandlePositionProvider(isLeft, IntOffset, null);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            startRestartGroup.endReplaceableGroup();
            AndroidPopup_androidKt.Popup((SelectionHandlePositionProvider) rememberedValue, null, new PopupProperties(false, false, false, null, true, false, 15, null), function2, startRestartGroup, (i2 >> 6) & 7168, 2);
        }
        ScopeUpdateScope endRestartGroup2 = startRestartGroup.endRestartGroup();
        if (endRestartGroup2 == null) {
            return;
        }
        endRestartGroup2.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$SelectionHandlePopup$1
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

            public final void invoke(Composer composer2, int i3) {
                AndroidSelectionHandles_androidKt.m538SelectionHandlePopupjEXSnY(Offset.this, offset2, z, pair, z2, function2, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLeft(boolean z, Pair<? extends ResolvedTextDirection, ? extends ResolvedTextDirection> pair, boolean z2) {
        if (z) {
            return isHandleLtrDirection(pair.getFirst(), z2);
        }
        return !isHandleLtrDirection(pair.getSecond(), z2);
    }

    public static final boolean isHandleLtrDirection(ResolvedTextDirection direction, boolean z) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        return (direction == ResolvedTextDirection.Ltr && !z) || (direction == ResolvedTextDirection.Rtl && z);
    }
}
