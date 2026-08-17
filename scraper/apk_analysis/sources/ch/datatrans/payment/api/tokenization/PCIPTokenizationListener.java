package ch.datatrans.payment.api.tokenization;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.exception.PCIPTokenizationException;
import io.sentry.SentryEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPTokenizationListener;", "", "onTokenizationSuccess", "", "result", "Lch/datatrans/payment/api/tokenization/PCIPTokenizationSuccess;", "onTokenizationError", SentryEvent.JsonKeys.EXCEPTION, "Lch/datatrans/payment/exception/PCIPTokenizationException;", "onTokenizationCancel", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public interface PCIPTokenizationListener {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        @Deprecated
        public static void onTokenizationCancel(PCIPTokenizationListener pCIPTokenizationListener) {
            PCIPTokenizationListener.super.onTokenizationCancel();
        }
    }

    default void onTokenizationCancel() {
    }

    void onTokenizationError(PCIPTokenizationException exception);

    void onTokenizationSuccess(PCIPTokenizationSuccess result);
}
