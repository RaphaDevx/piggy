package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Scroll.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a!\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a2\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u000f\u001a6\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a2\u0010\u001b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u000f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"HorizontalScrollableClipModifier", "Landroidx/compose/ui/Modifier;", "MaxSupportedElevation", "Landroidx/compose/ui/unit/Dp;", "F", "VerticalScrollableClipModifier", "rememberScrollState", "Landroidx/compose/foundation/ScrollState;", "initial", "", "(ILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/ScrollState;", "assertNotNestingScrollableContainers", "", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "assertNotNestingScrollableContainers-K40F9xA", "(JZ)V", "clipScrollableContainer", "horizontalScroll", SentryThread.JsonKeys.STATE, ExtentionsKt.ENABLED_KEY, "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "reverseScrolling", "scroll", "isScrollable", "verticalScroll", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ScrollKt {
    private static final float MaxSupportedElevation = Dp.m3007constructorimpl(30);
    private static final Modifier HorizontalScrollableClipModifier = ClipKt.clip(Modifier.INSTANCE, new Shape() { // from class: androidx.compose.foundation.ScrollKt$HorizontalScrollableClipModifier$1
        @Override // androidx.compose.ui.graphics.Shape
        /* renamed from: createOutline-Pq9zytI, reason: not valid java name */
        public Outline mo181createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
            float f;
            Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
            Intrinsics.checkNotNullParameter(density, "density");
            f = ScrollKt.MaxSupportedElevation;
            float mo222roundToPx0680j_4 = density.mo222roundToPx0680j_4(f);
            return new Outline.Rectangle(new Rect(0.0f, -mo222roundToPx0680j_4, Size.m1094getWidthimpl(size), Size.m1091getHeightimpl(size) + mo222roundToPx0680j_4));
        }
    });
    private static final Modifier VerticalScrollableClipModifier = ClipKt.clip(Modifier.INSTANCE, new Shape() { // from class: androidx.compose.foundation.ScrollKt$VerticalScrollableClipModifier$1
        @Override // androidx.compose.ui.graphics.Shape
        /* renamed from: createOutline-Pq9zytI */
        public Outline mo181createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
            float f;
            Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
            Intrinsics.checkNotNullParameter(density, "density");
            f = ScrollKt.MaxSupportedElevation;
            float mo222roundToPx0680j_4 = density.mo222roundToPx0680j_4(f);
            return new Outline.Rectangle(new Rect(-mo222roundToPx0680j_4, 0.0f, Size.m1094getWidthimpl(size) + mo222roundToPx0680j_4, Size.m1091getHeightimpl(size)));
        }
    });

    public static final ScrollState rememberScrollState(final int i, Composer composer, int i2, int i3) {
        composer.startReplaceableGroup(122203214);
        ComposerKt.sourceInformation(composer, "C(rememberScrollState)78@3503L90:Scroll.kt#71ulvw");
        if ((i3 & 1) != 0) {
            i = 0;
        }
        ScrollState scrollState = (ScrollState) RememberSaveableKt.m954rememberSaveable(new Object[0], (Saver) ScrollState.INSTANCE.getSaver(), (String) null, (Function0) new Function0<ScrollState>() { // from class: androidx.compose.foundation.ScrollKt$rememberScrollState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ScrollState invoke() {
                return new ScrollState(i);
            }
        }, composer, 72, 4);
        composer.endReplaceableGroup();
        return scrollState;
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return verticalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier verticalScroll(Modifier modifier, ScrollState state, boolean z, FlingBehavior flingBehavior, boolean z2) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        return scroll(modifier, state, z2, flingBehavior, z, true);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return horizontalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier horizontalScroll(Modifier modifier, ScrollState state, boolean z, FlingBehavior flingBehavior, boolean z2) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        return scroll(modifier, state, z2, flingBehavior, z, false);
    }

    /* renamed from: assertNotNestingScrollableContainers-K40F9xA, reason: not valid java name */
    public static final void m180assertNotNestingScrollableContainersK40F9xA(long j, boolean z) {
        if (z) {
            if (!(Constraints.m2976getMaxHeightimpl(j) != Integer.MAX_VALUE)) {
                throw new IllegalStateException("Nesting scrollable in the same direction layouts like LazyColumn and Column(Modifier.verticalScroll()) is not allowed. If you want to add a header before the list of items please take a look on LazyColumn component which has a DSL api which allows to first add a header via item() function and then the list of items via items().".toString());
            }
        } else {
            if (!(Constraints.m2977getMaxWidthimpl(j) != Integer.MAX_VALUE)) {
                throw new IllegalStateException("Nesting scrollable in the same direction layouts like LazyRow and Row(Modifier.horizontalScroll() is not allowed. If you want to add a header before the list of items please take a look on LazyRow component which has a DSL api which allows to first add a fixed element via item() function and then the list of items via items().".toString());
            }
        }
    }

    public static final Modifier clipScrollableContainer(Modifier modifier, boolean z) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return modifier.then(z ? VerticalScrollableClipModifier : HorizontalScrollableClipModifier);
    }

    private static final Modifier scroll(Modifier modifier, final ScrollState scrollState, final boolean z, final FlingBehavior flingBehavior, final boolean z2, final boolean z3) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ScrollKt$scroll$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("scroll");
                inspectorInfo.getProperties().set(SentryThread.JsonKeys.STATE, ScrollState.this);
                inspectorInfo.getProperties().set("reverseScrolling", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("flingBehavior", flingBehavior);
                inspectorInfo.getProperties().set("isScrollable", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("isVertical", Boolean.valueOf(z3));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-1641237902);
                ComposerKt.sourceInformation(composer, "C262@9815L24,263@9881L7:Scroll.kt#71ulvw");
                composer.startReplaceableGroup(-723524056);
                ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue = composer.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    rememberedValue = compositionScopedCoroutineScopeCanceller;
                }
                composer.endReplaceableGroup();
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
                composer.endReplaceableGroup();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                boolean z4 = (z3 || !(consume == LayoutDirection.Rtl)) ? z : !z;
                Modifier.Companion companion = Modifier.INSTANCE;
                final boolean z5 = z2;
                final boolean z6 = z3;
                final ScrollState scrollState2 = scrollState;
                final boolean z7 = z4;
                Modifier then = ScrollKt.clipScrollableContainer(SemanticsModifierKt.semantics$default(companion, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semantics) {
                        Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                        if (z5) {
                            final ScrollState scrollState3 = scrollState2;
                            Function0<Float> function0 = new Function0<Float>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$accessibilityScrollState$1
                                {
                                    super(0);
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final float invoke2() {
                                    return ScrollState.this.getValue();
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Float invoke() {
                                    return Float.valueOf(invoke2());
                                }
                            };
                            final ScrollState scrollState4 = scrollState2;
                            ScrollAxisRange scrollAxisRange = new ScrollAxisRange(function0, new Function0<Float>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$accessibilityScrollState$2
                                {
                                    super(0);
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final float invoke2() {
                                    return ScrollState.this.getMaxValue();
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Float invoke() {
                                    return Float.valueOf(invoke2());
                                }
                            }, z7);
                            if (z6) {
                                SemanticsPropertiesKt.setVerticalScrollAxisRange(semantics, scrollAxisRange);
                            } else {
                                SemanticsPropertiesKt.setHorizontalScrollAxisRange(semantics, scrollAxisRange);
                            }
                            final CoroutineScope coroutineScope2 = coroutineScope;
                            final boolean z8 = z6;
                            final ScrollState scrollState5 = scrollState2;
                            SemanticsPropertiesKt.scrollBy$default(semantics, null, new Function2<Float, Float, Boolean>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Boolean invoke(Float f, Float f2) {
                                    return Boolean.valueOf(invoke(f.floatValue(), f2.floatValue()));
                                }

                                public final boolean invoke(float f, float f2) {
                                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new C00021(z8, scrollState5, f2, f, null), 3, null);
                                    return true;
                                }

                                /* compiled from: Scroll.kt */
                                @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                @DebugMetadata(c = "androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$1$1", f = "Scroll.kt", i = {}, l = {285, 287}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$1$1, reason: invalid class name and collision with other inner class name */
                                static final class C00021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ boolean $isVertical;
                                    final /* synthetic */ ScrollState $state;
                                    final /* synthetic */ float $x;
                                    final /* synthetic */ float $y;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C00021(boolean z, ScrollState scrollState, float f, float f2, Continuation<? super C00021> continuation) {
                                        super(2, continuation);
                                        this.$isVertical = z;
                                        this.$state = scrollState;
                                        this.$y = f;
                                        this.$x = f2;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C00021(this.$isVertical, this.$state, this.$y, this.$x, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C00021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            if (this.$isVertical) {
                                                this.label = 1;
                                                if (ScrollExtensionsKt.scrollBy(this.$state, this.$y, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                            } else {
                                                this.label = 2;
                                                if (ScrollExtensionsKt.scrollBy(this.$state, this.$x, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                            }
                                        } else {
                                            if (i != 1 && i != 2) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }, 1, null);
                        }
                    }
                }, 1, null).then(ScrollableKt.scrollable(Modifier.INSTANCE, scrollState, z3 ? Orientation.Vertical : Orientation.Horizontal, z2, !z4, flingBehavior, scrollState.getInternalInteractionSource())), z3).then(new ScrollingLayoutModifier(scrollState, z, z3));
                composer.endReplaceableGroup();
                return then;
            }
        });
    }
}
