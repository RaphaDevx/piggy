package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.N7;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0007J\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0007¨\u0006\u0015"}, d2 = {"Lch/datatrans/payment/paymentmethods/AuthorizationInitRequestConfig;", "", "", "appCallbackUrl", "<init>", "(Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/AuthorizationInitRequestConfig;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getAppCallbackUrl", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class AuthorizationInitRequestConfig {

    /* renamed from: a, reason: from kotlin metadata */
    public final String appCallbackUrl;

    public AuthorizationInitRequestConfig(String appCallbackUrl) {
        Intrinsics.checkNotNullParameter(appCallbackUrl, "appCallbackUrl");
        this.appCallbackUrl = appCallbackUrl;
    }

    public static /* synthetic */ AuthorizationInitRequestConfig copy$default(AuthorizationInitRequestConfig authorizationInitRequestConfig, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authorizationInitRequestConfig.appCallbackUrl;
        }
        return authorizationInitRequestConfig.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppCallbackUrl() {
        return this.appCallbackUrl;
    }

    public final AuthorizationInitRequestConfig copy(String appCallbackUrl) {
        Intrinsics.checkNotNullParameter(appCallbackUrl, "appCallbackUrl");
        return new AuthorizationInitRequestConfig(appCallbackUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AuthorizationInitRequestConfig) && Intrinsics.areEqual(this.appCallbackUrl, ((AuthorizationInitRequestConfig) other).appCallbackUrl);
    }

    public final String getAppCallbackUrl() {
        return this.appCallbackUrl;
    }

    public int hashCode() {
        return this.appCallbackUrl.hashCode();
    }

    public String toString() {
        return N7.a(new StringBuilder("AuthorizationInitRequestConfig(appCallbackUrl="), this.appCallbackUrl, ')');
    }
}
