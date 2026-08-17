package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TapGestureEvent;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/gestures/TapGestureEvent$Down;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelSecondDown$2", f = "TapGestureDetector.kt", i = {0}, l = {246}, m = "invokeSuspend", n = {"minUptime"}, s = {"J$0"})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$awaitChannelSecondDown$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TapGestureEvent.Down>, Object> {
    final /* synthetic */ ReceiveChannel<TapGestureEvent> $channel;
    final /* synthetic */ TapGestureEvent.Up $firstUp;
    final /* synthetic */ MutableState<Boolean> $onlyDownsSignal;
    final /* synthetic */ ViewConfiguration $viewConfiguration;
    long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TapGestureDetectorKt$awaitChannelSecondDown$2(TapGestureEvent.Up up, ViewConfiguration viewConfiguration, MutableState<Boolean> mutableState, ReceiveChannel<? extends TapGestureEvent> receiveChannel, Continuation<? super TapGestureDetectorKt$awaitChannelSecondDown$2> continuation) {
        super(2, continuation);
        this.$firstUp = up;
        this.$viewConfiguration = viewConfiguration;
        this.$onlyDownsSignal = mutableState;
        this.$channel = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TapGestureDetectorKt$awaitChannelSecondDown$2(this.$firstUp, this.$viewConfiguration, this.$onlyDownsSignal, this.$channel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TapGestureEvent.Down> continuation) {
        return ((TapGestureDetectorKt$awaitChannelSecondDown$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0038 -> B:5:0x003b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L19
            if (r1 != r2) goto L11
            long r3 = r7.J$0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L3b
        L11:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L19:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.foundation.gestures.TapGestureEvent$Up r8 = r7.$firstUp
            long r3 = r8.getUptimeMillis()
            androidx.compose.ui.platform.ViewConfiguration r8 = r7.$viewConfiguration
            long r5 = r8.getDoubleTapMinTimeMillis()
            long r3 = r3 + r5
        L29:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r8 = r7.$onlyDownsSignal
            kotlinx.coroutines.channels.ReceiveChannel<androidx.compose.foundation.gestures.TapGestureEvent> r1 = r7.$channel
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.J$0 = r3
            r7.label = r2
            java.lang.Object r8 = androidx.compose.foundation.gestures.TapGestureDetectorKt.access$awaitChannelDown(r8, r1, r5)
            if (r8 != r0) goto L3b
            return r0
        L3b:
            androidx.compose.foundation.gestures.TapGestureEvent$Down r8 = (androidx.compose.foundation.gestures.TapGestureEvent.Down) r8
            long r5 = r8.getUptimeMillis()
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 < 0) goto L29
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitChannelSecondDown$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
