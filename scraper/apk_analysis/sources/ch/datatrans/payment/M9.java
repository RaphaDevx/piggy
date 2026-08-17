package ch.datatrans.payment;

import android.text.Editable;
import android.text.TextWatcher;
import ch.datatrans.payment.creditcard.PlaceholderTextField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class M9 implements TextWatcher {
    public final /* synthetic */ PlaceholderTextField a;

    public M9(PlaceholderTextField placeholderTextField) {
        this.a = placeholderTextField;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        int selectionStart = this.a.m.getSelectionStart();
        if (selectionStart > 0) {
            input.delete(selectionStart, input.length());
        }
        Function1 function1 = this.a.s;
        if (function1 != null) {
            function1.invoke(input);
        }
        this.a.a();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
