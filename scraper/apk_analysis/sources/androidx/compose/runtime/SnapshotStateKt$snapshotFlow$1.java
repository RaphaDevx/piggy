package androidx.compose.runtime;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SnapshotState.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt$snapshotFlow$1", f = "SnapshotState.kt", i = {0, 1, 1}, l = {868, 872, 894}, m = "invokeSuspend", n = {"lastValue", "lastValue", "found"}, s = {"L$5", "L$5", "I$0"})
/* loaded from: classes.dex */
final class SnapshotStateKt$snapshotFlow$1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<T> $block;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SnapshotStateKt$snapshotFlow$1(Function0<? extends T> function0, Continuation<? super SnapshotStateKt$snapshotFlow$1> continuation) {
        super(2, continuation);
        this.$block = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SnapshotStateKt$snapshotFlow$1 snapshotStateKt$snapshotFlow$1 = new SnapshotStateKt$snapshotFlow$1(this.$block, continuation);
        snapshotStateKt$snapshotFlow$1.L$0 = obj;
        return snapshotStateKt$snapshotFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((SnapshotStateKt$snapshotFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00e2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ee A[Catch: all -> 0x0052, TryCatch #1 {all -> 0x0052, blocks: (B:14:0x00ea, B:16:0x00ee, B:20:0x00f8, B:24:0x0106, B:30:0x011c, B:32:0x0125, B:42:0x0143, B:43:0x0146, B:57:0x004d, B:26:0x0111, B:29:0x0119, B:38:0x013e, B:39:0x0141, B:28:0x0115), top: B:56:0x004d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0106 A[Catch: all -> 0x0052, TRY_LEAVE, TryCatch #1 {all -> 0x0052, blocks: (B:14:0x00ea, B:16:0x00ee, B:20:0x00f8, B:24:0x0106, B:30:0x011c, B:32:0x0125, B:42:0x0143, B:43:0x0146, B:57:0x004d, B:26:0x0111, B:29:0x0119, B:38:0x013e, B:39:0x0141, B:28:0x0115), top: B:56:0x004d, inners: #0 }] */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt$snapshotFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
