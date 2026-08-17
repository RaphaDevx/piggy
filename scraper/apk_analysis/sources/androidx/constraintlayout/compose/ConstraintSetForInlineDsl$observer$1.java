package androidx.constraintlayout.compose;

import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.push.g;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConstraintLayout.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n"}, d2 = {"<anonymous>", "", g.h, "Lkotlin/Function0;"}, k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class ConstraintSetForInlineDsl$observer$1 extends Lambda implements Function1<Function0<? extends Unit>, Unit> {
    final /* synthetic */ ConstraintSetForInlineDsl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ConstraintSetForInlineDsl$observer$1(ConstraintSetForInlineDsl constraintSetForInlineDsl) {
        super(1);
        this.this$0 = constraintSetForInlineDsl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0) {
        invoke2((Function0<Unit>) function0);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final Function0<Unit> it) {
        Handler handler;
        Intrinsics.checkNotNullParameter(it, "it");
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            handler = this.this$0.handler;
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
                this.this$0.handler = handler;
            }
            handler.post(new Runnable() { // from class: androidx.constraintlayout.compose.ConstraintSetForInlineDsl$observer$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ConstraintSetForInlineDsl$observer$1.m3264invoke$lambda1(Function0.this);
                }
            });
            return;
        }
        it.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1, reason: not valid java name */
    public static final void m3264invoke$lambda1(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }
}
