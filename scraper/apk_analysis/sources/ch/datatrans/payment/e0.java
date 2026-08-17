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
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStoreOwner;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.CvvOnlyCard;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/e0;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class e0 extends Fragment {
    public final Lazy a;
    public final Lazy b;
    public C0204r c;

    public e0() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return e0.a(e0.this);
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new M(function0));
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(f0.class), new N(lazy), new O(lazy), new P(this, lazy));
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new S(new Q(this)));
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0177m0.class), new T(lazy2), new U(lazy2), new V(this, lazy2));
    }

    public final void a() {
        ((f0) this.a.getValue()).a.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (List) obj);
            }
        }));
        ((f0) this.a.getValue()).b.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (InterfaceC0235ud) obj);
            }
        }));
        ((f0) this.a.getValue()).c.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (C0211s0) obj);
            }
        }));
        ((f0) this.a.getValue()).d.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (C0219t0) obj);
            }
        }));
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        R9 r9 = new R9(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
        this.c = new C0204r(r9, new E4(requireContext2));
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new K(this, null), 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_flow_fragment, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        C0204r c0204r = this.c;
        if (c0204r == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binRangesRepository");
            c0204r = null;
        }
        c0204r.getClass();
        C0204r.c = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        a(bundle);
        a();
        if (bundle == null) {
            C0177m0 c0177m0 = (C0177m0) this.b.getValue();
            if (!c0177m0.a()) {
                c0177m0.g.postValue(EnumC0152h0.a);
                return;
            }
            PCIPTokenization pCIPTokenization = c0177m0.m;
            Intrinsics.checkNotNull(pCIPTokenization);
            CvvOnlyCard cvvOnlyCard = pCIPTokenization.getCvvOnlyCard();
            Intrinsics.checkNotNull(cvvOnlyCard);
            c0177m0.j.postValue(cvvOnlyCard);
            int cvvLength = cvvOnlyCard.getType().getCvvLength();
            AbstractC0144g0.a.b = cvvLength;
            c0177m0.d.postValue(Integer.valueOf(cvvLength));
            c0177m0.e.postValue(Boolean.valueOf(cvvLength > 0));
            U1 u1 = AbstractC0144g0.b;
            String str = cvvOnlyCard.getExpiryDate().getFormattedMonth() + cvvOnlyCard.getExpiryDate().getFormattedYear();
            u1.getClass();
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            u1.a = str;
        }
    }

    public static final Unit a(e0 e0Var, Card card) {
        f0 f0Var = (f0) e0Var.a.getValue();
        Intrinsics.checkNotNull(card);
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(card, "card");
        f0Var.e.postValue(card);
        return Unit.INSTANCE;
    }

    public static final Unit a(e0 e0Var, String cvv) {
        f0 f0Var = (f0) e0Var.a.getValue();
        Intrinsics.checkNotNull(cvv);
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(cvv, "cvv");
        f0Var.g.postValue(cvv);
        return Unit.INSTANCE;
    }

    public static final ViewModelStoreOwner a(e0 e0Var) {
        Fragment parentFragment = e0Var.getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        FragmentActivity requireActivity = e0Var.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return requireActivity;
    }

    public static final Unit a(e0 e0Var, List value) {
        C0177m0 c0177m0 = (C0177m0) e0Var.b.getValue();
        Intrinsics.checkNotNull(value);
        c0177m0.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
        Iterator it = value.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((PaymentMethodType) it.next()).getLogo()));
        }
        c0177m0.b.postValue(CollectionsKt.plus((Collection<? extends Integer>) arrayList, Integer.valueOf(G3.h)));
        c0177m0.a = value;
        return Unit.INSTANCE;
    }

    public static final Unit a(e0 e0Var, InterfaceC0235ud text) {
        C0177m0 c0177m0 = (C0177m0) e0Var.b.getValue();
        Intrinsics.checkNotNull(text);
        c0177m0.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        c0177m0.c.postValue(text);
        return Unit.INSTANCE;
    }

    public static final Unit a(e0 e0Var, C0211s0 data) {
        C0177m0 c0177m0 = (C0177m0) e0Var.b.getValue();
        Intrinsics.checkNotNull(data);
        c0177m0.getClass();
        Intrinsics.checkNotNullParameter(data, "data");
        C0205r0 c0205r0 = AbstractC0144g0.a;
        Intrinsics.checkNotNullParameter(data, "<set-?>");
        AbstractC0144g0.f = data;
        return Unit.INSTANCE;
    }

    public static final Unit a(e0 e0Var, C0219t0 c0219t0) {
        ((C0177m0) e0Var.b.getValue()).getClass();
        AbstractC0144g0.g = c0219t0;
        return Unit.INSTANCE;
    }

    public final void a(Bundle bundle) {
        if (!((C0177m0) this.b.getValue()).a()) {
            ((C0177m0) this.b.getValue()).g.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return e0.a(e0.this, (EnumC0152h0) obj);
                }
            }));
        } else if (bundle == null) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
            O1 fragment = new O1();
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
        }
        ((C0177m0) this.b.getValue()).h.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (Card) obj);
            }
        }));
        ((C0177m0) this.b.getValue()).i.observe(getViewLifecycleOwner(), new L(new Function1() { // from class: ch.datatrans.payment.e0$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return e0.a(e0.this, (String) obj);
            }
        }));
    }

    public static final Unit a(e0 e0Var, EnumC0152h0 enumC0152h0) {
        Fragment fragment;
        PaymentMethodType paymentMethodType;
        int i = enumC0152h0 == null ? -1 : H0.a[enumC0152h0.ordinal()];
        if (i == 1) {
            fragment = new r3();
        } else if (i == 2) {
            fragment = new O1();
        } else if (i == 3) {
            ((C0177m0) e0Var.b.getValue()).getClass();
            C0211s0 merchantDCCCurrencyModel = AbstractC0144g0.f;
            ((C0177m0) e0Var.b.getValue()).getClass();
            W0 dccModel = AbstractC0144g0.c;
            C0219t0 c0219t0 = AbstractC0144g0.g;
            dccModel.h = (c0219t0 == null || (paymentMethodType = AbstractC0144g0.d.b.c) == null) ? false : c0219t0.d.contains(paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String());
            ((C0177m0) e0Var.b.getValue()).getClass();
            C3 c3 = AbstractC0144g0.d;
            PaymentMethodType paymentMethodType2 = c3.b.c;
            Intrinsics.checkNotNull(paymentMethodType2);
            String str = c3.a;
            U1 u1 = AbstractC0144g0.b;
            Integer intOrNull = u1.a.length() >= 2 ? StringsKt.toIntOrNull(StringsKt.take(u1.a, 2)) : null;
            Intrinsics.checkNotNull(intOrNull);
            int intValue = intOrNull.intValue();
            U1 u12 = AbstractC0144g0.b;
            Integer intOrNull2 = u12.a.length() == 4 ? StringsKt.toIntOrNull(StringsKt.takeLast(u12.a, 2)) : null;
            Intrinsics.checkNotNull(intOrNull2);
            O2 creditCardDCCModel = new O2(paymentMethodType2, new CardExpiryDate(intValue, intOrNull2.intValue()), str, AbstractC0144g0.a.a);
            Intrinsics.checkNotNullParameter(merchantDCCCurrencyModel, "merchantDCCCurrencyModel");
            Intrinsics.checkNotNullParameter(dccModel, "dccModel");
            Intrinsics.checkNotNullParameter(creditCardDCCModel, "creditCardDCCModel");
            Bundle bundle = new Bundle();
            bundle.putSerializable("MERCHANT_DCC_CURRENCY_MODEL_EXTRA", merchantDCCCurrencyModel);
            bundle.putSerializable("DCC_MODEL_EXTRA", dccModel);
            bundle.putSerializable("CREDIT_CARD_MODEL_EXTRA", creditCardDCCModel);
            fragment = new S0();
            fragment.setArguments(bundle);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        FragmentManager childFragmentManager = e0Var.getChildFragmentManager();
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
        return Unit.INSTANCE;
    }
}
