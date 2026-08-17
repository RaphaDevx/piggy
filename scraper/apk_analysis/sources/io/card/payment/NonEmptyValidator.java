package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

/* loaded from: classes2.dex */
class NonEmptyValidator implements Validator {
    private String value;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        return null;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    NonEmptyValidator() {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.value = editable.toString().trim();
    }

    @Override // io.card.payment.Validator
    public String getValue() {
        return this.value;
    }

    @Override // io.card.payment.Validator
    public boolean hasFullLength() {
        return isValid();
    }

    @Override // io.card.payment.Validator
    public boolean isValid() {
        String str = this.value;
        return str != null && str.length() > 0;
    }
}
