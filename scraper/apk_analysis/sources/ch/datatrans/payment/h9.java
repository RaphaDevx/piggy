package ch.datatrans.payment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.view.MenuProvider;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class h9 implements MenuProvider {
    public final /* synthetic */ p9 a;

    public h9(p9 p9Var) {
        this.a = p9Var;
    }

    @Override // androidx.core.view.MenuProvider
    public final void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
        menuInflater.inflate(R.menu.dtpl_payment_method_selection_fragment, menu);
    }

    @Override // androidx.core.view.MenuProvider
    public final boolean onMenuItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() != R.id.cancel) {
            return false;
        }
        ((Jc) this.a.a.getValue()).e.postValue(Unit.INSTANCE);
        return true;
    }
}
