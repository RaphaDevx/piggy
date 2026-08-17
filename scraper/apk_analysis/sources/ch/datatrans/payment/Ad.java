package ch.datatrans.payment;

import androidx.fragment.app.Fragment;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes3.dex */
public final /* synthetic */ class Ad extends FunctionReferenceImpl implements Function0 {
    public Ad(Fragment fragment) {
        super(0, fragment, Fragment.class, "requireActivity", "requireActivity()Landroidx/fragment/app/FragmentActivity;", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return ((Fragment) this.receiver).requireActivity();
    }
}
