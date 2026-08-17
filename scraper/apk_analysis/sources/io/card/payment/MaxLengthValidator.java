package io.card.payment;

/* loaded from: classes2.dex */
class MaxLengthValidator extends NonEmptyValidator implements Validator {
    private int maxLength;

    MaxLengthValidator(int i) {
        this.maxLength = i;
    }

    @Override // io.card.payment.NonEmptyValidator, io.card.payment.Validator
    public boolean isValid() {
        return super.isValid() && getValue().length() <= this.maxLength;
    }
}
