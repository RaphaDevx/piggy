package ch.datatrans.payment;

import android.content.Context;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;
import lib.android.paypal.com.magnessdk.Environment;
import lib.android.paypal.com.magnessdk.InvalidInputException;
import lib.android.paypal.com.magnessdk.MagnesResult;
import lib.android.paypal.com.magnessdk.MagnesSDK;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.MagnesSource;

/* loaded from: classes3.dex */
public abstract class U8 {
    public static String a(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        MagnesSDK.getInstance().setUp(new MagnesSettings.Builder(context).setMagnesEnvironment(Environment.LIVE).disableRemoteConfig(false).disableBeacon(false).setMagnesSource(MagnesSource.DEFAULT).build());
        try {
            MagnesResult collectAndSubmit = MagnesSDK.getInstance().collectAndSubmit(context, str, null);
            Intrinsics.checkNotNullExpressionValue(collectAndSubmit, "collectAndSubmit(...)");
            String paypalClientMetaDataId = collectAndSubmit.getPaypalClientMetaDataId();
            if (paypalClientMetaDataId != null) {
                paypalClientMetaDataId.length();
            }
            Intrinsics.areEqual(str, paypalClientMetaDataId);
            return paypalClientMetaDataId;
        } catch (InvalidInputException e) {
            String localizedMessage = e.getLocalizedMessage();
            Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
            Log.e("DTPL", localizedMessage);
            return null;
        }
    }
}
