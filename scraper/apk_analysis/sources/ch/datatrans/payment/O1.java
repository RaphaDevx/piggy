package ch.datatrans.payment;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.autofill.AutofillManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.creditcard.PlaceholderTextField;
import ch.datatrans.payment.paymentmethods.CvvOnlyCard;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/O1;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class O1 extends Fragment {
    public static final /* synthetic */ int k = 0;
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public final Lazy d;
    public View e;
    public PlaceholderTextField f;
    public PlaceholderTextField g;
    public TextView h;
    public Button i;
    public WindowInsetsControllerCompat j;
    public final Lazy l;

    public O1() {
        Function0 a = Gd.a(new Ad(this), Jc.class);
        this.l = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Pa.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return O1.c(O1.this);
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new C0206r1(function0));
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Z0.class), new C0212s1(lazy), new C0220t1(lazy), new C0229u1(this, lazy));
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new C0238v1(new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return O1.d(O1.this);
            }
        }));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0177m0.class), new C0244w1(lazy2), new C0251x1(lazy2), new C0255y1(this, lazy2));
        Function0 function02 = new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return O1.h(O1.this);
            }
        };
        Lazy lazy3 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new C0191o1(new C0257z1(this)));
        this.d = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(S1.class), new p1(lazy3), new q1(lazy3), function02);
    }

    public static final Unit a(O1 o1, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) o1.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) o1.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final ViewModelStoreOwner c(O1 o1) {
        Fragment requireParentFragment = o1.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(requireParentFragment, "requireParentFragment(...)");
        return requireParentFragment;
    }

    public static final ViewModelStoreOwner d(O1 o1) {
        Fragment requireParentFragment = o1.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(requireParentFragment, "requireParentFragment(...)");
        return requireParentFragment;
    }

    public static final Unit e(O1 o1) {
        ((Jc) o1.l.getValue()).d();
        return Unit.INSTANCE;
    }

    public static final void f(O1 o1) {
        PlaceholderTextField placeholderTextField = o1.f;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
            placeholderTextField = null;
        }
        placeholderTextField.requestFocus();
    }

    public static final void g(O1 o1) {
        PlaceholderTextField placeholderTextField = o1.g;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField = null;
        }
        placeholderTextField.requestFocus();
    }

    public static final ViewModelProvider.Factory h(O1 o1) {
        ((C0177m0) o1.c.getValue()).getClass();
        U1 u1 = AbstractC0144g0.b;
        ((C0177m0) o1.c.getValue()).getClass();
        C0205r0 c0205r0 = AbstractC0144g0.a;
        ((C0177m0) o1.c.getValue()).getClass();
        W0 w0 = AbstractC0144g0.c;
        ((C0177m0) o1.c.getValue()).getClass();
        C3 c3 = AbstractC0144g0.d;
        ((C0177m0) o1.c.getValue()).getClass();
        return new T1(u1, c0205r0, w0, c3, AbstractC0144g0.g);
    }

    public final void b() {
        final Pa pa = (Pa) this.a.getValue();
        pa.a.postValue(Boolean.TRUE);
        if (((C0177m0) this.c.getValue()).a()) {
            ((C0177m0) this.c.getValue()).j.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return O1.a(O1.this, pa, (CvvOnlyCard) obj);
                }
            }));
            return;
        }
        ((C0177m0) this.c.getValue()).getClass();
        C3 c3 = AbstractC0144g0.d;
        String takeLast = StringsKt.takeLast(c3.a, 4);
        PaymentMethodType paymentMethodType = c3.b.c;
        Intrinsics.checkNotNull(paymentMethodType);
        SpannableString a = a(Integer.valueOf(paymentMethodType.getLogo()), "•••• " + takeLast);
        String string = requireContext().getString(R.string.datatrans_sdk_accessibility_payment_method_ending_in, paymentMethodType.name(), takeLast);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Pa.a(pa, a, string, 2);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_credit_card_date_cvv_fragment, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.pay_button);
        final Button button = (Button) findViewById;
        button.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                O1.a(button, this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById, "apply(...)");
        this.i = button;
        View findViewById2 = inflate.findViewById(R.id.credit_card_date_input);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.f = (PlaceholderTextField) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.credit_card_cvv_container);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.e = findViewById3;
        View findViewById4 = inflate.findViewById(R.id.credit_card_cvv_input);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.g = (PlaceholderTextField) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.credit_card_cvv_optional_text);
        TextView textView = (TextView) findViewById5;
        textView.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                O1.a(O1.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById5, "apply(...)");
        this.h = textView;
        Window window = requireActivity().getWindow();
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, window.getDecorView());
        this.j = windowInsetsControllerCompat;
        windowInsetsControllerCompat.show(WindowInsetsCompat.Type.ime());
        Intrinsics.checkNotNull(inflate);
        AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.credit_card_number_autofill_dummy);
        ((C0177m0) this.c.getValue()).getClass();
        appCompatEditText.setText(AbstractC0144g0.d.a);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        if (((C0177m0) this.c.getValue()).a()) {
            Window window = requireActivity().getWindow();
            new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        d();
        c();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        boolean isTransactionFailure$lib_release = transactionRegistry.isTransactionFailure$lib_release(requireActivity);
        PlaceholderTextField placeholderTextField = null;
        WindowInsetsControllerCompat windowInsetsControllerCompat = null;
        if (isTransactionFailure$lib_release) {
            Pa pa = (Pa) this.a.getValue();
            C7 c7 = r4.a;
            C0178m4 errorModel = v4.a(new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return O1.e(O1.this);
                }
            });
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
            WindowInsetsControllerCompat windowInsetsControllerCompat2 = this.j;
            if (windowInsetsControllerCompat2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("insetsController");
            } else {
                windowInsetsControllerCompat = windowInsetsControllerCompat2;
            }
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.ime());
            return;
        }
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        requireActivity2.addMenuProvider(new Nf(this), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        if (bundle == null) {
            ((S1) this.d.getValue()).c();
        }
        b();
        a();
        e();
        ((S1) this.d.getValue()).a();
        PlaceholderTextField placeholderTextField2 = this.f;
        if (placeholderTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
        } else {
            placeholderTextField = placeholderTextField2;
        }
        x4.a(placeholderTextField, !((C0177m0) this.c.getValue()).a());
    }

    public static final Unit c(O1 o1, Boolean bool) {
        Button button = o1.i;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payButton");
            button = null;
        }
        Intrinsics.checkNotNull(bool);
        button.setVisibility(bool.booleanValue() ? 0 : 8);
        if (bool.booleanValue()) {
            Button button3 = o1.i;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("payButton");
            } else {
                button2 = button3;
            }
            x4.a(button2, 1500L);
        }
        return Unit.INSTANCE;
    }

    public static final void d(O1 o1, Object obj) {
        ((C0177m0) o1.c.getValue()).g.postValue(EnumC0152h0.c);
    }

    public static final Unit e(O1 o1, Boolean bool) {
        Pa pa = (Pa) o1.a.getValue();
        Intrinsics.checkNotNull(bool);
        bool.booleanValue();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    public static final Unit d(O1 o1, Boolean bool) {
        TextView textView = o1.h;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvOptionalText");
            textView = null;
        }
        Intrinsics.checkNotNull(bool);
        textView.setVisibility(bool.booleanValue() ? 0 : 8);
        return Unit.INSTANCE;
    }

    public final void e() {
        ((C0177m0) this.c.getValue()).d.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (Integer) obj);
            }
        }));
        ((C0177m0) this.c.getValue()).e.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (Boolean) obj);
            }
        }));
        ((C0177m0) this.c.getValue()).c.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (InterfaceC0235ud) obj);
            }
        }));
        ((C0177m0) this.c.getValue()).f.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.b(O1.this, (Boolean) obj);
            }
        }));
        ((C0177m0) this.c.getValue()).k.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (t4) obj);
            }
        }));
        ((C0177m0) this.c.getValue()).l.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (Y0) obj);
            }
        }));
    }

    public static final Unit b(O1 o1, Boolean bool) {
        Pa pa = (Pa) o1.a.getValue();
        Intrinsics.checkNotNull(bool);
        bool.booleanValue();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    public static final Unit b(O1 o1, Y0 y0) {
        Z0 z0 = (Z0) o1.b.getValue();
        Intrinsics.checkNotNull(y0);
        z0.a(y0);
        return Unit.INSTANCE;
    }

    public static final Unit b(O1 o1) {
        S1 s1 = (S1) o1.d.getValue();
        s1.b.d = false;
        s1.a(false);
        s1.b();
        return Unit.INSTANCE;
    }

    public static final Unit a(O1 o1, Y0 y0) {
        Z0 z0 = (Z0) o1.b.getValue();
        Intrinsics.checkNotNull(y0);
        z0.a(y0);
        return Unit.INSTANCE;
    }

    public static final Unit b(O1 o1, Editable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        S1 s1 = (S1) o1.d.getValue();
        s1.getClass();
        if (!Intrinsics.areEqual(String.valueOf(input), s1.f.getValue())) {
            if (input != null) {
                C0205r0 c0205r0 = s1.b;
                String obj = input.toString();
                c0205r0.getClass();
                Intrinsics.checkNotNullParameter(obj, "<set-?>");
                c0205r0.a = obj;
                s1.f.postValue(input.toString());
                if (s1.b.a()) {
                    s1.a();
                }
            }
            s1.b();
            s1.a(true);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(O1 o1, Integer num) {
        ((C0177m0) o1.c.getValue()).getClass();
        String str = AbstractC0144g0.a.a;
        PlaceholderTextField placeholderTextField = o1.g;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(num);
        placeholderTextField.setMaxLength(num.intValue());
        PlaceholderTextField placeholderTextField3 = o1.g;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField3 = null;
        }
        placeholderTextField3.setHintLength(num.intValue());
        PlaceholderTextField placeholderTextField4 = o1.g;
        if (placeholderTextField4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
        } else {
            placeholderTextField2 = placeholderTextField4;
        }
        placeholderTextField2.setText(str);
        return Unit.INSTANCE;
    }

    public static final Unit a(O1 o1) {
        S1 s1 = (S1) o1.d.getValue();
        s1.b.d = false;
        s1.a(false);
        s1.b();
        return Unit.INSTANCE;
    }

    public static final Unit b(O1 o1, String str) {
        PlaceholderTextField placeholderTextField = o1.f;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(str);
        placeholderTextField.setText(str);
        return Unit.INSTANCE;
    }

    public static final void b(final O1 o1, Object obj) {
        PlaceholderTextField placeholderTextField = o1.g;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField = null;
        }
        placeholderTextField.post(new Runnable() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                O1.g(O1.this);
            }
        });
        PlaceholderTextField placeholderTextField3 = o1.g;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
        } else {
            placeholderTextField2 = placeholderTextField3;
        }
        x4.a(placeholderTextField2, 500L);
    }

    public static final Unit a(O1 o1, Editable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        S1 s1 = (S1) o1.d.getValue();
        s1.getClass();
        if (!Intrinsics.areEqual(String.valueOf(input), s1.g.getValue())) {
            if (input != null) {
                if (input.length() == 1 && input.charAt(0) != '1' && input.charAt(0) != '0') {
                    input.insert(0, "0");
                }
                if (input.length() == 2 && input.charAt(0) == '1' && Intrinsics.compare((int) input.charAt(1), 51) >= 0) {
                    input.delete(1, 2);
                }
                if (input.length() == 3 && !S1.a(input)) {
                    input.delete(2, 3);
                }
                if (input.length() == 4 && !S1.a(input)) {
                    input.delete(3, 4);
                }
                U1 u1 = s1.a;
                String obj = input.toString();
                u1.getClass();
                Intrinsics.checkNotNullParameter(obj, "<set-?>");
                u1.a = obj;
                s1.g.postValue(input.toString());
                if (s1.a.a()) {
                    s1.a();
                }
            }
            s1.b();
            s1.a(true);
        }
        return Unit.INSTANCE;
    }

    public static final void a(O1 o1, View view) {
        S1 s1 = (S1) o1.d.getValue();
        C0205r0 c0205r0 = s1.b;
        c0205r0.d = true;
        Intrinsics.checkNotNullParameter("", "<set-?>");
        c0205r0.a = "";
        s1.f.postValue("");
        s1.n.postValue(Unit.INSTANCE);
        s1.a(false);
        s1.b();
    }

    public final void a() {
        ((S1) this.d.getValue()).f.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (String) obj);
            }
        }));
        ((S1) this.d.getValue()).g.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.b(O1.this, (String) obj);
            }
        }));
        ((S1) this.d.getValue()).h.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.c(O1.this, (Boolean) obj);
            }
        }));
        ((S1) this.d.getValue()).k.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.d(O1.this, (Boolean) obj);
            }
        }));
        ((S1) this.d.getValue()).l.observe(getViewLifecycleOwner(), new Observer() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                O1.a(O1.this, obj);
            }
        });
        ((S1) this.d.getValue()).m.observe(getViewLifecycleOwner(), new Observer() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                O1.b(O1.this, obj);
            }
        });
        ((S1) this.d.getValue()).n.observe(getViewLifecycleOwner(), new Observer() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                O1.c(O1.this, obj);
            }
        });
        ((S1) this.d.getValue()).o.observe(getViewLifecycleOwner(), new Observer() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                O1.d(O1.this, obj);
            }
        });
        ((S1) this.d.getValue()).i.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.e(O1.this, (Boolean) obj);
            }
        }));
        ((S1) this.d.getValue()).p.observe(getViewLifecycleOwner(), new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.b(O1.this, (Y0) obj);
            }
        }));
    }

    public final void a(final MenuItem menuItem) {
        ((S1) this.d.getValue()).j.observe(this, new k1(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(menuItem, (Boolean) obj);
            }
        }));
    }

    public static final void a(final Button button, O1 o1, View view) {
        button.setEnabled(false);
        button.postDelayed(new Runnable() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                O1.a(button);
            }
        }, 400L);
        ((C0177m0) o1.c.getValue()).c();
        AutofillManager autofillManager = (AutofillManager) button.getContext().getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.commit();
        }
    }

    public static final void a(Button button) {
        button.setEnabled(true);
    }

    public static final Unit a(MenuItem menuItem, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        menuItem.setVisible(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(O1 o1, Pa pa, CvvOnlyCard cvvOnlyCard) {
        String takeLast = StringsKt.takeLast(cvvOnlyCard.getMaskedCardNumber(), 4);
        Pa.a(pa, o1.a(Integer.valueOf(cvvOnlyCard.getType().getLogo()), "•••• " + takeLast), null, 6);
        return Unit.INSTANCE;
    }

    public static final Unit a(O1 o1, String str) {
        PlaceholderTextField placeholderTextField = o1.g;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField = null;
        }
        Intrinsics.checkNotNull(str);
        placeholderTextField.setText(str);
        return Unit.INSTANCE;
    }

    public static final void a(final O1 o1, Object obj) {
        PlaceholderTextField placeholderTextField = o1.f;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
            placeholderTextField = null;
        }
        placeholderTextField.post(new Runnable() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                O1.f(O1.this);
            }
        });
        PlaceholderTextField placeholderTextField3 = o1.f;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
        } else {
            placeholderTextField2 = placeholderTextField3;
        }
        x4.a(placeholderTextField2, 500L);
    }

    public static final Unit a(O1 o1, Boolean bool) {
        View view = o1.e;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvContainer");
            view = null;
        }
        Intrinsics.checkNotNull(bool);
        view.setVisibility(bool.booleanValue() ? 0 : 8);
        return Unit.INSTANCE;
    }

    public final void d() {
        PlaceholderTextField placeholderTextField = this.f;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
            placeholderTextField = null;
        }
        placeholderTextField.setAfterTextWatcher(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.a(O1.this, (Editable) obj);
            }
        });
        PlaceholderTextField placeholderTextField3 = this.g;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
        } else {
            placeholderTextField2 = placeholderTextField3;
        }
        placeholderTextField2.setAfterTextWatcher(new Function1() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return O1.b(O1.this, (Editable) obj);
            }
        });
    }

    public static final void c(O1 o1, Object obj) {
        WindowInsetsControllerCompat windowInsetsControllerCompat = o1.j;
        PlaceholderTextField placeholderTextField = null;
        if (windowInsetsControllerCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("insetsController");
            windowInsetsControllerCompat = null;
        }
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.ime());
        PlaceholderTextField placeholderTextField2 = o1.g;
        if (placeholderTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
            placeholderTextField2 = null;
        }
        placeholderTextField2.clearFocus();
        PlaceholderTextField placeholderTextField3 = o1.f;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
        } else {
            placeholderTextField = placeholderTextField3;
        }
        placeholderTextField.clearFocus();
    }

    public final void c() {
        PlaceholderTextField placeholderTextField = this.f;
        PlaceholderTextField placeholderTextField2 = null;
        if (placeholderTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateInput");
            placeholderTextField = null;
        }
        placeholderTextField.setClickWatcher(new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return O1.a(O1.this);
            }
        });
        PlaceholderTextField placeholderTextField3 = this.g;
        if (placeholderTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cvvInput");
        } else {
            placeholderTextField2 = placeholderTextField3;
        }
        placeholderTextField2.setClickWatcher(new Function0() { // from class: ch.datatrans.payment.O1$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return O1.b(O1.this);
            }
        });
    }

    public static final Unit a(O1 o1, InterfaceC0235ud interfaceC0235ud) {
        Button button = o1.i;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payButton");
            button = null;
        }
        x4.a(button, interfaceC0235ud);
        return Unit.INSTANCE;
    }

    public final SpannableString a(Integer num, String str) {
        SpannableString spannableString = new SpannableString("  " + str);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), num.intValue());
        if (drawable != null) {
            drawable.setBounds(new Rect(0, 0, AbstractC0184n4.a(30), AbstractC0184n4.a(20)));
            spannableString.setSpan(new C0199q0(drawable, AbstractC0184n4.a(-1)), 0, 1, 33);
            return spannableString;
        }
        throw new IllegalArgumentException("Drawable with resourceId " + num + " not found");
    }
}
