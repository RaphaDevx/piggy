package ch.datatrans.payment;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.r9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0209r9 extends RecyclerView.ViewHolder {
    public final View a;
    public final View b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0209r9(final C0254x9 c0254x9, View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.pay_with_google_pay_button);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        View findViewById2 = view.findViewById(R.id.pay_with_google_pay_text_top);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.a = findViewById2;
        View findViewById3 = view.findViewById(R.id.pay_with_google_pay_text_bottom);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.b = findViewById3;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.r9$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                C0209r9.a(C0254x9.this, this, view2);
            }
        });
    }

    public static final Unit a(C0254x9 c0254x9, C0209r9 c0209r9) {
        ((AbstractC0135e9) c0254x9.a.get(c0209r9.getAdapterPosition())).a.invoke();
        return Unit.INSTANCE;
    }

    public static final void a(final C0254x9 c0254x9, final C0209r9 c0209r9, View view) {
        Function0 action = new Function0() { // from class: ch.datatrans.payment.r9$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0209r9.a(C0254x9.this, c0209r9);
            }
        };
        c0254x9.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        if (G9.a.compareAndSet(false, true)) {
            action.invoke();
        }
    }
}
