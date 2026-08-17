package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

/* loaded from: classes2.dex */
class AlwaysValid implements Validator {
    private String placeholder;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        return null;
    }

    @Override // io.card.payment.Validator
    public boolean hasFullLength() {
        return true;
    }

    @Override // io.card.payment.Validator
    public boolean isValid() {
        return true;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // io.card.payment.Validator
    public String getValue() {
        return this.placeholder;
    }
}
