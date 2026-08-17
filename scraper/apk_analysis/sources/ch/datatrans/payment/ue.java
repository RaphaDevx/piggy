package ch.datatrans.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.web.WebLoadingIndicator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lch/datatrans/payment/ue;", "Landroidx/fragment/app/DialogFragment;", "Landroidx/appcompat/widget/Toolbar$OnMenuItemClickListener;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class ue extends DialogFragment implements Toolbar.OnMenuItemClickListener {
    public static final /* synthetic */ int l = 0;
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public final Lazy d;
    public WebView e;
    public K9 f;
    public WebLoadingIndicator g;
    public SwipeRefreshLayout h;
    public Toolbar i;
    public final v4 j;
    public final C0161ie k;

    public ue() {
        Function0 a = Gd.a(new Ad(this), Pa.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Jc.class);
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        Function0 a3 = Gd.a(new Ad(this), C0182n.class);
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0182n.class), new xd(this), new yd(this), a3 == null ? new C0260zd(this) : a3);
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new qe(new pe(this)));
        this.d = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Ee.class), new re(lazy), new se(lazy), new te(this, lazy));
        this.j = new v4();
        this.k = new C0161ie(this);
    }

    public static final Unit b(ue ueVar) {
        Xa xa = ((Pa) ueVar.a.getValue()).f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public final void a() {
        Be g;
        Ae ae = ((Ee) this.d.getValue()).a;
        if (ae != null && (g = ae.g()) != null) {
            g.c();
        }
        requireActivity().unregisterReceiver(this.k);
        super.dismiss();
    }

    public final boolean c() {
        ((Jc) this.b.getValue()).getClass();
        return TransactionRegistry.INSTANCE.getTransactionModel$lib_release().i != PaymentMethodType.IDEAL;
    }

    public final void d() {
        WebLoadingIndicator webLoadingIndicator = this.g;
        WebView webView = null;
        if (webLoadingIndicator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingIndicator");
            webLoadingIndicator = null;
        }
        webLoadingIndicator.b();
        Ae ae = ((Pa) this.a.getValue()).h;
        if (ae == null) {
            throw new IllegalArgumentException("WebProcess not available");
        }
        AbstractC0200q5 c = ae.c();
        K9 k9 = this.f;
        if (k9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewClient");
            k9 = null;
        }
        Ae ae2 = ((Pa) this.a.getValue()).h;
        if (ae2 == null) {
            throw new IllegalArgumentException("WebProcess not available");
        }
        String viewPortAdjustment = ae2.a();
        Ae ae3 = ((Pa) this.a.getValue()).h;
        if (ae3 == null) {
            throw new IllegalArgumentException("WebProcess not available");
        }
        int b = ae3.b();
        k9.getClass();
        Intrinsics.checkNotNullParameter(viewPortAdjustment, "viewPortAdjustment");
        k9.d = viewPortAdjustment;
        k9.e = b;
        if (c instanceof C0196p5) {
            WebView webView2 = this.e;
            if (webView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView = webView2;
            }
            webView.postUrl(c.a, ((C0196p5) c).b);
            return;
        }
        WebView webView3 = this.e;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView = webView3;
        }
        webView.loadUrl(c.a);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final void dismiss() {
        requireActivity().unregisterReceiver(this.k);
        super.dismiss();
    }

    public final void e() {
        SwipeRefreshLayout swipeRefreshLayout = this.h;
        SwipeRefreshLayout swipeRefreshLayout2 = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        if (((Pa) this.a.getValue()).h == null) {
            throw new IllegalArgumentException("WebProcess not available");
        }
        swipeRefreshLayout.setEnabled(!r3.d());
        SwipeRefreshLayout swipeRefreshLayout3 = this.h;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout3 = null;
        }
        swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ue.a(ue.this);
            }
        });
        SwipeRefreshLayout swipeRefreshLayout4 = this.h;
        if (swipeRefreshLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        } else {
            swipeRefreshLayout2 = swipeRefreshLayout4;
        }
        swipeRefreshLayout2.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnChildScrollUpCallback
            public final boolean canChildScrollUp(SwipeRefreshLayout swipeRefreshLayout5, View view) {
                return ue.a(ue.this, swipeRefreshLayout5, view);
            }
        });
    }

    public final void f() {
        Toolbar toolbar = this.i;
        Toolbar toolbar2 = null;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        toolbar.setNavigationIcon(c() ? ContextCompat.getDrawable(requireContext(), R.drawable.dtpl_ic_close_24) : null);
        Toolbar toolbar3 = this.i;
        if (toolbar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar3 = null;
        }
        toolbar3.setNavigationContentDescription(R.string.datatrans_sdk_cancel);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        int i = R.drawable.dtpl_ic_lock_24;
        SpannableString spannableString = new SpannableString("  Datatrans");
        Drawable drawable = ContextCompat.getDrawable(requireContext, i);
        if (drawable == null) {
            throw new IllegalArgumentException(("Drawable with resourceId " + i + " not found").toString());
        }
        drawable.setBounds(new Rect(0, 0, AbstractC0184n4.a(16), AbstractC0184n4.a(16)));
        drawable.setTint(ContextCompat.getColor(requireContext, R.color.dtpl_web_toolbar_link));
        spannableString.setSpan(new C0199q0(drawable, AbstractC0184n4.a(-1)), 0, 1, 33);
        Toolbar toolbar4 = this.i;
        if (toolbar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar4 = null;
        }
        ((TextView) toolbar4.findViewById(R.id.toolbarTitle)).setText(spannableString);
        Toolbar toolbar5 = this.i;
        if (toolbar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar5 = null;
        }
        toolbar5.setNavigationOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ue.a(ue.this, view);
            }
        });
        Toolbar toolbar6 = this.i;
        if (toolbar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar6 = null;
        }
        toolbar6.inflateMenu(R.menu.dtpl_web_fragment);
        Toolbar toolbar7 = this.i;
        if (toolbar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar7 = null;
        }
        MenuItem findItem = toolbar7.getMenu().findItem(R.id.refresh);
        if (((Pa) this.a.getValue()).h == null) {
            throw new IllegalArgumentException("WebProcess not available");
        }
        findItem.setVisible(!r3.d());
        Toolbar toolbar8 = this.i;
        if (toolbar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        } else {
            toolbar2 = toolbar8;
        }
        toolbar2.setOnMenuItemClickListener(this);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.DTPLWebFragmentTheme);
        ((Ee) this.d.getValue()).a = ((Pa) this.a.getValue()).h;
        ((Ee) this.d.getValue()).c.observe(this, new oe(new Function1() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ue.a(ue.this, (Unit) obj);
            }
        }));
        ((Ee) this.d.getValue()).d.observe(this, new oe(new Function1() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ue.b(ue.this, (Unit) obj);
            }
        }));
        ((Ee) this.d.getValue()).e.observe(this, new oe(new Function1() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ue.c(ue.this, (Unit) obj);
            }
        }));
        ContextCompat.registerReceiver(requireContext(), this.k, new IntentFilter(ExternalProcessRelayActivity.IDEAL_ACTION), 4);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Window window;
        me meVar = new me(this, requireContext(), getTheme());
        meVar.requestWindowFeature(1);
        ((Jc) this.b.getValue()).getClass();
        if (!TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.v && (window = meVar.getWindow()) != null) {
            window.addFlags(8192);
        }
        return meVar;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_web_fragment, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.i = (Toolbar) findViewById;
        View findViewById2 = inflate.findViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.g = (WebLoadingIndicator) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.h = (SwipeRefreshLayout) findViewById3;
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.webViewContainer);
        try {
            Intrinsics.checkNotNull(viewGroup2);
            a(viewGroup2);
            f();
            e();
        } catch (IllegalArgumentException unused) {
            requireActivity().unregisterReceiver(this.k);
            super.dismiss();
            b();
        }
        ViewCompat.setOnApplyWindowInsetsListener(inflate, new OnApplyWindowInsetsListener() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda7
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return ue.a(view, windowInsetsCompat);
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        WebView webView = this.e;
        if (webView != null) {
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.stopLoading();
        }
        super.onDestroy();
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == 16908332) {
            if (c()) {
                a();
            } else {
                WebView webView = this.e;
                if (webView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webView");
                    webView = null;
                }
                webView.loadUrl("javascript:history.go(-1)");
            }
        } else if (itemId == R.id.refresh) {
            d();
        }
        return onOptionsItemSelected(item);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        Window window;
        super.onResume();
        if (requireContext().getResources().getBoolean(R.bool.isTablet)) {
            Rect rect = new Rect();
            requireActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int width = rect.width();
            int height = rect.height();
            double d = width;
            int i = (int) (width > height ? d * 0.7d : d * 0.9d);
            int i2 = (int) (height * 0.9d);
            Dialog dialog = getDialog();
            if (dialog == null || (window = dialog.getWindow()) == null) {
                return;
            }
            window.setLayout(i, i2);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        ((Jc) this.b.getValue()).k.postValue(Unit.INSTANCE);
        G9.a.set(false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        try {
            d();
        } catch (IllegalArgumentException unused) {
        }
    }

    public static final Unit b(ue ueVar, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int i = R.string.datatrans_sdk_error_message_connection_retry;
        ueVar.getClass();
        new AlertDialog.Builder(ueVar.requireContext()).setTitle(R.string.datatrans_sdk_error_title_generic).setMessage(i).setPositiveButton(R.string.datatrans_sdk_error_alert_ok, (DialogInterface.OnClickListener) null).show();
        return Unit.INSTANCE;
    }

    public static final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    public static final Unit c(ue ueVar) {
        ((Jc) ueVar.b.getValue()).d();
        return Unit.INSTANCE;
    }

    public static final Unit c(ue ueVar, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int i = R.string.datatrans_sdk_error_message_no_secure_connection;
        ueVar.getClass();
        new AlertDialog.Builder(ueVar.requireContext()).setTitle(R.string.datatrans_sdk_error_title_generic).setMessage(i).setPositiveButton(R.string.datatrans_sdk_error_alert_ok, (DialogInterface.OnClickListener) null).show();
        return Unit.INSTANCE;
    }

    public final void b() {
        TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (transactionRegistry.isTransactionFailure$lib_release(requireActivity)) {
            Pa pa = (Pa) this.a.getValue();
            C7 c7 = r4.a;
            C0178m4 errorModel = v4.a(new Function0() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ue.c(ue.this);
                }
            });
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
            return;
        }
        Pa pa2 = (Pa) this.a.getValue();
        v4 v4Var = this.j;
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.ue$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ue.b(ue.this);
            }
        };
        v4Var.getClass();
        C0178m4 errorModel2 = v4.a(function0);
        pa2.getClass();
        Intrinsics.checkNotNullParameter(errorModel2, "errorModel");
        pa2.d.postValue(errorModel2);
    }

    public static final void a(ue ueVar, View view) {
        if (ueVar.c()) {
            ueVar.a();
            return;
        }
        WebView webView = ueVar.e;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.loadUrl("javascript:history.go(-1)");
    }

    public static final Unit a(ue ueVar, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ueVar.requireActivity().unregisterReceiver(ueVar.k);
        super.dismiss();
        return Unit.INSTANCE;
    }

    public static final void a(ue ueVar) {
        SwipeRefreshLayout swipeRefreshLayout = ueVar.h;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setRefreshing(false);
        ueVar.d();
    }

    public static final boolean a(ue ueVar, SwipeRefreshLayout swipeRefreshLayout, View view) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<unused var>");
        WebView webView = ueVar.e;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        return webView.getScrollY() > 10;
    }

    public final void a(ViewGroup viewGroup) {
        Context requireContext = requireContext();
        WebView webView = new WebView(requireContext);
        webView.setBackgroundColor(ContextCompat.getColor(requireContext, R.color.dtpl_background));
        webView.setDefaultFocusHighlightEnabled(false);
        WebSettings settings = webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "getSettings(...)");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        Ee ee = (Ee) this.d.getValue();
        WebLoadingIndicator webLoadingIndicator = this.g;
        WebView webView2 = null;
        if (webLoadingIndicator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingIndicator");
            webLoadingIndicator = null;
        }
        K9 k9 = new K9(ee, webLoadingIndicator, true ^ c());
        this.f = k9;
        webView.setWebViewClient(k9);
        this.e = webView;
        if (WebViewFeature.isFeatureSupported("FORCE_DARK")) {
            WebView webView3 = this.e;
            if (webView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView2 = webView3;
            }
            WebSettings settings2 = webView2.getSettings();
            Intrinsics.checkNotNullExpressionValue(settings2, "getSettings(...)");
            int i = getResources().getConfiguration().uiMode & 48;
            if (i == 0 || i == 16) {
                WebSettingsCompat.setForceDark(settings2, 0);
            } else if (i == 32) {
                WebSettingsCompat.setForceDark(settings2, 2);
            }
        }
        viewGroup.addView(webView);
    }
}
