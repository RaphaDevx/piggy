package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

/* loaded from: classes2.dex */
class FixedLengthValidator implements Validator {
    public int requiredLength;
    private String value;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public FixedLengthValidator(int i) {
        this.requiredLength = i;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.value = editable.toString();
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
        return str != null && str.length() == this.requiredLength;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (i2 <= 0 || ((spanned.length() + i4) - i3) + i2 <= this.requiredLength) {
            return null;
        }
        return "";
    }
}
