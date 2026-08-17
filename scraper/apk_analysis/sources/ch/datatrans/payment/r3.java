package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelStoreOwner;
import ch.datatrans.payment.creditcard.DateAutofillEditText;
import ch.datatrans.payment.creditcard.PlaceholderTextField;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lch/datatrans/payment/r3;", "Landroidx/fragment/app/Fragment;", "", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class r3 extends Fragment {
    public static final /* synthetic */ int g = 0;
    public final Lazy a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new V2(this), new W2(this), new X2(this));
    public final Lazy b;
    public final Lazy c;
    public PlaceholderTextField d;
    public LinearLayout e;
    public TextView f;

    public r3() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return r3.a(r3.this);
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Y2(function0));
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0177m0.class), new Z2(lazy), new a3(lazy), new b3(this, lazy));
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new d3(new C0117c3(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(v3.class), new C0133e3(lazy2), new C0140f3(lazy2), new C0146g3(this, lazy2));
    }

    public static final ViewModelStoreOwner a(r3 r3Var) {
        Fragment requireParentFragment = r3Var.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(requireParentFragment, "requireParentFragment(...)");
        return requireParentFragment;
    }

    public static final Unit b(MenuItem menuItem, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        menuItem.setVisible(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void c() {
        ((C0177m0) this.b.getValue()).b.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(r3.this, (List) obj);
            }
        }));
    }

    public final void d() {
        ((v3) this.c.getValue()).d.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.b(r3.this, (List) obj);
            }
        }));
        ((v3) this.c.getValue()).b.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(r3.this, (Integer) obj);
            }
        }));
        ((v3) this.c.getValue()).c.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.b(r3.this, (Integer) obj);
            }
        }));
        ((v3) this.c.getValue()).a.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(r3.this, (String) obj);
            }
        }));
        ((v3) this.c.getValue()).e.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.c(r3.this, (List) obj);
            }
        }));
        ((v3) this.c.getValue()).f.observe(getViewLifecycleOwner(), new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.c(r3.this, (Integer) obj);
            }
        }));
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_credit_card_number_fragment, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.credit_card_number_input);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.d = (PlaceholderTextField) findViewById;
        View findViewById2 = inflate.findViewById(R.id.credit_card_logos);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.e = (LinearLayout) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.credit_card_logo_message);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.f = (TextView) findViewById3;
        a();
        PlaceholderTextField placeholderTextField = this.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        x4.a(placeholderTextField, 1000L);
        Intrinsics.checkNotNull(inflate);
        DateAutofillEditText dateAutofillEditText = (DateAutofillEditText) inflate.findViewById(R.id.credit_card_date_autofill_dummy);
        dateAutofillEditText.setDate(true);
        Intrinsics.checkNotNull(dateAutofillEditText);
        dateAutofillEditText.addTextChangedListener(new n1(this));
        AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.credit_card_cvv_autofill_dummy);
        Intrinsics.checkNotNull(appCompatEditText);
        appCompatEditText.addTextChangedListener(new m1(this));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        Window window = requireActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        v3 v3Var = (v3) this.c.getValue();
        MediatorLiveData mediatorLiveData = v3Var.i;
        F3 f3 = v3Var.j;
        if (f3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardScannerHelper");
            f3 = null;
        }
        mediatorLiveData.removeSource(f3.g);
        v3Var.i.removeSource(v3Var.g);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        b();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Pa pa = (Pa) this.a.getValue();
        pa.a.postValue(Boolean.TRUE);
        String string = getString(R.string.datatrans_sdk_credit_card_number_input_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Pa.a(pa, string, null, 6);
        d();
        c();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        requireActivity.addMenuProvider(new T2(this), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        v3 v3Var = (v3) this.c.getValue();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        ((C0177m0) this.b.getValue()).getClass();
        v3Var.a(this, requireContext, AbstractC0144g0.d);
    }

    public final void a(List list) {
        List list2 = (List) ((C0177m0) this.b.getValue()).b.getValue();
        if (list2 != null) {
            int i = 0;
            for (Object obj : list2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int intValue = ((Number) obj).intValue();
                LinearLayout linearLayout = this.e;
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logosView");
                    linearLayout = null;
                }
                View childAt = linearLayout.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                childAt.setVisibility(((list != null || intValue == G3.h) && (list == null || !list.contains(Integer.valueOf(intValue)))) ? 8 : 0);
                i = i2;
            }
        }
    }

    public static final void b(final r3 r3Var) {
        PlaceholderTextField placeholderTextField = r3Var.d;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        placeholderTextField.requestFocus();
        PlaceholderTextField placeholderTextField3 = r3Var.d;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
        } else {
            placeholderTextField2 = placeholderTextField3;
        }
        placeholderTextField2.post(new Runnable() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                r3.c(r3.this);
            }
        });
    }

    public static final void c(r3 r3Var) {
        Window window = r3Var.requireActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).show(WindowInsetsCompat.Type.ime());
    }

    public static final Unit b(r3 r3Var, List list) {
        PlaceholderTextField placeholderTextField = r3Var.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(list);
        placeholderTextField.setDividerSpaces(list);
        return Unit.INSTANCE;
    }

    public static final Unit c(r3 r3Var, List list) {
        LinearLayout linearLayout = r3Var.e;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logosView");
            linearLayout = null;
        }
        if (linearLayout.getChildCount() > 0) {
            r3Var.a(list);
        }
        return Unit.INSTANCE;
    }

    public static final Unit b(r3 r3Var, Integer num) {
        PlaceholderTextField placeholderTextField = r3Var.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(num);
        placeholderTextField.setHintLength(num.intValue());
        return Unit.INSTANCE;
    }

    public final void b() {
        PlaceholderTextField placeholderTextField = this.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        placeholderTextField.setAfterTextWatcher(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(r3.this, (Editable) obj);
            }
        });
    }

    public static final Unit c(r3 r3Var, Integer num) {
        TextView textView = null;
        if (num != null) {
            TextView textView2 = r3Var.f;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logoMessage");
                textView2 = null;
            }
            textView2.setText(num.intValue());
            TextView textView3 = r3Var.f;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logoMessage");
            } else {
                textView = textView3;
            }
            textView.setVisibility(0);
            r3Var.requireView().announceForAccessibility(r3Var.requireContext().getString(num.intValue()));
        } else {
            TextView textView4 = r3Var.f;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logoMessage");
                textView4 = null;
            }
            textView4.setText("");
            TextView textView5 = r3Var.f;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logoMessage");
            } else {
                textView = textView5;
            }
            textView.setVisibility(8);
        }
        return Unit.INSTANCE;
    }

    public final void a(int i, int i2) {
        ((C0177m0) this.b.getValue()).getClass();
        U1 u1 = AbstractC0144g0.b;
        String format = String.format("%02d%s", Arrays.copyOf(new Object[]{Integer.valueOf(i), StringsKt.takeLast(String.valueOf(i2), 2)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        u1.getClass();
        Intrinsics.checkNotNullParameter(format, "<set-?>");
        u1.a = format;
    }

    public final void a(final MenuItem menuItem, final MenuItem menuItem2) {
        ((v3) this.c.getValue()).g.observe(this, new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(menuItem, (Boolean) obj);
            }
        }));
        ((v3) this.c.getValue()).h.observe(this, new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.a(menuItem, this, (Boolean) obj);
            }
        }));
        ((v3) this.c.getValue()).i.observe(this, new U2(new Function1() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return r3.b(menuItem2, (Boolean) obj);
            }
        }));
    }

    public static final Unit a(MenuItem menuItem, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        menuItem.setVisible(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(MenuItem menuItem, r3 r3Var, Boolean bool) {
        int alphaComponent;
        Intrinsics.checkNotNull(bool);
        menuItem.setEnabled(bool.booleanValue());
        Context context = r3Var.requireContext();
        Intrinsics.checkNotNullExpressionValue(context, "requireContext(...)");
        Intrinsics.checkNotNullParameter(menuItem, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        int color = ContextCompat.getColor(context, R.color.dtpl_bar_link_color);
        if (menuItem.isEnabled()) {
            alphaComponent = ColorUtils.setAlphaComponent(color, 255);
        } else {
            alphaComponent = ColorUtils.setAlphaComponent(color, 128);
        }
        SpannableString spannableString = new SpannableString(String.valueOf(menuItem.getTitle()));
        spannableString.setSpan(new ForegroundColorSpan(alphaComponent), 0, spannableString.length(), 0);
        menuItem.setTitle(spannableString);
        return Unit.INSTANCE;
    }

    public final void a() {
        PlaceholderTextField placeholderTextField = this.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        placeholderTextField.postDelayed(new Runnable() { // from class: ch.datatrans.payment.r3$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                r3.b(r3.this);
            }
        }, 400L);
    }

    public static final Unit a(r3 r3Var, List list) {
        LinearLayout linearLayout = r3Var.e;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logosView");
            linearLayout = null;
        }
        linearLayout.removeAllViews();
        Intrinsics.checkNotNull(list);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int intValue = ((Number) it.next()).intValue();
            LayoutInflater layoutInflater = r3Var.requireActivity().getLayoutInflater();
            int i = R.layout.dtpl_credit_card_item;
            LinearLayout linearLayout2 = r3Var.e;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logosView");
                linearLayout2 = null;
            }
            View inflate = layoutInflater.inflate(i, (ViewGroup) linearLayout2, false);
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.ImageView");
            ImageView imageView = (ImageView) inflate;
            imageView.setImageResource(intValue);
            imageView.setClipToOutline(true);
            LinearLayout linearLayout3 = r3Var.e;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logosView");
                linearLayout3 = null;
            }
            linearLayout3.addView(imageView);
        }
        r3Var.a((List) ((v3) r3Var.c.getValue()).e.getValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(r3 r3Var, Integer num) {
        PlaceholderTextField placeholderTextField = r3Var.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(num);
        placeholderTextField.setMaxLength(num.intValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(r3 r3Var, String str) {
        PlaceholderTextField placeholderTextField = r3Var.d;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(str);
        placeholderTextField.setText(str);
        return Unit.INSTANCE;
    }

    public static final Unit a(r3 r3Var, Editable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String obj = input.toString();
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String replace = new Regex("[^0-9]").replace(obj, "");
        ((C0177m0) r3Var.b.getValue()).getClass();
        if (!Intrinsics.areEqual(replace, AbstractC0144g0.d.a)) {
            r3Var.a(replace, false);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x01eb, code lost:
    
        if (r6.a == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x020f, code lost:
    
        if (r8.g <= r9.g) goto L67;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x014d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 947
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.r3.a(java.lang.String, boolean):void");
    }
}
