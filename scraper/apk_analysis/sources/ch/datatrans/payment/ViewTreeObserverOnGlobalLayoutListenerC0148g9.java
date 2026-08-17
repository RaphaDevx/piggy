package ch.datatrans.payment;

import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.g9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class ViewTreeObserverOnGlobalLayoutListenerC0148g9 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ p9 a;

    public ViewTreeObserverOnGlobalLayoutListenerC0148g9(p9 p9Var) {
        this.a = p9Var;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        List values = ((E9) this.a.b.getValue()).a(((E9) this.a.b.getValue()).a.j, ((Boolean) this.a.f.getValue()).booleanValue());
        C0254x9 c0254x9 = this.a.d;
        RecyclerView recyclerView = null;
        if (c0254x9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            c0254x9 = null;
        }
        boolean z = !((Boolean) this.a.f.getValue()).booleanValue();
        c0254x9.getClass();
        Intrinsics.checkNotNullParameter(values, "values");
        c0254x9.c = z;
        c0254x9.a = values;
        c0254x9.notifyDataSetChanged();
        RecyclerView recyclerView2 = this.a.e;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
