package ch.datatrans.payment.api.tokenization;

import android.app.Activity;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001J\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\nR(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPTokenizationRegistry;", "", "Landroid/app/Activity;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Lch/datatrans/payment/api/tokenization/PCIPTokenization;", "tokenization", "", "startTokenization", "(Landroid/app/Activity;Lch/datatrans/payment/api/tokenization/PCIPTokenization;)V", "clear$lib_release", "()V", "clear", "value", "a", "Lch/datatrans/payment/api/tokenization/PCIPTokenization;", "getTokenization", "()Lch/datatrans/payment/api/tokenization/PCIPTokenization;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PCIPTokenizationRegistry {
    public static final PCIPTokenizationRegistry INSTANCE = new PCIPTokenizationRegistry();

    /* renamed from: a, reason: from kotlin metadata */
    public static PCIPTokenization tokenization;

    public final void clear$lib_release() {
        tokenization = null;
    }

    public final PCIPTokenization getTokenization() {
        return tokenization;
    }

    public final void startTokenization(Activity activity, PCIPTokenization tokenization2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(tokenization2, "tokenization");
        if (tokenization != null) {
            Log.e("DTPL", "A tokenization request is already in progress.");
        } else {
            tokenization = tokenization2;
            tokenization2.start$lib_release(activity);
        }
    }
}
