package ch.datatrans.payment.bottomsheet.tokenization;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.AbstractActivityC0147g4;
import ch.datatrans.payment.C0247w8;
import ch.datatrans.payment.C0253x8;
import ch.datatrans.payment.F;
import ch.datatrans.payment.Pa;
import ch.datatrans.payment.R;
import ch.datatrans.payment.R7;
import ch.datatrans.payment.S7;
import ch.datatrans.payment.T7;
import ch.datatrans.payment.U7;
import ch.datatrans.payment.V7;
import ch.datatrans.payment.W7;
import ch.datatrans.payment.Z9;
import ch.datatrans.payment.api.InitialLoader;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess;
import ch.datatrans.payment.e0;
import ch.datatrans.payment.exception.PCIPTokenizationException;
import ch.datatrans.payment.f0;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.t4;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/bottomsheet/tokenization/PCIPTokenizationRequestSheetActivity;", "Lch/datatrans/payment/g4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PCIPTokenizationRequestSheetActivity extends AbstractActivityC0147g4 {
    public static final /* synthetic */ int q = 0;
    public final ViewModelLazy o = new ViewModelLazy(Reflection.getOrCreateKotlinClass(C0247w8.class), new S7(this), new Function0() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this);
        }
    }, new T7(this));
    public final ViewModelLazy p = new ViewModelLazy(Reflection.getOrCreateKotlinClass(f0.class), new V7(this), new U7(this), new W7(this));

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, String cvv) {
        C0247w8 c0247w8 = (C0247w8) pCIPTokenizationRequestSheetActivity.o.getValue();
        Intrinsics.checkNotNull(cvv);
        c0247w8.getClass();
        Intrinsics.checkNotNullParameter(cvv, "cvv");
        c0247w8.q = cvv;
        c0247w8.a(true);
        return Unit.INSTANCE;
    }

    public static final Unit b(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, Boolean bool) {
        ((F) pCIPTokenizationRequestSheetActivity.l.getValue()).a.postValue(bool);
        return Unit.INSTANCE;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        if (tokenization != null) {
            super.attachBaseContext(AbstractActivityC0147g4.a(base, tokenization.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().getLanguage()));
        } else {
            Log.d("DTPL", "PCIP tokenization request not found, skipping applying of language");
            super.attachBaseContext(base);
        }
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final void c() {
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        if (tokenization != null) {
            tokenization.cancel$lib_release();
        }
        finish();
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final void d() {
        InitialLoader customInitialLoader = ((C0247w8) this.o.getValue()).f.getCustomInitialLoader();
        if (customInitialLoader != null) {
            customInitialLoader.dismiss();
        }
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4
    public final boolean e() {
        return ((C0247w8) this.o.getValue()).f.getSuppressCriticalErrorDialog();
    }

    public final void i() {
        if (((C0247w8) this.o.getValue()).d) {
            Z9 text = new Z9(R.string.datatrans_sdk_confirm_registration_button, new Object[0]);
            f0 f0Var = (f0) this.p.getValue();
            f0Var.getClass();
            Intrinsics.checkNotNullParameter(text, "text");
            f0Var.b.postValue(text);
            ((f0) this.p.getValue()).g.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (String) obj);
                }
            }));
            return;
        }
        f0 f0Var2 = (f0) this.p.getValue();
        ArrayList cards = ((C0247w8) this.o.getValue()).g;
        Intrinsics.checkNotNull(cards);
        f0Var2.getClass();
        Intrinsics.checkNotNullParameter(cards, "cards");
        f0Var2.a.postValue(cards);
        Z9 text2 = new Z9(R.string.datatrans_sdk_confirm_registration_button, new Object[0]);
        f0 f0Var3 = (f0) this.p.getValue();
        f0Var3.getClass();
        Intrinsics.checkNotNullParameter(text2, "text");
        f0Var3.b.postValue(text2);
        ((f0) this.p.getValue()).e.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (Card) obj);
            }
        }));
    }

    public final void j() {
        ((C0247w8) this.o.getValue()).i.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (t4) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).j.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (PCIPTokenizationException) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).k.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (Unit) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).l.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (PCIPTokenizationSuccess) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).m.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.b(PCIPTokenizationRequestSheetActivity.this, (Unit) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).n.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.a(PCIPTokenizationRequestSheetActivity.this, (Boolean) obj);
            }
        }));
        ((C0247w8) this.o.getValue()).o.observe(this, new R7(new Function1() { // from class: ch.datatrans.payment.bottomsheet.tokenization.PCIPTokenizationRequestSheetActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PCIPTokenizationRequestSheetActivity.b(PCIPTokenizationRequestSheetActivity.this, (Boolean) obj);
            }
        }));
    }

    @Override // ch.datatrans.payment.AbstractActivityC0147g4, ch.datatrans.payment.C, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PCIPTokenizationRegistry.INSTANCE.getTokenization() == null) {
            Log.e("DTPL", "PCIP tokenization request not found, something went wrong");
            finish();
        } else {
            j();
            i();
        }
    }

    public static final Unit b(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, Unit unit) {
        pCIPTokenizationRequestSheetActivity.d();
        ((F) pCIPTokenizationRequestSheetActivity.l.getValue()).a.postValue(Boolean.TRUE);
        e0 fragment = new e0();
        FragmentManager supportFragmentManager = pCIPTokenizationRequestSheetActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        Intrinsics.checkNotNullParameter(supportFragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        boolean isEmpty = supportFragmentManager.getFragments().isEmpty();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (!isEmpty) {
            beginTransaction.setCustomAnimations(R.anim.dtpl_slide_left_in, R.anim.dtpl_slide_left_out, R.anim.dtpl_slide_right_in, R.anim.dtpl_slide_right_out);
            beginTransaction.addToBackStack("bottom_sheet");
        }
        beginTransaction.replace(R.id.container, fragment, (String) null);
        beginTransaction.commit();
        return Unit.INSTANCE;
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, Card card) {
        C0247w8 c0247w8 = (C0247w8) pCIPTokenizationRequestSheetActivity.o.getValue();
        Intrinsics.checkNotNull(card);
        c0247w8.getClass();
        Intrinsics.checkNotNullParameter(card, "card");
        c0247w8.p = card;
        c0247w8.a(true);
        return Unit.INSTANCE;
    }

    public static final ViewModelProvider.Factory a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity) {
        Application application = pCIPTokenizationRequestSheetActivity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
        return new C0253x8(application);
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) pCIPTokenizationRequestSheetActivity.n.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) pCIPTokenizationRequestSheetActivity.n.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.C
    public final boolean a() {
        return getSupportFragmentManager().getFragments().isEmpty();
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, PCIPTokenizationException pCIPTokenizationException) {
        Intrinsics.checkNotNull(pCIPTokenizationException);
        pCIPTokenizationRequestSheetActivity.getClass();
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        if (tokenization != null) {
            tokenization.fail$lib_release(pCIPTokenizationException);
        }
        pCIPTokenizationRequestSheetActivity.finish();
        return Unit.INSTANCE;
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, Unit unit) {
        if (pCIPTokenizationRequestSheetActivity.getSupportFragmentManager().getFragments().isEmpty()) {
            pCIPTokenizationRequestSheetActivity.c();
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, PCIPTokenizationSuccess pCIPTokenizationSuccess) {
        Intrinsics.checkNotNull(pCIPTokenizationSuccess);
        pCIPTokenizationRequestSheetActivity.getClass();
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        if (tokenization != null) {
            tokenization.finish$lib_release(pCIPTokenizationSuccess);
        }
        pCIPTokenizationRequestSheetActivity.finish();
        return Unit.INSTANCE;
    }

    public static final Unit a(PCIPTokenizationRequestSheetActivity pCIPTokenizationRequestSheetActivity, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        pCIPTokenizationRequestSheetActivity.c(bool.booleanValue());
        return Unit.INSTANCE;
    }
}
