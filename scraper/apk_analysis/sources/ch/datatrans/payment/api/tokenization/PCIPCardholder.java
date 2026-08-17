package ch.datatrans.payment.api.tokenization;

import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.N7;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\tJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u0005R.\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u0005¨\u0006\u001c"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "", "", HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS, "<init>", "(Ljava/lang/String;)V", HintConstants.AUTOFILL_HINT_PHONE_NUMBER, "(Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getEmailAddress", "setEmailAddress", "value", "b", "getPhoneNumber", "setPhoneNumber", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class PCIPCardholder {

    /* renamed from: a, reason: from kotlin metadata */
    public String emailAddress;

    /* renamed from: b, reason: from kotlin metadata */
    public String phoneNumber;

    /* JADX WARN: Multi-variable type inference failed */
    public PCIPCardholder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ PCIPCardholder copy$default(PCIPCardholder pCIPCardholder, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pCIPCardholder.emailAddress;
        }
        return pCIPCardholder.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEmailAddress() {
        return this.emailAddress;
    }

    public final PCIPCardholder copy(String emailAddress) {
        return new PCIPCardholder(emailAddress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PCIPCardholder) && Intrinsics.areEqual(this.emailAddress, ((PCIPCardholder) other).emailAddress);
    }

    public final String getEmailAddress() {
        return this.emailAddress;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int hashCode() {
        String str = this.emailAddress;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final void setEmailAddress(String str) {
        this.emailAddress = str;
    }

    public final void setPhoneNumber(String str) {
        String replace$default;
        String replace$default2;
        String replace$default3;
        String replace$default4 = (str == null || (replace$default = StringsKt.replace$default(str, StringUtils.SPACE, "", false, 4, (Object) null)) == null || (replace$default2 = StringsKt.replace$default(replace$default, "(", "", false, 4, (Object) null)) == null || (replace$default3 = StringsKt.replace$default(replace$default2, ")", "", false, 4, (Object) null)) == null) ? null : StringsKt.replace$default(replace$default3, "-", "", false, 4, (Object) null);
        this.phoneNumber = replace$default4;
        if (replace$default4 != null) {
            Intrinsics.checkNotNullParameter(replace$default4, "<this>");
            if (replace$default4.charAt(0) == '+') {
                String substring = replace$default4.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                for (int i = 0; i < substring.length(); i++) {
                    if (Character.isDigit(substring.charAt(i))) {
                    }
                }
                return;
            }
            Log.w("DTPL", "PCIPCardholder.phoneNumber (" + replace$default4 + ") is invalid. Numbers must start with a plus (+) sign followed by digits between 0 and 9.");
        }
    }

    public String toString() {
        return N7.a(new StringBuilder("PCIPCardholder(emailAddress="), this.emailAddress, ')');
    }

    public PCIPCardholder(String str) {
        this.emailAddress = str;
    }

    public /* synthetic */ PCIPCardholder(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    public PCIPCardholder(String str, String str2) {
        this(str);
        setPhoneNumber(str2);
    }
}
