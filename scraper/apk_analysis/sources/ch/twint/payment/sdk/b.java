package ch.twint.payment.sdk;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class b extends ActivityResultContract {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Intent createIntent(Context context, Object obj) {
        a input = (a) obj;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent putExtra = new Intent(input.b).putExtra("code", input.a);
        Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
        return putExtra;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final ActivityResultContract.SynchronousResult getSynchronousResult(Context context, Object obj) {
        a input = (a) obj;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        return null;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Object parseResult(int i, Intent intent) {
        return i == -1 ? TwintPayResult.TW_B_SUCCESS : TwintPayResult.TW_B_ERROR;
    }
}
