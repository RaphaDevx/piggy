package ch.datatrans.payment;

import android.animation.LayoutTransition;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.ConfigurationCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStoreOwner;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/S0;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class S0 extends Fragment {
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public final Lazy e;

    public S0() {
        Function0 a = Gd.a(new Ad(this), Jc.class);
        this.e = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Pa.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return S0.a(S0.this);
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new J0(function0));
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(f0.class), new K0(lazy), new L0(lazy), new M0(this, lazy));
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new O0(new N0(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(b1.class), new P0(lazy2), new Q0(lazy2), new R0(this, lazy2));
    }

    public static final Unit a(S0 s0, M3 dccCardData) {
        f0 f0Var = (f0) s0.b.getValue();
        Intrinsics.checkNotNull(dccCardData);
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(dccCardData, "dccCardData");
        f0Var.f.postValue(dccCardData);
        return Unit.INSTANCE;
    }

    public static final Unit b(S0 s0) {
        ((Jc) s0.e.getValue()).d();
        return Unit.INSTANCE;
    }

    public static final void c(S0 s0, View view) {
        L3 l3;
        b1 b1Var = (b1) s0.c.getValue();
        boolean areEqual = Intrinsics.areEqual(((b1) s0.c.getValue()).f.getValue(), Boolean.TRUE);
        MutableLiveData mutableLiveData = b1Var.h;
        W0 w0 = b1Var.l;
        O2 o2 = null;
        if (w0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dccModel");
            w0 = null;
        }
        if (areEqual) {
            l3 = new L3(w0.a);
        } else {
            w0.getClass();
            l3 = new L3();
        }
        O2 o22 = b1Var.k;
        if (o22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardModel");
            o22 = null;
        }
        PaymentMethodType paymentMethodType = o22.a;
        Intrinsics.checkNotNull(paymentMethodType);
        O2 o23 = b1Var.k;
        if (o23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardModel");
            o23 = null;
        }
        String str = o23.c;
        O2 o24 = b1Var.k;
        if (o24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardModel");
            o24 = null;
        }
        CardExpiryDate cardExpiryDate = o24.b;
        Intrinsics.checkNotNull(cardExpiryDate);
        O2 o25 = b1Var.k;
        if (o25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("creditCardModel");
        } else {
            o2 = o25;
        }
        mutableLiveData.postValue(new M3(l3, new Card(paymentMethodType, str, cardExpiryDate, o2.d, null, 16, null)));
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_credit_card_dcc_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (transactionRegistry.isTransactionFailure$lib_release(requireActivity)) {
            Pa pa = (Pa) this.a.getValue();
            C7 c7 = r4.a;
            C0178m4 errorModel = v4.a(new Function0() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return S0.b(S0.this);
                }
            });
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
            return;
        }
        Bundle arguments = getArguments();
        C0211s0 merchantDCCModel = (C0211s0) (arguments != null ? arguments.getSerializable("MERCHANT_DCC_CURRENCY_MODEL_EXTRA") : null);
        Bundle arguments2 = getArguments();
        W0 dccModel = (W0) (arguments2 != null ? arguments2.getSerializable("DCC_MODEL_EXTRA") : null);
        Bundle arguments3 = getArguments();
        O2 creditCardModel = (O2) (arguments3 != null ? arguments3.getSerializable("CREDIT_CARD_MODEL_EXTRA") : null);
        if (merchantDCCModel != null && dccModel != null && creditCardModel != null) {
            C0211s0 cardDCCModel = new C0211s0(dccModel.c, dccModel.b);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getResources().getString(R.string.datatrans_sdk_dcc_markup_info);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String rateText = String.format(string, Arrays.copyOf(new Object[]{merchantDCCModel.b, a(dccModel.d), dccModel.b, a(dccModel.e)}, 4));
            Intrinsics.checkNotNullExpressionValue(rateText, "format(...)");
            if (dccModel.g) {
                merchantDCCModel.c = null;
                cardDCCModel.c = null;
                dccModel.g = false;
            }
            b1 b1Var = (b1) this.c.getValue();
            b1Var.getClass();
            Intrinsics.checkNotNullParameter(merchantDCCModel, "merchantDCCModel");
            Intrinsics.checkNotNullParameter(cardDCCModel, "cardDCCModel");
            Intrinsics.checkNotNullParameter(rateText, "rateText");
            Intrinsics.checkNotNullParameter(creditCardModel, "creditCardModel");
            Intrinsics.checkNotNullParameter(dccModel, "dccModel");
            b1Var.i = merchantDCCModel;
            b1Var.j = cardDCCModel;
            b1Var.k = creditCardModel;
            b1Var.l = dccModel;
            b1Var.c.postValue(merchantDCCModel);
            MutableLiveData mutableLiveData = b1Var.d;
            C0211s0 c0211s0 = b1Var.j;
            if (c0211s0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardDCCModel");
                c0211s0 = null;
            }
            mutableLiveData.postValue(c0211s0);
            b1Var.e.postValue(rateText);
            b1Var.a(dccModel.h);
            C0211s0 c0211s02 = b1Var.i;
            if (c0211s02 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("merchantDCCModel");
                c0211s02 = null;
            }
            Boolean bool = c0211s02.c;
            if (bool != null) {
                b1Var.b(bool.booleanValue());
            }
            C0211s0 c0211s03 = b1Var.j;
            if (c0211s03 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardDCCModel");
                c0211s03 = null;
            }
            Boolean bool2 = c0211s03.c;
            if (bool2 != null) {
                b1Var.a(bool2.booleanValue());
            }
        }
        Pa pa2 = (Pa) this.a.getValue();
        pa2.a.postValue(Boolean.TRUE);
        String string2 = getString(R.string.datatrans_sdk_dcc_choose_currency_title);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        Pa.a(pa2, string2, null, 6);
        b(view);
        c(view);
        a(view);
    }

    public static final void b(S0 s0, View view) {
        ((b1) s0.c.getValue()).a(false);
        ((b1) s0.c.getValue()).b(true);
    }

    public static final ViewModelStoreOwner a(S0 s0) {
        if (s0.requireParentFragment() instanceof C0245w2) {
            Fragment requireParentFragment = s0.requireParentFragment();
            Intrinsics.checkNotNull(requireParentFragment);
            return requireParentFragment;
        }
        Fragment requireParentFragment2 = s0.requireParentFragment().requireParentFragment();
        Intrinsics.checkNotNull(requireParentFragment2);
        return requireParentFragment2;
    }

    public final void b(View view) {
        final Button button = (Button) view.findViewById(R.id.credit_card_dcc_pay_button);
        button.setText(getResources().getString(R.string.datatrans_sdk_dcc_pay_button_inactive));
        button.setEnabled(false);
        ((b1) this.c.getValue()).a.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(button, this, (String) obj);
            }
        }));
        ((b1) this.c.getValue()).b.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(button, (Boolean) obj);
            }
        }));
        ((b1) this.c.getValue()).h.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(S0.this, (M3) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                S0.c(S0.this, view2);
            }
        });
    }

    public static final void a(S0 s0, View view) {
        ((b1) s0.c.getValue()).a(true);
        ((b1) s0.c.getValue()).b(false);
    }

    public static final Unit a(Button button, S0 s0, String str) {
        button.setText(s0.getResources().getString(R.string.datatrans_sdk_confirm_pay_amount_button, str));
        return Unit.INSTANCE;
    }

    public static final Unit a(Button button, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        button.setEnabled(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(AppCompatTextView appCompatTextView, String str) {
        appCompatTextView.setText(str);
        return Unit.INSTANCE;
    }

    public static final Unit b(S0 s0, ConstraintLayout constraintLayout, C0211s0 c0211s0) {
        Intrinsics.checkNotNull(constraintLayout);
        String str = c0211s0.b;
        s0.getClass();
        ((TextView) constraintLayout.findViewById(R.id.credit_card_dcc_option_currency_text)).setText(str);
        ((TextView) constraintLayout.findViewById(R.id.credit_card_dcc_option_amount_text)).setText(c0211s0.a());
        return Unit.INSTANCE;
    }

    public final void a(View view) {
        final ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.credit_card_dcc_card_currency_container);
        final ConstraintLayout constraintLayout2 = (ConstraintLayout) view.findViewById(R.id.credit_card_dcc_merchant_currency_container);
        Intrinsics.checkNotNull(constraintLayout2);
        int dimensionPixelSize = constraintLayout2.getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_top_bottom);
        int dimensionPixelSize2 = constraintLayout2.getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_left_right);
        constraintLayout2.setSelected(true);
        constraintLayout2.setEnabled(false);
        View findViewById = constraintLayout2.findViewById(R.id.credit_card_dcc_option_background);
        findViewById.setAlpha(0.8f);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
        findViewById.setLayoutParams(layoutParams2);
        ((AppCompatImageView) constraintLayout2.findViewById(R.id.credit_card_dcc_option_selected_iv)).setVisibility(8);
        constraintLayout2.setEnabled(true);
        Intrinsics.checkNotNull(constraintLayout);
        int dimensionPixelSize3 = constraintLayout.getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_top_bottom);
        int dimensionPixelSize4 = constraintLayout.getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_left_right);
        constraintLayout.setSelected(true);
        constraintLayout.setEnabled(false);
        View findViewById2 = constraintLayout.findViewById(R.id.credit_card_dcc_option_background);
        findViewById2.setAlpha(0.8f);
        ViewGroup.LayoutParams layoutParams3 = findViewById2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
        layoutParams4.setMargins(dimensionPixelSize4, dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize3);
        findViewById2.setLayoutParams(layoutParams4);
        ((AppCompatImageView) constraintLayout.findViewById(R.id.credit_card_dcc_option_selected_iv)).setVisibility(8);
        constraintLayout.setEnabled(true);
        ViewCompat.setAccessibilityDelegate(constraintLayout2, new C0243w0());
        ViewCompat.setAccessibilityDelegate(constraintLayout, new C0250x0());
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                S0.a(S0.this, view2);
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                S0.b(S0.this, view2);
            }
        });
        ((b1) this.c.getValue()).c.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.b(S0.this, constraintLayout2, (C0211s0) obj);
            }
        }));
        ((b1) this.c.getValue()).d.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(S0.this, constraintLayout, (C0211s0) obj);
            }
        }));
        ((b1) this.c.getValue()).f.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(S0.this, constraintLayout, (Boolean) obj);
            }
        }));
        ((b1) this.c.getValue()).g.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.b(S0.this, constraintLayout2, (Boolean) obj);
            }
        }));
    }

    public final void c(View view) {
        final AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.credit_card_dcc_rate_text);
        ((b1) this.c.getValue()).e.observe(getViewLifecycleOwner(), new C0237v0(new Function1() { // from class: ch.datatrans.payment.S0$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return S0.a(AppCompatTextView.this, (String) obj);
            }
        }));
    }

    public static final Unit b(S0 s0, ConstraintLayout constraintLayout, Boolean bool) {
        if (bool.booleanValue()) {
            Intrinsics.checkNotNull(constraintLayout);
            a(s0, constraintLayout);
        } else {
            Intrinsics.checkNotNull(constraintLayout);
            s0.a(constraintLayout);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(S0 s0, ConstraintLayout constraintLayout, C0211s0 c0211s0) {
        Intrinsics.checkNotNull(constraintLayout);
        String str = c0211s0.b;
        s0.getClass();
        ((TextView) constraintLayout.findViewById(R.id.credit_card_dcc_option_currency_text)).setText(str);
        ((TextView) constraintLayout.findViewById(R.id.credit_card_dcc_option_amount_text)).setText(c0211s0.a());
        return Unit.INSTANCE;
    }

    public static final Unit a(S0 s0, ConstraintLayout constraintLayout, Boolean bool) {
        if (bool.booleanValue()) {
            Intrinsics.checkNotNull(constraintLayout);
            a(s0, constraintLayout);
        } else {
            Intrinsics.checkNotNull(constraintLayout);
            s0.a(constraintLayout);
        }
        return Unit.INSTANCE;
    }

    public static void a(S0 s0, ConstraintLayout constraintLayout) {
        s0.getClass();
        constraintLayout.setSelected(true);
        constraintLayout.setEnabled(false);
        View findViewById = constraintLayout.findViewById(R.id.credit_card_dcc_option_background);
        findViewById.setAlpha(1.0f);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(0, 0, 0, 0);
        findViewById.setLayoutParams(layoutParams2);
        ((AppCompatImageView) constraintLayout.findViewById(R.id.credit_card_dcc_option_selected_iv)).setVisibility(0);
    }

    public final void a(ConstraintLayout constraintLayout) {
        View findViewById = constraintLayout.findViewById(R.id.credit_card_dcc_option_background);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_left_right), getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_top_bottom), getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_left_right), getResources().getDimensionPixelSize(R.dimen.dtpl_credit_card_dcc_option_unselected_top_bottom));
        findViewById.setLayoutParams(layoutParams2);
        constraintLayout.setSelected(false);
        constraintLayout.setEnabled(true);
        AppCompatImageView appCompatImageView = (AppCompatImageView) constraintLayout.findViewById(R.id.credit_card_dcc_option_selected_iv);
        ViewParent parent = appCompatImageView.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        LayoutTransition layoutTransition = ((ViewGroup) parent).getLayoutTransition();
        Intrinsics.checkNotNullExpressionValue(layoutTransition, "getLayoutTransition(...)");
        layoutTransition.disableTransitionType(3);
        appCompatImageView.setVisibility(8);
        layoutTransition.enableTransitionType(3);
    }

    public static String a(String str) {
        double parseDouble = Double.parseDouble(str);
        String substring = str.substring(StringsKt.indexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null) + 1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        int length = substring.length();
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        if (locale == null) {
            locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        }
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        numberFormat.setMinimumFractionDigits(length);
        numberFormat.setMaximumFractionDigits(length);
        String format = numberFormat.format(parseDouble);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
