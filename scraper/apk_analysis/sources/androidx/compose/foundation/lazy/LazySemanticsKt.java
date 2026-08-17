package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazySemantics.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"lazyListSemantics", "Landroidx/compose/ui/Modifier;", "stateOfItemsProvider", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", SentryThread.JsonKeys.STATE, "Landroidx/compose/foundation/lazy/LazyListState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isVertical", "", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazySemanticsKt {
    public static final Modifier lazyListSemantics(Modifier modifier, final State<? extends LazyListItemsProvider> stateOfItemsProvider, final LazyListState state, final CoroutineScope coroutineScope, final boolean z) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(stateOfItemsProvider, "stateOfItemsProvider");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        return SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                final State<LazyListItemsProvider> state2 = stateOfItemsProvider;
                SemanticsPropertiesKt.indexForKey(semantics, new Function1<Object, Integer>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
                        return Integer.valueOf(invoke2(obj));
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final int invoke2(Object needle) {
                        Intrinsics.checkNotNullParameter(needle, "needle");
                        LazySemanticsKt$lazyListSemantics$1$1$key$1 lazySemanticsKt$lazyListSemantics$1$1$key$1 = new LazySemanticsKt$lazyListSemantics$1$1$key$1(state2.getValue());
                        int itemsCount = state2.getValue().getItemsCount();
                        if (itemsCount <= 0) {
                            return -1;
                        }
                        int i = 0;
                        while (true) {
                            int i2 = i + 1;
                            if (Intrinsics.areEqual(lazySemanticsKt$lazyListSemantics$1$1$key$1.invoke((LazySemanticsKt$lazyListSemantics$1$1$key$1) Integer.valueOf(i)), needle)) {
                                return i;
                            }
                            if (i2 >= itemsCount) {
                                return -1;
                            }
                            i = i2;
                        }
                    }
                });
                final LazyListState lazyListState = state;
                ScrollAxisRange scrollAxisRange = new ScrollAxisRange(new Function0<Float>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$accessibilityScrollState$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Float invoke() {
                        return Float.valueOf(invoke2());
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final float invoke2() {
                        return LazyListState.this.getFirstVisibleItemIndex() + (LazyListState.this.getFirstVisibleItemScrollOffset() / 100000.0f);
                    }
                }, new Function0<Float>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$accessibilityScrollState$2
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final float invoke2() {
                        return Float.POSITIVE_INFINITY;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Float invoke() {
                        return Float.valueOf(invoke2());
                    }
                }, false, 4, null);
                if (z) {
                    SemanticsPropertiesKt.setVerticalScrollAxisRange(semantics, scrollAxisRange);
                } else {
                    SemanticsPropertiesKt.setHorizontalScrollAxisRange(semantics, scrollAxisRange);
                }
                final boolean z2 = z;
                final CoroutineScope coroutineScope2 = coroutineScope;
                final LazyListState lazyListState2 = state;
                SemanticsPropertiesKt.scrollBy$default(semantics, null, new Function2<Float, Float, Boolean>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f, Float f2) {
                        return Boolean.valueOf(invoke(f.floatValue(), f2.floatValue()));
                    }

                    /* compiled from: LazySemantics.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$2$1", f = "LazySemantics.kt", i = {}, l = {71}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$2$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ float $delta;
                        final /* synthetic */ LazyListState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(LazyListState lazyListState, float f, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$state = lazyListState;
                            this.$delta = f;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$state, this.$delta, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (ScrollExtensionsKt.scrollBy(this.$state, this.$delta, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    public final boolean invoke(float f, float f2) {
                        if (z2) {
                            f = f2;
                        }
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(lazyListState2, f, null), 3, null);
                        return true;
                    }
                }, 1, null);
                final State<LazyListItemsProvider> state3 = stateOfItemsProvider;
                final CoroutineScope coroutineScope3 = coroutineScope;
                final LazyListState lazyListState3 = state;
                SemanticsPropertiesKt.scrollToIndex$default(semantics, null, new Function1<Integer, Boolean>() { // from class: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
                        return Boolean.valueOf(invoke(num.intValue()));
                    }

                    public final boolean invoke(int i) {
                        boolean z3 = i >= 0 && i < state3.getValue().getItemsCount();
                        State<LazyListItemsProvider> state4 = state3;
                        if (z3) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass2(lazyListState3, i, null), 3, null);
                            return true;
                        }
                        throw new IllegalArgumentException(("Can't scroll to index " + i + ", it is out of bounds [0, " + state4.getValue().getItemsCount() + ')').toString());
                    }

                    /* compiled from: LazySemantics.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$3$2", f = "LazySemantics.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.foundation.lazy.LazySemanticsKt$lazyListSemantics$1$3$2, reason: invalid class name */
                    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ int $index;
                        final /* synthetic */ LazyListState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass2(LazyListState lazyListState, int i, Continuation<? super AnonymousClass2> continuation) {
                            super(2, continuation);
                            this.$state = lazyListState;
                            this.$index = i;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass2(this.$state, this.$index, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (LazyListState.scrollToItem$default(this.$state, this.$index, 0, this, 2, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, 1, null);
                boolean z3 = z;
                SemanticsPropertiesKt.setCollectionInfo(semantics, new CollectionInfo(z3 ? -1 : 1, z3 ? 1 : -1));
            }
        }, 1, null);
    }
}
