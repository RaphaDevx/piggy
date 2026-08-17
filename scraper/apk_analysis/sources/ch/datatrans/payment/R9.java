package ch.datatrans.payment;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Unit;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class R9 implements mg {
    public final SharedPreferences a;

    public R9(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context.getSharedPreferences("ch.datatrans.shared_preferences", 0);
    }

    public final Unit a(String str, boolean z) {
        SharedPreferences.Editor edit = this.a.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
        edit.remove(str);
        if (z) {
            Boxing.boxBoolean(edit.commit());
        } else {
            edit.apply();
        }
        return Unit.INSTANCE;
    }

    public final Unit a(String str, String str2, boolean z) {
        SharedPreferences.Editor edit = this.a.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
        edit.putString(str, str2);
        if (z) {
            Boxing.boxBoolean(edit.commit());
        } else {
            edit.apply();
        }
        return Unit.INSTANCE;
    }
}
