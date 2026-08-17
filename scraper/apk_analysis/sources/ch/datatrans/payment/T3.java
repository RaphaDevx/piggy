package ch.datatrans.payment;

import android.view.autofill.AutofillManager;
import androidx.activity.OnBackPressedCallback;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class T3 extends OnBackPressedCallback {
    public final /* synthetic */ AbstractActivityC0147g4 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T3(AbstractActivityC0147g4 abstractActivityC0147g4) {
        super(true);
        this.a = abstractActivityC0147g4;
    }

    @Override // androidx.activity.OnBackPressedCallback
    public final void handleOnBackPressed() {
        AutofillManager autofillManager = (AutofillManager) this.a.getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.cancel();
        }
        if (((Pa) this.a.n.getValue()).d.getValue() == 0 && !Intrinsics.areEqual(((Pa) this.a.n.getValue()).b.getValue(), Boolean.TRUE)) {
            this.a.f();
        }
    }
}
