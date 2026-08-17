package androidx.compose.foundation.text.selection;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: TextSelectionMouseDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2", f = "TextSelectionMouseDetector.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextSelectionMouseDetectorKt$mouseSelectionDetector$2 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $finalPass;
    final /* synthetic */ MouseSelectionObserver $observer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextSelectionMouseDetectorKt$mouseSelectionDetector$2(boolean z, MouseSelectionObserver mouseSelectionObserver, Continuation<? super TextSelectionMouseDetectorKt$mouseSelectionDetector$2> continuation) {
        super(2, continuation);
        this.$finalPass = z;
        this.$observer = mouseSelectionObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextSelectionMouseDetectorKt$mouseSelectionDetector$2 textSelectionMouseDetectorKt$mouseSelectionDetector$2 = new TextSelectionMouseDetectorKt$mouseSelectionDetector$2(this.$finalPass, this.$observer, continuation);
        textSelectionMouseDetectorKt$mouseSelectionDetector$2.L$0 = obj;
        return textSelectionMouseDetectorKt$mouseSelectionDetector$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TextSelectionMouseDetectorKt$mouseSelectionDetector$2) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: TextSelectionMouseDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1", f = "TextSelectionMouseDetector.kt", i = {0, 0, 1, 1, 2, 2}, l = {92, 99, 114}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "clicksCounter", "$this$awaitPointerEventScope", "clicksCounter", "$this$awaitPointerEventScope", "clicksCounter"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
    /* renamed from: androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $finalPass;
        final /* synthetic */ MouseSelectionObserver $observer;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, MouseSelectionObserver mouseSelectionObserver, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$finalPass = z;
            this.$observer = mouseSelectionObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$finalPass, this.$observer, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0054 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x009a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0078 -> B:10:0x00d6). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0097 -> B:10:0x00d6). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00b4 -> B:10:0x00d6). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00d3 -> B:10:0x00d6). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L33
                if (r1 == r4) goto L27
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                goto L1a
            L12:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L1a:
                java.lang.Object r1 = r11.L$1
                androidx.compose.foundation.text.selection.ClicksCounter r1 = (androidx.compose.foundation.text.selection.ClicksCounter) r1
                java.lang.Object r5 = r11.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                kotlin.ResultKt.throwOnFailure(r12)
                goto Ld6
            L27:
                java.lang.Object r1 = r11.L$1
                androidx.compose.foundation.text.selection.ClicksCounter r1 = (androidx.compose.foundation.text.selection.ClicksCounter) r1
                java.lang.Object r5 = r11.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                kotlin.ResultKt.throwOnFailure(r12)
                goto L58
            L33:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r12 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r12
                androidx.compose.foundation.text.selection.ClicksCounter r1 = new androidx.compose.foundation.text.selection.ClicksCounter
                androidx.compose.ui.platform.ViewConfiguration r5 = r12.getViewConfiguration()
                r1.<init>(r5)
            L43:
                boolean r5 = r11.$finalPass
                r6 = r11
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r11.L$0 = r12
                r11.L$1 = r1
                r11.label = r4
                java.lang.Object r5 = androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt.access$awaitMouseEventDown(r12, r5, r6)
                if (r5 != r0) goto L55
                return r0
            L55:
                r10 = r5
                r5 = r12
                r12 = r10
            L58:
                androidx.compose.ui.input.pointer.PointerEvent r12 = (androidx.compose.ui.input.pointer.PointerEvent) r12
                r1.update(r12)
                java.util.List r6 = r12.getChanges()
                r7 = 0
                java.lang.Object r6 = r6.get(r7)
                androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
                boolean r12 = androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt.isShiftPressed(r12)
                if (r12 == 0) goto L9a
                androidx.compose.foundation.text.selection.MouseSelectionObserver r12 = r11.$observer
                long r7 = r6.getPosition()
                boolean r12 = r12.mo497onExtendk4lQ0M(r7)
                if (r12 == 0) goto Ld6
                androidx.compose.ui.input.pointer.PointerEventKt.consumeDownChange(r6)
                long r6 = r6.getId()
                androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1$1 r12 = new androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1$1
                androidx.compose.foundation.text.selection.MouseSelectionObserver r8 = r11.$observer
                r12.<init>()
                kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
                r8 = r11
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r11.L$0 = r5
                r11.L$1 = r1
                r11.label = r3
                java.lang.Object r12 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m194dragjO51t88(r5, r6, r12, r8)
                if (r12 != r0) goto Ld6
                return r0
            L9a:
                int r12 = r1.getClicks()
                if (r12 == r4) goto La8
                if (r12 == r3) goto La5
                androidx.compose.foundation.text.selection.SelectionAdjustment r12 = androidx.compose.foundation.text.selection.SelectionAdjustment.PARAGRAPH
                goto Laa
            La5:
                androidx.compose.foundation.text.selection.SelectionAdjustment r12 = androidx.compose.foundation.text.selection.SelectionAdjustment.WORD
                goto Laa
            La8:
                androidx.compose.foundation.text.selection.SelectionAdjustment r12 = androidx.compose.foundation.text.selection.SelectionAdjustment.NONE
            Laa:
                androidx.compose.foundation.text.selection.MouseSelectionObserver r7 = r11.$observer
                long r8 = r6.getPosition()
                boolean r7 = r7.mo499onStart3MmeM6k(r8, r12)
                if (r7 == 0) goto Ld6
                androidx.compose.ui.input.pointer.PointerEventKt.consumeDownChange(r6)
                long r6 = r6.getId()
                androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1$2 r8 = new androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2$1$2
                androidx.compose.foundation.text.selection.MouseSelectionObserver r9 = r11.$observer
                r8.<init>()
                kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
                r12 = r11
                kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
                r11.L$0 = r5
                r11.L$1 = r1
                r11.label = r2
                java.lang.Object r12 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m194dragjO51t88(r5, r6, r8, r12)
                if (r12 != r0) goto Ld6
                return r0
            Ld6:
                r12 = r5
                goto L43
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextSelectionMouseDetectorKt$mouseSelectionDetector$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (((PointerInputScope) this.L$0).awaitPointerEventScope(new AnonymousClass1(this.$finalPass, this.$observer, null), this) == coroutine_suspended) {
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
