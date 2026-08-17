package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedCard;
import java.util.ArrayList;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/w2;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: ch.datatrans.payment.w2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0245w2 extends Fragment {
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public final Lazy d;
    public final Lazy e;

    public C0245w2() {
        Function0 a = Gd.a(new Ad(this), Jc.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Pa.class);
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        C0115b2 c0115b2 = new C0115b2(this);
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new c2(c0115b2));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(f0.class), new d2(lazy), new C0132e2(lazy), new C0139f2(this, lazy));
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new h2(new C0145g2(this)));
        this.d = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Z0.class), new i2(lazy2), new j2(lazy2), new W1(this, lazy2));
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0245w2.a(C0245w2.this);
            }
        };
        Lazy lazy3 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Y1(new X1(this)));
        this.e = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(M2.class), new Z1(lazy3), new a2(lazy3), function0);
    }

    public static final Unit a(C0245w2 c0245w2, M3 m3) {
        L3 l3 = m3.a;
        Card card = m3.b;
        ((M2) c0245w2.e.getValue()).a.g = l3;
        Nb nb = ((M2) c0245w2.e.getValue()).a;
        nb.getClass();
        nb.a(CollectionsKt.listOf(card.getType()));
        nb.f = card;
        ((M2) c0245w2.e.getValue()).c();
        return Unit.INSTANCE;
    }

    public static final Unit b(C0245w2 c0245w2, Unit unit) {
        ((Pa) c0245w2.b.getValue()).b.postValue(Boolean.FALSE);
        Xa xa = ((Jc) c0245w2.a.getValue()).k;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        e0 fragment = new e0();
        FragmentManager childFragmentManager = c0245w2.getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        Intrinsics.checkNotNullParameter(childFragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        boolean isEmpty = childFragmentManager.getFragments().isEmpty();
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        if (!isEmpty) {
            beginTransaction.setCustomAnimations(R.anim.dtpl_slide_left_in, R.anim.dtpl_slide_left_out, R.anim.dtpl_slide_right_in, R.anim.dtpl_slide_right_out);
            beginTransaction.addToBackStack("bottom_sheet");
        }
        beginTransaction.replace(R.id.container, fragment, (String) null);
        beginTransaction.commit();
        return unit2;
    }

    public static final Unit c(C0245w2 c0245w2, Unit unit) {
        ((Pa) c0245w2.b.getValue()).b.postValue(Boolean.FALSE);
        if (c0245w2.getChildFragmentManager().getFragments().isEmpty()) {
            ((Pa) c0245w2.b.getValue()).f.postValue(Unit.INSTANCE);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_flow_fragment, viewGroup, false);
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
            return;
        }
        a();
        b();
        if (bundle == null) {
            M2 m2 = (M2) this.e.getValue();
            Nb nb = m2.a;
            if (nb.l == null && nb.f == null) {
                m2.j.postValue(Unit.INSTANCE);
            } else {
                m2.d();
            }
        }
    }

    public static final Unit a(C0245w2 c0245w2, TransactionException transactionException) {
        Jc jc = (Jc) c0245w2.a.getValue();
        Nb nb = ((M2) c0245w2.e.getValue()).a;
        Intrinsics.checkNotNull(transactionException);
        jc.a(nb, transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0245w2 c0245w2, Nb nb) {
        Jc jc = (Jc) c0245w2.a.getValue();
        Intrinsics.checkNotNull(nb);
        jc.a(nb);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0245w2 c0245w2, Ae webProcess) {
        ((Pa) c0245w2.b.getValue()).b.postValue(Boolean.TRUE);
        Pa pa = (Pa) c0245w2.b.getValue();
        Intrinsics.checkNotNull(webProcess);
        pa.getClass();
        Intrinsics.checkNotNullParameter(webProcess, "webProcess");
        pa.h = webProcess;
        Xa xa = pa.g;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit a(C0245w2 c0245w2, Unit unit) {
        C0211s0 merchantDCCCurrencyModel;
        W0 dccModel;
        O2 creditCardDCCModel;
        ((Pa) c0245w2.b.getValue()).b.postValue(Boolean.FALSE);
        Xa xa = ((Jc) c0245w2.a.getValue()).k;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        Nb nb = ((M2) c0245w2.e.getValue()).a;
        if (nb.d != null) {
            merchantDCCCurrencyModel = new C0211s0(-1L, "");
        } else {
            W8 w8 = nb.k;
            Intrinsics.checkNotNull(w8);
            merchantDCCCurrencyModel = new C0211s0(w8.a, w8.b);
        }
        M2 m2 = (M2) c0245w2.e.getValue();
        Nb nb2 = m2.a;
        Card card = nb2.f;
        SavedCard savedCard = (SavedCard) nb2.l;
        if (card != null) {
            dccModel = m2.b;
            dccModel.h = nb2.t.contains(card.getType().getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String());
        } else if (savedCard != null) {
            dccModel = m2.b;
            dccModel.h = nb2.t.contains(savedCard.getType().getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String());
        } else {
            dccModel = m2.b;
            List list = nb2.t;
            PaymentMethodType paymentMethodType = nb2.i;
            dccModel.h = CollectionsKt.contains(list, paymentMethodType != null ? paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String() : null);
        }
        Nb nb3 = ((M2) c0245w2.e.getValue()).a;
        Card card2 = nb3.f;
        SavedCard savedCard2 = (SavedCard) nb3.l;
        if (card2 != null) {
            PaymentMethodType type = card2.getType();
            CardExpiryDate expiryDate = card2.getExpiryDate();
            String number = card2.getNumber();
            String cvv = card2.getCvv();
            creditCardDCCModel = new O2(type, expiryDate, number, cvv != null ? cvv : "");
        } else if (savedCard2 != null) {
            creditCardDCCModel = new O2(savedCard2.getType(), savedCard2.getCardExpiryDate(), savedCard2.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String(), "");
        } else {
            creditCardDCCModel = new O2(null, null, "", "");
        }
        Intrinsics.checkNotNullParameter(merchantDCCCurrencyModel, "merchantDCCCurrencyModel");
        Intrinsics.checkNotNullParameter(dccModel, "dccModel");
        Intrinsics.checkNotNullParameter(creditCardDCCModel, "creditCardDCCModel");
        Bundle bundle = new Bundle();
        bundle.putSerializable("MERCHANT_DCC_CURRENCY_MODEL_EXTRA", merchantDCCCurrencyModel);
        bundle.putSerializable("DCC_MODEL_EXTRA", dccModel);
        bundle.putSerializable("CREDIT_CARD_MODEL_EXTRA", creditCardDCCModel);
        S0 fragment = new S0();
        fragment.setArguments(bundle);
        FragmentManager childFragmentManager = c0245w2.getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        Intrinsics.checkNotNullParameter(childFragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        boolean isEmpty = childFragmentManager.getFragments().isEmpty();
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        if (!isEmpty) {
            beginTransaction.setCustomAnimations(R.anim.dtpl_slide_left_in, R.anim.dtpl_slide_left_out, R.anim.dtpl_slide_right_in, R.anim.dtpl_slide_right_out);
            beginTransaction.addToBackStack("bottom_sheet");
        }
        beginTransaction.replace(R.id.container, fragment, (String) null);
        beginTransaction.commit();
        return unit2;
    }

    public final void b() {
        ((M2) this.e.getValue()).k.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Unit) obj);
            }
        }));
        ((M2) this.e.getValue()).j.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.b(C0245w2.this, (Unit) obj);
            }
        }));
        ((M2) this.e.getValue()).d.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (t4) obj);
            }
        }));
        ((M2) this.e.getValue()).e.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (TransactionException) obj);
            }
        }));
        ((M2) this.e.getValue()).g.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Nb) obj);
            }
        }));
        ((M2) this.e.getValue()).i.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Boolean) obj);
            }
        }));
        ((M2) this.e.getValue()).f.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.c(C0245w2.this, (Unit) obj);
            }
        }));
        ((M2) this.e.getValue()).h.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Ae) obj);
            }
        }));
        ((M2) this.e.getValue()).l.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Y0) obj);
            }
        }));
    }

    public static final Unit a(C0245w2 c0245w2, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) c0245w2.b.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) c0245w2.b.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0245w2 c0245w2, Boolean bool) {
        Pa pa = (Pa) c0245w2.b.getValue();
        Intrinsics.checkNotNull(bool);
        bool.booleanValue();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    public final void a() {
        Z9 text;
        C0211s0 data;
        f0 f0Var = (f0) this.c.getValue();
        ArrayList cards = ((M2) this.e.getValue()).c;
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(cards, "cards");
        f0Var.a.postValue(cards);
        f0 f0Var2 = (f0) this.c.getValue();
        Nb nb = ((M2) this.e.getValue()).a;
        if (nb.d != null) {
            text = new Z9(R.string.datatrans_sdk_confirm_registration_button, new Object[0]);
        } else {
            W8 w8 = nb.k;
            Intrinsics.checkNotNull(w8);
            text = new Z9(R.string.datatrans_sdk_confirm_pay_amount_button, w8.g);
        }
        f0Var2.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        f0Var2.b.postValue(text);
        f0 f0Var3 = (f0) this.c.getValue();
        Nb nb2 = ((M2) this.e.getValue()).a;
        if (nb2.d != null) {
            data = new C0211s0(-1L, "");
        } else {
            W8 w82 = nb2.k;
            Intrinsics.checkNotNull(w82);
            data = new C0211s0(w82.a, w82.b);
        }
        f0Var3.getClass();
        Intrinsics.checkNotNullParameter(data, "data");
        f0Var3.c.postValue(data);
        f0 f0Var4 = (f0) this.c.getValue();
        W8 w83 = ((M2) this.e.getValue()).a.k;
        C0219t0 dccData = new C0219t0(w83 != null ? w83.c : null, ((M2) this.e.getValue()).a.r, ((M2) this.e.getValue()).a.t, ((M2) this.e.getValue()).a.d == null);
        f0Var4.getClass();
        Intrinsics.checkNotNullParameter(dccData, "dccData");
        f0Var4.d.postValue(dccData);
        ((f0) this.c.getValue()).e.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (Card) obj);
            }
        }));
        ((f0) this.c.getValue()).f.observe(getViewLifecycleOwner(), new V1(new Function1() { // from class: ch.datatrans.payment.w2$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0245w2.a(C0245w2.this, (M3) obj);
            }
        }));
    }

    public static final Unit a(C0245w2 c0245w2, Y0 y0) {
        Z0 z0 = (Z0) c0245w2.d.getValue();
        Intrinsics.checkNotNull(y0);
        z0.a(y0);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0245w2 c0245w2, Card card) {
        ((M2) c0245w2.e.getValue()).a.g = null;
        Nb nb = ((M2) c0245w2.e.getValue()).a;
        if (card != null) {
            nb.getClass();
            nb.a(CollectionsKt.listOf(card.getType()));
        }
        nb.f = card;
        ((M2) c0245w2.e.getValue()).c();
        return Unit.INSTANCE;
    }

    public static final ViewModelProvider.Factory a(C0245w2 c0245w2) {
        ((Jc) c0245w2.a.getValue()).getClass();
        Nb a = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        Context requireContext = c0245w2.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return new N2(a, new R9(requireContext));
    }
}
