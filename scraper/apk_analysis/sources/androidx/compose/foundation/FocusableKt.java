package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Focusable.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"focusable", "Landroidx/compose/ui/Modifier;", ExtentionsKt.ENABLED_KEY, "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FocusableKt {
    public static /* synthetic */ Modifier focusable$default(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            mutableInteractionSource = null;
        }
        return focusable(modifier, z, mutableInteractionSource);
    }

    public static final Modifier focusable(Modifier modifier, final boolean z, final MutableInteractionSource mutableInteractionSource) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.FocusableKt$focusable$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("focusable");
                inspectorInfo.getProperties().set(ExtentionsKt.ENABLED_KEY, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.FocusableKt$focusable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Modifier.Companion companion;
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(1407538239);
                ComposerKt.sourceInformation(composer, "C56@2216L24,57@2270L58,58@2350L34,59@2389L316,68@2710L390:Focusable.kt#71ulvw");
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
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue2 = composer.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composer.updateRememberedValue(rememberedValue2);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState = (MutableState) rememberedValue2;
                composer.startReplaceableGroup(-3687241);
                ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
                Object rememberedValue3 = composer.rememberedValue();
                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    composer.updateRememberedValue(rememberedValue3);
                }
                composer.endReplaceableGroup();
                final MutableState mutableState2 = (MutableState) rememberedValue3;
                MutableInteractionSource mutableInteractionSource2 = MutableInteractionSource.this;
                final MutableInteractionSource mutableInteractionSource3 = MutableInteractionSource.this;
                EffectsKt.DisposableEffect(mutableInteractionSource2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.FocusableKt$focusable$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        final MutableState<FocusInteraction.Focus> mutableState3 = mutableState;
                        final MutableInteractionSource mutableInteractionSource4 = mutableInteractionSource3;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.FocusableKt$focusable$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                FocusInteraction.Focus focus = (FocusInteraction.Focus) MutableState.this.getValue();
                                if (focus == null) {
                                    return;
                                }
                                FocusInteraction.Unfocus unfocus = new FocusInteraction.Unfocus(focus);
                                MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource4;
                                if (mutableInteractionSource5 != null) {
                                    mutableInteractionSource5.tryEmit(unfocus);
                                }
                                MutableState.this.setValue(null);
                            }
                        };
                    }
                }, composer, 0);
                Boolean valueOf = Boolean.valueOf(z);
                final boolean z2 = z;
                final MutableInteractionSource mutableInteractionSource4 = MutableInteractionSource.this;
                EffectsKt.DisposableEffect(valueOf, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.FocusableKt$focusable$2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                        if (!z2) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(mutableState, mutableInteractionSource4, null), 3, null);
                        }
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.FocusableKt$focusable$2$2$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                            }
                        };
                    }

                    /* compiled from: Focusable.kt */
                    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                    @DebugMetadata(c = "androidx.compose.foundation.FocusableKt$focusable$2$2$1", f = "Focusable.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.foundation.FocusableKt$focusable$2$2$1, reason: invalid class name */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ MutableState<FocusInteraction.Focus> $focusedInteraction;
                        final /* synthetic */ MutableInteractionSource $interactionSource;
                        Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(MutableState<FocusInteraction.Focus> mutableState, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$focusedInteraction = mutableState;
                            this.$interactionSource = mutableInteractionSource;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$focusedInteraction, this.$interactionSource, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            MutableState<FocusInteraction.Focus> mutableState;
                            MutableState<FocusInteraction.Focus> mutableState2;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                FocusInteraction.Focus value = this.$focusedInteraction.getValue();
                                if (value != null) {
                                    MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                                    mutableState = this.$focusedInteraction;
                                    FocusInteraction.Unfocus unfocus = new FocusInteraction.Unfocus(value);
                                    if (mutableInteractionSource != null) {
                                        this.L$0 = mutableState;
                                        this.label = 1;
                                        if (mutableInteractionSource.emit(unfocus, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        mutableState2 = mutableState;
                                    }
                                    mutableState.setValue(null);
                                }
                                return Unit.INSTANCE;
                            }
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            mutableState2 = (MutableState) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            mutableState = mutableState2;
                            mutableState.setValue(null);
                            return Unit.INSTANCE;
                        }
                    }
                }, composer, 0);
                if (z) {
                    Modifier semantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.FocusableKt$focusable$2.3
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
                            SemanticsPropertiesKt.setFocused(semantics, FocusableKt$focusable$2.m178invoke$lambda2(mutableState2));
                        }
                    }, 1, null);
                    final MutableInteractionSource mutableInteractionSource5 = MutableInteractionSource.this;
                    companion = FocusModifierKt.focusTarget(FocusChangedModifierKt.onFocusChanged(semantics$default, new Function1<FocusState, Unit>() { // from class: androidx.compose.foundation.FocusableKt$focusable$2.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(FocusState focusState) {
                            invoke2(focusState);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(FocusState it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            FocusableKt$focusable$2.m179invoke$lambda3(mutableState2, it.isFocused());
                            if (FocusableKt$focusable$2.m178invoke$lambda2(mutableState2)) {
                                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(mutableState, mutableInteractionSource5, null), 3, null);
                            } else {
                                BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass2(mutableState, mutableInteractionSource5, null), 3, null);
                            }
                        }

                        /* compiled from: Focusable.kt */
                        @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.foundation.FocusableKt$focusable$2$4$1", f = "Focusable.kt", i = {1}, l = {93, 97}, m = "invokeSuspend", n = {DataSources.EventTypeValue.INTERACTION_EVENT_TYPE}, s = {"L$0"})
                        /* renamed from: androidx.compose.foundation.FocusableKt$focusable$2$4$1, reason: invalid class name */
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ MutableState<FocusInteraction.Focus> $focusedInteraction;
                            final /* synthetic */ MutableInteractionSource $interactionSource;
                            Object L$0;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(MutableState<FocusInteraction.Focus> mutableState, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$focusedInteraction = mutableState;
                                this.$interactionSource = mutableInteractionSource;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$focusedInteraction, this.$interactionSource, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                                /*
                                    r6 = this;
                                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                    int r1 = r6.label
                                    r2 = 2
                                    r3 = 1
                                    if (r1 == 0) goto L26
                                    if (r1 == r3) goto L1e
                                    if (r1 != r2) goto L16
                                    java.lang.Object r0 = r6.L$0
                                    androidx.compose.foundation.interaction.FocusInteraction$Focus r0 = (androidx.compose.foundation.interaction.FocusInteraction.Focus) r0
                                    kotlin.ResultKt.throwOnFailure(r7)
                                    goto L6f
                                L16:
                                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                                    r6.<init>(r7)
                                    throw r6
                                L1e:
                                    java.lang.Object r1 = r6.L$0
                                    androidx.compose.runtime.MutableState r1 = (androidx.compose.runtime.MutableState) r1
                                    kotlin.ResultKt.throwOnFailure(r7)
                                    goto L4e
                                L26:
                                    kotlin.ResultKt.throwOnFailure(r7)
                                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.FocusInteraction$Focus> r7 = r6.$focusedInteraction
                                    java.lang.Object r7 = r7.getValue()
                                    androidx.compose.foundation.interaction.FocusInteraction$Focus r7 = (androidx.compose.foundation.interaction.FocusInteraction.Focus) r7
                                    if (r7 != 0) goto L34
                                    goto L53
                                L34:
                                    androidx.compose.foundation.interaction.MutableInteractionSource r1 = r6.$interactionSource
                                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.FocusInteraction$Focus> r4 = r6.$focusedInteraction
                                    androidx.compose.foundation.interaction.FocusInteraction$Unfocus r5 = new androidx.compose.foundation.interaction.FocusInteraction$Unfocus
                                    r5.<init>(r7)
                                    if (r1 != 0) goto L40
                                    goto L4f
                                L40:
                                    androidx.compose.foundation.interaction.Interaction r5 = (androidx.compose.foundation.interaction.Interaction) r5
                                    r6.L$0 = r4
                                    r6.label = r3
                                    java.lang.Object r7 = r1.emit(r5, r6)
                                    if (r7 != r0) goto L4d
                                    return r0
                                L4d:
                                    r1 = r4
                                L4e:
                                    r4 = r1
                                L4f:
                                    r7 = 0
                                    r4.setValue(r7)
                                L53:
                                    androidx.compose.foundation.interaction.FocusInteraction$Focus r7 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
                                    r7.<init>()
                                    androidx.compose.foundation.interaction.MutableInteractionSource r1 = r6.$interactionSource
                                    if (r1 != 0) goto L5d
                                    goto L70
                                L5d:
                                    r3 = r7
                                    androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
                                    r4 = r6
                                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                                    r6.L$0 = r7
                                    r6.label = r2
                                    java.lang.Object r1 = r1.emit(r3, r4)
                                    if (r1 != r0) goto L6e
                                    return r0
                                L6e:
                                    r0 = r7
                                L6f:
                                    r7 = r0
                                L70:
                                    androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.FocusInteraction$Focus> r6 = r6.$focusedInteraction
                                    r6.setValue(r7)
                                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                                    return r6
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.FocusableKt$focusable$2.AnonymousClass4.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        /* compiled from: Focusable.kt */
                        @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.foundation.FocusableKt$focusable$2$4$2", f = "Focusable.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: androidx.compose.foundation.FocusableKt$focusable$2$4$2, reason: invalid class name */
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ MutableState<FocusInteraction.Focus> $focusedInteraction;
                            final /* synthetic */ MutableInteractionSource $interactionSource;
                            Object L$0;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(MutableState<FocusInteraction.Focus> mutableState, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$focusedInteraction = mutableState;
                                this.$interactionSource = mutableInteractionSource;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$focusedInteraction, this.$interactionSource, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                MutableState<FocusInteraction.Focus> mutableState;
                                MutableState<FocusInteraction.Focus> mutableState2;
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    FocusInteraction.Focus value = this.$focusedInteraction.getValue();
                                    if (value != null) {
                                        MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                                        mutableState = this.$focusedInteraction;
                                        FocusInteraction.Unfocus unfocus = new FocusInteraction.Unfocus(value);
                                        if (mutableInteractionSource != null) {
                                            this.L$0 = mutableState;
                                            this.label = 1;
                                            if (mutableInteractionSource.emit(unfocus, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            mutableState2 = mutableState;
                                        }
                                        mutableState.setValue(null);
                                    }
                                    return Unit.INSTANCE;
                                }
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                mutableState2 = (MutableState) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                mutableState = mutableState2;
                                mutableState.setValue(null);
                                return Unit.INSTANCE;
                            }
                        }
                    }));
                } else {
                    companion = Modifier.INSTANCE;
                }
                composer.endReplaceableGroup();
                return companion;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: invoke$lambda-2, reason: not valid java name */
            public static final boolean m178invoke$lambda2(MutableState<Boolean> mutableState) {
                return mutableState.getValue().booleanValue();
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: invoke$lambda-3, reason: not valid java name */
            public static final void m179invoke$lambda3(MutableState<Boolean> mutableState, boolean z2) {
                mutableState.setValue(Boolean.valueOf(z2));
            }
        });
    }
}
