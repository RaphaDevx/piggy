package ch.datatrans.payment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.bottomsheet.TransactionSheetActivity;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ie, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0161ie extends BroadcastReceiver {
    public final /* synthetic */ ue a;

    public C0161ie(ue ueVar) {
        this.a = ueVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri uri = intent != null ? (Uri) intent.getParcelableExtra("data") : null;
        String scheme = uri != null ? uri.getScheme() : null;
        ((Jc) this.a.b.getValue()).getClass();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        if (Intrinsics.areEqual(scheme, transactionRegistry.getTransactionModel$lib_release().h.a)) {
            FragmentActivity requireActivity = this.a.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type ch.datatrans.payment.bottomsheet.TransactionSheetActivity");
            ((TransactionSheetActivity) requireActivity).d(false);
            FragmentActivity requireActivity2 = this.a.requireActivity();
            Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type ch.datatrans.payment.bottomsheet.TransactionSheetActivity");
            ((TransactionSheetActivity) requireActivity2).c(true);
            C0182n c0182n = (C0182n) this.a.c.getValue();
            ((Jc) this.a.b.getValue()).getClass();
            c0182n.a(transactionRegistry.getTransactionModel$lib_release());
            this.a.dismiss();
        }
    }
}
