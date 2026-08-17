package ch.datatrans.payment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.api.tokenization.PCIPTokenization;
import ch.datatrans.payment.api.tokenization.PCIPTokenizationRegistry;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* renamed from: ch.datatrans.payment.g4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractActivityC0147g4 extends C {
    public final ViewModelLazy n = new ViewModelLazy(Reflection.getOrCreateKotlinClass(Pa.class), new X3(this), new W3(this), new Y3(this));

    public static final Unit a(AbstractActivityC0147g4 abstractActivityC0147g4, Oa oa) {
        CharSequence title = oa.a;
        boolean z = oa.b;
        String str = oa.c;
        abstractActivityC0147g4.getClass();
        Intrinsics.checkNotNullParameter(title, "title");
        E.a(abstractActivityC0147g4, title, z, str);
        return Unit.INSTANCE;
    }

    public static final Unit b(AbstractActivityC0147g4 abstractActivityC0147g4, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        abstractActivityC0147g4.c(bool.booleanValue());
        abstractActivityC0147g4.findViewById(R.id.loadingBox).announceForAccessibility(abstractActivityC0147g4.getText(R.string.datatrans_sdk_accessibility_loading));
        return Unit.INSTANCE;
    }

    public static final Unit c(AbstractActivityC0147g4 abstractActivityC0147g4, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        abstractActivityC0147g4.a(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public abstract void c();

    public abstract void d();

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public abstract boolean e();

    public final void f() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        E.a(supportFragmentManager, new Function0() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AbstractActivityC0147g4.a(AbstractActivityC0147g4.this);
            }
        });
    }

    public final void g() {
        ((Pa) this.n.getValue()).a.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.a(AbstractActivityC0147g4.this, (Boolean) obj);
            }
        }));
        ((Pa) this.n.getValue()).b.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.b(AbstractActivityC0147g4.this, (Boolean) obj);
            }
        }));
        ((Pa) this.n.getValue()).c.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.c(AbstractActivityC0147g4.this, (Boolean) obj);
            }
        }));
        ((Pa) this.n.getValue()).e.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.a(AbstractActivityC0147g4.this, (Oa) obj);
            }
        }));
        ((Pa) this.n.getValue()).f.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.a(AbstractActivityC0147g4.this, (Unit) obj);
            }
        }));
        ((Pa) this.n.getValue()).g.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.b(AbstractActivityC0147g4.this, (Unit) obj);
            }
        }));
    }

    public final void h() {
        ((Pa) this.n.getValue()).d.observe(this, new U3(new Function1() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractActivityC0147g4.a(AbstractActivityC0147g4.this, (t4) obj);
            }
        }));
    }

    @Override // ch.datatrans.payment.C, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Window window;
        Window window2;
        super.onCreate(bundle);
        h();
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        if (transactionRegistry.isTransactionFailure$lib_release(this)) {
            c();
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new S3(this, null), 2, null);
        Transaction transaction = transactionRegistry.getTransaction();
        if (transaction != null && !transaction.getOptions().getIsTesting() && (window2 = getWindow()) != null) {
            window2.addFlags(8192);
        }
        PCIPTokenization tokenization = PCIPTokenizationRegistry.INSTANCE.getTokenization();
        if (tokenization != null && !tokenization.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().getIsTesting() && (window = getWindow()) != null) {
            window.addFlags(8192);
        }
        if (!getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(1);
        }
        g();
        getOnBackPressedDispatcher().addCallback(this, new T3(this));
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        ((Pa) this.n.getValue()).f.postValue(Unit.INSTANCE);
        return true;
    }

    public static final Unit b(AbstractActivityC0147g4 abstractActivityC0147g4, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int i = ue.l;
        FragmentManager fragmentManager = abstractActivityC0147g4.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(fragmentManager, "getSupportFragmentManager(...)");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        ue ueVar = new ue();
        ueVar.show(fragmentManager, "WEB_FRAGMENT");
        Intrinsics.checkNotNullParameter(ueVar, "<set-?>");
        return Unit.INSTANCE;
    }

    public static final Unit a(AbstractActivityC0147g4 abstractActivityC0147g4, t4 t4Var) {
        abstractActivityC0147g4.a(t4Var);
        return Unit.INSTANCE;
    }

    public static final Unit a(AbstractActivityC0147g4 abstractActivityC0147g4, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        boolean booleanValue = bool.booleanValue();
        ActionBar supportActionBar = abstractActivityC0147g4.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(booleanValue);
            if (booleanValue) {
                supportActionBar.setHomeAsUpIndicator(R.drawable.dtpl_arrow_back);
            }
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(AbstractActivityC0147g4 abstractActivityC0147g4, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        abstractActivityC0147g4.f();
        return Unit.INSTANCE;
    }

    public final void a(t4 t4Var) {
        if (e() && (t4Var instanceof C0178m4)) {
            ((C0178m4) t4Var).c.invoke();
        } else {
            a(t4Var, new Function0() { // from class: ch.datatrans.payment.g4$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AbstractActivityC0147g4.b(AbstractActivityC0147g4.this);
                }
            });
        }
    }

    public static final Unit a(AbstractActivityC0147g4 abstractActivityC0147g4) {
        abstractActivityC0147g4.c();
        return Unit.INSTANCE;
    }

    public static Context a(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (str == null) {
            return context;
        }
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        Intrinsics.checkNotNull(createConfigurationContext);
        return createConfigurationContext;
    }

    public static final Unit b(AbstractActivityC0147g4 abstractActivityC0147g4) {
        abstractActivityC0147g4.d();
        return Unit.INSTANCE;
    }
}
