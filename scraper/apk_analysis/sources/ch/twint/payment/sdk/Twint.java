package ch.twint.payment.sdk;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import ch.twint.payment.sdk.exceptions.TwintCodeEmptyOrBlankException;
import ch.twint.payment.sdk.exceptions.TwintMethodCalledBeforeOnCreateException;
import ch.twint.payment.sdk.exceptions.TwintResultPendingException;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B/\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000bj\u0002`\r¢\u0006\u0004\b\u000f\u0010\u0010B)\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000bj\u0002`\r¢\u0006\u0004\b\u000f\u0010\u0013B)\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000bj\u0002`\r¢\u0006\u0004\b\u000f\u0010\u0016J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0019"}, d2 = {"Lch/twint/payment/sdk/Twint;", "", "", "code", "", "payWithCode", "registerForUOF", "Landroidx/activity/result/ActivityResultCaller;", "activityResultCaller", "Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistry", "Lkotlin/Function1;", "Lch/twint/payment/sdk/TwintPayResult;", "Lch/twint/payment/sdk/TwintPayResultHandler;", "handler", "<init>", "(Landroidx/activity/result/ActivityResultCaller;Landroidx/savedstate/SavedStateRegistry;Lkotlin/jvm/functions/Function1;)V", "Landroidx/activity/ComponentActivity;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "(Landroidx/activity/ComponentActivity;Lkotlin/jvm/functions/Function1;)V", "Landroidx/fragment/app/Fragment;", "fragment", "(Landroidx/fragment/app/Fragment;Lkotlin/jvm/functions/Function1;)V", "ch/twint/payment/sdk/a", "ch/twint/payment/sdk/b", "TwintSDK_release"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes3.dex */
public final class Twint {
    public final SavedStateRegistry a;
    public final Function1 b;
    public boolean c;
    public final ActivityResultLauncher d;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Twint(androidx.activity.ComponentActivity r3, kotlin.jvm.functions.Function1<? super ch.twint.payment.sdk.TwintPayResult, kotlin.Unit> r4) {
        /*
            r2 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "handler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.savedstate.SavedStateRegistry r0 = r3.getSavedStateRegistry()
            java.lang.String r1 = "<get-savedStateRegistry>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r3, r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.twint.payment.sdk.Twint.<init>(androidx.activity.ComponentActivity, kotlin.jvm.functions.Function1):void");
    }

    public static final Bundle a(Twint this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return BundleKt.bundleOf(TuplesKt.to("ch.twint.payment.sdk.is_awaiting_result", Boolean.valueOf(this$0.c)));
    }

    public final void payWithCode(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        a(code, "ch.twint.action.TWINT_PAYMENT");
    }

    public final void registerForUOF(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        a(code, "ch.twint.action.TWINT_UOF_REGISTRATION");
    }

    public Twint(ActivityResultCaller activityResultCaller, SavedStateRegistry savedStateRegistry, Function1<? super TwintPayResult, Unit> handler) {
        Intrinsics.checkNotNullParameter(activityResultCaller, "activityResultCaller");
        Intrinsics.checkNotNullParameter(savedStateRegistry, "savedStateRegistry");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = savedStateRegistry;
        this.b = handler;
        ActivityResultLauncher registerForActivityResult = activityResultCaller.registerForActivityResult(new b(), new ActivityResultCallback() { // from class: ch.twint.payment.sdk.Twint$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                Twint.a(Twint.this, (TwintPayResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.d = registerForActivityResult;
        savedStateRegistry.registerSavedStateProvider("ch.twint.payment.sdk.saved_state_provider", new SavedStateRegistry.SavedStateProvider() { // from class: ch.twint.payment.sdk.Twint$$ExternalSyntheticLambda1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return Twint.a(Twint.this);
            }
        });
    }

    public final void a(String str, String str2) {
        try {
            Bundle consumeRestoredStateForKey = this.a.consumeRestoredStateForKey("ch.twint.payment.sdk.saved_state_provider");
            boolean z = consumeRestoredStateForKey != null ? consumeRestoredStateForKey.getBoolean("ch.twint.payment.sdk.is_awaiting_result") : false;
            this.c = z;
            if (z) {
                throw new TwintResultPendingException();
            }
            if (StringsKt.isBlank(str)) {
                throw new TwintCodeEmptyOrBlankException();
            }
            try {
                this.c = true;
                this.d.launch(new a(str, str2));
            } catch (ActivityNotFoundException unused) {
                this.c = false;
                this.b.invoke(TwintPayResult.TW_B_APP_NOT_INSTALLED);
            }
        } catch (IllegalStateException unused2) {
            throw new TwintMethodCalledBeforeOnCreateException();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Twint(androidx.fragment.app.Fragment r3, kotlin.jvm.functions.Function1<? super ch.twint.payment.sdk.TwintPayResult, kotlin.Unit> r4) {
        /*
            r2 = this;
            java.lang.String r0 = "fragment"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "handler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.savedstate.SavedStateRegistry r0 = r3.getSavedStateRegistry()
            java.lang.String r1 = "<get-savedStateRegistry>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r3, r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.twint.payment.sdk.Twint.<init>(androidx.fragment.app.Fragment, kotlin.jvm.functions.Function1):void");
    }

    public static final void a(Twint this$0, TwintPayResult twintPayResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = false;
        Function1 function1 = this$0.b;
        Intrinsics.checkNotNull(twintPayResult);
        function1.invoke(twintPayResult);
    }
}
