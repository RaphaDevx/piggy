package ch.datatrans.payment;

import android.content.Intent;
import android.content.IntentSender;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuProvider;
import io.card.payment.CardIOActivity;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class T2 implements MenuProvider {
    public final /* synthetic */ r3 a;

    public T2(r3 r3Var) {
        this.a = r3Var;
    }

    @Override // androidx.core.view.MenuProvider
    public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
        menuInflater.inflate(R.menu.dtpl_credit_card_number_fragment, menu);
        MenuItem findItem = menu.findItem(R.id.next);
        MenuItem findItem2 = menu.findItem(R.id.scanner);
        r3 r3Var = this.a;
        Intrinsics.checkNotNull(findItem);
        Intrinsics.checkNotNull(findItem2);
        int i = r3.g;
        r3Var.a(findItem, findItem2);
    }

    @Override // androidx.core.view.MenuProvider
    public final boolean onMenuItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.next) {
            C0177m0 c0177m0 = (C0177m0) this.a.b.getValue();
            c0177m0.getClass();
            if (!((Boolean) AbstractC0144g0.d.e.getValue()).booleanValue()) {
                throw new IllegalStateException("Check failed.");
            }
            c0177m0.a(false);
            return true;
        }
        if (itemId != R.id.scanner) {
            return false;
        }
        F3 f3 = ((v3) this.a.c.getValue()).j;
        IntentSender intentSender = null;
        if (f3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardScannerHelper");
            f3 = null;
        }
        if (f3.d) {
            ActivityResultLauncher activityResultLauncher = f3.i;
            IntentSender intentSender2 = f3.f;
            if (intentSender2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("intentSender");
            } else {
                intentSender = intentSender2;
            }
            activityResultLauncher.launch(new IntentSenderRequest.Builder(intentSender).build());
        } else if (f3.e) {
            Intent intent = new Intent(f3.b.requireContext(), (Class<?>) CardIOActivity.class);
            intent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
            intent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);
            intent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
            intent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true);
            intent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, true);
            intent.putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, ContextCompat.getColor(f3.b.requireContext(), R.color.dtpl_scan_guide));
            f3.h.launch(intent);
        }
        return true;
    }
}
