package ch.datatrans.payment;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class n1 implements TextWatcher {
    public final /* synthetic */ r3 a;

    public n1(r3 r3Var) {
        this.a = r3Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        String valueOf = String.valueOf(editable);
        if (StringsKt.toIntOrNull(valueOf) == null || valueOf.length() != 4) {
            return;
        }
        ((C0177m0) this.a.b.getValue()).getClass();
        U1 u1 = AbstractC0144g0.b;
        u1.getClass();
        Intrinsics.checkNotNullParameter(valueOf, "<set-?>");
        u1.a = valueOf;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
