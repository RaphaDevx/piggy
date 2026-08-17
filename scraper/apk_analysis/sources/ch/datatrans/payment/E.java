package ch.datatrans.payment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class E {
    public static final void a(C c, CharSequence title, boolean z, String str) {
        Intrinsics.checkNotNullParameter(c, "<this>");
        Intrinsics.checkNotNullParameter(title, "title");
        View view = null;
        c.setTitle((CharSequence) null);
        LayoutInflater from = LayoutInflater.from(c);
        Toolbar toolbar = (Toolbar) c.findViewById(R.id.toolbar);
        if (toolbar == null) {
            throw new Exception("No view found with id R.id.toolbar");
        }
        View inflate = from.inflate(R.layout.dtpl_bottom_sheet_toolbar_title, (ViewGroup) toolbar, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) inflate;
        textView.setText(title);
        if (str != null) {
            textView.setContentDescription(str);
        }
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -2);
        layoutParams.gravity = z ? 17 : 8388627;
        textView.setLayoutParams(layoutParams);
        Iterator<View> it = ViewGroupKt.getChildren(toolbar).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View next = it.next();
            if (next.getId() == R.id.toolbarTitle) {
                view = next;
                break;
            }
        }
        View view2 = view;
        if (view2 != null) {
            toolbar.removeView(view2);
        }
        toolbar.addView(textView);
    }

    public static /* synthetic */ boolean a(FragmentManager fragmentManager) {
        return a(fragmentManager, new Function0() { // from class: ch.datatrans.payment.E$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E.a();
            }
        });
    }

    public static final Unit a() {
        return Unit.INSTANCE;
    }

    public static final boolean a(FragmentManager fragmentManager, Function0 finish) {
        Intrinsics.checkNotNullParameter(fragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(finish, "finish");
        List<Fragment> fragments = fragmentManager.getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
        if (fragments.isEmpty()) {
            return false;
        }
        List<Fragment> fragments2 = fragmentManager.getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments2, "getFragments(...)");
        FragmentManager childFragmentManager = ((Fragment) CollectionsKt.last((List) fragments2)).getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        if (a(childFragmentManager)) {
            return true;
        }
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return true;
        }
        finish.invoke();
        return false;
    }
}
