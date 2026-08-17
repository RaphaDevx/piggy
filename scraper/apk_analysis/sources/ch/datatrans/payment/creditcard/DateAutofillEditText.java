package ch.datatrans.payment.creditcard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.autofill.AutofillValue;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import io.sentry.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u0004\u0018\u00010\fH\u0017¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0012\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lch/datatrans/payment/creditcard/DateAutofillEditText;", "Landroidx/appcompat/widget/AppCompatEditText;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "getAutofillType", "()I", "Landroid/view/autofill/AutofillValue;", "getAutofillValue", "()Landroid/view/autofill/AutofillValue;", "", "b", "Z", "isDate", "()Z", "setDate", "(Z)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class DateAutofillEditText extends AppCompatEditText {
    public final SimpleDateFormat a;

    /* renamed from: b, reason: from kotlin metadata */
    public boolean isDate;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DateAutofillEditText(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.widget.TextView, android.view.View
    public final void autofill(AutofillValue autofillValue) {
        if (!this.isDate || autofillValue == null || !autofillValue.isDate()) {
            super.autofill(autofillValue);
        } else {
            setText(this.a.format(new Date(autofillValue.getDateValue())));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int getAutofillType() {
        if (this.isDate) {
            return 4;
        }
        return super.getAutofillType();
    }

    @Override // android.widget.TextView, android.view.View
    public AutofillValue getAutofillValue() {
        if (!this.isDate) {
            return super.getAutofillValue();
        }
        AutofillValue autofillValue = super.getAutofillValue();
        Date date = null;
        if (autofillValue == null) {
            return null;
        }
        try {
            date = this.a.parse(autofillValue.getTextValue().toString());
        } catch (ParseException unused) {
        }
        return date != null ? AutofillValue.forDate(date.getTime()) : autofillValue;
    }

    public final void setDate(boolean z) {
        this.isDate = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DateAutofillEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DateAutofillEditText(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? R.attr.editTextStyle : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateAutofillEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = new SimpleDateFormat("MMyy", Locale.getDefault());
        setTypeface(ResourcesCompat.getFont(context, ch.datatrans.payment.R.font.droid_sans_mono));
    }
}
