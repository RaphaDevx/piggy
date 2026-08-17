package ch.datatrans.payment;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class m1 implements TextWatcher {
    public final /* synthetic */ r3 a;

    public m1(r3 r3Var) {
        this.a = r3Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        String valueOf = String.valueOf(editable);
        if (StringsKt.toIntOrNull(valueOf) != null) {
            ((C0177m0) this.a.b.getValue()).getClass();
            C0205r0 c0205r0 = AbstractC0144g0.a;
            c0205r0.getClass();
            Intrinsics.checkNotNullParameter(valueOf, "<set-?>");
            c0205r0.a = valueOf;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
