package ch.datatrans.payment.api.tokenization;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toJSONObject", "Lorg/json/JSONObject;", "Lch/datatrans/payment/api/tokenization/PCIPCardholder;", "lib_release"}, k = 2, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PCIPCardholderKt {
    public static final JSONObject toJSONObject(PCIPCardholder pCIPCardholder) {
        Intrinsics.checkNotNullParameter(pCIPCardholder, "<this>");
        if (pCIPCardholder.getEmailAddress() == null && pCIPCardholder.getAndroidx.autofill.HintConstants.AUTOFILL_HINT_PHONE_NUMBER java.lang.String() == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        String emailAddress = pCIPCardholder.getEmailAddress();
        if (emailAddress != null) {
            jSONObject.put(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS, emailAddress);
        }
        String str = pCIPCardholder.getAndroidx.autofill.HintConstants.AUTOFILL_HINT_PHONE_NUMBER java.lang.String();
        if (str != null) {
            jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, str);
        }
        return jSONObject;
    }
}
