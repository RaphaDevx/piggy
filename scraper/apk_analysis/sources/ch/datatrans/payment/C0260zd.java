package ch.datatrans.payment;

import androidx.fragment.app.Fragment;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: ch.datatrans.payment.zd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0260zd extends Lambda implements Function0 {
    public final /* synthetic */ Fragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0260zd(Fragment fragment) {
        super(0);
        this.a = fragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.requireActivity().getDefaultViewModelProviderFactory();
    }
}
