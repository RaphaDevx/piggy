package ch.datatrans.payment;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.t9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0222t9 extends RecyclerView.ViewHolder {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0222t9(final C0254x9 c0254x9, View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.t9$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                C0222t9.a(C0254x9.this, view2);
            }
        });
    }

    public static final Unit a(C0254x9 c0254x9) {
        Function0 function0 = c0254x9.b;
        Intrinsics.checkNotNull(function0);
        function0.invoke();
        return Unit.INSTANCE;
    }

    public static final void a(final C0254x9 c0254x9, View view) {
        Function0 action = new Function0() { // from class: ch.datatrans.payment.t9$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0222t9.a(C0254x9.this);
            }
        };
        c0254x9.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        if (G9.a.compareAndSet(false, true)) {
            action.invoke();
        }
    }
}
