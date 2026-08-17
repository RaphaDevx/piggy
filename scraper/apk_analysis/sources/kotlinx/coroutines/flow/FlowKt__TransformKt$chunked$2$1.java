package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

/* compiled from: Transform.kt */
@Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes4.dex */
final class FlowKt__TransformKt$chunked$2$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<ArrayList<T>> $result;
    final /* synthetic */ int $size;
    final /* synthetic */ FlowCollector<List<? extends T>> $this_flow;

    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__TransformKt$chunked$2$1(Ref.ObjectRef<ArrayList<T>> objectRef, int i, FlowCollector<? super List<? extends T>> flowCollector) {
        this.$result = objectRef;
        this.$size = i;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(T r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1
            if (r0 == 0) goto L14
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1$emit$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1 r4 = (kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L64
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r6 = r4.$result
            T r6 = r6.element
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            if (r6 != 0) goto L4c
            java.util.ArrayList r6 = new java.util.ArrayList
            int r2 = r4.$size
            r6.<init>(r2)
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r2 = r4.$result
            r2.element = r6
        L4c:
            r6.add(r5)
            int r5 = r6.size()
            int r2 = r4.$size
            if (r5 != r2) goto L69
            kotlinx.coroutines.flow.FlowCollector<java.util.List<? extends T>> r5 = r4.$this_flow
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.emit(r6, r0)
            if (r5 != r1) goto L64
            return r1
        L64:
            kotlin.jvm.internal.Ref$ObjectRef<java.util.ArrayList<T>> r4 = r4.$result
            r5 = 0
            r4.element = r5
        L69:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$chunked$2$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
