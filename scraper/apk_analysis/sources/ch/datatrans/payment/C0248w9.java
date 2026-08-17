package ch.datatrans.payment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.w9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0248w9 extends RecyclerView.ViewHolder {
    public final ImageView a;
    public final TextView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0248w9(final C0254x9 c0254x9, View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.icon);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (ImageView) findViewById;
        View findViewById2 = view.findViewById(R.id.text);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.b = (TextView) findViewById2;
        view.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.w9$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                C0248w9.a(C0254x9.this, this, view2);
            }
        });
        ViewCompat.setAccessibilityDelegate(view, new C0241v9());
    }

    public static final Unit a(C0254x9 c0254x9, C0248w9 c0248w9) {
        ((AbstractC0135e9) c0254x9.a.get(c0248w9.getAdapterPosition())).a.invoke();
        return Unit.INSTANCE;
    }

    public static final void a(final C0254x9 c0254x9, final C0248w9 c0248w9, View view) {
        Function0 action = new Function0() { // from class: ch.datatrans.payment.w9$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0248w9.a(C0254x9.this, c0248w9);
            }
        };
        c0254x9.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        if (G9.a.compareAndSet(false, true)) {
            action.invoke();
        }
    }
}
