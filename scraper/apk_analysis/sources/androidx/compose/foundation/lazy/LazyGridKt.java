package androidx.compose.foundation.lazy;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.protocol.SentryThread;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: LazyGrid.kt */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010\f\u001aL\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a^\u0010\u0015\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0016*\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001723\b\u0004\u0010\u0018\u001a-\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u0013H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a^\u0010\u0015\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0016*\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160 23\b\u0004\u0010\u0018\u001a-\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u0013H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010!\u001as\u0010\"\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0016*\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160\u00172H\b\u0004\u0010\u0018\u001aB\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010#¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u0013H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010%\u001as\u0010\"\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0016*\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00160 2H\b\u0004\u0010\u0018\u001aB\u0012\u0004\u0012\u00020\u001a\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010#¢\u0006\u0002\b\u001e¢\u0006\u0002\b\u0013H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010&\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"FixedLazyGrid", "", "nColumns", "", "modifier", "Landroidx/compose/ui/Modifier;", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "scope", "Landroidx/compose/foundation/lazy/LazyGridScopeImpl;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/lazy/LazyGridScopeImpl;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalGrid", "cells", "Landroidx/compose/foundation/lazy/GridCells;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/GridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "items", ExifInterface.GPS_DIRECTION_TRUE, "", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Lkotlin/ParameterName;", "name", "item", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)V", "", "(Landroidx/compose/foundation/lazy/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "Lkotlin/Function3;", FirebaseAnalytics.Param.INDEX, "(Landroidx/compose/foundation/lazy/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyGridKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x006a  */
    @androidx.compose.foundation.ExperimentalFoundationApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyVerticalGrid(final androidx.compose.foundation.lazy.GridCells r20, androidx.compose.ui.Modifier r21, androidx.compose.foundation.lazy.LazyListState r22, androidx.compose.foundation.layout.PaddingValues r23, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.LazyGridScope, kotlin.Unit> r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyGridKt.LazyVerticalGrid(androidx.compose.foundation.lazy.GridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    @ExperimentalFoundationApi
    public static final <T> void items(LazyGridScope lazyGridScope, final List<? extends T> items, final Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), ComposableLambdaKt.composableLambdaInstance(-985537774, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$items$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope items2, int i, Composer composer, int i2) {
                int i3;
                Intrinsics.checkNotNullParameter(items2, "$this$items");
                ComposerKt.sourceInformation(composer, "C135@4695L22:LazyGrid.kt#428nma");
                if ((i2 & 14) == 0) {
                    i3 = (composer.changed(items2) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & b.p) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if (((i3 & 731) ^ 146) == 0 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                } else {
                    itemContent.invoke(items2, items.get(i), composer, Integer.valueOf(i3 & 14));
                }
            }
        }));
    }

    @ExperimentalFoundationApi
    public static final <T> void itemsIndexed(LazyGridScope lazyGridScope, final List<? extends T> items, final Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), ComposableLambdaKt.composableLambdaInstance(-985537877, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$itemsIndexed$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope items2, int i, Composer composer, int i2) {
                int i3;
                Intrinsics.checkNotNullParameter(items2, "$this$items");
                ComposerKt.sourceInformation(composer, "C149@5102L26:LazyGrid.kt#428nma");
                if ((i2 & 14) == 0) {
                    i3 = (composer.changed(items2) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & b.p) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if (((i3 & 731) ^ 146) == 0 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                } else {
                    itemContent.invoke(items2, Integer.valueOf(i), items.get(i), composer, Integer.valueOf(i3 & WebSocketProtocol.PAYLOAD_SHORT));
                }
            }
        }));
    }

    @ExperimentalFoundationApi
    public static final <T> void items(LazyGridScope lazyGridScope, final T[] items, final Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, ComposableLambdaKt.composableLambdaInstance(-985537534, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$items$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope items2, int i, Composer composer, int i2) {
                int i3;
                Intrinsics.checkNotNullParameter(items2, "$this$items");
                ComposerKt.sourceInformation(composer, "C163@5447L22:LazyGrid.kt#428nma");
                if ((i2 & 14) == 0) {
                    i3 = (composer.changed(items2) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & b.p) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if (((i3 & 731) ^ 146) == 0 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                } else {
                    itemContent.invoke(items2, items[i], composer, Integer.valueOf(i3 & 14));
                }
            }
        }));
    }

    @ExperimentalFoundationApi
    public static final <T> void itemsIndexed(LazyGridScope lazyGridScope, final T[] items, final Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, ComposableLambdaKt.composableLambdaInstance(-985536609, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$itemsIndexed$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope items2, int i, Composer composer, int i2) {
                int i3;
                Intrinsics.checkNotNullParameter(items2, "$this$items");
                ComposerKt.sourceInformation(composer, "C177@5858L26:LazyGrid.kt#428nma");
                if ((i2 & 14) == 0) {
                    i3 = (composer.changed(items2) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & b.p) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if (((i3 & 731) ^ 146) == 0 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                } else {
                    itemContent.invoke(items2, Integer.valueOf(i), items[i], composer, Integer.valueOf(i3 & WebSocketProtocol.PAYLOAD_SHORT));
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ExperimentalFoundationApi
    public static final void FixedLazyGrid(final int i, Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, final LazyGridScopeImpl lazyGridScopeImpl, Composer composer, final int i2, final int i3) {
        LazyListState lazyListState2;
        int i4;
        Composer startRestartGroup = composer.startRestartGroup(-902002143);
        ComposerKt.sourceInformation(startRestartGroup, "C(FixedLazyGrid)P(2,1,4)185@6035L23,190@6213L800:LazyGrid.kt#428nma");
        Modifier modifier2 = (i3 & 2) != 0 ? Modifier.INSTANCE : modifier;
        if ((i3 & 4) != 0) {
            i4 = i2 & (-897);
            lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
        } else {
            lazyListState2 = lazyListState;
            i4 = i2;
        }
        PaddingValues m308PaddingValues0680j_4 = (i3 & 8) != 0 ? PaddingKt.m308PaddingValues0680j_4(Dp.m3007constructorimpl(0)) : paddingValues;
        final int totalSize = ((lazyGridScopeImpl.getTotalSize() + i) - 1) / i;
        LazyDslKt.LazyColumn(modifier2, lazyListState2, m308PaddingValues0680j_4, false, null, null, null, new Function1<LazyListScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$FixedLazyGrid$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                invoke2(lazyListScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LazyListScope LazyColumn) {
                Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
                int i5 = totalSize;
                final int i6 = i;
                final LazyGridScopeImpl lazyGridScopeImpl2 = lazyGridScopeImpl;
                LazyListScope.DefaultImpls.items$default(LazyColumn, i5, null, ComposableLambdaKt.composableLambdaInstance(-985536118, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$FixedLazyGrid$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer2, Integer num2) {
                        invoke(lazyItemScope, num.intValue(), composer2, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LazyItemScope items, int i7, Composer composer2, int i8) {
                        int i9;
                        char c;
                        Intrinsics.checkNotNullParameter(items, "$this$items");
                        ComposerKt.sourceInformation(composer2, "C196@6371L626:LazyGrid.kt#428nma");
                        if ((i8 & 14) == 0) {
                            i9 = i8 | (composer2.changed(items) ? 4 : 2);
                        } else {
                            i9 = i8;
                        }
                        if ((i8 & b.p) == 0) {
                            i9 |= composer2.changed(i7) ? 32 : 16;
                        }
                        if (((i9 & 731) ^ 146) != 0 || !composer2.getSkipping()) {
                            int i10 = i6;
                            LazyGridScopeImpl lazyGridScopeImpl3 = lazyGridScopeImpl2;
                            composer2.startReplaceableGroup(-1989997546);
                            ComposerKt.sourceInformation(composer2, "C(Row)P(2,1,3)72@3453L58,73@3516L130:Row.kt#2w3rfo");
                            Modifier.Companion companion = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer2, 0);
                            int i11 = 1376089335;
                            composer2.startReplaceableGroup(1376089335);
                            ComposerKt.sourceInformation(composer2, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            int i12 = 103361330;
                            ComposerKt.sourceInformationMarkerStart(composer2, 103361330, "C:CompositionLocal.kt#9igjgp");
                            Object consume = composer2.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            Density density = (Density) consume;
                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                            ComposerKt.sourceInformationMarkerStart(composer2, 103361330, "C:CompositionLocal.kt#9igjgp");
                            Object consume2 = composer2.consume(localLayoutDirection);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            LayoutDirection layoutDirection = (LayoutDirection) consume2;
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf = LayoutKt.materializerOf(companion);
                            if (!(composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            composer2.disableReusing();
                            Composer m941constructorimpl = Updater.m941constructorimpl(composer2);
                            Updater.m948setimpl(m941constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m948setimpl(m941constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                            Updater.m948setimpl(m941constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                            composer2.enableReusing();
                            materializerOf.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(composer2)), composer2, 0);
                            composer2.startReplaceableGroup(2058660585);
                            composer2.startReplaceableGroup(-326682743);
                            ComposerKt.sourceInformation(composer2, "C74@3561L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            composer2.startReplaceableGroup(-1740946226);
                            ComposerKt.sourceInformation(composer2, "C:LazyGrid.kt#428nma");
                            if (i10 > 0) {
                                int i13 = 0;
                                while (true) {
                                    int i14 = i13 + 1;
                                    int i15 = (i7 * i10) + i13;
                                    if (i15 < lazyGridScopeImpl3.getTotalSize()) {
                                        composer2.startReplaceableGroup(-1740946063);
                                        ComposerKt.sourceInformation(composer2, "200@6582L267");
                                        Modifier weight = rowScopeInstance.weight(Modifier.INSTANCE, 1.0f, true);
                                        composer2.startReplaceableGroup(-1990474327);
                                        ComposerKt.sourceInformation(composer2, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                        MeasurePolicy rememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true, composer2, 48);
                                        composer2.startReplaceableGroup(i11);
                                        ComposerKt.sourceInformation(composer2, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                                        ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                        ComposerKt.sourceInformationMarkerStart(composer2, i12, "C:CompositionLocal.kt#9igjgp");
                                        Object consume3 = composer2.consume(localDensity2);
                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                        Density density2 = (Density) consume3;
                                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                                        ComposerKt.sourceInformationMarkerStart(composer2, i12, "C:CompositionLocal.kt#9igjgp");
                                        Object consume4 = composer2.consume(localLayoutDirection2);
                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                        LayoutDirection layoutDirection2 = (LayoutDirection) consume4;
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf2 = LayoutKt.materializerOf(weight);
                                        if (!(composer2.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer2.startReusableNode();
                                        if (composer2.getInserting()) {
                                            composer2.createNode(constructor2);
                                        } else {
                                            composer2.useNode();
                                        }
                                        composer2.disableReusing();
                                        Composer m941constructorimpl2 = Updater.m941constructorimpl(composer2);
                                        Updater.m948setimpl(m941constructorimpl2, rememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m948setimpl(m941constructorimpl2, density2, ComposeUiNode.INSTANCE.getSetDensity());
                                        Updater.m948setimpl(m941constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                        composer2.enableReusing();
                                        materializerOf2.invoke(SkippableUpdater.m932boximpl(SkippableUpdater.m933constructorimpl(composer2)), composer2, 0);
                                        c = 43753;
                                        composer2.startReplaceableGroup(2058660585);
                                        composer2.startReplaceableGroup(-1253629305);
                                        ComposerKt.sourceInformation(composer2, "C72@3384L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        composer2.startReplaceableGroup(1171140845);
                                        ComposerKt.sourceInformation(composer2, "C204@6815L8:LazyGrid.kt#428nma");
                                        lazyGridScopeImpl3.contentFor(i15, items).invoke(composer2, 0);
                                        composer2.endReplaceableGroup();
                                        composer2.endReplaceableGroup();
                                        composer2.endReplaceableGroup();
                                        composer2.endNode();
                                        composer2.endReplaceableGroup();
                                        composer2.endReplaceableGroup();
                                        composer2.endReplaceableGroup();
                                    } else {
                                        c = 43753;
                                        composer2.startReplaceableGroup(-1740945742);
                                        ComposerKt.sourceInformation(composer2, "207@6903L40");
                                        SpacerKt.Spacer(rowScopeInstance.weight(Modifier.INSTANCE, 1.0f, true), composer2, 0);
                                        composer2.endReplaceableGroup();
                                    }
                                    if (i14 >= i10) {
                                        break;
                                    }
                                    i13 = i14;
                                    i11 = 1376089335;
                                    i12 = 103361330;
                                }
                            }
                            composer2.endReplaceableGroup();
                            composer2.endReplaceableGroup();
                            composer2.endReplaceableGroup();
                            composer2.endNode();
                            composer2.endReplaceableGroup();
                            composer2.endReplaceableGroup();
                            return;
                        }
                        composer2.skipToGroupEnd();
                    }
                }), 2, null);
            }
        }, startRestartGroup, (i4 >> 3) & 1022, 120);
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        final Modifier modifier3 = modifier2;
        final LazyListState lazyListState3 = lazyListState2;
        final PaddingValues paddingValues2 = m308PaddingValues0680j_4;
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyGridKt$FixedLazyGrid$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i5) {
                LazyGridKt.FixedLazyGrid(i, modifier3, lazyListState3, paddingValues2, lazyGridScopeImpl, composer2, i2 | 1, i3);
            }
        });
    }
}
