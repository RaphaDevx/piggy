package ch.datatrans.payment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.x9, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0254x9 extends RecyclerView.Adapter {
    public List a;
    public Function0 b;
    public boolean c;

    public C0254x9(List values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.a = values;
        this.c = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.b != null ? this.a.size() + 1 : this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (this.b != null && i == this.a.size()) {
            return 1;
        }
        if (this.a.get(i) instanceof C0179m5) {
            return 2;
        }
        return super.getItemViewType(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof C0248w9) {
            Object obj = this.a.get(i);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type ch.datatrans.payment.methodselection.DefaultPaymentMethodModel");
            C0162j4 c0162j4 = (C0162j4) obj;
            C0248w9 c0248w9 = (C0248w9) holder;
            c0248w9.a.setImageResource(c0162j4.c);
            x4.a(c0248w9.b, c0162j4.b);
            c0248w9.b.setContentDescription(c0162j4.d);
            return;
        }
        if (holder instanceof C0209r9) {
            if (this.c) {
                C0209r9 c0209r9 = (C0209r9) holder;
                c0209r9.a.setVisibility(0);
                c0209r9.b.setVisibility(8);
            } else {
                C0209r9 c0209r92 = (C0209r9) holder;
                c0209r92.a.setVisibility(8);
                c0209r92.b.setVisibility(0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i == 1) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dtpl_other_payment_method_item, parent, false);
            Intrinsics.checkNotNull(inflate);
            return new C0222t9(this, inflate);
        }
        if (i == 2) {
            View inflate2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.dtpl_google_pay_native_item, parent, false);
            Intrinsics.checkNotNull(inflate2);
            return new C0209r9(this, inflate2);
        }
        View inflate3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.dtpl_payment_method_item, parent, false);
        ((ImageView) inflate3.findViewById(R.id.icon)).setClipToOutline(true);
        Intrinsics.checkNotNull(inflate3);
        return new C0248w9(this, inflate3);
    }
}
