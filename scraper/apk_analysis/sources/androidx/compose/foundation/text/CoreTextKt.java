package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
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
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreText.kt */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001ao\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\b0\u0006H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a>\u0010\u001f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2'\u0010 \u001a#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u0002H\u0001¢\u0006\u0002\u0010!\u001aa\u0010\"\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002\u001ao\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,\"K\u0010\u0000\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*:\b\u0002\u0010-\"\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u00032\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003*\u0018\b\u0002\u0010.\"\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0012\u0004\u0012\u00020\u00040\u0003\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006/"}, d2 = {"EmptyInlineContent", "Lkotlin/Pair;", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "Landroidx/compose/foundation/text/PlaceholderRange;", "Lkotlin/Function1;", "", "", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/foundation/text/InlineContentRange;", "CoreText", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "CoreText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "InlineChildren", "inlineContents", "(Landroidx/compose/ui/text/AnnotatedString;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "resolveInlineContent", "updateTextDelegate", "Landroidx/compose/foundation/text/TextDelegate;", SentryThread.JsonKeys.CURRENT, "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "placeholders", "updateTextDelegate-x_uQXYA", "(Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;ZIILjava/util/List;)Landroidx/compose/foundation/text/TextDelegate;", "InlineContentRange", "PlaceholderRange", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class CoreTextKt {
    private static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> EmptyInlineContent = new Pair<>(CollectionsKt.emptyList(), CollectionsKt.emptyList());

    /* renamed from: CoreText-4YKlhWE, reason: not valid java name */
    public static final void m450CoreText4YKlhWE(final AnnotatedString text, Modifier modifier, final TextStyle style, final boolean z, final int i, final int i2, final Map<String, InlineTextContent> inlineContent, final Function1<? super TextLayoutResult, Unit> onTextLayout, Composer composer, final int i3, final int i4) {
        SelectionRegistrar selectionRegistrar;
        String str;
        int i5;
        boolean z2;
        Modifier.Companion companion;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(inlineContent, "inlineContent");
        Intrinsics.checkNotNullParameter(onTextLayout, "onTextLayout");
        Composer startRestartGroup = composer.startRestartGroup(1241032154);
        ComposerKt.sourceInformation(startRestartGroup, "C(CoreText)P(7,2,6,5,4:c#ui.text.style.TextOverflow,1)115@5834L7,116@5873L7,117@5922L7,118@5990L7,129@6628L139,132@6784L410,161@7641L34,164@7723L1114,195@8843L64:CoreText.kt#423gt5");
        Modifier modifier2 = (i4 & 2) != 0 ? Modifier.INSTANCE : modifier;
        if (!(i2 > 0)) {
            throw new IllegalArgumentException("maxLines should be greater than 0".toString());
        }
        ProvidableCompositionLocal<SelectionRegistrar> localSelectionRegistrar = SelectionRegistrarKt.getLocalSelectionRegistrar();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume = startRestartGroup.consume(localSelectionRegistrar);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        final SelectionRegistrar selectionRegistrar2 = (SelectionRegistrar) consume;
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume2 = startRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Density density = (Density) consume2;
        ProvidableCompositionLocal<Font.ResourceLoader> localFontLoader = CompositionLocalsKt.getLocalFontLoader();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume3 = startRestartGroup.consume(localFontLoader);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Font.ResourceLoader resourceLoader = (Font.ResourceLoader) consume3;
        ProvidableCompositionLocal<TextSelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume4 = startRestartGroup.consume(localTextSelectionColors);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        long backgroundColor = ((TextSelectionColors) consume4).getBackgroundColor();
        Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> resolveInlineContent = resolveInlineContent(text, inlineContent);
        List<AnnotatedString.Range<Placeholder>> component1 = resolveInlineContent.component1();
        final List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> component2 = resolveInlineContent.component2();
        long longValue = ((Number) RememberSaveableKt.m954rememberSaveable(new Object[]{text, selectionRegistrar2}, (Saver) null, (String) null, (Function0) new Function0<Long>() { // from class: androidx.compose.foundation.text.CoreTextKt$CoreText$selectableId$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Long invoke() {
                return Long.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final long invoke2() {
                SelectionRegistrar selectionRegistrar3 = SelectionRegistrar.this;
                if (selectionRegistrar3 == null) {
                    return 0L;
                }
                return selectionRegistrar3.nextSelectableId();
            }
        }, startRestartGroup, 8, 6)).longValue();
        startRestartGroup.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(startRestartGroup, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = startRestartGroup.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            selectionRegistrar = selectionRegistrar2;
            str = "C(remember):Composables.kt#9igjgp";
            i5 = 0;
            z2 = true;
            TextState textState = new TextState(new TextDelegate(text, style, i2, z, i, density, resourceLoader, component1, null), longValue);
            startRestartGroup.updateRememberedValue(textState);
            rememberedValue = textState;
        } else {
            selectionRegistrar = selectionRegistrar2;
            str = "C(remember):Composables.kt#9igjgp";
            i5 = 0;
            z2 = true;
        }
        startRestartGroup.endReplaceableGroup();
        TextState textState2 = (TextState) rememberedValue;
        textState2.setTextDelegate(m451updateTextDelegatex_uQXYA(textState2.getTextDelegate(), text, style, density, resourceLoader, z, i, i2, component1));
        textState2.setOnTextLayout(onTextLayout);
        textState2.m535setSelectionBackgroundColor8_81llA(backgroundColor);
        startRestartGroup.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(startRestartGroup, str);
        Object rememberedValue2 = startRestartGroup.rememberedValue();
        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            rememberedValue2 = new TextController(textState2);
            startRestartGroup.updateRememberedValue(rememberedValue2);
        }
        startRestartGroup.endReplaceableGroup();
        TextController textController = (TextController) rememberedValue2;
        SelectionRegistrar selectionRegistrar3 = selectionRegistrar;
        textController.update(selectionRegistrar3);
        ComposableLambda m447getLambda1$foundation_release = component2.isEmpty() ? ComposableSingletons$CoreTextKt.INSTANCE.m447getLambda1$foundation_release() : ComposableLambdaKt.composableLambda(startRestartGroup, -819890150, z2, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextKt$CoreText$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i6) {
                ComposerKt.sourceInformation(composer2, "C168@7830L39:CoreText.kt#423gt5");
                if (((i6 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                    composer2.skipToGroupEnd();
                } else {
                    CoreTextKt.InlineChildren(AnnotatedString.this, component2, composer2, (i3 & 14) | 64);
                }
            }
        });
        Modifier then = modifier2.then(textController.getModifiers());
        if (selectionRegistrar3 != null) {
            if (TouchMode_androidKt.isInTouchMode()) {
                companion = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textController.getLongPressDragObserver(), new CoreTextKt$CoreText$3(textController, null));
            } else {
                companion = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textController.getMouseSelectionObserver(), new CoreTextKt$CoreText$4(textController, null));
            }
        } else {
            companion = Modifier.INSTANCE;
        }
        Modifier then2 = then.then(companion);
        MeasurePolicy measurePolicy = textController.getMeasurePolicy();
        startRestartGroup.startReplaceableGroup(1376089335);
        ComposerKt.sourceInformation(startRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume5 = startRestartGroup.consume(localDensity2);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        Density density2 = (Density) consume5;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume6 = startRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) consume6;
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(then2);
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
        Updater.m948setimpl(m941constructorimpl, density2, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        startRestartGroup.enableReusing();
        materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(startRestartGroup)), startRestartGroup, Integer.valueOf(i5));
        startRestartGroup.startReplaceableGroup(2058660585);
        m447getLambda1$foundation_release.invoke(startRestartGroup, Integer.valueOf(i5));
        startRestartGroup.endReplaceableGroup();
        startRestartGroup.endNode();
        startRestartGroup.endReplaceableGroup();
        EffectsKt.DisposableEffect(selectionRegistrar3, textController.getCommit(), startRestartGroup, i5);
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        final Modifier modifier3 = modifier2;
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextKt$CoreText$5
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

            public final void invoke(Composer composer2, int i6) {
                CoreTextKt.m450CoreText4YKlhWE(AnnotatedString.this, modifier3, style, z, i, i2, inlineContent, onTextLayout, composer2, i3 | 1, i4);
            }
        });
    }

    public static final void InlineChildren(final AnnotatedString text, final List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> inlineContents, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(inlineContents, "inlineContents");
        Composer startRestartGroup = composer.startRestartGroup(710802501);
        ComposerKt.sourceInformation(startRestartGroup, "C(InlineChildren)P(1)*204@9094L356:CoreText.kt#423gt5");
        int size = inlineContents.size() - 1;
        if (size >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                AnnotatedString.Range<Function3<String, Composer, Integer, Unit>> range = inlineContents.get(i2);
                Function3<String, Composer, Integer, Unit> component1 = range.component1();
                int start = range.getStart();
                int end = range.getEnd();
                CoreTextKt$InlineChildren$1$2 coreTextKt$InlineChildren$1$2 = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextKt$InlineChildren$1$2
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
                    public final MeasureResult mo40measure3p2s80s(MeasureScope Layout, List<? extends Measurable> children, long j) {
                        Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                        Intrinsics.checkNotNullParameter(children, "children");
                        ArrayList arrayList = new ArrayList(children.size());
                        int size2 = children.size() - 1;
                        if (size2 >= 0) {
                            int i4 = 0;
                            while (true) {
                                int i5 = i4 + 1;
                                arrayList.add(children.get(i4).mo2510measureBRTryo0(j));
                                if (i5 > size2) {
                                    break;
                                }
                                i4 = i5;
                            }
                        }
                        final ArrayList arrayList2 = arrayList;
                        return MeasureScope.DefaultImpls.layout$default(Layout, Constraints.m2977getMaxWidthimpl(j), Constraints.m2976getMaxHeightimpl(j), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.CoreTextKt$InlineChildren$1$2$measure$1
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
                                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                List<Placeable> list = arrayList2;
                                int size3 = list.size() - 1;
                                if (size3 < 0) {
                                    return;
                                }
                                int i6 = 0;
                                while (true) {
                                    int i7 = i6 + 1;
                                    Placeable.PlacementScope.placeRelative$default(layout, list.get(i6), 0, 0, 0.0f, 4, null);
                                    if (i7 > size3) {
                                        return;
                                    } else {
                                        i6 = i7;
                                    }
                                }
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
                Updater.m948setimpl(m941constructorimpl, coreTextKt$InlineChildren$1$2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m948setimpl(m941constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                startRestartGroup.enableReusing();
                materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(startRestartGroup)), startRestartGroup, 0);
                startRestartGroup.startReplaceableGroup(2058660585);
                startRestartGroup.startReplaceableGroup(-1487993655);
                ComposerKt.sourceInformation(startRestartGroup, "C*205@9126L42:CoreText.kt#423gt5");
                component1.invoke(text.subSequence(start, end).getText(), startRestartGroup, 0);
                startRestartGroup.endReplaceableGroup();
                startRestartGroup.endReplaceableGroup();
                startRestartGroup.endNode();
                startRestartGroup.endReplaceableGroup();
                if (i3 > size) {
                    break;
                } else {
                    i2 = i3;
                }
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.CoreTextKt$InlineChildren$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                CoreTextKt.InlineChildren(AnnotatedString.this, inlineContents, composer2, i | 1);
            }
        });
    }

    /* renamed from: updateTextDelegate-x_uQXYA, reason: not valid java name */
    public static final TextDelegate m451updateTextDelegatex_uQXYA(TextDelegate current, AnnotatedString text, TextStyle style, Density density, Font.ResourceLoader resourceLoader, boolean z, int i, int i2, List<AnnotatedString.Range<Placeholder>> placeholders) {
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(resourceLoader, "resourceLoader");
        Intrinsics.checkNotNullParameter(placeholders, "placeholders");
        if (Intrinsics.areEqual(current.getText(), text) && Intrinsics.areEqual(current.getStyle(), style)) {
            if (current.getSoftWrap() == z) {
                if (TextOverflow.m2958equalsimpl0(current.getOverflow(), i)) {
                    if (current.getMaxLines() == i2 && Intrinsics.areEqual(current.getDensity(), density) && Intrinsics.areEqual(current.getPlaceholders(), placeholders)) {
                        return current;
                    }
                    return new TextDelegate(text, style, i2, z, i, density, resourceLoader, placeholders, null);
                }
                return new TextDelegate(text, style, i2, z, i, density, resourceLoader, placeholders, null);
            }
        }
        return new TextDelegate(text, style, i2, z, i, density, resourceLoader, placeholders, null);
    }

    private static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> resolveInlineContent(AnnotatedString annotatedString, Map<String, InlineTextContent> map) {
        if (map.isEmpty()) {
            return EmptyInlineContent;
        }
        int i = 0;
        List<AnnotatedString.Range<String>> stringAnnotations = annotatedString.getStringAnnotations(InlineTextContentKt.INLINE_CONTENT_TAG, 0, annotatedString.length());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = stringAnnotations.size() - 1;
        if (size >= 0) {
            while (true) {
                int i2 = i + 1;
                AnnotatedString.Range<String> range = stringAnnotations.get(i);
                InlineTextContent inlineTextContent = map.get(range.getItem());
                if (inlineTextContent != null) {
                    arrayList.add(new AnnotatedString.Range(inlineTextContent.getPlaceholder(), range.getStart(), range.getEnd()));
                    arrayList2.add(new AnnotatedString.Range(inlineTextContent.getChildren(), range.getStart(), range.getEnd()));
                }
                if (i2 > size) {
                    break;
                }
                i = i2;
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }
}
