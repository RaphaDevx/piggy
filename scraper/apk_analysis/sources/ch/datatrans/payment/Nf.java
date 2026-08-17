package ch.datatrans.payment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.view.MenuProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Nf implements MenuProvider {
    public final /* synthetic */ O1 a;

    public Nf(O1 o1) {
        this.a = o1;
    }

    @Override // androidx.core.view.MenuProvider
    public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
        menuInflater.inflate(R.menu.dtpl_credit_card_date_cvv_menu, menu);
        MenuItem findItem = menu.findItem(R.id.next);
        O1 o1 = this.a;
        Intrinsics.checkNotNull(findItem);
        int i = O1.k;
        o1.a(findItem);
    }

    @Override // androidx.core.view.MenuProvider
    public final boolean onMenuItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() != R.id.next) {
            return false;
        }
        ((S1) this.a.d.getValue()).a(false);
        return true;
    }
}
