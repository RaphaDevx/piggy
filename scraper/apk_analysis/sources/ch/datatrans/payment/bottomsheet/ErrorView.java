package ch.datatrans.payment.bottomsheet;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.InterfaceC0235ud;
import ch.datatrans.payment.R;
import ch.datatrans.payment.x4;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lch/datatrans/payment/bottomsheet/ErrorView;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class ErrorView extends LinearLayout {
    public final TextView a;
    public final TextView b;
    public final Button c;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ErrorView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void a(int i, InterfaceC0235ud message, boolean z, final Function0 buttonAction) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(buttonAction, "buttonAction");
        this.a.setText(i);
        this.a.post(new Runnable() { // from class: ch.datatrans.payment.bottomsheet.ErrorView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ErrorView.a(ErrorView.this);
            }
        });
        this.b.setMovementMethod(new ScrollingMovementMethod());
        x4.a(this.b, message);
        this.c.setText(z ? R.string.datatrans_sdk_error_alert_retry : R.string.datatrans_sdk_error_alert_ok);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.bottomsheet.ErrorView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ErrorView.a(Function0.this, view);
            }
        });
        setAlpha(0.0f);
        animate().alpha(1.0f);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater.from(context).inflate(R.layout.dtpl_error_view, this);
        setOrientation(1);
        setGravity(1);
        View findViewById = findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.message);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.b = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.button);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.c = (Button) findViewById3;
    }

    public static final void a(ErrorView errorView) {
        errorView.a.sendAccessibilityEvent(8);
    }

    public /* synthetic */ ErrorView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public static final void a(Function0 function0, View view) {
        function0.invoke();
    }
}
