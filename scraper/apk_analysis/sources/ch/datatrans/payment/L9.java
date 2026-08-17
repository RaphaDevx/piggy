package ch.datatrans.payment;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/* loaded from: classes3.dex */
public final class L9 extends PasswordTransformationMethod {
    @Override // android.text.method.PasswordTransformationMethod, android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        return charSequence == null ? "" : charSequence;
    }
}
