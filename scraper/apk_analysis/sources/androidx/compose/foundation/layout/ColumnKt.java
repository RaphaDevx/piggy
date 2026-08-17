package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: Column.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aM\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\u0010\u0015\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"DefaultColumnMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "getDefaultColumnMeasurePolicy$annotations", "()V", "getDefaultColumnMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "Column", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "columnMeasurePolicy", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "foundation-layout_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ColumnKt {
    private static final MeasurePolicy DefaultColumnMeasurePolicy;

    public static /* synthetic */ void getDefaultColumnMeasurePolicy$annotations() {
    }

    public static final void Column(Modifier modifier, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(-1113031299);
        ComposerKt.sourceInformation(composer, "C(Column)P(2,3,1)71@3450L61,72@3516L133:Column.kt#2w3rfo");
        if ((i2 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            vertical = Arrangement.INSTANCE.getTop();
        }
        if ((i2 & 4) != 0) {
            horizontal = Alignment.INSTANCE.getStart();
        }
        MeasurePolicy columnMeasurePolicy = columnMeasurePolicy(vertical, horizontal, composer, (i >> 3) & WebSocketProtocol.PAYLOAD_SHORT);
        composer.startReplaceableGroup(1376089335);
        ComposerKt.sourceInformation(composer, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Density density = (Density) consume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object consume2 = composer.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composer);
        LayoutDirection layoutDirection = (LayoutDirection) consume2;
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(modifier);
        if (!(composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        composer.disableReusing();
        Composer m941constructorimpl = Updater.m941constructorimpl(composer);
        Updater.m948setimpl(m941constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m948setimpl(m941constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        composer.enableReusing();
        materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(composer)), composer, 0);
        composer.startReplaceableGroup(2058660585);
        composer.startReplaceableGroup(276693241);
        ComposerKt.sourceInformation(composer, "C73@3564L9:Column.kt#2w3rfo");
        content.invoke(ColumnScopeInstance.INSTANCE, composer, Integer.valueOf(((i >> 6) & b.p) | 6));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    public static final MeasurePolicy getDefaultColumnMeasurePolicy() {
        return DefaultColumnMeasurePolicy;
    }

    static {
        LayoutOrientation layoutOrientation = LayoutOrientation.Vertical;
        float spacing = Arrangement.INSTANCE.getTop().getSpacing();
        CrossAxisAlignment horizontal$foundation_layout_release = CrossAxisAlignment.INSTANCE.horizontal$foundation_layout_release(Alignment.INSTANCE.getStart());
        DefaultColumnMeasurePolicy = RowColumnImplKt.m340rowColumnMeasurePolicyTDGSqEk(layoutOrientation, new Function5<Integer, int[], LayoutDirection, Density, int[], Unit>() { // from class: androidx.compose.foundation.layout.ColumnKt$DefaultColumnMeasurePolicy$1
            @Override // kotlin.jvm.functions.Function5
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, int[] iArr, LayoutDirection layoutDirection, Density density, int[] iArr2) {
                invoke(num.intValue(), iArr, layoutDirection, density, iArr2);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int[] size, LayoutDirection noName_2, Density density, int[] outPosition) {
                Intrinsics.checkNotNullParameter(size, "size");
                Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                Intrinsics.checkNotNullParameter(density, "density");
                Intrinsics.checkNotNullParameter(outPosition, "outPosition");
                Arrangement.INSTANCE.getTop().arrange(density, i, size, outPosition);
            }
        }, spacing, SizeMode.Wrap, horizontal$foundation_layout_release);
    }

    public static final MeasurePolicy columnMeasurePolicy(final Arrangement.Vertical verticalArrangement, Alignment.Horizontal horizontalAlignment, Composer composer, int i) {
        MeasurePolicy m340rowColumnMeasurePolicyTDGSqEk;
        Intrinsics.checkNotNullParameter(verticalArrangement, "verticalArrangement");
        Intrinsics.checkNotNullParameter(horizontalAlignment, "horizontalAlignment");
        composer.startReplaceableGroup(1466279149);
        ComposerKt.sourceInformation(composer, "C(columnMeasurePolicy)P(1)95@4247L664:Column.kt#2w3rfo");
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean changed = composer.changed(verticalArrangement) | composer.changed(horizontalAlignment);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            if (Intrinsics.areEqual(verticalArrangement, Arrangement.INSTANCE.getTop()) && Intrinsics.areEqual(horizontalAlignment, Alignment.INSTANCE.getStart())) {
                m340rowColumnMeasurePolicyTDGSqEk = getDefaultColumnMeasurePolicy();
            } else {
                LayoutOrientation layoutOrientation = LayoutOrientation.Vertical;
                float spacing = verticalArrangement.getSpacing();
                CrossAxisAlignment horizontal$foundation_layout_release = CrossAxisAlignment.INSTANCE.horizontal$foundation_layout_release(horizontalAlignment);
                m340rowColumnMeasurePolicyTDGSqEk = RowColumnImplKt.m340rowColumnMeasurePolicyTDGSqEk(layoutOrientation, new Function5<Integer, int[], LayoutDirection, Density, int[], Unit>() { // from class: androidx.compose.foundation.layout.ColumnKt$columnMeasurePolicy$1$1
                    {
                        super(5);
                    }

                    @Override // kotlin.jvm.functions.Function5
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, int[] iArr, LayoutDirection layoutDirection, Density density, int[] iArr2) {
                        invoke(num.intValue(), iArr, layoutDirection, density, iArr2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i2, int[] size, LayoutDirection noName_2, Density density, int[] outPosition) {
                        Intrinsics.checkNotNullParameter(size, "size");
                        Intrinsics.checkNotNullParameter(noName_2, "$noName_2");
                        Intrinsics.checkNotNullParameter(density, "density");
                        Intrinsics.checkNotNullParameter(outPosition, "outPosition");
                        Arrangement.Vertical.this.arrange(density, i2, size, outPosition);
                    }
                }, spacing, SizeMode.Wrap, horizontal$foundation_layout_release);
            }
            rememberedValue = m340rowColumnMeasurePolicyTDGSqEk;
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue;
        composer.endReplaceableGroup();
        return measurePolicy;
    }
}
